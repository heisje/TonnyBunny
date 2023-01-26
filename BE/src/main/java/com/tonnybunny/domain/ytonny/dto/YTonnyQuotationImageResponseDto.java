package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyQuotationImageEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 예약 통역 견적서 이미지 seq
 * yTonnyQuotation      : 예약 통역 견적서 seq
 * imagePath            : 이미지 경로
 */
@Data
public class YTonnyQuotationImageResponseDto {

	private Long seq;
	private YTonnyQuotationResponseDto yTonnyQuotation;
	private String imagePath;


	public static YTonnyQuotationImageResponseDto fromEntity(YTonnyQuotationImageEntity yTonnyQuotationImage) {
		return new YTonnyQuotationImageResponseDto();
	}


	public static List<YTonnyQuotationImageResponseDto> fromEntityList(List<YTonnyQuotationImageEntity> yTonnyQuotationImageList) {
		List<YTonnyQuotationImageResponseDto> result = new ArrayList<>();
		for (YTonnyQuotationImageEntity yTonnyQuotationImage : yTonnyQuotationImageList) {
			YTonnyQuotationImageResponseDto yTonnyQuotationImageResponseDto = YTonnyQuotationImageResponseDto.fromEntity(yTonnyQuotationImage);
			result.add(yTonnyQuotationImageResponseDto);
		}
		return result;
	}

}
