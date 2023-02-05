package com.tonnybunny.domain.user.service;


import com.tonnybunny.domain.user.dto.CertificateRequestDto;
import com.tonnybunny.domain.user.dto.HelperInfoRequestDto;
import com.tonnybunny.domain.user.entity.*;
import com.tonnybunny.domain.user.repository.*;
import com.tonnybunny.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.tonnybunny.exception.ErrorCode.NOT_FOUND_ENTITY;
import static com.tonnybunny.exception.ErrorCode.NOT_FOUND_USER;


@Service
@RequiredArgsConstructor
public class HelperInfoService {

	private final UserRepository userRepository;
	private final CertificateRepository certificateRepository;
	private final HelperInfoRepository helperInfoRepository;
	private final HelperInfoImageRepository helperInfoImageRepository;
	private final PossibleLanguageRepository possibleLanguageRepository;


	/**
	 * 유저가 가진 자격증 목록 조회
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return 자격증 목록 조회
	 */
	public List<CertificateEntity> getCertificateList(Long userSeq) {

		UserEntity user = userRepository.findById(userSeq).orElseThrow( // 대상 User 찾기
			() -> new CustomException(NOT_FOUND_USER)
		);
		HelperInfoEntity helperInfo = user.getHelperInfo(); // User를 토대로 헬퍼정보 꺼내오기
		List<CertificateEntity> certificateList = helperInfo.getCertificateList(); // 헬퍼정보에서 자격증 정보 꺼내기

		return certificateList;
	}


	/**
	 * 대상 유저에 속하는 자격증을 추가, 한번에 여러 자격증을 등록
	 *
	 * @param userSeq         : 대상 유저 seq
	 * @param certificateList : 등록할 자격증 목록
	 * @return 등록한 자격증 entity의 seq 목록
	 */
	public List<Long> createCertificateList(Long userSeq, List<CertificateRequestDto> certificateList) {

		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		HelperInfoEntity helperInfo = user.getHelperInfo(); // 대상 유저 헬퍼정보 가져오기

		List<Long> result = new ArrayList<>(); // 결과 seq 담을 리스트

		for (CertificateRequestDto certificateRequestDto : certificateList) {
			CertificateEntity certificate = new CertificateEntity(certificateRequestDto.getCertName(), certificateRequestDto.getContent(), helperInfo); // 새 Entity 생성
			certificateRepository.save(certificate); // Entity 저장
			result.add(certificate.getSeq()); // 결과 리스트에 시퀀스 추가
		}

		return result;
	}


	/**
	 * 한번에 여러 자격증 정보를 삭제
	 *
	 * @param certificateSeqList : 삭제할 자격증 seq 목록
	 * @return 삭제 성공 여부
	 */
	public Boolean deleteCertificateList(List<Long> certificateSeqList) {

		for (Long seq : certificateSeqList) {
			CertificateEntity certificate = certificateRepository.findById(seq).orElseThrow(
				() -> new CustomException(NOT_FOUND_USER)
			);
			certificate.deleteCertificate();
			certificateRepository.save(certificate);
		}

		return true;
	}


	/**
	 * 유저가 등록했던 헬퍼 소개서 이미지 목록을 조회
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return 헬퍼 소개서 이미지 파일 목록
	 */
	public List<MultipartFile> getHelperInfoImageList(Long userSeq) {
		// TODO : 로직 구현

		return new ArrayList<>();
	}


	/**
	 * 헬퍼 소개서 이미지를 등록, 한번에 여러 이미지 등록 가능
	 *
	 * @param helperInfoSeq   : 대상 헬퍼의 정보 seq
	 * @param helperInfoImage : 이미지 파일
	 * @return 추가한 헬퍼 소개서 이미지들의 seq 목록
	 */
	public List<Long> createHelperInfoImageList(Long helperInfoSeq, MultipartFile helperInfoImage) {
		// TODO : 로직 구현
		return new ArrayList<>();
	}


	/**
	 * 헬퍼 소개서 이미지를 삭제, 한번에 여러 이미지 삭제 가능
	 *
	 * @param helperInfoImageSeqList : 삭제할 헬퍼 소개서 이미지의 seq 목록
	 * @return 삭제 성공 여부
	 */
	public Boolean deleteHelperInfoImageList(List<Long> helperInfoImageSeqList) {

		for (Long seq : helperInfoImageSeqList) {
			HelperInfoImageEntity helperInfoImage = helperInfoImageRepository.findById(seq).orElseThrow(
				() -> new CustomException(NOT_FOUND_USER)
			);
			helperInfoImage.deleteHelperInfoImage();
			helperInfoImageRepository.save(helperInfoImage);
		}

		return true;
	}


	/**
	 * 대상 유저의 가능 언어 목록을 조회
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return 가능 언어 목록 // FIXME : String(공통코드) or Entity 중 편한 걸로 수정하기.
	 */
	public List<PossibleLanguageEntity> getPossibleLangList(Long userSeq) {
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		HelperInfoEntity helperInfo = helperInfoRepository.findByUser(user).orElseThrow(
			() -> new CustomException(NOT_FOUND_ENTITY)
		);
		List<PossibleLanguageEntity> possibleLanguageList = helperInfo.getPossibleLanguageList();

		return possibleLanguageList;
	}


