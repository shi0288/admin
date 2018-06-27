package com.xyauto.mq;

import com.xyauto.system.entiy.Task;

public interface IRabbitMqService {
	
	void send(String exchange, String routingKey, Object message );
	void send( String routingKey, Object task );
	void send( String routingKey, Task task );
}
