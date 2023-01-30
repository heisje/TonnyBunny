package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import com.tonnybunny.domain.bunny.entity.BunnyNotiHelperEntity;
import com.tonnybunny.domain.bunny.entity.BunnyQuotationEntity;
import com.tonnybunny.domain.chat.entity.ChatLogEntity;
import com.tonnybunny.domain.chat.entity.ChatRoomEntity;
import com.tonnybunny.domain.point.entity.PointLogEntity;
import com.tonnybunny.domain.review.entity.ReviewEntity;
import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyNotiHelperEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyQuotationEntity;
import lombok.AccessLevel;
import lombok.Builder;
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
	@OneToMany(mappedBy = "user")
	private List<BlockEntity> blockUserList = new ArrayList<>(); // 차단한 유저
	@OneToMany(mappedBy = "user")
	private List<FollowEntity> followUserList = new ArrayList<>(); // 팔로잉 목록
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private HelperInfoEntity helperInfo;                            // 헬퍼 정보
	// 예약통역
	@OneToMany(mappedBy = "client")
	private List<YTonnyNotiEntity> yTonnyNotiList = new ArrayList<>(); // 예약통역공고리스트
	@OneToMany(mappedBy = "helper")
	private List<YTonnyNotiHelperEntity> yTonnyNotiHelperList = new ArrayList<>(); // 예약통역공고신청자리스트
	// 스케쥴
	@OneToMany(mappedBy = "user")
	private List<ScheduleEntity> scheduleList = new ArrayList<>(); // 고객 스케쥴 리스트

	//	@OneToMany(mappedBy = "client")
	//	private List<YTonnyHistoryEntity> yTonnyClientResultList = new ArrayList<>(); // 예약통역 결과 고객
	//
	//	@OneToMany(mappedBy = "helper")
	//	private List<YTonnyHistoryEntity> yTonnyHelperResultList = new ArrayList<>(); // 예약통역 결과 헬퍼
	//
	//	// 즉시통역
	//	@OneToMany(mappedBy = "client")
	//	private List<JTonnyHistoryEntity> jTonnyClientResultList = new ArrayList<>(); // 즉시통역 결과 고객
	//
	//	@OneToMany(mappedBy = "helper")
	//	private List<JTonnyHistoryEntity> jTonnyHelperResultList = new ArrayList<>(); // 즉시통역 결과 헬퍼
	// 포인트
	@OneToMany(mappedBy = "user")
	private List<PointLogEntity> pointLogList = new ArrayList<>(); // 포인트 사용내역 리스트
	// 알람
	@OneToMany(mappedBy = "user")
	private List<AlertLogEntity> alertLogList = new ArrayList<>(); // 알람 로그 리스트
	@OneToOne(mappedBy = "user")
	private AlertSettingsEntity alertSettings; // 알람 세팅 설정
	// 리뷰
	@OneToMany(mappedBy = "user")
	private List<ReviewEntity> reviewList = new ArrayList<>(); // 리뷰 리스트
	@OneToMany(mappedBy = "client")
	private List<ChatRoomEntity> clientChatRoomList = new ArrayList<>(); // 고객 쪽 채팅방 리스트

	// 채팅방
	@OneToMany(mappedBy = "helper")
	private List<ChatRoomEntity> helperChatRoomList = new ArrayList<>(); // 헬퍼 쪽 채팅방 리스트
	@OneToMany(mappedBy = "user")
	private List<ChatLogEntity> chatLogList = new ArrayList<>(); // 채팅 로그 리스트
	@OneToMany(mappedBy = "user")
	private List<BunnyNotiEntity> bunnyNotiList = new ArrayList<>(); // 번역 공고 리스트

	// 번역 공고
	@OneToMany(mappedBy = "user")
	private List<BunnyNotiHelperEntity> bunnyNotiHelperList = new ArrayList<>(); // 번역 공고 신청 헬퍼 리스트
	@OneToMany(mappedBy = "client")
	private List<BunnyQuotationEntity> clientBunnyQuotationList = new ArrayList<>(); // 고객 측 번역 요청 견적서 리스트

	// 번역 요청 견적서 관련
	@OneToMany(mappedBy = "helper")
	private List<BunnyQuotationEntity> helperBunnyQuotationList = new ArrayList<>(); // 헬퍼 측 번역 요청 견적서 리스트
	@OneToMany(mappedBy = "client")
	private List<YTonnyQuotationEntity> clientYTonnyQuatationList = new ArrayList<>(); // 고객 측 예약 통역 요청 견적서 리스트

	// 예약 통역 견적서 관련
	@OneToMany(mappedBy = "helper")
	private List<YTonnyQuotationEntity> helperYTonnyQuotationList = new ArrayList<>(); // 헬퍼 측 예약 통역 요청 견적서 리스트

	@Builder
	public UserEntity(String email, String password, String phoneNumber, String nickName) {
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.nickName = nickName;
	}


	public void setPoint(Long point) {
		this.point = point;
	}

}