	/**
	 * 대상 유저의 가능 언어를 추가, 한번에 여러 언어 추가 가능
	 * 대상 유저가 이미 가지고 있는 언어인지 확인 해야 함(N*M?)
	 *
	 * @param userSeq              : 대상 유저 seq
	 * @param possibleLangCodeList : 추가할 언어의 공통코드 목록
	 * @return 추가한 가능 언어 Entity의 seq 목록
	 */
	public List<Long> createPossibleLangList(Long userSeq, List<String> possibleLangCodeList) {

		UserEntity user = userRepository.findById(userSeq).orElseThrow( // 유저 정보 추출
			() -> new CustomException(NOT_FOUND_USER)
		);
		HelperInfoEntity helperInfo = helperInfoRepository.findByUser(user).orElseThrow( // 헬퍼정보 추출
			() -> new CustomException(NOT_FOUND_ENTITY)
		);
		List<PossibleLanguageEntity> possibleLanguageList = helperInfo.getPossibleLanguageList(); // 가능언어리스트 추출
		List<String> oldLangCode = new ArrayList<>();
		// 등록된 언어인지 확인
		for (PossibleLanguageEntity possibleLanguage : possibleLanguageList) {
			String langCode = possibleLanguage.getLangCode();
			oldLangCode.add(langCode);
		}

		List<Long> result = new ArrayList<>();

		for (String newLang : possibleLangCodeList) {
			if (!oldLangCode.contains(newLang)) {
				PossibleLanguageEntity possibleLanguage = new PossibleLanguageEntity(helperInfo, newLang);
				possibleLanguageRepository.save(possibleLanguage);
				result.add(possibleLanguage.getSeq());
			}
		}

		return result;
	}


	/**
	 * 가능 언어를 삭제, 한번에 여러 언어 삭제 가능
	 *
	 * @param userSeq             : 대상 유저 seq
	 * @param possibleLangSeqList : 삭제할 가능 언어 Entity의 seq 목록
	 * @return 삭제 성공 여부
	 */
	public Boolean deletePossibleLangList(Long userSeq, List<Long> possibleLangSeqList) {
		// TODO : 로직 구현
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		HelperInfoEntity helperInfo = helperInfoRepository.findByUser(user).orElseThrow(
			() -> new CustomException(NOT_FOUND_ENTITY)
		);
		List<PossibleLanguageEntity> possibleLanguageList = helperInfo.getPossibleLanguageList();

		// 로직 확인하고 만들던 거 계속 만들기

		return true;
	}


	/**
	 * 헬퍼 정보를 수정.
	 * 인자로 받은 userSeq를 통해 helperInfoSeq를 얻어서 Entity를 수정
	 * seq, 가능언어, 자격증(언어, 자격증 명, 내용)
	 *
	 * @param userSeq              : 대상 유저 seq
	 * @param helperInfoRequestDto : 수정할 헬퍼 정보
	 * @return 수정한 HelperInfoEntity의 seq
	 */
	public Long modifyHelperInfo(Long userSeq, HelperInfoRequestDto helperInfoRequestDto) {
		/** TODO : 로직 구현
		 1. 가능 언어 목록 수정
		 createPossibleLangList(), deletePossibleLangList();
		 2. 자격증 목록 수정
		 createCertificateList(), deleteCertificateList(); 내부 변수 어떻게 줄지. 여기서 가공 일단 해보자
		 3. 헬퍼 소개서 사진 목록 수정
		 createHelperInfoImageList(), deleteHelperInfoImageList();
		 4. 한 줄 소개, 본인 소개 수정
		 */
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		if (helperInfoRepository.findByUser(user).isPresent()) { // 이미 헬퍼정보가 있을 경우(modify의 경우)
			HelperInfoEntity helerInfo = helperInfoRepository.findByUser(user).orElseThrow();
		} else {

		}

		return 1L;
	}


	/**
	 * 대상 유저의 헬퍼 프로필 정보를 조회
	 *
	 * @param userSeq : 프로필을 조회하고 싶은 유저의 Seq
	 * @return 헬퍼 프로필 정보
	 */
	public HelperInfoEntity getHelperInfo(Long userSeq) {
		/** TODO : 로직 구현
		 1. 가능 언어 목록 조회
		 getPossibleLangList();
		 2. 자격증 목록 조회
		 getCertificateList();
		 3. 헬퍼 소개서 사진 목록 조회
		 getHelperInfoImageList();
		 4, 한 줄 소개, 본인 소개 조회
		 */
		return (HelperInfoEntity) new Object();
	}


	/**
	 * 유저의 공통코드를 헬퍼로 변경
	 *
	 * @param userSeq
	 * @return
	 */
	@Transactional
	public Long modifyUserCode(Long userSeq) {

		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		user.changeUserCode();
		userRepository.save(user);

		return userSeq;
	}

	/**
	 * 헬퍼 정보를 최초로 등록 (자격증 및 언어능력)
	 *
	 * @param userSeq
	 * @param helperInfoRequestDto
	 * @return
	 */
	//	public Long createHelperInfo(Long userSeq, HelperInfoRequestDto helperInfoRequestDto) {
	//		UserEntity user = userRepository.findById(userSeq).orElseThrow(
	//			() -> new CustomException(NOT_FOUND_USER)
	//		);
	//		if (!helperInfoRepository.findByUser(user).isPresent()) {
	//			throw new CustomException(DUPLICATE_RESOURCE);
	//		}
	//		List<PossibleLanguageEntity> possibleLanguageList = createPossibleLangList(userSeq, helperInfoRequestDto.getPossibleLanguageList());
	//		List<CertificateEntity> certificateList = createCertificateList(userSeq, helperInfoRequestDto.getCertificateList());
	//
	//		HelperInfoEntity helperInfo = new HelperInfoEntity(user, possibleLanguageList, helperInfoRequestDto.getCertificateList());
	//
	//	}

}
