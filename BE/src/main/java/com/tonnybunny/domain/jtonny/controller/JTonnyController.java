package com.tonnybunny.domain.jtonny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyDto;
import com.tonnybunny.domain.jtonny.service.JTonnyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/jtonny")
@Api(tags = "즉시 통역 관련 API")
public class JTonnyController {

	private final JTonnyService jTonnyService;

	/* client */


	/**
	 * 즉시통역 공고 생성
	 * "ws://pub/jtonny/request" 로 들어오는 메세지를 처리한다.
	 * 1. 해당 언어를 구독중인 helper 에게 공고를 띄워야 한다.
	 *
	 * @param jTonnyDto : 즉시통역 공고 생성 폼의 정보가 담긴 JTonnyDto
	 */
	@MessageMapping("/jtonny/request")
	@ApiOperation(value = "즉시통역 공고 생성 API")
	public ResponseEntity<ResultDto<Boolean>> createJTonny(@RequestBody JTonnyDto jTonnyDto) {
		jTonnyService.createJTonny(jTonnyDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}


	/**
	 * 즉시통역 공고 생성 취소
	 * "ws://pub/jtonny/request/cancel" 로 들어오는 메세지를 처리한다.
	 * 1. 해당 언어를 구독중인 helper 의 공고 목록에서 공고를 제거해야 한다.
	 *
	 * @param jTonnyDto : 즉시통역 공고를 생성한 고객의 pk 와 언어 (헬퍼는 해당 정보를 key 값으로 공고 목록을 관리한다고 가정)
	 */
	@MessageMapping("/jtonny/request/cancel")
	@ApiOperation(value = "즉시통역 공고 생성 취소 API")
	public ResponseEntity<ResultDto<Boolean>> deleteJTonny(@RequestBody JTonnyDto jTonnyDto) {
		jTonnyService.deleteJTonny(jTonnyDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/**
	 * 즉시 통역 공고 신청 수락
	 * "ws://pub/jtonny/accept" 로 들어오는 메세지를 처리한다.
	 * 1. 해당 언어를 구독중인 helper 의 공고 목록에서 공고를 제거해야 한다. (혹은 공고 상태를 '완료됨'으로 변경)
	 * 2. JTonnyDto 를 바탕으로 JTonnyEntity 를 생성하고 DB 에 저장해야 한다.
	 * 3. 즉시통역을 위한 화상채팅 방으로 이동해야 한다. (혹은 이동 링크 제공, 페이지를 벗어나게 되면 헬퍼의 구독은 모두 해제됨)
	 *
	 * @param jTonnyDto : 최종적으로 완성된 JTonnyDto
	 */
	@MessageMapping("/jtonny/accept")
	@ApiOperation(value = "즉시통역 공고 신청 수락 API", notes = "고객이 즉시통역 공고 신청을 수락한다.")
	public ResponseEntity<ResultDto<Boolean>> acceptJTonnyApply(@RequestBody JTonnyDto jTonnyDto) {
		jTonnyService.acceptJTonnyApply(jTonnyDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/**
	 * 즉시 통역 공고 신청 거절
	 * "ws://pub/jtonny/reject" 로 들어오는 메세지를 처리한다.
	 * 1. 해당 신청을 client 의 헬퍼 목록에서 제거해야 한다. (클라이언트가 하면 됨)
	 * 2. 해당 신청을 보낸 helper 에게 신청이 거절되었음을 알려야 한다.
	 * 3. 해당 신청을 보낸 helper 의 공고 목록에서 공고를 제거해야 한다. (혹은 공고 상태를 '완료됨'으로 변경)
	 *
	 * @param jTonnyDto : 즉시통역 공고를 신청한 헬퍼의 pk 와 생성한 고객의 pk (헬퍼는 해당 정보를 key 값으로 공고 목록을 관리한다고 가정)
	 */
	@MessageMapping("/jtonny/reject")
	@ApiOperation(value = "즉시통역 공고 신청 거절 API", notes = "고객이 들어온 즉시통역 공고 신청을 거절한다.")
	public ResponseEntity<ResultDto<Boolean>> rejectJTonnyApply(@RequestBody JTonnyDto jTonnyDto) {
		jTonnyService.rejectJTonnyApply(jTonnyDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/* helper */


	/**
	 * 즉시통역 공고 신청
	 * "ws://pub/jtonny/apply" 로 들어오는 메세지를 처리한다.
	 * 1. 해당 공고를 생성한 client 에게 helper 의 정보를 띄워야 한다.
	 *
	 * @param jTonnyDto : 고객이 전송한 정보에서 헬퍼의 pk 와 단가가 추가된 JTonnyDto
	 */
	@MessageMapping("/jtonny/apply")
	@ApiOperation(value = "즉시통역 공고 신청 API")
	public ResponseEntity<ResultDto<Boolean>> createJTonnyApply(@RequestBody JTonnyDto jTonnyDto) {
		jTonnyService.createJTonnyApply(jTonnyDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}


	/**
	 * 즉시통역 공고 신청 취소
	 * "ws://pub/jtonny/apply/cancel" 로 들어오는 메세지를 처리한다.
	 * 1. 해당 공고를 생성한 client 의 헬퍼 목록에서 헬퍼를 지워야 한다.
	 *
	 * @param jTonnyDto : 즉시통역 공고를 신청한 헬퍼의 pk 와 생성한 고객의 pk (고객은 해당 정보를 key 값으로 헬퍼 목록을 관리한다고 가정)
	 */
	@MessageMapping("/jtonny/apply/cancel")
	@ApiOperation(value = "즉시통역 공고 신청 취소 API")
	public ResponseEntity<ResultDto<Boolean>> deleteJTonnyApply(@RequestBody JTonnyDto jTonnyDto) {
		jTonnyService.deleteJTonnyApply(jTonnyDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
	}

	/**
	 * MEMO : DELETE
	 * 즉시 통역 공고 삭제
	 *
	 * @param jTonnySeq : 삭제할 공고 seq
	 * @return : 로직 성공 여부
	 */
	/*
	@DeleteMapping("/{jTonnySeq}")
	@ApiOperation(value = "즉시통역 공고 삭제 API", notes = "고객이 올린 즉시통역 공고를 삭제한다.")
	public ResponseEntity<ResultDto<Boolean>> deleteJTonny(@PathVariable("jTonnySeq") Long jTonnySeq) {

		Boolean isSuccess = jTonnyService.deleteJTonny(jTonnySeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	} */

	/**
	 * 즉시 통역 공고 신청 삭제
	 *
	 * @param jTonnyHelperSeq : 삭제할 신청 seq
	 * @return : 로직 성공 여부
	 */
	/*
	@DeleteMapping("/enroll/{jTonnyHelperSeq}")
	@ApiOperation(value = "즉시통역 공고 신청 삭제 API", notes = "헬퍼가 즉시통역 공고 신청을 삭제한다.")
	public ResponseEntity<ResultDto<Boolean>> deleteJTonnyApply(@PathVariable("jTonnyHelperSeq") Long jTonnyHelperSeq) {

		Boolean isSuccess = jTonnyService.deleteJTonnyApply(jTonnyHelperSeq);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofFail());
		}
	} */

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
