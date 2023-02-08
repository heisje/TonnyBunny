package com.tonnybunny.domain.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * port             : 세션 연결한 포트 번호 (포트 번호로 전달할 세션 구분함. 포트 번호 == 세션 아이디와 같은 역할)
 * isConnectedRoom  : 채팅방에 들어간 상태
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantDto implements Serializable {

	private Integer port; // TODO : Set으로 저장 -> 한 유저가 여러 브라우저 창에서 같은 채팅방에 접속할 수 있음
	private Integer enterRoomCount = 0;


	public void increaseEnterRoomCount() {
		this.enterRoomCount++;
	}


	public void decreaseEnterRoomCount() {
		this.enterRoomCount--;
	}

}
