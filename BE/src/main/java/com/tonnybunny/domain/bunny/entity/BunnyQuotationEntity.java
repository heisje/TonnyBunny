package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

	@Column(name = "bunny_noti_seq")
	private Long bunnyNotiSeq;

	@Column(name = "client_seq")
	private Long clientSeq;
	@Column(name = "helper_seq")
	private Long helperSeq;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private String title;
	private String content;

	private Integer totalPrice;

	private String bunnyQuotationStateCode;
	private String startLangCode;
	private String endLangCode;

	// 견적서
	@OneToMany(mappedBy = "bunnyQuotationSeq")
	@Builder.Default
	private List<BunnyQuotationImageEntity> bunnyQuotationImageList = new ArrayList<>(); // 이미지 리스트

}
