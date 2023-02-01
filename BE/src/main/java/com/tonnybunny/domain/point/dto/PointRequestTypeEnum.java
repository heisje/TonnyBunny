package com.tonnybunny.domain.point.dto;


public enum PointRequestTypeEnum {
	충전("0110001"),
	출금("0110002"),
	거래("0110003");

	private final String typeCode;


	PointRequestTypeEnum(String typeCode) {
		this.typeCode = typeCode;
	}


	@Override
	public String toString() {
		return this.typeCode;
	}
	
}