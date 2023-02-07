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

	private String taskCode;
	private String content;
	private Boolean isRead;
	private Boolean isEnd; // 끝이 났는지 아닌지


	public void update(Boolean isRead, Boolean isEnd) {
		this.isRead = isRead;
		this.isEnd = isEnd;
	}

}
