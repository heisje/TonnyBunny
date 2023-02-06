package com.tonnybunny.domain.schedule.dto;


import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class ScheduleResponseDto {

	private String title;
	private String content;

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private String taskCode;

	private Boolean isComplete;


	public static ScheduleResponseDto fromEntity(ScheduleEntity schedule) {
		/* entity -> dto */
		return new ScheduleResponseDto();
	}


	public static List<ScheduleResponseDto> fromEntityList(List<ScheduleEntity> scheduleList) {
		List<ScheduleResponseDto> result = new ArrayList<>();
		for (ScheduleEntity schedule : scheduleList) {
			ScheduleResponseDto scheduleResponseDto = fromEntity(schedule);
			result.add(scheduleResponseDto);
		}
		return result;
	}

}
