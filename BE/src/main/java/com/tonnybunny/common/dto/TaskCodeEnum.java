package com.tonnybunny.common.dto;


import lombok.ToString;

import java.util.Arrays;


@ToString
public enum TaskCodeEnum {
	즉시통역("0030001"),
	예약통역("0030002"),
	번역("0030003");

	private final String taskCode;


	TaskCodeEnum(String taskCode) {
		this.taskCode = taskCode;
	}


	public static TaskCodeEnum valueOfCode(String taskCode) {
		return Arrays.stream(values())
		             .filter(value -> value.getTaskCode().equals(taskCode))
		             .findAny()
		             .orElse(null);
	}


	public String getTaskCode() {
		return taskCode;
	}
}
