package com.tonnybunny.domain.jtonny.entity;


import com.tonnybunny.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "jtonny_table")
public class JTonnyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "j_tonny_seq")
	private Long seq;

	@ManyToOne
	@JoinColumn(name = "client_seq")
	private UserEntity client;

	@ManyToOne
	@JoinColumn(name = "helper_seq")
	private UserEntity helper;

	private String taskCode;
	private String taskStateCode;

	private String startLangCode;
	private String endLangCode;
	private String tonnySituCode;
	private String content;
	private LocalTime estimateTime;
	private Integer unitPrice;

}
