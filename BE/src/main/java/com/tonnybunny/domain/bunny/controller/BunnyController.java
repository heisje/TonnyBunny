package com.tonnybunny.domain.bunny.controller;


import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.domain.bunny.dto.*;
import com.tonnybunny.domain.bunny.entity.BunnyApplyEntity;
import com.tonnybunny.domain.bunny.entity.BunnyEntity;
import com.tonnybunny.domain.bunny.service.BunnyService;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/bunny")
@RequiredArgsConstructor
@Api(tags = "번역 관련 API")
public class BunnyController {

	private final BunnyService bunnyService;
	private final UserService userService;


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
		System.out.println("bunnyEntity = " + bunnyEntity);

		UserEntity user = bunnyEntity.getUser();
		System.out.println("user = " + user);

		// 작성자 정보
		BunnyResponseDto bunnyResponseDto = BunnyResponseDto.fromEntity(bunnyEntity);
		bunnyResponseDto.getClient().put("imagePath", user.getProfileImagePath());
		bunnyResponseDto.getClient().put("nickName", user.getNickName());
		bunnyResponseDto.getClient().put("seq", user.getSeq().toString());

		// 헬퍼 정보
		Long helperSeq = bunnyEntity.getHelperSeq();

		if (helperSeq != 0) {
			UserEntity helper = userService.getUserInfo(helperSeq);
			bunnyResponseDto.getHelper().put("imagePath", helper.getProfileImagePath());
			bunnyResponseDto.getHelper().put("nickName", helper.getNickName());
			bunnyResponseDto.getHelper().put("seq", helper.getSeq().toString());

		}

		//  apply 커스텀
		for (BunnyApplyResponseDto bunnyApplyResponseDto : bunnyResponseDto.getBunnyApplyList()) {
			Long userSeq = bunnyApplyResponseDto.getUserSeq();

			UserEntity tempUser = userService.getUserInfo(userSeq);
			Map<String, Object> helperInfo = new HashMap<>();

			bunnyApplyResponseDto.setNickName(tempUser.getNickName());
			bunnyApplyResponseDto.setProfileImagePath(tempUser.getProfileImagePath());

			helperInfo.put("likeCount", tempUser.getHelperInfo().getLikeCount());
			helperInfo.put("reviewCount", tempUser.getHelperInfo().getReviewCount());
			helperInfo.put("avgScore",
				tempUser.getHelperInfo().getReviewCount() == 0 ? 0 : Math.round(tempUser.getHelperInfo().getTotalScore() * 10.0 / tempUser.getHelperInfo().getReviewCount()) / 10.0);
			helperInfo.put("oneLineIntroduction", tempUser.getHelperInfo().getOneLineIntroduction());
			helperInfo.put("unitPrice", bunnyApplyResponseDto.getEstimatePrice());

			bunnyApplyResponseDto.setHelperInfo(helperInfo);
		}

		// quotation 커스텀
		for (BunnyQuotationResponseDto bunnyQuotationResponseDto : bunnyResponseDto.getBunnyQuotationList()) {
			Long userSeq = bunnyQuotationResponseDto.getHelperSeq();

			UserEntity tempUser = userService.getUserInfo(userSeq);
			Map<String, Object> helperInfo = new HashMap<>();

			bunnyQuotationResponseDto.setNickName(tempUser.getNickName());
			bunnyQuotationResponseDto.setProfileImagePath(tempUser.getProfileImagePath());

			helperInfo.put("likeCount", tempUser.getHelperInfo().getLikeCount());
			helperInfo.put("reviewCount", tempUser.getHelperInfo().getReviewCount());
			helperInfo.put("oneLineIntroduction", tempUser.getHelperInfo().getOneLineIntroduction());
			helperInfo.put("unitPrice", bunnyQuotationResponseDto.getTotalPrice());

			if (tempUser.getHelperInfo().getReviewCount() == 0) {
				helperInfo.put("avg", 0F);
			} else {
				helperInfo.put("avg", Float.valueOf(tempUser.getHelperInfo().getTotalScore()) / Float.valueOf(tempUser.getHelperInfo().getReviewCount()));
			}

			bunnyQuotationResponseDto.setHelperInfo(helperInfo);
		}

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyResponseDto));
	}

	/**
	 *  DTO 수정해야하고
	 *  clinetSeq -> client Entity
	 */

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
		List<BunnyResponseDto> bunnyResponseDtoList = new ArrayList<>();

		for (BunnyEntity bunny : bunnyList) {
			// 고객 정보 넣기
			UserEntity user = bunny.getUser();
			BunnyResponseDto bunnyResponseDto = BunnyResponseDto.fromEntity(bunny);
			bunnyResponseDto.getClient().put("imagePath", user.getProfileImagePath());
			bunnyResponseDto.getClient().put("nickName", user.getNickName());
			bunnyResponseDto.getClient().put("seq", user.getSeq().toString());

			// 헬퍼 정보 넣기
			Long helperSeq = bunny.getHelperSeq();
			if (helperSeq != 0) {
				UserEntity helper = userService.getUserInfo(helperSeq);
				bunnyResponseDto.getHelper().put("imagePath", helper.getProfileImagePath());
				bunnyResponseDto.getHelper().put("nickName", helper.getNickName());
				bunnyResponseDto.getHelper().put("seq", helper.getSeq().toString());

			}

			bunnyResponseDtoList.add(bunnyResponseDto);
		}

		for (BunnyResponseDto tmp : bunnyResponseDtoList) {
			System.out.println("BunnyController.getBunnyListByFilter");
			System.out.println(LocalDateTime.now());
			System.out.println("안되면많이화날것같음...");
			System.out.println(tmp.getCreatedAt().toString());
		}

		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(bunnyResponseDtoList));

	}


	/**
	 * 고객 seq로 고객이 올린 번역공고 조회하기
	 *
	 * @param clientSeq : 고객 seq
	 * @return
	 */
	@GetMapping("/{clientSeq}/user")
	@ApiOperation(value = "번역 공고 리스트를 조회합니다 (with clientSeq)")
	public ResponseEntity<ResultDto<List<BunnyResponseDto>>> getBunnyListByClientSeq(@PathVariable("clientSeq") Long clientSeq) {
		System.out.println("clientSeq = " + clientSeq);
		List<BunnyEntity> bunnyList = bunnyService.getBunnyListByClientSeq(clientSeq);
		List<BunnyResponseDto> bunnyResponseDtoList = new ArrayList<>();

		for (BunnyEntity bunny : bunnyList) {
			UserEntity user = bunny.getUser();
			BunnyResponseDto bunnyResponseDto = BunnyResponseDto.fromEntity(bunny);
			bunnyResponseDto.getClient().put("imagePath", user.getProfileImagePath());
			bunnyResponseDto.getClient().put("nickName", user.getNickName());
			bunnyResponseDto.getClient().put("seq", user.getSeq().toString());

			bunnyResponseDtoList.add(bunnyResponseDto);
		}

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

}
