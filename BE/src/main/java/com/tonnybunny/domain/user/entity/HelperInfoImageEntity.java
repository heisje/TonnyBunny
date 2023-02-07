package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.entity.CommonEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "helper_info_image_table")
public class HelperInfoImageEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "helper_info_image_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_info_seq")
	private HelperInfoEntity helperInfo;

	private String imagePath;
	@Builder.Default
	private Boolean isDeleted = false;


	public void deleteHelperInfoImage() {
		this.isDeleted = !isDeleted;
	}

}
