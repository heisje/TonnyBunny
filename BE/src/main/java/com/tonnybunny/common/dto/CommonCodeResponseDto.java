package com.tonnybunny.common.dto;


import com.tonnybunny.common.entity.CommonCodeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class CommonCodeResponseDto {

	private String value;
	private String name;


	public static CommonCodeResponseDto fromEntity(CommonCodeEntity commonCode) {
		return CommonCodeResponseDto.builder()
		                            .value(commonCode.getGroupCode().getGroupCode() + commonCode.getCode())
		                            .name(commonCode.getCodeName())
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
