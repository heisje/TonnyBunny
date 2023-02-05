package com.tonnybunny.domain.user.service;


import com.tonnybunny.common.auth.dto.AuthResponseDto;
import com.tonnybunny.common.auth.entity.AuthEntity;
import com.tonnybunny.common.auth.repository.AuthRepository;
import com.tonnybunny.common.auth.service.AuthService;
import com.tonnybunny.domain.user.dto.AccountRequestDto;
import com.tonnybunny.domain.user.dto.AccountResponseDto;
import com.tonnybunny.domain.user.dto.HistoryRequestDto;
import com.tonnybunny.domain.user.dto.UserRequestDto;
import com.tonnybunny.domain.user.entity.BlockEntity;
import com.tonnybunny.domain.user.entity.FollowEntity;
import com.tonnybunny.domain.user.entity.HistoryEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.*;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.tonnybunny.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final HistoryRepository historyRepository;
	private final AuthService authService;
	private final AuthRepository authRepository;
	private final PasswordEncoder passwordEncoder;
	private final FollowRepository followRepository;
	private final BlockRepository blockRepository;
	private final HelperInfoRepository helperInfoRepository;


	public Optional<UserEntity> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	@Transactional
	public Long signup(UserRequestDto userRequestDto) {

		/**
		 * 기존에 요청으로 확인했던 부분들을 여기서 재확인해야 할지에 대한 고민
		 * 이메일과 닉네임 중복 확인의 경우? 직접 데이터가 들어올 수 있다는 점을 생각하면 검증해야할듯
		 */
		if (findByEmail(userRequestDto.getEmail()).isPresent()) {
			throw new CustomException(SIGNUP_INVALIDATION);
		}
		if (checkNicknameDuplication(userRequestDto)) {
			throw new CustomException(SIGNUP_INVALIDATION);
		}
		if (!checkPasswordMatch(userRequestDto.getPassword(), userRequestDto.getCheckPassword())) {
			throw new CustomException(SIGNUP_INVALIDATION);
		}

		// 유저 정보 저장
		UserEntity user =
			userRepository.save(
				UserEntity.builder()
				          .password(passwordEncoder.encode(userRequestDto.getPassword()))
				          .email(userRequestDto.getEmail())
				          .phoneNumber(userRequestDto.getPhoneNumber())
				          .nickName(userRequestDto.getNickName())
				          .userCode(userRequestDto.getUserCode())
				          .build());

		String refreshToken = authService.saveRefreshToken(user);

		// 토큰 정보 저장
		authRepository.save(
			AuthEntity.builder().user(user).refreshToken(refreshToken).build());

		// 반환값 생성 및 리턴
		return user.getSeq();
	}


	/**
	 * @param userRequestDto
	 * @return
	 */
	@Transactional
	public AuthResponseDto signin(UserRequestDto userRequestDto) {
		UserEntity user =
			userRepository
				.findByEmail(userRequestDto.getEmail())
				.orElseThrow(() -> new CustomException(LOGIN_BAD_REQUEST)
				            );
		AuthEntity auth =
			authRepository
				.findByUserSeq(user.getSeq())
				.orElseThrow(() -> new CustomException(REFRESH_TOKEN_NOT_FOUND));
		if (!passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword())) {
			throw new CustomException(LOGIN_BAD_REQUEST);
		}
		String accessToken = "";
		String refreshToken = auth.getRefreshToken();

		accessToken = authService.generateJwtToken(auth.getUser());
		refreshToken = authService.saveRefreshToken(user);
		System.out.println("new refreshToken :" + refreshToken);
		auth.refreshUpdate(refreshToken);
		authRepository.save(auth);

		return new AuthResponseDto(accessToken, refreshToken, user.getSeq(), user.getEmail(), user.getNickName(), user.getProfileImagePath(), user.getPoint(), user.getUserCode());
	}


	/**
	 * 리프레시 토큰의 유효기간과 시퀀스 일치 여부 확인
	 *
	 * @return AuthResponseDto
	 */
	@Transactional
	public AuthResponseDto checkRefreshToken(String refreshToken, Long userSeq) {
		authService.isValidRefreshToken(refreshToken); // token 유효성 확인(유효기간 및 형식)
		Long tokenUserSeq = authService.extractRefreshTokenInfo(refreshToken);
		if (!userSeq.equals(tokenUserSeq)) { // 보낸 유저와 토큰 내의 유저정보가 일치하지 않을 경우
			throw new CustomException(REFRESH_TOKEN_ERROR);
		}
		// 위에서 유효성 검사 및 보낸 사용자를 확인했으므로 새로운 Access Token 과 Refresh Token을 발급한다.
		AuthEntity auth = authRepository.findByUserSeq(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_TOKEN)
		                                                                   );
		if (!auth.getRefreshToken().equals(refreshToken)) { // DB에 있는 정보와 한번 더 비교하여 오류처리
			System.out.println("refreshToken = " + refreshToken + ", DBToken = " + auth.getRefreshToken());
			throw new CustomException(REFRESH_TOKEN_ERROR);
		}
		UserEntity user = userRepository.findById(userSeq).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
		String accessToken = authService.generateJwtToken(user);
		String newRefreshToken = authService.saveRefreshToken(user);
		auth.refreshUpdate(newRefreshToken);
		authRepository.save(auth);

		return new AuthResponseDto(accessToken, newRefreshToken);
	}


	public List<UserEntity> findUsers() {
		return userRepository.findAll();
	}


	public Boolean checkNicknameDuplication(UserRequestDto userRequestDto) {

		if (userRepository.findByNickName(userRequestDto.getNickName()).isPresent()) {
			return true;
		} else {
			return false;
		}

		/**
		 * repository 에서 닉네임 중복확인 절차를 마치고 true/false 를 반환해준다.
		 * Boolean isDuplicate = userRepository.checkNicknameDuplication(user);
		 * return isDuplicate;
		 */
	}


	public Boolean sendAuthCode(String phoneNumber) {
		/**
		 * 휴대폰으로 인증코드 발송 해야함
		 */
		return true;
	}


	public Boolean checkAuthCode(String authCode) {
		/**
		 * 인증코드 일치 여부 검증
		 */
		return true;
	}

	// 회원가입 시 약관동의 여부 True로 받아오기로 합의
	// private Boolean checkAgreementStatus(Boolean bool) {
	// 	/**
	// 	 * 프론트에서는 모든 약관에 동의했는지에 대한 데이터를 넘겨주어야 한다
	// 	 * bool (약관동의) 가 true 일때 true를 반환한다
	// 	 */
	// 	return bool;
	// }


	/**
	 * Email 과 Password 에 대한 유효성 검사는
	 * Spring Validation으로 수행
	 */
	//	private Boolean checkEmailValidation(String email) {
	//		/**
	//		 * 이메일 유효성 검사 로직
	//		 */
	//		return true;
	//	}

	//	private Boolean checkPasswordValidation(String password) {
	//		/**
	//		 * 비밀번호 유효성 확인 로직
	//		 * 불일치 시 비밀번호 양식에 맞지 않다고 출력
	//		 */
	//		return true;
	//	}
	private Boolean checkPasswordMatch(String password, String checkPassword) {
		/**
		 * 비밀번호 일치 확인 로직
		 * 불일치 시 비밀번호가 일치하지 않습니다 출력
		 */
		if (password.equals(checkPassword)) {
			return true;
		} else {
			return false;
		}
	}

	//	public Boolean login(UserRequestDto userRequestDto) {
	//		UserEntity user = userRequestDto.toEntity();
	//		/**
	//		 * user.email로 user를 조회 => findUserByEmail
	//		 * 조회가 안될 시 익셉션 터트리기
	//		 * user.password를 encryptPassword로 복호화 후 비교하여 결과 반환
	//		 * 일치하지 않을 시 익셉션 터트리기
	//		 */
	//		return true;
	//	}

	//
	//	public Boolean logout(UserRequestDto userRequestDto) {
	//		UserEntity user = userRequestDto.toEntity();
	//		/**
	//		 * 로그아웃 진행 코드
	//		 * 토큰 회수? 무슨 방식?
	//		 * access token 의 유효시간을 짧게 하는 것으로 대체
	//		 */
	//		return true;
	//	}


	/**
	 * 유저 정보 찾기 관련
	 *
	 * @param accountRequestDto
	 * @return
	 */
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


	/**
	 * 회원 정보 조회
	 *
	 * @param userSeq : 조회할 userSeq 포함
	 * @return findUserBySeq로 조회된 searchedUser
	 */
	@Transactional
	public UserEntity getUserInfo(Long userSeq) {
		// TODO : 로직
		UserEntity user = userRepository.findById(userSeq)
		                                .orElseThrow(
			                                () -> new CustomException(NOT_FOUND_USER));
		return user;
	}


	/**
	 * 회원 정보 수정
	 *
	 * @param userSeq        : 수정할 user의 seq
	 * @param userRequestDto : 수정할 데이터
	 * @return 수정 후 user의 seq
	 */
	@Transactional
	public Long modifyUserInfo(Long userSeq, UserRequestDto userRequestDto) {

		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                              );

		user.updateUserInfo(userRequestDto.getProfileImagePath(), userRequestDto.getNickName());
		userRepository.save(user);

		return user.getSeq();
	}


	/**
	 * 회원 비밀번호 수정
	 *
	 * @param userSeq        : 수정할 user의 seq
	 * @param userRequestDto : 수정할 데이터
	 * @return 수정 후 user의 seq
	 */
	@Transactional
	public Long modifyUserPassword(Long userSeq, UserRequestDto userRequestDto) {

		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                              );
		if (!userRequestDto.getNewPassword().equals(userRequestDto.getCheckPassword())) {
			System.out.println("확인용 비밀번호가 일치하지 않습니다.");
			throw new CustomException(DATA_BAD_REQUEST);
		}
		if (!passwordEncoder.encode(userRequestDto.getPassword()).equals(user.getPassword())) {
			System.out.println("현재 비밀번호와 일치하지 않는 비밀번호입니다.");
			throw new CustomException(PASSWORD_NOT_MATCH);
		}
		if (passwordEncoder.encode(userRequestDto.getNewPassword()).equals(user.getPassword())) {
			System.out.println("이전 비밀번호와 현재 비밀번호가 같습니다.");
			throw new CustomException(DATA_BAD_REQUEST);
		}

		user.updatePassword(passwordEncoder.encode(userRequestDto.getNewPassword()));
		userRepository.save(user);

		return user.getSeq();
	}


	/**
	 * 회원 정보 삭제
	 *
	 * @param userSeq : 삭제할 user의 seq
	 * @return 유저 삭제 로직 성공 여부
	 */
	public Boolean deleteUserInfo(Long userSeq) {
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                              );
		user.deleteUserInfo();
		userRepository.save(user);

		return true;
	}

	/**
	 * 삭제된 회원인지 확인
	 */

	/**
	 * 즐겨찾기 조회
	 *
	 * repository 에서 findFollowList() 를 수행한다.
	 *
	 * @return List<FollowResponseDto>
	 */

	@Transactional
	public List<FollowEntity> getFollowList(Long userSeq) {
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                              );

		List<FollowEntity> followList = user.getFollowUserList();

		return followList;
	}


	/**
	 * 즐겨찾기 추가
	 *
	 * @param userSeq         : 누군가를 추가하기를 원하는 userSeq
	 * @param followedUserSeq : 추가될 유저의 seq
	 * @return
	 */
	@Transactional
	public Long createFollow(Long userSeq, Long followedUserSeq) {
		if (userSeq.equals(followedUserSeq)) {
			throw new CustomException(SAME_USER_REQUEST);
		}
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                              );
		FollowEntity follow = new FollowEntity(user, followedUserSeq);
		followRepository.save(follow);

		return follow.getFollowedUserSeq();
	}


	/**
	 * 즐겨찾기 삭제
	 *
	 * @param userSeq   : 누군가를 삭제하기를 원하는 userSeq
	 * @param followSeq : 삭제될 누군가의 seq
	 * @return
	 */
	@Transactional
	public Boolean deleteFollow(Long userSeq, Long followSeq) {
		if (userSeq.equals(followSeq)) {
			throw new CustomException(SAME_USER_REQUEST);
		}

		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                              );
		followRepository.deleteFollowBySeq(user, followSeq);
		return true;
	}


	/**
	 * 차단목록 조회
	 *
	 * repository 에서 findBlockList() 를 수행한다.
	 *
	 * @return List<FollowResponseDto>
	 */

	@Transactional
	public List<BlockEntity> getBlockList(Long userSeq) {
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                              );

		List<BlockEntity> blockList = user.getBlockUserList();

		return blockList;
	}


	/**
	 * 차단 유저 추가
	 *
	 * @param userSeq  : 누군가를 추가하기를 원하는 userSeq
	 * @param blockSeq : 추가될 누군가의 seq
	 * @return
	 */
	@Transactional
	public Long createBlock(Long userSeq, Long blockSeq) {
		if (userSeq.equals(blockSeq)) {
			throw new CustomException(SAME_USER_REQUEST);
		}

		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                              );
		BlockEntity block = new BlockEntity(user, blockSeq);
		blockRepository.save(block);

		return block.getBlockedUserSeq();
	}


	/**
	 * 차단 유저 삭제
	 *
	 * @param userSeq  : 누군가를 삭제하기를 원하는 userSeq
	 * @param blockSeq : 삭제될 누군가의 seq
	 * @return
	 */
	@Transactional
	public Boolean deleteBlock(Long userSeq, Long blockSeq) {
		if (userSeq.equals(blockSeq)) {
			throw new CustomException(SAME_USER_REQUEST);
		}

		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                              );
		blockRepository.deleteBlockBySeq(user, blockSeq);

		return true;

	}


	/**
	 * 유저 신고하기
	 *
	 * @param userSeq   : 로그인 유저 seq
	 * @param reportSeq : 신고 당한 유저 seq
	 * @return
	 */
	@Transactional
	public Boolean createReport(Long userSeq, Long reportSeq) {
		if (userSeq.equals(reportSeq)) {
			throw new CustomException(SAME_USER_REQUEST);
		}

		UserEntity reportedUser = userRepository.findById(reportSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		                                                                        );

		reportedUser.setReportCount(reportedUser.getReportCount() + 1);
		userRepository.save(reportedUser);

		return true;
	}

	// --------------------------------- 히스토리 ------------------------------------


	/**
	 * @param userSeq           : 로그인 유저 seq
	 * @param historyRequestDto : 목록 조회 필터링 조건
	 * @return 히스토리 목록 EntityList
	 */
	@Transactional
	public List<HistoryEntity> getUserHistoryList(Long userSeq, HistoryRequestDto historyRequestDto) {
		/**
		 * 히스토리 목록 조회 로직
		 *
		 * 필터링 기준
		 * 1. 고객 기준
		 * 2. 헬퍼 기준
		 * 3. 통역/번역 기준
		 * 4. 언어 기준 (시작 언어, 번역 언어 통틀어서 조회)
		 */
		List<HistoryEntity> historyList;
		// 정렬 기준 동적 생성
		Sort.TypedSort<HistoryEntity> typedSort = Sort.sort(HistoryEntity.class);
		Sort sort;
		if (historyRequestDto.getOrderByCreatedAtAsc()) sort = typedSort.by(HistoryEntity::getCreatedAt).ascending();
		else sort = typedSort.by(HistoryEntity::getCreatedAt).descending();

		// 목록 조회
		if (historyRequestDto.getClientSeq() != null) { // 고객 기준 조회
			historyList = historyRepository.findByClient(historyRequestDto.getClientSeq(), sort);
		} else if (historyRequestDto.getHelperSeq() != null) { // 헬퍼 기준 조회
			historyList = historyRepository.findByHelper(historyRequestDto.getHelperSeq(), sort);
		} else if (historyRequestDto.getLangCode() != null) { // 업무에 사용된 언어 기준 조회
			String langCode = historyRequestDto.getLangCode();
			historyList = historyRepository.findByStartLangCodeOrEndLangCode(langCode, langCode, sort);
		} else if (historyRequestDto.getTaskCode() != null) { // 업무 기준 조회 (통역, 번역)
			historyList = historyRepository.findByTaskCode(historyRequestDto.getTaskCode(), sort);
		} else { // 사용자 기준 전체 조회 (고객 또는 헬퍼로 참여한 업무 전체 조회)
			historyList = historyRepository.findByClientOrHelper(userSeq, userSeq, sort);
		}
		return historyList;
	}


	/**
	 * @param userSeq    : 로그인 사용자 seq
	 * @param historySeq : 조회할 히스토리 seq
	 * @return
	 */
	@Transactional
	public HistoryEntity getUserHistory(Long userSeq, Long historySeq) {
		/**
		 * 히스토리 단일 조회
		 */
		Optional<HistoryEntity> history = historyRepository.findById(historySeq);
		return history.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
	}

}
