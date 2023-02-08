package com.tonnybunny.domain.live.service;


import com.tonnybunny.common.dto.TaskCodeEnum;
import com.tonnybunny.domain.jtonny.dto.JTonnyDto;
import com.tonnybunny.domain.jtonny.entity.JTonnyHistoryEntity;
import com.tonnybunny.domain.user.entity.HistoryEntity;
import com.tonnybunny.domain.user.repository.HistoryRepository;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.domain.ytonny.entity.YTonnyEntity;
import com.tonnybunny.domain.ytonny.entity.YTonnyHistoryEntity;
import com.tonnybunny.domain.ytonny.repository.YTonnyRepository;
import com.tonnybunny.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.tonnybunny.exception.ErrorCode.NOT_FOUND_ENTITY;
import static com.tonnybunny.exception.ErrorCode.NOT_FOUND_USER;


@Service
@RequiredArgsConstructor
@Log4j2
public class LiveService {

	private final UserRepository userRepository;
	private final YTonnyRepository yTonnyRepository;
	private final HistoryRepository historyRepository;


	public Long startJTonnyLive(JTonnyDto jTonnyDto) {
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

		return jTonnyHistory.getSeq();
	}


	public Long startYTonnyLive(Long yTonnySeq) {

		/**
		 * 미리 생성된 예약통역 seq 를 service 에 넘겨서
		 * 히스토리 생성, start_date_time 에 now 찍기
		 * repository 저장
		 * history 의 seq 반환
		 *
		 * TODO
		 *  start 이전에 uuid 를 생성해주어야 한다? 방에 입장 후 해당 메서드를 호출함
		 *  Entity 가 sessionId 로 사용할 uuid 를 가지고 있어야 하는가
		 */
		YTonnyEntity yTonny = yTonnyRepository.findById(yTonnySeq)
		                                      .orElseThrow(() -> new CustomException(NOT_FOUND_ENTITY));

		YTonnyHistoryEntity yTonnyHistory = YTonnyHistoryEntity.builder()
		                                                       .client(userRepository.findById(yTonny.getClient().getSeq())
		                                                                             .orElseThrow(() -> new CustomException(NOT_FOUND_USER)))
		                                                       .helper(userRepository.findById(yTonny.getHelperSeq())
		                                                                             .orElseThrow(() -> new CustomException(NOT_FOUND_USER)))
		                                                       .taskCode(yTonny.getTaskCode())
		                                                       .notiSeq(yTonny.getSeq())
		                                                       .startLangCode(yTonny.getStartLangCode())
		                                                       .endLangCode(yTonny.getEndLangCode())
		                                                       .content(yTonny.getContent())
		                                                       .startDateTime(LocalDateTime.now())
		                                                       .title(yTonny.getTitle())
		                                                       //			.totalTime(yt) <-- 끝날때
		                                                       // TODO
		                                                       //  .unitPrice(yTonny.getUnitPrice()) <-- 총 금액 계산하려면 단가가 있어야 하지 않나?
		                                                       .tonnySituCode(yTonny.getTonnySituCode())
		                                                       .build();

		historyRepository.save(yTonnyHistory);

		return yTonnyHistory.getSeq();

	}


	public void completeLive(Long historySeq, LocalTime totalTime, String recordVideoPath) {
		/**
		 * 즉시 통역과 관련된 히스토리 가져와서 (findById)
		 * end_date_time 에 now 찍기
		 * 프론트에서 넘어온 소요시간과, DB에 저장된 소요시간을 비교하고 --> 생략?
		 * 일정 오차범위 이내면 return true
		 * TODO
		 *  캐스팅 했을 때 필드 다 있는지 확인해야 함, 없으면 방식 변경
		 */
		HistoryEntity history = historyRepository.findById(historySeq)
		                                         .orElseThrow(() -> new CustomException(NOT_FOUND_ENTITY));
		log.info("history = {}", history);

		if (history.getTaskCode().equals(TaskCodeEnum.즉시통역.getTaskCode())) {
			JTonnyHistoryEntity targetHistory = (JTonnyHistoryEntity) history;
			log.info("targetHistory = {}", targetHistory);
			targetHistory.completeLive(totalTime, recordVideoPath);
			historyRepository.save(targetHistory);
		} else if (history.getTaskCode().equals(TaskCodeEnum.예약통역.getTaskCode())) {
			YTonnyHistoryEntity targetHistory = (YTonnyHistoryEntity) history;
			log.info("targetHistory = {}", targetHistory);
			targetHistory.completeLive(totalTime, recordVideoPath);
			historyRepository.save(targetHistory);
		}
	}

}
