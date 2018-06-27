package com.xyauto.bi.service;

import java.util.List;
import java.util.Map;

import com.xyauto.bi.entity.QuestionBi;
import com.xyauto.bi.entity.QuestionTypeBi;
import com.xyauto.bi.entity.QuestionUserBi;
import com.xyauto.bi.entity.UserExamineBi;
import com.xyauto.bi.entity.vo.CityVo;
import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.Question;
import com.xyauto.qa.entity.Series;
import com.xyauto.qa.entity.User;


/**
 * 
 * @author zhangbh
 *
 */
public interface IQuestionBIService {
	/**
	 * 从问答数据中封装问答总数统计数据，仅供定时任务使用
	 * @param beforeDay:提取当前时间的前N天(仅一天)的数据
	 */
	int insertQuestionBI(Integer beforeDay);
	int deleteQuestionBI(Integer beforeDay);
	//从问答数据中封装按类型统计的数据,仅供定时任务使用
	int insertQuestionTypeBI(Integer beforeDay);
	int deleteQuestionTypeBI(Integer beforeDay);
	//从问题数据中封装按用户统计的数据，仅供定时任务使用
	int insertQuestionUserBI(Integer beforeDay);
	int deleteQuestionUserBI(Integer beforeDay);
	/**
	 * 7月活动统计数据---统计专家3,5,10,10分钟以上的答题数
	 * @param beforeDay
	 * @return
	 */
	int insertUserExamine(Integer beforeDay);
	int deleteUserExamine(Integer beforeDay);
	
	//查询问答总数--默认查询前一天的数据
	QuestionBi selectQuestionBi(Map<String,Object> map );
	//查询分类型问答数据
	List<QuestionTypeBi> selectQuestionTypeBi(Map<String,Object> map);
	//查询用户问答数据
	List<QuestionUserBi> selectQuestionUserBi(Map<String,Object> map);
	List<UserExamineBi> selectUserExamine(Map<String,Object> map);
	
	//查询活跃度--默认查询前一天
	String selectActiveUser(String startTime,String endTime,Integer userType);
	//查询问题最多的前50车型
	List<Series> selectCarsByQuesitoncount(String startTime,String endTime);
	//查询回答数最多的前20个问题---提问时间在时间段
	List<Question> selectQuestionByAnswercount(String startTime,String endTime);
	//查询点赞数最多的前20个回复---回复时间在时间段
	List<Answer> selectAnswerByAgreencount(String startTime,String endTime);
	//查询发布问题或回答量最多的前50个用户
	List<User> selectUserByQueCountOrAnsCount(String startTime,String endTime,String type);
	//查询城市分布前30以及关联的问题和回复数
	List<CityVo> selectCity(String startTime,String endTime,String type);
	/**
	 * 按照日期分组,查询问答基础数据,时间倒序,结果集中的创建时间已经减1天
	 * @param map
	 * @return
	 */
	List<QuestionBi> selectQuetionBiGroupByDay(Map<String,Object> map);
	/**
	 * 按照日期分组,查询数据,时间倒序,结果集中的创建时间已经减1天,typeValue采用in
	 * map.put("typeValue",list);
	 * @param map
	 * @return
	 */
	List<QuestionTypeBi> selectQuestionTypeBiGroupByDay(Map<String,Object> map);
	/**
	 * 查询活跃度,key：时间，value：活跃度
	 * @param map
	 * @return
	 */
	Map<Integer,String> selectActiveUserGroupByDay(String startDay,String endDay,Integer type);
	
	List<Integer> selectCarAdviser(String startTime,String endTime);

}
