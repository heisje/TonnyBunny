package com.tonnybunny.domain.live.dto;


import lombok.Data;


@Data
public class HistoryCompleteDto {

	private Long historySeq;

	private String recordVideoPath;

	private String totalTime;

	private Integer totalPrice;

}
