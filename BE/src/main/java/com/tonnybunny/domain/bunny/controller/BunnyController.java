package com.tonnybunny.domain.bunny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.bunny.dto.*;
import com.tonnybunny.domain.bunny.entity.BunnyApplyEntity;
import com.tonnybunny.domain.bunny.entity.BunnyEntity;
import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import com.tonnybunny.domain.bunny.service.BunnyService;
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
@Api(tags = "번역 관련 API")
public class BunnyController {

	private final BunnyService bunnyService;


	/**
	 * 번역 공고 생성
	 *
	 * @param bunnyRequestDto : 번역 공고 폼 입력
	 * @return : 생성된 번역 공고의 seq
	 */
	@PostMapping
	@ApiOperation(value = "번역 공고 생성")
	public ResponseEntity<ResultDto<Long>> createBunny(@RequestBody BunnyRequestDto bunnyRequestDto) {

		Long bunnySeq = bunnyService.createBunny(bunnyRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnySeq));
	}


	/**
	 * 번역 공고 삭제
	 *
	 * @param bunnySeq : 삭제할 번역 공고 seq
	 * @return : 삭제 성공 여부
	 */
	@DeleteMapping("/{bunnySeq}")
	@ApiOperation(value = "번역 공고 삭제")
	public ResponseEntity<ResultDto<Long>> deleteBunny(@PathVariable("bunnySeq") Long bunnySeq) {

		Long resultSeq = bunnyService.deleteBunny(bunnySeq);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(resultSeq));
	}


	/**
	 * 번역 공고 상세 조회
	 *
	 * @param bunnySeq : 조회할 번역 공고 seq
	 * @return : 조회된 번역 공고 Dto
	 */
	@GetMapping("/{bunnySeq}")
	@ApiOperation(value = "번역 공고 상세 조회")
	public ResponseEntity<ResultDto<BunnyResponseDto>> getBunny(@PathVariable("bunnySeq") Long bunnySeq) {
		BunnyEntity bunnyEntity = bunnyService.getBunny(bunnySeq);
		BunnyResponseDto bunnyResponseDto = BunnyResponseDto.fromEntity(bunnyEntity);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyResponseDto));
	}


	/**
	 * 번역 공고 목록 조회 (필터기능)
	 *
	 * @param lang     : 언어코드
	 * @param category : 카테고리코드
	 * @return : 조회된 번역 공고 DtoList
	 */
	@GetMapping
	@ApiOperation(value = "번역 공고 리스트를 조회합니다 (with Filter)")
	public ResponseEntity<ResultDto<List<BunnyResponseDto>>> getBunnyListByFilter(@RequestParam("lang") String lang, @RequestParam("category") String category) {

		List<BunnyEntity> bunnyList = bunnyService.getBunnyListByFilter(lang, category);
		List<BunnyResponseDto> bunnyResponseDtoList = BunnyResponseDto.fromEntityList(bunnyList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyResponseDtoList));

	}


	/**
	 * 번역 공고 신청 생성
	 *
	 * @param bunnyApplyRequestDto : 번역 공고 신청 정보
	 * @return : 생성된 신청 seq
	 */
	@PostMapping("/{bunnySeq}/apply")
	@ApiOperation(value = "번역 공고 신청 생성")
	public ResponseEntity<ResultDto<Long>> createBunnyApply(@RequestBody BunnyApplyRequestDto bunnyApplyRequestDto) {

		Long bunnyApplySeq = bunnyService.createBunnyApply(bunnyApplyRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyApplySeq));

	}


	/**
	 * 번역 공고 신청 취소
	 *
	 * @param bunnyApplyRequestDto :취소할 신청 seq
	 * @return :로직 성공 여부
	 */
	@DeleteMapping("/{bunnySeq}/apply/{bunnyApplySeq}")
	@ApiOperation(value = "번역 공고 신청 취소")
	public ResponseEntity<ResultDto<Boolean>> deleteBunnyApply(@RequestBody BunnyApplyRequestDto bunnyApplyRequestDto) {

		Boolean isSuccess = bunnyService.deleteBunnyApply(bunnyApplyRequestDto);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * 번역 공고 신청 목록 조회
	 *
	 * @return : 번역 요청 신청 목록 Response Dto List
	 */
	@GetMapping("/{bunnySeq}/apply")
	@ApiOperation(value = "번역 공고 신청 목록 조회")
	public ResponseEntity<ResultDto<List<BunnyApplyResponseDto>>> getBunnyApplyList(@PathVariable("bunnySeq") Long bunnySeq) {

		List<BunnyApplyEntity> bunnyApplyList = bunnyService.getBunnyApplyList(bunnySeq);
		List<BunnyApplyResponseDto> bunnyApplyResponseDtoList = BunnyApplyResponseDto.fromEntityList(bunnyApplyList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyApplyResponseDtoList));
	}


	/**
	 * 번역 견적서 생성
	 *
	 * @param bunnyQuotationRequestDto
	 * @return
	 */
	@PostMapping("/{bunnySeq}/quotation")
	@ApiOperation(value = "번역 견적서 생성")
	public ResponseEntity<ResultDto<Long>> createBunnyQuotation(@RequestBody BunnyQuotationRequestDto bunnyQuotationRequestDto) {
		Long createdBunnyQuotation = bunnyService.createBunnyQuotation(bunnyQuotationRequestDto);
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
		List<BunnyQuotationEntity> bunnyQuotationList = bunnyService.getBunnyQuotationList(bunnySeq);
		List<BunnyQuotationResponseDto> bunnyQuotationResponseDtoList = BunnyQuotationResponseDto.fromEntityList((bunnyQuotationList));
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyQuotationResponseDtoList));
	}


	/**
	 * 번역 공고에 포함되는 번역 견적서 상세 조회
	 *
	 * @param bunnyQuotationRequestDto
	 * @return
	 */
	@GetMapping("/{bunnySeq}/quotation/{bunnyQuotationSeq}")
	@ApiOperation(value = "번역 견적서 상세 조회")
	public ResponseEntity<ResultDto<BunnyQuotationResponseDto>> getBunnyQuotation(@RequestBody BunnyQuotationRequestDto bunnyQuotationRequestDto) {
		BunnyQuotationEntity bunnyQuotation = bunnyService.getBunnyQuotation(bunnyQuotationRequestDto);
		BunnyQuotationResponseDto bunnyQuotationResponseDto = BunnyQuotationResponseDto.fromEntity(bunnyQuotation);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyQuotationResponseDto));
	}


	/**
	 * 번역 견적서 수락
	 *
	 * @param bunnyQuotationRequestDto : 수락할 견적서 seq
	 * @return : 로직 성공 여부
	 */
	@PutMapping("/{bunnySeq}/quotation/{bunnyQuotationSeq}/accept")
	@ApiOperation(value = "번역 공고 신청에 수락하기")

	public ResponseEntity<ResultDto<Boolean>> acceptBunnyQuotation(@RequestBody BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		Boolean isSuccess = bunnyService.acceptBunnyQuotation(bunnyQuotationRequestDto);
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

		Boolean isSuccess = bunnyService.rejectBunnyQuotation(bunnyQuotationRequestDto);
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
	public ResponseEntity<ResultDto<Boolean>> completeBunnyQuotation(@RequestBody BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		Boolean isSuccess = bunnyService.completeBunnyQuotation(bunnyQuotationRequestDto);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

}
