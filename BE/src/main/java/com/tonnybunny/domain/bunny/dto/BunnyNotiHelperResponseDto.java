package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiHelperEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BunnyNotiHelperResponseDto {

	public static BunnyNotiHelperResponseDto fromEntity(BunnyNotiHelperEntity bunnyNotiHelperEntity) {
		return new BunnyNotiHelperResponseDto();
	}


	public static List<BunnyNotiHelperResponseDto> fromEntityList(List<BunnyNotiHelperEntity> bunnyNotiHelperList) {
		List<BunnyNotiHelperResponseDto> result = new ArrayList<>();

		for (BunnyNotiHelperEntity bunnyNotiHelper : bunnyNotiHelperList) {
			BunnyNotiHelperResponseDto bunnyNotiHelperResponseDto = fromEntity(bunnyNotiHelper);
			result.add(bunnyNotiHelperResponseDto);
		}
		return result;
	}

}
