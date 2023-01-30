package com.tonnybunny.domain.alert.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertLogResponseDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.service.AlertServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
@Api(tags = "알림 설정, 알림 로그 추가, 알림 로그 조회 API")
public class AlertController {

	@Autowired
	private final AlertServiceImpl alertService;


	/**
	 * @param alertSettingsDto
	 * @return
	 */
	@PutMapping("/settings")
	@ApiOperation(value = "알람 설정 수정 API", notes = "")
	public ResponseEntity<ResultDto<Boolean>> modifyAlertSettings(@RequestBody AlertSettingsDto alertSettingsDto) {
		alertService.modifyAlertSettings(alertSettingsDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/**
	 * FIXME : 현재 목업 컨셉은 프론트에서 알람 로그 메세지를 전달하는 방식
	 * 이벤트 발생 시, 알림 메세지를 생성할 때 사용하는 API
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, 알림 내용 정보 포함
	 * @return 생성 성공 여부
	 */
	@PostMapping("/log")
	@ApiOperation(value = "알림 생성 API", notes = "이벤트 발생 시, 알림 메세지를 생성할 때 사용하는 API")
	public ResponseEntity<ResultDto<Boolean>> createAlertLog(@RequestBody AlertLogRequestDto alertLogRequestDto) {
		alertService.createAlertLog(alertLogRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/**
	 * 전체 알림 로그 목록을 반환
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return 전체 알림 로그 목록
	 */
	@GetMapping("/log/{userSeq}")
	@ApiOperation(value = "전체 알림 로그를 반환", notes = "")
	public ResponseEntity<ResultDto<List<AlertLogResponseDto>>> getAlertLogList(@PathVariable Long userSeq) {
		List<AlertLogEntity> alertLogList = alertService.getAlertLogList(userSeq);
		List<AlertLogResponseDto> alertLogResponseDtoList = AlertLogResponseDto.fromEntityList(alertLogList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(alertLogResponseDtoList));
	}

}
