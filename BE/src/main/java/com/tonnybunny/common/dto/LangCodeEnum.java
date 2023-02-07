package com.tonnybunny.common.dto;


import lombok.ToString;

import java.util.Arrays;


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


	public static LangCodeEnum valueOfCode(String langCode) {
		return Arrays.stream(values())
		             .filter(value -> value.getLangCode().equals(langCode))
		             .findAny()
		             .orElse(null);
	}


	public String getLangCode() {
		return langCode;
	}

}
