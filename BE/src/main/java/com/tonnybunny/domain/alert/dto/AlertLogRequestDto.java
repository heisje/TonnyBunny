package com.tonnybunny.domain.alert.dto;


import lombok.Data;


@Data
public class AlertLogRequestDto {

	private int page = 0; // offset
	private int size = 10; // limit
	private Long userSeq;
	private String taskCode;
	private String content;

}
