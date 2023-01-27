package com.tonnybunny.domain.point.dto;


import lombok.Data;


/**
 * pointRequestType에 따라 요구되는 필드값이 달라짐
 * switch( pointRequestType ){
 * case 충전 : fromUserSeq, pointAmount
 * case 출금 : fromUserSeq, pointAmount, bankAccount(출금할 계좌)
 * case 거래 : fromUserSeq, toUserSeq, pointAmount
 * }
 *
 * fromUserSeq          : (거래 시) 포인트를 주는 유저
 * toUserSeq            : (거래 시) 포인트를 받는 유저
 * pointAmount          : 포인트 양
 * backAccount          : 계좌 번호
 * pointRequestType     : 포인트 거래 유형
 */
@Data
public class PointRequestDto {

	private Long fromUserSeq;

	private Long toUserSeq;

	private Integer pointAmount;

	private String bankAccount;

	private PointRequestTypeEnum pointRequestType;

}
