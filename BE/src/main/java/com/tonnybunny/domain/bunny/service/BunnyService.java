package com.tonnybunny.domain.bunny.service;


import com.tonnybunny.domain.bunny.dto.BunnyApplyRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyImageRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyRequestDto;
import com.tonnybunny.domain.bunny.entity.BunnyApplyEntity;
import com.tonnybunny.domain.bunny.entity.BunnyEntity;
import com.tonnybunny.domain.bunny.entity.BunnyImageEntity;
import com.tonnybunny.domain.bunny.repository.BunnyApplyRepository;
import com.tonnybunny.domain.bunny.repository.BunnyImageRepository;
import com.tonnybunny.domain.bunny.repository.BunnyRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BunnyService {

	private final UserRepository userRepository;
	private final BunnyRepository bunnyRepository;
	private final BunnyApplyRepository bunnyApplyRepository;
	private final BunnyImageRepository bunnyImageRepository;

	// --------------------------------------- 번역 공고 ---------------------------------------


	/**
	 * 번역 공고 생성
	 *
	 * @param bunnyRequestDto : 번역 공고 생성 폼
	 * @return : 생성된 공고의 seq
	 */
	public Long createBunny(BunnyRequestDto bunnyRequestDto) {
		Optional<UserEntity> user = userRepository.findById(bunnyRequestDto.getClientSeq());
		BunnyEntity bunny = BunnyEntity.builder()
		                               .user(user.get())
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

		BunnyEntity bunny = bunnyRepository.findById(bunnySeq).orElseThrow(() -> new CustomException(ErrorCode.ERROR_NAME));
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
		Optional<BunnyEntity> bunny = bunnyRepository.findById(bunnySeq);
		System.out.println("bunny = " + bunny);
		return bunny.orElseThrow(() -> new CustomException(ErrorCode.ERROR_NAME));      // 에러처리
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
			// 아직 미구현
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

		Optional<UserEntity> user = userRepository.findById(bunnyApplyRequestDto.getUserSeq());                 // 에러처리
		Optional<BunnyEntity> bunny = bunnyRepository.findById(bunnyApplyRequestDto.getBunnySeq());             // 에러처리

		BunnyApplyEntity bunnyApply = BunnyApplyEntity.builder().
		                                              bunny(bunny.get()).estimatePrice(bunnyApplyRequestDto.getEstimatePrice()).user(user.get()).build();

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

		BunnyApplyEntity bunnyApply = bunnyApplyRepository.findById(bunnyApplyRequestDto.getBunnyApplySeq()).orElseThrow(); // 에러처리
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
	 * 번역 공고 신청 상세 조회
	 *
	 * @param bunnyApplySeq : 조회할 신청 seq
	 * @return : 조회된 신청 Entity
	 */
	public BunnyApplyEntity getBunnyApply(Long bunnyApplySeq) {
		// TODO : 로직

		return (BunnyApplyEntity) new Object();
	}


	/**
	 * 번역 공고 신청 수락
	 *
	 * @param bunnyApplySeq : 수락할 번역 공고로의 신청 seq
	 * @return 로직 성공 여부
	 */
	public Boolean acceptBunnyApply(Long bunnyApplySeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 번역 공고 신청 거절
	 *
	 * @param bunnyApplySeq : 거절된 번역 공고로의 신청 seq
	 * @return 로직 성공 여부
	 */
	public Boolean rejectBunnyApply(Long bunnyApplySeq) {
		// TODO : 로직

		return true;
	}

}
