package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.entity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "helper_info_table")
public class HelperInfoEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "helper_info_seq")
	private Long seq;

	private Boolean isActive = false;
	private Integer totalScore = 0;
	private Integer reviewCount = 0;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;

	@Builder.Default
	private Integer unitPrice = 0;

	@Builder.Default
	private String oneLineIntroduction = "안녕하세요, 언제나 성심성의껏 도와드리겠습니다.";
	@Builder.Default
	private String introduction = "안녕하세요, 당신을 도와드릴 토니버니의 헬퍼입니다. 언제나 성심성의껏 도와드리겠습니다.";

	// 연결
	@OneToMany(mappedBy = "helperInfo")
	@Builder.Default
	private List<PossibleLanguageEntity> possibleLanguageList = new ArrayList<>(); // 사용언어

	@OneToMany(mappedBy = "helperInfo")
	@Builder.Default
	private List<CertificateEntity> certificateList = new ArrayList<>(); // 자격증

	@OneToMany(mappedBy = "helperInfo")
	@Builder.Default
	private List<HelperInfoImageEntity> helperInfoImageList = new ArrayList<>(); // 프로필이미지 리스트

	@Builder.Default
	private Integer likeCount = 0;


	public void updateCertificateList(List<CertificateEntity> certificateList) {
		this.certificateList = certificateList;
	}


	public void updatePossibleLanguageList(List<PossibleLanguageEntity> possibleLanguageList) {
		this.possibleLanguageList = possibleLanguageList;
	}


	public void updateHelperInfoImageList(List<HelperInfoImageEntity> helperInfoImageList) {
		this.helperInfoImageList = helperInfoImageList;
	}


	public void updateOneLineIntroduction(String oneLineIntroduction) {
		this.oneLineIntroduction = oneLineIntroduction;
	}


	public void updateIntroduction(String introduction) {
		this.introduction = introduction;
	}


	public void updateLikedCount(Integer like) {
		this.likeCount += like;
	}


	public void updateUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

}
