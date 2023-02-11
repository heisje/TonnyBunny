package com.tonnybunny.domain.chat.dto;


import com.tonnybunny.domain.chat.entity.ChatLogEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatLogDto implements Serializable {

	private static final long serialVersionUID = 2983478989237498L;
	private String roomSeq;
	private Long userSeq;
	private LocalDateTime date;
	private String message;
	private String messageType;


	public ChatLogEntity toEntity() {
		return ChatLogEntity.builder()
			.roomSeq(this.roomSeq)
			.userSeq(this.userSeq)
			.message(this.message)
			.date(this.date)
			.dataTypeCode(this.messageType)
			.build();
	}

}
