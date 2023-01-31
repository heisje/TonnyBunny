package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class AlertLogRequestDto {

	private final ModelMapper modelMapper;
	
	private int page; // offset
	private int size; // limit
	private Long userSeq;
	private String taskCode;
	private String content;


	public AlertLogEntity toEntity(AlertLogRequestDto alertLogRequestDto) {

		return modelMapper.map(alertLogRequestDto, AlertLogEntity.class);
	}

}
