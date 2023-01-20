package com.tonnybunny.domain.jtonny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.review.entity.ReviewEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
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

	@OneToOne // 단방향 1대1 매핑
	@JoinColumn(name = "review_seq")
	private ReviewEntity review;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_seq")
	private UserEntity client;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_seq")
	private UserEntity helper;

	private LocalTime totalTime;    // 타입 체크 필요

	private String tonnySituCode;

	private String description;

	private LocalDateTime startDateTime;

	private Integer unitPrice;
	private Integer totalPrice;

	private String recordVideoPath;

	private String taskCode;
	private String startLangCode;
	private String endLangCode;

}
