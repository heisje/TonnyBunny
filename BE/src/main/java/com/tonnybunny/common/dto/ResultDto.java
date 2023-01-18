package com.tonnybunny.common.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ResultDto<T> {

	private final T data;
	private final ResultEnum resultCode;


	public static <T> ResultDto<T> of(T data, ResultEnum result) {
		return new ResultDto<>(data, result);
	}


	public static <T> ResultDto<T> of(T data) {
		return new ResultDto<>(data, ResultEnum.SUCCESS);
	}


	public static ResultDto<Boolean> ofSuccess() {
		return new ResultDto<>(Boolean.TRUE, ResultEnum.SUCCESS);
	}


	public static ResultDto<Boolean> ofFail() {
		return new ResultDto<>(Boolean.FALSE, ResultEnum.FAIL);
	}

}