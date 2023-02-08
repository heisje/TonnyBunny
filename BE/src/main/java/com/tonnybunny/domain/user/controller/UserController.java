package com.tonnybunny.domain.user.controller;


import com.tonnybunny.common.auth.dto.AuthResponseDto;
import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.user.dto.*;
import com.tonnybunny.domain.user.entity.*;
import com.tonnybunny.domain.user.repository.HelperInfoRepository;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.domain.user.service.EmailService;
import com.tonnybunny.domain.user.service.HelperInfoService;
import com.tonnybunny.domain.user.service.SmsService;
import com.tonnybunny.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.util.List;

import static com.tonnybunny.domain.user.dto.UserCodeEnum.클라이언트;


@RestController
@RequiredArgsConstructor
@Api(tags = "사용자 정보 관련 API")
public class UserController {

	private final UserService userService;
	private final HelperInfoService helperInfoService;

	private final SmsService smsService;
	private final EmailService emailService;
	private final HelperInfoRepository helperInfoRepository;
	private final UserRepository userRepository;


	@PostMapping("/signup")
	@ApiOperation(value = "공통 회원가입을 진행합니다.")
	public ResponseEntity<ResultDto<Long>> signup(@RequestBody @Valid UserRequestDto userRequestDto) {

		Long userSeq = userService.signup(userRequestDto);

		return ResponseEntity.status(HttpStatus.OK)
		                     .body(ResultDto.of(userSeq));

	}

	//	@PostMapping("/signin")
	//	@ApiOperation(value = "로그인 기능을 수행합니다.")
	//	public ResponseEntity<ResultDto<AuthResponseDto>> signin(@RequestBody UserRequestDto userRequestDto) {
	//		AuthResponseDto authResponseDto = userService.signin(userRequestDto);
	//
	//		return ResponseEntity.ok().body(ResultDto.of(authResponseDto));
	//	}


	/**
	 * 토큰을 요구하지 않는 테스트 로그인
	 *
	 * @param userRequestDto
	 * @return
	 */
	@PostMapping("/signin")
	@ApiOperation(value = "로그인 기능을 수행합니다.")
	public ResponseEntity<ResultDto<UserResponseDto>> signin(@RequestBody UserRequestDto userRequestDto) {
		UserEntity user = userService.signin(userRequestDto);
		UserResponseDto userResponseDto = UserResponseDto.fromEntity(user);

		return ResponseEntity.ok().body(ResultDto.of(userResponseDto));
	}


