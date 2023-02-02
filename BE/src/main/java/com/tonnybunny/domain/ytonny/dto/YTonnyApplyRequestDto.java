package com.tonnybunny.domain.ytonny.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 * yTonnyNotiSeq   : 예약 통역 공고 seq
 * helperSeq       : 헬퍼 유저
 * totalPrice      : 견적 금액
 */
@Data
@AllArgsConstructor
@Builder
public class YTonnyApplyRequestDto {

	private Long yTonnySeq;
	private Long helperSeq; // 헬퍼의 userSeq

	private Integer totalPrice;

}
