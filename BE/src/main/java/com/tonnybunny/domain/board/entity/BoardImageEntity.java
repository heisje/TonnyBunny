package com.tonnybunny.domain.board.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardImageEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_image_seq")
	private Long seq;

	@ManyToOne(targetEntity = BoardEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "board_seq")
	private BoardEntity board;
	
	private String imagePath;

}
