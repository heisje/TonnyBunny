package com.tonnybunny.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		           // setter 없이도 Entity 필드 접근 가능하게 하는 설정? 접근 안될 시 풀기
		           // .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
		           .setSkipNullEnabled(true);
		return modelMapper;
	}

}
