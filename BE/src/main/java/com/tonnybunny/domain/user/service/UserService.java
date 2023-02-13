package com.tonnybunny.domain.user.service;


import com.tonnybunny.common.auth.dto.AuthResponseDto;
import com.tonnybunny.common.auth.entity.AuthEntity;
import com.tonnybunny.common.auth.repository.AuthRepository;
import com.tonnybunny.common.auth.service.AuthService;
import com.tonnybunny.domain.user.dto.AccountRequestDto;
import com.tonnybunny.domain.user.dto.AuthCodeRequestDto;
import com.tonnybunny.domain.user.dto.HistoryRequestDto;
import com.tonnybunny.domain.user.dto.UserRequestDto;
import com.tonnybunny.domain.user.entity.*;
import com.tonnybunny.domain.user.repository.*;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.tonnybunny.domain.user.dto.UserCodeEnum.헬퍼;
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
	private final RedisUtill redisUtil;

	private final String uploadFolder = "user" + File.separator + "profile";

	@Value("${app.file.path}")
	private String uploadPath;


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
		if (checkNickNameDuplication(userRequestDto)) {
			throw new CustomException(SIGNUP_INVALIDATION);
		}
		if (!checkPasswordMatch(userRequestDto.getPassword(), userRequestDto.getCheckPassword())) {
			throw new CustomException(SIGNUP_INVALIDATION);
		}

		// 유저 정보 저장
		UserEntity user = userRepository.save(
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

		// 헬퍼일 경우, 헬퍼 정보 생성
		if (user.getUserCode().equals(헬퍼.getUserCode())) {
			HelperInfoEntity helperInfo = HelperInfoEntity.builder()
			                                              .user(user)
			                                              .build();
			helperInfoRepository.save(helperInfo);
		}

		// 반환값 생성 및 리턴
		return user.getSeq();
	}

	// /**
	// * @param userRequestDto
	// * @return
	// */
	// @Transactional
	// public AuthResponseDto signin(UserRequestDto userRequestDto) {
	// UserEntity user =
	// userRepository
	// .findByEmail(userRequestDto.getEmail())
	// .orElseThrow(() -> new CustomException(LOGIN_BAD_REQUEST)
	// );
	// AuthEntity auth =
	// authRepository
	// .findByUserSeq(user.getSeq())
	// .orElseThrow(() -> new CustomException(REFRESH_TOKEN_NOT_FOUND));
	// if (!passwordEncoder.matches(userRequestDto.getPassword(),
	// user.getPassword())) {
	// throw new CustomException(LOGIN_BAD_REQUEST);
	// }
	// String accessToken = "";
	// String refreshToken = auth.getRefreshToken();
	//
	// accessToken = authService.generateJwtToken(auth.getUser());
	// refreshToken = authService.saveRefreshToken(user);
	// System.out.println("new refreshToken :" + refreshToken);
	// auth.refreshUpdate(refreshToken);
	// authRepository.save(auth);
	//
	// return new AuthResponseDto(accessToken, refreshToken, user.getSeq(),
	// user.getEmail(), user.getNickName(), user.getProfileImagePath(),
	// user.getPoint(), user.getUserCode());
	// }


	/**
	 * 토큰 확인 안하는 로그인 로직
	 *
	 * @param userRequestDto
	 * @return
	 */
	@Transactional
	public UserEntity signin(UserRequestDto userRequestDto) {
		UserEntity user = userRepository
			.findByEmail(userRequestDto.getEmail())
			.orElseThrow(() -> new CustomException(NOT_FOUND_USER));

		if (!passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword())) {
			throw new CustomException(LOGIN_BAD_REQUEST);
		}

		return user;
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
			() -> new CustomException(NOT_FOUND_TOKEN));
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


	public Boolean checkNickNameDuplication(UserRequestDto userRequestDto) {

		if (userRepository.findByNickName(userRequestDto.getNickName()).isPresent()) {
			return true;
		} else {
			return false;
		}

		/**
		 * repository 에서 닉네임 중복확인 절차를 마치고 true/false 를 반환해준다.
		 * Boolean isDuplicate = userRepository.checkNickNameDuplication(user);
		 * return isDuplicate;
		 */
	}


	/**
	 * 작성한 코드와 핸드폰번호를 입력받아서 동일한 값인지 확인한다.
	 *
	 * @param authCodeRequestDto
	 * @return
	 */
	public Boolean checkAuthCode(AuthCodeRequestDto authCodeRequestDto) {
		String authCode = authCodeRequestDto.getAuthCode();
		String phoneNumber = authCodeRequestDto.getPhoneNumber();

		String value = redisUtil.getData(authCode);
		System.out.println("value = " + value);
		if (value.equals(null)) {
			throw new CustomException(DATA_BAD_REQUEST);
		}
		if (!value.equals(phoneNumber)) {
			throw new CustomException(DATA_BAD_REQUEST);
		}
		redisUtil.deleteData(authCode);

		return true;
	}


	/**
	 * Email 과 Password 에 대한 유효성 검사는
	 * Spring Validation으로 수행
	 */

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


	/**
	 * 유저 정보 찾기 관련
	 * 인증은 다른 서비스에서 진행된다.
	 * 휴대폰번호와 인증 사실만 확인받는다.
	 *
	 * @param accountRequestDto
	 * @return
	 */
	public String findEmailByPhoneNumber(AccountRequestDto accountRequestDto) {
		if ((accountRequestDto.getIsAuthed()).isBlank()) {
			throw new CustomException(DATA_BAD_REQUEST);
		}
		UserEntity user = userRepository.findByPhoneNumber(accountRequestDto.getPhoneNumber()).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));

		return user.getEmail();
	}


	public Long findPasswordByPhoneNumber(AccountRequestDto accountRequestDto) {
		if ((accountRequestDto.getIsAuthed()).isBlank()) {
			System.out.println("인증정보없음");
			throw new CustomException(DATA_BAD_REQUEST);
		}
		String email = accountRequestDto.getEmail();
		String phoneNumber = accountRequestDto.getPhoneNumber();
		System.out.println("phoneNumber = " + phoneNumber);
		UserEntity user = userRepository.findByEmail(email).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));
		System.out.println("user.getPhoneNumber() = " + user.getPhoneNumber());
		// if (user.getPhoneNumber().equals(String.valueOf(phoneNumber))) {
		// System.out.println("핸드폰 번호 불일치");
		// throw new CustomException(DATA_BAD_REQUEST);
		// }

		return user.getSeq();
	}


	/**
	 * seq
	 * password
	 * checkPassword
	 *
	 * @param accountRequestDto
	 * @return Long
	 */
	@Transactional
	public Long resetPassword(Long userSeq, AccountRequestDto accountRequestDto) {

		if (!accountRequestDto.getPassword().equals(accountRequestDto.getCheckPassword())) {
			throw new CustomException(PASSWORD_NOT_MATCH);
		}
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));
		user.updatePassword(passwordEncoder.encode(accountRequestDto.getPassword()));
		userRepository.save(user);

		return user.getSeq();

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
	 * 유저 Seq 리스트로 받으면
	 * 유저 Entity List로 반환하는 서비스
	 *
	 * @param userSeqList
	 * @return
	 */
	@Transactional
	public List<UserEntity> getUserInfoList(List<Long> userSeqList) {
		List<UserEntity> result = new ArrayList<>();

		for (Long seq : userSeqList) {
			UserEntity user = userRepository.findById(seq).orElseThrow(
				() -> new CustomException(NOT_FOUND_USER));
			result.add(user);
		}

		return result;
	}


	/**
	 * 회원 정보 수정
	 *
	 * @param userSeq        : 수정할 user의 seq
	 * @param userRequestDto : 수정할 데이터
	 * @return 수정 후 user의 seq
	 */
	@Transactional
	public Long modifyNickName(Long userSeq, UserRequestDto userRequestDto) {
		System.out.println("userRequestDto.getNickName() = " + userRequestDto.getNickName());
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));

		user.updateNickName(userRequestDto.getNickName());
		userRepository.save(user);

		return user.getSeq();
	}


	/**
	 * 프로필 사진 수정
	 *
	 * @param userSeq
	 * @param request
	 * @return
	 */
	@Transactional
	public String modifyProfileImage(Long userSeq, MultipartHttpServletRequest request) {
		System.out.println("UserService.modifyProfileImage");
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));

		try {
			File uploadDir = new File(uploadPath + File.separator + uploadFolder); // 폴더가 없을 경우 생성하는 로직
			if (!uploadDir.exists())
				uploadDir.mkdirs();
			System.out.println("uploadDir = " + uploadDir);

			MultipartFile file = request.getFile("file"); // 하나만 전송되기 때문에 file
			System.out.println("file = " + file);

			if (file != null) {
				String originalFilename = file.getOriginalFilename();
				String extension = FilenameUtils.getExtension(originalFilename);
				UUID uuid = UUID.randomUUID();
				String fileName = uuid + "." + extension;
				System.out.println("fileName = " + fileName);

				String filePath = uploadFolder + File.separator + fileName;
				File saveFile = new File(uploadPath + File.separator + filePath);
				System.out.println("saveFile = " + saveFile);

				file.transferTo(saveFile);

				user.updateProfileImage(filePath);

			} else {
				user.updateProfileImage("noProfile.png"); // 디폴트로 변경
			}
			userRepository.save(user);

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(DATA_BAD_REQUEST);
		}
		return user.getProfileImagePath();
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
		System.out.println("passwordEncoder.encode(userRequestDto.getPassword() : "
			+ passwordEncoder.encode(userRequestDto.getPassword()));

		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));
		System.out.println("now Password : " + user.getPassword());
		if (!userRequestDto.getNewPassword().equals(userRequestDto.getCheckPassword())) {
			System.out.println("확인용 비밀번호가 일치하지 않습니다.");
			throw new CustomException(DATA_BAD_REQUEST);
		}
		if (!passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword())) {
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
	@Transactional
	public Boolean deleteUserInfo(Long userSeq) {
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));
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
	public List<Long> getFollowList(Long userSeq) {
		UserEntity user = userRepository.findById(userSeq)
		                                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
		List<Long> followedUserList = new ArrayList<>();
		List<FollowEntity> followList = user.getFollowUserList();
		for (FollowEntity followEntity : followList) {
			followedUserList.add(followEntity.getFollowedUserSeq());
		}

		return followedUserList;
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
			() -> new CustomException(NOT_FOUND_USER));
		UserEntity followedUser = userRepository.findById(followedUserSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));

		// 새로운 즐겨찾기 생성
		// 현재 즐겨찾기에 없을 경우만 추가
		if (user.getFollowUserList().contains(followedUser)) {
			throw new CustomException(DUPLICATE_RESOURCE);
		}
		FollowEntity follow = new FollowEntity(user, followedUserSeq);
		followRepository.save(follow);
		// 즐겨찾기 카운터 수정

		HelperInfoEntity helperInfo = followedUser.getHelperInfo();

		helperInfo.updateLikedCount(1);
		helperInfoRepository.save(helperInfo);
		return follow.getFollowedUserSeq();
	}


	/**
	 * 즐겨찾기 삭제
	 *
	 * @param userSeq         : 누군가를 삭제하기를 원하는 userSeq
	 * @param followedUserSeq : 삭제될 누군가의 seq
	 * @return
	 */
	@Transactional
	public Boolean deleteFollow(Long userSeq, Long followedUserSeq) {
		if (userSeq.equals(followedUserSeq)) {
			throw new CustomException(SAME_USER_REQUEST);
		}

		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));
		followRepository.deleteFollowBySeq(user, followedUserSeq);

		UserEntity followedUser = userRepository.findById(followedUserSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));
		HelperInfoEntity helperInfo = followedUser.getHelperInfo();
		helperInfo.updateLikedCount(-1);

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
	public List<Long> getBlockList(Long userSeq) {
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));

		List<Long> blockedUserList = new ArrayList<>();
		List<BlockEntity> blockList = user.getBlockUserList();
		for (BlockEntity blockEntity : blockList) {
			blockedUserList.add(blockEntity.getBlockedUserSeq());
		}

		return blockedUserList;
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
			() -> new CustomException(NOT_FOUND_USER));
		UserEntity blockUser = userRepository.findById(blockSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER));
		if (user.getBlockUserList().contains(blockUser)) {
			throw new CustomException(DUPLICATE_RESOURCE);
		}

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
			() -> new CustomException(NOT_FOUND_USER));
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
			() -> new CustomException(NOT_FOUND_USER));

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
		if (historyRequestDto.getOrderByCreatedAtAsc())
			sort = typedSort.by(HistoryEntity::getCreatedAt).ascending();
		else
			sort = typedSort.by(HistoryEntity::getCreatedAt).descending();

		// 목록 조회
		if (historyRequestDto.getClientSeq() != null) { // 고객 기준 조회
			UserEntity client = userRepository.findById(historyRequestDto.getClientSeq())
			                                  .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
			historyList = historyRepository.findByClient(client, sort);
		} else if (historyRequestDto.getHelperSeq() != null) { // 헬퍼 기준 조회
			UserEntity helper = userRepository.findById(historyRequestDto.getHelperSeq())
			                                  .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
			historyList = historyRepository.findByHelper(helper, sort);
		} else if (historyRequestDto.getLangCode() != null) { // 업무에 사용된 언어 기준 조회
			String langCode = historyRequestDto.getLangCode();
			historyList = historyRepository.findByStartLangCodeOrEndLangCode(langCode, langCode, sort);
		} else if (historyRequestDto.getTaskCode() != null) { // 업무 기준 조회 (통역, 번역)
			historyList = historyRepository.findByTaskCode(historyRequestDto.getTaskCode(), sort);
		} else { // 사용자 기준 전체 조회 (고객 또는 헬퍼로 참여한 업무 전체 조회)
			UserEntity user = userRepository.findById(userSeq).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
			historyList = historyRepository.findByClientOrHelper(user, user, sort);
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
