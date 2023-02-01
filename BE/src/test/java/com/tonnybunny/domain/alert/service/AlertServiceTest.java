package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.repository.AlertLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DisplayName("알림 API TEST")
class AlertServiceTest {

	@Autowired
	private AlertLogRepository alertLogRepository;


	@Test
	@DisplayName("알림 로그 생성 TEST")
	void createAlertLog() {
		// given : 알림 로그 관련 정보
		// when : 알림 로그 생성
		// then : 알림 로그 find 와
	}


	@Test
	@DisplayName("알림 로그 목록 조회 TEST")
	void getAlertLogList() {
	}


	@Test
	@DisplayName("알림 세팅 조회 TEST")
	void getAlertSettings() {
	}


	@Test
	@DisplayName("알림 읽음확인 TEST")
	void modifyAlertIsRead() {
	}


	@Test
	@DisplayName("알림 설정 수정 TEST")
	void modifyAlertSettings() {
	}


	@Test
	@DisplayName("알림 삭제 TEST")
	void deleteAlertLog() {
	}

}