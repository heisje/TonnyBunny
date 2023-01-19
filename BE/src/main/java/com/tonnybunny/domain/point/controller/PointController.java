package com.tonnybunny.domain.point.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.point.dto.PointLogRequestDto;
import com.tonnybunny.domain.point.dto.PointLogResponseDto;
import com.tonnybunny.domain.point.dto.PointRequestDto;
import com.tonnybunny.domain.point.entity.PointLogEntity;
import com.tonnybunny.domain.point.service.PointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(tags = "포인트 관리 API")
public class PointController {

	private final PointService pointService;


	@GetMapping("/point-log")
	@ApiOperation(value = "포인트 로그 목록 조회", notes = "")
	public ResponseEntity<ResultDto<List<PointLogResponseDto>>> getPointLogList(
		@RequestBody PointLogRequestDto pointLogRequestDto) {
		List<PointLogEntity> pointLogList = pointService.getPointLogList(pointLogRequestDto);
		List<PointLogResponseDto> pointLogResponseDtoList = PointLogResponseDto.fromEntityList(
			pointLogList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(pointLogResponseDtoList));
	}


	@GetMapping("/points/{userSeq}")
	@ApiOperation(value = "대상 유저의 포인트 양 조회", notes = "")
	public ResponseEntity<ResultDto<Integer>> getPoint(@PathVariable("userSeq") Long userSeq) {
		Integer point = pointService.getPoint(userSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(point));
	}


	@PutMapping("/points")
	@ApiOperation(value = "포인트 거래(충전, 출금, 거래)", notes = "인자로 전달한 거래 타입(충전, 출금, 거래)에 따라 로직이 분기됨")
	public ResponseEntity<ResultDto<Boolean>> modifyPoint(
		@RequestBody PointRequestDto pointRequestDto) {
		switch (pointRequestDto.getPointRequestType()) {
		case 충전:
		case 출금:
		case 거래:
		}

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

}
