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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
@Api(tags = "알림 관련 API")
public class AlertController {

	@Autowired
	private final AlertServiceImpl alertService;


	/**
	 * MARK : 계약이 성립되면 알람 생성
	 * ex) 즉시통역이 성립되었습니다. 오늘 통역예약이 있습니다. + 날짜, 시간 표기
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, 카테고리 taskCode, 내용 content
	 * @return 생성 성공 여부
	 */
	@PostMapping("/log")
	@ApiOperation(value = "알림 생성 API", notes = "이벤트 발생 시, 알림 메세지를 생성할 때 사용하는 API")
	public ResponseEntity<ResultDto<Boolean>> createAlertLog(@RequestBody AlertLogRequestDto alertLogRequestDto) {

		Boolean isCreate = alertService.createAlertLog(alertLogRequestDto);

		if (isCreate) {
		} else {
		}

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		
	}


	/**
	 * MARK : 전체 알림 로그 목록을 반환
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, page, size
	 * @return 전체 알림 로그 목록
	 */
	@GetMapping("/log")
	@ApiOperation(value = "전체 알림 로그를 반환", notes = "")
	public ResponseEntity<ResultDto<List<AlertLogResponseDto>>> getAlertLogList(AlertLogRequestDto alertLogRequestDto) {

		Page<AlertLogEntity> alertLogList = alertService.getAlertLogList(alertLogRequestDto);
		System.out.println("안녕안녕:" + alertLogList);

		//		List<AlertLogResponseDto> alertLogResponseDtoList = AlertLogResponseDto.fromEntityList(alertLogList);
		List<AlertLogResponseDto> alertLogResponseDtoList = null;

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(alertLogResponseDtoList));
	}


	/**
	 * MARK : 알림 읽음확인 수정
	 *
	 * @param alertLogSeq : 대상 알림 로그 seq
	 * @return 알림 읽음확인 수정 여부
	 */
	@PutMapping("/log/{alertLogSeq}")
	@ApiOperation(value = "알림 읽음 확인 수정 API", notes = "")
	public ResponseEntity<ResultDto<Boolean>> modifyAlertSettings(@PathVariable Long alertLogSeq) {

		Boolean isModify = alertService.modifyAlertIsRead(alertLogSeq);

		// isModify 이라면 ofSuccess

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/**
	 * MARK : 알림 설정 toggle 수정
	 *
	 * @param alertSettingsDto : 통역, 번역, 커뮤니티, 메시지(채팅) 알람
	 * @return 알림 설정 변경 여부
	 */
	@PutMapping("/settings")
	@ApiOperation(value = "알림 설정 수정 API", notes = "")
	public ResponseEntity<ResultDto<Boolean>> modifyAlertSettings(@RequestBody AlertSettingsDto alertSettingsDto) {

		Boolean isModify = alertService.modifyAlertSettings(alertSettingsDto);

		// 만약 isModify 됐다면 ok
		// 아니면 exception

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/**
	 * MARK : 알림 삭제
	 *
	 * @param alertLogSeq : 대상 알림 로그 seq
	 * @return 알림 삭제 여부
	 */
	@DeleteMapping("/log/{alertLogSeq}")
	@ApiOperation(value = "알림 삭제 API", notes = "")
	public ResponseEntity<ResultDto<Boolean>> deleteAlertLog(@PathVariable Long alertLogSeq) {
		//		Boolean isDelete = alertService.deleteAlertLog(alertLogSeq);

		// isModify 이라면 ofSuccess

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

}
