package com.tonnybunny.domain.user.service;


import com.tonnybunny.domain.user.dto.AccountRequestDto;
import com.tonnybunny.domain.user.dto.AccountResponseDto;
import com.tonnybunny.domain.user.dto.UserRequestDto;
import com.tonnybunny.domain.user.entity.UserEntity;


public class UserService {

	public UserEntity signup(UserRequestDto userRequestDto) {
		UserEntity user = userRequestDto.toEntity();
		/**
		 * repository 에서 회원가입 절차를 마치고 userEntity(또는 seq) 를 반환해준다.
		 * UserEntity savedUser = userRepository.signup(user);
		 * return savedUser;
		 */
		return user;
	}


	public Boolean checkNicknameDuplication(UserRequestDto userRequestDto) {
		UserEntity user = userRequestDto.toEntity();
		/**
		 * repository 에서 닉네임 중복확인 절차를 마치고 true/false 를 반환해준다.
		 * Boolean isDuplicate = userRepository.checkNicknameDuplication(user);
		 * return isDuplicate;
		 */
		return false;
	}


	public boolean sendAuthCode(String phoneNumber) {
		/**
		 * 휴대폰으로 인증코드 발송 해야함
		 */
		return true;
	}


	public boolean checkAuthCode(String authCode) {
		/**
		 * 인증코드 일치 여부 검증
		 */
		return true;
	}


	private boolean checkAgreementStatus(Boolean bool) {
		/**
		 * 프론트에서는 모든 약관에 동의했는지에 대한 데이터를 넘겨주어야 한다
		 * bool (약관동의) 가 true 일때 true를 반환한다
		 */
		return bool;
	}


	private boolean checkEmailValidation(String email) {
		/**
		 * 이메일 유효성 검사 로직
		 */
		return true;
	}


	private boolean checkPasswordValidation(String password) {
		/**
		 * 비밀번호 유효성 확인 로직
		 * 불일치 시 비밀번호 양식에 맞지 않다고 출력
		 */
		return true;
	}


	private boolean checkPasswordMatch(String password, String checkPassword) {
		/**
		 * 비밀번호 일치 확인 로직
		 * 불일치 시 비밀번호가 일치하지 않습니다 출력
		 */
		return true;
	}


	private String encryptPassword(String password) {
		/**
		 * 비밀번호 암호화 로직
		 */
		return "aisfnaiosnfoidnfioansefns";
	}


	public boolean login(UserRequestDto userRequestDto) {
		UserEntity user = userRequestDto.toEntity();
		/**
		 * user.email로 user를 조회 => findUserByEmail
		 * 조회가 안될 시 익셉션 터트리기
		 * user.password를 encryptPassword로 복호화 후 비교하여 결과 반환
		 * 일치하지 않을 시 익셉션 터트리기
		 */
		return true;
	}


	public Boolean logout(UserRequestDto userRequestDto) {
		UserEntity user = userRequestDto.toEntity();
		/**
		 * 로그아웃 진행 코드
		 * 토큰 회수? 무슨 방식?
		 */
		return true;
	}


	public AccountResponseDto findAccouontInfo(AccountRequestDto accountRequestDto) {
		/**
		 * checkAuthCode() 을 통과했을 시
		 * 프론트에서 userDto의 type을 보내주어야 한다 => Email or Password
		 * 만약 Email 이면
		 * Email찾기 로직을 실행 => findUserByPhoneNumber 하여 유저의 이메일을 반환
		 *
		 * 만약 Passwrod 이면 Password찾기 로직을 실행
		 * findUserByPhoneNumber 하여 userEntity를 조회하고
		 * 조회된 user의 email이 userDto의 email과 같으면 true / false 반환? 재설정하기 위해?
		 */
		return new AccountResponseDto();
	}

}
