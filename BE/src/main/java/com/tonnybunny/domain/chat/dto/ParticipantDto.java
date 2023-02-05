package com.tonnybunny.domain.chat.dto;


import lombok.Data;


/**
 * port             : 세션 연결한 포트 번호 (포트 번호로 전달할 세션 구분함. 포트 번호 == 세션 아이디와 같은 역할)
 * isConnectedRoom  : 채팅방에 들어간 상태
 */
@Data
public class ParticipantDto {

	private Integer port;
	private Boolean isConnectedRoom;

}
