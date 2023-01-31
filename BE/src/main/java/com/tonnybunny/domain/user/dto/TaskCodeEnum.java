package com.tonnybunny.domain.user.dto;


/**
 * 공통 코드 용으로 만든 임시 enum
 */
public enum TaskCodeEnum {
	즉시통역("0030001"), 예약통역("0030002"), 번역("0030003");
	private final String taskCode;


	TaskCodeEnum(String taskCode) {
		this.taskCode = taskCode;
	}


	public String getTaskCode() {
		return this.taskCode;
	}

}
