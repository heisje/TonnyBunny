package com.tonnybunny.common;


import com.tonnybunny.common.dto.CommonGroupCodeResponseDto;
import com.tonnybunny.common.dto.ResultDto;
import com.tonnybunny.common.entity.CommonGroupCodeEntity;
import com.tonnybunny.common.repository.CommonGroupCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * nginx 테스트용 Controller
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CommonController {

	private final CommonGroupCodeRepository commonGroupCodeRepository;


	@GetMapping
	public ResponseEntity<?> getRootPath() {
		List<CommonGroupCodeEntity> CommonGroupCodeList = commonGroupCodeRepository.findAll();
		List<CommonGroupCodeResponseDto> CommonGroupCodeResponseDtoList = CommonGroupCodeResponseDto.fromEntityList(CommonGroupCodeList);
		return ResponseEntity.status(HttpStatus.OK).body(ResultDto.of(CommonGroupCodeResponseDtoList));
	}


	@GetMapping("/api")
	public String getApiPath() {
		return "path: api";
	}

}
