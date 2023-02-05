package com.tonnybunny.common.dto;


import com.tonnybunny.common.entity.CommonGroupCodeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class CommonGroupCodeResponseDto {

	private String groupCode;
	private String groupCodeName;
	private List<CommonCodeResponseDto> commonCodeList;


	public static CommonGroupCodeResponseDto fromEntity(CommonGroupCodeEntity commonGroupCode) {
		return CommonGroupCodeResponseDto.builder()
		                                 .groupCode(commonGroupCode.getGroupCode())
		                                 .groupCodeName(commonGroupCode.getGroupCodeName())
		                                 .commonCodeList(CommonCodeResponseDto.fromEntityList(commonGroupCode.getCodeList()))
		                                 .build();
	}


	public static List<CommonGroupCodeResponseDto> fromEntityList(List<CommonGroupCodeEntity> commonGroupCodeList) {
		ArrayList<CommonGroupCodeResponseDto> result = new ArrayList<>();

		for (CommonGroupCodeEntity commonGroupCode : commonGroupCodeList) {
			result.add(CommonGroupCodeResponseDto.fromEntity(commonGroupCode));
		}

		return result;
	}

}
