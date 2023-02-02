package com.tonnybunny.domain.jtonny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyApplyRequestDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyRequestDto;
import com.tonnybunny.domain.jtonny.service.JTonnyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/jtonny")
@Api(tags = "즉시 통역 관련 API")
public class JTonnyController {

	private final JTonnyService jTonnyService;


	/**
	 * 즉시 통역 공고 생성
	 * "ws://pub/jtonny/request" 로 들어오는 메세지를 처리한다.
	 *
	 * @param jTonnyRequestDto : 즉시 통역 공고 생성 폼
	 */
	@MessageMapping("/jtonny/request")
	@ApiOperation(value = "즉시통역 공고 생성 API")
	public ResponseEntity<ResultDto<Boolean>> createJTonny(@RequestBody JTonnyRequestDto jTonnyRequestDto) {

		jTonnyService.createJTonny(jTonnyRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}


	/**
	 * 즉시 통역 공고 신청
	 * "ws://pub/jtonny/apply" 로 들어오는 메세지를 처리한다.
	 *
	 * @param jTonnyApplyRequestDto : 즉시 통역 공고 신청 폼
	 */
	@MessageMapping("/jtonny/apply")
	@ApiOperation(value = "즉시통역 공고 신청 생성 API")
	public ResponseEntity<ResultDto<Boolean>> createJTonnyApply(@RequestBody JTonnyApplyRequestDto jTonnyApplyRequestDto) {

		jTonnyService.createJTonnyApply(jTonnyApplyRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/**
	 * MEMO : UPDATE
	 * 즉시 통역 공고 신청 수락
	 *
	 * @param jTonnyHelperSeq : 수락할 신청 seq
	 * @return : 로직 성공 여부
	 */
	@GetMapping("/{jTonnyHelperSeq}/accept")
	@ApiOperation(value = "즉시통역 공고 신청 수락 API", notes = "고객이 즉시통역 공고 신청을 수락한다.")
	public ResponseEntity<ResultDto<Boolean>> acceptJTonnyApply(@PathVariable("jTonnyHelperSeq") Long jTonnyHelperSeq) {

		Boolean isSuccess = jTonnyService.acceptJTonnyApply(jTonnyHelperSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * MEMO : DELETE
	 * 즉시 통역 공고 삭제
	 *
	 * @param jTonnySeq : 삭제할 공고 seq
	 * @return : 로직 성공 여부
	 */
	@DeleteMapping("/{jTonnySeq}")
	@ApiOperation(value = "즉시통역 공고 삭제 API", notes = "고객이 올린 즉시통역 공고를 삭제한다.")
	public ResponseEntity<ResultDto<Boolean>> deleteJTonny(@PathVariable("jTonnySeq") Long jTonnySeq) {

		Boolean isSuccess = jTonnyService.deleteJTonny(jTonnySeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}


	/**
	 * 즉시 통역 공고 신청 삭제
	 *
	 * @param jTonnyHelperSeq : 삭제할 신청 seq
	 * @return : 로직 성공 여부
	 */
	@DeleteMapping("/enroll/{jTonnyHelperSeq}")
	@ApiOperation(value = "즉시통역 공고 신청 삭제 API", notes = "헬퍼가 즉시통역 공고 신청을 삭제한다.")
	public ResponseEntity<ResultDto<Boolean>> deleteJTonnyApply(@PathVariable("jTonnyHelperSeq") Long jTonnyHelperSeq) {

		Boolean isSuccess = jTonnyService.deleteJTonnyApply(jTonnyHelperSeq);
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
	@ApiOperation(value = "즉시통역 공고 신청 거절 API", notes = "고객이 들어온 즉시통역 공고 신청을 거절한다.")
	public ResponseEntity<ResultDto<Boolean>> rejectJTonnyApply(@PathVariable("jTonnyHelperSeq") Long jTonnyHelperSeq) {

		Boolean isSuccess = jTonnyService.rejectJTonnyApply(jTonnyHelperSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	}

	/**
	 * MEMO : READ
	 * 즉시 통역 공고 목록 조회
	 *
	 * @return 즉시 통역 공고 Response Dto List
	 */
	/*
	@GetMapping
	@ApiOperation(value = "즉시통역 목록 조회 API")
	public ResponseEntity<ResultDto<List<JTonnyResponseDto>>> getJTonnyList() {

		List<JTonnyEntity> jTonnyList = jTonnyService.getJTonnyList();
		List<JTonnyResponseDto> jTonnyResponseDtoList = JTonnyResponseDto.fromEntityList(jTonnyList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyResponseDtoList));
	} */

	/**
	 * 즉시 통역 공고 상세 조회
	 *
	 * @param jTonnySeq : 조회할 공고 seq
	 * @return : 조회된 공고 Response Dto
	 */
	/*
	@GetMapping("/{jTonnySeq}")
	@ApiOperation(value = "즉시통역 공고 목록 상세 조회 API")
	public ResponseEntity<ResultDto<JTonnyResponseDto>> getJTonnyDetail(@PathVariable("jTonnySeq") Long jTonnySeq) {

		JTonnyEntity jTonny = jTonnyService.getJTonnyDetail(jTonnySeq);
		JTonnyResponseDto jTonnyResponseDto = JTonnyResponseDto.fromEntity(jTonny);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyResponseDto));
	} */

	/**
	 * 즉시 통역 공고 신청 목록 조회
	 *
	 * @return : 즉시 통역 공고 신청 Response Dto List
	 */
	/*
	@GetMapping("/enroll")
	@ApiOperation(value = "즉시통역 공고 신청 목록 조회 API")
	public ResponseEntity<ResultDto<List<JTonnyApplyResponseDto>>> getJTonnyApplyList() {

		List<JTonnyApplyEntity> jTonnyApplyList = jTonnyService.getJTonnyApplyList();
		List<JTonnyApplyResponseDto> jTonnyApplyResponseDtos = JTonnyApplyResponseDto.fromEntityList(jTonnyApplyList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyApplyResponseDtos));
	} */

	/**
	 * 즉시 통역 공고 신청 목록 조회
	 *
	 * @param jTonnyHelperSeq : 조회할 신청 seq
	 * @return : 조회된 신청 Response Dto
	 */
	/*
	@GetMapping("/enroll/{jTonnyHelperSeq}")
	@ApiOperation(value = "즉시통역 공고 신청 목록 상세 조회 API")
	public ResponseEntity<ResultDto<JTonnyApplyResponseDto>> getJTonnyApplyDetail(@PathVariable("jTonnyHelperSeq") Long jTonnyHelperSeq) {

		JTonnyApplyEntity jTonnyApply = jTonnyService.getJTonnyApplyDetail(jTonnyHelperSeq);
		JTonnyApplyResponseDto jTonnyApplyResponseDto = JTonnyApplyResponseDto.fromEntity(jTonnyApply);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(jTonnyApplyResponseDto));
	} */
	
}
