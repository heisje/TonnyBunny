package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyResultEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class JTonnyResultResponseDto {

	public static JTonnyResultResponseDto fromEntity(JTonnyResultEntity jTonnyResult) {
		return new JTonnyResultResponseDto();
	}


	public static List<JTonnyResultResponseDto> fromEntityList(List<JTonnyResultEntity> jTonnyResultList) {
		List<JTonnyResultResponseDto> result = new ArrayList<>();
		for (JTonnyResultEntity jTonnyResult : jTonnyResultList) {
			JTonnyResultResponseDto jTonnyResultResponseDto = JTonnyResultResponseDto.fromEntity(jTonnyResult);
			result.add(jTonnyResultResponseDto);
		}
		return result;
	}

}
