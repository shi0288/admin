package com.xyauto.qa.service.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "qcdq-qa-task" )
public interface TaskUserService {
	/**
	 * 立即执行定时任务
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/task/fastTask", method = RequestMethod.GET)
	public String startJob(@RequestParam("task_id") Long id);
}
