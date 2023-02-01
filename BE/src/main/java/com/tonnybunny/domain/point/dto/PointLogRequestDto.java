package com.tonnybunny.domain.point.dto;


import lombok.Data;


/**
 * userSeq      : 조회하고 싶은 대상 유저 seq
 * logCount     : 조회하고 싶은 로그 개수
 */
@Data
public class PointLogRequestDto {

	private Long userSeq;

	private Integer logCount;

}