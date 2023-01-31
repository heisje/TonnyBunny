package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class AlertLogResponseDto {

	private String taskCode;
	private String content;
	private boolean isRead;


	public static AlertLogResponseDto fromEntity(AlertLogEntity alertLog) {
		return new AlertLogResponseDto();
	}


	public static List<AlertLogResponseDto> fromEntityList(List<AlertLogEntity> alertLogList) {
		List<AlertLogResponseDto> result = new ArrayList<>();

		for (AlertLogEntity alertLog : alertLogList) {
			AlertLogResponseDto alertLogResponseDto = fromEntity(alertLog);
			result.add(alertLogResponseDto);
		}

		return result;
	}

}
