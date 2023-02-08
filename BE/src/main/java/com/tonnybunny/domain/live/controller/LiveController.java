package com.tonnybunny.domain.live.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyDto;
import com.tonnybunny.domain.live.service.LiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;


@RestController
@RequestMapping("/live")
@RequiredArgsConstructor
@Api(tags = "화상 통화 관련 API")
public class LiveController {

	private final LiveService liveService;


	@PostMapping("/start")
	@ApiOperation(value = "화상 통화를 시작합니다")
	public ResponseEntity<ResultDto<Long>> startLive(@RequestBody JTonnyDto jTonnyDto) {

		/**
		 * service 에 넘겨서
		 * 히스토리 생성, start_date_time 에 now 찍기
		 * repository 저장
		 * history 의 seq 반환
		 */
		Long liveSeq = liveService.startLive(jTonnyDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(liveSeq));

	}


	@PostMapping("/end")
	@ApiOperation(value = "화상 통화를 종료합니다")
	public void endLive(@RequestBody Long historySeq, @RequestBody LocalTime totalTime, @RequestBody String recordVideoPath) {

		/**
		 * 즉시 통역과 관련된 히스토리 가져와서 (findById)
		 * end_date_time 에 now 찍기
		 * 프론트에서 넘어온 소요시간과, DB에 저장된 소요시간을 비교하고
		 * 일정 오차범위 이내면 return true
		 */
		liveService.endLive(historySeq, totalTime, recordVideoPath);

	}

}
