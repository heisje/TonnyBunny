package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiImageEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : seq
 * bunnyQuatation       : 번역 견적서
 * imagePath            : 이미지 경로
 */
@Data
public class BunnyQuotationImageResponseDto {

	private Long seq;
	private Long bunnyQuatationSeq;
	private String imagePath;


	public static BunnyQuotationImageResponseDto fromEntity(BunnyNotiImageEntity bunnyNotiImage) {
		return new BunnyQuotationImageResponseDto();
	}


	public static List<BunnyQuotationImageResponseDto> fromEntityList(List<BunnyNotiImageEntity> bunnyNotiImageList) {
		List<BunnyQuotationImageResponseDto> result = new ArrayList<>();

		for (BunnyNotiImageEntity bunnyNotiImage : bunnyNotiImageList) {
			BunnyQuotationImageResponseDto bunnyQuotationImageResponseDto = fromEntity(bunnyNotiImage);
			result.add(bunnyQuotationImageResponseDto);
		}

		return result;

	}

}
