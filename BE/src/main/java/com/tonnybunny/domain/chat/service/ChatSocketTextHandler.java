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
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
public class ChatSocketTextHandler extends TextWebSocketHandler {

	//	private final ChatService chatService;
	private final RedisTemplate<String, Object> redisTemplate;
	private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
	private final SimpMessagingTemplate template;

	/**
	 * port : [roomSeq, userSeq] -> roomInfo(roomSeq : userSeq : port)
	 */
	private final Map<Integer, String[]> connectedPortInfo = new HashMap<>();
	/**
	 * userSeq:roomSeq:anotherUserSeq 정보 저장 -> 같은 방 다른 유저를 쉽게 찾기 위함
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

	/**
	 * "ALERT-userSeq", roomSeq, {alertLogSeq: content}
	 * alertLogSeq는 js의 new Date().toISOString() 으로 설정
	 *
	 * content {
	 * task: "즉통(JTonny)/예통(YTonny)/번역(Bunny)/채팅(Chat)"
	 * detailTask: "헬퍼가수락(HelperAccept)/고객이수락(ClientAccept)/채팅(Chat)"
	 * pageSeq: "", => 알림을 눌렀을 때 해당 페이지로 이동할수 있는 페이지 ID (선택) // 채팅에선 roomSeq
	 * receivedUserSeq: "",
	 * message: "",
	 * notReadCount: 0, // 채팅 전용
	 * senderUserSeq: "", // 채팅 전용
	 * senderUserNickname: "", // 채팅 전용
	 * }
	 */
	private HashOperations<String, String, Map<String, String>> alertInfo;


	@PostConstruct
	public void init() {
		//	public ChatSocketTextHandler(RedisTemplate<String, Object> redisTemplate) {
		System.out.println(" !!! Constructor !!! ");
		//		this.chatService = new ChatService();
		this.userMapInfo = redisTemplate.opsForHash();
		this.roomInfo = redisTemplate.opsForHash();
		this.chatLogInfo = redisTemplate.opsForHash();
		this.notReadInfo = redisTemplate.opsForHash();
		this.alertInfo = redisTemplate.opsForHash();
	}


