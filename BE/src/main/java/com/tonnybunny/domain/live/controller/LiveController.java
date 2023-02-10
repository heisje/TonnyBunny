package com.tonnybunny.domain.live.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyDto;
import com.tonnybunny.domain.live.dto.HistoryCompleteDto;
import com.tonnybunny.domain.live.service.LiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/live")
@RequiredArgsConstructor
@Api(tags = "화상 통화 관련 API")
public class LiveController {

	private final LiveService liveService;


	@PostMapping("/jtonny/start")
	@ApiOperation(value = "즉시통역 화상 통화를 시작합니다")
	public ResponseEntity<ResultDto<Long>> startJTonnyLive(@RequestBody JTonnyDto jTonnyDto) {

		/**
		 * service 에 넘겨서
		 * 히스토리 생성, start_date_time 에 now 찍기
		 * repository 저장
		 * history 의 seq 반환
		 */
		Long historySeq = liveService.startJTonnyLive(jTonnyDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(historySeq));

	}


	@PostMapping("/ytonny/{yTonnySeq}/start")
	@ApiOperation(value = "예약통역 화상 통화를 시작합니다")
	public ResponseEntity<ResultDto<Long>> startYTonnyLive(@PathVariable("yTonnySeq") Long yTonnySeq) {

		/**
		 * 미리 생성된 예약통역 seq 를 service 에 넘겨서
		 * 히스토리 생성, start_date_time 에 now 찍기
		 * repository 저장
		 * history 의 seq 반환
		 * 근데 이전에 uuid 를 생성해주어야 한다? 방에 입장 후 해당 메서드를 호출함
		 */
		Long historySeq = liveService.startYTonnyLive(yTonnySeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(historySeq));
	}


	@PostMapping("/complete")
	@ApiOperation(value = "화상 통화를 종료합니다")
	public ResponseEntity<ResultDto<Boolean>> completeLive(@RequestBody HistoryCompleteDto historyCompleteDto) {

		/**
		 * 통역과 관련된 히스토리 가져와서 (findById)
		 * end_date_time 에 now 찍기
		 * 프론트에서 넘어온 소요시간과, DB에 저장된 소요시간을 비교하고 --> 생략?
		 * 일정 오차범위 이내면 return true
		 */
		liveService.completeLive(historyCompleteDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}

}
