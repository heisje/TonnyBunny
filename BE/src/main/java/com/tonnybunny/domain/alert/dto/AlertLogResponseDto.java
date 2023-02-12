package com.tonnybunny.domain.alert.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertLogResponseDto {

	private Long alertLogSeq;

	//	private String sessionName;
	private String taskCode;
	private String content;
	private Boolean isRead;
	private Boolean isEnd;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	//	private final ModelMapper modelMapper;
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
