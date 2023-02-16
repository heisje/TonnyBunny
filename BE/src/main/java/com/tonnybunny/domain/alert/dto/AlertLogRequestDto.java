package com.tonnybunny.domain.alert.dto;


import lombok.Data;


@Data
public class AlertLogRequestDto {

	private int size = 10; // limit
	private int page = 0; // offset

	private Long alertLogSeq;
	private Long userSeq;

	private Long clientSeq;
	private Long helperSeq;

	private Long yTonnySeq;
	private Long bunnySeq;

	private String clientNickname;
	private String helperNickname;

	private String taskCode;
	private String title;
	private String content;
	private Boolean isRead;

}
