package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum QuotationStateCodeEnum {
	미선택("0070001"), 수락됨("0070002"), 완료됨("0070003"), 거절됨("0070004");

	private final String quotationStateCode;


	QuotationStateCodeEnum(String quotationStateCode) {
		this.quotationStateCode = quotationStateCode;
	}


	public String getQuotationStateCode() {
		return this.quotationStateCode;
	}
}
