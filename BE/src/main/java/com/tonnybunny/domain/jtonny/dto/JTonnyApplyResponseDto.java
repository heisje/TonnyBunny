package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyApplyEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class JTonnyApplyResponseDto {

	private Long seq;


	public static JTonnyApplyResponseDto fromEntity(JTonnyApplyEntity jTonnyHelper) {
		return JTonnyApplyResponseDto.builder()
		                             .seq(jTonnyHelper.getSeq())
		                             .build();
	}


	public static List<JTonnyApplyResponseDto> fromEntityList(List<JTonnyApplyEntity> jTonnyHelperList) {
		List<JTonnyApplyResponseDto> result = new ArrayList<>();
		for (JTonnyApplyEntity jTonnyHelper : jTonnyHelperList) {
			JTonnyApplyResponseDto jTonnyApplyResponseDto = JTonnyApplyResponseDto.fromEntity(jTonnyHelper);
			result.add(jTonnyApplyResponseDto);
		}
		return result;
	}

}
