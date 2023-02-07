package com.tonnybunny.domain.chat.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.chat.dto.ChatLogDto;
import com.tonnybunny.domain.chat.dto.ChatRoomResponseDto;
import com.tonnybunny.domain.chat.entity.ChatRoomEntity;
import com.tonnybunny.domain.chat.service.ChatRoomService;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ChatRoomController {

	private final ChatRoomService chatRoomService;
	private final UserService userService;


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
			// 다른 참가자의 정보
			Long anotherUserSeq = chatRoomService.getAnotherUserSeq(chatRoom, userSeq);
			UserEntity anotherUser = userService.getUserInfo(anotherUserSeq);
			Map<String, String> anotherUserInfo = new HashMap<>();
			anotherUserInfo.put("userSeq", anotherUserSeq.toString());
			anotherUserInfo.put("imagePath", anotherUser.getProfileImagePath());
			anotherUserInfo.put("nickName", anotherUser.getNickName());
			chatRoomResponseDto.setAnotherUserInfo(anotherUserInfo);

			chatRoomResponseDtoList.add(chatRoomResponseDto);
		}
		return ResponseEntity.ok(ResultDto.of(chatRoomResponseDtoList));
	}


	/**
	 * 임시 API : userSeq1, userSeq2가 참여한 방을 생성, 이미 있는 방이면 방의 Seq를 반환
	 * => API가 아니라 다른 API 내부에서 service 로직으로 쓰일 예정
	 *
	 * @param userSeq1
	 * @param userSeq2
	 * @return
	 */
	@PostMapping("/chat/room/{userSeq1}/{userSeq2}")
	public ResponseEntity<ResultDto<String>> createRoom(@PathVariable("userSeq1") Long userSeq1, @PathVariable("userSeq2") Long userSeq2) {
		String chatRoomSeq = chatRoomService.createChatRoomSeq(userSeq1, userSeq2);
		return ResponseEntity.ok(ResultDto.of(chatRoomSeq));
	}


	@GetMapping("/chat/log/{roomSeq}")
	public ResponseEntity<ResultDto<List<ChatLogDto>>> getPreviousChatLog(@PathVariable("roomSeq") String roomSeq) {
		List<ChatLogDto> chatLogDtoList = chatRoomService.getPreviousChatLog(roomSeq);
		System.out.println("Chat Log Dto: " + chatLogDtoList);
		return ResponseEntity.ok(ResultDto.of(chatLogDtoList));
	}

}
