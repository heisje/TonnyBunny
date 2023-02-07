package com.tonnybunny.domain.schedule.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.schedule.dto.ScheduleRequestDto;
import com.tonnybunny.domain.schedule.dto.ScheduleResponseDto;
import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import com.tonnybunny.domain.schedule.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Api(tags = "일정 관리 관련 API")
public class ScheduleController {

	private final ScheduleService scheduleService;


	@PostMapping
	@ApiOperation(value = "특정 날짜 일정 생성 API", notes = "")
	public ResponseEntity<ResultDto<Long>> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {

		// service
		Long createdScheduleSeq = scheduleService.createSchedule(scheduleRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdScheduleSeq));

	}


	@PutMapping("/{scheduleSeq}")
	@ApiOperation(value = "특정 날짜 일정 수정 API", notes = "")
	public ResponseEntity<ResultDto<Long>> modifySchedule(@PathVariable("scheduleSeq") Long scheduleSeq,
	                                                      @RequestBody ScheduleRequestDto scheduleRequestDto
	) {

		// service
		Long updatedScheduleSeq = scheduleService.modifySchedule(scheduleSeq, scheduleRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedScheduleSeq));

	}


	@GetMapping
	@ApiOperation(value = "특정 날짜 일정 목록 조회 API", notes = "조회하고 싶은 날짜의 연, 월, 일 정보를 보냅니다.")
	public ResponseEntity<ResultDto<List<ScheduleResponseDto>>> getScheduleList(@RequestBody ScheduleRequestDto scheduleRequestDto) {

		// service
		List<ScheduleEntity> scheduleList = scheduleService.getScheduleList(scheduleRequestDto);

		// entity -> dto
		List<ScheduleResponseDto> scheduleResponseDtoList = ScheduleResponseDto.fromEntityList(scheduleList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(scheduleResponseDtoList));
	}


	@GetMapping("/{scheduleSeq}")
	@ApiOperation(value = "특정 날짜 일정 상세 조회 API", notes = "")
	public ResponseEntity<ResultDto<ScheduleResponseDto>> getSchedule(@PathVariable("scheduleSeq") Long scheduleSeq) {

		// service
		ScheduleEntity schedule = scheduleService.getSchedule(scheduleSeq);

		// entity -> dto
		ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.fromEntity(schedule);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(scheduleResponseDto));

	}


	@DeleteMapping("/{scheduleSeq}")
	@ApiOperation(value = "특정 날짜 일정 삭제 API", notes = "")
	public ResponseEntity<ResultDto<Boolean>> deleteSchedule(@PathVariable("scheduleSeq") Long scheduleSeq) {

		// service
		Boolean result = scheduleService.deleteSchedule(scheduleSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(result));

	}

}
