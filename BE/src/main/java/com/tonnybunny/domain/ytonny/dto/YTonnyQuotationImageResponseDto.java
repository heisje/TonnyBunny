package com.tonnybunny.domain.ytonny.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 * seq                  : 예약 통역 견적서 이미지 seq
 * yTonnyQuotation      : 예약 통역 견적서 seq
 * imagePath            : 이미지 경로
 */
@Data
@AllArgsConstructor
@Builder
public class YTonnyQuotationImageResponseDto {

	private Long seq;
	
	private YTonnyQuotationResponseDto yTonnyQuotation;
	private String imagePath;

}
