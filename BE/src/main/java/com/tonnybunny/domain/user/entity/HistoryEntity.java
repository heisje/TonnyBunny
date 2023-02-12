package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.review.entity.ReviewEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "task_code")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "history_table")
public class HistoryEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_seq")
	private UserEntity client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_seq")
	private UserEntity helper;

	private String content;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private String startLangCode;
	private String endLangCode;

	@Column(name = "task_code", insertable = false, updatable = false)
	private String taskCode;

	private Long notiSeq;                   // 즉통, 예통의 경우 NotiSeq, 번역의 경우 QuotationSeq

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "history")
	private ReviewEntity review;


	public void completeLive() {
		this.endDateTime = LocalDateTime.now();
	}

}
