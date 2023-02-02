package com.tonnybunny.domain.ytonny.entity;


import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class YTonnyQuotationImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_quotation_image_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "y_tonny_quotation_seq")
	private YTonnyQuotationEntity yTonnyQuotation;

	private String imageName;
	private String imageContentType;
	private Long imageFileSize;
	private String imagePath;

}
