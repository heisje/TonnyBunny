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

	private Long point;
	private int reportCount;

	private String profileImagePath;

	private String userCode;

	// 연결
	@OneToMany(mappedBy = "userSeq")
	private List<PossibleLanguageEntity> possibleLanguageList = new ArrayList<>(); // 사용언어

	@OneToMany(mappedBy = "userSeq")
	private List<CertificateEntity> certificateList = new ArrayList<>(); // 자격증

	//	@OneToMany(mappedBy = "userSeq")
	//	private List<BlockEntity> blockUserList = new ArrayList<>(); // 차단한 유저
	//
	//	@OneToMany(mappedBy = "blockedUserSeq")
	//	private List<BlockEntity> blockedUserList = new ArrayList<>(); // 차단된 유저
	//
	//	@OneToMany(mappedBy = "followerSeq")
	//	private List<FollowEntity> followerList = new ArrayList<>(); // 팔로워 목록
	//
	//	@OneToMany(mappedBy = "followingSeq")
	//	private List<FollowEntity> followingList = new ArrayList<>(); // 팔로잉 목록

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userSeq", cascade = {
		CascadeType.REMOVE })
	private HelperInfoEntity helperInfo;                            // 헬퍼 정보

}
