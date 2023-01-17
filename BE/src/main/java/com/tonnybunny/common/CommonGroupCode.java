package com.tonnybunny.common;


public enum CommonGroupCode {
	USER_TYPE("001"),
	LANGUAGE("002"),
	WORK_TYPE("003"),
	TONNY_CATEGORY("004"),
	BUNNY_CATEGORY("005"),
	BUNNY_TAG("006"),
	ESTIMATE_STATUS("007"),
	Y_TONNY_NOTI_STATUS("009");

	private final String code;


	CommonGroupCode(String code) {
		this.code = code;
	}


	@Override
	public String toString() {
		return this.code;
	}
}
