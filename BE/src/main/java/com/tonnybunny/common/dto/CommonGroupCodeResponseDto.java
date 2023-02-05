package com.tonnybunny.common.dto;


import com.tonnybunny.common.entity.CommonGroupCodeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Builder
public class CommonGroupCodeResponseDto {

	public static Map<String, List<CommonCodeResponseDto>> fromEntityList(List<CommonGroupCodeEntity> commonGroupCodeList) {
		Map<String, List<CommonCodeResponseDto>> result = new HashMap<>();

		for (CommonGroupCodeEntity commonGroupCode : commonGroupCodeList) {
			result.put(commonGroupCode.getGroupCodeName(), CommonCodeResponseDto.fromEntityList(commonGroupCode.getCodeList()));
		}

		return result;
	}

}
