package com.tonnybunny.common.dto;


public enum ResultEnum {
	SUCCESS("success"),
	FAIL("fail");

	private final String msg;


	ResultEnum(String msg) {
		this.msg = msg;
	}


	@Override
	public String toString() {
		return this.msg;
	}
}
