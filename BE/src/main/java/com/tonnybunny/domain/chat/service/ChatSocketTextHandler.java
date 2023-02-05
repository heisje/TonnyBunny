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

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@RequiredArgsConstructor
public class ChatSocketTextHandler extends TextWebSocketHandler {

	private final ChatService chatService;
	private final RedisTemplate<String, Object> redisTemplate;
	private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

	/** 방에 포함된 사람의 세션 정보 저장 */
	private HashOperations<String, String, Map<Long, ParticipantDto>> roomInfo; // "CHAT_ROOM", roomSeq, {userSeq1: {...}, userSeq2: {...}}

	/** 각 방에 오고간 채팅 로그 저장 */
	private HashOperations<String, String, List<ChatLogDto>> chatLogInfo; // "CHAT_LOG", roomSeq, [{userSeq, msg, ...}, ...]

	/** 유저가 각 방 마다 안 읽은 채팅 메세지 카운트 저장 */
	private HashOperations<String, Long, Map<String, Integer>> notReadInfo; // "CHAT_NO_ENTER", userSeq, { roomSeq1: 0, roomSeq2: 2}


	@PostConstruct
	public void init() {
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
		case "no_enter": {

		}
		case "enter": {

		}
		case "message": {

		}
		}
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
	}

}
