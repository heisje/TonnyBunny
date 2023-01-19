package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyNotiHelperEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class YTonnyHelperResponseDto {

	public static YTonnyHelperResponseDto fromEntity(YTonnyNotiHelperEntity xx) {
		return new YTonnyHelperResponseDto();
	}


	public static List<YTonnyHelperResponseDto> fromEntityList(
		List<YTonnyNotiHelperEntity> yTonnyNotiHelperList) {
		List<YTonnyHelperResponseDto> result = new ArrayList<>();
		for (YTonnyNotiHelperEntity yTonnyNotiHelper : yTonnyNotiHelperList) {
			YTonnyHelperResponseDto yTonnyHelperResponseDto = YTonnyHelperResponseDto.fromEntity(
				yTonnyNotiHelper);
			result.add(yTonnyHelperResponseDto);
		}
		return result;
	}

}
