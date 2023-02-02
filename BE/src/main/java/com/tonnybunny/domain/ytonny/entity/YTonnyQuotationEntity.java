package com.tonnybunny.domain.ytonny.entity;


import com.tonnybunny.common.dto.LangCodeEnum;
import com.tonnybunny.common.dto.QuotationStateCodeEnum;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class YTonnyQuotationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_quotation_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "y_tonny_seq")
	private YTonnyEntity yTonny;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_seq")
	private UserEntity client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_seq")
	private UserEntity helper;

	private Integer unitPrice;
	private QuotationStateCodeEnum quotationStateCode;

	private String title;
	private String content;

	private LocalDate estimateDate;
	private LocalTime estimateStartTime;
	private LocalTime estimateTime;

	private LangCodeEnum startLangCode;
	private LangCodeEnum endLangCode;

	@Builder.Default
	@OneToMany(mappedBy = "yTonnyQuotation")
	private List<YTonnyQuotationImageEntity> yTonnyQuotationImageList = new ArrayList<>(); // 견적서 이미지 리스트

}


