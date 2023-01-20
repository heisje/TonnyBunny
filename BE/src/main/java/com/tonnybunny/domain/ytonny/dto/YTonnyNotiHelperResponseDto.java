package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyNotiHelperEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class YTonnyNotiHelperResponseDto {

	public static YTonnyNotiHelperResponseDto fromEntity(YTonnyNotiHelperEntity yTonnyNotiHelper) {
		return new YTonnyNotiHelperResponseDto();
	}


	public static List<YTonnyNotiHelperResponseDto> fromEntityList(
		List<YTonnyNotiHelperEntity> yTonnyNotiHelperList) {
		List<YTonnyNotiHelperResponseDto> result = new ArrayList<>();
		for (YTonnyNotiHelperEntity yTonnyNotiHelper : yTonnyNotiHelperList) {
			YTonnyNotiHelperResponseDto yTonnyNotiHelperResponseDto = YTonnyNotiHelperResponseDto.fromEntity(
				yTonnyNotiHelper);
			result.add(yTonnyNotiHelperResponseDto);
		}
		return result;
	}

}
