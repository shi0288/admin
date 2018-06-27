package com.xyauto.qa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xyauto.qa.entity.Series;
import com.xyauto.qa.mapper.SeriesMapper;
import com.xyauto.qa.service.SeriesService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shiqm on 2017/3/27.
 */

@Service
public class SeriesServiceImpl extends ABaseServiceImpl<Series,Integer> implements SeriesService{

	@Autowired
	private SeriesMapper seriesMapper;
	@Override
	public List<Series> selectSeriesByQuestionCount(String startTime,
			String endTime) {
		if (StringUtils.isBlank(startTime)||StringUtils.isBlank(endTime)) {
			logger.info("参数有误");
			return null;
		}
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);		
		return seriesMapper.selectSeriesByQuestionCount(map);
	}
	@Override
	public List<Series> selectListBySerieIds(List<Integer> list) {
		// TODO Auto-generated method stub
		if (list==null||list.size()==0) {
			return null;
		}
		return seriesMapper.selectListBySerieIds(list);
	}
}