	/**
	 * Header에 있는 Refresh Token 확인
	 * Refresh Token의 seq와 UserSeq가 동일한지 확인
	 * 유효기간이 남았으면 Success와 함께 새로운 Token 반환
	 * 유효기간이 지났으면 실패 메세지 전송
	 */
	@GetMapping("/refresh/{userSeq}")
	@ApiOperation(value = "인증 토큰을 확인합니다.")
	public ResponseEntity<ResultDto<AuthResponseDto>> checkRefreshToken(@RequestHeader(value = "REFRESH_TOKEN") String refreshToken,
	                                                                    @PathVariable("userSeq") Long userSeq) {

		System.out.println("refreshToken = " + refreshToken);
		AuthResponseDto authResponseDto = userService.checkRefreshToken(refreshToken, userSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(authResponseDto));

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


	/**
	 * Request : "to" 유의(phoneNumber로 하니 에러)
	 * 사용자의 번호를 입력받아서 해당 번호로 랜덤으로 생성된 인증코드 전송
	 * 해당 코드 Redis에 저장. 저장기한 3분
	 *
	 * @param messageRequestDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/send/authcode")
	@ApiOperation(value = "입력한 휴대폰 번호에 인증코드를 발송합니다")
	public ResponseEntity<ResultDto<String>> sendAuthCode(@RequestBody MessageRequestDto messageRequestDto) {

		//		smsService.sendSms(messageRequestDto); 테스트 중에는 주석처리
		String code = smsService.smsTest(messageRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(code));
	}


	@PostMapping("/send/authcode/test")
	@ApiOperation(value = "입력한 휴대폰 번호에 인증코드를 발송합니다(테스트)")
	public ResponseEntity<ResultDto<String>> sendAuthCodeTest(@RequestBody MessageRequestDto messageRequestDto) {

		String code = smsService.smsTest(messageRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(code));
	}

	// 이메일 전송 오류 해결해야 해서, 일단 주석처리
	//	@PostMapping("/send/authcode/email")
	//	@ApiOperation(value = "입력한 이메일에 인증코드를 발송합니다")
	//	public ResponseEntity<ResultDto<String>> sendAuthCodeEmail(@RequestBody EmailRequestDto emailRequestDto) throws Exception {
	//		String email = emailRequestDto.getEmail();
	//		System.out.println("email = " + email);
	//		String authenticCode = emailService.sendSimpleMessage(email);
	//		System.out.println("authenticCode = " + authenticCode);
	//		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(authenticCode));
	//
	//	}


	/**
	 * 프론트에서 전송받은 인증코드와 휴대전화를 확인
	 * 해당하는 인증코드가 없거나 만료되었을 경우 에러
	 * value와 번호가 일치할 경우 true
	 *
	 * @param authCodeRequestDto
	 * @return
	 */
	@PostMapping("/check/authcode")
	@ApiOperation(value = "인증코드를 확인합니다")
	public ResponseEntity<ResultDto<Boolean>> checkAuthCode(@RequestBody AuthCodeRequestDto authCodeRequestDto) {
		System.out.println("authCodeRequestDto = " + authCodeRequestDto);
		userService.checkAuthCode(authCodeRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}

	//	@PostMapping("/login")
	//	@ApiOperation(value = "로그인을 진행합니다")
	//	public ResponseEntity<ResultDto<Boolean>> login(@RequestBody UserRequestDto userRequestDto) {
	//
	//		Boolean isSuccess = userService.login(userRequestDto);
	//		if (isSuccess) {
	//			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	//		} else {
	//			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
	//		}
	//	}

	//	@PostMapping("/logout")
	//	@ApiOperation(value = "로그아웃을 진행합니다")
	//	public ResponseEntity<ResultDto<Boolean>> logout(@RequestBody UserRequestDto userRequestDto) {
	//		Boolean isSuccess = userService.logout(userRequestDto);
	//		if (isSuccess) {
	//			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	//		} else {
	//			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
	//		}
	//	}


	/**
	 * 두 과정이 요구값과 로직이 다르므로 별도로 구분
	 * 아이디 찾기의 경우 찾는 Email을 반환
	 *
	 * @param accountRequestDto
	 * @return
	 */
	@PostMapping("/login/find/email")
	@ApiOperation(value = "아이디 찾기를 진행합니다")
	public ResponseEntity<ResultDto<String>> findEmailByPhoneNumber(@RequestBody AccountRequestDto accountRequestDto) {
		/**
		 * 프론트에서는 accountResponseDto에서 필요한 정보를 꺼내 쓴다 (Email or Boolean)
		 */
		String email = userService.findEmailByPhoneNumber(accountRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(email));
	}


	/**
	 * 비밀번호 찾기의 경우 인증되었을 경우, 새 비밀번호 설정 페이지로 이동
	 * 해당 로직의 경우 리턴값을 Boolean으로 설정
	 *
	 * @param accountRequestDto
	 * @return
	 */
	@PostMapping("/login/find/password")
	@ApiOperation(value = "비밀번호 찾기를 진행합니다")
	public ResponseEntity<ResultDto<Long>> findPasswordByPhoneNumber(@RequestBody AccountRequestDto accountRequestDto) {

		Long userSeq = userService.findPasswordByPhoneNumber(accountRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(userSeq));
	}


	/**
	 * 비밀번호를 알려준다는 것은 불가능하므로
	 * 비밀번호를 재설정하는 로직으로
	 * 유저정보에 있는 비밀번호변경을 재활용
	 *
	 * @param accountRequestDto
	 * @return
	 */
	@PostMapping("/login/find/password/{userSeq}")
	@ApiOperation(value = "비밀번호를 재설정 합니다.")
	public ResponseEntity<ResultDto<Long>> resetPassword(@PathVariable("userSeq") Long userSeq, @RequestBody AccountRequestDto accountRequestDto) {

		userService.resetPassword(userSeq, accountRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(userSeq));
	}

	// -------------------------------------- 마이페이지 ---------------------------------------------


	@GetMapping("/mypage/{userSeq}")
	@ApiOperation(value = "회원정보를 조회합니다")
	public ResponseEntity<ResultDto<UserResponseDto>> getUserInfo(@PathVariable("userSeq") Long userSeq) {
		System.out.println("userSeq = " + userSeq);
		UserEntity searchedUser = userService.getUserInfo(userSeq);
		if (searchedUser.getUserCode().equals(클라이언트.getUserCode())) {
			UserResponseDto userResponseDto = UserResponseDto.fromEntity(searchedUser);
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(userResponseDto));
		} else {
			UserResponseDto userResponseDto = UserResponseDto.createUserWithHelper(searchedUser);
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(userResponseDto));
		}

	}


