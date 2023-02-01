package com.tonnybunny.domain.alert.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertLogResponseDto {

	public static ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ModelMapper modelBeanMapper;

	private Long alertLogSeq;

	private String taskCode;
	private String content;
	private Boolean isRead;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	public static AlertLogResponseDto fromEntity(AlertLogEntity alertLog) {
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


	@PostConstruct
	private void initialize() {
		modelMapper = this.modelBeanMapper;
	}

}
