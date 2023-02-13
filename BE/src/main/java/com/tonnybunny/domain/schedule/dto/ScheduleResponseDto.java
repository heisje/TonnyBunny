package com.tonnybunny.domain.schedule.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class ScheduleResponseDto {

	private String title;
	private String content;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime startDateTime;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime endDateTime;

	private String taskCode;

	private Boolean isComplete;


	public static ScheduleResponseDto fromEntity(ScheduleEntity scheduleEntity) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();
		return modelMapper.map(scheduleEntity, ScheduleResponseDto.class);
	}


	public static List<ScheduleResponseDto> fromEntityList(List<ScheduleEntity> scheduleEntityList) {
		return scheduleEntityList.stream().map(m -> fromEntity(m)).collect(Collectors.toList());
	}

}
