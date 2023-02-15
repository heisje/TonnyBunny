package com.tonnybunny.domain.alert.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonnybunny.domain.alert.dto.AlertLogRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Log4j2
@RequiredArgsConstructor
@Service
public class AlertApplyCancelSubscriber implements MessageListener {

	private final ObjectMapper objectMapper;
	private final SimpMessagingTemplate template;


	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {

			String msg = new String(message.getBody());
			AlertLogRequestDto alertLogRequestDto = objectMapper.readValue(msg, AlertLogRequestDto.class);

			log.info("alertLogRequestDto = {}", alertLogRequestDto);

			String url;
			url = "/sub/alert/apply/cancel" + alertLogRequestDto.getClientSeq();
			template.convertAndSend(url, alertLogRequestDto);

			url = "/sub/alert/apply/cancel" + alertLogRequestDto.getHelperSeq();
			template.convertAndSend(url, alertLogRequestDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
