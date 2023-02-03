package com.tonnybunny.domain.bunny.service;


import com.tonnybunny.common.dto.BunnyStateCodeEnum;
import com.tonnybunny.common.dto.QuotationStateCodeEnum;
import com.tonnybunny.domain.bunny.dto.*;
import com.tonnybunny.domain.bunny.entity.*;
import com.tonnybunny.domain.bunny.repository.*;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BunnyService {

	private final UserRepository userRepository;
	private final BunnyRepository bunnyRepository;
	private final BunnyApplyRepository bunnyApplyRepository;
	private final BunnyImageRepository bunnyImageRepository;
	private final BunnyQuotationRepository bunnyQuotationRepository;
	private final BunnyQuotationImageRepository bunnyQuotationImageRepository;

	// --------------------------------------- 번역 공고 ---------------------------------------


	/**
	 * 번역 공고 생성
	 *
	 * @param bunnyRequestDto : 번역 공고 생성 폼
	 * @return : 생성된 공고의 seq
	 */
	public Long createBunny(BunnyRequestDto bunnyRequestDto) {
		UserEntity user = userRepository.findById(bunnyRequestDto.getClientSeq()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		BunnyEntity bunny = BunnyEntity.builder()
			.user(user)
			.title(bunnyRequestDto.getTitle())
			.content(bunnyRequestDto.getContent())
			.estimatePrice(bunnyRequestDto.getEstimatePrice())
			.startDate(bunnyRequestDto.getStartDate())
			.endDate(bunnyRequestDto.getEndDate())
			.startLangCode(bunnyRequestDto.getStartLangCode())
			.endLangCode(bunnyRequestDto.getEndLangCode())
			.bunnySituCode(bunnyRequestDto.getBunnySituCode())
			.bunnyStateCode(bunnyRequestDto.getBunnyStateCode())
			.build();

		bunny = bunnyRepository.save(bunny);

		for (BunnyImageRequestDto bunnyImageRequestDto : bunnyRequestDto.getBunnyImageList()) {

			BunnyImageEntity bunnyImage = BunnyImageEntity.builder()
				.bunny(bunny)
				.imagePath(bunnyImageRequestDto.getImagePath())
				.build();

			bunnyImage = bunnyImageRepository.save(bunnyImage);

			bunny.getBunnyImageList().add(bunnyImage);

		}

		return bunny.getSeq();
	}


	/**
	 * 번역 공고 삭제
	 *
	 * @param bunnySeq : 삭제할 번역 공고의 seq
	 * @return : 삭제 성공 여부
	 */
	public Long deleteBunny(Long bunnySeq) {

		BunnyEntity bunny = bunnyRepository.findById(bunnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		bunny.delete();
		bunnyRepository.save(bunny);
		return bunny.getSeq();
	}


	/**
	 * 번역 공고 상세 조회
	 *
	 * @param bunnySeq : 조회할 번역 공고의 seq
	 * @return : 조회된 공고의 Entity
	 */
	public BunnyEntity getBunny(Long bunnySeq) {
		BunnyEntity bunny = bunnyRepository.findById(bunnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		return bunny;
	}


	/**
	 * 번역 공고 목록 조회 (필터기능)
	 *
	 * @param lang     : 언어코드
	 * @param category : 카테고리코드
	 * @return : 조회된 번역 공고 EntityList
	 */
	public List<BunnyEntity> getBunnyListByFilter(String lang, String category) {

		List<BunnyEntity> bunnyList;

		if (lang.isEmpty() && category.isEmpty()) {
			// 필터하지 않고 전체 조회
			bunnyList = bunnyRepository.findAllByOrderByCreatedAtDesc();
		} else if (category.isEmpty()) {
			// 언어만 필터하여 조회
			bunnyList = bunnyRepository.findByStartLangCodeOrEndLangCodeOrderByCreatedAtDesc(lang, lang);
		} else if (lang.isEmpty()) {
			// 카테고리만 필터하여 조회
			bunnyList = bunnyRepository.findByBunnySituCodeOrderByCreatedAtDesc(category);
		} else {
			// 둘 다 필터하여 조회
			bunnyList = bunnyRepository.findByStartLangCodeAndBunnySituCodeOrEndLangCodeAndBunnySituCodeOrderByCreatedAtDesc(lang, category, lang, category);
		}

		return bunnyList;
	}


	/**
	 * 번역 공고 신청 생성
	 *
	 * @param bunnyApplyRequestDto : 번역 공고 신청 정보
	 * @return : 생성된 신청 seq
	 */
	public Long createBunnyApply(BunnyApplyRequestDto bunnyApplyRequestDto) {

		UserEntity user = userRepository.findById(bunnyApplyRequestDto.getUserSeq()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		BunnyEntity bunny = bunnyRepository.findById(bunnyApplyRequestDto.getBunnySeq()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

		BunnyApplyEntity bunnyApply = BunnyApplyEntity.builder().
			bunny(bunny).estimatePrice(bunnyApplyRequestDto.getEstimatePrice()).user(user).build();

		BunnyApplyEntity creaetedBunnyApply = bunnyApplyRepository.save(bunnyApply);
		return creaetedBunnyApply.getSeq();
	}


	/**
	 * 번역 공고 신청 취소
	 *
	 * @param bunnyApplyRequestDto :취소할 신청 seq
	 * @return :로직 성공 여부
	 */
	public Boolean deleteBunnyApply(BunnyApplyRequestDto bunnyApplyRequestDto) {

		BunnyApplyEntity bunnyApply = bunnyApplyRepository.findById(bunnyApplyRequestDto.getBunnyApplySeq()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		bunnyApplyRepository.delete(bunnyApply);

		return true;
	}


	/**
	 * 번역 공고 신청 목록 조회
	 *
	 * @return : 번역 공고 신청 Entity List
	 */
	public List<BunnyApplyEntity> getBunnyApplyList(Long bunnySeq) {

		List<BunnyApplyEntity> bunnyApplyList = bunnyApplyRepository.findByBunnySeq(bunnySeq);

		return bunnyApplyList;
	}


	/**
	 * 번역 견적서를 작성
	 *
	 * @param bunnyQuotationRequestDto : 견적서에 넣는 내용 정보
	 * @return 생성한 견적서 Seq
	 */
	public Long createBunnyQuotation(BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		BunnyEntity bunny = bunnyRepository.findById(bunnyQuotationRequestDto.getBunnySeq()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		UserEntity client = userRepository.findById(bunnyQuotationRequestDto.getClientSeq()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		UserEntity helper = userRepository.findById(bunnyQuotationRequestDto.getHelperSeq()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		BunnyQuotationEntity bunnyQuotation = BunnyQuotationEntity.builder()
			.bunny(bunny)
			.client(client)
			.helper(helper)
			.startDateTime(bunnyQuotationRequestDto.getStartDateTime())
			.endDateTime(bunnyQuotationRequestDto.getEndDateTime())
			.title(bunnyQuotationRequestDto.getTitle())
			.content(bunnyQuotationRequestDto.getContent())
			.totalPrice(bunnyQuotationRequestDto.getTotalPrice())
			.startLangCode(bunnyQuotationRequestDto.getStartLangCode())
			.endLangCode(bunnyQuotationRequestDto.getEndLangCode())
			.build();

		bunnyQuotation = bunnyQuotationRepository.save(bunnyQuotation);

		for (BunnyQuotationImageRequestDto bunnyQuotationImageRequestDto : bunnyQuotationRequestDto.getBunnyQuotationImageRequestDtoList()) {

			BunnyQuotationImageEntity bunnyQuotationImage = BunnyQuotationImageEntity.builder()
				.bunnyQuotation(bunnyQuotation)
				.imagePath(bunnyQuotationImageRequestDto.getImagePath())
				.build();

			bunnyQuotationImage = bunnyQuotationImageRepository.save(bunnyQuotationImage);

			bunnyQuotation.getBunnyQuotationImageList().add(bunnyQuotationImage);

		}

		return bunnyQuotation.getSeq();
	}


	/**
	 * FIXME : 조회 기준 Seq를 변경해야 함
	 * 1:1 상담 채팅방에 올라온 견적 상담 채팅방에 올라온 견적서들의 목록 조회
	 *
	 * @param bunnySeq : 번역 공고 게시글 Seq
	 * @return
	 */
	public List<BunnyQuotationEntity> getBunnyQuotationList(Long bunnySeq) {

		List<BunnyQuotationEntity> bunnyQuotationList = bunnyQuotationRepository.findByBunnySeq(bunnySeq);

		return bunnyQuotationList;
	}


	/**
	 * 번역 견적서 상세 조회
	 *
	 * @param bunnyQuotationRequestDto : 조회할 견적서 Seq
	 * @return 조회한 견적서
	 */
	public BunnyQuotationEntity getBunnyQuotation(BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		Long bunnyQuotationSeq = bunnyQuotationRequestDto.getSeq();
		BunnyQuotationEntity bunnyQuotation = bunnyQuotationRepository.findById(bunnyQuotationSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		return bunnyQuotation;
	}

	/**
	 * 견적서 상태 타입 수정
	 * - 미선택 : 아직 견적서 협의가 확정되지 않은 상태
	 * - 선택 : 견적서 협의가 확정된 상태. 고객이 견적서를 수락한 상태.
	 * - 완료 : 번역 업무가 완료된 상태.
	 *
	 * @param bunnyQuotationSeq       : 대상 견적서 Seq
	 * @param bunnyQuotationStateCode : 견적서 상태 타입 코드 (공통 코드)
	 */

	/**
	 * 번역 견적서 신청 수락
	 *
	 * @param bunnyQuotationRequestDto : 수락할 번역 공고로의 신청 seq
	 * @return 로직 성공 여부
	 */
	public Boolean acceptBunnyQuotation(BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		// 번역 견적서 상태코드 변경
		Long bunnyQuotationSeq = bunnyQuotationRequestDto.getSeq();
		BunnyQuotationEntity bunnyQuotation = bunnyQuotationRepository.findById(bunnyQuotationSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY))
		bunnyQuotation.changeStateCode(QuotationStateCodeEnum.수락됨.getQuotationStateCode());
		bunnyQuotationRepository.save(bunnyQuotation);

		// 번역 공고 상태코드 변경
		Long bunnySeq = bunnyQuotationRequestDto.getBunnySeq();
		BunnyEntity bunny = bunnyRepository.findById(bunnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		bunny.changeStateCode(BunnyStateCodeEnum.매칭완료.getBunnyStateCode());
		bunnyRepository.save(bunny);
		return true;
	}


	/**
	 * 번역 공고 신청 거절
	 *
	 * @param bunnyQuotationRequestDto : 거절된 번역 공고로의 신청 seq
	 * @return 로직 성공 여부
	 */
	public Boolean rejectBunnyQuotation(BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		// 번역 견적서 상태코드 변경
		Long bunnyQuotationSeq = bunnyQuotationRequestDto.getSeq();
		BunnyQuotationEntity bunnyQuotation = bunnyQuotationRepository.findById(bunnyQuotationSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		bunnyQuotation.changeStateCode(QuotationStateCodeEnum.거절됨.getQuotationStateCode());
		bunnyQuotationRepository.save(bunnyQuotation);

		// 번역 공고 상태코드 변경
		Long bunnySeq = bunnyQuotationRequestDto.getBunnySeq();
		BunnyEntity bunny = bunnyRepository.findById(bunnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		bunny.changeStateCode(BunnyStateCodeEnum.시작전.getBunnyStateCode());
		bunnyRepository.save(bunny);
		return true;
	}

	//	public void modifyBunnyQuotationType(Long bunnyQuotationSeq, String bunnyQuotationStateCode) {
	//		// TODO : 구현
	//	}

}
