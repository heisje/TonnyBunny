package com.tonnybunny.domain.alert.dto;


import lombok.Data;


@Data
public class AlertLogRequestDto {

	private int size = 10; // limit
	private int page = 0; // offset

	private Long alertLogSeq;
	private Long userSeq;

	//	private String sessionName;
	private String taskCode;
	private String content;
	private Boolean isRead;
	private Boolean isEnd;

}
