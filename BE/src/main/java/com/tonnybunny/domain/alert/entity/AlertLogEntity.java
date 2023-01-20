package com.tonnybunny.domain.alert.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlertLogEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alert_log_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user; // M:1 매핑
	private String content;
	private boolean isRead;

}
