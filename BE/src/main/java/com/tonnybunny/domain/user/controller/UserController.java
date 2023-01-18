package com.tonnybunny.domain.user.controller;


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
	public ResponseEntity<?> signup(UserRequestDto userRequestDto) {
		UserEntity savedUser = userService.signup(userRequestDto);
		//		 String session = userService.login(savedUser);

		return ResponseEntity.status(HttpStatus.OK).body(savedUser);
	}


	@PostMapping("/signup/nickname")
	@ApiOperation(value = "닉네임 중복여부를 확인합니다")
	public ResponseEntity<?> checkNicknameDuplication(UserRequestDto userRequestDto) {
		/**
		 * service에서 반환받은 isDuplicate가 true 이면 중복된 닉네임이라고 알려주기, false 면 통과
		 *
		 */
		Boolean isDuplicate = userService.checkNicknameDuplication(userRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(isDuplicate);
	}


	@PostMapping("/signup/phonenumber")
	@ApiOperation(value = "입력한 휴대폰 번호에 인증코드를 발송합니다")
	public ResponseEntity<?> checkPhoneNumber(@RequestBody String phoneNumber) {
		/**
		 * 프론트에서 phoneNumber JSON으로 보내줘야 합니다
		 * service에서 인증코드 전송 성공 여부를 반환받습니다
		 * userService.sendAuthCode
		 */
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}


	@PostMapping("/signup/authcode")
	@ApiOperation(value = "인증코드를 확인합니다")
	public ResponseEntity<?> checkAuthCode(@RequestBody String authCode) {
		/**
		 * 프론트에서 authCode를 JSON으로 보내줘야 합니다
		 * service에서 인증코드 일치 여부를 반환받습니다
		 * userService.checkAuthCode
		 */
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}

}
