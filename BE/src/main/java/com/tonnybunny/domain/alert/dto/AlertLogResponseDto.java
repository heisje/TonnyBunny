package com.tonnybunny.domain.alert.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.user.dto.UserResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class AlertLogResponseDto {

	private Long alertLogSeq;

	private UserResponseDto user;

	private String taskCode;
	private String content;
	private Boolean isRead;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime createdAt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime updatedAt;


	public static AlertLogResponseDto fromEntity(AlertLogEntity alertLogEntity) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();
		return modelMapper.map(alertLogEntity, AlertLogResponseDto.class);
	}


	public static List<AlertLogResponseDto> fromEntityList(List<AlertLogEntity> alertLogEntityList) {
		return alertLogEntityList.stream().map(m -> fromEntity(m)).collect(Collectors.toList());
	}

}
