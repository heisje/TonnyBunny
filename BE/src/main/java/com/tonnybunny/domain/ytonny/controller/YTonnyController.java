package com.tonnybunny.domain.ytonny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.ytonny.dto.*;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiHelperEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyResultEntity;
import com.tonnybunny.domain.ytonny.service.YTonnyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ytonny")
@RequiredArgsConstructor
@Api(tags = "예약 통역 관련 API")
public class YTonnyController {

	private final YTonnyService yTonnyService;


	@PostMapping("/")
	@ApiOperation(value = "고객의 예약 통역 공고 생성", notes = "")
	public ResponseEntity<ResultDto<Long>> createYTonnyNoti(@RequestBody YTonnyNotiRequestDto yTonnyNotiRequestDto) {
		Long createdYTonnyNotiSeq = yTonnyService.createYTonnyNoti(yTonnyNotiRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdYTonnyNotiSeq));
	}


	@PutMapping("/{yTonnyNotiSeq}")
	@ApiOperation(value = "고객의 예약 통역 공고 수정", notes = "")
	public ResponseEntity<ResultDto<Long>> modifyYTonnyNoti(@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq, @RequestBody YTonnyNotiRequestDto yTonnyNotiRequestDto) {
		Long updatedYTonnyNotiSeq = yTonnyService.modifyYTonnyNoti(yTonnyNotiSeq, yTonnyNotiRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedYTonnyNotiSeq));
	}


	@DeleteMapping("/{yTonnyNotiSeq}")
	@ApiOperation(value = "고객의 예약 통역 공고 취소", notes = "")
	public ResponseEntity<ResultDto<Boolean>> deleteYTonnyNoti(@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq) {
		yTonnyService.deleteYTonnyNoti(yTonnyNotiSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	@GetMapping("/")
	@ApiOperation(value = "예약 통역 공고 목록 조회", notes = "")
	public ResponseEntity<ResultDto<List<YTonnyNotiResponseDto>>> getYTonnyNotiList() {
		List<YTonnyNotiEntity> yTonnyNotiList = yTonnyService.getYTonnyNotiList();
		List<YTonnyNotiResponseDto> yTonnyNotiResponseDtoList = YTonnyNotiResponseDto.fromEntityList(yTonnyNotiList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyNotiResponseDtoList));
	}


	@GetMapping("/{yTonnyNotiSeq}")
	@ApiOperation(value = "예약 통역 공고 상세 조회", notes = "")
	public ResponseEntity<ResultDto<YTonnyNotiResponseDto>> getYTonnyNoti(@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq) {
		YTonnyNotiEntity yTonnyNoti = yTonnyService.getYTonnyNoti(yTonnyNotiSeq);
		YTonnyNotiResponseDto yTonnyNotiResponseDto = YTonnyNotiResponseDto.fromEntity(yTonnyNoti);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyNotiResponseDto));
	}


	@PostMapping("/enroll")
	@ApiOperation(value = "헬퍼의 예약 통역 신청 등록", notes = "")
	public ResponseEntity<ResultDto<Long>> createYTonnyNotiHelper(@RequestBody YTonnyNotiHelperRequestDto yTonnyNotiHelperRequestDto) {
		Long craetedYTonnyNotiHelper = yTonnyService.createYTonnyNotiHelper(yTonnyNotiHelperRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(craetedYTonnyNotiHelper));
	}


	@DeleteMapping("/enroll/{yTonnyNotiHelperSeq}")
	@ApiOperation(value = "헬퍼의 예약 통역 신청 취소", notes = "")
	public ResponseEntity<ResultDto<Boolean>> deleteYTonnyNotiHelper(@PathVariable("yTonnyNotiHelperSeq") Long yTonnyNotiHelperSeq) {
		yTonnyService.deleteYTonnyNotiHelper(yTonnyNotiHelperSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	@GetMapping("/enroll/{yTonnyNotiSeq}")
	@ApiOperation(value = "예약 통역 신청 목록 조회", notes = "")
	public ResponseEntity<ResultDto<List<YTonnyNotiHelperResponseDto>>> getYTonnyNotiHelperList(@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq) {
		List<YTonnyNotiHelperEntity> yTonnyNotiHelperList = yTonnyService.getYTonnyNotiHelperList(yTonnyNotiSeq);
		List<YTonnyNotiHelperResponseDto> yTonnyNotiHelperResponseDtoList = YTonnyNotiHelperResponseDto.fromEntityList(yTonnyNotiHelperList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyNotiHelperResponseDtoList));
	}


	@PostMapping("/match/{yTonnyNotiSeq}/{yTonnyNotiHelperSeq}")
	@ApiOperation(value = "예약 통역 공고에서 헬퍼의 신청을 수락", notes = "")
	public ResponseEntity<ResultDto<Boolean>> matchYTonny(@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq, @PathVariable("yTonnyNotiHelperSeq") Long yTonnyNotiHelperSeq) {
		yTonnyService.matchYTonny(yTonnyNotiSeq, yTonnyNotiHelperSeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}


	@PostMapping("/result")
	@ApiOperation(value = "예약 통역 종료 후, 결과를 저장", notes = "")
	public ResponseEntity<ResultDto<Long>> createYTonnyResult(@RequestBody YTonnyResultDto yTonnyResultDto) {
		Long createdYTonnyResultSeq = yTonnyService.createYTonnyResult(yTonnyResultDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdYTonnyResultSeq));
	}


	@GetMapping("/result/{yTonnyResultSeq}")
	@ApiOperation(value = "예약 통역 결과 조회", notes = "")
	public ResponseEntity<ResultDto<YTonnyResultDto>> getYTonnyResult(@PathVariable("yTonnyResultSeq") Long yTonnyResultSeq) {
		YTonnyResultEntity yTonnyResult = yTonnyService.getYTonnyResult(yTonnyResultSeq);
		YTonnyResultDto yTonnyResultDto = YTonnyResultDto.fromEntity(yTonnyResult);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyResultDto));
	}

	// TODO : 예약 통역 결과 목록 조회 API도 필요한가? 아니면 히스토리 Controller에서 추가?
	//  만약 추가한다면, 고객 userSeq로 조회 메소드 & 헬퍼 userSeq로 조회 메소드 총 2개가 있어야 할 듯
}
