package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bunny_noti_seq")
	private BunnyNotiEntity bunnyNoti;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;

	private Integer estimatePrice;

}
