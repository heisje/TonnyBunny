package com.tonnybunny.domain.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatAlertDto implements Serializable {

	private static final long serialVersionUID = 120938012890987L;

	private String roomSeq;
	private Long targetUserSeq;

	private String message;

	private Integer notReadCount;

	private Map<String, String> senderUserInfo = new HashMap<>(); // userSeq, nickName

}
