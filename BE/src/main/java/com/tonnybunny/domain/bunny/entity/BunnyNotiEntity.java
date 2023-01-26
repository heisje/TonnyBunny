package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BunnyNotiEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_noti_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;
	private Long helperSeq;

	private String title;
	private String content;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private Integer estimatePrice;

	private String startLangCode;
	private String endLangCode;
	private String bunnySituCode;
	private String bunnyStateCode;

	// 번역 공고 지원 헬퍼 리스트
	@OneToMany(mappedBy = "bunnyNoti")
	private List<BunnyNotiHelperEntity> bunnyNotiHelperList = new ArrayList<>();

	// 번역 공고 이미지 리스트
	@OneToMany(mappedBy = "bunnyNoti")
	private List<BunnyNotiImageEntity> bunnyNotiImageList = new ArrayList<>();

	// 번역 공고 견적서 리스트
	@OneToMany(mappedBy = "bunnyNoti")
	private List<BunnyQuotationEntity> bunnyQuotationList = new ArrayList<>();

}
