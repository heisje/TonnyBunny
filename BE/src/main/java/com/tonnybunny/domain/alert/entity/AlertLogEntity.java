package com.tonnybunny.domain.alert.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@Table(name = "alert_log_table")
public class AlertLogEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alert_log_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user; // M:1 매핑

	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@JoinColumn(name = "j_tonny_seq")
	//	private JTonnyEntity jTonny; // M:1 매핑
	//
	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@JoinColumn(name = "y_tonny_seq")
	//	private YTonnyEntity yTonny; // M:1 매핑
	//
	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@JoinColumn(name = "bunny_seq")
	//	private BunnyEntity bunny; // M:1 매핑

	private String taskCode;
	private String content;
	private Boolean isRead;


	public void update(Boolean isRead) {
		this.isRead = isRead;
	}

}
