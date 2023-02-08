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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(tags = "포인트 관리 API")
public class PointController {

	private final PointService pointService;


	/**
	 * @param pointLogRequestDto : 대상 유저 Seq, 조회하고 싶은 로그 개수 정보 포함
	 * @return 포인트 로그 목록
	 */
	@PostMapping("/point-log")
	@ApiOperation(value = "대상 유저의 포인트 로그 목록 조회", notes = "")
	public ResponseEntity<ResultDto<List<PointLogResponseDto>>> getPointLogList(@RequestBody PointLogRequestDto pointLogRequestDto) {
		List<PointLogEntity> pointLogList = pointService.getPointLogList(pointLogRequestDto);
		List<PointLogResponseDto> pointLogResponseDtoList = PointLogResponseDto.fromEntityList(pointLogList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(pointLogResponseDtoList));
	}


	/**
	 * @param pointRequestDto : 거래 유저들 Seq, 포인트 양, 변동 타입(충전, 출금, 거래), 계좌 정보 포함
	 * @return 성공 여부
	 */
	@PutMapping("/points")
	@ApiOperation(value = "포인트 변동(충전, 출금, 거래)", notes = "인자로 전달한 변동 타입(충전, 출금, 거래)에 따라 로직이 분기됨")
	public ResponseEntity<ResultDto<Boolean>> modifyPoint(@RequestBody PointRequestDto pointRequestDto) {
		switch (pointRequestDto.getPointRequestType()) {
		case 충전:
			pointService.buyPoint(pointRequestDto);
			break;
		case 출금:
			pointService.withdrawPoint(pointRequestDto);
			break;
		case 거래:
			pointService.dealPoint(pointRequestDto);
			break;
		}

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

}
