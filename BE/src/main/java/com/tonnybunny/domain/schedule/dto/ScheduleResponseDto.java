package com.tonnybunny.domain.schedule.dto;


import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ScheduleResponseDto {

	public static ScheduleResponseDto fromEntity(ScheduleEntity schedule){
		/* entity -> dto */
		return new ScheduleResponseDto();
	}

	public static List<ScheduleResponseDto> fromEntityList(List<ScheduleEntity> scheduleList){
		List<ScheduleResponseDto> result = new ArrayList<>();
		for(ScheduleEntity schedule : scheduleList){
			ScheduleResponseDto scheduleResponseDto = fromEntity(schedule);
			result.add( scheduleResponseDto );
		}
		return result;
	}

}
