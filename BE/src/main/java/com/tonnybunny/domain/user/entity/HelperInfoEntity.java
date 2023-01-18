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
	private int reviewCount;

	private Long userSeq;

	private int unitPrice;
	private String oneLineIntroduction;
	private String introduction;

}
