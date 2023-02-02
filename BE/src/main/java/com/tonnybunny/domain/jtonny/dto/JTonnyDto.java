package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;


@Data
@Builder
public class JTonnyDto {

	/* 즉시 통역 공고 정보 (client 입력) */
	private Long clientSeq;

	private String startLangCode;
	private String endLangCode;
	private String tonnySituCode;
	private String content;
	private LocalTime estimateTime;

	/* 즉시 통역 신청 정보 (helper 입력) */
	private Long helperSeq;

	private Integer unit_price;


	public JTonnyEntity toEntity() {
		return (JTonnyEntity) new Object();
	}

}
