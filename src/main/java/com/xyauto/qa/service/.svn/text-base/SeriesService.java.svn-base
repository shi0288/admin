package com.xyauto.qa.service;

import java.util.List;

import com.xyauto.qa.entity.Series;

/**
 * Created by shiqm on 2017/3/27.
 */
public interface SeriesService extends ABaseService<Series,Integer>{
	//按时段查询问题数最多的前50个车型
	List<Series> selectSeriesByQuestionCount(String startTime,String endTime);
	
	/**
	 * 根据车型ID查询车型品牌信息
	 * @param list
	 * @return
	 */
	List<Series> selectListBySerieIds(List<Integer> list);
}
