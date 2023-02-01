package com.tonnybunny.domain.alert.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
public class AlertLogResponseDto {

	private final ModelMapper modelMapper;

	private Long alertLogSeq;

	private String taskCode;
	private String content;
	private Boolean isRead;
	private Boolean isEnd;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	//	public static ModelMapper modelMapper = new ModelMapper();

	//	public static AlertLogResponseDto fromEntity(AlertLogEntity alertLog) {
	//		return modelMapper.map(alertLog, AlertLogResponseDto.class);
	//	}
	//
	//
	//	public static List<AlertLogResponseDto> fromEntityList(List<AlertLogEntity> alertLogList) {
	//		List<AlertLogResponseDto> result = new ArrayList<>();
	//
	//		for (AlertLogEntity alertLog : alertLogList) {
	//			AlertLogResponseDto alertLogResponseDto = fromEntity(alertLog);
	//			result.add(alertLogResponseDto);
	//		}
	//
	//		return result;
	//	}
	//
	//
	//	@PostConstruct
	//	private void initialize() {
	//		modelMapper = this.modelBeanMapper;
	//	}

}
