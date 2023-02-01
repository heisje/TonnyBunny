package com.tonnybunny.domain.alert.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class AlertLogRequestDto {

	private int page = 0; // offset
	private int size = 10; // limit
	private Long userSeq;
	private String taskCode;
	private String content;


	public AlertLogEntity toEntity(AlertLogRequestDto alertLogRequestDto) {
		ModelMapper mapper = ModelMapperFactory.getMapper();
		return mapper.map(alertLogRequestDto, AlertLogEntity.class);
	}

}
