package com.tonnybunny.domain.jtonny.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonnybunny.domain.jtonny.dto.JTonnyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Log4j2
@RequiredArgsConstructor
@Service
public class JTonnyRequestCancelSubscriber implements MessageListener {

	private final ObjectMapper objectMapper;
	private final SimpMessagingTemplate template;


	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {

			String msg = new String(message.getBody());
			JTonnyDto jTonnyDto = objectMapper.readValue(msg, JTonnyDto.class);

			log.info("jTonnyDto = {}", jTonnyDto);

			String url;

			/**
			 * 요청 언어를 구독중인 helper 에게 뜬 공고를 지우는 subscriber
			 * "/sub/jtonny/request/0020002/cancel"
			 */
			if (jTonnyDto.getStartLangCode().equals("0020001")) url = "/sub/jtonny/request/" + jTonnyDto.getEndLangCode() + "/cancel";
			else url = "/sub/jtonny/request/" + jTonnyDto.getStartLangCode() + "/cancel";

			template.convertAndSend(url, jTonnyDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
