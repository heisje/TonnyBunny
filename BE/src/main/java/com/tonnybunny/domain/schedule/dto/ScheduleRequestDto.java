package com.tonnybunny.domain.schedule.dto;


import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import lombok.Data;


@Data
public class ScheduleRequestDto {

	public ScheduleEntity toEntity(ScheduleResponseDto responseDto){
		return (ScheduleEntity) new Object();
	}
}
