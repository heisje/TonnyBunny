package com.tonnybunny.domain.jtonny.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JTonnyResultEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "j_tonny_result_seq")
	private Long seq;

	private Long clientSeq;
	private Long helperSeq;

	private LocalTime totalTime;    // 타입 체크 필요

	private String tonnySituCode;

	private String description;

	private LocalDateTime startDateTime;

	private Long unitPrice;
	private Long totalPrice;

	private String recordVideoPath;

	private Long reviewSeq;

	private String taskCode;
	private String startLangCode;
	private String endLangCode;

}
