package com.tonnybunny.config;


import com.tonnybunny.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/**")             // 모든 url 에 대해 적용
			.excludePathPatterns("/login/**")   // 제외 1
			.excludePathPatterns("/signup/**")  // 제외 2
			.excludePathPatterns("/swagger-resources/**")   // swagger
			.excludePathPatterns("/swagger-ui/**")          // swagger
			.excludePathPatterns("/v2/**")                  // swagger
			.excludePathPatterns("/ytonny/**"); // (test)
	}

}
