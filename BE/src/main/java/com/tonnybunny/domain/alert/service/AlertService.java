package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import com.tonnybunny.domain.alert.repository.AlertLogRepository;
import com.tonnybunny.domain.alert.repository.AlertSettingsRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor // Autowired 안쓰려면 final 이랑 같이 써야함
public class AlertService {

	private final UserRepository userRepository;

	private final AlertLogRepository alertLogRepository;
	private final AlertSettingsRepository alertSettingsRepository;


	/**
	 * MEMO : CREATE
	 * MARK : 계약이 성립되면 알람 생성
	 * ex) 즉시통역이 성립되었습니다. 오늘 통역예약이 있습니다. + 날짜, 시간 표기
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, 카테고리 taskCode, 내용 content
	 * @return 생성된 알림 로그 seq
	 */
	@Transactional
	public Long createAlertLog(AlertLogRequestDto alertLogRequestDto) {

		System.out.println("AlertService.createAlertLog");

		// param setting
		Long userSeq = alertLogRequestDto.getUserSeq();
		String taskCode = alertLogRequestDto.getTaskCode();
		String content = alertLogRequestDto.getContent();
		//		String sessionName = alertLogRequestDto.getSessionName();

		// find
		UserEntity userEntity = userRepository.findById(userSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		AlertLogEntity alertLogEntity = AlertLogEntity.builder()
		                                              .user(userEntity)
		                                              .taskCode(taskCode)
		                                              .content(content)
		                                              //		                                              .sessionName(sessionName)
		                                              .isRead(false)
		                                              .isEnd(false)
		                                              .build();

		// save
		return alertLogRepository.save(alertLogEntity).getSeq();

	}


	/**
	 * MEMO : READ
	 * MARK : 전체 알림 로그 목록을 반환
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, page, size
	 * @return 전체 알림 로그 목록
	 */
	public List<AlertLogEntity> getAlertLogList(AlertLogRequestDto alertLogRequestDto) {

		System.out.println("AlertService.getAlertLogList");

		// param setting
		Long userSeq = alertLogRequestDto.getUserSeq();
		int page = alertLogRequestDto.getPage();
		int size = alertLogRequestDto.getSize();

		UserEntity userEntity = userRepository.findById(userSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		// pagination
		Pageable pageable = PageRequest.of(page, size);

		// find
		return alertLogRepository.findByUserSeqOrderByCreatedAtDesc(userSeq, pageable).getContent();

	}


	/**
	 * MARK : 대상 유저의 알림 설정 값을 반환
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return 알림 설정 목록
	 */
	public AlertSettingsEntity getAlertSettings(Long userSeq) {

		System.out.println("AlertService.getAlertSettings");

		UserEntity userEntity = userRepository.findById(userSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		// find
		return alertSettingsRepository.findByUserSeq(userSeq);

	}


	/**
	 * MEMO : UPDATE
	 * MARK : 알림 로그 수정
	 *
	 * @param alertLogRequestDto : isRead, isEnd
	 * @return 수정된 알림 로그 seq
	 */
	@Transactional
	public Long modifyAlertLog(AlertLogRequestDto alertLogRequestDto) {

		System.out.println("AlertService.modifyAlertIsRead");

		// param setting
		Long alertLogSeq = alertLogRequestDto.getAlertLogSeq();
		Boolean isRead = alertLogRequestDto.getIsRead();
		Boolean isEnd = alertLogRequestDto.getIsEnd();

		// find
		AlertLogEntity alertLogEntity = alertLogRepository.findById(alertLogSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ENTITY));

		// 수정
		alertLogEntity.update(isRead, isEnd);

		// save
		return alertLogRepository.save(alertLogEntity).getSeq();

	}


	/**
	 * MARK : 푸시 알림 설정 수정
	 *
	 * @param alertSettingsDto : 통역, 번역, 커뮤니티, 메시지(채팅) 알람
	 * @return 설정 수정된 알림 설정 seq
	 */
	public Long modifyAlertSettings(AlertSettingsDto alertSettingsDto) {

		System.out.println("AlertService.modifyAlertSettings");

		// param setting
		Long userSeq = alertSettingsDto.getUserSeq();
		Boolean isAll = alertSettingsDto.getIsAll();
		Boolean isTonnyBunny = alertSettingsDto.getIsTonnyBunny();
		Boolean isCommunity = alertSettingsDto.getIsCommunity();
		Boolean isChat = alertSettingsDto.getIsChat();

		// find
		UserEntity userEntity = userRepository.findById(userSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		AlertSettingsEntity alertSettings = alertSettingsRepository.findByUserSeq(userSeq);

		// 수정
		alertSettings.update(isAll, isTonnyBunny, isCommunity, isChat);

		// save
		return alertSettingsRepository.save(alertSettings).getSeq();

	}


	/**
	 * MEMO : DELETE
	 * MARK : 알람 로그 삭제
	 *
	 * @param alertLogSeq : 삭제할 대상 알람 로그 seq
	 * @return
	 */
	public void deleteAlertLog(Long alertLogSeq) {

		System.out.println("AlertService.deleteAlertLog");

		// find
		AlertLogEntity alertLogEntity = alertLogRepository.findById(alertLogSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		// delete
		alertLogRepository.delete(alertLogEntity);

	}

}
