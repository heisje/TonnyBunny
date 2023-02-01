package com.tonnybunny.domain.alert.dto;


import lombok.Data;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Data
public class AlertLogRequestDto {

	@Autowired
	private ModelMapper modelMapper;

	@NonNull
	private int size = 10; // limit
	private int page = 0; // offset

	private Long userSeq;

	private String taskCode;
	private String content;

}
