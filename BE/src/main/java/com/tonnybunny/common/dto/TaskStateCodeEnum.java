package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum TaskStateCodeEnum {
	모집중(""), 진행중(""), 완료됨("");

	private final String taskStateCode;


	TaskStateCodeEnum(String taskStateCode) {
		this.taskStateCode = taskStateCode;
	}
}