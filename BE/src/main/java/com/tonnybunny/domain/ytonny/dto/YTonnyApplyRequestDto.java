package com.tonnybunny.domain.ytonny.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * yTonnyNotiSeq   : 예약 통역 공고 seq
 * helperSeq       : 헬퍼 유저
 * unitPrice      : 견적 금액
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YTonnyApplyRequestDto {

	@Builder.Default
	private int size = 4; // limit
	@Builder.Default
	private int page = 0; // offset

	private Long yTonnyApplySeq;
	private Long yTonnySeq;
	private Long helperSeq; // 헬퍼의 userSeq

	private Integer unitPrice;

}
