package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum LangCodeEnum {
	한국어(""), 영어(""), 일본어("");

	private final String langCode;


	LangCodeEnum(String langCode) {
		this.langCode = langCode;
	}
}