	/**
	 * 클라이언트와 서버 간 소켓 연결이 수립되었을 때 수행되는 핸들러
	 *
	 * @param session
	 * @throws Exception
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(String.format("Redis [접속] id:%s, uri: %s, port: %s, remote address: %s, local address: %s, ",
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
		System.out.println("jsonObject : " + jsonObject);
		switch (jsonObject.getString("type")) {
		case "exit": { // 채팅방에 들어가지 않은 상태
			String roomSeq = jsonObject.getString("roomSeq");
			Long userSeq = jsonObject.getLong("userSeq");
			// 입장 카운트 감소함
			decreaseEnterRoomCount(roomSeq, userSeq.toString());
			break;
		}
		case "enter": { // 채팅방에 들어간 상태
			String roomSeq = jsonObject.getString("roomSeq");
			Long userSeq = jsonObject.getLong("userSeq");
			Long anotherSeq = jsonObject.getLong("anotherSeq");

			// 해당 port 접속
			connectedPortInfo.put(session.getRemoteAddress().getPort(), new String[] { roomSeq, userSeq.toString() });

			//			System.out.println(" enter Setting " + roomSeq + " \n\tRoomStatus : " + roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq)
			//				+ " \n\tNotReadInfo : " + notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString()));
			// 안 읽은 메세지 수를 0으로 초기화
			initNotReadCount(roomSeq, userSeq, anotherSeq);

			// user:room 을 추가
			addUserMapInfo(roomSeq, userSeq, anotherSeq);

			// 방에 참가자 세션 정보가 추가(redis)
			initParticipantInfoInChatRoom(roomSeq, userSeq, anotherSeq);

			// 방에 재접속시 정보 갱신
			updateParticipantInfoInChatRoom(roomSeq, userSeq, session);

			// 이전에 남아있던 메세지 로그를 전달 => http로 전달
			//			ChatLogDto chat = ChatLogDto.builder().roomSeq(roomSeq).userSeq(userSeq).message(jsonObject.getString("message")).date(LocalDateTime.now()).build();
			//			sendChatInChatRoom(roomSeq, session);

			break;
		}
		case "message": { // 메세지 전달

			roomInfo = redisTemplate.opsForHash();
			System.out.println("Redis [채팅 " + session.getId() + "] " + jsonObject + " / " + jsonObject.getString("message"));
			//			System.out.println("\t\t** participant info: " + roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), jsonObject.getString("roomSeq")));

			String roomSeq = jsonObject.getString("roomSeq");
			Long userSeq = jsonObject.getLong("userSeq");

			// 메세지 로그 Redis에 저장
			saveChatLog(payload); //jsonObject.getString("message"));

			// 채팅방에 연결된 유저들의 port 번호로 메세지 전달 -> 접속 안한 사람도 최근 메세지를 받아야 하므로 계속 보냄
			// 그러면 ChatRoom에서 줄 필요 없음! 그냥 소켓으로 보내면 됨 -> 아님 소켓 연결 안된 곳은 못 받으므로 별도로 보내줘야 함
			ChatLogDto chat = ChatLogDto.fromJsonString(payload);
			//			ChatLogDto chat = ChatLogDto.builder().roomSeq(roomSeq).userSeq(userSeq).message(jsonObject.getString("message")).date(LocalDateTime.now()).build();
			sendMessageByRoomSeq(roomSeq, chat);

			// 같은 방인데 접속 안한 유저면 -> 안 읽은 메세지 카운트 ++
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Long> usermap = objectMapper.convertValue(userMapInfo.get(ChatTypeEnum.USER_MAP.toString(), userSeq.toString()), new TypeReference<Map<String, Long>>() {});
			//			System.out.println(" Usermap : " + usermap);
			Long anotherUserSeq = usermap.get(roomSeq);

			Map<String, ParticipantDto> anotherUser = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq), new TypeReference<Map<String, ParticipantDto>>() {});
			//			System.out.println("another User: " + anotherUser + " class : " + anotherUser.getClass());
			//			System.out.println(String.format("Keys : %s, antoherSeq: %s", anotherUser.keySet(), anotherUserSeq));
			ParticipantDto anotherUserInfo = anotherUser.get(anotherUserSeq.toString());
			//			Integer anotherUserPort = anotherUserInfo.getPort();
			//			if (connectedPortInfo.containsKey(anotherUserPort) && connectedPortInfo.get(anotherUserPort) == false) { // 어느 세션에서도 접속하지 않은 상태
			//			System.out.println("AnotherUserInfo : " + anotherUserInfo.toString());
			if (anotherUserInfo.getPort() == -1) {            // 안 읽은 메세지 수를 0으로 초기화
				initNotReadCount(roomSeq, userSeq, anotherUserSeq);
				increaseNotReadCount(roomSeq, anotherUserSeq);
			}

			// 같은 방 다른 유저한테 채팅 알림 Publish
			// FIXME : userInfo를 기본적으로 redis에 갖고 있어야 함. => socket 통신할 때마다 DB에서 user정보를 조회한는 건 말도 안됨.

			//			Integer notReadCount = this.getNotReadCount(roomSeq, anotherUserSeq);
			//			//			Map<String, String> senderUserInfo = new HashMap<>();
			//			//			senderUserInfo.put("userSeq", userSeq.toString()); // 닉네임은 나중에..
			//			ChatUserInfo senderUserInfo = ChatUserInfo.builder().userSeq(userSeq).nickName("").profileImagePath("").build();
			//			ChatAlertDto chatAlertDto = ChatAlertDto.builder().roomSeq(roomSeq).senderUserInfo(senderUserInfo).message(jsonObject.getString("message")).notReadCount(notReadCount).build();
			//			String alertJsonString = objectMapper.writeValueAsString(chatAlertDto);
			//			System.out.println(String.format("Redis [구독자에게 알림을 보냄] - [%s] %s", "/sub/chat/" + anotherUserSeq, alertJsonString));
			//			template.convertAndSend("/sub/chat/" + anotherUserSeq, alertJsonString);
			break;

		}
		case "alert": {
			System.out.println(String.format("Redis [알림] %s", jsonObject));

			String receivedUserSeq = String.valueOf(jsonObject.getLong("receivedUserSeq"));
			String alertLogSeq = jsonObject.getString("alertLogSeq");
			String roomSeq = jsonObject.getString("pageSeq"); // roomSeq
			String anotherUserSeq = String.valueOf(jsonObject.getLong("senderUserSeq"));
			Integer notReadCount = this.getNotReadCount(roomSeq, Long.valueOf(anotherUserSeq));

			// 같은 방인데 접속한 유저이면 -> 알림 안보냄
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, ParticipantDto> anotherUser = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq), new TypeReference<Map<String, ParticipantDto>>() {});
			ParticipantDto anotherUserInfo = anotherUser.get(receivedUserSeq);
			if (anotherUserInfo.getPort() != -1) {  // 알림 안 보냄
				//				System.out.println("상대방 seq: " + receivedUserSeq + ", 상대방 port: " + anotherUserInfo.getPort());
				break;
			}

			// 접속 안 한 유저 -> 알림 보냄
			// content 저장할 map
			Map<String, String> content;
			if (alertInfo.hasKey("ALERT-" + receivedUserSeq, roomSeq)) {
				content = alertInfo.get("ALERT-" + receivedUserSeq, roomSeq);
			} else {
				content = new HashMap<>();
			}
			ChatAlertDto contentValue = objectMapper.readValue(jsonObject.toString(), ChatAlertDto.class);
			System.out.println("Redis [content deserialize] before set : " + objectMapper.writeValueAsString(contentValue));
			contentValue.setNotReadCount(notReadCount);
			System.out.println("Redis [content deserialize] after set : " + objectMapper.writeValueAsString(contentValue));

			// content에 contentValue 저장
			content.put(alertLogSeq, objectMapper.writeValueAsString(contentValue));

			// redis에 저장
			alertInfo.put("ALERT-" + receivedUserSeq, roomSeq, content);

			// 구독자에게 알림 전송
			template.convertAndSend("/sub/chat/" + receivedUserSeq, objectMapper.writeValueAsString(contentValue));
			break;
		}
		//		case "deleteAlert": {
		//			// FIXME : socket으로 받지 말고, 그냥 rest 요청을 보내는게 나을 것.
		//			System.out.println(String.format("Redis [알림 삭제] %s", jsonObject));
		//			String userSeq = String.valueOf(jsonObject.getLong("userSeq"));
		//			String alertLogSeq = jsonObject.getString("alertLogSeq");
		//			deleteChatAlertLog(userSeq, alertLogSeq);
		//			break;
		//		}
		}
	}


	/**
	 * 특정 알림 삭제
	 *
	 * @param userSeq
	 * @param alertLogSeq
	 * @return
	 */
	public Boolean deleteChatAlertLog(String userSeq, String roomSeq, String alertLogSeq) {
		System.out.println("Redis [알림 삭제 요청]");
		// content 삭제
		if (alertInfo.hasKey("ALERT-" + userSeq, roomSeq)) {
			Map<String, String> content = alertInfo.get("ALERT-" + userSeq, roomSeq);
			if (content.containsKey(alertLogSeq)) {
				content.remove(alertLogSeq);
				alertInfo.put("ALERT-" + userSeq, roomSeq, content);
				System.out.println("Redis [알림 삭제 완료]");
			}
		}
		return true;
	}


