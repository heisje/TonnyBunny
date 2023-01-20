package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;


@Data
public class AlertLogRequestDto {

	public AlertLogEntity toEntity(AlertLogRequestDto alertLogRequestDto) {
		return (AlertLogEntity) new Object();
	}

}
