package com.tonnybunny.domain.point.service;


import com.tonnybunny.domain.point.dto.PointLogRequestDto;
import com.tonnybunny.domain.point.dto.PointRequestDto;
import com.tonnybunny.domain.point.entity.PointLogEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PointService {

	/**
	 * 포인트 로그 목록 조회
	 *
	 * @param pointLogRequestDto : 대상 유저, 조회할 로그 개수
	 * @return 포인트 로그 목록
	 */
	public List<PointLogEntity> getPointLogList(PointLogRequestDto pointLogRequestDto) {
		// TODO : 구현

		return new ArrayList<>();
	}


	/**
	 * 대상 유저의 포인트의 양을 조회
	 *
	 * @param userSeq : 조회할 대상 유저
	 * @return 대상 유저의 포인트 양
	 */
	public Integer getPoint(Long userSeq) {
		// TODO : 구현

		return 0;
	}


	/**
	 * 포인트 충전 :
	 * - 대상 유저(fromUser)의 포인트 양을 추가(addPoint()), 포인트 로그에 추가
	 *
	 * @param pointRequestDto : 대상 유저 seq, 포인트 양 정보 포함
	 * @return 포인트 충전 성공 여부
	 */
	public Boolean buyPoint(PointRequestDto pointRequestDto) {
		// TODO : 구현

		return true;
	}


	/**
	 * 포인트 거래 :
	 * - 소비 유저(fromUserSeq) 포인트 양 감소(subtractPoint()), 포인트 로그에 추가
	 * - 획득 유저(toUserSeq) 포인트 양 증(addPoint())가, 포인트 로그에 추가
	 *
	 * @param pointRequestDto : 소비 유저 seq, 획득 유저 seq, 포인트 양 정보 포함
	 * @return 포인트 거래 성공 여부
	 */
	public Boolean dealPoint(PointRequestDto pointRequestDto) {
		// TODO : 구현

		return true;
	}


	/**
	 * 포인트 출금 :
	 * - 대상 유저 포인트 양 감소(subtractPoint()), 계좌 거래 후, 포인트 로그에 추가
	 *
	 * @param pointRequestDto : 대상 유저 seq, 포인트 양, 계좌 번호 정보 포함
	 * @return
	 */
	public Boolean withdrawPoint(PointRequestDto pointRequestDto) {
		// TODO : 구현

		return true;
	}


	/**
	 * 대상 유저의 포인트 증가
	 *
	 * @param userSeq     : 대상 유저 Seq
	 * @param pointAmount : 증가되는 포인트 양
	 * @return 증가 성공 여부
	 */
	private Boolean addPoint(Long userSeq, Integer pointAmount) {
		// TODO : 구현

		return true;
	}


	/**
	 * 대상 유저의 포인트 감소
	 *
	 * @param userSeq     : 대상 유저 Seq
	 * @param pointAmount : 감소하는 포인트 양
	 * @return 감소 성공 여부
	 */
	private Boolean subtractPoint(Long userSeq, Integer pointAmount) {
		// TODO : 구현

		return true;
	}

}
