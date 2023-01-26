package com.tonnybunny.domain.bunny.dto;


import com.tonnybunny.domain.bunny.entity.BunnyNotiEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/**
 * clientSeq            : 작성자 seq
 *
 * title                : 공고 제목
 * content              : 공고 내용 (null)
 * iestimatePrice       : 예상 의뢰금액
 * startDateTime        : 시작일                : 생성 폼에서 DATE만 받지만 시간 자정까지 추가?
 * endDateTime          : 마감일                : 생성 폼에서 DATE만 받지만 시간 자정까지 추가?
 * bunnyNotiImageList   : 번역 공고 이미지 리스트
 *
 * startLangCode        : 시작 언어 코드
 * endLangCode          : 종료 언어 코드
 * bunnySituCode        : 번역상황 카테고리 코드
 * bunnyStateCode       : 번역공고 상태 코드
 */
@Data
public class BunnyNotiRequestDto {

	private Long clientSeq;

	private String title;
	private String content;
	private Integer estimatePrice;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private List<BunnyNotiImageRequestDto> bunnyNotiImageList;

	private String startLangCode;
	private String endLangCode;
	private String bunnySituCode;
	private String bunnyStateCode;


	public BunnyNotiEntity toEntity() {
		return (BunnyNotiEntity) new Object();
	}

}
