package com.tonnybunny.domain.schedule.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(name = "schedule_table")
public class ScheduleEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user; // M:1 매핑

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private String title;
	private String taskCode;
	private String content;

	@Builder.Default
	private Boolean isComplete = false;


	public void updateIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}

}