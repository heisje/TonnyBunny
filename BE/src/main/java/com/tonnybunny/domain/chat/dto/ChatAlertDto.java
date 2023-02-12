package com.tonnybunny.domain.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * roomSeq          : 채팅방 번호
 * message          : 채팅 메세지
 * senderUserInfo   : 채팅 보낸 사람의 정보 (userSeq, nickName, profileImagePath)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatAlertDto implements Serializable {

	private static final long serialVersionUID = 120938012890987L;

	private String roomSeq;

	private String message;

	private ChatUserInfo senderUserInfo;

}
