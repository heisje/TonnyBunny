package com.tonnybunny.domain.alert.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlertSettingsEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alert_settings_seq")
	private Long seq;

	private boolean isAll;
	private boolean isTonnyBunny;
	private boolean isCommunity;
	private boolean isChat;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;


	@Builder
	public AlertSettingsEntity(Long alertSettingsSeq, boolean isAll, boolean isTonnyBunny, boolean isCommunity, boolean isChat, UserEntity user) {
		this.seq = alertSettingsSeq;
		this.isAll = isAll;
		this.isTonnyBunny = isTonnyBunny;
		this.isCommunity = isCommunity;
		this.isChat = isChat;
		this.user = user;
	}

}
