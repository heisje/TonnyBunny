package com.tonnybunny.domain.user.dto;


import com.tonnybunny.config.ModelMapperFactory;
import com.tonnybunny.domain.bunny.entity.BunnyHistoryEntity;
import com.tonnybunny.domain.jtonny.entity.JTonnyHistoryEntity;
import com.tonnybunny.domain.review.dto.ReviewResponseDto;
import com.tonnybunny.domain.user.entity.HistoryEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyHistoryEntity;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.Data;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * seq              : 히스토리 Seq
 * clientSeq        : 고객 Seq
 * helperSeq        : 헬퍼 Seq
 * content          : 공고 내용
 * startDateTime    : 시작 시간
 * endDateTime      : 종료 시간
 * startLangCode    : 기존 언어
 * endLangCode      : 통역/번역 언어
 * taskCode         : 즉통/예통/번역 공통 코드
 * notiSeq          : 공고
 * review           : 리뷰
 * totalTime        : 총 소요 시간 (즉통, 예통만 )
 * unitPrice        : 단가 (즉통, 예통만)
 * recordVideoPath  : 녹화 파일 (즉통, 예통만)
 * tonnySituCode    : 통역 상황 코드 (즉통, 예통만)
 * title            : 공고 제목 (예통, 번역만)
 */
@Data
public class HistoryResponseDto {

	private Long seq;

	private Long clientSeq;

	private Long helperSeq;

	private String content;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	private String startLangCode;
	private String endLangCode;
	private String taskCode;

	private Long notiSeq;                   // 즉통, 예통의 경우 NotiSeq, 번역의 경우 QuotationSeq

	private ReviewResponseDto review;

	/************************ 즉통 & 예통 **************************/

	private LocalTime totalTime;
	private Integer unitPrice;
	private String recordVideoPath;
	private String tonnySituCode = "0";       // 디폴트 "일상" 코드 넣기

	/************************ 예통 & 번역 **************************/

	private String title;


	public static <T extends HistoryEntity> HistoryResponseDto fromEntity(T history) {
		ModelMapper modelMapper = ModelMapperFactory.getMapper();

		// Entity로 들어온 값을 Seq로 타입 변환
		modelMapper.typeMap(HistoryEntity.class, HistoryResponseDto.class).addMappings(
			mapper -> {
				// 고객 Entity -> 고객 Seq
				mapper.using((Converter<UserEntity, Long>) client -> client.getSource().getSeq())
					.map(HistoryEntity::getClient, HistoryResponseDto::setClientSeq);
				// 헬퍼 Entity -> 헬퍼 Seq
				mapper.using((Converter<UserEntity, Long>) helper -> helper.getSource().getSeq())
					.map(HistoryEntity::getHelper, HistoryResponseDto::setHelperSeq);

			}
		);
		// 값 매핑
		HistoryResponseDto historyResponseDto = modelMapper.map(history, HistoryResponseDto.class);

		// TaskCode에 따라 즉시통역, 예약통역, 번역 Entity가 들어올것임
		// => 들어오는 Entity에 따라 달라지는 필드 값을 채움
		if (history.getTaskCode() == TaskCodeEnum.즉시통역.getTaskCode()) {
			if (history instanceof JTonnyHistoryEntity) {
				JTonnyHistoryEntity jTonnyHistory = (JTonnyHistoryEntity) history;

				historyResponseDto.setTotalTime(jTonnyHistory.getTotalTime());
				historyResponseDto.setUnitPrice(jTonnyHistory.getUnitPrice());
				historyResponseDto.setRecordVideoPath(jTonnyHistory.getRecordVideoPath());
				historyResponseDto.setTonnySituCode(jTonnyHistory.getTonnySituCode());
			} else
				throw new CustomException(ErrorCode.MISMATCH_REQUEST);
		}
		if (history.getTaskCode() == TaskCodeEnum.예약통역.getTaskCode()) {
			if (history instanceof YTonnyHistoryEntity) {
				YTonnyHistoryEntity yTonnyHistory = (YTonnyHistoryEntity) history;

				historyResponseDto.setTotalTime(yTonnyHistory.getTotalTime());
				historyResponseDto.setUnitPrice(yTonnyHistory.getUnitPrice());
				historyResponseDto.setRecordVideoPath(yTonnyHistory.getRecordVideoPath());
				historyResponseDto.setTonnySituCode(yTonnyHistory.getTonnySituCode());
				historyResponseDto.setTitle(yTonnyHistory.getTitle());
			} else
				throw new CustomException(ErrorCode.MISMATCH_REQUEST);
		}
		if (history.getTaskCode() == TaskCodeEnum.번역.getTaskCode()) {
			if (history instanceof BunnyHistoryEntity) {
				BunnyHistoryEntity bunnyHistory = (BunnyHistoryEntity) history;

				historyResponseDto.setTitle(bunnyHistory.getTitle());
			} else
				throw new CustomException(ErrorCode.MISMATCH_REQUEST);
		}
		return historyResponseDto;
	}


	public static List<HistoryResponseDto> fromEntityList(List<HistoryEntity> historyList) {
		List<HistoryResponseDto> result = new ArrayList<>();
		for (HistoryEntity history : historyList) {
			HistoryResponseDto historyResponseDto = fromEntity(history);
			result.add(historyResponseDto);
		}
		return result;
	}

}
