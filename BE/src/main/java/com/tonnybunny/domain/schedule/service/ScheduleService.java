package com.tonnybunny.domain.schedule.service;


import com.tonnybunny.domain.schedule.dto.ScheduleRequestDto;
import com.tonnybunny.domain.schedule.dto.ScheduleResponseDto;
import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ScheduleService {

	/**
	 * 연, 월, 일 정보를 받아서 해당 날짜의 일정 목록을 반환한다.
	 * @param scheduleRequestDto : 일정 조회할 날짜의 연, 월, 일 정보
	 * @return 일정 Entity 목록
	 */
	public List<ScheduleEntity> getScheduleList(ScheduleRequestDto scheduleRequestDto){
		// TODO : 로직 구현

		return new ArrayList<>();
	}

	/**
	 * PK에 해당하는 일정을 반환한다.
	 * @param scheduleSeq : 조회할 일정의 PK
	 * @return 조회한 일정 Entity
	 */
	public ScheduleEntity getSchedule(Long scheduleSeq){
		// TODO : 로직 구현

		return (ScheduleEntity) new Object();
	}

	/**
	 * 일정Entity흫 생성한다.
	 * @param scheduleRequestDto
	 * @return created entity의 PK
	 */
	public Long createSchedule(ScheduleRequestDto scheduleRequestDto){
		// TODO : 로직 구현

		return 1L;
	}

	/**
	 * 일정 Entity를 수정한다.
	 * @param scheduleSeq : 수정할 ScheduleEntity의 PK
	 * @param scheduleRequestDto : 수정할 내용
	 * @return updated entity의 PK
	 */
	public Long modifySchedule(Long scheduleSeq, ScheduleRequestDto scheduleRequestDto){
		// TODO : 로직 구현

		return 1L;
	}

	/**
	 * PK에 해당하는 Entity를 삭제한다.
	 * @param scheduleSeq : 삭제할 Entity의 PK
	 * @return 삭제 여부
	 */
	public Boolean deleteSchedule(Long scheduleSeq){
		// TODO : 로직 구현

		return true;
	}
}
