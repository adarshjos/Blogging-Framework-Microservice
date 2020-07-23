package com.IAM.shoutOut;

import com.IAM.shoutOut.authorization.filter.LoggingFilterPre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableEurekaClient
@EnableZuulProxy
public class ShoutOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoutOutApplication.class, args);
	}

	@Bean
	public LoggingFilterPre getLoggingFilterPre(){
		return new LoggingFilterPre();
	}
}
