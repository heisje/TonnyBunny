package com.tonnybunny.domain.jtonny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyNotiHelperRequestDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyNotiHelperResponseDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyNotiRequestDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyNotiResponseDto;
import com.tonnybunny.domain.jtonny.entity.JTonnyNotiEntity;
import com.tonnybunny.domain.jtonny.entity.JTonnyNotiHelperEntity;
import com.tonnybunny.domain.jtonny.service.JTonnyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jtonny")
@RequiredArgsConstructor
@Api(tags = "즉시 통역 관련 API")
public class JTonnyController {

	private final JTonnyService jTonnyService;

	// --------------------- 즉시 통역 공고 ---------------------


	/**
	 * 즉시 통역 공고 생성
	 *
	 * @param jTonnyNotiRequestDto : 즉시 통역 공고 생성 폼
	 * @return : 생성된 공고 seq
	 */
	@PostMapping
	@ApiOperation(value = "즉시 통역 공고 생성")
	public ResponseEntity<ResultDto<Long>> createJTonnyNoti(@RequestBody JTonnyNotiRequestDto jTonnyNotiRequestDto) {

		Long jTonnyNotiSeq = jTonnyService.createJTonnyNoti(jTonnyNotiRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyNotiSeq));
	}


	/**
	 * 즉시 통역 공고 삭제
	 *
	 * @param jTonnySeq : 삭제할 공고 seq
	 * @return : 로직 성공 여부
	 */
	@DeleteMapping("/{jTonnySeq}")
	@ApiOperation(value = "즉시 통역 공고 삭제")
	public ResponseEntity<ResultDto<Boolean>> deleteJTonnyNoti(@PathVariable("jTonnySeq") Long jTonnySeq) {

		Boolean isSuccess = jTonnyService.deleteJTonnyNoti(jTonnySeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * 즉시 통역 공고 목록 조회
	 *
	 * @return 즉시 통역 공고 Response Dto List
	 */
	@GetMapping
	@ApiOperation(value = "즉시 통역 공고 목록 조회")
	public ResponseEntity<ResultDto<List<JTonnyNotiResponseDto>>> getJTonnyNotiList() {

		List<JTonnyNotiEntity> jTonnyNotiList = jTonnyService.getJTonnyNotiList();
		List<JTonnyNotiResponseDto> jTonnyNotiResponseDtoList = JTonnyNotiResponseDto.fromEntityList(jTonnyNotiList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyNotiResponseDtoList));
	}


	/**
	 * 즉시 통역 공고 상세 조회
	 *
	 * @param jTonnySeq : 조회할 공고 seq
	 * @return : 조회된 공고 Response Dto
	 */
	@GetMapping("/{jTonnySeq}")
	@ApiOperation(value = "즉시 통역 공고 상세 조회")
	public ResponseEntity<ResultDto<JTonnyNotiResponseDto>> getJTonnyNoti(@PathVariable("jTonnySeq") Long jTonnySeq) {

		JTonnyNotiEntity jTonnyNoti = jTonnyService.getJTonnyNoti(jTonnySeq);
		JTonnyNotiResponseDto jTonnyNotiResponseDto = JTonnyNotiResponseDto.fromEntity(jTonnyNoti);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyNotiResponseDto));
	}

	// --------------------- 즉시 통역 공고 신청(헬퍼) ---------------------


	/**
	 * 즉시 통역 공고 신청 생성
	 *
	 * @param jTonnyNotiHelperRequestDto : 즉시 통역 공고 신청 폼
	 * @return : 신청된 즉시 통역 공고 신청의 seq
	 */
	@PostMapping("/enroll")
	@ApiOperation(value = "즉시 통역 공고 신청 생성")
	public ResponseEntity<ResultDto<Long>> createJTonnyNotiHelper(@RequestBody JTonnyNotiHelperRequestDto jTonnyNotiHelperRequestDto) {

		Long jTonnyNotiHelperSeq = jTonnyService.createJTonnyNotiHelper(jTonnyNotiHelperRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyNotiHelperSeq));
	}


	/**
	 * 즉시 통역 공고 신청 삭제
	 *
	 * @param jTonnyHelperSeq : 삭제할 신청 seq
	 * @return : 로직 성공 여부
	 */
	@DeleteMapping("/enroll/{jTonnyHelperSeq}")
	@ApiOperation(value = "즉시 통역 공고 신청 삭제")
	public ResponseEntity<ResultDto<Boolean>> deleteJTonnyNotiHelper(@PathVariable("jTonnyHelperSeq") Long jTonnyHelperSeq) {

		Boolean isSuccess = jTonnyService.deleteJTonnyNotiHelper(jTonnyHelperSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

	// --------------------- 즉시 통역 공고 신청 응답(고객) ---------------------


	/**
	 * 즉시 통역 공고 신청 목록 조회
	 *
	 * @return : 즉시 통역 공고 신청 Response Dto List
	 */
	@GetMapping("/enroll")
	@ApiOperation(value = "즉시 통역 공고 신청 목록 조회")
	public ResponseEntity<ResultDto<List<JTonnyNotiHelperResponseDto>>> getJTonnyNotiHelperList() {

		List<JTonnyNotiHelperEntity> jTonnyNotiHelperList = jTonnyService.getJTonnyNotiHelperList();
		List<JTonnyNotiHelperResponseDto> jTonnyNotiHelperResponseDtos = JTonnyNotiHelperResponseDto.fromEntityList(jTonnyNotiHelperList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyNotiHelperResponseDtos));
	}


	/**
	 * 즉시 통역 공고 신청 목록 조회
	 *
	 * @param jTonnyHelperSeq : 조회할 신청 seq
	 * @return : 조회된 신청 Response Dto
	 */
	@GetMapping("/enroll/{jTonnyHelperSeq}")
	@ApiOperation(value = "즉시 통역 공고 신청 상세 조회")
	public ResponseEntity<ResultDto<JTonnyNotiHelperResponseDto>> getJTonnyNotiHelper(@PathVariable("jTonnyHelperSeq") Long jTonnyHelperSeq) {

		JTonnyNotiHelperEntity jTonnyNotiHelper = jTonnyService.getJTonnyNotiHelper(jTonnyHelperSeq);
		JTonnyNotiHelperResponseDto jTonnyNotiHelperResponseDto = JTonnyNotiHelperResponseDto.fromEntity(jTonnyNotiHelper);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyNotiHelperResponseDto));
	}


	/**
	 * 즉시 통역 공고 신청 수락
	 *
	 * @param jTonnyHelperSeq : 수락할 신청 seq
	 * @return : 로직 성공 여부
	 */
	@GetMapping("/{jTonnyHelperSeq}/accept")
	@ApiOperation(value = "즉시 통역 공고 신청 수락")
	public ResponseEntity<ResultDto<Boolean>> acceptJTonnyRequestFromHelper(@PathVariable("jTonnyHelperSeq") Long jTonnyHelperSeq) {

		Boolean isSuccess = jTonnyService.acceptJTonnyRequestFromHelper(jTonnyHelperSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * 즉시 통역 공고 신청 거절
	 *
	 * @param jTonnyHelperSeq : 거절할 신청 seq
	 * @return : 로직 성공 여부
	 */
	@GetMapping("/{jTonnyHelperSeq}/reject")
	@ApiOperation(value = "즉시 통역 공고 신청 거절")
	public ResponseEntity<ResultDto<Boolean>> rejectJTonnyRequestFromHelper(@PathVariable("jTonnyHelperSeq") Long jTonnyHelperSeq) {

		Boolean isSuccess = jTonnyService.rejectJTonnyRequestFromHelper(jTonnyHelperSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

}
