package com.tonnybunny.domain.alert.controller;


import com.tonnybunny.domain.alert.repository.AlertLogRepository;
import com.tonnybunny.domain.alert.service.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@SpringBootTest(properties = { "classpath:/application.yml" })
@AutoConfigureMockMvc
@DisplayName("알림 API TEST")
class AlertControllerTest {

	@Autowired
	private AlertLogRepository alertLogRepository;
	@Autowired
	private AlertService alertService;

	// mockmvc 사용
	@Autowired
	private MockMvc mvc;

	private MultiValueMap<String, String> params;


	@BeforeEach
	void beforeEach() {
		params = new LinkedMultiValueMap<>();
	}


	@Test
	@DisplayName("알림 생성 TEST")
	void createAlertLog() throws Exception {

		// param setting
		params.add("userSeq", "1");
		//		params.add("page", "1");
		//		params.add("size", "3");

		// get
		//		mvc.perform(get("/api/alerts/log").params(params))
		//		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//		   .andExpect(status().isOk())
		//		   .andExpect(jsonPath("$.data[0].taskCode").value("1234"))
		//		   .andDo(print());

	}


	@Test
	@DisplayName("알림 조회 TEST")
	void getAlertLogList() throws Exception {

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