package com.xyauto.bi.mapper;

import java.util.List;
import java.util.Map;

import com.xyauto.bi.entity.QuestionTypeBi;


public interface QuestionTypeBiMapper {
	int insert(List<QuestionTypeBi> list);
	List<QuestionTypeBi> selectByMap(Map<String, Object> map);

	List<QuestionTypeBi> selectByDays(Map<String, Object> map);

	List<QuestionTypeBi> selectCountByDays(Map<String, Object> map);
	
	/**
	 * 按照日期分组,查询数据,时间倒序,结果集中的创建时间已经减1天,typeValue采用in
	 * map.put("typeValue",list);
	 * @param map
	 * @return
	 */
	List<QuestionTypeBi> selectQuestionTypeBiGroupByDay(Map<String,Object> map);
	
	int delete(Integer beforeDay);

}
