package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AlertServiceImpl implements AlertService {

	/**
	 * 알림 설정
	 *
	 * @param alertSettingsDto : 알림 설정 정보 포함
	 */
	@Override
	public void modifyAlertSettings(AlertSettingsDto alertSettingsDto) {
		// TODO : 구현

	}


	/**
	 * 이벤트 알림 생성
	 *
	 * @param alertLogRequestDto : 알림 메세지 등의 내용 포함
	 * @return 성공 여부
	 */
	@Override
	public Boolean createAlertLog(AlertLogRequestDto alertLogRequestDto) {
		// TODO : 구현

		return true;
	}


	/**
	 * 대상 유저의 안 읽은 알림 목록 조회
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return 알림 목록
	 */
	@Override
	public List<AlertLogEntity> getAlertLogList(Long userSeq) {
		// TODO : 구현

		return new ArrayList<>();
	}

}
