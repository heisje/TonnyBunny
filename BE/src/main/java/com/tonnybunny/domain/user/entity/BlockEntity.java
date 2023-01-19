package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "block_seq")
	private Long seq;

	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@JoinColumn(name="user_seq")
	//	private UserEntity userSeq;
	//
	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@JoinColumn(name="user_seq")
	//	private UserEntity blockedUserSeq;

}
