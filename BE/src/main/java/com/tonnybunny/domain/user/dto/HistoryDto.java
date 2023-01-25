package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.bunny.entity.BunnyHistoryEntity;
import com.tonnybunny.domain.jtonny.entity.JTonnyHistoryEntity;
import com.tonnybunny.domain.user.entity.HistoryEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyHistoryEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class HistoryDto {

	public static HistoryDto fromEntity(HistoryEntity history) {
		return new HistoryDto();
	}


	public static List<HistoryDto> fromEntityList(List<HistoryEntity> historyList) {
		List<HistoryDto> result = new ArrayList<>();
		for (HistoryEntity history : historyList) {
			HistoryDto historyDto = fromEntity(history);
			result.add(historyDto);
		}
		return result;
	}


	public HistoryEntity toEntity() {
		return (HistoryEntity) new Object();
	}


	public BunnyHistoryEntity toBunnyHistoryEntity() {
		return (BunnyHistoryEntity) new Object();
	}


	public JTonnyHistoryEntity toJTonnyHistoryEntity() {
		return (JTonnyHistoryEntity) new Object();
	}


	public YTonnyHistoryEntity toYTonnyHistoryEntity() {
		return (YTonnyHistoryEntity) new Object();
	}

}
