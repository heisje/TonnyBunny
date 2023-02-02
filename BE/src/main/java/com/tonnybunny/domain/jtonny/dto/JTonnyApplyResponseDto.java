package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyApplyEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class JTonnyApplyResponseDto {

	public static JTonnyApplyResponseDto fromEntity(JTonnyApplyEntity jTonnyHelper) {
		return new JTonnyApplyResponseDto();
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
