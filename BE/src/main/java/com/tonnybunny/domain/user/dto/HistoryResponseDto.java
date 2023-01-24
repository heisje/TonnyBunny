package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.HistoryEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class HistoryResponseDto {

	public static HistoryResponseDto fromEntity(HistoryEntity history) {
		return new HistoryResponseDto();
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
