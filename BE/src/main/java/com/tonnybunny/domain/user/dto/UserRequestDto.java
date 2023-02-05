package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * userCode             : 일반유저, 헬퍼유저 구분하는 공통코드
 * email                : 가입시 입력하는 이메일 (인증 우선순위 낮음)
 * password             : 암호화 과정 위치 결정이 필요함 (프론트/백)
 * [password2]          : 입력 비밀번호 일치 여부를 서버에서도 확인 할것인가?
 * nickname             : 가입을 희망하는 닉네임. 중복 불가능
 * phoneNumber          : 본인 인증을 통과한 전화번호
 * isAuthed             : 본인 인증 여부
 * profileImagePath     : 프로필 이미지 경로
 * newPassword          : 변경할 비밀번호
 * [newPassword2]       : 입력 비밀번호
 */
@Data
public class UserRequestDto {

	private Long seq;

	/* 일반 유저 회원가입 */
	private String userCode;

	@NotBlank(message = "이메일은 필수값입니다.")
	@Email(message = "유효하지 않은 이메일 형식입니다.")
	private String email;

	@NotBlank(message = "비밀번호는 필수값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
		message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자의 비밀번호여야 합니다.")
	private String password;

	@NotBlank(message = "비밀번호확인은 필수값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
		message = "비밀번호는 영문 대소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자의 비밀번호여야 합니다.")
	private String checkPassword;

	@NotBlank(message = "닉네임은 필수값입니다.")
	@Pattern(regexp = "(?=.*[가-힣a-zA-Z0-9]).{2,16}",
		message = "닉네임은 영문, 숫자, 한글만 사용이 가능합니다.")
	private String nickName;
	@NotBlank(message = "핸드폰 번호는 필수값입니다.")
	@Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력 가능합니다.")
	private String phoneNumber;

	@NotBlank(message = "휴대전화 인증이 필요합니다.")
	private String isAuthed; // 인증 여부를 확인하는 변수 (백에서도 인증 사실을 확인하기 위함)

	/* 일반 유저 회원정보 수정 */
	private String profileImagePath = "default.png";

	private String newPassword;


	/**
	 * 1. 빌더패턴으로 작성 2. UserEntity 내부의 비즈니스 로직(팩토리 메소드)으로 인스턴스 생성 후 반환
	 */
	public UserEntity toEntity() {
		return (UserEntity) new Object();
	}

}
