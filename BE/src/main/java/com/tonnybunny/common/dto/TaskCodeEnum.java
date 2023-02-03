package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum TaskCodeEnum {
	즉시통역("0030001"),
	예약통역("0030002"),
	번역("0030003");

	private final String taskCode;


	TaskCodeEnum(String taskCode) {
		this.taskCode = taskCode;
	}


	public String getTaskCode() {
		return taskCode;
	}
}
