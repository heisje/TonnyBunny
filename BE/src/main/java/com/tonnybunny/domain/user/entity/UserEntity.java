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
public class UserEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_seq")
	private Long seq;

	private String email;
	private String password;
	private String phoneNumber;
	private String nickName;

	private Long point = 0L;
	private Integer reportCount = 0;

	private String profileImagePath;

	private String userCode;

	// 연결
	@OneToMany(mappedBy = "user")
	private List<PossibleLanguageEntity> possibleLanguageList = new ArrayList<>(); // 사용언어

	@OneToMany(mappedBy = "user")
	private List<CertificateEntity> certificateList = new ArrayList<>(); // 자격증

	@OneToMany(mappedBy = "user")
	private List<BlockEntity> blockUserList = new ArrayList<>(); // 차단한 유저

	@OneToMany(mappedBy = "user")
	private List<FollowEntity> followUserList = new ArrayList<>(); // 팔로잉 목록

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private HelperInfoEntity helperInfo;                            // 헬퍼 정보

}
