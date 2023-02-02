package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class JTonnyResponseDto {

	private Long seq;


	public static JTonnyResponseDto fromEntity(JTonnyEntity jTonny) {
		return JTonnyResponseDto.builder()
		                        .seq(jTonny.getSeq())
		                        .build();
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
