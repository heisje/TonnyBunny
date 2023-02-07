package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class HelperInfoResponseDto {

	private Long seq;
	@Builder.Default
	private Float avgScore = 0f;
	@Builder.Default
	private Integer reviewCount = 0;
	@Builder.Default
	private Integer unitPrice = 0;
	@Builder.Default
	private String oneLineIntroduction = "안녕하세요, 언제나 성심성의껏 도와드리겠습니다.";
	@Builder.Default
	private String introduction = "안녕하세요, 당신을 도와드릴 토니버니의 헬퍼입니다. 언제나 성심성의껏 도와드리겠습니다.";
	@Builder.Default
	private List<PossibleLanguageDto> possibleLanguageList = new ArrayList<>();
	@Builder.Default
	private List<CertificateResponseDto> certificateList = new ArrayList<>();
	@Builder.Default
	private List<HelperInfoImageResponseDto> helperInfoImageList = new ArrayList<>();


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
