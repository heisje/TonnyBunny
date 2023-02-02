package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum TaskStateCodeEnum {
	모집중("0090001"),
	진행중("0090002"),
	완료됨("0090003");

	private final String taskStateCode;


	TaskStateCodeEnum(String taskStateCode) {
		this.taskStateCode = taskStateCode;
	}
}
