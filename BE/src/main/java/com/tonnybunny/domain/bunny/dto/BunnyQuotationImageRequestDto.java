package com.tonnybunny.domain.bunny.dto;


import lombok.Data;


/**
 * bunnyQuatationSeq   : 번역 견적서 seq
 * imagePath           : 이미지 경로
 */
@Data
public class BunnyQuotationImageRequestDto {

	private Long bunnyQuatationSeq;
	private String imagePath;


	public BunnyQuotationImageRequestDto toEntity() {
		return (BunnyQuotationImageRequestDto) new Object();
	}

}
