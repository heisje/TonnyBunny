package com.tonnybunny.config;


import com.tonnybunny.domain.chat.service.ChatSocketTextHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

	//	private final RedisTemplate<String, Object> redisTemplate;

	private final ChatSocketTextHandler chatSocketTextHandler;


	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatSocketTextHandler, "/chat", "/no-chat")
			.setAllowedOriginPatterns("*")
			.withSockJS();
	}

}