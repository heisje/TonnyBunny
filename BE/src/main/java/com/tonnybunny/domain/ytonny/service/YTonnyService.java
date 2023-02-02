package com.tonnybunny.domain.ytonny.service;


import com.tonnybunny.common.dto.LangCodeEnum;
import com.tonnybunny.common.dto.TaskCodeEnum;
import com.tonnybunny.common.dto.TaskStateCodeEnum;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.domain.ytonny.dto.YTonnyApplyRequestDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyRequestDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyApplyEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyEntity;
import com.tonnybunny.domain.ytonny.repository.YTonnyApplyRepository;
import com.tonnybunny.domain.ytonny.repository.YTonnyRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class YTonnyService {

	private final UserRepository userRepository;

	private final YTonnyRepository yTonnyRepository;
	private final YTonnyApplyRepository yTonnyApplyRepository;


	/**
	 * MEMO : CREATE
	 * MARK : 예약통역 공고를 생성
	 *
	 * @param yTonnyRequestDto : 예약통역 공고 관련 정보
	 * @return 생성된 예약통역 공고 seq
	 */
	@Transactional
	public Long createYTonny(YTonnyRequestDto yTonnyRequestDto) {

		System.out.println("YTonnyService.createYTonny");

		// param setting
		UserEntity userEntity = userRepository.findById(yTonnyRequestDto.getClientSeq())
		                                      .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		// dto -> entity
		YTonnyEntity yTonnyEntity = YTonnyEntity.builder()
		                                        .client(userEntity)
		                                        .title(yTonnyRequestDto.getTitle())
		                                        .content(yTonnyRequestDto.getContent())
		                                        .estimateDate(yTonnyRequestDto.getEstimateDate())
		                                        .estimateStartTime(yTonnyRequestDto.getEstimateStartTime())
		                                        .estimateTime(yTonnyRequestDto.getEstimateTime())
		                                        .estimatePrice(yTonnyRequestDto.getEstimatePrice())
		                                        .startLangCode(LangCodeEnum.한국어)
		                                        .endLangCode(yTonnyRequestDto.getEndLangCode())
		                                        .tonnySituCode(yTonnyRequestDto.getTonnySituCode())
		                                        .taskCode(TaskCodeEnum.예약통역)
		                                        .taskStateCode(TaskStateCodeEnum.모집중)
		                                        .isDeleted(false)
		                                        .build();

		// save
		return yTonnyRepository.save(yTonnyEntity).getSeq();

	}


	/**
	 * MARK : (헬퍼가) 예약통역 공고에 신청을 생성
	 * 즉슨, 예약통역 한다고 신청한다는 말..
	 *
	 * @param yTonnyApplyRequestDto : 헬퍼 seq, 해당 공고 seq, 견적 금액
	 * @return 생성된 예약통역 공고 신청 seq
	 */
	public Long createYTonnyApply(YTonnyApplyRequestDto yTonnyApplyRequestDto) {

		System.out.println("YTonnyService.createYTonnyApply");

		// param setting
		Long yTonnySeq = yTonnyApplyRequestDto.getYTonnySeq();
		Long helperSeq = yTonnyApplyRequestDto.getHelperSeq();
		Integer totalPrice = yTonnyApplyRequestDto.getTotalPrice();

		// find
		YTonnyEntity yTonnyEntity = yTonnyRepository.findById(yTonnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));
		UserEntity helperEntity = userRepository.findById(helperSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		// dto -> entity
		YTonnyApplyEntity yTonnyApplyEntity = YTonnyApplyEntity.builder()
		                                                       .yTonny(yTonnyEntity)
		                                                       .helper(helperEntity)
		                                                       .totalPrice(totalPrice)
		                                                       .build();

		return yTonnyApplyRepository.save(yTonnyApplyEntity).getSeq();

	}


	/**
	 * MEMO : READ
	 * MARK : 예약통역 공고 목록을 조회 with pagination
	 *
	 * @param yTonnyRequestDto : page, size
	 * @return 예약통역 공고 목록
	 */
	public List<YTonnyEntity> getYTonnyList(YTonnyRequestDto yTonnyRequestDto) {

		System.out.println("YTonnyService.getYTonnyList");

		// param setting
		int page = yTonnyRequestDto.getPage();
		int size = yTonnyRequestDto.getSize();

		// pagination
		Pageable pageable = PageRequest.of(page, size);

		// FIXME : 필터링도 필요하지 않나?

		// find
		return yTonnyRepository.findAllByOrderByCreatedAtDesc(pageable).getContent();

	}


	/**
	 * MARK : 예약통역 공고 목록을 상세 조회
	 *
	 * @param yTonnySeq : 해당 조회 공고 seq
	 * @return 예약통역 상세 정보
	 */
	public YTonnyEntity getYTonnyDetail(Long yTonnySeq) {

		System.out.println("YTonnyService.getYTonnyDetail");

		// find
		return yTonnyRepository.findById(yTonnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

	}


	/**
	 * MARK : 예약통역 공고 신청 목록을 조회
	 *
	 * @param yTonnySeq
	 * @return 생성된 예약통역 공고 seq
	 */
	public List<YTonnyApplyEntity> getYTonnyApplyList(Long yTonnySeq) {

		System.out.println("YTonnyService.getYTonnyApplyList");

		// find
		return yTonnyApplyRepository.findByyTonnySeq(yTonnySeq);

	}


	/**
	 * MEMO : UPDATE
	 * MARK : 예약통역 공고를 수정
	 *
	 * @param yTonnyRequestDto : 수정 예약통역 공고 내용
	 * @return 수정된 예약통역 공고 seq
	 */
	public Long modifyYTonny(YTonnyRequestDto yTonnyRequestDto) {

		System.out.println("YTonnyService.modifyYTonny");

		// find
		YTonnyEntity yTonnyEntity = yTonnyRepository.findById(yTonnyRequestDto.getYTonnySeq())
		                                            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

		// 수정
		// dto -> entity
		yTonnyEntity = YTonnyEntity.builder()
		                           .title(yTonnyRequestDto.getTitle())
		                           .content(yTonnyRequestDto.getContent())
		                           .estimateDate(yTonnyRequestDto.getEstimateDate())
		                           .estimateStartTime(yTonnyRequestDto.getEstimateStartTime())
		                           .estimateTime(yTonnyRequestDto.getEstimateTime())
		                           .startLangCode(yTonnyRequestDto.getStartLangCode())
		                           .endLangCode(yTonnyRequestDto.getEndLangCode())
		                           .tonnySituCode(yTonnyRequestDto.getTonnySituCode())
		                           .build();

		// save
		return yTonnyRepository.save(yTonnyEntity).getSeq();

	}


	/**
	 * MARK : 헬퍼의 예약통역 신청을 수락
	 *
	 * @param yTonnyApplyRequestDto : yTonnySeq, helperSeq
	 * @return 생성된 예약통역 공고 seq
	 */
	public Long acceptYTonnyApply(YTonnyApplyRequestDto yTonnyApplyRequestDto) {

		System.out.println("YTonnyService.acceptYTonnyApply");

		// param setting
		Long yTonnySeq = yTonnyApplyRequestDto.getYTonnySeq();
		Long helperSeq = yTonnyApplyRequestDto.getHelperSeq();

		// find
		//		UserEntity helperEntity = userRepository.findById(helperSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		YTonnyEntity yTonnyEntity = yTonnyRepository.findById(yTonnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

		// 수정
		yTonnyEntity.helperSeq(helperSeq);

		// save
		return yTonnyRepository.save(yTonnyEntity).getSeq();
	}


	/**
	 * MEMO : DELETE
	 * 예약 통역 공고 삭제
	 *
	 * @param yTonnySeq : 삭제할 공고 게시글 Seq
	 * @return 삭제 성공 여부
	 */
	public Long deleteYTonny(Long yTonnySeq) {

		System.out.println("YTonnyService.deleteYTonny");

		// find
		YTonnyEntity yTonnyEntity = yTonnyRepository.findById(yTonnySeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

		// delete
		yTonnyEntity.isDeleted(true);

		// save
		return yTonnyRepository.save(yTonnyEntity).getSeq();

	}


	/**
	 * MARK : (헬퍼가) 예약통역 공고 신청을 취소
	 *
	 * @param yTonnyApplySeq : 공고 신청한 seq
	 * @return
	 */
	public void deleteYTonnyApply(Long yTonnyApplySeq) {

		System.out.println("YTonnyService.deleteYTonnyApply");

		// find
		YTonnyApplyEntity yTonnyApplyEntity = yTonnyApplyRepository.findById(yTonnyApplySeq)
		                                                           .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

		// delete
		yTonnyApplyRepository.delete(yTonnyApplyEntity);

	}

}
