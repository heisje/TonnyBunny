package com.tonnybunny.domain.bunny.dto;


import lombok.Data;


/**
 * userSeq          : 신청자 seq
 * bunnyNoti        : 번역 공고 seq
 * estimatePrice    : 견적 금액
 */
@Data
public class BunnyApplyRequestDto {

	private Long bunnyApplySeq;
	private Long userSeq;
	private Long bunnySeq;
	private Integer estimatePrice;
	
}
