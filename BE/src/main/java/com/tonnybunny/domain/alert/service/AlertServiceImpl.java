package com.tonnybunny.domain.alert.service;


import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import com.tonnybunny.domain.alert.dto.AlertSettingsDto;
import com.tonnybunny.domain.alert.entity.AlertLogEntity;
import com.tonnybunny.domain.alert.repository.AlertRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor // Autowired 안쓰려면 final 이랑 같이 써야함
public class AlertServiceImpl implements AlertService {

	private final AlertRepository alertRepository;
	private final UserRepository userRepository;


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
	public List<AlertLogEntity> getAlertLogList(AlertLogRequestDto alertLogRequestDto) {

		System.out.println("getAlertLogList Service !!");

		int page = alertLogRequestDto.getPage();
		int size = alertLogRequestDto.getSize();

		Pageable pageable = PageRequest.of(page, size);
		List<AlertLogEntity> alertLogList = alertRepository.findAll(pageable).getContent();

		System.out.println("alertLogList : " + alertLogList);

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
