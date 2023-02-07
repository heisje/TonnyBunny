package com.tonnybunny.domain.chat.service;


public enum ChatTypeEnum {
	USER_MAP("USER_MAP"), // userSeq : roomSeq : anotherUserSeq
	CHAT_LOG("CHAT_LOG"), // 채팅 메세지
	CHAT_ROOM("CHAT_ROOM"), // 채팅 방 설정
	CHAT_NO_ENTER("CHAT_NO_ENTER"); // 채팅 방 입장하지 않았을 때
	private final String chatType;


	ChatTypeEnum(String chatType) {
		this.chatType = chatType;
	}


	@Override
	public String toString() {
		return this.chatType;
	}
}
