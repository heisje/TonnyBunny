package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BunnyNotiHelperEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_noti_helper_seq")
	private Long seq;

	@Column(name = "bunny_noti_seq")
	private Long bunnyNotiSeq;

	@Column(name = "user_seq")
	private Long userSeq;

	private Integer estimatePrice;

}
