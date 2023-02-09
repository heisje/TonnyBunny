package com.tonnybunny.domain.ytonny.controller;


import com.tonnybunny.common.dto.*;
import com.tonnybunny.domain.user.service.UserService;
import com.tonnybunny.domain.ytonny.dto.YTonnyApplyRequestDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyApplyResponseDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyRequestDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyResponseDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyApplyEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyEntity;
import com.tonnybunny.domain.ytonny.service.YTonnyService;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ytonny")
@RequiredArgsConstructor
@Api(tags = "예약통역 관련 API")
public class YTonnyController {

	private final UserService userService;
	private final YTonnyService yTonnyService;


	/**
	 * MEMO : CREATE
	 * MARK : 예약통역 공고를 생성
	 *
	 * @param yTonnyRequestDto : 예약통역 공고 관련 정보
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
	 * MARK : (헬퍼가) 예약통역 공고에 신청을 생성
	 * 즉슨, 예약통역 한다고 신청한다는 말..
	 *
	 * @param yTonnyApplyRequestDto : 헬퍼 seq, 해당 공고 seq, 견적 금액
	 * @return 생성된 예약통역 공고 신청 seq
	 */
	@PostMapping("/{yTonnySeq}/apply")
	@ApiOperation(value = "예약통역 공고 신청 생성 API", notes = "헬퍼가 예약통역 공고에 신청한다.")
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
	 * @param yTonnyRequestDto : page, size
	 * @return List<YTonnyResponseDto> : 예약통역 공고 목록
	 */
	@GetMapping
	@ApiOperation(value = "예약통역 공고 목록 조회 API", notes = "사용자가 예약통역 목록을 조회한다.")
	public ResponseEntity<ResultDto<List<YTonnyResponseDto>>> getYTonnyList(YTonnyRequestDto yTonnyRequestDto) {

		System.out.println("YTonnyController.getYTonnyList");
		System.out.println("yTonnyRequestDto = " + yTonnyRequestDto);

		// service
		List<YTonnyEntity> yTonnyList = yTonnyService.getYTonnyList(yTonnyRequestDto);

		// entity -> dto
		List<YTonnyResponseDto> yTonnyResponseDtoList = YTonnyResponseDto.fromEntityList(yTonnyList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyResponseDtoList));

	}


	/**
	 * MARK : 예약통역 공고 목록을 상세 조회
	 *
	 * @param yTonnySeq : 해당 조회 공고 seq
	 * @return YTonnyResponseDto : 예약통역 상세 정보
	 */
	@GetMapping("/{yTonnySeq}")
	@ApiOperation(value = "예약통역 공고 목록 상세 조회 API", notes = "사용자가 예약통역 공고를 상세 조회한다.")
	public ResponseEntity<ResultDto<YTonnyResponseDto>> getYTonnyDetail(@PathVariable Long yTonnySeq) {

		System.out.println("YTonnyController.getYTonnyDetail");

		if (yTonnySeq == null) throw new CustomException(ErrorCode.ID_IS_NULL);

		// service
		YTonnyEntity yTonnyEntity = yTonnyService.getYTonnyDetail(yTonnySeq);

		// entity -> dto
		YTonnyResponseDto yTonnyResponseDto = YTonnyResponseDto.fromEntity(yTonnyEntity);

		// code name 값으로 변경해서 넘겨주기
		yTonnyResponseDto.setStartLangCode(LangCodeEnum.valueOfCode(yTonnyResponseDto.getStartLangCode()).name());
		yTonnyResponseDto.setEndLangCode(LangCodeEnum.valueOfCode(yTonnyResponseDto.getEndLangCode()).name());
		yTonnyResponseDto.setTonnySituCode(TonnySituCodeEnum.valueOfCode(yTonnyResponseDto.getTonnySituCode()).name());
		yTonnyResponseDto.setTaskCode(TaskCodeEnum.valueOfCode(yTonnyResponseDto.getTaskCode()).name());
		yTonnyResponseDto.setTaskStateCode(TaskStateCodeEnum.valueOfCode(yTonnyResponseDto.getTaskStateCode()).name());

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyResponseDto));

	}


	@GetMapping("/count")
	@ApiOperation(value = "예약통역 공고 목록 총 갯수 조회 API", notes = "공고 목록 총 개수를 조회한다.")
	public ResponseEntity<ResultDto<Long>> getYTonnyListTotalCount() {

		Long totalCount = yTonnyService.getYTonnyListTotalCount();

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(totalCount));

	}


	@GetMapping("/{yTonnySeq}/apply/count")
	@ApiOperation(value = "예약통역 공고 신청 목록 총 갯수 조회 API", notes = "공고 신청 목록 총 개수를 조회한다.")
	public ResponseEntity<ResultDto<Long>> getYTonnyApplyListTotalCount(@PathVariable Long yTonnySeq) {

		Long totalCount = yTonnyService.getYTonnyApplyListTotalCount(yTonnySeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(totalCount));

	}


	/**
	 * MARK : 예약통역 공고 신청 목록을 조회 with pagination
	 *
	 * @param yTonnySeq             : 조회하려는 대상 예약통역 공고 seq
	 * @param yTonnyApplyRequestDto : pagination
	 * @return 생성된 예약통역 공고 seq
	 */
	@GetMapping("/{yTonnySeq}/apply")
	@ApiOperation(value = "예약통역 신청 목록 조회 API", notes = "고객이 해당 공고의 신청 목록을 조회한다.")
	public ResponseEntity<ResultDto<List<YTonnyApplyResponseDto>>> getYTonnyApplyList(@PathVariable Long yTonnySeq,
	                                                                                  YTonnyApplyRequestDto yTonnyApplyRequestDto
	) {

		System.out.println("YTonnyController.getYTonnyApplyList");

		if (yTonnySeq == null) throw new CustomException(ErrorCode.ID_IS_NULL);

		// service
		List<YTonnyApplyEntity> yTonnyApplyList = yTonnyService.getYTonnyApplyList(yTonnySeq, yTonnyApplyRequestDto);

		// entity -> dto
		List<YTonnyApplyResponseDto> yTonnyApplyResponseDtoList = YTonnyApplyResponseDto.fromEntityList(yTonnyApplyList);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(yTonnyApplyResponseDtoList));

	}


	/**
	 * MEMO : UPDATE
	 * MARK : 예약통역 공고를 수정
	 *
	 * @param yTonnyRequestDto : 수정 예약통역 공고 내용
	 * @return 수정된 예약통역 공고 seq
	 */
	@PutMapping("/{yTonnySeq}")
	@ApiOperation(value = "예약통역 공고 수정 API", notes = "고객이 예약통역을 수정한다.")
	public ResponseEntity<ResultDto<Long>> modifyYTonny(@RequestBody YTonnyRequestDto yTonnyRequestDto) {

		System.out.println("YTonnyController.modifyYTonny");
		System.out.println("yTonnyRequestDto = " + yTonnyRequestDto);

		if (yTonnyRequestDto.getYTonnySeq() == null) throw new CustomException(ErrorCode.ID_IS_NULL);

		// service
		Long updatedSeq = yTonnyService.modifyYTonny(yTonnyRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedSeq));

	}


	/**
	 * MARK : 헬퍼의 예약통역 신청을 수락
	 *
	 * @param yTonnyApplyRequestDto : yTonnySeq, helperSeq
	 * @return 생성된 예약통역 공고 seq
	 */
	@PutMapping("/{yTonnySeq}/apply/{yTonnyApplySeq}/accept")
	@ApiOperation(value = "예약통역 신청 수락 API", notes = "고객이 헬퍼의 예약통역 신청을 수락한다.")
	public ResponseEntity<ResultDto<Long>> acceptYTonnyApply(@RequestBody YTonnyApplyRequestDto yTonnyApplyRequestDto) {

		System.out.println("YTonnyController.acceptYTonnyApply");
		System.out.println("yTonnyApplyRequestDto = " + yTonnyApplyRequestDto);

		// service
		Long updatedSeq = yTonnyService.acceptYTonnyApply(yTonnyApplyRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(updatedSeq));

	}


	/**
	 * MEMO : DELETE
	 * MARK : 예약통역 공고를 삭제
	 *
	 * @param yTonnySeq : 삭제할 대상 공고 seq
	 * @return 공고 삭제 여부
	 */
	@DeleteMapping("/{yTonnySeq}")
	@ApiOperation(value = "예약통역 공고 삭제 API", notes = "고객이 예약통역을 삭제한다.")
	public ResponseEntity<ResultDto<Long>> deleteYTonny(@PathVariable Long yTonnySeq) {

		System.out.println("YTonnyController.deleteYTonny");

		// service
		Long isDeleted = yTonnyService.deleteYTonny(yTonnySeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(isDeleted));

	}


	/**
	 * MARK : (헬퍼가) 예약통역 공고 신청을 취소
	 *
	 * @param yTonnyApplySeq : 공고 신청한 seq
	 * @return 공고 취소 여부
	 */
	@DeleteMapping("/{yTonnySeq}/apply/{yTonnyApplySeq}")
	@ApiOperation(value = "예약통역 공고 신청 취소 API", notes = "헬퍼가 예약통역 신청을 취소한다.")
	public ResponseEntity<ResultDto<Boolean>> deleteYTonnyApply(@PathVariable Long yTonnyApplySeq) {

		System.out.println("YTonnyController.deleteYTonnyApply");

		// service
		yTonnyService.deleteYTonnyApply(yTonnyApplySeq);

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.ofSuccess());

	}

}
