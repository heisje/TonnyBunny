package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.repository.AlertLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@DisplayName("알림 API TEST")
class AlertServiceTest {

	@Autowired
	private AlertLogRepository alertLogRepository;


	@Test
	@DisplayName("알림 생성 TEST")
	void createAlertLog() {
	}


	@Test
	@DisplayName("알림 조회 TEST")
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