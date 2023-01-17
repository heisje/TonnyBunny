package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HelperInfoEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "helper_info_seq")
	private Long seq;

	private boolean isActive;
	private float avgScore;
	private Long reviewedCount; // 그냥 reviewCount 도 괜찮을듯

	private Long userSeq;

	private Long unitPrice;
	private String oneLineIntroduction;
	private String introduction;

}
