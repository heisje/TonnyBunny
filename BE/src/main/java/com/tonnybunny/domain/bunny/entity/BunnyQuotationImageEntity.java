package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BunnyQuotationImageEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_quotation_image_seq")
	private Long seq;

	@Column(name = "bunny_quotation_seq")
	private Long bunnyQuotationSeq;

	private String imagePath;

}
