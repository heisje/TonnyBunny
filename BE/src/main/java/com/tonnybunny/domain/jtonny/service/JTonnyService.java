package com.tonnybunny.domain.jtonny.service;


import com.tonnybunny.domain.jtonny.dto.JTonnyApplyRequestDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyRequestDto;
import com.tonnybunny.domain.jtonny.entity.JTonnyApplyEntity;
import com.tonnybunny.domain.jtonny.entity.JTonnyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class JTonnyService {

	private final RedisTemplate<String, Object> redisTemplate;


	/**
	 * 즉시 통역 공고 생성
	 *
	 * @param jTonnyRequestDto : 즉시 통역 공고 생성 폼
	 * @return : 생성된 공고 seq
	 */
	public void createJTonny(JTonnyRequestDto jTonnyRequestDto) {
		JTonnyEntity jTonny = jTonnyRequestDto.toEntity();
		redisTemplate.convertAndSend("jtonny/request", jTonny);
	}


	/**
	 * 즉시 통역 공고 신청 생성
	 *
	 * @param jTonnyApplyRequestDto : 즉시 통역 공고 신청 폼
	 * @return : 생성된 신청 seq
	 */
	public void createJTonnyApply(JTonnyApplyRequestDto jTonnyApplyRequestDto) {
		JTonnyApplyEntity jTonnyApply = jTonnyApplyRequestDto.toEntity();
		redisTemplate.convertAndSend("jtonny/apply", jTonnyApply);
	}


	/**
	 * MEMO : READ
	 * 즉시 통역 공고 목록 조회
	 *
	 * @return : 즉시 통역 공고 Entity List
	 */
	public List<JTonnyEntity> getJTonnyList() {
		// TODO : 로직

		return new ArrayList<>();
	}


	/**
	 * 즉시 통역 공고 상세 조회
	 *
	 * @param jTonnySeq : 조회할 공고 seq
	 * @return : 조회된 공고 Entity
	 */
	public JTonnyEntity getJTonnyDetail(Long jTonnySeq) {
		// TODO : 로직

		return (JTonnyEntity) new Object();
	}


	/**
	 * 즉시 통역 공고 신청 목록 조회
	 *
	 * @return 즉시 통역 공고 신청 Entity List
	 */
	public List<JTonnyApplyEntity> getJTonnyApplyList() {
		// TODO : 로직

		return new ArrayList<>();
	}


	/**
	 * 즉시 통역 공고 신청 상세 조회
	 *
	 * @param jTonnyHelperSeq : 조회할 신청 seq
	 * @return : 조회된 신청 Entity
	 */
	public JTonnyApplyEntity getJTonnyApplyDetail(Long jTonnyHelperSeq) {
		// TODO : 로직

		return (JTonnyApplyEntity) new Object();
	}


	/**
	 * MEMO : PUT
	 * 즉시 통역 공고 신청 수락
	 *
	 * @param jTonnyHelperSeq : 수락할 공고 seq
	 * @return : 로직 성공 여부
	 */
	public Boolean acceptJTonnyApply(Long jTonnyHelperSeq) {
		// TODO : 로직

		/**
		 * 수락한 신청 외의 신청에 이미 종료됨을 알리기
		 */
		return true;
	}


	/**
	 * MEMO : DELETE
	 * 즉시 통역 공고 삭제
	 *
	 * @param jTonnySeq : 삭제할 공고 seq
	 * @return : 로직 성공 여부
	 */
	public Boolean deleteJTonny(Long jTonnySeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 즉시 통역 공고 신청 삭제
	 *
	 * @param jTonnyHelperSeq
	 * @return
	 */
	public Boolean deleteJTonnyApply(Long jTonnyHelperSeq) {
		// TODO : 로직

		return true;
	}


	/**
	 * 즉시 통역 공고 신청 거절
	 *
	 * @param jTonnyHelperSeq : 거절할 공고 seq
	 * @return
	 */
	public Boolean rejectJTonnyApply(Long jTonnyHelperSeq) {
		// TODO : 로직

		return true;
	}

}
