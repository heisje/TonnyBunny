package com.tonnybunny.domain.bunny.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tonnybunny.common.entity.CommonEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BunnyImageEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bunny_image_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "bunny_seq")
	private BunnyEntity bunny;

	private String imagePath;

}
