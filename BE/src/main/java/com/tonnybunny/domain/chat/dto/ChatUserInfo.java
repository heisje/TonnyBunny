package com.tonnybunny.domain.chat.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class ChatUserInfo implements Serializable {

	private static final long serialVersionUID = 2983478989237498L;
	private Long userSeq;
	private String nickName;
	private String profileImagePath;

}
