package com.tonnybunny.domain.jtonny.dto;


import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class JTonnyHelperInfoDto {

	private Float avgScore;
	private Integer reviewCount;
	private String oneLineIntroduction;
	private Integer likeCount;


	public static JTonnyHelperInfoDto fromEntity(HelperInfoEntity helperInfo) {
		return JTonnyHelperInfoDto.builder()
		                          .avgScore(helperInfo.getAvgScore())
		                          .reviewCount(helperInfo.getReviewCount())
		                          .oneLineIntroduction(helperInfo.getOneLineIntroduction())
		                          .likeCount(helperInfo.getLikeCount())
		                          .build();
	}

}
