package com.tonnybunny.domain.ytonny.dto;


import com.tonnybunny.common.dto.TaskStateCodeEnum;
import com.tonnybunny.common.dto.TonnySituCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * clientSeq                : 고객 seq
 *
 * title                    : 제목
 * estimateDate             : 요청 날짜
 * estimateStartTime        : 요청 시간
 * estimateTime             : 예상 소요 시간
 * estimatePrice            : 의뢰 금액 (null)
 * content                  : 내용 (null)
 *
 * startLangCode            : 시작 언어 코드
 * endLangCode              : 종료 언어 코드
 * tonnySituCode            : 통역상황 카테고리 코드 (null)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YTonnyRequestDto {

	//  @Builder will ignore the initializing expression entirely.
	// 빌더는 초기화 표현식을 무시 (그래서 final 을 쓰거나 @Builder.Default 을 쓰기)
	@Builder.Default
	private int size = 5; // limit
	@Builder.Default
	private int page = 0; // offset

	private Long yTonnySeq;
	private Long clientSeq;
	private Long helperSeq;

	private String title;
	@Builder.Default
	private String tonnySituCode = TonnySituCodeEnum.기타.getTonnySituCode();
	@Builder.Default
	private String content = "";

	private String startLangCode;
	private String endLangCode;

	@DateTimeFormat(pattern = "'yyyy-MM-dd'T'HH:mm'")
	private LocalDateTime startDateTime;
	private String estimateTime;
	private Integer estimatePrice;

	@Builder.Default
	private String taskStateCode = TaskStateCodeEnum.모집중.getTaskStateCode();

}
