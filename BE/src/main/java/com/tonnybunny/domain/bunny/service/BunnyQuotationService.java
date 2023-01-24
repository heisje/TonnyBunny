package com.tonnybunny.domain.bunny.service;


import com.tonnybunny.domain.bunny.dto.BunnyQuotationRequestDto;
import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BunnyQuotationService {

	/**
	 * 번역 견적서를 작성
	 *
	 * @param bunnyQuotationRequestDto : 견적서에 넣는 내용 정보
	 * @return 생성한 견적서 Seq
	 */
	public Long createBunnyQuotation(BunnyQuotationRequestDto bunnyQuotationRequestDto) {
		// TODO : 구현
		return 0L;
	}


	/**
	 * FIXME : 조회 기준 Seq를 변경해야 함
	 * 1:1 상담 채팅방에 올라온 견적 상담 채팅방에 올라온 견적서들의 목록 조회
	 *
	 * @param bunnyNotiSeq : 번역 공고 게시글 Seq
	 * @return
	 */
	public List<BunnyQuotationEntity> getBunnyQuotationList(Long bunnyNotiSeq) {
		// TODO : 구현
		return new ArrayList<>();
	}


	/**
	 * 번역 견적서 상세 조회
	 *
	 * @param bunnyQuotationSeq : 조회할 견적서 Seq
	 * @return 조회한 견적서
	 */
	public BunnyQuotationEntity getBunnyQuotation(Long bunnyQuotationSeq) {
		// TODO : 구현
		return (BunnyQuotationEntity) new Object();
	}


	/**
	 * 견적서 상태 타입 수정
	 * - 미선택 : 아직 견적서 협의가 확정되지 않은 상태
	 * - 선택 : 견적서 협의가 확정된 상태. 고객이 견적서를 수락한 상태.
	 * - 완료 : 번역 업무가 완료된 상태.
	 *
	 * @param bunnyQuotationSeq       : 대상 견적서 Seq
	 * @param bunnyQuotationStateCode : 견적서 상태 타입 코드 (공통 코드)
	 */
	public void modifyBunnyQuotationType(Long bunnyQuotationSeq, String bunnyQuotationStateCode) {
		// TODO : 구현
	}

}
