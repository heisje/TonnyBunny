package com.tonnybunny.domain.point.service;


import com.tonnybunny.domain.point.dto.PointLogRequestDto;
import com.tonnybunny.domain.point.dto.PointRequestDto;
import com.tonnybunny.domain.point.dto.PointRequestTypeEnum;
import com.tonnybunny.domain.point.entity.PointLogEntity;
import com.tonnybunny.domain.point.repository.PointLogRepository;
import com.tonnybunny.domain.user.entity.UserEntity;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PointService {

	private final PointLogRepository pointLogRepository;

	private final UserRepository userRepository;


	/**
	 * 포인트 로그 목록 조회
	 *
	 * @param pointLogRequestDto : 대상 유저, 조회할 로그 개수
	 * @return 포인트 로그 목록
	 */
	public List<PointLogEntity> getPointLogList(PointLogRequestDto pointLogRequestDto) {
		long userSeq = pointLogRequestDto.getUserSeq();
		UserEntity user = userRepository.findById(userSeq).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		int logCount = pointLogRequestDto.getLogCount();

		// 동적으로 limit 정할 수 없어서 list에서 컷함
		List<PointLogEntity> pointLogList = pointLogRepository.findAllByUserOrderByCreatedAtDesc(user);

		// 조회할 로그 개수만큼 반환 (요청 개수보다 수가 적으면 적은만큼 반환)
		return pointLogList.subList(0, Math.min(logCount, pointLogList.size()));
	}


	/**
	 * 포인트 충전 :
	 * - 대상 유저(fromUser)의 포인트 양을 추가(addPoint()), 포인트 로그에 추가
	 *
	 * @param pointRequestDto : 대상 유저 seq, 포인트 양 정보 포함
	 * @return 포인트 충전 성공 여부
	 */
	public Boolean buyPoint(PointRequestDto pointRequestDto) {
		UserEntity fromUser = userRepository.findById(pointRequestDto.getFromUserSeq())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		int pointAmount = pointRequestDto.getPointAmount();

		// 포인트 로그 저장
		PointLogEntity fromUserPointLog = pointLogRepository.save(
			PointLogEntity.builder()
				.amount(pointAmount)
				.typeCode(PointRequestTypeEnum.충전.toString())
				.user(fromUser)
				.build());

		// 유저의 포인트 로그 양방향 리스트 필드에 추가
		fromUser.getPointLogList().add(fromUserPointLog);

		// 포인트 증가
		addPoint(fromUser, pointAmount);
		return true;
	}


	/**
	 * 포인트 거래 :
	 * - 소비 유저(fromUserSeq) 포인트 양 감소(subtractPoint()), 포인트 로그에 추가
	 * - 획득 유저(toUserSeq) 포인트 양 증(addPoint())가, 포인트 로그에 추가
	 *
	 * @param pointRequestDto : 소비 유저 seq, 획득 유저 seq, 포인트 양 정보 포함
	 * @return 포인트 거래 성공 여부
	 */
	public Boolean dealPoint(PointRequestDto pointRequestDto) {

		UserEntity fromUser = userRepository.findById(pointRequestDto.getFromUserSeq())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		UserEntity toUser = userRepository.findById(pointRequestDto.getToUserSeq())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		int pointAmount = pointRequestDto.getPointAmount();

		// 포인트 차감되는 유저의 포인트 양이 결제 양 이상인지 확인
		if (!hasEnoughPoint(fromUser, pointAmount)) throw new CustomException(ErrorCode.LACK_OF_POINTS);

		// 포인트 로그 저장
		PointLogEntity fromUserPointLog = pointLogRepository.save(
			PointLogEntity.builder()
				.amount(-pointAmount) // 감소하므로 음수값
				.typeCode(PointRequestTypeEnum.거래.toString())
				.user(fromUser)
				.build());

		PointLogEntity toUserPointLog = pointLogRepository.save(
			PointLogEntity.builder()
				.amount(pointAmount)
				.typeCode(PointRequestTypeEnum.거래.toString())
				.user(toUser)
				.build());

		// 유저의 포인트 로그 양방향 리스트 필드에 추가
		fromUser.getPointLogList().add(fromUserPointLog);
		toUser.getPointLogList().add(toUserPointLog);

		// 포인트 증가 / 차감
		subtractPoint(fromUser, pointAmount);
		addPoint(toUser, pointAmount);

		return true;
	}


	/**
	 * 포인트 출금 :
	 * - 대상 유저 포인트 양 감소(subtractPoint()), 계좌 거래 후, 포인트 로그에 추가
	 *
	 * @param pointRequestDto : 대상 유저 seq, 포인트 양, 계좌 번호 정보 포함
	 * @return
	 */
	public Boolean withdrawPoint(PointRequestDto pointRequestDto) {
		UserEntity fromUser = userRepository.findById(pointRequestDto.getFromUserSeq())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		int pointAmount = pointRequestDto.getPointAmount();

		// 포인트 차감되는 유저의 포인트 양이 결제 양 이상인지 확인
		if (!hasEnoughPoint(fromUser, pointAmount)) throw new CustomException(ErrorCode.LACK_OF_POINTS);

		// 포인트 로그 저장
		PointLogEntity fromUserPointLog = pointLogRepository.save(
			PointLogEntity.builder()
				.amount(-pointAmount) // 감소하므로 음수값
				.typeCode(PointRequestTypeEnum.출금.toString())
				.user(fromUser)
				.build());

		// 유저의 포인트 로그 양방향 리스트 필드에 추가
		fromUser.getPointLogList().add(fromUserPointLog);

		// 포인트 차감
		subtractPoint(fromUser, pointAmount);
		return true;
	}


	/**
	 * 대상 유저의 포인트 증가
	 *
	 * @param user        : 대상 유저
	 * @param pointAmount : 증가되는 포인트 양
	 * @return 증가 성공 여부
	 */
	private void addPoint(UserEntity user, Integer pointAmount) {
		long point = user.getPoint();
		user.setPoint(point + pointAmount);
		userRepository.save(user);
	}


	/**
	 * 대상 유저의 포인트 감소
	 *
	 * @param user        : 대상 유저
	 * @param pointAmount : 감소하는 포인트 양
	 * @return 감소 성공 여부
	 */
	private void subtractPoint(UserEntity user, Integer pointAmount) {
		long point = user.getPoint();
		user.setPoint(point - pointAmount);
		userRepository.save(user);
	}


	private Boolean hasEnoughPoint(UserEntity user, Integer pointAmount) {
		return user.getPoint() >= pointAmount ? true : false;
	}

}
