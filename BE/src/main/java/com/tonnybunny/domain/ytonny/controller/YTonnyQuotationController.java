package com.tonnybunny.domain.ytonny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyQuotationRequestDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyQuotationEntity;
import com.tonnybunny.domain.ytonny.service.YTonnyQuotationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/ytonny")
@RequiredArgsConstructor
@Api(tags = "예약통역 견적서 API")
public class YTonnyQuotationController {

	private final YTonnyQuotationService yTonnyQuotationService;


	/**
	 * MEMO : CREATE
	 * MARK : 예약통역 견적서를 생성
	 *
	 * @param yTonnyQuotationRequestDto : 예약통역 공고 관련 정보
	 * @param yTonnySeq                 : 대상 예약통역 공고 seq
	 * @return 생성된 예약통역 공고 seq
	 */
	@PostMapping("/{yTonnySeq}/quotation")
	@ApiOperation(value = "예약통역 견적서 생성 API", notes = "헬퍼가 견적서를 생성한다.")
	public ResponseEntity<ResultDto<Long>> createYTonnyQuotation(@PathVariable Long yTonnySeq,
	                                                             @RequestBody YTonnyQuotationRequestDto yTonnyQuotationRequestDto,
	                                                             MultipartHttpServletRequest request) {

		System.out.println("YTonnyQuotationController.createYTonnyQuotation");

		// service
		Long createdSeq = yTonnyQuotationService.createYTonnyQuotation(yTonnySeq, yTonnyQuotationRequestDto, request);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdSeq));

	}


	/**
	 * MEMO : READ
	 * MARK : 번역 견적서 목록 조회
	 *
	 * @param yTonnySeq : 예약통역 공고 관련 정보
	 * @return 생성된 예약통역 공고 seq
	 */
	@GetMapping("/{yTonnySeq}/quotation")
	@ApiOperation(value = "예약통역 견적서 조회 API", notes = "고객과 헬퍼가 예약통역 공고 관련 견적서를 조회한다.")
	public ResponseEntity<ResultDto<List<YTonnyQuotationRequestDto>>> getYTonnyQuotationList(@PathVariable Long yTonnySeq) {

		System.out.println("YTonnyQuotationController.getYTonnyQuotationList");

		// service
		List<YTonnyQuotationEntity> yTonnyQuotationEntityList = yTonnyQuotationService.getYTonnyQuotationList(yTonnySeq);

		// entity -> dto
		ModelMapper modelMapper = new ModelMapper();
		List<YTonnyQuotationRequestDto> yTonnyQuotationRequestDtoList = yTonnyQuotationEntityList.stream()
		                                                                                         .map(m -> modelMapper.map(m, YTonnyQuotationRequestDto.class)).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyQuotationRequestDtoList));

	}


	/**
	 * MARK : 번역 견적서 상세 조회
	 *
	 * @param yTonnySeq          : 대상 예약통역 seq
	 * @param yTonnyQuotationSeq : 대상 견적서 seq
	 * @return 견적서 내용 반환
	 */
	@GetMapping("/{yTonnySeq}/quotation/{yTonnyQuotationSeq}")
	@ApiOperation(value = "예약통역 견적서 상세 조회 API", notes = "고객과 헬퍼가 예약통역 공고 관련 견적서를 상세 조회한다.")
	public ResponseEntity<ResultDto<YTonnyQuotationRequestDto>> getYTonnyQuotationDetail(@PathVariable Long yTonnySeq, @PathVariable Long yTonnyQuotationSeq) {

		System.out.println("YTonnyQuotationController.getYTonnyQuotationList");

		// service
		YTonnyQuotationEntity yTonnyQuotationEntity = yTonnyQuotationService.getYTonnyQuotationDetail(yTonnyQuotationSeq);

		// entity -> dto
		ModelMapper modelMapper = new ModelMapper();
		YTonnyQuotationRequestDto yTonnyQuotationRequestDto = modelMapper.map(yTonnyQuotationEntity, YTonnyQuotationRequestDto.class);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyQuotationRequestDto));

	}


	/**
	 * MEMO : UPDATE
	 * MARK : 예약통역 견석서의 상태를 수정
	 *
	 * @param yTonnySeq                 : 대상 예약통역 seq
	 * @param yTonnyQuotationRequestDto : 대상 견적서 관련 정보
	 * @return 수정된 견적서 seq
	 */
	@PutMapping("/{yTonnySeq}/quotation")
	@ApiOperation(value = "예약통역 견적서 상태 수정 API", notes = "고객이 예약통역 공고를 생성한다.")
	public ResponseEntity<ResultDto<Long>> modifyYTonnyQuotationState(@PathVariable Long yTonnySeq, @RequestBody YTonnyQuotationRequestDto yTonnyQuotationRequestDto) {

		System.out.println("YTonnyQuotationController.modifyYTonnyQuotation");

		// service
		Long updatedSeq = yTonnyQuotationService.modifyYTonnyQuotationState(yTonnyQuotationRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedSeq));

	}

}
