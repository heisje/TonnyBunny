package com.tonnybunny.domain.jtonny.service;


import com.tonnybunny.domain.jtonny.dto.JTonnyDto;
import com.tonnybunny.domain.jtonny.dto.JTonnyUserDto;
import com.tonnybunny.domain.jtonny.entity.JTonnyEntity;
import com.tonnybunny.domain.jtonny.repository.JTonnyRepository;
import com.tonnybunny.domain.user.repository.UserRepository;
import com.tonnybunny.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.tonnybunny.exception.ErrorCode.NOT_FOUND_USER;


@Log4j2
@Service
@RequiredArgsConstructor
public class JTonnyService {

	private final UserRepository userRepository;
	private final JTonnyRepository jTonnyRepository;
	private final RedisTemplate<String, Object> redisTemplate;
	// helperInfoRepository

	/* client */


	/**
	 * 즉시 통역 공고 생성
	 *
	 * @param jTonnyDto : 즉시 통역 공고 생성 폼
	 */
	public void createJTonny(JTonnyDto jTonnyDto) {
		redisTemplate.convertAndSend("jtonny/request", jTonnyDto);
	}


	/**
	 * 즉시통역 공고 생성 취소
	 *
	 * @param jTonnyDto : 즉시 통역 공고 생성 폼
	 */
	public void deleteJTonny(JTonnyDto jTonnyDto) {
		redisTemplate.convertAndSend("jtonny/request-cancel", jTonnyDto);
	}


	/**
	 * 즉시통역 공고 신청 수락
	 *
	 * @param jTonnyDto : 즉시 통역 공고 생성 폼
	 */
	public void acceptJTonnyApply(JTonnyDto jTonnyDto) {
		// 공고 제거
		redisTemplate.convertAndSend("jtonny/request-cancel", jTonnyDto);

		log.info("ftofto", jTonnyDto);

		// JTonnyEntity 생성하여 DB 저장
		JTonnyEntity jTonny = JTonnyEntity.builder()
		                                  .client(userRepository.findById(jTonnyDto.getClient().getSeq())
		                                                        .orElseThrow(() -> new CustomException(NOT_FOUND_USER)))
		                                  .helper(userRepository.findById(jTonnyDto.getHelper().getSeq())
		                                                        .orElseThrow(() -> new CustomException(NOT_FOUND_USER)))
		                                  .taskCode(jTonnyDto.getTaskCode())
		                                  .taskStateCode(jTonnyDto.getTaskStateCode())
		                                  .startLangCode(jTonnyDto.getStartLangCode())
		                                  .endLangCode(jTonnyDto.getEndLangCode())
		                                  .tonnySituCode(jTonnyDto.getTonnySituCode())
		                                  .content(jTonnyDto.getContent())
		                                  .estimateTime(jTonnyDto.getEstimateTime())
		                                  .unitPrice(jTonnyDto.getUnitPrice())
		                                  .build();
		jTonnyRepository.save(jTonny);

		// 즉시통역을 위한 화상채팅 방으로 이동 (양 측에 uuid 전달)
		JTonnyDto jTonnyResponseDto = JTonnyDto.fromEntity(jTonny);
		redisTemplate.convertAndSend("jtonny/accept", jTonnyResponseDto);
	}


	/**
	 * 즉시통역 공고 신청 거절
	 *
	 * @param jTonnyDto : 즉시 통역 공고 생성 폼
	 */
	public void rejectJTonnyApply(JTonnyDto jTonnyDto) {
		Long helperSeq = jTonnyDto.getHelper().getSeq();
		jTonnyDto.setHelper(new JTonnyUserDto(userRepository.findById(helperSeq).get()));
		redisTemplate.convertAndSend("jtonny/reject", jTonnyDto);
	}


	/* helper */


	/**
	 * 즉시통역 공고 신청
	 *
	 * @param jTonnyDto : 즉시 통역 공고 생성 폼
	 */
	public void createJTonnyApply(JTonnyDto jTonnyDto) {
		redisTemplate.convertAndSend("jtonny/apply", jTonnyDto);
	}


	/**
	 * 즉시통역 공고 신청 취소
	 *
	 * @param jTonnyDto : 즉시 통역 공고 생성 폼
	 */
	public void deleteJTonnyApply(JTonnyDto jTonnyDto) {
		redisTemplate.convertAndSend("jtonny/apply-cancel", jTonnyDto);
	}

}
