package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyResultEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BunnyResultResponseDto {

	public static BunnyResultResponseDto fromEntity(BunnyResultEntity bunnyResult) {
		return new BunnyResultResponseDto();
	}


	public static List<BunnyResultResponseDto> fromEntityList(List<BunnyResultEntity> bunnyResultList) {
		List<BunnyResultResponseDto> result = new ArrayList<>();
		for (BunnyResultEntity bunnyResult : bunnyResultList) {
			BunnyResultResponseDto bunnyResultResponseDto = BunnyResultResponseDto.fromEntity(bunnyResult);
			result.add(bunnyResultResponseDto);
		}
		return result;
	}

}
