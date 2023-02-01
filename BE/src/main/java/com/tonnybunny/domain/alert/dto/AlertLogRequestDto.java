package com.tonnybunny.domain.alert.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // not null 만 넘겨주도록
public class AlertLogRequestDto {

	// 의존성 주입에 static 사용하기
	public static ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ModelMapper modelBeanMapper;

	private int page = 0; // offset
	private int size = 10; // limit

	private Long userSeq;
	private String taskCode;
	private String content;


	public static AlertLogEntity toEntity(AlertLogRequestDto alertLogRequestDto) {
		return modelMapper.map(alertLogRequestDto, AlertLogEntity.class);
	}


	@PostConstruct
	private void initialize() {
		modelMapper = this.modelBeanMapper;
	}

}
