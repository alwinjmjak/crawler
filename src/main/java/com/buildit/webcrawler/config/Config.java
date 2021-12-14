package com.buildit.webcrawler.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

/**
 * Creating a async configuration class
 * @author Alwin
 *
 */
@Configuration
@EnableAsync
public class Config {
		
	/**
	 * create and return rest template bean
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	/**
	 * create and return a ThreadPoolTaskExecutor with default configuraiton
	 * @return
	 */
	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.initialize();
		return executor;
	}
}
