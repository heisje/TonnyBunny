package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BunnyNotiResponseDto {

	public static BunnyNotiResponseDto fromEntity(BunnyNotiEntity bunnyNotiSeq) {
		return new BunnyNotiResponseDto();
	}


	public static List<BunnyNotiResponseDto> fromEntityList(List<BunnyNotiEntity> bunnyNotiList) {
		List<BunnyNotiResponseDto> result = new ArrayList<>();

		for (BunnyNotiEntity bunnyNoti : bunnyNotiList) {
			BunnyNotiResponseDto bunnyNotiResponseDto = fromEntity(bunnyNoti);
			result.add(bunnyNotiResponseDto);
		}

		return result;

	}

}
