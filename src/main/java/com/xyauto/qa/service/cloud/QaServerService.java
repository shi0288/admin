package com.xyauto.qa.service.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "QCDQ-QA-SERVER")
public interface QaServerService {
	public static String app_id="8d82c130f9a4a46ef43dc7841810992e";
	@RequestMapping(value = "/question/batch_add?app_id=8d82c130f9a4a46ef43dc7841810992e", method = RequestMethod.POST)
	public String batchAdd(@RequestParam("uid") long uid,
			@RequestParam("category_id") int category_id,
			@RequestParam("content") String content,
			@RequestParam("cars") String cars,
//			@RequestParam("app_id") String app_id,
			@RequestParam("created_at") int created_at,
			@RequestParam("today") int today);
}
