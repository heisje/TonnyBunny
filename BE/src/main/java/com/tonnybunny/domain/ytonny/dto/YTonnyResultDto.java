package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.domain.ytonny.entity.YTonnyResultEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class YTonnyResultDto {

	public static YTonnyResultDto fromEntity(YTonnyResultEntity yTonnyResult) {
		return new YTonnyResultDto();
	}


	public static List<YTonnyResultDto> fromEntityList(
		List<YTonnyResultEntity> yTonnyResultList) {
		List<YTonnyResultDto> result = new ArrayList<>();
		for (YTonnyResultEntity yTonnyResult : yTonnyResultList) {
			YTonnyResultDto yTonnyResultDto = YTonnyResultDto.fromEntity(yTonnyResult);
			result.add(yTonnyResultDto);
		}
		return result;
	}

}
