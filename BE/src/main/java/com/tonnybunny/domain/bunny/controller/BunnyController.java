package com.tonnybunny.domain.bunny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiHelperRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiResponseDto;
import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
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
	 * @param bunnyNotiRequestDto : 번역 공고 폼 입력
	 * @return : 생성된 번역 공고의 seq
	 */
	@PostMapping("/noti")
	@ApiOperation(value = "번역 공고 생성입니다")
	public ResponseEntity<ResultDto<Long>> createYBunnyNoti(@RequestBody BunnyNotiRequestDto bunnyNotiRequestDto) {

		Long bunnyNotiSeq = bunnyService.createYBunnyNoti(bunnyNotiRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiSeq));
	}


	/**
	 * @param notiSeq : 삭제할 번역 공고 seq
	 * @return : 삭제 성공 여부
	 */
	@DeleteMapping("/noti/{notiSeq}")
	@ApiOperation(value = "번역 공고 삭제입니다")
	public ResponseEntity<ResultDto<Boolean>> deleteYBunnyNoti(@PathVariable("notiSeq") Long notiSeq) {

		Boolean isSuccess = bunnyService.deleteYBunnyNoti(notiSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * @param notiSeq : 조회할 번역 공고 seq
	 * @return : 조회된 번역 공고 Dto
	 */
	@GetMapping("/noti/{notiSeq}")
	@ApiOperation(value = "번역 공고 하나를 조회합니다")
	public ResponseEntity<ResultDto<BunnyNotiResponseDto>> getYBunnyNoti(@PathVariable("notiSeq") Long notiSeq) {

		BunnyNotiEntity bunnyNotiEntity = bunnyService.getYBunnyNoti(notiSeq);
		BunnyNotiResponseDto bunnyNotiResponseDto = BunnyNotiResponseDto.fromEntity(bunnyNotiEntity);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiResponseDto));
	}


	/**
	 * @param lang     : 언어코드
	 * @param category : 카테고리코드
	 * @return : 조회된 번역 공고 DtoList
	 */
	@GetMapping("/noti")
	@ApiOperation(value = "번역 공고 리스트를 조회합니다 (with Filter)")
	public ResponseEntity<ResultDto<List<BunnyNotiResponseDto>>> getYBunnyListByFilter(@RequestParam("lang") String lang, @RequestParam("category") String category) {

		List<BunnyNotiEntity> bunnyNotiList = bunnyService.getYBunnyListByFilter(lang, category);
		List<BunnyNotiResponseDto> bunnyNotiResponseDtoList = BunnyNotiResponseDto.fromEntityList(bunnyNotiList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiResponseDtoList));

	}


	/**
	 * @param bunnyNotiHelperRequestDto : 번역 공고로의 신청 정보
	 * @return : 생성된 번역 공고로의 신청 seq
	 */
	@PostMapping("/noti/request")
	@ApiOperation(value = "번역 공고에 신청 보내기")
	public ResponseEntity<ResultDto<Long>> sendYBunnyRequestFromHelper(@RequestBody BunnyNotiHelperRequestDto bunnyNotiHelperRequestDto) {

		Long bunnyNotiHelperSeq = bunnyService.sendYBunnyRequestFromHelper(bunnyNotiHelperRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyNotiHelperSeq));

	}


	/**
	 * @param requestSeq : 수락된 번역 공고로의 신청 seq
	 * @return
	 */
	@GetMapping("/noti/{requestSeq}/accept")
	@ApiOperation(value = "번역 공고 신청에 수락하기")
	public ResponseEntity<ResultDto<Boolean>> acceptYTonnyRequestFromHelper(@PathVariable("requestSeq") Long requestSeq) {

		Boolean isSuccess = bunnyService.acceptYTonnyRequestFromHelper(requestSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * @param requestSeq : 거절된 번역 공고로의 신청 seq
	 * @return
	 */
	@GetMapping("/noti/{requestSeq}/reject")
	@ApiOperation(value = "번역 공고 신청에 거절하기")
	public ResponseEntity<ResultDto<Boolean>> rejectYTonnyRequestFromHelper(@PathVariable("requestSeq") Long requestSeq) {

		Boolean isSuccess = bunnyService.rejectYTonnyRequestFromHelper(requestSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

}