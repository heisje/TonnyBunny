package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class JTonnyResponseDto {

	public static JTonnyResponseDto fromEntity(JTonnyEntity jTonny) {
		return new JTonnyResponseDto();
	}


	public static List<JTonnyResponseDto> fromEntityList(List<JTonnyEntity> jTonnyList) {
		List<JTonnyResponseDto> result = new ArrayList<>();
		for (JTonnyEntity jTonny : jTonnyList) {
			JTonnyResponseDto jTonnyResponseDto = JTonnyResponseDto.fromEntity(jTonny);
			result.add(jTonnyResponseDto);
		}
		return result;
	}

}
