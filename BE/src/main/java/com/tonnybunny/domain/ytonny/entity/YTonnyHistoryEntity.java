package com.tonnybunny.domain.ytonny.entity;


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
public class YTonnyHistoryEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_history_seq")
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

	private String title;
	private LocalDateTime startDateTime;
	private String description;
	private LocalTime totalTime;
	private int totalPrice;
	private String recordVideoPath;

	private String taskCode;
	private String startLangCode;
	private String endLangCode;
	private String tonnySituCode;

}