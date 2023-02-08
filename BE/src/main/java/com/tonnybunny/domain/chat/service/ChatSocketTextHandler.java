package com.tonnybunny.domain.chat.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tonnybunny.domain.chat.dto.ChatAlertDto;
import com.tonnybunny.domain.chat.dto.ChatLogDto;
import com.tonnybunny.domain.chat.dto.ParticipantDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RequiredArgsConstructor
@Component
public class ChatSocketTextHandler extends TextWebSocketHandler {

	//	private final ChatService chatService;
	private final RedisTemplate<String, Object> redisTemplate;
	private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
	private final SimpMessagingTemplate template;
	/**
	 * userSeq:roomId:anotherUserSeq 정보 저장 -> 같은 방 다른 유저를 쉽게 찾기 위함
	 * 데이터 추가 시점 : 한 명이라도 방을 만들면 -> 양쪽다 만듦
	 */
	/**
	 * "USER_MAP", userSeq : {roomSeq : anotherUserSeq}
	 */
	private HashOperations<String, String, Map<String, Long>> userMapInfo;

	/**
	 * 방에 포함된 사람의 세션 정보 저장
	 * 데이터 추가 시점 : 한 명이라도 방을 만들면 -> 양쪽 다 만듦 (port는 비워둠)
	 */
	/**
	 * "CHAT_ROOM", roomSeq, {userSeq1: {...}, userSeq2: {...}}
	 */
	private HashOperations<String, String, Map<String, ParticipantDto>> roomInfo;

	/**
	 * 각 방에 오고간 채팅 로그 저장
	 * 데이터 추가 시점 : 메세지 보냈을 때
	 */
	/**
	 * "CHAT_LOG", roomSeq, [{userSeq, msg, ...}, ...]
	 */
	private HashOperations<String, String, List<ChatLogDto>> chatLogInfo;

	/**
	 * 유저가 각 방 마다 안 읽은 채팅 메세지 카운트 저장
	 * 초기 status : false여야함.
	 * 채팅방 들어올 때 true, 채팅방 나갈 때 false
	 * -> 근데 status 하나만 연결하면, 브라우저 여러개 열어서 동시에 여러 채팅방 들어갔을 때, 하나라도 나가면 false가 돼서 다른 채팅방 나가버린 상태가 됨.
	 * -> 이건 방 마다 status 가 바뀌기 때문에 상관없음!
	 *
	 * -> 브라우저 여러개 열어서 동시에 같은 채팅방을 열었을 때 문제가 됨.
	 * => status를 boolean으로 하지 말고, Integer 카운트로 셈하자!
	 *
	 * 데이터 추가 시점 : 방이 만들어 졌을 때 -> 양쪽 다 만듦 (count는 0)
	 */
	/**
	 * "CHAT_NO_ENTER", userSeq, { roomSeq1: 0, roomSeq2: 2}
	 */
	private HashOperations<String, String, Map<String, Integer>> notReadInfo; // "CHAT_NO_ENTER", userSeq, { roomSeq1: 0, roomSeq2: 2}


	@PostConstruct
	public void init() {
		//	public ChatSocketTextHandler(RedisTemplate<String, Object> redisTemplate) {
		System.out.println(" !!! Constructor !!! ");
		//		this.chatService = new ChatService();
		this.userMapInfo = redisTemplate.opsForHash();
		this.roomInfo = redisTemplate.opsForHash();
		this.chatLogInfo = redisTemplate.opsForHash();
		this.notReadInfo = redisTemplate.opsForHash();
	}


