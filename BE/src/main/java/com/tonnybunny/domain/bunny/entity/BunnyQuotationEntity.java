package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BunnyQuotationEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_quotation_seq")
	private Long seq;

	private Long clientSeq;
	private Long helperSeq;

	private LocalDate startDate;
	private LocalDate endDate;

	private String title;
	private String content;

	private int totalPrice;

	private String bunnyQuotationStateCode;
	private String startLangCode;
	private String endLangCode;

}
