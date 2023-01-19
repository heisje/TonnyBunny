package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyNotiEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class YTonnyNotiResponseDto {

	public static YTonnyNotiResponseDto fromEntity(YTonnyNotiEntity xx) {
		return new YTonnyNotiResponseDto();
	}


	public static List<YTonnyNotiResponseDto> fromEnityList(
		List<YTonnyNotiEntity> yTonnyNotiList) {
		List<YTonnyNotiResponseDto> result = new ArrayList<>();
		for (YTonnyNotiEntity yTonnyNoti : yTonnyNotiList) {
			YTonnyNotiResponseDto yTonnyNotiResponseDto = YTonnyNotiResponseDto.fromEntity(
				yTonnyNoti);
			result.add(yTonnyNotiResponseDto);
		}
		return result;
	}

}
