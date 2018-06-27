package com.xyauto.mq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseQueueConfig {
	public static String exchange="qa_base_exchange";
	@Bean
	public DirectExchange defaultExchange() {
		return new DirectExchange(exchange, true, false);
	}
}
