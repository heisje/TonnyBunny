package com.tonnybunny.domain.schedule.dto;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ScheduleRequestDto {

	// FIXME: 현재 날짜 기준 으로 디폴트값 변경
	private String year;
	private String month;
	private String day;

	private Long userSeq;

	private String title;
	private String content;

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private String taskCode;

	private Boolean isComplete;

}
