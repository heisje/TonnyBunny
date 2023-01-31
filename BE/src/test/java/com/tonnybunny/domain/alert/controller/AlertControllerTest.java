package com.tonnybunny.domain.alert.controller;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import org.junit.jupiter.api.Test;


class AlertControllerTest {

	@Test
	void getAlertLogList() {
		System.out.println("getAlertLog 테스트 진행 중");

		// given : 주어진 것
		AlertLogRequestDto alertLogRequestDto = new AlertLogRequestDto();
		alertLogRequestDto.setPage(2); // 2 page 에서 시작해서
		alertLogRequestDto.setSize(3); // 2 개씩 가져오기

		// when : 기능 상황
		// then : 결과
	}

}