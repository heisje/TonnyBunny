package com.tonnybunny.domain.chat.dto;


import com.tonnybunny.domain.chat.entity.ChatRoomEntity;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * roomSeq          : 채팅방 seq
 * noReadCount      : 해당 채팅방에서 아직 안 읽은 채팅 수
 * recentMessage    : 가장 최근에 온 채팅 문자열
 */
@Data
@Builder
public class ChatRoomResponseDto {

	private String roomSeq;

	private Integer notReadCount;
	private String recentMessage;

	private Map<String, String> anotherUserInfo = new HashMap<>();


	public static ChatRoomResponseDto fromEntity(ChatRoomEntity chatRoom, Integer notReadCount, String recentMessage) {
		return ChatRoomResponseDto.builder()
			.roomSeq(chatRoom.getSeq())
			.notReadCount(notReadCount)
			.recentMessage(recentMessage)
			.build();
	}

}
