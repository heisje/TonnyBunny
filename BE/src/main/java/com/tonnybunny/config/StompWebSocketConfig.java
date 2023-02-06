package com.tonnybunny.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@EnableWebSocketMessageBroker
@Configuration
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/**
	 * git
	 * WebSocket 커넥션을 생성할 경로를 설정한다.
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp")
		        .setAllowedOriginPatterns("*")
		        .withSockJS();
	}


	/**
	 * 사용할 메세지 브로커(STOMP)의 경로 설정
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/* 클라이언트에게 메세지를 전달할 경로 */
		registry.enableSimpleBroker("/sub");
		//		        .setTaskScheduler(taskScheduler())
		//		        .setHeartbeatValue(new long[] { 10000L, 100000L });

		/* STOMP 요청을 MessageMapping annotation 으로 보낼 prefix */
		registry.setApplicationDestinationPrefixes("/pub");

		/* specific user endpoint prefix (default /user) */
		//		registry.setUserDestinationPrefix("/user");
	}


	private TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.initialize();
		return taskScheduler;
	}

}
