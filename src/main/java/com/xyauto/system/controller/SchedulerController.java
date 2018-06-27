package com.xyauto.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xyauto.mq.IRabbitMqService;
import com.xyauto.mq.config.MessageEnum;
import com.xyauto.mq.config.SearchQueueConfig;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.service.cloud.TaskUserService;
import com.xyauto.qa.service.cloud.RUserService;
import com.xyauto.qa.util.QaResult;
import com.xyauto.system.entiy.Task;
import com.xyauto.system.service.ITaskService;

@Controller
@RequestMapping("user/system")
public class SchedulerController {
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IRabbitMqService rabbitMqService;
	@Autowired
	private TaskUserService taskUservice;

	@RequestMapping("jobList")
	public void selectJob(ModelMap modelMap, Task task) {
		List<Task> list = taskService.selectAll(task);
		modelMap.put("jobList", list);
		modelMap.put("task", task);
	}

	@RequestMapping("toAddJob")
	public void toAddJod() {

	}

	@RequestMapping("addJob")
	@ResponseBody
	public QaResult addJob(Task task) {
		if (StringUtils.isBlank(task.getCron())
				|| StringUtils.isBlank(task.getJobDetailName())
				|| StringUtils.isBlank(task.getJobGroupName())) {
			return new QaResult(ErrorCode.OVER, "参数错误");
		}
		task.setTriggerName(task.getJobDetailName());
		task.setTriggerGroupName(task.getJobGroupName());
		taskService.insert(task);
		Map<String, Object> map = new HashMap<String, Object>();
		if (task.getIsOpen() == 0) {
			map.put("code", MessageEnum.delete.getCode());
		} else {
			map.put("code", MessageEnum.add.getCode());
		}
		map.put("data", task);
		rabbitMqService.send(SearchQueueConfig.refreshJob,
				JSONObject.toJSONString(map));
		return new QaResult();
	}

	@RequestMapping("toUpdateJob")
	public void toUpdateJob(Long id, ModelMap modelMap) {
		Task task = new Task();
		task.setId(id);
		task = taskService.selectOne(task);
		modelMap.put("task", task);
	}

	@RequestMapping("updateJob")
	@ResponseBody
	public QaResult updateJob(Task task) {
		if (StringUtils.isBlank(task.getCron())
				|| StringUtils.isBlank(task.getJobDetailName())
				|| StringUtils.isBlank(task.getJobGroupName())) {
			return new QaResult(ErrorCode.OVER, "参数错误");
		}
		Task oldTask = new Task();
		oldTask.setId(task.getId());
		oldTask = taskService.selectOne(oldTask);
		boolean isdelete = false;
		if (!oldTask.getJobDetailName().equals(task.getJobDetailName())) {
			isdelete = true;
		}
		if (!oldTask.getJobGroupName().equals(task.getJobGroupName())) {
			isdelete = true;
		}
		task.setTriggerName(task.getJobDetailName());
		task.setTriggerGroupName(task.getJobGroupName());
		taskService.update(task);
		Map<String, Object> map = new HashMap<String, Object>();
		if (isdelete) {
			// 更新了任务名称，需要先删除任务在添加
			map.put("code", MessageEnum.delete.getCode());
			map.put("data", oldTask);
			rabbitMqService.send(SearchQueueConfig.refreshJob,
					JSONObject.toJSONString(map));
			map.put("code", MessageEnum.add.getCode());
			map.put("data", task);
			rabbitMqService.send(SearchQueueConfig.refreshJob,
					JSONObject.toJSONString(map));
		} else {
			// 修改了时间，只需要更新任务即可
			map.put("code", MessageEnum.update.getCode());
			map.put("data", task);
			rabbitMqService.send(SearchQueueConfig.refreshJob,
					JSONObject.toJSONString(map));
		}
		return new QaResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public QaResult delete(Long id) {
		Task task=new Task();
		task.setId(id);
		task=taskService.selectOne(task);
		//如果问题
		if (task.getIsOpen()==1) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", MessageEnum.delete.getCode());
			map.put("data", task);
			rabbitMqService.send(SearchQueueConfig.refreshJob,
					JSONObject.toJSONString(map));
		}
		taskService.delete(id);
		return new QaResult();
	}

	@RequestMapping("openJob")
	@ResponseBody
	public QaResult openJob(Long id, Short isOpen) {
		Task task = new Task();
		task.setId(id);
		task=taskService.selectOne(task);
		task.setIsOpen(isOpen);
		taskService.update(task);
		Map<String, Object> map = new HashMap<String, Object>();
		if (task.getIsOpen() == 0) {
			map.put("code", MessageEnum.delete.getCode());
		} else {
			map.put("code", MessageEnum.add.getCode());
		}
		map.put("data", task);
		rabbitMqService.send(SearchQueueConfig.refreshJob,
				JSONObject.toJSONString(map));
		return new QaResult();
	}

	@RequestMapping("startJob")
	@ResponseBody
	public QaResult startJob(Long id) {
		try {
			Task task = new Task();
			task.setId(id);
			task = taskService.selectOne(task);
			if (task == null) {
				return new QaResult(ErrorCode.OVER);
			}
			System.out.println("手动执行任务，任务id是："+id);
			String str = taskUservice.startJob(id);
			JSONObject jsonObject = JSON.parseObject(str);
			System.out.println("手动执行任务结束，返回结果是："+str);
			if (CommonCons.Cloud_User_Flag.CODE_OK.equals(jsonObject
					.getInteger("code"))) {
				return new QaResult(ErrorCode.OVER);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new QaResult(ErrorCode.OVER);
		}
		return new QaResult();		
	}

}
