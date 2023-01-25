package com.tonnybunny.domain.jtonny.service;


import com.tonnybunny.domain.jtonny.dto.JTonnyNotiHelperRequestDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyNotiRequestDto;
import com.tonnybunny.domain.jtonny.entity.JTonnyNotiEntity;
import com.tonnybunny.domain.jtonny.entity.JTonnyNotiHelperEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JTonnyService {

	/**
	 * 즉시 통역 공고 생성
	 *
	 * @param jTonnyNotiRequestDto : 즉시 통역 공고 생성 폼
	 * @return : 생성된 공고 seq
	 */
	public Long createJTonnyNoti(JTonnyNotiRequestDto jTonnyNotiRequestDto) {
		// TODO : 로직

		JTonnyNotiEntity jTonnyNoti = jTonnyNotiRequestDto.toEntity();
		return jTonnyNoti.getSeq();
	}


	/**
	 * 즉시 통역 공고 삭제
	 *
	 * @param jTonnySeq : 삭제할 공고 seq
	 * @return : 로직 성공 여부
	 */
	public Boolean deleteJTonnyNoti(Long jTonnySeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 즉시 통역 공고 목록 조회
	 *
	 * @return : 즉시 통역 공고 Entity List
	 */
	public List<JTonnyNotiEntity> getJTonnyNotiList() {
		// TODO : 로직

		return new ArrayList<>();
	}


	/**
	 * 즉시 통역 공고 상세 조회
	 *
	 * @param jTonnySeq : 조회할 공고 seq
	 * @return : 조회된 공고 Entity
	 */
	public JTonnyNotiEntity getJTonnyNoti(Long jTonnySeq) {
		// TODO : 로직

		return (JTonnyNotiEntity) new Object();
	}


	/**
	 * 즉시 통역 공고 신청 생성
	 *
	 * @param jTonnyNotiHelperRequestDto : 즉시 통역 공고 신청 폼
	 * @return : 생성된 신청 seq
	 */
	public Long createJTonnyNotiHelper(JTonnyNotiHelperRequestDto jTonnyNotiHelperRequestDto) {
		// TODO : 로직

		JTonnyNotiHelperEntity jTonnyNotiHelper = jTonnyNotiHelperRequestDto.toEntity();
		return jTonnyNotiHelper.getSeq();
	}


	/**
	 * 즉시 통역 공고 신청 삭제
	 *
	 * @param jTonnyHelperSeq
	 * @return
	 */
	public Boolean deleteJTonnyNotiHelper(Long jTonnyHelperSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 즉시 통역 공고 신청 목록 조회
	 *
	 * @return 즉시 통역 공고 신청 Entity List
	 */
	public List<JTonnyNotiHelperEntity> getJTonnyNotiHelperList() {
		// TODO : 로직

		return new ArrayList<>();
	}


	/**
	 * 즉시 통역 공고 신청 상세 조회
	 *
	 * @param jTonnyHelperSeq : 조회할 신청 seq
	 * @return : 조회된 신청 Entity
	 */
	public JTonnyNotiHelperEntity getJTonnyNotiHelper(Long jTonnyHelperSeq) {
		// TODO : 로직

		return (JTonnyNotiHelperEntity) new Object();
	}


	/**
	 * 즉시 통역 공고 신청 수락
	 *
	 * @param jTonnyHelperSeq : 수락할 공고 seq
	 * @return : 로직 성공 여부
	 */
	public Boolean acceptJTonnyRequestFromHelper(Long jTonnyHelperSeq) {
		// TODO : 로직

		/**
		 * 수락한 신청 외의 신청에 이미 종료됨을 알리기
		 */
		return true;
	}


	/**
	 * 즉시 통역 공고 신청 거절
	 *
	 * @param jTonnyHelperSeq : 거절할 공고 seq
	 * @return
	 */
	public Boolean rejectJTonnyRequestFromHelper(Long jTonnyHelperSeq) {
		// TODO : 로직

		return true;
	}
	
}
