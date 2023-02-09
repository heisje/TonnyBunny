package com.tonnybunny.domain.review.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.HistoryEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review_table")
public class ReviewEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user; // M:1 매핑

	private Integer score;

	private String comment;

	@Builder.Default
	private Boolean isDeleted = false;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "history_seq")
	private HistoryEntity history;


	public void deleteReview() {
		this.isDeleted = true;
	}

}
