package com.tonnybunny.domain.ytonny.entity;


import com.tonnybunny.common.CommonEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class YTonnyApplyEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "y_tonny_apply_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "y_tonny_seq")
	private YTonnyEntity yTonny;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_seq")
	private UserEntity helper;

	private Integer totalPrice; // FIXME : unitPrice 로 변경? front 랑 확인

}
