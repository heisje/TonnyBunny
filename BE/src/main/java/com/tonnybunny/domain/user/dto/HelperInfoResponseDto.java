package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * seq                  : 반환하는 헬퍼 정보의 키 값
 *
 * isActive             : 해당 헬퍼의 콜 수신 여부
 * avgScore             : 평점
 * reviewCount          : 헬퍼가 받은 리뷰 개수
 * unitPrice            : 헬퍼가 희망하는 작업 단가
 * oneLineIntroduction  : 한 줄 소개
 * introduction         : 본인소개
 * possibleLanguageList : 작업 가능한 언어 리스트
 * certificateList      : 자격증 리스트
 * HelperInfoImageList  : 헬퍼 정보 이미지 경로가 담긴 리스트
 */
@Data
@Builder
@AllArgsConstructor
public class HelperInfoResponseDto {

	private Long seq;

	private Boolean isActive;
	private Float avgScore;
	private Integer reviewCount;
	private Integer unitPrice;
	private String oneLineIntroduction;
	private String introduction;
	private List<PossibleLanguageDto> possibleLanguageList;
	private List<CertificateResponseDto> certificateList;
	private List<HelperInfoImageResponseDto> helperInfoImageList;


	public static HelperInfoResponseDto fromEntity(HelperInfoEntity helperInfo) {
		return HelperInfoResponseDto.builder()
		                            .seq(helperInfo.getSeq())
		                            .avgScore(helperInfo.getAvgScore())
		                            .reviewCount(helperInfo.getReviewCount())
		                            .unitPrice(helperInfo.getUnitPrice())
		                            .oneLineIntroduction(helperInfo.getOneLineIntroduction())
		                            .introduction(helperInfo.getIntroduction())
		                            .possibleLanguageList(PossibleLanguageDto.fromEntityList(helperInfo.getPossibleLanguageList()))
		                            .certificateList(CertificateResponseDto.fromEntityList(helperInfo.getCertificateList()))
		                            .helperInfoImageList(HelperInfoImageResponseDto.fromEntityList(helperInfo.getHelperInfoImageList()))
		                            .build();
	}


	public static List<HelperInfoResponseDto> fromEntityList(
		List<HelperInfoEntity> helperInfoList) {
		List<HelperInfoResponseDto> result = new ArrayList<>();
		for (HelperInfoEntity helperInfo : helperInfoList) {
			HelperInfoResponseDto helperInfoResponseDto = fromEntity(helperInfo);
			result.add(helperInfoResponseDto);
		}
		return result;
	}

}
