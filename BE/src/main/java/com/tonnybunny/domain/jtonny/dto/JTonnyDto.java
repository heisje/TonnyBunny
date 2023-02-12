package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.jtonny.entity.JTonnyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class JTonnyDto {

	private Long seq;
	private String sessionName;

	/* 즉시 통역 공고 정보 (client 입력) */
	private JTonnyUserDto client;

	private String taskCode;
	private String taskStateCode;
	private String startLangCode;
	private String endLangCode;
	private String tonnySituCode;
	private String content;

	private String estimateTime; // 소요시간

	/* 즉시 통역 신청 정보 (helper 입력) */
	private JTonnyUserDto helper;

	private Integer unitPrice;

	/* toEntity 는 UserEntity 가 필요해서 service 에서 생성 */


	public static JTonnyDto fromEntity(JTonnyEntity jTonny) {
		return JTonnyDto.builder()
		                .seq(jTonny.getSeq())
		                .sessionName(UUID.randomUUID().toString())
		                .client(new JTonnyUserDto(jTonny.getClient()))
		                .helper(new JTonnyUserDto(jTonny.getHelper()))
		                .taskCode(jTonny.getTaskCode())
		                .taskStateCode(jTonny.getTaskStateCode())
		                .startLangCode(jTonny.getStartLangCode())
		                .endLangCode(jTonny.getEndLangCode())
		                .tonnySituCode(jTonny.getTonnySituCode())
		                .content(jTonny.getContent())
		                .estimateTime(jTonny.getEstimateTime())
		                .unitPrice(jTonny.getUnitPrice())
		                .build();
	}

}
