package com.tonnybunny.domain.alert.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertLogResponseDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import com.tonnybunny.domain.alert.service.AlertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
@Api(tags = "알림 관련 API")
public class AlertController {

	private final AlertService alertService;
	private final RedisTemplate<String, Object> redisTemplate;


	@GetMapping
	@ApiOperation(value = "알림 Socket", notes = "userSeq 에 따라서 알림 목록을 반환한다.")
	public ResponseEntity<ResultDto<Boolean>> getAlertLog(AlertLogRequestDto alertLogRequestDto) {
		redisTemplate.convertAndSend("alerts", alertLogRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	@PostMapping("/log")
	@ApiOperation(value = "알림 로그 생성 API", notes = "이벤트 발생 시, 알림 메세지를 생성한다.")
	public ResponseEntity<ResultDto<Long>> createAlertLog(@RequestBody AlertLogRequestDto alertLogRequestDto) {
		Long alertLogSeq = alertService.createAlertLog(alertLogRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(alertLogSeq));
	}


	@GetMapping("/log")
	@ApiOperation(value = "알림 로그 목록 반환 API", notes = "userSeq 에 따라서 알림 목록을 반환한다.")
	public ResponseEntity<ResultDto<List<AlertLogResponseDto>>> getAlertLogList(AlertLogRequestDto alertLogRequestDto) {
		List<AlertLogEntity> alertLogList = alertService.getAlertLogList(alertLogRequestDto);
		List<AlertLogResponseDto> alertLogResponseDtoList = AlertLogResponseDto.fromEntityList(alertLogList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(alertLogResponseDtoList));
	}


	@GetMapping("/settings/{userSeq}")
	@ApiOperation(value = "알림 설정 조회 API", notes = "사용자에 따른 알림 설정 값을 반환한다.")
	public ResponseEntity<ResultDto<AlertSettingsDto>> getAlertSettings(@PathVariable Long userSeq) {

		// service
		AlertSettingsEntity alertSettings = alertService.getAlertSettings(userSeq);

		// entity -> dto
		AlertSettingsDto alertSettingsDto = AlertSettingsDto.builder()
		                                                    .alertSettingsSeq(alertSettings.getSeq())
		                                                    .userSeq(userSeq)
		                                                    .isAll(alertSettings.getIsAll())
		                                                    .isChat(alertSettings.getIsChat())
		                                                    .isCommunity(alertSettings.getIsCommunity())
		                                                    .isTonnyBunny(alertSettings.getIsTonnyBunny())
		                                                    .build();

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(alertSettingsDto));

	}


	@PutMapping("/log")
	@ApiOperation(value = "알림 로그 수정 API", notes = "알림 읽었을 시, 읽었다고 표시 변경을 해준다.")
	public ResponseEntity<ResultDto<Long>> modifyAlertLog(@RequestBody AlertLogRequestDto alertLogRequestDto) {

		// service
		Long resultAlertLogSeq = alertService.modifyAlertLog(alertLogRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(resultAlertLogSeq));

	}


	@PutMapping("/settings")
	@ApiOperation(value = "알림 설정 수정 API", notes = "푸시 알림 설정을 변경한다.")
	public ResponseEntity<ResultDto<Long>> modifyAlertSettings(@RequestBody AlertSettingsDto alertSettingsDto) {

		// service
		Long resultAlertLogSeq = alertService.modifyAlertSettings(alertSettingsDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(resultAlertLogSeq));

	}


	@DeleteMapping("/log/{alertLogSeq}")
	@ApiOperation(value = "알림 삭제 API", notes = "필요없는 알림을 삭제한다.")
	public ResponseEntity<ResultDto<Boolean>> deleteAlertLog(@PathVariable Long alertLogSeq) {

		// service
		alertService.deleteAlertLog(alertLogSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}

}
