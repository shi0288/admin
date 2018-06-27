package com.xyauto.mq.impl;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.mq.IRabbitMqService;
import com.xyauto.mq.config.SearchQueueConfig;
import com.xyauto.system.entiy.Task;

@Service
public class RabbitMqServiceImpl implements IRabbitMqService{

	@Autowired 
	private RabbitTemplate rabbitTemplate;
	@Override
	public void send(String exchange, String routingKey, Object message) {		
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}
	@Override
	public void send(String routingKey, Object task) {
		rabbitTemplate.convertAndSend(SearchQueueConfig.exchange, routingKey, task);		
	}
	@Override
	public void send(String routingKey, Task task) {
		StringBuffer buffer=new StringBuffer();
		buffer.append(task.getTriggerGroupName()).append(";").append(task.getTriggerName());
		rabbitTemplate.convertAndSend(SearchQueueConfig.exchange, routingKey, buffer.toString());		
	}
	
}
