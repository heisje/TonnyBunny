package com.tonnybunny.domain.alert.controller;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.service.AlertTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AlertTestController {

	private final AlertTestService alertTestService;


	@PostMapping("/test/alert")
	public String testMethod(@RequestBody AlertLogRequestDto alertLogRequestDto) {
		System.out.println("alertLogRequestDto = " + alertLogRequestDto);

		AlertLogEntity alertLog = alertTestService.test(alertLogRequestDto);

		System.out.println("AlertTestController.testMethod");
		System.out.println("alertLog = " + alertLog);

		return "success";
	}

}
