package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyNotiHelperEntity;
import lombok.Data;


/**
 * yTonnyNotiSeq   : 예약 통역 공고 seq
 * helperSeq       : 헬퍼 유저
 * totalPrice      : 견적 금액
 */
@Data
public class YTonnyNotiHelperRequestDto {

	private Long yTonnyNotiSeq;
	private Long helperSeq;
	private Integer totalPrice;


	public YTonnyNotiHelperEntity toEntity() {
		return (YTonnyNotiHelperEntity) new Object();
	}

}
