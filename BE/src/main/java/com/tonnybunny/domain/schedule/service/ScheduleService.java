package com.tonnybunny.domain.schedule.service;


import com.tonnybunny.domain.schedule.dto.ScheduleRequestDto;
import com.tonnybunny.domain.schedule.entity.ScheduleEntity;
import com.tonnybunny.domain.schedule.repository.ScheduleRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final UserEntity userEntity;
	private final ScheduleRepository scheduleRepository;
	private final UserRepository userRepository;


	/**
	 * 일정Entity흫 생성한다.
	 *
	 * @param scheduleRequestDto
	 * @return created entity의 PK
	 */
	public Long createSchedule(ScheduleRequestDto scheduleRequestDto) {

		// param setting
		Long userSeq = scheduleRequestDto.getUserSeq();

		// find
		UserEntity userEntity = userRepository.findById(userSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		// dto -> entity
		ScheduleEntity scheduleEntity = ScheduleEntity.builder()
		                                              .user(userEntity)
		                                              .title(scheduleRequestDto.getTitle())
		                                              .content(scheduleRequestDto.getContent())
		                                              .startDateTime(scheduleRequestDto.getStartDateTime())
		                                              .endDateTime(scheduleRequestDto.getEndDateTime())
		                                              .taskCode(scheduleRequestDto.getTaskCode())
		                                              .isComplete(false)
		                                              .build();

		// save
		return scheduleRepository.save(scheduleEntity).getSeq();

	}


	/**
	 * 연, 월, 일 정보를 받아서 해당 날짜의 일정 목록을 반환한다.
	 *
	 * @param scheduleRequestDto : 일정 조회할 날짜의 연, 월, 일 정보
	 * @return 일정 Entity 목록
	 */
	public List<ScheduleEntity> getScheduleList(ScheduleRequestDto scheduleRequestDto) {

		List<ScheduleEntity> scheduleEntityList = scheduleRepository.findAll();

		return scheduleEntityList;

	}


	/**
	 * PK에 해당하는 일정을 반환한다.
	 *
	 * @param scheduleSeq : 조회할 일정의 PK
	 * @return 조회한 일정 Entity
	 */
	public ScheduleEntity getSchedule(Long scheduleSeq) {

		// find
		return scheduleRepository.findById(scheduleSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

	}


	/**
	 * 일정 Entity를 수정한다.
	 *
	 * @param scheduleSeq        : 수정할 ScheduleEntity의 PK
	 * @param scheduleRequestDto : 수정할 내용
	 * @return updated entity의 PK
	 */
	public Long modifySchedule(Long scheduleSeq, ScheduleRequestDto scheduleRequestDto) {

		// dto -> entity
		ScheduleEntity scheduleEntity = ScheduleEntity.builder()
		                                              .title(scheduleRequestDto.getTitle())
		                                              .content(scheduleRequestDto.getContent())
		                                              .startDateTime(scheduleRequestDto.getStartDateTime())
		                                              .endDateTime(scheduleRequestDto.getEndDateTime())
		                                              .taskCode(scheduleRequestDto.getTaskCode())
		                                              .isComplete(false)
		                                              .build();

		// save
		return scheduleRepository.save(scheduleEntity).getSeq();

	}


	/**
	 * PK에 해당하는 Entity를 삭제한다.
	 *
	 * @param scheduleSeq : 삭제할 Entity의 PK
	 * @return 삭제 여부
	 */
	public Boolean deleteSchedule(Long scheduleSeq) {

		// find
		ScheduleEntity scheduleEntity = scheduleRepository.findById(scheduleSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

		// delete
		scheduleRepository.delete(scheduleEntity);

		return true;
	}

}
