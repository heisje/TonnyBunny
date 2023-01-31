package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.repository.AlertRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AlertServiceImpl implements AlertService {

	AlertRepository alertRepository;
	UserRepository userRepository;


	/**
	 * MARK : 계약이 성립되면 알람 생성
	 * ex) 즉시통역이 성립되었습니다. 오늘 통역예약이 있습니다. + 날짜, 시간 표기
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, 카테고리 taskCode, 내용 content
	 * @return 생성 성공 여부
	 */
	@Override
	public Boolean createAlertLog(AlertLogRequestDto alertLogRequestDto) {

		System.out.println("service !!!!!!!!!!!");

		Long userSeq = alertLogRequestDto.getUserSeq();
		String taskCode = alertLogRequestDto.getTaskCode();
		String content = alertLogRequestDto.getContent();

		Optional<UserEntity> userEntity = userRepository.findById(userSeq);

		System.out.println(userEntity);

		//
		//		System.out.println("---------------------------------");
		//		System.out.println(userEntity);

		//		AlertLogEntity fromAlertLog = alertRepository.save(
		//			AlertLogEntity.builder()
		//			              .user(userEntity)
		//			              .taskCode(taskCode)
		//			              .content(content)
		//			              .build()
		//		);

		//		AlertLogEntity fromAlertLog = alertRepository.save(
		//			AlertLogEntity.builder()
		//			              .alertLogSeq();
		//		)

		// save
		//		PointLogEntity fromUserPointLog = pointLogRepository.save(
		//			PointLogEntity.builder()
		//				.amount(-pointAmount) // 감소하므로 음수값
		//				.typeCode(PointRequestTypeEnum.거래.toString())
		//				.user(fromUser)
		//				.build());

		return true;
	}


	/**
	 * MARK : 전체 알림 로그 목록을 반환
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, page, size
	 * @return 전체 알림 로그 목록
	 */
	@Override
	public Page<AlertLogEntity> getAlertLogList(AlertLogRequestDto alertLogRequestDto) {

		System.out.println(alertLogRequestDto);
		
		int page = alertLogRequestDto.getPage();
		int size = alertLogRequestDto.getSize();

		System.out.println("하ㅣ이루!!!!!" + page + " " + size);

		Pageable pageable = PageRequest.of(page, size);
		System.out.println("되고있는거 맞나 ???????????/");
		Page<AlertLogEntity> alertLogList = alertRepository.findAll(pageable);

		System.out.println(alertLogList);

		return alertLogList;
	}


	/**
	 * MARK : 알림 읽음확인 수정
	 *
	 * @param alertLogSeq : 대상 알림 로그 seq
	 * @return 알림 읽음확인 수정 여부
	 */
	@Override
	public Boolean modifyAlertIsRead(Long alertLogSeq) {
		// find by id
		// isread 만 변경
		return true;
	}


	/**
	 * MARK : 알림 설정 toggle 수정
	 *
	 * @param alertSettingsDto : 통역, 번역, 커뮤니티, 메시지(채팅) 알람
	 * @return 알림 설정 변경 여부
	 */
	@Override
	public Boolean modifyAlertSettings(AlertSettingsDto alertSettingsDto) {
		// TODO : 구현
		// alert setting 변경
		return true;
	}


	/**
	 * MARK : 알림 삭제
	 *
	 * @param alertLogSeq : 대상 알림 로그 seq
	 * @return 알림 삭제 여부
	 */
	@Override
	public Boolean deleteAlertLog(Long alertLogSeq) {

		return null;
	}

}
