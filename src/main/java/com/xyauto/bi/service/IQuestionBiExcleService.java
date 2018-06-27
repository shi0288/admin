package com.xyauto.bi.service;


import java.util.Map;

import javax.servlet.http.HttpServletResponse;




public interface IQuestionBiExcleService {
	//导出问答总数
	void exportQuestionBi(HttpServletResponse response,Map<String,Object> map);
	//按类型导出问答总数
	void exportQuestionTypeBi(HttpServletResponse response,Map<String,Object> map);
	//按用户导出问答总数
	void exportQuestionUserBi(HttpServletResponse response,Map<String,Object> map);
	//导出车型数据
	void exportCarsByQuesitoncount(HttpServletResponse response,String startTime,String endTime);
	//导出回复数最多的问题
	void exportQuestionByAnswercount(HttpServletResponse response,String startTime,String endTime);
	//导出点赞数最多的回复
	void exportAnswerByAgreencount(HttpServletResponse response,String startTime,String endTime);
	//导出提问/回复数最多的用户
	void exportUserByQueCountOrAnsCount(HttpServletResponse response,String startTime,String endTime,String type);
	//导出关联问题/回复最多的城市
	void exportCity(HttpServletResponse response,String startTime,String endTime,String type);
	
	/**
	 * 导出专家用户活动数据
	 * @param response
	 * @param startTime:开始时间
	 * @param endTime：结束时间
	 */
	void exportUserExpert(HttpServletResponse response,String startTime,String endTime);
}
