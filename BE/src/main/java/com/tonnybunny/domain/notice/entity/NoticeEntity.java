package com.tonnybunny.domain.notice.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_seq")
	private Long seq;
	private Long userSeq;   // 역(반?)정규화 - erd 팀에 확인하기

	private String title;
	private String content;

}
