package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiHelperEntity;
import lombok.Data;


/**
 * userSeq          : 신청자 seq
 * bunnyNoti        : 번역 공고 seq
 * estimatePrice    : 견적 금액
 */
@Data
public class BunnyNotiHelperRequestDto {

	private Long userSeq;
	private Long bunnyNotiSeq;
	private Integer estimatePrice;


	public BunnyNotiHelperEntity toEntity() {
		return (BunnyNotiHelperEntity) new Object();
	}

}
