package com.tonnybunny.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	ERROR_NAME(NOT_FOUND, "에러 메세지 입니다"),

	/* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
	DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),
	ENTITY_NOT_FOUND(NOT_FOUND, "엔티티가 존재하지 않습니다."),
	MISMATCH_REQUEST(BAD_REQUEST, "데이터 간 의미가 어긋납니다 (예: 공통코드와 대응되는 데이터)"),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
