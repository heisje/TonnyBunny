package com.tonnybunny.domain.bunny.service;


import com.tonnybunny.domain.bunny.dto.BunnyNotiRequestDto;
import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BunnyService {

	// --------------------------------------- 번역 공고 ---------------------------------------


	/**
	 * @param bunnyNotiRequestDto : 번역 공고 생성 폼
	 * @return : 생성된 번역 공고의 seq
	 */
	public Long createYBunnyNoti(BunnyNotiRequestDto bunnyNotiRequestDto) {
		// TODO : 로직
		BunnyNotiEntity bunnyNoti = bunnyNotiRequestDto.toEntity();
		return bunnyNoti.getSeq();
	}


	/**
	 * @param notiSeq : 삭제할 번역 공고의 seq
	 * @return : 삭제 성공 여부
	 */
	public Boolean deleteYBunnyNoti(Long notiSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * @param notiSeq : 조회할 번역 공고의 seq
	 * @return : 조호된 공고의 Entity
	 */
	public BunnyNotiEntity getYBunnyNoti(Long notiSeq) {
		// TODO : 로직
		return (BunnyNotiEntity) new Object();
	}


	/**
	 * @param lang     : 언어코드
	 * @param category : 카테고리코드
	 * @return : 조회된 번역 공고 EntityList
	 */
	public List<BunnyNotiEntity> getYBunnyListByFilter(String lang, String category) {
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

}
