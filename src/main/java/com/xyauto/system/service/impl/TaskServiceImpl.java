package com.xyauto.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.service.impl.ABaseServiceImpl;
import com.xyauto.system.entiy.Task;
import com.xyauto.system.mapper.TaskMapper;
import com.xyauto.system.service.ITaskService;
@Service
public class TaskServiceImpl implements ITaskService{
	@Autowired
	private TaskMapper taskMapper;
	@Log
    protected Logger logger;
	@Override
	public void insert(Task task) {
		try {
			taskMapper.insert(task);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("插入数据出错，参数是task{}:"+task.toString());
		}		
	}

	@Override
	public Task selectOne(Task task) {
		try {
			task=taskMapper.selectOne(task);
			return task;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询数据出错，参数是task{}:"+task.toString());
		}		
		return null;
	}

	@Override
	public List<Task> selectAll(Task task) {
		try {
			List<Task> taskList=taskMapper.selectAll(task);
			return taskList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询数据出错，参数是task{}:"+task.toString());
		}		
		return null;
	}

	@Override
	public void update(Task task) {
		try {
			taskMapper.update(task);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新数据出错，参数是task{}:"+task.toString());
		}		
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return taskMapper.delete(id);
	}

}
