package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.entity.AlertSettingsEntity;
import com.tonnybunny.domain.alert.repository.AlertLogRepository;
import com.tonnybunny.domain.alert.repository.AlertSettingsRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor // Autowired 안쓰려면 final 이랑 같이 써야함
public class AlertService {

	private final AlertLogRepository alertLogRepository;
	private final AlertSettingsRepository alertSettingsRepository;

	private final UserRepository userRepository;


	/**
	 * MEMO : CREATE
	 * MARK : 계약이 성립되면 알람 생성
	 * ex) 즉시통역이 성립되었습니다. 오늘 통역예약이 있습니다. + 날짜, 시간 표기
	 *
	 * @param alertLogRequestDto : 대상 유저 seq, 카테고리 taskCode, 내용 content
	 * @return
	 */
	@Transactional
	public Long createAlertLog(AlertLogRequestDto alertLogRequestDto) {

		System.out.println("AlertService.createAlertLog");

		// param setting
		Long userSeq = alertLogRequestDto.getUserSeq();
		String taskCode = alertLogRequestDto.getTaskCode();
		String content = alertLogRequestDto.getContent();

		// find

		UserEntity userEntity = userRepository.findById(userSeq)
		                                      .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
		AlertLogEntity alertLogEntity = AlertLogEntity.builder()
		                                              .user(userEntity)
		                                              .taskCode(taskCode)
		                                              .content(content)
		                                              .isRead(false)
		                                              .isEnd(false)
		                                              .build();

		// save
		Long alertLogSeq = alertLogRepository.save(alertLogEntity).getSeq();

		return alertLogSeq;
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

		// pagination
		Pageable pageable = PageRequest.of(page, size);

		// find
		List<AlertLogEntity> alertLogList = alertLogRepository.findByUserSeqOrderByCreatedAtDesc(userSeq, pageable).getContent();

		return alertLogList;
	}


	/**
	 * MARK : 대상 유저의 알림 설정 값을 반환
	 *
	 * @param userSeq : 대상 유저 seq
	 * @return 알림 설정 목록
	 */
	public AlertSettingsEntity getAlertSettings(Long userSeq) {

		System.out.println("AlertService.getAlertSettings");

		// find
		AlertSettingsEntity alertSettings = alertSettingsRepository.findByUserSeq(userSeq)
		                                                           .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 알림 설정입니다."));

		return alertSettings;
	}


	/**
	 * MEMO : UPDATE
	 * MARK : 알림 읽음확인 수정
	 *
	 * @param alertLogSeq : 대상 알림 로그 seq
	 * @return
	 */
	@Transactional
	public void modifyAlertIsRead(Long alertLogSeq) {

		System.out.println("AlertService.modifyAlertIsRead");

		// find
		AlertLogEntity alertLogEntity = alertLogRepository.findById(alertLogSeq)
		                                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 알림입니다."));

		// 수정
		alertLogEntity.updateIsRead(true);

		// save
		alertLogRepository.save(alertLogEntity);

	}


	/**
	 * MARK : 푸시 알림 설정 수정
	 *
	 * @param alertSettingsDto : 통역, 번역, 커뮤니티, 메시지(채팅) 알람
	 * @return
	 */
	public void modifyAlertSettings(AlertSettingsDto alertSettingsDto) {

		System.out.println("AlertService.modifyAlertSettings");

		// param setting
		Long userSeq = alertSettingsDto.getUserSeq();
		Boolean isAll = alertSettingsDto.getIsAll();
		Boolean isTonnyBunny = alertSettingsDto.getIsTonnyBunny();
		Boolean isCommunity = alertSettingsDto.getIsCommunity();
		Boolean isChat = alertSettingsDto.getIsChat();
		// update data도

		// find
		//		UserEntity userEntity = userRepository.findById(userSeq).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
		AlertSettingsEntity alertSettings = alertSettingsRepository.findByUserSeq(userSeq)
		                                                           .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 알림 입니다."));

		// 수정
		alertSettings.update(isAll, isTonnyBunny, isCommunity, isChat);

		// save
		alertSettingsRepository.save(alertSettings);

	}


	/**
	 * MEMO : DELETE
	 * MARK : 알림 로그 삭제
	 *
	 * @param alertLogSeq : 대상 알림 로그 seq
	 * @return
	 */
	public void deleteAlertLog(Long alertLogSeq) {

		System.out.println("AlertService.deleteAlertLog");

		// find
		AlertLogEntity alertLogEntity = alertLogRepository.findById(alertLogSeq).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 알림 로그 입니다."));

		// delete
		alertLogRepository.delete(alertLogEntity);

	}

}
