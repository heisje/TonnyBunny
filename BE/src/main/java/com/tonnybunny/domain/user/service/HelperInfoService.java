package com.tonnybunny.domain.user.service;


import com.tonnybunny.domain.user.dto.CertificateRequestDto;
import com.tonnybunny.domain.user.dto.HelperInfoRequestDto;
import com.tonnybunny.domain.user.dto.PossibleLanguageDto;
import com.tonnybunny.domain.user.entity.*;
import com.tonnybunny.domain.user.repository.*;
import com.tonnybunny.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tonnybunny.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class HelperInfoService {

	private final UserRepository userRepository;
	private final CertificateRepository certificateRepository;
	private final HelperInfoRepository helperInfoRepository;
	private final HelperInfoImageRepository helperInfoImageRepository;
	private final PossibleLanguageRepository possibleLanguageRepository;

	private final String uploadFolder = "helperinfo" + File.separator + "image";

	@Value("${app.file.path}")
	private String uploadPath;


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
	 * @param helperInfo      : 대상 유저 헬퍼 정보
	 * @param certificateList : 등록할 자격증 목록
	 * @return 등록한 자격증 entity의 seq 목록
	 */
	@Transactional
	public List<CertificateEntity> createCertificateList(HelperInfoEntity helperInfo, List<CertificateRequestDto> certificateList) {
		System.out.println("HelperInfoService.createCertificateList");
		//		List<Long> result = new ArrayList<>(); // 결과 seq 담을 리스트
		List<CertificateEntity> newCertificateList = new ArrayList<>();

		for (CertificateRequestDto certificateRequestDto : certificateList) {
			CertificateEntity certificate = CertificateEntity.builder()
			                                                 .helperInfo(helperInfo)
			                                                 .langCode(certificateRequestDto.getLangCode())
			                                                 .certName(certificateRequestDto.getCertName())
			                                                 .content(certificateRequestDto.getContent())
			                                                 .build();
			certificateRepository.save(certificate); // Entity 저장
			newCertificateList.add(certificate); // 결과 리스트에 시퀀스 추가
		}
		System.out.println("HelperInfoService.createCertificateList");
		return newCertificateList;
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
	public List<HelperInfoImageEntity> getHelperInfoImageList(Long userSeq) {
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		HelperInfoEntity helperInfo = helperInfoRepository.findByUser(user).orElseThrow(
			() -> new CustomException(NOT_FOUND_ENTITY)
		);
		List<HelperInfoImageEntity> helperInfoImageList = helperInfo.getHelperInfoImageList();

		return helperInfoImageList;
	}


	/**
	 * 헬퍼 소개서 이미지를 등록, 한번에 여러 이미지 등록 가능
	 *
	 * @param helperInfo : 대상 헬퍼의 정보
	 * @param request    : 이미지 파일
	 * @return 추가한 헬퍼 소개서 이미지들의 seq 목록
	 */
	@Transactional
	public List<HelperInfoImageEntity> createHelperInfoImageList(HelperInfoEntity helperInfo, MultipartHttpServletRequest request) {

		List<HelperInfoImageEntity> helperInfoImageList = new ArrayList<>();

		try {
			// upload file path 세팅
			// static/upload/helperinfo/image
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) uploadDir.mkdirs(); // 업로드 경로가 존재하지 않으면 폴더 생성하기

			// form file data 가져오기
			List<MultipartFile> fileList = request.getFiles("file");
			System.out.println("fileList = " + fileList);

			if (!fileList.isEmpty() && fileList != null) {

				for (MultipartFile partFile : fileList) {

					// file name
					String originalFilename = partFile.getOriginalFilename(); // 오리지널 파일 명
					String extension = FilenameUtils.getExtension(originalFilename); // 확장자 뽑아내기
					UUID uuid = UUID.randomUUID(); // 랜덤 생성된 파일 UUID
					String fileName = uuid + "." + extension; // 실제 저장되는 file name
					System.out.println("fileName = " + fileName);

					// file object
					String filePath = uploadFolder + File.separator + fileName;
					File saveFile = new File(uploadPath + File.separator + filePath);
					System.out.println("saveFile = " + saveFile);

					// file save
					partFile.transferTo(saveFile);

					// 이미지 Entity 생성
					HelperInfoImageEntity helperInfoImage = HelperInfoImageEntity.builder()
					                                                             .helperInfo(helperInfo)
					                                                             .imagePath(filePath)
					                                                             .build();
					helperInfoImageRepository.save(helperInfoImage);
					helperInfoImageList.add(helperInfoImage);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(DATA_BAD_REQUEST);
		}

		return helperInfoImageList;
	}


	/**
	 * 헬퍼 소개서 이미지를 삭제, 한번에 여러 이미지 삭제 가능
	 *
	 * @param helperInfoImageSeqList : 삭제할 헬퍼 소개서 이미지의 seq 목록
	 * @return 삭제 성공 여부
	 */
	@Transactional
	public Boolean deleteHelperInfoImageList(List<Long> helperInfoImageSeqList) {
		// 삭제할 이미지의 시퀀스를 프론트에서 리스트 형태로 받아옴
		for (Long helperInfoImageSeq : helperInfoImageSeqList) {
			/**
			 * 실제 파일 삭제 로직
			 */
			// 엔티티로 꺼내기
			HelperInfoImageEntity helperInfoImage = helperInfoImageRepository.findById(helperInfoImageSeq).orElseThrow(
				() -> new CustomException(NOT_FOUND_ENTITY)
			);
			String filePath = helperInfoImage.getImagePath();
			File file = new File(filePath);
			if (file.exists()) { // 파일이 존재할 경우
				file.delete(); // 해당 파일 삭제
			}
			/**
			 * 엔티티 삭제 로직
			 */
			helperInfoImageRepository.delete(helperInfoImage); // 해당 엔티티 삭제

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
	//	public List<PossibleLanguageEntity> createPossibleLangList(Long userSeq, List<String> possibleLangCodeList) {
	//
	//		UserEntity user = userRepository.findById(userSeq).orElseThrow( // 유저 정보 추출
	//			() -> new CustomException(NOT_FOUND_USER)
	//		);
	//		HelperInfoEntity helperInfo = helperInfoRepository.findByUser(user).orElseThrow( // 헬퍼정보 추출
	//			() -> new CustomException(NOT_FOUND_ENTITY)
	//		);
	//		List<PossibleLanguageEntity> possibleLanguageList = helperInfo.getPossibleLanguageList(); // 가능언어리스트 추출
	//		List<String> oldLangCode = new ArrayList<>();
	//		// 등록된 언어인지 확인
	//		for (PossibleLanguageEntity possibleLanguage : possibleLanguageList) {
	//			String langCode = possibleLanguage.getLangCode();
	//			oldLangCode.add(langCode);
	//		}
	//
	//		List<PossibleLanguageEntity> result = new ArrayList<>();
	//
	//		for (String newLang : possibleLangCodeList) {
	//			if (!oldLangCode.contains(newLang)) {
	//				PossibleLanguageEntity possibleLanguage = new PossibleLanguageEntity(helperInfo, newLang);
	//				possibleLanguageRepository.save(possibleLanguage);
	//				result.add(possibleLanguage);
	//			}
	//		}
	//
	//		return result;
	//	}


	/**
	 * 대상 유저에 속하는 언어 정보 리스트 생성
	 *
	 * @param helperInfo
	 * @param possibleLanguageList
	 * @return
	 */
	@Transactional
	public List<PossibleLanguageEntity> createPossibleLangList(HelperInfoEntity helperInfo, List<PossibleLanguageDto> possibleLanguageList) {
		System.out.println("HelperInfoService.createPossibleLangList");
		//		List<Long> result = new ArrayList<>(); // 결과 seq 담을 리스트
		List<PossibleLanguageEntity> newPossibleLanguageList = new ArrayList<>();

		for (PossibleLanguageDto possibleLanguage : possibleLanguageList) {
			PossibleLanguageEntity possibleLang = PossibleLanguageEntity.builder()
			                                                            .helperInfo(helperInfo)
			                                                            .langCode(possibleLanguage.getValue())
			                                                            .langName(possibleLanguage.getName())
			                                                            .build();
			possibleLanguageRepository.save(possibleLang);
			newPossibleLanguageList.add(possibleLang);
		}
		System.out.println("HelperInfoService.createPossibleLangList");
		return newPossibleLanguageList;
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
	 * 헬퍼의 자격증, 언어정보를 토대로 헬퍼 정보 생성
	 *
	 * @param userSeq
	 * @param helperInfoRequestDto
	 * @return
	 */
	public HelperInfoEntity createHelperInfo(Long userSeq, HelperInfoRequestDto helperInfoRequestDto) {

		System.out.println("HelperInfoService.createHelperInfo");
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		System.out.println("user = " + user);
		HelperInfoEntity helperInfo = helperInfoRepository.findByUser(user).orElseThrow(
			() -> new CustomException(NOT_FOUND_ENTITY)
		);
		System.out.println("helperInfo = " + helperInfo);

		// 삭제하고, create로 다시 생성
		certificateRepository.deleteAllByHelperInfo(helperInfo);
		possibleLanguageRepository.deleteAllByHelperInfo(helperInfo);

		// 삭제 로직

		// 자격증 리스트
		List<CertificateRequestDto> certificateList = helperInfoRequestDto.getCertificateList();
		if (certificateList.isEmpty()) { // 만약 빈 리스트를 받았을 경우
			System.out.println("empty certificate");
			List<CertificateEntity> newCertificateList = new ArrayList<>();
			helperInfo.updateCertificateList(newCertificateList); // 빈 리스트로 업데이트
		} else {
			List<CertificateEntity> newCertificateList = createCertificateList(helperInfo, certificateList);
			helperInfo.updateCertificateList(newCertificateList);
		}

		List<PossibleLanguageDto> possibleLanguageList = helperInfoRequestDto.getPossibleLanguageList();
		if (possibleLanguageList.isEmpty()) { // 만약 빈 리스트를 받았을 경우
			System.out.println("empty language");
			List<PossibleLanguageEntity> newPossibleLangList = new ArrayList<>();
			helperInfo.updatePossibleLanguageList(newPossibleLangList); // 빈 리스트로 업데이트
		} else {
			List<PossibleLanguageEntity> newPossibleLangList = createPossibleLangList(helperInfo, possibleLanguageList);
			helperInfo.updatePossibleLanguageList(newPossibleLangList);
		}

		helperInfoRepository.save(helperInfo);

		System.out.println("helperInfo = " + helperInfo.toString());

		return helperInfo;
	}


	/**
	 * 헬퍼 정보를 수정.
	 * 인자로 받은 userSeq를 통해 helperInfoSeq를 얻어서 Entity를 수정
	 * seq, 가능언어, 자격증(언어, 자격증 명, 내용)
	 * 기존에 있던 정보를 삭제하고, 새로운 정보를 입력
	 * 이미지 리스트는 유무가 불분명하므로 분기처리
	 *
	 * @param userSeq              : 대상 유저 seq
	 * @param helperInfoRequestDto : 수정할 헬퍼 정보
	 * @return 수정한 HelperInfoEntity의 seq
	 */
	public HelperInfoEntity modifyHelperInfo(Long userSeq, HelperInfoRequestDto helperInfoRequestDto, MultipartHttpServletRequest request) {
		/** TODO : 로직 구현
		 1. 가능 언어 목록 수정
		 createPossibleLangList(), deletePossibleLangList();
		 2. 자격증 목록 수정
		 createCertificateList(), deleteCertificateList(); 내부 변수 어떻게 줄지. 여기서 가공 일단 해보자
		 3. 헬퍼 소개서 사진 목록 수정
		 createHelperInfoImageList(), deleteHelperInfoImageList();
		 4. 한 줄 소개, 본인 소개 수정
		 */
		System.out.println("HelperInfoService.modifyHelperInfo");
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		HelperInfoEntity helperInfo = helperInfoRepository.findByUser(user).orElseThrow(
			() -> new CustomException(NOT_FOUND_ENTITY)
		);
		// 걍 뭉탱이로 꺼내기
		// 각자 로직 구현하기
		// 삭제하고, create로 다시 생성
		certificateRepository.deleteAllByHelperInfo(helperInfo);
		possibleLanguageRepository.deleteAllByHelperInfo(helperInfo);

		// 삭제 로직

		// 자격증 리스트
		List<CertificateRequestDto> certificateList = helperInfoRequestDto.getCertificateList();
		if (certificateList.isEmpty()) { // 만약 빈 리스트를 받았을 경우
			System.out.println("empty certificate");
			List<CertificateEntity> newCertificateList = new ArrayList<>();
			helperInfo.updateCertificateList(newCertificateList); // 빈 리스트로 업데이트
		} else {
			List<CertificateEntity> newCertificateList = createCertificateList(helperInfo, certificateList);
			helperInfo.updateCertificateList(newCertificateList);
		}

		List<PossibleLanguageDto> possibleLanguageList = helperInfoRequestDto.getPossibleLanguageList();
		if (possibleLanguageList.isEmpty()) { // 만약 빈 리스트를 받았을 경우
			System.out.println("empty language");
			List<PossibleLanguageEntity> newPossibleLangList = new ArrayList<>();
			helperInfo.updatePossibleLanguageList(newPossibleLangList); // 빈 리스트로 업데이트
		} else {
			List<PossibleLanguageEntity> newPossibleLangList = createPossibleLangList(helperInfo, possibleLanguageList);
			helperInfo.updatePossibleLanguageList(newPossibleLangList);
		}

		/**
		 * 이미지 저장
		 */
		List<Long> deleteHelperInfoImageList = helperInfoRequestDto.getDeleteHelperInfoImageList(); // 삭제할 이미지 시퀀스 리스트 가져오기
		if (!deleteHelperInfoImageList.isEmpty()) {                                                 // 삭제할 사진이 있을 경우
			deleteHelperInfoImageList(deleteHelperInfoImageList);                                   // 삭제 진행하기

		}
		List<HelperInfoImageEntity> helperInfoImageList = createHelperInfoImageList(helperInfo, request);// 새로 들어온 파일을 토대로 헬퍼 이미지 리스트 생성

		String oneLineIntroduction = helperInfoRequestDto.getOneLineIntroduction();
		String introduction = helperInfoRequestDto.getIntroduction();

		helperInfo.updateOneLineIntroduction(oneLineIntroduction);
		helperInfo.updateIntroduction(introduction);
		helperInfoRepository.save(helperInfo);

		System.out.println("helperInfo = " + helperInfo.toString());

		return helperInfo;
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
		UserEntity user = userRepository.findById(userSeq).orElseThrow(
			() -> new CustomException(NOT_FOUND_USER)
		);
		HelperInfoEntity helperInfo = helperInfoRepository.findByUser(user).orElseThrow(
			() -> new CustomException(NOT_FOUND_ENTITY)
		);

		return helperInfo;
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

		HelperInfoEntity helperInfo = HelperInfoEntity.builder()
		                                              .user(user)
		                                              .build();
		helperInfoRepository.save(helperInfo);

		return helperInfo.getSeq();
	}


	@Transactional
	public Boolean modifyUnitPrice(HelperInfoEntity helperInfo, Integer unitPrice) {
		try {
			helperInfo.updateUnitPrice(unitPrice);
			helperInfoRepository.save(helperInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(DATA_BAD_REQUEST);
		}

		return true;
	}

}
