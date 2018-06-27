package com.xyauto.qa.mapper;

import java.util.List;
import java.util.Map;

import com.xyauto.qa.entity.Series;
import com.xyauto.qa.util.BaseMapper;

public interface SeriesMapper extends BaseMapper<Series> {
	//按时段查询问题数最多的前50个车型
	List<Series> selectSeriesByQuestionCount(Map<String, Object> map);
	
	/**
	 * 根据车型ID查询车型品牌信息
	 * @param list
	 * @return
	 */
	List<Series> selectListBySerieIds(List<Integer> list);
}