package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.bunny.entity.BunnyQuotationImageEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

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
	private Long bunnyQuotationSeq;
	private String imagePath;


	public static BunnyQuotationImageResponseDto fromEntity(BunnyQuotationImageEntity bunnyQuotationImage) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();

		BunnyQuotationImageResponseDto bunnyQuotationImageResponseDto = modelMapper.map(bunnyQuotationImage, BunnyQuotationImageResponseDto.class);

		return bunnyQuotationImageResponseDto;
	}


	public static List<BunnyQuotationImageResponseDto> fromEntityList(List<BunnyQuotationImageEntity> bunnyQuotationImageList) {
		List<BunnyQuotationImageResponseDto> result = new ArrayList<>();

		for (BunnyQuotationImageEntity bunnyQuotationImage : bunnyQuotationImageList) {
			BunnyQuotationImageResponseDto bunnyQuotationImageResponseDto = fromEntity(bunnyQuotationImage);
			result.add(bunnyQuotationImageResponseDto);
		}

		return result;

	}

}
