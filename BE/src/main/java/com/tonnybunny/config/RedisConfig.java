//package com.tonnybunny.config;
//
//
//import com.tonnybunny.domain.jtonny.service.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//
//@Configuration
//@RequiredArgsConstructor
//public class RedisConfig {
//
//	private final JTonnyRequestSubscriber jTonnyRequestSubscriber;
//	private final JTonnyRequestCancelSubscriber jTonnyRequestCancelSubscriber;
//	private final JTonnyApplySubscriber jTonnyApplySubscriber;
//	private final JTonnyApplyCancelSubscriber jTonnyApplyCancelSubscriber;
//	private final JTonnyAcceptSubscriber jTonnyAcceptSubscriber;
//	private final JTonnyRejectSubscriber jTonnyRejectSubscriber;
//
//
//	@Bean
//	public RedisConnectionFactory redisConnectionFactory() {
//		return new LettuceConnectionFactory();
//	}
//
//
//	/**
//	 * redis pub/sub 메세지를 처리하는 listener 설정
//	 */
//	@Bean
//	public RedisMessageListenerContainer redisMessageListenerContainer() {
//		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//		container.setConnectionFactory(redisConnectionFactory());
//		container.addMessageListener(jTonnyRequestSubscriber, new ChannelTopic("jtonny/request"));
//		container.addMessageListener(jTonnyRequestCancelSubscriber, new ChannelTopic("jtonny/request-cancel"));
//		container.addMessageListener(jTonnyApplySubscriber, new ChannelTopic("jtonny/apply"));
//		container.addMessageListener(jTonnyApplyCancelSubscriber, new ChannelTopic("jtonny/apply-cancel"));
//		container.addMessageListener(jTonnyAcceptSubscriber, new ChannelTopic("jtonny/accept"));
//		container.addMessageListener(jTonnyRejectSubscriber, new ChannelTopic("jtonny/reject"));
//		//		container.addMessageListener(jTonnySubscriber, new ChannelTopic("chat"));
//
//		return container;
//	}
//
//
//	/**
//	 * redisTemplate 를 통해 redis 를 직접 사용할 수 있다.
//	 */
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(redisConnectionFactory());
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
//		return redisTemplate;
//	}
//
//}