package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Data;


/**
 * userCode             : 일반유저, 헬퍼유저 구분하는 공통코드
 * email                : 가입시 입력하는 이메일 (인증 우선순위 낮음)
 * password             : 암호화 과정 위치 결정이 필요함 (프론트/백)
 * [password2]          : 입력 비밀번호 일치 여부를 서버에서도 확인 할 것인가?
 * nickname             : 가입을 희망하는 닉네임. 중복 불가능
 * phoneNumber          : 본인 인증을 통과한 전화번호
 *
 * profileImagePath     : 프로필 이미지 경로
 * newPassword          : 변경할 비밀번호
 * [newPassword2]       : 입력 비밀번호 일치 여부를 서버에서도 확인 할 것인가?
 */
@Data
public class UserRequestDto {

	/* 일반 유저 회원가입 */
	private String userCode;

	private String email;
	private String password;
	private String nickname;
	private String phoneNumber;

	/* 일반 유저 회원정보 수정 */
	private String profileImagePath = "default.png";
	private String newPassword;


	/**
	 * 1. 빌더패턴으로 작성
	 * 2. UserEntity 내부의 비즈니스 로직(팩토리 메소드)으로 인스턴스 생성 후 반환
	 */
	public UserEntity toEntity() {
		return (UserEntity) new Object();
	}

}
