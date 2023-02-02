package com.tonnybunny.domain.jtonny.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonnybunny.domain.jtonny.dto.JTonnyApplyResponseDto;
import com.tonnybunny.domain.jtonny.entity.JTonnyApplyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Log4j2
@RequiredArgsConstructor
@Service
public class JTonnyApplySubscriber implements MessageListener {

	private final ObjectMapper objectMapper;
	private final SimpMessagingTemplate template;


	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			String msg = new String(message.getBody());
			JTonnyApplyEntity jTonnyApply = objectMapper.readValue(msg, JTonnyApplyEntity.class);

			log.info("jTonnyApply = {}", jTonnyApply);
			JTonnyApplyResponseDto jTonnyApplyResponseDto = JTonnyApplyResponseDto.fromEntity(jTonnyApply);
			template.convertAndSend("/sub/jtonny/user/{jtonny.getLanguage()}", jTonnyApplyResponseDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
