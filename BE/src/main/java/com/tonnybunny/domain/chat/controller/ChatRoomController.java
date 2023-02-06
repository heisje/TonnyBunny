package com.tonnybunny.domain.chat.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.chat.dto.ChatRoomResponseDto;
import com.tonnybunny.domain.chat.entity.ChatRoomEntity;
import com.tonnybunny.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ChatRoomController {

	private final ChatRoomService chatRoomService;


	/**
	 * 해당 유저가 속해있는 채팅방 목록을 반환
	 * - 채팅방 번호, 안 읽은 채팅 수, 최근 메세지 반환
	 *
	 * @param userSeq
	 * @return
	 */
	@PostMapping("/chat/room/{userSeq}")
	public ResponseEntity<ResultDto<List<ChatRoomResponseDto>>> findRoom(@PathVariable("userSeq") Long userSeq) {
		List<ChatRoomEntity> chatRoomList = chatRoomService.getChatRoomList(userSeq);
		List<ChatRoomResponseDto> chatRoomResponseDtoList = new ArrayList<>();
		for (ChatRoomEntity chatRoom : chatRoomList) {
			String roomSeq = chatRoom.getSeq();
			Integer notReadCount = chatRoomService.getNotReadCount(roomSeq, userSeq);
			String recentMesasge = chatRoomService.getRecentMessage(roomSeq);
			ChatRoomResponseDto chatRoomResponseDto = ChatRoomResponseDto.builder()
				.roomSeq(roomSeq)
				.notReadCount(notReadCount)
				.recentMessage(recentMesasge)
				.build();
			chatRoomResponseDtoList.add(chatRoomResponseDto);
		}
		return ResponseEntity.ok(ResultDto.of(chatRoomResponseDtoList));
	}


	@PostMapping("/chat/room/{userSeq1}/{userSeq2}")
	public ResponseEntity<ResultDto<String>> createRoom(@PathVariable("userSeq1") Long userSeq1, @PathVariable("userSeq2") Long userSeq2) {
		String chatRoomSeq = chatRoomService.createChatRoomSeq(userSeq1, userSeq2);
		return ResponseEntity.ok(ResultDto.of(chatRoomSeq));
	}

}
