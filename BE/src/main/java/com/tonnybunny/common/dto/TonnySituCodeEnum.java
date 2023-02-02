package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum TonnySituCodeEnum {
	기타("0040000"),
	일상("0040001"),
	경찰("0040002");

	private final String tonnySituCode;


	TonnySituCodeEnum(String tonnySituCode) {
		this.tonnySituCode = tonnySituCode;
	}
}
