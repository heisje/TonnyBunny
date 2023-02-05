package com.tonnybunny.domain.chat.service;


import com.tonnybunny.domain.chat.dto.ChatLogDto;
import com.tonnybunny.domain.chat.dto.ParticipantDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RequiredArgsConstructor
public class ChatSocketTextHandler extends TextWebSocketHandler {

	private final ChatService chatService;
	private final RedisTemplate<String, Object> redisTemplate;
	private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

	/** userSeq:roomId:anotherUserSeq 정보 저장 -> 같은 방 다른 유저를 쉽게 찾기 위함 */
	private HashOperations<String, Long, Map<String, Long>> userMapInfo; // "USER_MAP", userSeq : {roomSeq : anotherUserSeq}

	/** 방에 포함된 사람의 세션 정보 저장 */
	private HashOperations<String, String, Map<Long, ParticipantDto>> roomInfo; // "CHAT_ROOM", roomSeq, {userSeq1: {...}, userSeq2: {...}}

	/** 각 방에 오고간 채팅 로그 저장 */
	private HashOperations<String, String, List<ChatLogDto>> chatLogInfo; // "CHAT_LOG", roomSeq, [{userSeq, msg, ...}, ...]

	/**
	 * 유저가 각 방 마다 안 읽은 채팅 메세지 카운트 저장
	 * 초기 status : false여야함.
	 * 채팅방 들어올 때 true, 채팅방 나갈 때 false
	 * -> 근데 status 하나만 연결하면, 브라우저 여러개 열어서 동시에 여러 채팅방 들어갔을 때, 하나라도 나가면 false가 돼서 다른 채팅방 나가버린 상태가 됨.
	 * -> 이건 방 마다 status 가 바뀌기 때문에 상관없음!
	 *
	 * -> 브라우저 여러개 열어서 동시에 같은 채팅방을 열었을 때 문제가 됨.
	 * => status를 boolean으로 하지 말고, Integer 카운트로 셈하자!
	 */
	private HashOperations<String, Long, Map<String, Integer>> notReadInfo; // "CHAT_NO_ENTER", userSeq, { roomSeq1: 0, roomSeq2: 2}


