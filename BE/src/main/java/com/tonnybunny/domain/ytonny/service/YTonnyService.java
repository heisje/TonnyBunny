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
import com.tonnybunny.domain.ytonny.repository.YTonnyRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class YTonnyService {

	private final UserRepository userRepository;
	private final YTonnyRepository yTonnyRepository;


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

		// entity 로 변경
		YTonnyEntity yTonnyEntity = YTonnyEntity.builder()
		                                        .client(userEntity)
		                                        .title(yTonnyRequestDto.getTitle())
		                                        .content(yTonnyRequestDto.getContent())
		                                        .startLangCode(LangCodeEnum.한국어)
		                                        .endLangCode(yTonnyRequestDto.getEndLangCode())
		                                        .estimateDate(yTonnyRequestDto.getEstimateDate())
		                                        .estimateStartTime(yTonnyRequestDto.getEstimateStartTime())
		                                        .estimateTime(yTonnyRequestDto.getEstimateTime())
		                                        .estimatePrice(yTonnyRequestDto.getEstimatePrice())
		                                        .tonnySituCode(yTonnyRequestDto.getTonnySituCode())
		                                        .taskCode(TaskCodeEnum.예약통역) // enum 생성하기
		                                        .taskStateCode(TaskStateCodeEnum.모집중)
		                                        .isDeleted(false)
		                                        .build();

		// save
		return yTonnyRepository.save(yTonnyEntity).getSeq();

	}


	/**
	 * MARK : (헬퍼가) 예약통역 공고 신청을 생성
	 *
	 * @param yTonnyApplyRequestDto : 신청 폼 내용
	 * @return 생성한 신청 필드의 Seq
	 */
	public Long createYTonnyApply(YTonnyApplyRequestDto yTonnyApplyRequestDto) {
		// TODO : 구현
		return 0L;
	}


	/**
	 * MEMO : READ
	 * MARK : 예약통역 공고 목록을 조회 with pagination
	 *
	 * @param yTonnyRequestDto
	 * @return 예약통역 공고 목록 반환
	 */
	public List<YTonnyEntity> getYTonnyList(YTonnyRequestDto yTonnyRequestDto) {
		// TODO : 구현
		return new ArrayList<>();
	}


	/**
	 * MARK : 예약통역 공고 목록을 상세 조회
	 *
	 * @param yTonnyNotiSeq
	 * @return 생성된 예약통역 공고 seq
	 */
	public YTonnyEntity getYTonnyDetail(Long yTonnyNotiSeq) {
		// TODO : 구현
		return (YTonnyEntity) new Object();
	}


	/**
	 * MARK : 예약통역 공고 신청 목록을 상세 조회
	 *
	 * @param yTonnyNotiSeq
	 * @return 생성된 예약통역 공고 seq
	 */
	public List<YTonnyApplyEntity> getYTonnyApplyList(Long yTonnyNotiSeq) {
		// TODO : 구현
		return new ArrayList<>();
	}


	/**
	 * MEMO : UPDATE
	 * MARK : 예약통역 공고를 생성
	 *
	 * @param yTonnyRequestDto
	 * @return 생성된 예약통역 공고 seq
	 */
	public Long modifyYTonny(YTonnyRequestDto yTonnyRequestDto) {
		// TODO : 구현
		return 0L;
	}


	/**
	 * 해당 예약 통역 공고에서 수락한 신청 외의 모든 신청 필드들을 삭제
	 *
	 * @param yTonnyNotiSeq       : 대상 공고 게시글 Seq
	 * @param yTonnyNotiHelperSeq : 수락한 신청 Seq
	 * @return 삭제 성공 여부
	 */
	public Long matchYTonny(Long yTonnyNotiSeq, Long yTonnyNotiHelperSeq) {
		// TODO : 구현
		return 0L;
	}


	/**
	 * MEMO : DELETE
	 * 예약 통역 공고 삭제
	 *
	 * @param yTonnyNotiSeq : 삭제할 공고 게시글 Seq
	 * @return 삭제 성공 여부
	 */
	public Boolean deleteYTonny(Long yTonnyNotiSeq) {
		// TODO : 구현
		return true;
	}


	/**
	 * 예약 통역 신청 삭제
	 *
	 * @param yTonnyNotiHelperSeq : 삭제할 신청 데이터 Seq
	 * @return 삭제 성공 여부
	 */
	public Boolean deleteYTonnyApply(Long yTonnyNotiHelperSeq) {
		// TODO : 구현
		return true;
	}

}
