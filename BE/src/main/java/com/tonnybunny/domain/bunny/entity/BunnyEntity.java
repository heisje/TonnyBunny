package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.common.CommonEntity;
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
public class BunnyEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;
	private Long helperSeq;

	private String title;
	private String content;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private Integer estimatePrice;

	private String startLangCode;
	private String endLangCode;
	private String bunnySituCode;
	private String bunnyStateCode = "0100001";

	@Builder.Default
	private String isDeleted = "F";

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
		this.isDeleted = "T";
	}


	public void changeStateCode(String stateCode) {
		this.bunnyStateCode = stateCode;
	}

}
