package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.entity.CommonEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
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
	private String isDeleted = "F";


	public void deleteHelperInfoImage() {
		this.isDeleted = "T";
	}

}
