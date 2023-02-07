package com.tonnybunny.common.dto;


import lombok.ToString;

import java.util.Arrays;


@ToString
public enum TonnySituCodeEnum {
	기타("0040000"),
	일상("0040001"),
	경찰("0040002");

	private final String tonnySituCode;


	TonnySituCodeEnum(String tonnySituCode) {
		this.tonnySituCode = tonnySituCode;
	}


	public static TonnySituCodeEnum valueOfCode(String tonnySituCode) {
		return Arrays.stream(values())
		             .filter(value -> value.getTonnySituCode().equals(tonnySituCode))
		             .findAny()
		             .orElse(null);
	}


	public String getTonnySituCode() {
		return tonnySituCode;
	}
}
