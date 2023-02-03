//package com.tonnybunny.domain.bunny.controller;
//
//
//import com.tonnybunny.common.dto.ResultDto;
//import com.tonnybunny.domain.bunny.dto.BunnyQuotationRequestDto;
//import com.tonnybunny.domain.bunny.dto.BunnyQuotationResponseDto;
//import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
//import com.tonnybunny.domain.bunny.service.BunnyQuotationService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//// FIXME :BunnyController로 합칠 수도 있음
//@RestController
//@RequestMapping("/bunny/quotation")
//@RequiredArgsConstructor
//@Api(tags = "번역 견적서 관련 API")
//public class BunnyQuotationController {
//
//	private final BunnyQuotationService bunnyQuotationService;
//
//
//	@PostMapping
//	@ApiOperation(value = "번역 견적서 생성")
//	public ResponseEntity<ResultDto<Long>> createBunnyQuotation(@RequestBody BunnyQuotationRequestDto bunnyQuotationRequestDto) {
//		Long createdBunnyQuotation = bunnyQuotationService.createBunnyQuotation(bunnyQuotationRequestDto);
//		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(createdBunnyQuotation));
//	}
//
//
//	// FIXME : 신청 기준 Seq를 변경해야 함
//	@GetMapping("/{bunnySeq}")
//	@ApiOperation(value = "번역 공고에 포함되는 번역 견적서 목록 조회")
//	public ResponseEntity<ResultDto<List<BunnyQuotationResponseDto>>> getBunnyQuotationList(@PathVariable("bunnySeq") Long bunnySeq) {
//		List<BunnyQuotationEntity> bunnyQuotationList = bunnyQuotationService.getBunnyQuotationList(bunnySeq);
//		List<BunnyQuotationResponseDto> bunnyQuotationResponseDtoList = BunnyQuotationResponseDto.fromEntityList((bunnyQuotationList));
//		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyQuotationResponseDtoList));
//	}
//
//
//	@GetMapping("/{bunnyQuotationSeq}")
//	@ApiOperation(value = "번역 견적서 조회")
//	public ResponseEntity<ResultDto<BunnyQuotationResponseDto>> getBunnyQuotation(@PathVariable("bunnyQuotationSeq") Long bunnyQuotationSeq) {
//		BunnyQuotationEntity bunnyQuotation = bunnyQuotationService.getBunnyQuotation(bunnyQuotationSeq);
//		BunnyQuotationResponseDto bunnyQuotationResponseDto = BunnyQuotationResponseDto.fromEntity(bunnyQuotation);
//		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyQuotationResponseDto));
//	}
//
//
//	@PutMapping("/type/{bunnyQuotationSeq}")
//	@ApiOperation(value = "번역 견적서의 상태 코드를 수정", notes = "견적서의 상태 (미선택/선택/작업완료)를 변경합니다.")
//	public ResponseEntity<ResultDto<Boolean>> modifyBunnyQuotationType(@PathVariable("bunnyQuotationSeq") Long bunnyQuotationSeq, @RequestBody String bunnyQuotationStateCode) {
//		bunnyQuotationService.modifyBunnyQuotationType(bunnyQuotationSeq, bunnyQuotationStateCode);
//		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
//	}
//
//}
