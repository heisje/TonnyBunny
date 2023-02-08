package com.tonnybunny.common.dto;


import lombok.ToString;

import java.util.Arrays;


@ToString
public enum QuotationStateCodeEnum {
	미선택("0070001"), 수락됨("0070002"), 완료됨("0070003"), 거절됨("0070004");

	private final String quotationStateCode;


	QuotationStateCodeEnum(String quotationStateCode) {
		this.quotationStateCode = quotationStateCode;
	}


	public static QuotationStateCodeEnum valueOfCode(String quotationStateCode) {
		return Arrays.stream(values())
		             .filter(value -> value.getQuotationStateCode().equals(quotationStateCode))
		             .findAny()
		             .orElse(null);
	}


	public String getQuotationStateCode() {
		return this.quotationStateCode;
	}
}
