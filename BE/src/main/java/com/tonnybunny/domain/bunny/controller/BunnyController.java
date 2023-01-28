package com.tonnybunny.domain.bunny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiHelperRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiHelperResponseDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiResponseDto;
import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import com.tonnybunny.domain.bunny.entity.BunnyNotiHelperEntity;
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
	 * @param bunnyNotiRequestDto : 번역 공고 폼 입력
	 * @return : 생성된 번역 공고의 seq
	 */
	@PostMapping
	@ApiOperation(value = "번역 공고 생성")
	public ResponseEntity<ResultDto<Long>> createBunnyNoti(@RequestBody BunnyNotiRequestDto bunnyNotiRequestDto) {

		Long bunnyNotiSeq = bunnyService.createBunnyNoti(bunnyNotiRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiSeq));
	}


	/**
	 * 번역 공고 삭제
	 *
	 * @param bunnyNotiSeq : 삭제할 번역 공고 seq
	 * @return : 삭제 성공 여부
	 */
	@DeleteMapping("/{bunnyNotiSeq}")
	@ApiOperation(value = "번역 공고 삭제")
	public ResponseEntity<ResultDto<Boolean>> deleteBunnyNoti(@PathVariable("bunnyNotiSeq") Long bunnyNotiSeq) {

		Boolean isSuccess = bunnyService.deleteBunnyNoti(bunnyNotiSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * 번역 공고 상세 조회
	 *
	 * @param bunnyNotiSeq : 조회할 번역 공고 seq
	 * @return : 조회된 번역 공고 Dto
	 */
	@GetMapping("/{bunnyNotiSeq}")
	@ApiOperation(value = "번역 공고 상세 조회")
	public ResponseEntity<ResultDto<BunnyNotiResponseDto>> getBunnyNoti(@PathVariable("bunnyNotiSeq") Long bunnyNotiSeq) {

		BunnyNotiEntity bunnyNotiEntity = bunnyService.getBunnyNoti(bunnyNotiSeq);
		BunnyNotiResponseDto bunnyNotiResponseDto = BunnyNotiResponseDto.fromEntity(bunnyNotiEntity);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiResponseDto));
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
	public ResponseEntity<ResultDto<List<BunnyNotiResponseDto>>> getBunnyListByFilter(@RequestParam("lang") String lang, @RequestParam("category") String category) {

		List<BunnyNotiEntity> bunnyNotiList = bunnyService.getBunnyListByFilter(lang, category);
		List<BunnyNotiResponseDto> bunnyNotiResponseDtoList = BunnyNotiResponseDto.fromEntityList(bunnyNotiList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiResponseDtoList));

	}


	/**
	 * 번역 공고 신청 생성
	 *
	 * @param bunnyNotiHelperRequestDto : 번역 공고 신청 정보
	 * @return : 생성된 신청 seq
	 */
	@PostMapping("/enroll")
	@ApiOperation(value = "번역 공고 신청 생성")
	public ResponseEntity<ResultDto<Long>> createBunnyNotiHelper(@RequestBody BunnyNotiHelperRequestDto bunnyNotiHelperRequestDto) {

		Long bunnyNotiHelperSeq = bunnyService.createBunnyNotiHelper(bunnyNotiHelperRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiHelperSeq));

	}


	/**
	 * 번역 공고 신청 취소
	 *
	 * @param bunnyNotiHelperSeq : 취소할 신청 seq
	 * @return : 로직 성공 여부
	 */
	@DeleteMapping("/enroll/{bunnyNotiHelperSeq}")
	@ApiOperation(value = "번역 공고 신청 취소")
	public ResponseEntity<ResultDto<Boolean>> deleteBunnyNotiHelper(@PathVariable("bunnyNotiHelperSeq") Long bunnyNotiHelperSeq) {

		Boolean isSuccess = bunnyService.deleteBunnyNotiHelper(bunnyNotiHelperSeq);
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
	@GetMapping("/enroll")
	@ApiOperation(value = "번역 공고 신청 목록 조회")
	public ResponseEntity<ResultDto<List<BunnyNotiHelperResponseDto>>> getBunnyNotiHelperList() {

		List<BunnyNotiHelperEntity> bunnyNotiHelperList = bunnyService.getBunnyNotiHelperList();
		List<BunnyNotiHelperResponseDto> bunnyNotiHelperResponseDtoList = BunnyNotiHelperResponseDto.fromEntityList(bunnyNotiHelperList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiHelperResponseDtoList));
	}


	/**
	 * 번역 공고 신청 상세 조회
	 *
	 * @param bunnyNotiHelperSeq : 조회할 신청 seq
	 * @return : 조회된 신청 Response Dto
	 */
	@GetMapping("/enroll/{bunnyNotiHelperSeq}")
	@ApiOperation(value = "번역 공고 신청 상세 조회")
	public ResponseEntity<ResultDto<BunnyNotiHelperResponseDto>> getBunnyNotiHelper(@PathVariable("bunnyNotiHelperSeq") Long bunnyNotiHelperSeq) {

		BunnyNotiHelperEntity bunnyNotiHelper = bunnyService.getBunnyNotiHelper(bunnyNotiHelperSeq);
		BunnyNotiHelperResponseDto bunnyNotiHelperResponseDto = BunnyNotiHelperResponseDto.fromEntity(bunnyNotiHelper);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiHelperResponseDto));
	}


	/**
	 * 번역 공고 신청 수락
	 *
	 * @param bunnyNotiHelperSeq : 수락할 신청 seq
	 * @return : 로직 성공 여부
	 */
	@GetMapping("/{bunnyNotiHelperSeq}/accept")
	@ApiOperation(value = "번역 공고 신청에 수락하기")

	public ResponseEntity<ResultDto<Boolean>> acceptBunnyNotiHelper(@PathVariable("bunnyNotiHelperSeq") Long bunnyNotiHelperSeq) {

		Boolean isSuccess = bunnyService.acceptBunnyNotiHelper(bunnyNotiHelperSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * 번역 공고 신청 거절
	 *
	 * @param bunnyNotiHelperSeq : 거절할 신청 seq
	 * @return : 로직 성공 여부
	 */
	@GetMapping("/{bunnyNotiHelperSeq}/reject")
	@ApiOperation(value = "번역 공고 신청에 거절하기")
	public ResponseEntity<ResultDto<Boolean>> rejectBunnyNotiHelper(@PathVariable("bunnyNotiHelperSeq") Long bunnyNotiHelperSeq) {

		Boolean isSuccess = bunnyService.rejectBunnyNotiHelper(bunnyNotiHelperSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

}
