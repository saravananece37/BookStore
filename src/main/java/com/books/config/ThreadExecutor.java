package com.books.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadExecutor {

	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
		t.setCorePoolSize(1000);
		t.setMaxPoolSize(2000);
		t.setQueueCapacity(12000);
		t.setThreadNamePrefix("Async");
		t.initialize();
		return t;

	}
}
