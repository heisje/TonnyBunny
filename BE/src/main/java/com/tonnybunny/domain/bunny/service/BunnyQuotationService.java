package com.tonnybunny.domain.bunny.service;


import com.tonnybunny.common.dto.QuotationStateCodeEnum;
import com.tonnybunny.common.dto.TaskStateCodeEnum;
import com.tonnybunny.domain.bunny.dto.BunnyQuotationImageRequestDto;
import com.tonnybunny.domain.bunny.dto.BunnyQuotationRequestDto;
import com.tonnybunny.domain.bunny.entity.BunnyEntity;
import com.tonnybunny.domain.bunny.entity.BunnyHistoryEntity;
import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import com.tonnybunny.domain.bunny.entity.BunnyQuotationImageEntity;
import com.tonnybunny.domain.bunny.repository.BunnyQuotationImageRepository;
import com.tonnybunny.domain.bunny.repository.BunnyQuotationRepository;
import com.tonnybunny.domain.bunny.repository.BunnyRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.HistoryRepository;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BunnyQuotationService {

	private final UserRepository userRepository;
	private final BunnyRepository bunnyRepository;
	private final BunnyQuotationRepository bunnyQuotationRepository;
	private final BunnyQuotationImageRepository bunnyQuotationImageRepository;
	private final HistoryRepository historyRepository;


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
	 * @param bunnyQuotationSeq
	 * @return 조회한 견적서
	 */
	public BunnyQuotationEntity getBunnyQuotation(Long bunnyQuotationSeq) {

		BunnyQuotationEntity bunnyQuotation = bunnyQuotationRepository.findById(bunnyQuotationSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		return bunnyQuotation;
	}


	/**
	 * 번역 견적서 수락
	 *
	 * @param bunnyQuotationRequestDto
	 * @return 로직 성공 여부
	 */
	public Boolean acceptBunnyQuotation(BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		// 번역 견적서 상태코드 변경
		Long bunnyQuotationSeq = bunnyQuotationRequestDto.getSeq();
		BunnyQuotationEntity bunnyQuotation = bunnyQuotationRepository.findById(bunnyQuotationSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		bunnyQuotation.changeStateCode(QuotationStateCodeEnum.수락됨.getQuotationStateCode());
		bunnyQuotationRepository.save(bunnyQuotation);

		// 번역 공고 상태코드 변경
		Long bunnySeq = bunnyQuotationRequestDto.getBunnySeq();
		BunnyEntity bunny = bunnyRepository.findById(bunnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		bunny.changeStateCode(TaskStateCodeEnum.진행중.getTaskStateCode());
		bunnyRepository.save(bunny);
		return true;
	}


	/**
	 * 번역 견적서 거절
	 *
	 * @param bunnyQuotationRequestDto
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
		bunny.changeStateCode(TaskStateCodeEnum.모집중.getTaskStateCode());
		bunnyRepository.save(bunny);
		return true;
	}


	/**
	 * 번역 공고 완료
	 *
	 * @param bunnyQuotationRequestDto
	 * @return
	 */
	public Long completeBunnyQuotation(BunnyQuotationRequestDto bunnyQuotationRequestDto) {

		// 번역 견적서 상태코드 변경
		Long bunnyQuotationSeq = bunnyQuotationRequestDto.getSeq();
		BunnyQuotationEntity bunnyQuotation = bunnyQuotationRepository.findById(bunnyQuotationSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		bunnyQuotation.changeStateCode(QuotationStateCodeEnum.완료됨.getQuotationStateCode());
		bunnyQuotationRepository.save(bunnyQuotation);

		// 번역 공고 상태코드 변경
		Long bunnySeq = bunnyQuotationRequestDto.getBunnySeq();
		BunnyEntity bunny = bunnyRepository.findById(bunnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		bunny.changeStateCode(TaskStateCodeEnum.완료됨.getTaskStateCode());
		bunnyRepository.save(bunny);

		BunnyHistoryEntity bunnyHistory = BunnyHistoryEntity.builder()
			.client(bunnyQuotation.getClient())
			.helper(bunnyQuotation.getHelper())
			.taskCode(bunny.getTaskCode())
			.notiSeq(bunny.getSeq())
			.startLangCode(bunnyQuotation.getStartLangCode())
			.endLangCode(bunnyQuotation.getEndLangCode())
			.content(bunny.getContent())
			.startDateTime(bunnyQuotation.getStartDateTime())
			.endDateTime(bunnyQuotation.getEndDateTime())
			.title(bunny.getTitle())
			.totalPrice(bunnyQuotation.getTotalPrice())
			.build();

		// TODO
		//  bunnyHistory 에는 bunnyQuotationImage 저장 안하나?
		historyRepository.save(bunnyHistory);

		return bunnyHistory.getSeq();
	}

}
