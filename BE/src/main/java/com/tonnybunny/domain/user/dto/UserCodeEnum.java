package com.tonnybunny.domain.user.dto;


public enum UserCodeEnum {
	클라이언트("0010001"), 헬퍼("0010002"), 관리자("0010003");
	private final String userCode;


	UserCodeEnum(String userCode) {
		this.userCode = userCode;
	}


	public String getUserCode() {
		return this.userCode;
	}

}
