package com.tonnybunny.domain.user.service;


import com.tonnybunny.domain.user.dto.HelperInfoRequestDto;
import com.tonnybunny.domain.user.entity.CertificateEntity;
import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import com.tonnybunny.domain.user.entity.PossibleLanguageEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
public class HelperInfoService {

	/**
	 * 유저가 가진 자격증 목록 조회
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return 자격증 목록 조회
	 */
	public List<CertificateEntity> getCertificateList(Long userSeq) {
		// TODO : 로직 구현
		return new ArrayList<>();
	}


	/**
	 * 대상 유저에 속하는 자격증을 추가, 한번에 여러 자격증을 등록
	 *
	 * @param userSeq                   : 대상 유저 seq
	 * @param certificateRequestDtoList : 등록할 자격증 목록
	 * @return 등록한 자격증 entity의 seq 목록
	 */
	public List<Long> createCertificateList(Long userSeq, List<String> certificateRequestDtoList) {
		// TODO : 로직 구현

		return new ArrayList<>();
	}


	/**
	 * 한번에 여러 자격증 정보를 삭제
	 *
	 * @param certificateSeqList : 삭제할 자격증 seq 목록
	 * @return 삭제 성공 여부
	 */
	public Boolean deleteCertificateList(List<Long> certificateSeqList) {
		// TODO : 로직 구현
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
		// TODO : 로직 구현
		return true;
	}


	/**
	 * 대상 유저의 가능 언어 목록을 조회
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return 가능 언어 목록 // FIXME : String(공통코드) or Entity 중 편한 걸로 수정하기.
	 */
	public List<PossibleLanguageEntity> getPossibleLangList(Long userSeq) {
		// TODO : 로직 구현

		return new ArrayList<>();
	}


	/**
	 * 대상 유저의 가능 언어를 추가, 한번에 여러 언어 추가 가능
	 *
	 * @param userSeq              : 대상 유저 seq
	 * @param possibleLangCodeList : 추가할 언어의 공통코드 목록
	 * @return 추가한 가능 언어 Entity의 seq 목록
	 */
	public List<Long> createPossibleLangList(Long userSeq, List<String> possibleLangCodeList) {
		// TODO : 로직 구현

		return new ArrayList<>();
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

		return true;
	}


	/**
	 * 헬퍼 정보를 수정.
	 * - 인자로 받은 userSeq를 통해 helperInfoSeq를 얻어서 Entity를 수정
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
		 createCertificateList(), deleteCertificateList();
		 3. 헬퍼 소개서 사진 목록 수정
		 createHelperInfoImageList(), deleteHelperInfoImageList();
		 4. 한 줄 소개, 본인 소개 수정
		 */

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

}
