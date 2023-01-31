package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;


@Data
public class AlertLogRequestDto {

	private int page; // offset
	private int size; // limit

	private Long userSeq;

	private String taskCode;
	private String content;


	public AlertLogEntity toEntity(AlertLogRequestDto alertLogRequestDto) {
		return (AlertLogEntity) new Object();
	}

}
