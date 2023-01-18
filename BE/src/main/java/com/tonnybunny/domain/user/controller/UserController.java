package com.tonnybunny.domain.user.controller;


import com.tonnybunny.domain.user.dto.UserRequestDto;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Api(tags = "회원가입 관련 API")
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<UserResponseDto> signup(UserRequestDto userRequestDto) {
		UserEntity savedUser = userService.signup(userRequestDto);
		// String session = userService.login(savedUser);

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@PostMapping("/signup/check-nickname")
	public ResponseEntity<Boolean> checkNicknameDuplication(UserRequestDto userRequestDto) {
		/**
		 * service에서 반환받은 isDuplicate가 true 이면 회원가입 실패, false 이면 회원가입 통과
		 */
		Boolean isDuplicate = userService.checkNicknameDuplication(userRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(isDuplicate);
	}

}
