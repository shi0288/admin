package com.xyauto.scheduled;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.service.UserService;
import com.xyauto.utils.cache.RedisKeyUtil;
import com.xyauto.utils.cache.RedisKeyUtil.Model;
import com.xyauto.utils.cache.service.ICacheService;


@Component
public class UserTask {
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private UserService userService;
	@Log
	private Logger logger; 
	/**
	 * 恢复禁言用户身份
	 */
//	@Scheduled(cron = "0 0/10 * * * ?")
	public void updateUser() {
		// 集群部署简单锁
		boolean lock = false;
		String openkey = RedisKeyUtil.getKey(Model.answer,
				"recover_uid_task");
		lock = cacheService.setIfAbsent(openkey, "startTask", 20);
		Long seconds=cacheService.getTime(openkey, TimeUnit.SECONDS);
		if (!lock) {
			return;
		}
		logger.info("获得到锁，开始执行，时间是："+(int)(new Date().getTime()/1000));
		//执行任务
		userService.recoverUser();
	}
}
