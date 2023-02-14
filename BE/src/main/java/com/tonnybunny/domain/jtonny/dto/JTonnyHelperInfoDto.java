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

	private Integer totalScore;
	private Integer reviewCount;
	private String oneLineIntroduction;
	private Integer unitPrice;
	private Integer likeCount;


	public static JTonnyHelperInfoDto fromEntity(HelperInfoEntity helperInfo) {
		return JTonnyHelperInfoDto.builder()
		                          .totalScore(helperInfo.getTotalScore())
		                          .reviewCount(helperInfo.getReviewCount())
		                          .oneLineIntroduction(helperInfo.getOneLineIntroduction())
		                          .unitPrice(helperInfo.getUnitPrice())
		                          .likeCount(helperInfo.getLikeCount())
		                          .build();
	}

}
