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
 * userSeq      : 채팅 친 사람 Seq
 * message      : 채팅 메세지
 * date         : 채팅 친 날짜
 * dataTypeCode : 메세지/파일 공통코드
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatLogDto implements Serializable {

	private String roomSeq;
	private Long userSeq;
	private String message;
	private LocalDateTime date;
	private String dataTypeCode;


	public ChatLogEntity toEntity() {
		return ChatLogEntity.builder()
			.roomSeq(this.roomSeq)
			.userSeq(this.userSeq)
			.message(this.message)
			.date(this.date)
			.dataTypeCode(this.dataTypeCode)
			.build();
	}

}
