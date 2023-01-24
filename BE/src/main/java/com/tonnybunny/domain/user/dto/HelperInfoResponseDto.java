package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class HelperInfoResponseDto {

	public static HelperInfoResponseDto fromEntity(HelperInfoEntity helperInfo) {
		return new HelperInfoResponseDto();
	}


	public static List<HelperInfoResponseDto> fromEntityList(List<HelperInfoEntity> helperInfoList) {
		List<HelperInfoResponseDto> result = new ArrayList<>();
		for (HelperInfoEntity helperInfo : helperInfoList) {
			HelperInfoResponseDto helperInfoResponseDto = fromEntity(helperInfo);
			result.add(helperInfoResponseDto);
		}
		return result;
	}

}