	/**
	 * 클라이언트와 서버 간 소켓 연결이 수립되었을 때 수행되는 핸들러
	 *
	 * @param session
	 * @throws Exception
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(String.format("[접속] id:%s, uri: %s, port: %s, remote address: %s, local address: %s, ",
			session.getId(), session.getUri(), session.getRemoteAddress().getPort(),
			session.getRemoteAddress().getAddress().toString(), session.getLocalAddress().toString()));
		sessions.add(session);
	}


	/**
	 * 클라이언트로부터 메세지를 받았을 때 수행되는 핸들러
	 *
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		JSONObject jsonObject = new JSONObject(payload);
		switch (jsonObject.getString("type")) {
		case "exit": { // 채팅방에 들어가지 않은 상태
			String roomId = jsonObject.getString("roomId");
			String userSeq = jsonObject.getString("userSeq");
			// 입장 카운트 감소함
			decreaseEnterRoomCount(roomId, userSeq);
			break;
		}
		case "enter": { // 채팅방에 들어간 상태
			String roomId = jsonObject.getString("roomId");
			Long userSeq = Long.valueOf(jsonObject.getString("userSeq"));
			Long anotherSeq = Long.valueOf(jsonObject.getString("anotherSeq"));

			//			System.out.println(" enter Setting " + roomId + " \n\tRoomStatus : " + roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId)
			//				+ " \n\tNotReadInfo : " + notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString()));
			// 안 읽은 메세지 수를 0으로 초기화
			initNotReadCount(roomId, userSeq, anotherSeq);

			// user:room 을 추가
			addUserMapInfo(roomId, userSeq, anotherSeq);

			// 방에 참가자 세션 정보가 추가(redis)
			initParticipantInfoInChatRoom(roomId, userSeq, anotherSeq);

			// 방에 재접속시 정보 갱신
			updateParticipantInfoInChatRoom(roomId, userSeq, session);

			// 이전에 남아있던 메세지 로그를 전달 => http로 전달
			//			ChatLogDto chat = ChatLogDto.builder().roomSeq(roomId).userSeq(userSeq).message(jsonObject.getString("message")).date(LocalDateTime.now()).build();
			//			sendChatInChatRoom(roomId, session);

			break;
		}
		case "message": { // 메세지 전달

			roomInfo = redisTemplate.opsForHash();
			System.out.println("[채팅 " + session.getId() + "] " + jsonObject + " / " + jsonObject.getString("message"));
			//			System.out.println("\t\t** participant info: " + roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), jsonObject.getString("roomId")));

			String roomId = jsonObject.getString("roomId");
			Long userSeq = Long.valueOf(jsonObject.getString("userSeq"));

			// 메세지 로그 Redis에 저장
			saveChatLog(roomId, userSeq, jsonObject.getString("message"));

			// 채팅방에 연결된 유저들의 port 번호로 메세지 전달 -> 접속 안한 사람도 최근 메세지를 받아야 하므로 계속 보냄
			// 그러면 ChatRoom에서 줄 필요 없음! 그냥 소켓으로 보내면 됨 -> 아님 소켓 연결 안된 곳은 못 받으므로 별도로 보내줘야 함
			ChatLogDto chat = ChatLogDto.builder().roomSeq(roomId).userSeq(userSeq).message(jsonObject.getString("message")).date(LocalDateTime.now()).build();
			sendMessageByRoomId(roomId, chat);

			// 같은 방인데 접속 안한 유저면 -> 안 읽은 메세지 카운트 ++
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Long> usermap = objectMapper.convertValue(userMapInfo.get(ChatTypeEnum.USER_MAP.toString(), userSeq.toString()), new TypeReference<Map<String, Long>>() {});
			//			System.out.println(" Usermap : " + usermap);
			Long anotherUserSeq = usermap.get(roomId);

			Map<String, ParticipantDto> anotherUser = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId), new TypeReference<Map<String, ParticipantDto>>() {});
			//			System.out.println("another User: " + anotherUser + " class : " + anotherUser.getClass());
			//			System.out.println(String.format("Keys : %s, antoherSeq: %s", anotherUser.keySet(), anotherUserSeq));
			ParticipantDto anotherUserInfo = anotherUser.get(anotherUserSeq.toString());
			if (anotherUserInfo.getEnterRoomCount() == 0) { // 어느 세션에서도 접속하지 않은 상태
				increaseNotReadCount(roomId, anotherUserSeq);
			}

			// 같은 방 다른 유저한테 채팅 알림 Publish
			Integer notReadCount = this.getNotReadCount(roomId, anotherUserSeq);
			Map<String, String> senderUserInfo = new HashMap<>();
			senderUserInfo.put("userSeq", userSeq.toString()); // 닉네임은 나중에..
			ChatAlertDto chatAlertDto = ChatAlertDto.builder().roomSeq(roomId)
				.targetUserSeq(anotherUserSeq).message(jsonObject.getString("message"))
				.notReadCount(notReadCount).build();
			String alertJsonString = objectMapper.writeValueAsString(chatAlertDto);
			template.convertAndSend("/sub/chat/" + anotherUserSeq, alertJsonString);
			break;

		}
		}
	}


	/**
	 * UserMapperInfo에 userSeq : {roomId : anotherSeq} 를 저장
	 *
	 * @param roomId
	 * @param userSeq
	 * @param anotherSeq
	 */
	private void addUserMapInfo(String roomId, Long userSeq, Long anotherSeq) {
		// 나 추가
		Map<String, Long> userMapper = new HashMap<>();
		userMapper.put(roomId, anotherSeq);
		userMapInfo.put(ChatTypeEnum.USER_MAP.toString(), userSeq.toString(), userMapper);

		// 상대 추가
		Map<String, Long> anotherMapper = new HashMap<>();
		anotherMapper.put(roomId, userSeq);
		userMapInfo.put(ChatTypeEnum.USER_MAP.toString(), anotherSeq.toString(), anotherMapper);

	}


