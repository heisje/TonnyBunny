package com.tonnybunny.domain.schedule.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_seq")
	private Long seq;

	private Long userSeq;

	private String title;

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private String taskCode;

	private String content;

	private boolean isComplete;

}
