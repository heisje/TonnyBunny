package com.tonnybunny.domain.chat.service;


import com.tonnybunny.domain.chat.entity.ChatRoomEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChatRoomService {

	/**
	 * userSeq1과 userSeq2가 참여한 채팅방 Seq를 생성합니다.
	 * 이미 존재하는 채팅방이면 기존의 채팅방 Seq를 반환합니다.
	 *
	 * @param userSeq1 참여자 1 Seq
	 * @param userSeq2 참여자 2 Seq
	 * @return 채팅방 Seq
	 */
	public String createChatRoomSeq(Long userSeq1, Long userSeq2) {
		return "";
	}


	/**
	 * userSeq가 참여한 채팅방의 Seq 목록을 반환합니다.
	 *
	 * @param userSeq
	 * @return 채팅방 Seq 목록
	 */
	public List<ChatRoomEntity> getChatRoomList(Long userSeq) {
		return new ArrayList<>();
	}


	/**
	 * roomSeq 채팅방에서 userSeq가 아직 읽지 않은 채팅메세지 수를 반환합니다.
	 *
	 * @param roomSeq
	 * @param userSeq
	 * @return
	 */
	public Integer getNotReadCount(String roomSeq, Long userSeq) {
		return 0;
	}


	/**
	 * roomSeq 채팅방의 최신 채팅 메세지를 반환합니다.
	 *
	 * @param roomSeq
	 * @return
	 */
	public String getRecentMessage(String roomSeq) {
		return "";
	}

}
