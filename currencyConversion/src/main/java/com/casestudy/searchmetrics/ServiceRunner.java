package com.casestudy.searchmetrics;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * This is the main Configuration class for Spring
 * @author Admin
 * @version 1.0
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ServiceRunner {
	public static void main(String [] args) {
		SpringApplication.run(ServiceRunner.class, args);
	}
	 
	@Bean 
	public RestTemplate restTemplatBean() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	 @Bean
	    public Executor taskExecutor() {
	        return new SimpleAsyncTaskExecutor();
	    }
	 
}
 