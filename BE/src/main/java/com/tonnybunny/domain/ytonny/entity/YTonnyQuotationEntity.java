package com.tonnybunny.domain.ytonny.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class YTonnyQuotationEntity extends CommonEntity {

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

	//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	//	private LocalDateTime startDateTime;
	//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	//	private LocalDateTime endDate;

	private Integer unitPrice;

	@Builder.Default
	private String quotationStateCode = "0070001"; // 미선택

	// 아래는 YTonnyEntity 내용
	private String title;
	private String content;

	private String startLangCode;
	private String endLangCode;

	//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDateTime startDateTime;
	private LocalTime estimateTime;

	@Builder.Default
	@OneToMany(mappedBy = "yTonnyQuotation")
	private List<YTonnyQuotationImageEntity> yTonnyQuotationImageList = new ArrayList<>(); // 견적서 이미지 리스트


	public void yTonnyQuotationImageList(List<YTonnyQuotationImageEntity> yTonnyQuotationImageList) {
		this.yTonnyQuotationImageList = yTonnyQuotationImageList;
	}


	public void quotationStateCode(String quotationStateCode) {
		this.quotationStateCode = quotationStateCode;
	}

}


