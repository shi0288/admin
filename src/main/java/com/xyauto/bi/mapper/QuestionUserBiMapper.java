package com.xyauto.bi.mapper;

import java.util.List;
import java.util.Map;

import com.xyauto.bi.entity.QuestionUserBi;

public interface QuestionUserBiMapper {
	int insert(List<QuestionUserBi> questionUserBiList);
	
	List<QuestionUserBi> selectByMap(Map<String, Object> map);
	//根据用户身份查询用户人数---可带附加条件 
	int selectUserCountByUserType(Map<String, Object> map);
	/**
	 * 按照日期分组,查询用户数,时间倒序,结果集中的创建时间已经减1天,
	 * @param map
	 * @return
	 */
	List<QuestionUserBi> selectUserCountGroupByDay(Map<String,Object> map);
	int delete(Integer beforeDay);
}
