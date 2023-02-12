package com.tonnybunny.domain.chat.dto;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


/**
 * roomSeq          : 채팅방 seq
 * userSeq          : API 요청한 유저 Seq
 * anotherUserInfo  : 채팅방에 속한 상대 유저의 정보 (userSeq, nickName, profileImagePath)
 */
@Data
@SuperBuilder
public class ChatRoomDto implements Serializable {

	private static final long serialVersionUID = 2983478989237498L;
	private String roomSeq;
	private Long userSeq;
	private ChatUserInfo anotherUserInfo;

}