	public ChatSocketTextHandler() {
		chatService = new ChatService();
		redisTemplate = new RedisTemplate<>();
		roomInfo = redisTemplate.opsForHash();
		chatLogInfo = redisTemplate.opsForHash();
		notReadInfo = redisTemplate.opsForHash();
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
		JSONObject jsonObject = new JSONObject();
		switch (jsonObject.getString("type")) {
		case "no_enter": { // 채팅방에 들어가지 않은 상태

		}
		case "enter": { // 채팅방에 들어간 상태
			String roomId = jsonObject.getString("roomId");
			Long userSeq = Long.valueOf(jsonObject.getString("userSeq"));

			System.out.println(" enter Setting " + roomId + " " + userSeq);
			// 안 읽은 메세지 수를 0으로 초기화
			initNotReadCount(roomId, userSeq);

			// 방에 참가자 세션 정보가 추가(redis)
			updateParticipantInfoInChatRoom(roomId, userSeq, session);

			// 이전에 남아있던 메세지 로그를 전달
			sendLogInChatRoom(roomId, session);

			break;
		}
		case "message": { // 메세지 전달
			System.out.println("[채팅 " + session.getId() + "] " + jsonObject + " / " + jsonObject.getString("message"));
			System.out.print("\t - " + jsonObject.getString("roomId") + " 방에 연결된 포트 번호 : ");
			System.out.println(roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), jsonObject.getString("roomId")));

			String roomId = jsonObject.getString("roomId");
			Long userSeq = Long.valueOf(jsonObject.getString("userSeq"));

			// 메세지 로그 저장
			saveChatLog(roomId, userSeq, jsonObject.getString("message"));

			// 채팅방에 연결된 유저들의 port 번호로 메세지 전달 -> 접속 안한 사람도 최근 메세지를 받아야 하므로 계속 보냄
			// 그러면 ChatRoom에서 줄 필요 없음! 그냥 소켓으로 보내면 됨 -> 아님 소켓 연결 안된 곳은 못 받으므로 별도로 보내줘야 함
			sendMessageByRoomId(roomId, session.getId() + " : " + jsonObject.getString("message"));

			// 같은 방인데 접속 안한 유저면 -> 안 읽은 메세지 카운트 ++
			Long anotherUserSeq = userMapInfo.get(ChatTypeEnum.USER_MAP.toString(), userSeq).get(roomId);
			ParticipantDto anotherUserStatus = roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId).get(anotherUserSeq);
			if (anotherUserStatus.getEnterRoomCount() == 0) { // 어느 세션에서도 접속하지 않은 상태
				increaseNotReadCount(roomId, userSeq);
			}
			break;

		}
		}
	}


	/**
	 * 안 읽은 메세지 수 카운트를 0으로 초기화
	 *
	 * @param roomId
	 * @param userSeq
	 */
	private void initNotReadCount(String roomId, Long userSeq) {
		Map<String, Integer> notRead = notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq);
		notRead.put(roomId, 0);
		notReadInfo.put(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq, notRead);
	}


	/**
	 * 안 읽은 메세지 수 카운트를 ++
	 *
	 * @param roomId
	 * @param userSeq
	 */
	private void increaseNotReadCount(String roomId, Long userSeq) {
		Map<String, Integer> notRead = notReadInfo.get(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq);
		Integer notReadCount = notRead.get(roomId);
		notRead.put(roomId, notReadCount + 1);
		notReadInfo.put(ChatTypeEnum.CHAT_NO_ENTER.toString(), userSeq, notRead);
	}


	public String getRecentMessage(String roomId, Long userSeq) {
		// TODO : 최근 메세지 반환 -> ChatRoomService에서 쓸 예정
		return "";
	}


	/**
	 * 채팅방에 입장한 유저들에게 메세지 전달
	 *
	 * @param roomId
	 * @param message
	 * @throws Exception
	 */
	private void sendMessageByRoomId(String roomId, String message) throws IOException {
		roomInfo = redisTemplate.opsForHash();
		Map<Long, ParticipantDto> userInfos = roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId);
		// 서버에 연결된 세션(클라이언트)들에게 채팅 메세지를 전달함
		for (WebSocketSession s : sessions) {
			// 채팅방에 속한 유저들에게만 채팅을 전달함.
			if (userInfos.containsValue(s.getRemoteAddress().getPort())) {
				s.sendMessage(new TextMessage(message));
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
		chatLogInfo = redisTemplate.opsForHash();
		List<ChatLogDto> chatLogList;
		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomId)) {
			// 채팅 로그가 이미 존재하는 방이면
			chatLogList = chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomId);
		} else {
			// 채팅 로그가 없는 방이면
			chatLogList = new ArrayList<>();
		}
		ChatLogDto chatLogDto = new ChatLogDto();
		chatLogDto.setUserSeq(userSeq);
		chatLogDto.setMessage(message);
		chatLogDto.setDate(LocalDateTime.now());

		chatLogList.add(chatLogDto);
		chatLogInfo.put(ChatTypeEnum.CHAT_LOG.toString(), roomId, chatLogList);
	}


	/**
	 * 해당 채팅 방에서 오고간 채팅 로그를 특정 session에 전달
	 * (채팅방 입장했을 때 이전 채팅 로그를 띄우기 위함)
	 *
	 * @param roomId
	 * @param session
	 * @throws IOException
	 */
	private void sendLogInChatRoom(String roomId, WebSocketSession session) throws IOException {
		chatLogInfo = redisTemplate.opsForHash();
		if (chatLogInfo.hasKey(ChatTypeEnum.CHAT_LOG.toString(), roomId)) {
			List<ChatLogDto> chatLogList = chatLogInfo.get(ChatTypeEnum.CHAT_LOG.toString(), roomId);
			for (ChatLogDto chatLog : chatLogList) {
				// TODO : JSON Object 같은 객체로 전달하기
				session.sendMessage(new TextMessage(String.format(" [이전 채팅] %s : %s", chatLog.getUserSeq(), chatLog.getMessage())));
			}
		}
	}


	/**
	 * 채팅방에 입장한 유저의 정보(userSeq, port)를 Redis에 저장
	 *
	 * @param roomId
	 * @param userSeq
	 * @param session
	 * @throws IOException
	 */
	private void updateParticipantInfoInChatRoom(String roomId, Long userSeq, WebSocketSession session) throws IOException {
		roomInfo = redisTemplate.opsForHash();
		// key-vaalue로 유저 seq : port, seq, .. 정보를 묶음
		Map<Long, ParticipantDto> userMapper;
		ParticipantDto participantDto;
		if (roomInfo.hasKey(ChatTypeEnum.CHAT_ROOM.toString(), roomId)) {
			// 입장한 유저가 있다면
			userMapper = roomInfo.get(ChatTypeEnum.CHAT_ROOM.toString(), roomId); // FIXME: 꺼내고 데이터 새로 넣은다음에 Map을 새로 redis에 넣는게 옳은걸까? 아닌 것 같음.. 일단 동작 확인만 하자
			participantDto = userMapper.get(userSeq);
		} else {
			// 방이 처음 만들어졌다면 (유저 정보가 없다면)
			userMapper = new HashMap<>();
			participantDto = new ParticipantDto();
		}
		// 포트 번호와 방 입장 카운트++ 설정
		participantDto = userMapper.get(userSeq);
		participantDto.setPort(session.getRemoteAddress().getPort());
		participantDto.increaseEnterRoomCount();
		// 설정한 값으로 갱신
		userMapper.put(userSeq, participantDto);
		// redis에 넣기
		roomInfo.put(ChatTypeEnum.CHAT_ROOM.toString(), roomId, userMapper);

		// 로그 찍는 용도
		session.sendMessage(new TextMessage("Enter " + roomId + "'s Room."));

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

}
