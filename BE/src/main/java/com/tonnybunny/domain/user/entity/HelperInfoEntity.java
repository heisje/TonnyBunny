package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HelperInfoEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "helper_info_seq")
	private Long seq;

	private boolean isActive;
	private float avgScore;
	private int reviewCount;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq")
	private UserEntity userSeq;

	private int unitPrice;
	private String oneLineIntroduction;
	private String introduction;

	@OneToMany(mappedBy = "helperInfoSeq")
	private List<CertificateEntity> HelperInfoImageList = new ArrayList<>(); // 프로필이미지 리스트

}
