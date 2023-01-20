package com.tonnybunny.domain.point.dto;


public enum PointRequestTypeEnum {
	충전("buy"),
	출금("withdraw"),
	거래("deal");

	private final String type;


	PointRequestTypeEnum(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return this.type;
	}
}