package com.tonnybunny.domain.jtonny.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonnybunny.domain.jtonny.dto.JTonnyResponseDto;
import com.tonnybunny.domain.jtonny.entity.JTonnyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Log4j2
@RequiredArgsConstructor
@Service
public class JTonnyRequestSubscriber implements MessageListener {

	private final ObjectMapper objectMapper;
	private final SimpMessagingTemplate template;


	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			String msg = new String(message.getBody());
			JTonnyEntity jTonny = objectMapper.readValue(msg, JTonnyEntity.class);

			log.info("jtonny = {}", jTonny);
			JTonnyResponseDto jTonnyResponseDto = JTonnyResponseDto.fromEntity(jTonny);
			template.convertAndSend("/sub/jtonny/lang/{jtonny.getLanguage()}", jTonnyResponseDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
