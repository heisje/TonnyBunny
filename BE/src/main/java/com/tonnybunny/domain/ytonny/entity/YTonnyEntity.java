package com.tonnybunny.domain.ytonny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.common.dto.LangCodeEnum;
import com.tonnybunny.common.dto.TaskCodeEnum;
import com.tonnybunny.common.dto.TaskStateCodeEnum;
import com.tonnybunny.common.dto.TonnySituCodeEnum;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class YTonnyEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_noti_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity client;
	private Long helperSeq;

	private String title;
	private String content;

	private LangCodeEnum startLangCode;
	private LangCodeEnum endLangCode;

	private LocalDate estimateDate;
	private LocalTime estimateStartTime;
	private LocalTime estimateTime;
	private Integer estimatePrice;

	private TonnySituCodeEnum tonnySituCode;
	private TaskCodeEnum taskCode;
	private TaskStateCodeEnum taskStateCode;

	private Boolean isDeleted;

	// 헬퍼 신청 리스트
	@OneToMany(mappedBy = "yTonnyNoti")
	private List<YTonnyApplyEntity> yTonnyApplyList = new ArrayList<>();

	// 헬퍼 견적서 리스트
	@OneToMany(mappedBy = "yTonnyNoti")
	private List<YTonnyQuotationEntity> yTonnyQuotationList = new ArrayList<>();

}
