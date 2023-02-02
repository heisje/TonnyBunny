package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum LangCodeEnum {
	
	기타("0020000"),
	한국어("0020001"),
	영어("0020002"),
	일본어("0020003");

	private final String langCode;


	LangCodeEnum(String langCode) {
		this.langCode = langCode;
	}
}
