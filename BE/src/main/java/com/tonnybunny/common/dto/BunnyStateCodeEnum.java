package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum BunnyStateCodeEnum {
	시작전("0100001"), 매칭완료("0100002"), 완료("0100003");

	private final String BunnyStateCode;


	BunnyStateCodeEnum(String bunnyStateCode) {
		this.BunnyStateCode = bunnyStateCode;
	}


	public String getBunnyStateCode() {
		return this.BunnyStateCode;
	}
}
