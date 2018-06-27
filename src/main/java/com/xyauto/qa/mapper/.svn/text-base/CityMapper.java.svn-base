package com.xyauto.qa.mapper;

import java.util.List;
import java.util.Map;

import com.xyauto.bi.entity.vo.CityVo;
import com.xyauto.qa.entity.City;
import com.xyauto.qa.util.BaseMapper;

public interface CityMapper extends BaseMapper<City> {
	List<CityVo> selectCityByQuestionCount(Map<String, Object> map);
	List<CityVo> selectCityByAnswerCount(Map<String, Object> map);
	/**
	 * 查询用户所在城市
	 * @param uid
	 * @return
	 */
	List<City> selectCityByuid(Long uid);
}