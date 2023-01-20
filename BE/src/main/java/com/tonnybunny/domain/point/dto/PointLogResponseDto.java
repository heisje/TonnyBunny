package com.tonnybunny.domain.point.dto;


import com.tonnybunny.domain.point.entity.PointLogEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class PointLogResponseDto {

	public static PointLogResponseDto fromEntity(PointLogEntity pointLog) {
		return new PointLogResponseDto();
	}


	public static List<PointLogResponseDto> fromEntityList(List<PointLogEntity> pointLogList) {
		List<PointLogResponseDto> result = new ArrayList<>();
		for (PointLogEntity pointLog : pointLogList) {
			PointLogResponseDto pointLogResponseDto = fromEntity(pointLog);
			result.add(pointLogResponseDto);
		}
		return result;
	}

}
