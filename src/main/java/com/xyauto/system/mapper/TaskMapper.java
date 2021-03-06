package com.xyauto.system.mapper;

import java.util.List;

import com.xyauto.system.entiy.Task;

public interface TaskMapper {
	void insert(Task task);
	/**
	 * @param task
	 *  id或triggerGroupName+triggername
	 *  或jobGroupName+jobdetailname必须选择一项
	 * @return
	 */
	Task selectOne(Task task);
	List<Task> selectAll(Task task);
	/**
	 * @param task
	 *  id或triggerGroupName+triggername
	 *  或jobGroupName+jobdetailname必须选择一项
	 * @return
	 */
	void update(Task task);
	
	int delete(Long id);
}
