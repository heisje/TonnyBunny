package com.tonnybunny.domain.ytonny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class YTonnyNotiEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_noti_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity client;

	private String title;

	private LocalTime estimateTime;

	private String startLangCode;
	private String endLangCode;
	private String taskCode;
	private String tonnySituCode;
	private String taskStateCode;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "yTonnyNoti")
	private YTonnyNotiHelperEntity yTonnyNotiHelper;

}
