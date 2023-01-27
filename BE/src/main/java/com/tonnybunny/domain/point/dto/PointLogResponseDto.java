package com.tonnybunny.domain.point.dto;


import com.tonnybunny.domain.point.entity.PointLogEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 포인트 로그 Seq
 * amount               : 포인트 양
 * pointRequestType     : 충전/출금/거래 공통 코드
 * userSeq              : 포인트 사용자 Seq
 * created_at           : 포인트 로그 생성 날짜
 */
@Data
public class PointLogResponseDto {

	private Long seq;

	private Integer amount;

	private PointRequestTypeEnum pointRequestType;

	private Integer userSeq;

	private LocalDateTime created_at;


	public static PointLogResponseDto fromEntity(PointLogEntity pointLog) {
		return new PointLogResponseDto();
	}


	public static List<PointLogResponseDto> fromEntityList(List<PointLogEntity> pointLogList) {
		List<PointLogResponseDto> result = new ArrayList<>();
		for (PointLogEntity pointLog : pointLogList) {
			PointLogResponseDto pointLogResponseDto = fromEntity(pointLog);
			result.add(pointLogResponseDto);
		}
		return result;
	}

}
