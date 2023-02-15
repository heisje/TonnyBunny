package com.tonnybunny.domain.chat.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.chat.dto.ChatRoomDetailDto;
import com.tonnybunny.domain.chat.dto.ChatRoomDto;
import com.tonnybunny.domain.chat.dto.ChatUserInfo;
import com.tonnybunny.domain.chat.entity.ChatRoomEntity;
import com.tonnybunny.domain.chat.service.ChatRoomService;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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
	public ResponseEntity<ResultDto<List<ChatRoomDetailDto>>> findChatRoomList(@PathVariable("userSeq") Long userSeq) {
		List<ChatRoomEntity> chatRoomList = chatRoomService.getChatRoomList(userSeq);
		List<ChatRoomDetailDto> chatRoomDetailDtoList = new ArrayList<>();
		for (ChatRoomEntity chatRoom : chatRoomList) {
			String roomSeq = chatRoom.getSeq();
			Integer notReadCount = chatRoomService.getNotReadCount(roomSeq, userSeq);
			String recentMesasge = chatRoomService.getRecentMessage(roomSeq);
			// 다른 참가자의 정보
			Long anotherUserSeq = chatRoomService.getAnotherUserSeq(chatRoom, userSeq);
			UserEntity anotherUser = userService.getUserInfo(anotherUserSeq);
			ChatUserInfo anotherUserInfo = ChatUserInfo.builder()
				.userSeq(anotherUserSeq)
				.nickName(anotherUser.getNickName())
				.profileImagePath(anotherUser.getProfileImagePath())
				.build();

			ChatRoomDetailDto chatRoomDetailDto = ChatRoomDetailDto.builder()
				.roomSeq(roomSeq)
				.userSeq(userSeq)
				.anotherUserInfo(anotherUserInfo)
				.notReadCount(notReadCount)
				.recentMessage(recentMesasge)
				.build();

			chatRoomDetailDtoList.add(chatRoomDetailDto);
		}
		return ResponseEntity.ok(ResultDto.of(chatRoomDetailDtoList));
	}


	/**
	 * userSeq, anotherUserSeq가 참여한 방을 생성한 후 정보를 반환,
	 * 이미 있는 방이면 기존 방의 정보를 반환
	 *
	 * @param userSeq
	 * @param anotherUserSeq
	 * @return 방 Seq, API 요청한 유저의 Seq, 상대 유저의 정보 (userSeq, nickName, profileImagePath)
	 */
	@PostMapping("/chat/room/{userSeq}/{anotherUserSeq}")
	public ResponseEntity<ResultDto<ChatRoomDto>> findRoom(@PathVariable("userSeq") Long userSeq, @PathVariable("anotherUserSeq") Long anotherUserSeq) {
		ChatRoomEntity chatRoom = chatRoomService.getChatRoomSeq(userSeq, anotherUserSeq);

		// 다른 참가자의 정보
		UserEntity anotherUser = userService.getUserInfo(anotherUserSeq);
		ChatUserInfo anotherUserInfo = ChatUserInfo.builder()
			.userSeq(anotherUserSeq)
			.nickName(anotherUser.getNickName())
			.profileImagePath(anotherUser.getProfileImagePath())
			.build();
		ChatRoomDto chatRoomDto = ChatRoomDetailDto.builder()
			.roomSeq(chatRoom.getSeq())
			.userSeq(userSeq)
			.anotherUserInfo(anotherUserInfo)
			.build();
		return ResponseEntity.ok(ResultDto.of(chatRoomDto));
	}


	@GetMapping("/chat/log/{roomSeq}")
	public ResponseEntity<ResultDto<List<String>>> getPreviousChatLog(@PathVariable("roomSeq") String roomSeq) {
		List<String> chatLogDtoList = chatRoomService.getPreviousChatLog(roomSeq);
		System.out.println("Chat Log Dto: " + chatLogDtoList);
		return ResponseEntity.ok(ResultDto.of(chatLogDtoList));
	}


	@GetMapping("/chat/alert/{userSeq}")
	public ResponseEntity<ResultDto<List<String>>> getChatAlertLog(@PathVariable("userSeq") Long userSeq) {
		List<String> chatLogDtoList = chatRoomService.getChatAlertLog(userSeq);
		System.out.println("Chat Log Alert Dto: " + chatLogDtoList);
		return ResponseEntity.ok(ResultDto.of(chatLogDtoList));
	}

}
