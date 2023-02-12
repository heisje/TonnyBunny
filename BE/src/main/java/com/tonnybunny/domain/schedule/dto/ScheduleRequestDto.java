package com.tonnybunny.domain.schedule.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
public class ScheduleRequestDto {

	private Long userSeq;

	private String startYear;
	private String startMonth;
	private String startDay;

	private String endYear;
	private String endMonth;
	private String endDay;

	private String title;
	private String content;

	@DateTimeFormat(pattern = "'yyyy-MM-dd'T'HH:mm'")
	private LocalDateTime startDateTime;
	@DateTimeFormat(pattern = "'yyyy-MM-dd'T'HH:mm'")
	private LocalDateTime endDateTime;

	private String taskCode;

	private Boolean isComplete;

}
