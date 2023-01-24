package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyNotiEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class JTonnyNotiResponseDto {

	public static JTonnyNotiResponseDto fromEntity(JTonnyNotiEntity jTonnyNoti) {
		return new JTonnyNotiResponseDto();
	}


	public static List<JTonnyNotiResponseDto> fromEntityList(List<JTonnyNotiEntity> jTonnyNotiList) {
		List<JTonnyNotiResponseDto> result = new ArrayList<>();
		for (JTonnyNotiEntity jTonnyNoti : jTonnyNotiList) {
			JTonnyNotiResponseDto jTonnyNotiResponseDto = JTonnyNotiResponseDto.fromEntity(jTonnyNoti);
			result.add(jTonnyNotiResponseDto);
		}
		return result;
	}

}
