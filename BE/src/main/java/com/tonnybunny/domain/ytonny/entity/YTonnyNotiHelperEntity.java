package com.tonnybunny.domain.ytonny.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class YTonnyNotiHelperEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_noti_helper_seq")
	private Long seq;

	private Long yTonnyNotiSeq;

	private Long helperSeq;

	private Long totalPrice;

}
