package com.tonnybunny.domain.chat.dto;


import lombok.Data;
import lombok.experimental.SuperBuilder;


/**
 * 채팅방 목록 조회할 때 사용
 * roomSeq          : 채팅방 seq
 * userSeq          : API 요청한 유저 Seq
 * anotherUserInfo  : 채팅방에 속한 상대 유저의 정보 (userSeq, nickName, profileImagePath)
 * noReadCount      : 해당 채팅방에서 아직 안 읽은 채팅 수
 * recentMessage    : 가장 최근에 온 채팅 문자열
 */
@Data
@SuperBuilder
public class ChatRoomDetailDto extends ChatRoomDto {

	private static final long serialVersionUID = 2983478989237498L;
	private Integer notReadCount;
	private String recentMessage;

}
