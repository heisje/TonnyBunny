package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyNotiHelperEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class JTonnyNotiHelperResponseDto {
	
	public static JTonnyNotiHelperResponseDto fromEntity(JTonnyNotiHelperEntity jTonnyNotiHelper) {
		return new JTonnyNotiHelperResponseDto();
	}


	public static List<JTonnyNotiHelperResponseDto> fromEntityList(List<JTonnyNotiHelperEntity> jTonnyNotiHelperList) {
		List<JTonnyNotiHelperResponseDto> result = new ArrayList<>();
		for (JTonnyNotiHelperEntity jTonnyNotiHelper : jTonnyNotiHelperList) {
			JTonnyNotiHelperResponseDto jTonnyNotiHelperResponseDto = JTonnyNotiHelperResponseDto.fromEntity(jTonnyNotiHelper);
			result.add(jTonnyNotiHelperResponseDto);
		}
		return result;
	}

}
