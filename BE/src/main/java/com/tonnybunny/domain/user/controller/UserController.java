package com.tonnybunny.domain.user.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.user.dto.*;
import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import com.tonnybunny.domain.user.entity.HistoryEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.service.HelperInfoService;
import com.tonnybunny.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(tags = "회원가입 관련 API")
public class UserController {

	private final UserService userService;
	private final HelperInfoService helperInfoService;


	@PostMapping("/signup")
	@ApiOperation(value = "회원가입을 진행합니다")
	public ResponseEntity<ResultDto<UserResponseDto>> signup(@RequestBody UserRequestDto userRequestDto) {
		UserEntity savedUser = userService.signup(userRequestDto);
		UserResponseDto userResponseDto = UserResponseDto.fromEntity(savedUser);
		/**
		 * 회원가입 방식, 무엇을 리턴할지 미정
		 */

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(userResponseDto));
	}


	@PostMapping("/signup/nickname")
	@ApiOperation(value = "닉네임 중복여부를 확인합니다")
	public ResponseEntity<ResultDto<Boolean>> checkNicknameDuplication(@RequestBody UserRequestDto userRequestDto) {
		/**
		 * service에서 반환받은 isDuplicate가 true 이면 중복된 닉네임이라고 알려주기, false 면 통과
		 *
		 */
		Boolean isDuplicate = userService.checkNicknameDuplication(userRequestDto);
		if (isDuplicate) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		}
	}


	@PostMapping("/send/authcode")
	@ApiOperation(value = "입력한 휴대폰 번호에 인증코드를 발송합니다")
	public ResponseEntity<ResultDto<Boolean>> sendAuthCode(@RequestBody String phoneNumber) {
		/**
		 * 프론트에서 phoneNumber JSON으로 보내줘야 합니다
		 * service에서 인증코드 전송 성공 여부를 반환받습니다
		 * userService.sendAuthCode
		 */
		Boolean isSuccess = userService.sendAuthCode(phoneNumber);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	@PostMapping("/check/authcode")
	@ApiOperation(value = "인증코드를 확인합니다")
	public ResponseEntity<ResultDto<Boolean>> checkAuthCode(@RequestBody String authCode) {
		/**
		 * 프론트에서 authCode를 JSON으로 보내줘야 합니다
		 * service에서 인증코드 일치 여부를 반환받습니다
		 * userService.checkAuthCode
		 */
		Boolean isSuccess = userService.checkAuthCode(authCode);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	@PostMapping("/login")
	@ApiOperation(value = "로그인을 진행합니다")
	public ResponseEntity<ResultDto<Boolean>> login(@RequestBody UserRequestDto userRequestDto) {

		Boolean isSuccess = userService.login(userRequestDto);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	@PostMapping("/logout")
	@ApiOperation(value = "로그아웃을 진행합니다")
	public ResponseEntity<ResultDto<Boolean>> logout(@RequestBody UserRequestDto userRequestDto) {
		Boolean isSuccess = userService.logout(userRequestDto);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	@PostMapping("/login/find")
	@ApiOperation(value = "아이디/비밀번호 찾기를 진행합니다")
	public ResponseEntity<ResultDto<AccountResponseDto>> findAccountInfo(@RequestBody AccountRequestDto accountRequestDto) {
		/**
		 * 프론트에서는 accountResponseDto에서 필요한 정보를 꺼내 쓴다 (Email or Boolean)
		 */
		AccountResponseDto accountResponseDto = userService.findAccouontInfo(accountRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(accountResponseDto));
	}

	// -------------------------------------- 마이페이지 ---------------------------------------------


	@GetMapping("/mypage/{userSeq}")
	@ApiOperation(value = "회원정보를 조회합니다")
	public ResponseEntity<ResultDto<UserResponseDto>> getUserInfo(
		@PathVariable("userSeq") Long userSeq) {
		UserEntity searchedUser = userService.getUserInfo(userSeq);
		UserResponseDto userResponseDto = UserResponseDto.fromEntity(searchedUser);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(userResponseDto));
	}


	@PutMapping("/mypage/{userSeq}")
	@ApiOperation(value = "회원정보를 수정합니다")
	public ResponseEntity<ResultDto<Long>> modifyUserInfo(@PathVariable("userSeq") Long userSeq,
		@RequestBody UserRequestDto userRequestDto) {
		Long updatedUserSeq = userService.modifyUserInfo(userSeq, userRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedUserSeq));
	}


	@DeleteMapping("/mypage/{userSeq}")
	@ApiOperation(value = "회원정보를 삭제합니다")
	public ResponseEntity<ResultDto<Boolean>> deleteUserInfo(
		@PathVariable("userSeq") Long userSeq) {
		Boolean isSuccess = userService.deleteUserInfo(userSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

	// --------------------------------- 즐겨찾기 ------------------------------------


	@PostMapping("/mypage/{userSeq}/follow/{followSeq}")
	@ApiOperation(value = "즐겨찾기 목록에 유저를 추가합니다")
	public ResponseEntity<ResultDto<Boolean>> createBookmark(@PathVariable("userSeq") Long userSeq,
		@PathVariable("followSeq") Long followSeq) {
		Boolean isSuccess = userService.createBookmark(userSeq, followSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	@DeleteMapping("/mypage/{userSeq}/follow/{followSeq}")
	@ApiOperation(value = "즐겨찾기 목록에서 유저를 삭제합니다")
	public ResponseEntity<ResultDto<Boolean>> deleteBookmark(@PathVariable("userSeq") Long userSeq,
		@PathVariable("followSeq") Long followSeq) {
		Boolean isSuccess = userService.deleteBookmark(userSeq, followSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

	// --------------------------------- 차단 ------------------------------------


	@PostMapping("/mypage/{userSeq}/block/{blockSeq}")
	@ApiOperation(value = "유저를 차단합니다")
	public ResponseEntity<ResultDto<Boolean>> createBlock(
		@PathVariable("userSeq") Long userSeq, @PathVariable("blockSeq") Long blockSeq) {
		Boolean isSuccess = userService.createBlock(userSeq, blockSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	@DeleteMapping("/mypage/{userSeq}/block/{blockSeq}")
	@ApiOperation(value = "유저 차단을 취소합니다")
	public ResponseEntity<ResultDto<Boolean>> deleteBlock(
		@PathVariable("userSeq") Long userSeq, @PathVariable("blockSeq") Long blockSeq) {
		Boolean isSuccess = userService.deleteBlock(userSeq, blockSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

	// --------------------------------- 신고 ------------------------------------


	@PostMapping("/mypage/report")
	@ApiOperation(value = "유저를 신고합니다")
	public ResponseEntity<ResultDto<Boolean>> createReport(
		@RequestBody ReportRequestDto reportRequestDto) {
		Boolean isSuccess = userService.createReport(reportRequestDto);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

	// --------------------------------- 히스토리 ------------------------------------


	/**
	 * @param userSeq : 현재 로그인 유저 seq
	 * @return : 히스토리 목록 DtoList
	 */
	@GetMapping("/mypage/{userSeq}/history")
	@ApiOperation(value = "히스토리 목록을 조회합니다")
	public ResponseEntity<ResultDto<List<HistoryResponseDto>>> getUserHistoryList(@PathVariable("userSeq") Long userSeq) {
		List<HistoryEntity> historyEntityList = userService.getUserHistoryList(userSeq);
		List<HistoryResponseDto> historyResponseDtoList = HistoryResponseDto.fromEntityList(historyEntityList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(historyResponseDtoList));
	}


	@GetMapping("/mypage/{userSeq}/history/{historySeq}")
	@ApiOperation(value = "히스토리 하나를 조회합니다")
	public ResponseEntity<ResultDto<HistoryResponseDto>> getUserHistory(@PathVariable("userSeq") Long userSeq, @PathVariable("historySeq") Long historySeq) {
		HistoryEntity historyEntity = userService.getUserHistory(userSeq, historySeq);
		HistoryResponseDto historyResponseDto = HistoryResponseDto.fromEntity(historyEntity);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(historyResponseDto));
	}

	// ------------------------------ 헬퍼 프로필 ---------------------------------------


	@PutMapping("/mypage/{userSeq}/helper")
	@ApiOperation(value = "헬퍼의 프로필 정보를 수정합니다")
	public ResponseEntity<ResultDto<Long>> modifyHelperInfo(@PathVariable("userSeq") Long userSeq, @RequestBody HelperInfoRequestDto helperInfoRequestDto) {
		Long updatedHelperInfoSeq = helperInfoService.modifyHelperInfo(userSeq, helperInfoRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedHelperInfoSeq));
	}


	@GetMapping("/mypage/{userSeq}/helper")
	@ApiOperation(value = "헬퍼의 프로필 정보를 조회합니다")
	public ResponseEntity<ResultDto<HelperInfoResponseDto>> getHelperInfo(@PathVariable("userSeq") Long userSeq) {
		HelperInfoEntity helperInfo = helperInfoService.getHelperInfo(userSeq);
		HelperInfoResponseDto helperInfoResponseDto = HelperInfoResponseDto.fromEntity(helperInfo);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(helperInfoResponseDto));

	}

}
