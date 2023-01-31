package com.tonnybunny.domain.alert.controller;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.service.AlertService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("알림 테스트")
class AlertControllerTest {

	@Autowired
	AlertService alertService;


	@Test
	@DisplayName("알림 전체 목록 조회 테스트")
	void getAlertLogList() {

		System.out.println("getAlertLog 테스트 진행 중");

		// given : 주어진 것
		AlertLogRequestDto alertLogRequestDto = new AlertLogRequestDto();
		alertLogRequestDto.setPage(2); // 2 page 에서 시작해서
		alertLogRequestDto.setSize(3); // 2 개씩 가져오기

		// when : 기능 상황
		// then : 결과
	}


	@Test
	@DisplayName("알림 생성 테스트")
	void createAlertLog() {

		System.out.println("createAlertLog 테스트 !!!!!!1");

		// given
		//		AlertLogRequestDto alertLogRequestDto = new AlertLogRequestDto();
		//		alertLogRequestDto.setUserSeq(1L);
		//		alertLogRequestDto.setTaskCode("즉시통역");
		//		alertLogRequestDto.setContent("즉시 통역을 시작합니다.");
		//
		//		// when
		//		Boolean isCreate = alertService.createAlertLog(alertLogRequestDto);
		//
		//		// then
		//		System.out.println(isCreate);
	}

}