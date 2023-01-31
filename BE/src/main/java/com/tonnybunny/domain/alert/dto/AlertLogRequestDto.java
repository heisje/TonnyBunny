package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Data
public class AlertLogRequestDto {

	@Autowired
	private ModelMapper modelMapper;

	private int page = 0; // offset
	private int size = 10; // limit

	private Long userSeq;
	private String taskCode;
	private String content;


	public AlertLogEntity toEntity(AlertLogRequestDto alertLogRequestDto) {
		return modelMapper.map(alertLogRequestDto, AlertLogEntity.class);
	}

}
