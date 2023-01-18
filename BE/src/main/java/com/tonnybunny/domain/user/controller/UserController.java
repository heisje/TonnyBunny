package com.tonnybunny.domain.user.controller;


import com.tonnybunny.domain.user.dto.AccountRequestDto;
import com.tonnybunny.domain.user.dto.AccountResponseDto;
import com.tonnybunny.domain.user.dto.UserRequestDto;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Api(tags = "회원가입 관련 API")
public class UserController {

	private final UserService userService;


	@PostMapping("/signup")
	@ApiOperation(value = "회원가입을 진행합니다")
	public ResponseEntity<?> signup(@RequestBody UserRequestDto userRequestDto) {
		UserEntity savedUser = userService.signup(userRequestDto);
		//		 String session = userService.login(savedUser);

		return ResponseEntity.status(HttpStatus.OK).body(savedUser);
	}


	@PostMapping("/signup/nickname")
	@ApiOperation(value = "닉네임 중복여부를 확인합니다")
	public ResponseEntity<?> checkNicknameDuplication(@RequestBody UserRequestDto userRequestDto) {
		/**
		 * service에서 반환받은 isDuplicate가 true 이면 중복된 닉네임이라고 알려주기, false 면 통과
		 *
		 */
		Boolean isDuplicate = userService.checkNicknameDuplication(userRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(isDuplicate);
	}


	@PostMapping("/send/authcode")
	@ApiOperation(value = "입력한 휴대폰 번호에 인증코드를 발송합니다")
	public ResponseEntity<?> sendAuthCode(@RequestBody String phoneNumber) {
		boolean isSuccess = userService.sendAuthCode(phoneNumber);
		/**
		 * 프론트에서 phoneNumber JSON으로 보내줘야 합니다
		 * service에서 인증코드 전송 성공 여부를 반환받습니다
		 * userService.sendAuthCode
		 */
		return ResponseEntity.status(HttpStatus.OK).body(isSuccess);
	}


	@PostMapping("/check/authcode")
	@ApiOperation(value = "인증코드를 확인합니다")
	public ResponseEntity<?> checkAuthCode(@RequestBody String authCode) {
		boolean isSuccess = userService.checkAuthCode(authCode);
		/**
		 * 프론트에서 authCode를 JSON으로 보내줘야 합니다
		 * service에서 인증코드 일치 여부를 반환받습니다
		 * userService.checkAuthCode
		 */
		return ResponseEntity.status(HttpStatus.OK).body(isSuccess);
	}


	@PostMapping("/login")
	@ApiOperation(value = "로그인을 진행합니다")
	public ResponseEntity<?> login(@RequestBody UserRequestDto userRequestDto) {

		Boolean isSuccess = userService.login(userRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(isSuccess);
	}


	@PostMapping("/logout")
	@ApiOperation(value = "로그아웃을 진행합니다")
	public ResponseEntity<?> logout(@RequestBody UserRequestDto userRequestDto) {
		Boolean isSuccess = userService.logout(userRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(isSuccess);
	}


	@PostMapping("/login/find")
	@ApiOperation(value = "아이디/비밀번호 찾기를 진행합니다")
	public ResponseEntity<?> findAccountInfo(@RequestBody AccountRequestDto accountRequestDto) {
		/**
		 * 프론트에서는 accountResponseDto에서 필요한 정보를 꺼내 쓴다 (Email or Boolean)
		 */
		AccountResponseDto accountResponseDto = userService.findAccouontInfo(accountRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(accountResponseDto);
	}

}
