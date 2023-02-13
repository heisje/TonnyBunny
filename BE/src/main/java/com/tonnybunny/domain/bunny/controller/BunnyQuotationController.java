package com.tonnybunny.domain.bunny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.bunny.dto.BunnyQuotationRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyQuotationResponseDto;
import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import com.tonnybunny.domain.bunny.service.BunnyQuotationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bunny")
@RequiredArgsConstructor
@Api(tags = "번역 견적서 관련 API")
public class BunnyQuotationController {

	private final BunnyQuotationService bunnyQuotationService;


	/**
	 * 번역 견적서 생성
	 *
	 * @param bunnyQuotationRequestDto
	 * @return
	 */
	@PostMapping("/{bunnySeq}/quotation")
	@ApiOperation(value = "번역 견적서 생성")
	public ResponseEntity<ResultDto<Long>> createBunnyQuotation(@RequestBody BunnyQuotationRequestDto bunnyQuotationRequestDto) {
		Long createdBunnyQuotation = bunnyQuotationService.createBunnyQuotation(bunnyQuotationRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdBunnyQuotation));
	}


	/**
	 * 번역 공고에 포함되는 번역 견적서 목록 조회
	 *
	 * @param bunnySeq
	 * @return
	 */
	// FIXME : 신청 기준 Seq를 변경해야 함
	@GetMapping("/{bunnySeq}/quotation")
	@ApiOperation(value = "번역 공고에 포함되는 번역 견적서 목록 조회")
	public ResponseEntity<ResultDto<List<BunnyQuotationResponseDto>>> getBunnyQuotationList(@PathVariable("bunnySeq") Long bunnySeq) {
		List<BunnyQuotationEntity> bunnyQuotationList = bunnyQuotationService.getBunnyQuotationList(bunnySeq);
		List<BunnyQuotationResponseDto> bunnyQuotationResponseDtoList = BunnyQuotationResponseDto.fromEntityList((bunnyQuotationList));
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyQuotationResponseDtoList));
	}


	/**
	 * 번역 공고에 포함되는 번역 견적서 상세 조회
	 *
	 * @param bunnyQuotationSeq
	 * @return
	 */
	@GetMapping("/quotation/{bunnyQuotationSeq}")
	@ApiOperation(value = "번역 견적서 상세 조회")
	public ResponseEntity<ResultDto<BunnyQuotationResponseDto>> getBunnyQuotation(@PathVariable("bunnyQuotationSeq") Long bunnyQuotationSeq) {
		BunnyQuotationEntity bunnyQuotation = bunnyQuotationService.getBunnyQuotation(bunnyQuotationSeq);
		BunnyQuotationResponseDto bunnyQuotationResponseDto = BunnyQuotationResponseDto.fromEntity(bunnyQuotation);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyQuotationResponseDto));
	}


	/**
	 * 번역 견적서 수락
	 *
	 * @param bunnyQuotationRequestDto : 수락할 견적서 seq
	 * @return : 로직 성공 여부
	 */
	@PutMapping("/{bunnySeq}/quotation/{seq}/accept")
	@ApiOperation(value = "번역 공고 신청에 수락하기")
	public ResponseEntity<ResultDto<Boolean>> acceptBunnyQuotation(@RequestBody BunnyQuotationRequestDto bunnyQuotationRequestDto) {
		
		Boolean isSuccess = bunnyQuotationService.acceptBunnyQuotation(bunnyQuotationRequestDto);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * 번역 견적서 거절
	 *
	 * @param bunnyQuotationRequestDto : 거절할 신청 seq
	 * @return : 로직 성공 여부
	 */
	@PutMapping("/{bunnySeq}/quotation/{bunnyQuotationSeq}/reject")
	@ApiOperation(value = "번역 공고 신청에 거절하기")
	public ResponseEntity<ResultDto<Boolean>> rejectBunnyQuotation(@RequestBody BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		Boolean isSuccess = bunnyQuotationService.rejectBunnyQuotation(bunnyQuotationRequestDto);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * 번역 공고 완료
	 *
	 * @param bunnyQuotationRequestDto
	 * @return
	 */
	@PutMapping("/{bunnySeq}/quotation/{bunnyQuotationSeq}/complete")
	@ApiOperation(value = "번역 공고 완료하기")
	public ResponseEntity<ResultDto<Long>> completeBunnyQuotation(@RequestBody BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		Long historySeq = bunnyQuotationService.completeBunnyQuotation(bunnyQuotationRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(historySeq));
	}

}
