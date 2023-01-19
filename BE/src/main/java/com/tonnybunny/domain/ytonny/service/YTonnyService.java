package com.tonnybunny.domain.ytonny.service;


import com.tonnybunny.domain.ytonny.dto.YTonnyHelperRequestDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyNotiRequestDto;
import com.tonnybunny.domain.ytonny.dto.YTonnyResultDto;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiHelperEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class YTonnyService {

	/**
	 * 예약 통역 공고 생성
	 *
	 * @param yTonnyNotiRequestDto : 공고 게시글 내용
	 * @return 생성한 공고 게시글의 Seq
	 */
	public Long createYTonnyNoti(YTonnyNotiRequestDto yTonnyNotiRequestDto) {
		// TODO : 구현
		return 0L;
	}


	/**
	 * 예약 통역 공고 수정
	 *
	 * @param yTonnyNotiSeq        : 수정할 공고 게시글 Seq
	 * @param yTonnyNotiRequestDto : 수정할 내용
	 * @return 수정한 공고 게시글의 Seq
	 */
	public Long modifyYTonnyNoti(Long yTonnyNotiSeq, YTonnyNotiRequestDto yTonnyNotiRequestDto) {
		// TODO : 구현
		return 0L;
	}


	/**
	 * 예약 통역 공고 삭제
	 *
	 * @param yTonnyNotiSeq : 삭제할 공고 게시글 Seq
	 * @return 삭제 성공 여부
	 */
	public Boolean deleteYTonnyNoti(Long yTonnyNotiSeq) {
		// TODO : 구현
		return true;
	}


	/**
	 * 예약 통역 공고 목록 조회
	 *
	 * @return 공고 게시글 목록
	 */
	public List<YTonnyNotiEntity> getYTonnyNotiList() {
		// TODO : 구현
		return new ArrayList<>();
	}


	/**
	 * 예약 통역 공고 상세 조회
	 *
	 * @param yTonnyNotiSeq : 대상 공고 게시글 Seq
	 * @return 공고 게시글
	 */
	public YTonnyNotiEntity getYTonnyNoti(Long yTonnyNotiSeq) {
		// TODO : 구현
		return (YTonnyNotiEntity) new Object();
	}


	/**
	 * 예약 통역 신청 필드 추가
	 *
	 * @param yTonnyHelperRequestDto : 신청 폼 내용
	 * @return 생성한 신청 필드의 Seq
	 */
	public Long createYTonnyHelper(YTonnyHelperRequestDto yTonnyHelperRequestDto) {
		// TODO : 구현
		return 0L;
	}


	/**
	 * 예약 통역 신청 삭제
	 *
	 * @param yTonnyHelperSeq : 삭제할 신청 데이터 Seq
	 * @return 삭제 성공 여부
	 */
	public Boolean deleteYTonnyHelper(Long yTonnyHelperSeq) {
		// TODO : 구현
		return true;
	}


	/**
	 * 예약 통역 신청 목록 조회
	 *
	 * @param yTonnyNotiSeq : 신청 목록을 조회하고 싶은 공고 게시글의 Seq
	 * @return 해당 공고에 대한 신청 목록
	 */
	public List<YTonnyNotiHelperEntity> getYTonnyHelperList(Long yTonnyNotiSeq) {
		// TODO : 구현
		return new ArrayList<>();
	}


	/**
	 * 해당 예약 통역 공고에서 수락한 신청 외의 모든 신청 필드들을 삭제
	 *
	 * @param yTonnyNotiSeq   : 대상 공고 게시글 Seq
	 * @param yTonnyHelperSeq : 수락한 신청 Seq
	 * @return 삭제 성공 여부
	 */
	public Boolean matchYTonny(Long yTonnyNotiSeq, Long yTonnyHelperSeq) {
		// TODO : 구현
		return true;
	}


	/**
	 * 예약 통역 종료 후, 결과를 저장
	 *
	 * @param yTonnyResultDto : 예약 통역 완료 후 결과 정보
	 * @return 생성한 예약 통역 결과의 Seq
	 */
	public Long createYTonnyResult(YTonnyResultDto yTonnyResultDto) {
		// TODO : 구현
		return 0L;
	}

}
