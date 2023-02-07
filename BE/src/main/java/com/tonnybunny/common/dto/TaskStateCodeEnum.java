package com.tonnybunny.common.dto;


import lombok.ToString;

import java.util.Arrays;


@ToString
public enum TaskStateCodeEnum {
	모집중("0090001"),
	진행중("0090002"),
	완료됨("0090003");

	private final String taskStateCode;


	TaskStateCodeEnum(String taskStateCode) {
		this.taskStateCode = taskStateCode;
	}


	public static TaskStateCodeEnum valueOfCode(String taskStateCode) {
		return Arrays.stream(values())
		             .filter(value -> value.getTaskStateCode().equals(taskStateCode))
		             .findAny()
		             .orElse(null);
	}


	public String getTaskStateCode() {
		return taskStateCode;
	}
}
