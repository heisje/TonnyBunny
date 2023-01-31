package com.tonnybunny.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	ERROR_NAME(NOT_FOUND, "에러 메세지 입니다"),

	/* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
	DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
