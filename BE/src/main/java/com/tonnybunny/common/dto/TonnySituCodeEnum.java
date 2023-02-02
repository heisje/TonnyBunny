package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum TonnySituCodeEnum {
	긴급(""), 일상(""), 기타("");

	private final String tonnySituCode;


	TonnySituCodeEnum(String tonnySituCode) {
		this.tonnySituCode = tonnySituCode;
	}
}
