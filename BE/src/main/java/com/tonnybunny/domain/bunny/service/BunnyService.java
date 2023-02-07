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

import java.util.ArrayList;
import java.util.List;


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
		BunnyEntity bunny = bunnyRepository.findBySeqAndIsDeleted(bunnySeq, false).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
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

		List<BunnyEntity> bunnyList = new ArrayList<>();

		if (lang.isEmpty() && category.isEmpty()) {
			// 필터하지 않고 전체 조회
			bunnyList = bunnyRepository.findByIsDeletedOrderByCreatedAtDesc(false);
		} else if (category.isEmpty()) {
			// 언어만 필터하여 조회
			bunnyList = bunnyRepository.findByStartLangCodeAndIsDeletedOrEndLangCodeAndIsDeletedOrderByCreatedAtDesc(lang, false, lang, false);
		} else if (lang.isEmpty()) {
			// 카테고리만 필터하여 조회
			List<BunnyEntity> tempBunnyList = bunnyRepository.findByBunnySituCodeOrderByCreatedAtDesc(category);
			for (BunnyEntity bunny : tempBunnyList) {
				if (bunny.getIsDeleted().equals(false)) {
					bunnyList.add(bunny);
				}
			}
			System.out.println("bunnyList = " + bunnyList);
		} else {
			// 둘 다 필터하여 조회
			List<BunnyEntity> tempBunnyList = bunnyRepository.findByStartLangCodeAndBunnySituCodeOrEndLangCodeAndBunnySituCodeOrderByCreatedAtDesc(lang, category, lang, category);
			for (BunnyEntity bunny : tempBunnyList) {
				if (bunny.getIsDeleted().equals(false)) {
					bunnyList.add(bunny);
				}
			}
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
	 * @param bunnyApplyRequestDto
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

}
