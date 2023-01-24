package com.tonnybunny.domain.bunny.service;


import com.tonnybunny.domain.bunny.dto.BunnyNotiHelperRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyResultRequestDto;
import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import com.tonnybunny.domain.bunny.entity.BunnyNotiHelperEntity;
import com.tonnybunny.domain.bunny.entity.BunnyResultEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BunnyService {

	// --------------------------------------- 번역 공고 ---------------------------------------


	/**
	 * 번역 공고 생성
	 *
	 * @param bunnyNotiRequestDto : 번역 공고 생성 폼
	 * @return : 생성된 공고의 seq
	 */
	public Long createBunnyNoti(BunnyNotiRequestDto bunnyNotiRequestDto) {
		// TODO : 로직
		BunnyNotiEntity bunnyNoti = bunnyNotiRequestDto.toEntity();
		return bunnyNoti.getSeq();
	}


	/**
	 * 번역 공고 삭제
	 *
	 * @param bunnyNotiSeq : 삭제할 번역 공고의 seq
	 * @return : 삭제 성공 여부
	 */
	public Boolean deleteBunnyNoti(Long bunnyNotiSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 번역 공고 상세 조회
	 *
	 * @param bunnyNotiSeq : 조회할 번역 공고의 seq
	 * @return : 조회된 공고의 Entity
	 */
	public BunnyNotiEntity getBunnyNoti(Long bunnyNotiSeq) {
		// TODO : 로직
		return (BunnyNotiEntity) new Object();
	}


	/**
	 * 번역 공고 목록 조회 (필터기능)
	 *
	 * @param lang     : 언어코드
	 * @param category : 카테고리코드
	 * @return : 조회된 번역 공고 EntityList
	 */
	public List<BunnyNotiEntity> getBunnyListByFilter(String lang, String category) {
		// TODO : 로직

		if (lang == null && category == null) {
			// 둘 다 필터하여 조회
		} else if (category == null) {
			// 언어만 필터하여 조회
		} else if (lang == null) {
			// 카테고리만 필터하여 조회
		} else {
			// 필터하지 않고 전체 조회
		}

		return new ArrayList<>();
	}


	/**
	 * 번역 공고 신청 생성
	 *
	 * @param bunnyNotiHelperRequestDto : 번역 공고 신청 정보
	 * @return : 생성된 신청 seq
	 */
	public Long createBunnyNotiHelper(BunnyNotiHelperRequestDto bunnyNotiHelperRequestDto) {
		// TODO : 로직

		BunnyNotiHelperEntity bunnyNotiHelper = bunnyNotiHelperRequestDto.toEntity();
		return bunnyNotiHelper.getSeq();
	}


	/**
	 * 번역 공고 신청 취소
	 *
	 * @param bunnyNotiHelperSeq : 취소할 신청 seq
	 * @return : 로직 성공 여부
	 */
	public Boolean deleteBunnyNotiHelper(Long bunnyNotiHelperSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 번역 공고 신청 목록 조회
	 *
	 * @return : 번역 공고 신청 Entity List
	 */
	public List<BunnyNotiHelperEntity> getBunnyNotiHelperList() {
		// TODO : 로직

		return new ArrayList<>();
	}


	/**
	 * 번역 공고 신청 상세 조회
	 *
	 * @param bunnyNotiHelperSeq : 조회할 신청 seq
	 * @return : 조회된 신청 Entity
	 */
	public BunnyNotiHelperEntity getBunnyNotiHelper(Long bunnyNotiHelperSeq) {
		// TODO : 로직

		return (BunnyNotiHelperEntity) new Object();
	}


	/**
	 * 번역 공고 신청 수락
	 *
	 * @param bunnyNotiHelperSeq : 수락할 번역 공고로의 신청 seq
	 * @return 로직 성공 여부
	 */
	public Boolean acceptBunnyNotiHelper(Long bunnyNotiHelperSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 번역 공고 신청 거절
	 *
	 * @param bunnyNotiHelperSeq : 거절된 번역 공고로의 신청 seq
	 * @return 로직 성공 여부
	 */
	public Boolean rejectBunnyNotiHelper(Long bunnyNotiHelperSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 번역 결과 생성
	 *
	 * @param bunnyResultRequestDto : 번역 결과 생성 정보
	 * @return : 생성된 결과 seq
	 */
	public Long createBunnyResult(BunnyResultRequestDto bunnyResultRequestDto) {
		// TODO : 로직

		BunnyResultEntity bunnyResult = bunnyResultRequestDto.toEntity();
		return bunnyResult.getSeq();
	}


	/**
	 * 번역 결과 조회
	 *
	 * @param bunnyResultSeq : 조회할 결과 seq
	 * @return : 조회된 결과 Entity
	 */
	public BunnyResultEntity getBunnyResult(Long bunnyResultSeq) {
		// TODO : 로직

		return (BunnyResultEntity) new Object();
	}

}
