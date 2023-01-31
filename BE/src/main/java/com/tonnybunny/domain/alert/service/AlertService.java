package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface AlertService {

	// C
	Boolean createAlertLog(AlertLogRequestDto alertLogRequestDto); // 로그 생성

	// R
	List<AlertLogEntity> getAlertLogList(AlertLogRequestDto alertLogRequestDto); // 전체 로그 조회

	// U
	Boolean modifyAlertIsRead(Long alertLogSeq); // isRead 수정
	Boolean modifyAlertSettings(AlertSettingsDto alertSettingsDto); // 세팅 수정

	// D
	Boolean deleteAlertLog(Long alertLogSeq); // 로그 삭제

}