	/**
	 * 유저 닉네임만 수정
	 *
	 * @param userSeq
	 * @param userRequestDto
	 * @return
	 */
	@PutMapping("/mypage/{userSeq}/nickName")
	@ApiOperation(value = "닉네임을 수정합니다")
	public ResponseEntity<ResultDto<Long>> modifyNickName(@PathVariable("userSeq") Long userSeq,
	                                                      @RequestBody UserRequestDto userRequestDto) {
		Long updatedUserSeq = userService.modifyNickName(userSeq, userRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedUserSeq));
	}


	/**
	 * 생성과 동일한 로직
	 *
	 * @param userSeq
	 * @param request
	 * @return
	 */
	@PutMapping("/mypage/{userSeq}/profileImage")
	@ApiOperation(value = "프로필사진을 수정합니다")
	public ResponseEntity<ResultDto<String>> modifyProfileImage(@PathVariable("userSeq") Long userSeq, MultipartHttpServletRequest request) {
		System.out.println("request = " + request.toString());
		String profileFilePath = userService.modifyProfileImage(userSeq, request);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(profileFilePath));
	}


	@PutMapping("/mypage/{userSeq}/password")
	@ApiOperation(value = "비밀번호를 수정합니다")
	public ResponseEntity<ResultDto<Long>> modifyUserPassword(@PathVariable("userSeq") Long userSeq,
	                                                          @RequestBody UserRequestDto userRequestDto) {
		Long updatedUserSeq = userService.modifyUserPassword(userSeq, userRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedUserSeq));
	}


	@DeleteMapping("/mypage/{userSeq}")
	@ApiOperation(value = "회원정보를 삭제합니다")
	public ResponseEntity<ResultDto<Boolean>> deleteUserInfo(@PathVariable("userSeq") Long userSeq) {
		userService.deleteUserInfo(userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

	// --------------------------------- 즐겨찾기 ------------------------------------


	/**
	 * 사용자의 시퀀스값을 제공하면
	 * 사용자가 팔로우한 유저들의 RequestDto를 리스트로 반환함
	 */
	@GetMapping("/mypage/{userSeq}/follow")
	@ApiOperation(value = "즐겨찾기 목록을 조회합니다.")
	public ResponseEntity<ResultDto<List<UserResponseDto>>> getFollowList(@PathVariable(
		"userSeq") Long userSeq) {

		List<Long> followedUserList = userService.getFollowList(userSeq);
		List<UserEntity> userEntityList = userService.getUserInfoList(followedUserList);
		List<UserResponseDto> userResponseDtoList = UserResponseDto.fromEntityList(userEntityList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(userResponseDtoList));
	}


	@PostMapping("/mypage/{userSeq}/follow/{followSeq}")
	@ApiOperation(value = "즐겨찾기 목록에 유저를 추가합니다")
	public ResponseEntity<ResultDto<Long>> createFollow(@PathVariable("userSeq") Long userSeq,
	                                                    @PathVariable("followSeq") Long followSeq) {
		Long followUserSeq = userService.createFollow(userSeq, followSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(followUserSeq));

	}


	@DeleteMapping("/mypage/{userSeq}/follow/{followSeq}")
	@ApiOperation(value = "즐겨찾기 목록에서 유저를 삭제합니다")
	public ResponseEntity<ResultDto<Boolean>> deleteFollow(@PathVariable("userSeq") Long userSeq,
	                                                       @PathVariable("followSeq") Long followSeq) {
		userService.deleteFollow(userSeq, followSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}

	// --------------------------------- 차단 ------------------------------------


	/**
	 * 사용자의 시퀀스값을 제공하면
	 * 사용자가 차단한 유저들의 RequestDto를 리스트로 반환함
	 */
	@GetMapping("/mypage/{userSeq}/block/{blockSeq}")
	@ApiOperation(value = "차단 목록을 조회합니다.")
	public ResponseEntity<ResultDto<List<UserResponseDto>>> getBlockList(@PathVariable("userSeq") Long userSeq) {

		List<Long> blockedUserList = userService.getBlockList(userSeq);
		List<UserEntity> userEntityList = userService.getUserInfoList(blockedUserList);
		List<UserResponseDto> userResponseDtoList = UserResponseDto.fromEntityList(userEntityList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(userResponseDtoList));
	}


	@PostMapping("/mypage/{userSeq}/block/{blockSeq}")
	@ApiOperation(value = "유저를 차단합니다")
	public ResponseEntity<ResultDto<Long>> createBlock(@PathVariable("userSeq") Long userSeq,
	                                                   @PathVariable("blockSeq") Long blockSeq) {
		Long blockedUserSeq = userService.createBlock(userSeq, blockSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(blockedUserSeq));

	}


	@DeleteMapping("/mypage/{userSeq}/block/{blockSeq}")
	@ApiOperation(value = "유저 차단을 취소합니다")
	public ResponseEntity<ResultDto<Boolean>> deleteBlock(@PathVariable("userSeq") Long userSeq,
	                                                      @PathVariable("blockSeq") Long blockSeq) {

		userService.deleteBlock(userSeq, blockSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}

	// --------------------------------- 신고 ------------------------------------


	@PostMapping("/mypage/{userSeq}/report/{reportSeq}")
	@ApiOperation(value = "유저를 신고합니다")
	public ResponseEntity<ResultDto<Long>> createReport(@PathVariable("userSeq") Long userSeq,
	                                                    @PathVariable("reportSeq") Long reportSeq) {

		userService.createReport(userSeq, reportSeq);
		// 신고한 유저 자동 차단
		userService.createBlock(userSeq, reportSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(reportSeq));

	}

	// --------------------------------- 히스토리 ------------------------------------


	/**
	 * @param userSeq : 현재 로그인 유저 seq
	 * @return : 히스토리 목록 DtoList
	 */
	@GetMapping("/mypage/{userSeq}/history")
	@ApiOperation(value = "히스토리 목록을 조회합니다", notes = "조회 필터링 조건은 하나씩만 적용됩니다. 아무 조건을 넣지 않으면 내역 전체 조회를 합니다.")
	public ResponseEntity<ResultDto<List<HistoryResponseDto>>> getUserHistoryList(@PathVariable("userSeq") Long userSeq,
	                                                                              @RequestBody HistoryRequestDto historyRequestDto) {
		List<HistoryEntity> historyList = userService.getUserHistoryList(userSeq, historyRequestDto);
		List<HistoryResponseDto> historyResponseDtoList = HistoryResponseDto.fromEntityList(
			historyList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(historyResponseDtoList));
	}


	@GetMapping("/mypage/{userSeq}/history/{historySeq}")
	@ApiOperation(value = "히스토리 하나를 조회합니다")
	public ResponseEntity<ResultDto<HistoryResponseDto>> getUserHistory(@PathVariable("userSeq") Long userSeq,
	                                                                    @PathVariable("historySeq") Long historySeq) {
		HistoryEntity history = userService.getUserHistory(userSeq, historySeq);
		HistoryResponseDto historyResponseDto = HistoryResponseDto.fromEntity(history);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(historyResponseDto));
	}

	// ------------------------------ 헬퍼 프로필 ---------------------------------------


	@PostMapping("/mypage/{userSeq}/helper")
	@ApiOperation(value = "헬퍼의 능력 정보를 등록합니다.")
	public ResponseEntity<ResultDto<Long>> createHelperInfo(@PathVariable("userSeq") Long userSeq, @RequestBody HelperInfoRequestDto helperInfoRequestDto) {
		System.out.println("UserController.createHelperInfo");
		HelperInfoEntity helperInfo = helperInfoService.createHelperInfo(userSeq, helperInfoRequestDto);

		Long helperInfoSeq = helperInfo.getSeq();
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(helperInfoSeq));
	}


	@PutMapping("/mypage/{userSeq}/helper")
	@ApiOperation(value = "헬퍼의 프로필 정보를 수정합니다")
	public ResponseEntity<ResultDto<Long>> modifyHelperInfo(@PathVariable("userSeq") Long userSeq, @RequestBody HelperInfoRequestDto helperInfoRequestDto,
	                                                        MultipartHttpServletRequest request) {
		System.out.println("UserController.modifyHelperInfo");
		HelperInfoEntity helperInfo = helperInfoService.modifyHelperInfo(userSeq, helperInfoRequestDto, request);

		Long helperInfoSeq = helperInfo.getSeq();
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(helperInfoSeq));
	}


	@GetMapping("/mypage/{userSeq}/helper")
	@ApiOperation(value = "헬퍼의 프로필 정보를 조회합니다")
	public ResponseEntity<ResultDto<HelperInfoResponseDto>> getHelperInfo(@PathVariable("userSeq") Long userSeq) {
		HelperInfoEntity helperInfo = helperInfoService.getHelperInfo(userSeq);
		HelperInfoResponseDto helperInfoResponseDto = HelperInfoResponseDto.fromEntity(helperInfo);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(helperInfoResponseDto));

	}


	/**
	 * 일반 유저가 헬퍼 약관에 동의하면서 유저 코드를 변경
	 *
	 * @param userSeq
	 * @return
	 */
	@PutMapping("/mypage/{userSeq}/userCode")
	@ApiOperation(value = "일반 고객의 유저코드를 헬퍼로 변경합니다.")
	public ResponseEntity<ResultDto<Long>> modifyUserCode(@PathVariable("userSeq") Long userSeq) {
		Long helperInfoSeq = helperInfoService.modifyUserCode(userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(helperInfoSeq));
	}


	/**
	 * 헬퍼의 자격증 리스트를 조회합니다.
	 */
	@GetMapping("/mypage/{userSeq}/helper/certificate")
	@ApiOperation(value = "헬퍼의 자격증 정보를 조회합니다")
	public ResponseEntity<ResultDto<List<CertificateResponseDto>>> getHelperInfoCertificate(@PathVariable("userSeq") Long userSeq) {
		List<CertificateEntity> certificateList = helperInfoService.getCertificateList(userSeq);
		List<CertificateResponseDto> certificateResponseList = CertificateResponseDto.fromEntityList(certificateList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(certificateResponseList));

	}


	/**
	 * 헬퍼의 사용 언어 리스트를 조회합니다.
	 */
	@GetMapping("/mypage/{userSeq}/helper/possibleLang")
	@ApiOperation(value = "헬퍼의 사용 언어 정보를 조회합니다")
	public ResponseEntity<ResultDto<List<PossibleLanguageDto>>> getHelperInfoPossibleLang(@PathVariable("userSeq") Long userSeq) {
		List<PossibleLanguageEntity> possibleLangList = helperInfoService.getPossibleLangList(userSeq);
		List<PossibleLanguageDto> possibleLanguageList = PossibleLanguageDto.fromEntityList(possibleLangList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(possibleLanguageList));

	}


	/**
	 * 헬퍼의 이미지 리스트를 조회합니다.
	 */
	@GetMapping("/mypage/{userSeq}/helper/image")
	@ApiOperation(value = "헬퍼의 정보 이미지를 조회합니다")
	public ResponseEntity<ResultDto<List<HelperInfoImageResponseDto>>> getHelperInfoImage(@PathVariable("userSeq") Long userSeq) {
		List<HelperInfoImageEntity> helperInfoImageList = helperInfoService.getHelperInfoImageList(userSeq);
		List<HelperInfoImageResponseDto> helperInfoImageResponseList = HelperInfoImageResponseDto.fromEntityList(helperInfoImageList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(helperInfoImageResponseList));

	}

}
