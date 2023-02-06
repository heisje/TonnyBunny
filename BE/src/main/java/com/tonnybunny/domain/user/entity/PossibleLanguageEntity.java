package com.tonnybunny.domain.user.entity;


import com.tonnybunny.common.entity.CommonEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "possible_language_table")
public class PossibleLanguageEntity extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "possible_language_seq")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "helper_info_seq")
	private HelperInfoEntity helperInfo;

	private String langCode; // 언어공통코드
	private String langName; // 언어명


	public PossibleLanguageEntity(HelperInfoEntity helperInfo, String langCode) {
		this.helperInfo = helperInfo;
		this.langCode = langCode;
	}

}
