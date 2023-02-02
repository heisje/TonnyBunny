package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum TaskCodeEnum {
	즉시통역(""), 예약통역(""), 번역의뢰("");

	private final String taskCode;


	TaskCodeEnum(String taskCode) {
		this.taskCode = taskCode;
	}
}