	public Boolean deleteChatAlertLogByRoomSeq(String userSeq, String roomSeq) {
		System.out.println("Redis [알림 삭제 all 요청]");
		// content 삭제
		if (alertInfo.hasKey("ALERT-" + userSeq, roomSeq)) {
			alertInfo.delete("ALERT-" + userSeq, roomSeq);
			System.out.println("Redis [알림 전체 삭제 완료]");
		}
		return true;
	}


	/**
	 * 알림 목록 조회
	 *
	 * @param receivedUserSeq
	 * @return
	 */
	public List<String> getChatAlertLog(Long receivedUserSeq) {
		if (alertInfo.size("ALERT-" + receivedUserSeq) > 0) {
			Map<String, Map<String, String>> alerts = alertInfo.entries("ALERT-" + receivedUserSeq); // value가 이미 AlertDto를 String으로 변환시킨 것
			List<Map<String, String>> contentList = alerts.values().stream().collect(Collectors.toList());
			List<String> alertList = new ArrayList<>();
			contentList.forEach((s) -> {alertList.addAll(s.values().stream().collect(Collectors.toList()));});
			return alertList;
		}
		return new ArrayList<>();
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
		System.out.println(" 세션 연결이 끊어졌습니다. ");
		// 해당 port 연결을 false로.

		String[] info = connectedPortInfo.get(session.getRemoteAddress().getPort());
		if (info != null) {
			// 방에 있는 유저 정보에서 port번호를 -1로 수정함
			deleteParticipantInfoInChatRoom(info[0], Long.valueOf(info[1]));
		}
		sessions.remove(session);
		//		sendMessageByRoomSeq(roomSeq, "[알림] " + session.getId() + "님이 퇴장하셨습니다.");
	}


	/**
	 * UserMapperInfo에 userSeq : {roomSeq : anotherSeq} 를 저장
	 *
	 * @param roomSeq
	 * @param userSeq
	 * @param anotherSeq
	 */
	private void addUserMapInfo(String roomSeq, Long userSeq, Long anotherSeq) {
		// 나 추가
		Map<String, Long> userMapper = new HashMap<>();
		userMapper.put(roomSeq, anotherSeq);
		userMapInfo.put(ChatTypeEnum.USER_MAP.toString(), userSeq.toString(), userMapper);

		// 상대 추가
		Map<String, Long> anotherMapper = new HashMap<>();
		anotherMapper.put(roomSeq, userSeq);
		userMapInfo.put(ChatTypeEnum.USER_MAP.toString(), anotherSeq.toString(), anotherMapper);

	}


