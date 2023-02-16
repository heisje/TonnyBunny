package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.repository.AlertLogRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlertTestService {

	private final UserRepository userRepository;
	private final AlertLogRepository alertLogRepository;


	public AlertLogEntity test(AlertLogRequestDto alertLogRequestDto) {
		Optional<UserEntity> user = userRepository.findById(alertLogRequestDto.getUserSeq());

		AlertLogEntity alertLog = AlertLogEntity.builder()
		                                        .user(user.get())
		                                        .content(alertLogRequestDto.getContent())
		                                        .isRead(false)
		                                        .build();

		alertLogRepository.save(alertLog);

		return alertLog;
	}

}