	/**
	 * 안 읽은 메세지 수 카운트를 0으로 초기화
	 *
	 * @param roomId
	 * @param userSeq
	 */
	private void initNotReadCount(String roomId, Long userSeq, Long anotherUserSeq) {
		// 나 추가
		Map<String, Integer> notRead = new HashMap<>();
		notRead.put(roomId, 0);
		notReadInfo.put(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString(), notRead);

		// 상대방이 아직 한번도 채팅 기능을 사용하지 않았거나 현재 채팅 방에 접속을 하지 않았다면, 정보를 입력해줌
		if (notReadInfo.hasKey(ChatTypeEnum.CHAT_NO_ENTER.toString(), anotherUserSeq.toString()) == false
			|| notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), anotherUserSeq.toString()).containsKey(roomId)) {
			Map<String, Integer> notReadAnother = new HashMap<>();
			notReadAnother.put(roomId, 0);
			notReadInfo.put(ChatTypeEnum.CHAT_NO_ENTER.toString(), anotherUserSeq.toString(), notReadAnother);
		}
	}


	/**
	 * 안 읽은 메세지 수 카운트를 ++
	 *
	 * @param roomId
	 * @param userSeq
	 */
	private void increaseNotReadCount(String roomId, Long userSeq) {
		Map<String, Integer> notRead = notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString());
		Integer notReadCount = notRead.get(roomId) + 1;
		notRead.put(roomId, notReadCount);
		notReadInfo.put(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString(), notRead);
		//		System.out.println("\t\t *** Increase Not Read Count : " + notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString()));
	}


	/**
	 * 채팅방에 입장한 유저들에게 메세지 전달
	 *
	 * @param roomId
	 * @param chatLogDto
	 * @throws Exception
	 */
	private void sendMessageByRoomId(String roomId, ChatLogDto chatLogDto) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		Map<String, ParticipantDto> userInfos = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId), new TypeReference<Map<String, ParticipantDto>>() {});
		// 포트 번호 모음
		List<Integer> ports = new ArrayList<>();
		for (ParticipantDto participantDto : userInfos.values()) ports.add(participantDto.getPort());
		//		System.out.println("ports: " + ports);

		// 서버에 연결된 세션(클라이언트)들에게 채팅 메세지를 전달함
		for (WebSocketSession s : sessions) {
			// 채팅방에 속한 유저들에게만 채팅을 전달함.
			if (ports.contains(s.getRemoteAddress().getPort())) {
				String jsonString = objectMapper.writeValueAsString(chatLogDto);
				s.sendMessage(new TextMessage(jsonString));
			}
		}
	}


	/**
	 * 채팅방에서 오고간 채팅 로그를 Redis에 저장
	 *
	 * @param roomId
	 * @param userSeq
	 * @param message
	 */
	private void saveChatLog(String roomId, Long userSeq, String message) {
		List<ChatLogDto> chatLogList;
		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomId)) {
			// 채팅 로그가 이미 존재하는 방이면
			chatLogList = chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomId);
		} else {
			// 채팅 로그가 없는 방이면
			chatLogList = new ArrayList<>();
		}
		ChatLogDto chatLogDto = ChatLogDto.builder().userSeq(userSeq).message(message).date(LocalDateTime.now()).build();
		chatLogList.add(chatLogDto);
		chatLogInfo.put(ChatTypeEnum.CHAT_LOG.toString(), roomId, chatLogList);
	}

	/**
	 * 해당 채팅 방에서 오고간 채팅 로그를 특정 session에 전달 => 방 입장할 때 http로 전달함
	 * (채팅방 입장했을 때 이전 채팅 로그를 띄우기 위함)
	 *
	 * @param roomId
	 * @param session
	 * @throws IOException
	 */
	//	private void sendChatInChatRoom(String roomId, WebSocketSession session) throws IOException {
	//		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomId)) {
	//			//			System.out.println(" IN sendLogInChatRoom : " + chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomId).toString()
	//			//				+ "type : " + chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomId).getClass());
	//			ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
	//			List<ChatLogDto> chatLogList = objectMapper.convertValue(chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomId), new TypeReference<List<ChatLogDto>>() {});
	//			for (ChatLogDto chatLog : chatLogList) {
	//				String jsonString = objectMapper.writeValueAsString(chatLog.toString());
	//				session.sendMessage(new TextMessage(jsonString));
	//			}
	//		}
	//	}


	/**
	 * 채팅방에 입장한 유저의 정보(userSeq, port)를 Redis에 저장.
	 * 이미 추가된 유저면 추가 X
	 *
	 * @param roomId
	 * @param userSeq
	 * @param anotherSeq
	 */
	private void initParticipantInfoInChatRoom(String roomId, Long userSeq, Long anotherSeq) {
		if (roomInfo.hasKey(ChatTypeEnum.CHAT_ROOM.toString(), roomId)) {
			// 방이 만들어져있으면 추가 안된 사용자 추가함
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, ParticipantDto> userMapper = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId), new TypeReference<Map<String, ParticipantDto>>() {});
			userMapper.putIfAbsent(userSeq.toString(), ParticipantDto.builder().port(-1).enterRoomCount(0).build());
			userMapper.putIfAbsent(anotherSeq.toString(), ParticipantDto.builder().port(-1).enterRoomCount(0).build());
			roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomId, userMapper);
		} else {
			// 방이 안 만들어져있으면, 방 & 사용자 정보 추가
			Map<String, ParticipantDto> userMapper = new HashMap<>();
			userMapper.put(userSeq.toString(), ParticipantDto.builder().port(-1).enterRoomCount(0).build());
			userMapper.put(anotherSeq.toString(), ParticipantDto.builder().port(-1).enterRoomCount(0).build());
			roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomId, userMapper);
		}
		System.out.println("Init Participant : " + roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId));
	}


	/**
	 * 채팅방에 다시 접속했을때 채팅방에 입장한 유저의 정보(userSeq, port)를 업데이트
	 *
	 * @param roomId
	 * @param userSeq
	 * @param session
	 * @throws IOException
	 */

	private void updateParticipantInfoInChatRoom(String roomId, Long userSeq, WebSocketSession session) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		// key-vaalue로 유저 seq : port, seq, .. 정보를 묶음
		Map<String, ParticipantDto> userMapper = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId), new TypeReference<Map<String, ParticipantDto>>() {});
		ParticipantDto participantDto = userMapper.get(userSeq.toString());

		// 포트 번호와 방 입장 카운트++ 설정
		//		System.out.println(" participant Dto : " + participantDto);
		//		System.out.println(" Port: " + session.getRemoteAddress().getPort());
		participantDto.setPort(session.getRemoteAddress().getPort());
		participantDto.increaseEnterRoomCount();
		// 설정한 값으로 갱신
		userMapper.put(userSeq.toString(), participantDto);
		roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomId, userMapper);

		// 로그 찍는 용도
		//		session.sendMessage(new TextMessage("Enter " + roomId + "'s Room."));
	}


	/**
	 * 세션 종료 했을 때 enter room count 감소
	 *
	 * @param roomId
	 * @param userSeq
	 */
	public void decreaseEnterRoomCount(String roomId, String userSeq) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, ParticipantDto> dtoMap = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId), new TypeReference<Map<String, ParticipantDto>>() {});
		ParticipantDto participant = dtoMap.get(userSeq.toString());
		participant.decreaseEnterRoomCount();
		dtoMap.put(userSeq.toString(), participant);
		roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomId, dtoMap);
	}


	/**
	 * 클라이언트와 연결이 끊어졌을 때 수행되는 핸들러
	 *
	 * @param session
	 * @param status
	 * @throws Exception
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		/** TODO : status를 out으로 변경
		 * */
		sessions.remove(session);
		//		sendMessageByRoomId(roomId, "[알림] " + session.getId() + "님이 퇴장하셨습니다.");
	}


	/**
	 * 채팅방의 최근 메세지를 반환. ChatRoomService에서 사용.
	 *
	 * @param roomId
	 * @return
	 */
	public String getRecentMessage(String roomId) {
		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomId)) {
			List<ChatLogDto> chatLogDtoList = chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomId);
			return chatLogDtoList.isEmpty() ? "" : chatLogDtoList.get(chatLogDtoList.size() - 1).getMessage();
		}
		return "";
	}


	/**
	 * 채팅방에서 안 읽은 메세지 카운트를 반환. ChatRoomService에서 사용
	 *
	 * @param roomId
	 * @param userSeq
	 * @return
	 */
	public Integer getNotReadCount(String roomId, Long userSeq) {
		if (notReadInfo.hasKey(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq)) {
			Map<String, Integer> notRead = notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq);
			if (notRead.containsKey(roomId))
				return notRead.get(roomId);
		}
		return 0;
	}


	public List<ChatLogDto> getPreviousChatLog(String roomId) {
		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomId))
			return chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomId);
		return new ArrayList<>();
	}

}
