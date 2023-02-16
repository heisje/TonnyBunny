package com.tonnybunny.domain.alert.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertLogResponseDto {

	private Long alertLogSeq;

	private String content;
	private Boolean isRead;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime createdAt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime updatedAt;


	public static AlertLogResponseDto fromEntity(AlertLogEntity alertLog) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();
		return modelMapper.map(alertLog, AlertLogResponseDto.class);
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
