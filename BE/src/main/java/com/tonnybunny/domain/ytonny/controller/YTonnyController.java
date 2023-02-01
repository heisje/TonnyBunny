package com.tonnybunny.domain.ytonny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyApplyRequestDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyApplyResponseDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyRequestDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyResponseDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyApplyEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyEntity;
import com.tonnybunny.domain.ytonny.service.YTonnyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/ytonny")
@RequiredArgsConstructor
@Api(tags = "예약 통역 관련 API")
public class YTonnyController {

	private final YTonnyService yTonnyService;


	/**
	 * MEMO : CREATE
	 * MARK : 예약통역 공고를 생성
	 *
	 * @param yTonnyRequestDto
	 * @return 생성된 예약통역 공고 seq
	 */
	@PostMapping
	@ApiOperation(value = "예약통역 공고 생성 API", notes = "고객이 예약통역 공고를 생성한다.")
	public ResponseEntity<ResultDto<Long>> createYTonny(@RequestBody YTonnyRequestDto yTonnyRequestDto) {

		System.out.println("YTonnyController.createYTonny");

		// service
		Long createdSeq = yTonnyService.createYTonny(yTonnyRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdSeq));

	}


	/**
	 * MARK : (헬퍼가) 예약통역 공고 신청을 생성
	 *
	 * @param yTonnyApplyRequestDto
	 * @return 생성된 예약통역 공고 신청 seq
	 */
	@PostMapping("/enroll")
	@ApiOperation(value = "예약통역 공고 신청 생성 API", notes = "헬퍼가 예약통역 공고를 신청한다.")
	public ResponseEntity<ResultDto<Long>> createYTonnyApply(@RequestBody YTonnyApplyRequestDto yTonnyApplyRequestDto) {

		System.out.println("YTonnyController.createYTonnyApply");

		// service
		Long createdSeq = yTonnyService.createYTonnyApply(yTonnyApplyRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdSeq));

	}


	/**
	 * MEMO : READ
	 * MARK : 예약통역 공고 목록을 조회 with pagination
	 *
	 * @param yTonnyRequestDto
	 * @return 예약통역 공고 목록 반환
	 */
	@GetMapping
	@ApiOperation(value = "예약통역 공고 목록 조회 API", notes = "사용자가 예약통역 목록을 조회한다.")
	public ResponseEntity<ResultDto<List<YTonnyResponseDto>>> getYTonnyList(YTonnyRequestDto yTonnyRequestDto) {

		System.out.println("YTonnyController.getYTonnyList");

		// service
		List<YTonnyEntity> yTonnyNotiList = yTonnyService.getYTonnyList(yTonnyRequestDto);

		// dto 로 변경
		// isDelete 가 false 인 값들만 반환해야하나?
		List<YTonnyResponseDto> yTonnyResponseDtoList = yTonnyNotiList.stream()
		                                                              .map(m -> YTonnyResponseDto.builder()
		                                                                                         .title(m.getTitle())
		                                                                                         .content(m.getContent())
		                                                                                         .estimatePrice(m.getEstimatePrice())
		                                                                                         .estimateDate(m.getEstimateDate())
		                                                                                         .estimateStartTime(m.getEstimateStartTime())
		                                                                                         .estimateTime(m.getEstimateTime())
		                                                                                         .startLangCode(m.getStartLangCode())
		                                                                                         .endLangCode(m.getEndLangCode())
		                                                                                         .tonnySituCode(m.getTonnySituCode())
		                                                                                         .taskCode(m.getTaskCode())
		                                                                                         .taskStateCode(m.getTaskStateCode())
		                                                                                         .createdAt(m.getCreatedAt())
		                                                                                         .updatedAt(m.getUpdatedAt())
		                                                                                         .build())
		                                                              .collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyResponseDtoList));

	}


	/**
	 * MARK : 예약통역 공고 목록을 상세 조회
	 *
	 * @param yTonnyNotiSeq
	 * @return 생성된 예약통역 공고 seq
	 */
	@GetMapping("/{yTonnyNotiSeq}")
	@ApiOperation(value = "예약통역 공고 목록 상세 조회 API", notes = "사용자가 예약통역 공고를 상세 조회한다.")
	public ResponseEntity<ResultDto<YTonnyResponseDto>> getYTonnyDetail(@PathVariable Long yTonnyNotiSeq) {

		System.out.println("YTonnyController.getYTonnyDetail");

		// service
		YTonnyEntity yTonnyEntity = yTonnyService.getYTonnyDetail(yTonnyNotiSeq);

		// dto 로 변경
		YTonnyResponseDto yTonnyResponseDto = YTonnyResponseDto.builder()
		                                                       .title(yTonnyEntity.getTitle())
		                                                       .content(yTonnyEntity.getContent())
		                                                       .estimatePrice(yTonnyEntity.getEstimatePrice())
		                                                       .estimateDate(yTonnyEntity.getEstimateDate())
		                                                       .estimateStartTime(yTonnyEntity.getEstimateStartTime())
		                                                       .estimateTime(yTonnyEntity.getEstimateTime())
		                                                       .startLangCode(yTonnyEntity.getStartLangCode())
		                                                       .endLangCode(yTonnyEntity.getEndLangCode())
		                                                       .tonnySituCode(yTonnyEntity.getTonnySituCode())
		                                                       .taskCode(yTonnyEntity.getTaskCode())
		                                                       .taskStateCode(yTonnyEntity.getTaskStateCode())
		                                                       .createdAt(yTonnyEntity.getCreatedAt())
		                                                       .updatedAt(yTonnyEntity.getUpdatedAt())
		                                                       .build();

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyResponseDto));

	}


	/**
	 * MARK : 예약통역 공고 신청 목록을 조회
	 *
	 * @param yTonnyNotiSeq
	 * @return 생성된 예약통역 공고 seq
	 */
	@GetMapping("/enroll/{yTonnyNotiSeq}")
	@ApiOperation(value = "예약통역 신청 목록 조회 API", notes = "고객이 해당 공고의 신청 목록을 조회한다.")
	public ResponseEntity<ResultDto<List<YTonnyApplyResponseDto>>> getYTonnyApplyList(@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq) {

		System.out.println("YTonnyController.getYTonnyApplyList");

		// service
		List<YTonnyApplyEntity> yTonnyApplyList = yTonnyService.getYTonnyApplyList(yTonnyNotiSeq);

		// dto 로 변경
		List<YTonnyApplyResponseDto> yTonnyApplyResponseDtoList = yTonnyApplyList.stream()
		                                                                         .map(m -> YTonnyApplyResponseDto.builder()
		                                                                                                         .seq(m.getSeq())
		                                                                                                         .totalPrice(m.getTotalPrice())
		                                                                                                         .build())
		                                                                         .collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyApplyResponseDtoList));

	}


	/**
	 * MEMO : UPDATE
	 * MARK : 예약통역 공고를 생성
	 *
	 * @param yTonnyRequestDto
	 * @return 생성된 예약통역 공고 seq
	 */
	@PutMapping("/{yTonnyNotiSeq}")
	@ApiOperation(value = "예약통역 공고 수정 API", notes = "고객이 예약통역을 수정한다.")
	public ResponseEntity<ResultDto<Long>> modifyYTonny(@RequestBody YTonnyRequestDto yTonnyRequestDto) {

		System.out.println("YTonnyController.modifyYTonny");

		// service
		Long updatedSeq = yTonnyService.modifyYTonny(yTonnyRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedSeq));

	}


	/**
	 * MARK : 헬퍼의 예약통역 신청을 수락
	 *
	 * @param yTonnyNotiSeq, yTonnyNotiHelperSeq
	 * @return 생성된 예약통역 공고 seq
	 */
	@PutMapping("/match/{yTonnyNotiSeq}/{yTonnyNotiHelperSeq}")
	@ApiOperation(value = "예약통역 신청 수락 API", notes = "고객이 헬퍼의 예약통역 신청을 수락한다.")
	public ResponseEntity<ResultDto<Long>> matchYTonny(@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq,
		@PathVariable("yTonnyNotiHelperSeq") Long yTonnyNotiHelperSeq) {

		Long updatedSeq = yTonnyService.matchYTonny(yTonnyNotiSeq, yTonnyNotiHelperSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedSeq));

	}


	/**
	 * MEMO : DELETE
	 * MARK : 예약통역 공고를 생성
	 *
	 * @param yTonnyNotiSeq
	 * @return 생성된 예약통역 공고 seq
	 */
	@DeleteMapping("/{yTonnyNotiSeq}")
	@ApiOperation(value = "예약통역 공고 삭제 API", notes = "고객이 예약통역을 삭제한다.")
	public ResponseEntity<ResultDto<Boolean>> deleteYTonny(@PathVariable("yTonnyNotiSeq") Long yTonnyNotiSeq) {

		System.out.println("YTonnyController.deleteYTonny");

		// service
		yTonnyService.deleteYTonny(yTonnyNotiSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}


	/**
	 * MARK : 예약통역 공고를 생성
	 *
	 * @param yTonnyNotiHelperSeq
	 * @return 생성된 예약통역 공고 seq
	 */
	@DeleteMapping("/enroll/{yTonnyNotiHelperSeq}")
	@ApiOperation(value = "예약통역 공고 신청 취소 API", notes = "헬퍼가 예약통역 신청을 취소한다.")
	public ResponseEntity<ResultDto<Boolean>> deleteYTonnyApply(@PathVariable("yTonnyNotiHelperSeq") Long yTonnyNotiHelperSeq) {

		System.out.println("YTonnyController.deleteYTonnyApply");

		// service
		yTonnyService.deleteYTonnyApply(yTonnyNotiHelperSeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}

}
