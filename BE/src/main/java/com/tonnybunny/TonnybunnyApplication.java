package com.tonnybunny;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class TonnybunnyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TonnybunnyApplication.class, args);
	}


	@PostConstruct
	void stz() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

}
