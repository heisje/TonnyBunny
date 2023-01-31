package com.tonnybunny.exception;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


@Getter
@Builder
public class ErrorResponse {

	private final LocalDateTime timestamp = LocalDateTime.now();
	private final int status;   // 중복데이터
	private final String error;
	private final String code;  // 중복데이터
	private final String message;


	public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(ErrorResponse.builder()
			                   .status(errorCode.getHttpStatus().value())
			                   .error(errorCode.getHttpStatus().name())
			                   .code(errorCode.name())
			                   .message(errorCode.getMessage())
			                   .build()
			);
	}

}
