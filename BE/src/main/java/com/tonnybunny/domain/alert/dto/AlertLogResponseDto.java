package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@RequiredArgsConstructor
public class AlertLogResponseDto {

	private Long alertLogSeq;

	private String taskCode;
	private String content;
	private Boolean isRead;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	public static AlertLogResponseDto fromEntity(AlertLogEntity alertLog) {
		ModelMapper mapper = ModelMapperFactory.getMapper();
		return mapper.map(alertLog, AlertLogResponseDto.class);
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
