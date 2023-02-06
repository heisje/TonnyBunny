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
public class HelperInfoEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "helper_info_seq")
	private Long seq;

	private Boolean isActive = false;
	private Float avgScore = 0f;
	private Integer reviewCount = 0;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity user;

	private Integer unitPrice = 0;
	private String oneLineIntroduction;
	private String introduction;

	// 연결
	@OneToMany(mappedBy = "helperInfo")
	private List<PossibleLanguageEntity> possibleLanguageList = new ArrayList<>(); // 사용언어

	@OneToMany(mappedBy = "helperInfo")
	private List<CertificateEntity> certificateList = new ArrayList<>(); // 자격증

	@OneToMany(mappedBy = "helperInfo")
	private List<HelperInfoImageEntity> HelperInfoImageList = new ArrayList<>(); // 프로필이미지 리스트

}
