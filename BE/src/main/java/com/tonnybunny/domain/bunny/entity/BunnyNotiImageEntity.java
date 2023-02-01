package com.tonnybunny.domain.bunny.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tonnybunny.common.CommonEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BunnyNotiImageEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_noti_image_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "bunny_noti_seq")
	private BunnyNotiEntity bunnyNoti;

	private String imagePath;

}