	/**
	 * 안 읽은 메세지 수 카운트를 0으로 초기화
	 *
	 * @param roomSeq
	 * @param userSeq
	 */
	private void initNotReadCount(String roomSeq, Long userSeq, Long anotherUserSeq) {
		// 나 추가
		Map<String, Integer> notRead = new HashMap<>();
		notRead.put(roomSeq, 0);
		notReadInfo.put(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString(), notRead);

		// 상대방이 아직 한번도 채팅 기능을 사용하지 않았거나 현재 채팅 방에 접속을 하지 않았다면, 정보를 입력해줌
		if (notReadInfo.hasKey(ChatTypeEnum.CHAT_NO_ENTER.toString(), anotherUserSeq.toString()) == false
			|| notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), anotherUserSeq.toString()).containsKey(roomSeq) == false) {
			Map<String, Integer> notReadAnother = new HashMap<>();
			notReadAnother.put(roomSeq, 0);
			notReadInfo.put(ChatTypeEnum.CHAT_NO_ENTER.toString(), anotherUserSeq.toString(), notReadAnother);
		}
	}


	/**
	 * 안 읽은 메세지 수 카운트를 ++
	 *
	 * @param roomSeq
	 * @param userSeq
	 */
	private void increaseNotReadCount(String roomSeq, Long userSeq) {
		Map<String, Integer> notRead = notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString());
		Integer notReadCount = notRead.get(roomSeq) + 1;
		notRead.put(roomSeq, notReadCount);
		notReadInfo.put(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString(), notRead);
		System.out.println("\t\t *** Increase Not Read Count : " + notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString()));
	}


	/**
	 * 채팅방에 입장한 유저들에게 메세지 전달
	 *
	 * @param roomSeq
	 * @param chatLogDto
	 * @throws Exception
	 */
	private void sendMessageByRoomSeq(String roomSeq, ChatLogDto chatLogDto) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		Map<String, ParticipantDto> userInfos = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq), new TypeReference<Map<String, ParticipantDto>>() {});
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
	 * @param payload
	 */
	private void saveChatLog(String payload) {
		ChatLogDto chatLogDto = ChatLogDto.fromJsonString(payload);
		String roomSeq = chatLogDto.getRoomSeq();

		List<ChatLogDto> chatLogList;
		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomSeq)) {
			// 채팅 로그가 이미 존재하는 방이면
			chatLogList = chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomSeq);
		} else {
			// 채팅 로그가 없는 방이면
			chatLogList = new ArrayList<>();
		}
		chatLogList.add(chatLogDto);
		chatLogInfo.put(ChatTypeEnum.CHAT_LOG.toString(), roomSeq, chatLogList);
	}

	/**
	 * 해당 채팅 방에서 오고간 채팅 로그를 특정 session에 전달 => 방 입장할 때 http로 전달함
	 * (채팅방 입장했을 때 이전 채팅 로그를 띄우기 위함)
	 *
	 * @param roomSeq
	 * @param session
	 * @throws IOException
	 */
	//	private void sendChatInChatRoom(String roomSeq, WebSocketSession session) throws IOException {
	//		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomSeq)) {
	//			//			System.out.println(" IN sendLogInChatRoom : " + chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomSeq).toString()
	//			//				+ "type : " + chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomSeq).getClass());
	//			ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
	//			List<ChatLogDto> chatLogList = objectMapper.convertValue(chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomSeq), new TypeReference<List<ChatLogDto>>() {});
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
	 * @param roomSeq
	 * @param userSeq
	 * @param anotherSeq
	 */
	private void initParticipantInfoInChatRoom(String roomSeq, Long userSeq, Long anotherSeq) {
		if (roomInfo.hasKey(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq)) {
			// 방이 만들어져있으면 추가 안된 사용자 추가함
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, ParticipantDto> userMapper = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq), new TypeReference<Map<String, ParticipantDto>>() {});
			userMapper.putIfAbsent(userSeq.toString(), ParticipantDto.builder().port(-1).enterRoomCount(0).build());
			userMapper.putIfAbsent(anotherSeq.toString(), ParticipantDto.builder().port(-1).enterRoomCount(0).build());
			roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq, userMapper);
		} else {
			// 방이 안 만들어져있으면, 방 & 사용자 정보 추가
			Map<String, ParticipantDto> userMapper = new HashMap<>();
			userMapper.put(userSeq.toString(), ParticipantDto.builder().port(-1).enterRoomCount(0).build());
			userMapper.put(anotherSeq.toString(), ParticipantDto.builder().port(-1).enterRoomCount(0).build());
			roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq, userMapper);
		}
		System.out.println("Init Participant : " + roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq));
	}


	/**
	 * 채팅방에 다시 접속했을때 채팅방에 입장한 유저의 정보(userSeq, port)를 업데이트
	 *
	 * @param roomSeq
	 * @param userSeq
	 * @param session
	 * @throws IOException
	 */

	private void updateParticipantInfoInChatRoom(String roomSeq, Long userSeq, WebSocketSession session) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		// key-vaalue로 유저 seq : port, seq, .. 정보를 묶음
		Map<String, ParticipantDto> userMapper = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq), new TypeReference<Map<String, ParticipantDto>>() {});
		ParticipantDto participantDto = userMapper.get(userSeq.toString());

		// 포트 번호와 방 입장 카운트++ 설정
		//		System.out.println(" participant Dto : " + participantDto);
		//		System.out.println(" Port: " + session.getRemoteAddress().getPort());
		participantDto.setPort(session.getRemoteAddress().getPort());
		participantDto.increaseEnterRoomCount();
		// 설정한 값으로 갱신
		userMapper.put(userSeq.toString(), participantDto);
		roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq, userMapper);

		// 로그 찍는 용도
		//		session.sendMessage(new TextMessage("Enter " + roomSeq + "'s Room."));
	}


	private void deleteParticipantInfoInChatRoom(String roomSeq, Long userSeq) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		// key-vaalue로 유저 seq : port, seq, .. 정보를 묶음
		Map<String, ParticipantDto> userMapper = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq), new TypeReference<Map<String, ParticipantDto>>() {});
		ParticipantDto participantDto = userMapper.get(userSeq.toString());

		// 포트 번호와 방 입장 카운트++ 설정
		//		System.out.println(" participant Dto : " + participantDto);
		//		System.out.println(" Port: " + session.getRemoteAddress().getPort());
		participantDto.setPort(-1);
		participantDto.decreaseEnterRoomCount();
		// 설정한 값으로 갱신
		userMapper.put(userSeq.toString(), participantDto);
		roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq, userMapper);

		// 로그 찍는 용도
		//		session.sendMessage(new TextMessage("Enter " + roomSeq + "'s Room."));
	}


	/**
	 * 세션 종료 했을 때 enter room count 감소
	 *
	 * @param roomSeq
	 * @param userSeq
	 */
	public void decreaseEnterRoomCount(String roomSeq, String userSeq) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, ParticipantDto> dtoMap = objectMapper.convertValue(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq), new TypeReference<Map<String, ParticipantDto>>() {});
		ParticipantDto participant = dtoMap.get(userSeq.toString());
		participant.setPort(-1);
		participant.decreaseEnterRoomCount();
		dtoMap.put(userSeq.toString(), participant);
		roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomSeq, dtoMap);
	}


	/**
	 * 채팅방의 최근 메세지를 반환. ChatRoomService에서 사용.
	 *
	 * @param roomSeq
	 * @return
	 */
	public String getRecentMessage(String roomSeq) {
		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomSeq)) {
			List<ChatLogDto> chatLogDtoList = chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomSeq);
			return chatLogDtoList.isEmpty() ? "" : chatLogDtoList.get(chatLogDtoList.size() - 1).getMessage();
		}
		return "";
	}


	/**
	 * 채팅방에서 안 읽은 메세지 카운트를 반환. ChatRoomService에서 사용
	 *
	 * @param roomSeq
	 * @param userSeq
	 * @return
	 */
	public Integer getNotReadCount(String roomSeq, Long userSeq) {
		if (notReadInfo.hasKey(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString())) {
			Map<String, Integer> notRead = notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString());
			if (notRead.containsKey(roomSeq))
				return notRead.get(roomSeq);
			else {
				System.out.println(" [getNotReadCount()] notReadInfo에 roomSeq가 등록되지 않았었음");
			}
		}
		System.out.println(" [getNotReadCount()] notReadInfo에 정보가 등록되지 않았었음");
		Map<String, Integer> notRead = new HashMap<>();
		notRead.put(roomSeq, 0);
		notReadInfo.put(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq.toString(), notRead);
		return 0;
	}


	public List<ChatLogDto> getPreviousChatLog(String roomSeq) {
		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomSeq))
			return chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomSeq);
		return new ArrayList<>();
	}

}
