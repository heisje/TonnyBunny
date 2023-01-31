package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class AlertLogResponseDto {

	@Autowired
	private static final ModelMapper modelMapper = new ModelMapper();

	private String taskCode;
	private String content;
	private boolean isRead;


	public static AlertLogResponseDto fromEntity(AlertLogEntity alertLog) {
		return modelMapper.map(alertLog, AlertLogResponseDto.class);
	}


	public static List<AlertLogResponseDto> fromEntityList(List<AlertLogEntity> alertLogList) {
		List<AlertLogResponseDto> result = alertLogList.stream().map(alertLogEntity -> modelMapper.map(alertLogEntity, AlertLogResponseDto.class)).collect(Collectors.toList());
		return result;
	}

}
