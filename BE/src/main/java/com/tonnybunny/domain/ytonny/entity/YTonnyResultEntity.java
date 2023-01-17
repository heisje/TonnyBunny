package com.tonnybunny.domain.ytonny.entity;


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
public class YTonnyResultEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_result_seq")
	private Long seq;

	private Long reviewSeq;

	private Long clientSeq;

	private Long helperSeq;

	private String title;
	private LocalDateTime startDateTime;
	private String description;
	private LocalTime totalTime;
	private Long totalPrice;
	private String recordVideoPath;

	private String taskCode;
	private String startLangCode;
	private String endLangCode;
	private String tonnySituCode;

}
