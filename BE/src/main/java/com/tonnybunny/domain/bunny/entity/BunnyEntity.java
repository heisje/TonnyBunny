package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.entity.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "bunny_table")
public class BunnyEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;
	@Builder.Default
	private Long helperSeq = 0L;

	private String title;
	private String content;

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private Integer estimatePrice;

	private String startLangCode;
	private String endLangCode;
	private String bunnySituCode;
	@Builder.Default
	private String taskCode = "0030003";
	@Builder.Default
	private String taskStateCode = "0090001";

	@Builder.Default
	private Boolean isDeleted = false;

	// 번역 공고 지원 헬퍼 리스트
	@OneToMany(mappedBy = "bunny")
	@Builder.Default
	private List<BunnyApplyEntity> bunnyApplyList = new ArrayList<>();

	// 번역 공고 이미지 리스트
	@OneToMany(mappedBy = "bunny")
	@Builder.Default
	private List<BunnyImageEntity> bunnyImageList = new ArrayList<>();

	// 번역 공고 견적서 리스트
	@OneToMany(mappedBy = "bunny")
	@Builder.Default
	private List<BunnyQuotationEntity> bunnyQuotationList = new ArrayList<>();


	public void delete() {
		this.isDeleted = true;
	}


	public void changeStateCode(String stateCode) {
		this.taskStateCode = stateCode;
	}


	public void changeHelperSeq(Long helperSeq) {
		this.helperSeq = helperSeq;
	}

}
