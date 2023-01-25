package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BunnyQuotationEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_quotation_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bunny_noti_seq")
	private BunnyNotiEntity bunnyNoti;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_seq")
	private UserEntity client;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_seq")
	private UserEntity helper;

	private LocalDate startDate;
	private LocalDate endDate;

	private String title;
	private String content;

	private int totalPrice;

	private String bunnyQuotationStateCode;
	private String startLangCode;
	private String endLangCode;

	// 견적서
	@OneToMany(mappedBy = "bunnyQuotation")
	private List<BunnyQuotationImageEntity> bunnyQuotationImageList = new ArrayList<>(); // 이미지 리스트

}
