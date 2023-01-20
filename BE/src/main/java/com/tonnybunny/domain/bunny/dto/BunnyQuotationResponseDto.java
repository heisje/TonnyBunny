package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BunnyQuotationResponseDto {

	public static BunnyQuotationResponseDto fromEntity(BunnyQuotationEntity bunnyQuotation) {
		return new BunnyQuotationResponseDto();
	}


	public static List<BunnyQuotationResponseDto> fromEntityList(List<BunnyQuotationEntity> bunnyQuotationList) {
		List<BunnyQuotationResponseDto> result = new ArrayList<>();
		for (BunnyQuotationEntity bunnyQuotation : bunnyQuotationList) {
			BunnyQuotationResponseDto bunnyQuotationResponseDto = BunnyQuotationResponseDto.fromEntity(bunnyQuotation);
			result.add(bunnyQuotationResponseDto);
		}
		return result;
	}

}
