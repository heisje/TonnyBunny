package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyApplyEntity;
import lombok.Data;


/**
 * userSeq          : 신청자 seq
 * bunnyNoti        : 번역 공고 seq
 * estimatePrice    : 견적 금액
 */
@Data
public class BunnyApplyRequestDto {

	private Long userSeq;
	private Long bunnySeq;
	private Integer estimatePrice;


	public BunnyApplyEntity toEntity() {
		return (BunnyApplyEntity) new Object();
	}

}
