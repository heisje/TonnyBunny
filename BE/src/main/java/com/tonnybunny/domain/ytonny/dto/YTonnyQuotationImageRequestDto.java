package com.tonnybunny.domain.ytonny.dto;


import lombok.Data;


/**
 * yTonnyQuotationSeq  : 예약 통역 견적서 seq
 * imagePath           : 이미지 경로
 */
@Data
public class YTonnyQuotationImageRequestDto {

	private Long yTonnyQuotationSeq;
	
	private String imagePath;

}
