package com.tonnybunny.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	ERROR_NAME(NOT_FOUND, "에러 메세지 입니다")

	/* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */,
	DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),
	MISMATCH_REQUEST(BAD_REQUEST, "데이터 간 의미가 어긋납니다 (예: 공통코드와 대응되는 데이터)"),

	/* 400 BAD REQUEST : 잘못된 요청. 입력된 데이터에 문제가 있음 */
	SIGNUP_INVALIDATION(BAD_REQUEST, "입력된 정보가 지정된 형식과 다릅니다."),
	LOGIN_BAD_REQUEST(BAD_REQUEST, "로그인 정보가 등록된 정보와 다릅니다."),
	LACK_OF_POINTS(BAD_REQUEST, "포인트가 부족합니다."),
	SAME_USER_REQUEST(BAD_REQUEST, "요청하는 대상과 요청받는 대상이 동일합니다."),
	ID_IS_NULL(BAD_REQUEST, "ID 값이 존재하지 않습니다."),
	PASSWORD_NOT_MATCH(BAD_REQUEST, "패스워드가 일치하지 않습니다."),
	DATA_BAD_REQUEST(BAD_REQUEST, "입력된 데이터에 문제가 있습니다."),

	/* 401 UNAUTHORIZED : 권한 인증 문제. JWT 토큰과 관련된 에러 */
	ACCESS_TOKEN_EXPIRED(UNAUTHORIZED, "토큰의 유효기간이 만료되었습니다."),
	ACCESS_TOKEN_NOT_FOUND(UNAUTHORIZED, "액세스 토큰이 존재하지 않습니다."),
	REFRESH_TOKEN_NOT_FOUND(UNAUTHORIZED, "리프레시 토큰이 존재하지 않습니다."),
	ACCESS_TOKEN_ERROR(UNAUTHORIZED, "액세스 토큰에 문제가 있습니다."),
	REFRESH_TOKEN_ERROR(UNAUTHORIZED, "리프레시 토큰에 문제가 있습니다."),

	/* 404 NOT_FOUND : 대상이 존재하지 않음 */
	NOT_FOUND_ENTITY(NOT_FOUND, "엔티티가 존재하지 않습니다."),
	NOT_FOUND_USER(NOT_FOUND, "대상이 존재하지 않습니다."),
	NOT_FOUND_TOKEN(NOT_FOUND, "토큰이 존재하지 않습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
