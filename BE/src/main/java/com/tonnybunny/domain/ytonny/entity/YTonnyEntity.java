package com.tonnybunny.domain.ytonny.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.tonnybunny.common.dto.TaskCodeEnum;
import com.tonnybunny.common.dto.TaskStateCodeEnum;
import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(name = "ytonny_table")
public class YTonnyEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity client;
	@Builder.Default
	private Long yTonnyApplySeq = 0L; // nullable

	private String sessionName;

	private String title;
	private String tonnySituCode; // 상황 카테고리
	private String content;

	private String startLangCode;
	private String endLangCode;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime startDateTime; // 날짜 + 시간
	private String estimateTime; // 소요시간
	private Integer estimatePrice; // 지불금액

	@Builder.Default
	private String taskCode = TaskCodeEnum.예약통역.getTaskCode();
	@Builder.Default
	private String taskStateCode = TaskStateCodeEnum.모집중.getTaskStateCode();

	@Builder.Default
	private Boolean isDeleted = false;

	// 헬퍼 신청 리스트
	@Builder.Default
	@OneToMany(mappedBy = "yTonny")
	private List<YTonnyApplyEntity> yTonnyApplyList = new ArrayList<>();

	// 헬퍼 견적서 리스트
	@Builder.Default
	@OneToMany(mappedBy = "yTonny")
	private List<YTonnyQuotationEntity> yTonnyQuotationList = new ArrayList<>();


	public void updateYTonnyApplySeq(Long yTonnyApplySeq) {
		this.yTonnyApplySeq = yTonnyApplySeq;
	}


	public void updateTaskStateCode(String taskStateCode) {
		this.taskStateCode = taskStateCode;
	}


	public void updateEstimatePrice(Integer estimatePrice) {
		this.estimatePrice = estimatePrice;
	}


	public void updateIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public void updateYTonnyApplyList(List<YTonnyApplyEntity> yTonnyApplyList) {
		this.yTonnyApplyList = yTonnyApplyList;
	}


	public void update(String title, String tonnySituCode, String content, String startLangCode, String endLangCode, LocalDateTime startDateTime,
		String estimateTime, Integer estimatePrice) {
		this.title = title;
		this.tonnySituCode = tonnySituCode;
		this.content = content;
		this.startLangCode = startLangCode;
		this.endLangCode = endLangCode;
		this.startDateTime = startDateTime;
		this.estimateTime = estimateTime;
		this.estimatePrice = estimatePrice;
		this.taskCode = taskCode;
		this.taskStateCode = taskStateCode;
	}


	public void complete() {
		this.taskStateCode = TaskStateCodeEnum.완료됨.getTaskStateCode();
	}

}
