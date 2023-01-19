package com.tonnybunny.domain.ytonny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.ytonny.dto.*;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiHelperEntity;
import com.tonnybunny.domain.ytonny.service.YTonnyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/ytonny")
@RequiredArgsConstructor
@Api(tags = "예약 통역 관련 API")
public class YTonnyController {

	private final YTonnyService yTonnyService;


	@PostMapping
	@ApiOperation(value = "고객의 예약 통역 공고 생성", notes = "")
	public ResponseEntity<ResultDto<Long>> createYTonnyNoti(
		@RequestBody YTonnyNotiRequestDto yTonnyNotiRequestDto) {
		Long createdYTonnyNotiSeq = yTonnyService.createYTonnyNoti(yTonnyNotiRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdYTonnyNotiSeq));
	}


	@PutMapping("/{yTonnyNotiSeq}")
	@ApiOperation(value = "고객의 예약 통역 공고 수정", notes = "")
	public ResponseEntity<ResultDto<Long>> modifyYTonnyNoti(
		@PathVariable("yTonnyNotiSeq") Long yTonnySeq,
		@RequestBody YTonnyNotiRequestDto yTonnyNotiRequestDto) {
		Long updatedYTonnyNotiSeq = yTonnyService.modifyYTonnyNoti(yTonnySeq,
			yTonnyNotiRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedYTonnyNotiSeq));
	}


	@DeleteMapping("/{yTonnyNotiSeq}")
	@ApiOperation(value = "고객의 예약 통역 공고 취소", notes = "")
	public ResponseEntity<ResultDto<Boolean>> deleteYTonnyNoti(
		@PathVariable("yTonnyNotiSeq") Long yTonnySeq) {
		yTonnyService.deleteYTonnyNoti(yTonnySeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	@GetMapping("/list")
	@ApiOperation(value = "예약 통역 공고 목록 조회", notes = "")
	public ResponseEntity<ResultDto<List<YTonnyNotiResponseDto>>> getYTonnyNotiList() {
		List<YTonnyNotiEntity> yTonnyNotiList = yTonnyService.getYTonnyNotiList();
		List<YTonnyNotiResponseDto> yTonnyNotiResponseDtoList = YTonnyNotiResponseDto.fromEnityList(
			yTonnyNotiList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyNotiResponseDtoList));
	}


	@GetMapping("/{yTonnyNotiSeq}")
	@ApiOperation(value = "예약 통역 공고 상세 조회", notes = "")
	public ResponseEntity<ResultDto<YTonnyNotiResponseDto>> getYTonnyNoti(
		@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq) {
		YTonnyNotiEntity yTonnyNoti = yTonnyService.getYTonnyNoti(yTonnyNotiSeq);
		YTonnyNotiResponseDto yTonnyNotiResponseDto = YTonnyNotiResponseDto.fromEntity(yTonnyNoti);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyNotiResponseDto));
	}


	@PostMapping("/enroll/")
	@ApiOperation(value = "헬퍼의 예약 통역 신청 등록", notes = "")
	public ResponseEntity<ResultDto<Long>> createYTonnyHelper(
		@RequestBody YTonnyHelperRequestDto yTonnyHelperRequestDto) {
		Long craetedYTonnyHelper = yTonnyService.createYTonnyHelper(yTonnyHelperRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(craetedYTonnyHelper));
	}


	@DeleteMapping("/enroll/{yTonnyHelperSeq}")
	@ApiOperation(value = "헬퍼의 예약 통역 신청 취소", notes = "")
	public ResponseEntity<ResultDto<Boolean>> deleteYTonnyHelper(
		@PathVariable("yTonnyHelperSeq") Long yTonnyHelperSeq) {
		yTonnyService.deleteYTonnyHelper(yTonnyHelperSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	@GetMapping("/enroll/{yTonnyNotiSeq}")
	@ApiOperation(value = "예약 통역 신청 목록 조회", notes = "")
	public ResponseEntity<ResultDto<List<YTonnyHelperResponseDto>>> getYTonnyHelperList(
		@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq) {
		List<YTonnyNotiHelperEntity> yTonnyNotiHelperList =
			yTonnyService.getYTonnyHelperList(yTonnyNotiSeq);
		List<YTonnyHelperResponseDto> yTonnyHelperResponseDtoList = YTonnyHelperResponseDto.fromEnityList(
			yTonnyNotiHelperList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyHelperResponseDtoList));
	}


	@DeleteMapping("/match/{yTonnyNotiSeq}/{yTonnyHelperSeq}")
	@ApiOperation(value = "예약 통역 공고에서 헬퍼의 신청을 수락", notes = "")
	public ResponseEntity<ResultDto<Boolean>> matchYTonny(
		@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq,
		@PathVariable("yTonnyHelperSeq") Long yTonnyHelperSeq) {
		yTonnyService.matchYTonny(yTonnyNotiSeq, yTonnyHelperSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}


	@PostMapping("/result")
	@ApiOperation(value = "예약 통역 종료 후, 결과를 저장", notes = "")
	public ResponseEntity<ResultDto<Long>> createYTonnyResult(
		@RequestBody YTonnyResultDto yTonnyResultDto) {
		Long createdYTonnyResultSeq = yTonnyService.createYTonnyResult(yTonnyResultDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdYTonnyResultSeq));
	}

}
