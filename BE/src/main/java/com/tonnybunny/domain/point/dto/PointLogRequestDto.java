package com.tonnybunny.domain.point.dto;


import com.tonnybunny.domain.point.entity.PointLogEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @field userSeq : 조회하고 싶은 대상 유저 seq
 * @field logCount : 조회하고 싶은 로그 개수
 */
@Data
public class PointLogRequestDto {
	private Long userSeq;

	private Integer logCount;

	public PointLogEntity toEntity(PointLogRequestDto pointLogRequestDto){
		return (PointLogEntity) new Object();
	}

}
