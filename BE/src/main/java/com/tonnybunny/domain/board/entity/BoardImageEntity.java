package com.tonnybunny.domain.board.entity;


import com.tonnybunny.common.CommonEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // @Builder와 같이 씀
@Builder
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
