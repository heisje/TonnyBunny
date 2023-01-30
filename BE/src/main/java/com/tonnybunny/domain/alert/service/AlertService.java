package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;

import java.util.List;


public interface AlertService {

	void modifyAlertSettings(AlertSettingsDto alertSettingsDto);

	Boolean createAlertLog(AlertLogRequestDto alertLogRequestDto);

	List<AlertLogEntity> getAlertLogList(Long userSeq);

}
