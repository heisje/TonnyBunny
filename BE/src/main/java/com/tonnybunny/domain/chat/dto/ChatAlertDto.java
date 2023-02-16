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

	//	private String roomSeq;
	//
	//	private String message;
	//
	//	private ChatUserInfo senderUserInfo;
	//	private Integer notReadCount;
	private String alertLogSeq;
	private String type; // "alert"
	private String task; // "Chat"
	private String detailTask; // "Chat"
	private String pageSeq; //=> 알림을 눌렀을 때 해당 페이지로 이동할수 있는 페이지 ID (선택) // 채팅에선 roomSeq
	private Long receivedUserSeq;
	private String message;
	private Long senderUserSeq;
	private String senderUserNickname;

	private Integer notReadCount;

}
