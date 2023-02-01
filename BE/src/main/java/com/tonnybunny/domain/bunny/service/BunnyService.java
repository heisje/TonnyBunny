package com.tonnybunny.domain.bunny.service;


import com.tonnybunny.domain.bunny.dto.BunnyNotiHelperRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiImageRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyNotiRequestDto;
import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import com.tonnybunny.domain.bunny.entity.BunnyNotiHelperEntity;
import com.tonnybunny.domain.bunny.entity.BunnyNotiImageEntity;
import com.tonnybunny.domain.bunny.repository.BunnyNotiImageRepository;
import com.tonnybunny.domain.bunny.repository.BunnyNotiRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BunnyService {

	private final UserRepository userRepository;
	private final BunnyNotiRepository bunnyNotiRepository;
	private final BunnyNotiImageRepository bunnyNotiImageRepository;


	private BunnyNotiEntity bunnyNotiToEntity(BunnyNotiRequestDto bunnyNotiRequestDto) {

		Optional<UserEntity> user = userRepository.findById(bunnyNotiRequestDto.getClientSeq());
		BunnyNotiEntity bunnyNoti = BunnyNotiEntity.builder()
			.user(user.get())
			.title(bunnyNotiRequestDto.getTitle())
			.content(bunnyNotiRequestDto.getContent())
			.estimatePrice(bunnyNotiRequestDto.getEstimatePrice())
			.startDate(bunnyNotiRequestDto.getStartDate())
			.endDate(bunnyNotiRequestDto.getEndDate())
			.startLangCode(bunnyNotiRequestDto.getStartLangCode())
			.endLangCode(bunnyNotiRequestDto.getEndLangCode())
			.bunnySituCode(bunnyNotiRequestDto.getBunnySituCode())
			.bunnyStateCode(bunnyNotiRequestDto.getBunnyStateCode())
			.build();

		System.out.println("bunnyNoti.getBunnyNotiImageList() = " + bunnyNoti.getBunnyNotiImageList());

		bunnyNoti = bunnyNotiRepository.save(bunnyNoti);

		for (BunnyNotiImageRequestDto bunnyNotiImageRequestDto : bunnyNotiRequestDto.getBunnyNotiImageList()) {

			BunnyNotiImageEntity bunnyNotiImage = BunnyNotiImageEntity.builder()
				.bunnyNoti(bunnyNoti)
				.imagePath(bunnyNotiImageRequestDto.getImagePath())
				.build();

			bunnyNotiImage = bunnyNotiImageRepository.save(bunnyNotiImage);

			bunnyNoti.getBunnyNotiImageList().add(bunnyNotiImage);

		}

		return bunnyNoti;
	}

	// --------------------------------------- 번역 공고 ---------------------------------------


	/**
	 * 번역 공고 생성
	 *
	 * @param bunnyNotiRequestDto : 번역 공고 생성 폼
	 * @return : 생성된 공고의 seq
	 */
	public Long createBunnyNoti(BunnyNotiRequestDto bunnyNotiRequestDto) {
		// TODO : 로직
		BunnyNotiEntity bunnyNoti = bunnyNotiToEntity(bunnyNotiRequestDto);

		return bunnyNoti.getSeq();
	}


	/**
	 * 번역 공고 삭제
	 *
	 * @param bunnyNotiSeq : 삭제할 번역 공고의 seq
	 * @return : 삭제 성공 여부
	 */
	public Boolean deleteBunnyNoti(Long bunnyNotiSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 번역 공고 상세 조회
	 *
	 * @param bunnyNotiSeq : 조회할 번역 공고의 seq
	 * @return : 조회된 공고의 Entity
	 */
	public BunnyNotiEntity getBunnyNoti(Long bunnyNotiSeq) {
		// TODO : 로직
		Optional<BunnyNotiEntity> bunnyNoti = bunnyNotiRepository.findById(bunnyNotiSeq);
		return bunnyNoti.orElseThrow(() -> new CustomException(ErrorCode.ERROR_NAME));      // 이따 엔티티 없음으로 바꿈
	}


	/**
	 * 번역 공고 목록 조회 (필터기능)
	 *
	 * @param lang     : 언어코드
	 * @param category : 카테고리코드
	 * @return : 조회된 번역 공고 EntityList
	 */
	public List<BunnyNotiEntity> getBunnyListByFilter(String lang, String category) {
		// TODO : 로직
		List<BunnyNotiEntity> bunnyNotiList;

		if (lang.isEmpty() && category.isEmpty()) {
			// 필터하지 않고 전체 조회
			bunnyNotiList = bunnyNotiRepository.findAllByOrderByCreatedAtDesc();
		} else if (category.isEmpty()) {
			// 언어만 필터하여 조회
			bunnyNotiList = bunnyNotiRepository.findByStartLangCodeOrEndLangCodeOrderByCreatedAtDesc(lang, lang);
		} else if (lang.isEmpty()) {
			// 카테고리만 필터하여 조회
			bunnyNotiList = bunnyNotiRepository.findByBunnySituCodeOrderByCreatedAtDesc(category);
		} else {
			// 둘 다 필터하여 조회
			// 아직 미구현
			bunnyNotiList = bunnyNotiRepository.findByStartLangCodeAndBunnySituCodeOrEndLangCodeAndBunnySituCodeOrderByCreatedAtDesc(lang, category, lang, category);
		}

		return bunnyNotiList;
	}


	/**
	 * 번역 공고 신청 생성
	 *
	 * @param bunnyNotiHelperRequestDto : 번역 공고 신청 정보
	 * @return : 생성된 신청 seq
	 */
	public Long createBunnyNotiHelper(BunnyNotiHelperRequestDto bunnyNotiHelperRequestDto) {
		// TODO : 로직

		BunnyNotiHelperEntity bunnyNotiHelper = bunnyNotiHelperRequestDto.toEntity();
		return bunnyNotiHelper.getSeq();
	}


	/**
	 * 번역 공고 신청 취소
	 *
	 * @param bunnyNotiHelperSeq : 취소할 신청 seq
	 * @return : 로직 성공 여부
	 */
	public Boolean deleteBunnyNotiHelper(Long bunnyNotiHelperSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 번역 공고 신청 목록 조회
	 *
	 * @return : 번역 공고 신청 Entity List
	 */
	public List<BunnyNotiHelperEntity> getBunnyNotiHelperList() {
		// TODO : 로직

		return new ArrayList<>();
	}


	/**
	 * 번역 공고 신청 상세 조회
	 *
	 * @param bunnyNotiHelperSeq : 조회할 신청 seq
	 * @return : 조회된 신청 Entity
	 */
	public BunnyNotiHelperEntity getBunnyNotiHelper(Long bunnyNotiHelperSeq) {
		// TODO : 로직

		return (BunnyNotiHelperEntity) new Object();
	}


	/**
	 * 번역 공고 신청 수락
	 *
	 * @param bunnyNotiHelperSeq : 수락할 번역 공고로의 신청 seq
	 * @return 로직 성공 여부
	 */
	public Boolean acceptBunnyNotiHelper(Long bunnyNotiHelperSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 번역 공고 신청 거절
	 *
	 * @param bunnyNotiHelperSeq : 거절된 번역 공고로의 신청 seq
	 * @return 로직 성공 여부
	 */
	public Boolean rejectBunnyNotiHelper(Long bunnyNotiHelperSeq) {
		// TODO : 로직

		return true;
	}

}
