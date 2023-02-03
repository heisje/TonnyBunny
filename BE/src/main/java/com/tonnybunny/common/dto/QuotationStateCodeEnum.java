package com.tonnybunny.common.dto;


import lombok.ToString;


@ToString
public enum QuotationStateCodeEnum {
	미선택("0070001"),
	선택("0070002"),
	작업완료("0070003");

	private final String quotationStateCode;


	QuotationStateCodeEnum(String quotationStateCode) {
		this.quotationStateCode = quotationStateCode;
	}

}
