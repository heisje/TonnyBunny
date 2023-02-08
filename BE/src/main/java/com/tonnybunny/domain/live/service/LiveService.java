package com.tonnybunny.domain.live.service;


import com.tonnybunny.domain.jtonny.dto.JTonnyDto;
import com.tonnybunny.domain.jtonny.entity.JTonnyHistoryEntity;
import com.tonnybunny.domain.user.repository.HistoryRepository;
import com.tonnybunny.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Service
@RequiredArgsConstructor
public class LiveService {

	private final UserRepository userRepository;
	private final HistoryRepository historyRepository;


	public Long startLive(JTonnyDto jTonnyDto) {
		/**
		 * service 에 넘겨서
		 * 히스토리 생성, start_date_time 에 now 찍기
		 * repository 저장
		 */
		JTonnyHistoryEntity jTonnyHistory = JTonnyHistoryEntity.builder()
		                                                       .client(userRepository.findById(jTonnyDto.getClient().getSeq()).get())
		                                                       .helper(userRepository.findById(jTonnyDto.getHelper().getSeq()).get())
		                                                       .taskCode(jTonnyDto.getTaskCode())
		                                                       .notiSeq(jTonnyDto.getSeq())
		                                                       .startLangCode(jTonnyDto.getStartLangCode())
		                                                       .endLangCode(jTonnyDto.getEndLangCode())
		                                                       .content(jTonnyDto.getContent())
		                                                       .startDateTime(LocalDateTime.now())
		                                                       .tonnySituCode(jTonnyDto.getTonnySituCode())
		                                                       .unitPrice(jTonnyDto.getUnitPrice())
		                                                       .build();

		historyRepository.save(jTonnyHistory);

		return jTonnyDto.getSeq();
	}


	public void endLive(Long historySeq, LocalTime totalTime, String recordVideoPath) {
		/**
		 * 즉시 통역과 관련된 히스토리 가져와서 (findById)
		 * end_date_time 에 now 찍기
		 * 프론트에서 넘어온 소요시간과, DB에 저장된 소요시간을 비교하고
		 * 일정 오차범위 이내면 return true
		 */
		JTonnyHistoryEntity history = (JTonnyHistoryEntity) historyRepository.findById(historySeq).get();
		/* 시간 비교 로직? */
		history.endLive(totalTime, recordVideoPath);
		historyRepository.save(history);
	}

}
