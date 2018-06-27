package com.xyauto.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchQueueConfig extends BaseQueueConfig{
	public static String refreshJob="refresh_job";
	@Bean
	public Queue refreshJobQueue() {
		return new Queue(refreshJob, true, false, false); // 队列持久
	}

	@Bean
	public Binding refreshJobBinding() {
		return BindingBuilder.bind(refreshJobQueue()).to(defaultExchange())
				.with(refreshJob);
	}
	
}
