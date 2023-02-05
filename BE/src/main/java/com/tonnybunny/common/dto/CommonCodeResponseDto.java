package com.tonnybunny.common.dto;


import com.tonnybunny.common.entity.CommonCodeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class CommonCodeResponseDto {

	private String code;
	private String codeName;


	public static CommonCodeResponseDto fromEntity(CommonCodeEntity commonCode) {
		return CommonCodeResponseDto.builder()
		                            .code(commonCode.getCode())
		                            .codeName(commonCode.getCodeName())
		                            .build();
	}


	public static List<CommonCodeResponseDto> fromEntityList(List<CommonCodeEntity> commonCodeList) {
		ArrayList<CommonCodeResponseDto> result = new ArrayList<>();

		for (CommonCodeEntity commonCode : commonCodeList) {
			result.add(CommonCodeResponseDto.fromEntity(commonCode));
		}

		return result;
	}

}
