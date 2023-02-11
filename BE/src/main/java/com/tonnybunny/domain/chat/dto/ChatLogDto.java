package com.tonnybunny.domain.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * roomSeq      : 채팅 방 Seq
 * userSeq      : 채팅 친 사람 seq
 * date         : 채팅 친 날짜
 * message      : 채팅 메세지
 * messageType  : "text" or "url" 메세지 타입
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatLogDto implements Serializable {

	private static final long serialVersionUID = 2983478989237498L;
	private String roomSeq;
	private Long userSeq;
	private String message;
	private String type;
	private String messageType;
	private LocalDateTime date;

	// noti로 부터 접속한 채팅방인 경우
	private String urlPage;
	private Long urlPageSeq;


	public static ChatLogDto fromJsonString(String payload) {
		// objectMapper 썼다가 deserialize 에러 폭탄 맞아서 jsonObject사용
		JSONObject jsonObject = new JSONObject(payload);
		String roomSeq = jsonObject.getString("roomSeq");
		Long userSeq = jsonObject.getLong("userSeq");
		String message = jsonObject.getString("message");
		String type = jsonObject.getString("type");
		String messageType = jsonObject.getString("messageType");
		String urlPage = jsonObject.has("urlPage") ? jsonObject.getString("urlPage") : "";
		Long urlPageSeq = jsonObject.has("urlPageSeq") ? jsonObject.getLong("urlPageSeq") : 0L;

		return ChatLogDto.builder()
			.roomSeq(roomSeq).userSeq(Long.valueOf(userSeq))
			.message(message).type(type).messageType(messageType)
			.date(LocalDateTime.now())
			.urlPage(urlPage).urlPageSeq(urlPageSeq).build();

	}

}
