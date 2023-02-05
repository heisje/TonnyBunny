package com.tonnybunny.domain.user.dto;


import lombok.Data;


/**
 * PhoneNumber      : 휴대폰번호로 유저 정보 검색
 * email            : 패스워드 찾기(재설정)에만 사용
 * password         : 재설정을 위해 입력하는 새로운 패스워드
 * checkPassword    : 패스워드 확인
 */
@Data
public class AccountRequestDto {

	private String phoneNumber;
	private String email;
	private String isAuthed;

	/* 비밀번호 찾기 시 새 비밀번호 입력창으로 */
	private String password;

	private String checkPassword;

}
