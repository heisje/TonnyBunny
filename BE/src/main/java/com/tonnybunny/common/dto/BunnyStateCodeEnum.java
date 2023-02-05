package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum BunnyStateCodeEnum {
	모집중("0100001"), 진행중("0100002"), 완료됨("0100003");

	private final String BunnyStateCode;


	BunnyStateCodeEnum(String bunnyStateCode) {
		this.BunnyStateCode = bunnyStateCode;
	}


	public String getBunnyStateCode() {
		return this.BunnyStateCode;
	}
}
