package com.xyauto.qa.mapper;

import java.util.List;
import java.util.Map;

import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.util.BaseMapper;

public interface AnswerMapper extends BaseMapper<Answer>{
	List<Answer> getAnswerList(Answer answer);
	List<Answer> getAnswerListByMapParam(Map<String, Object> map);
	List<Answer> getDelAnswerListByMapParam(Map<String, Object> map);
	int selectAnswerUserCount(Map<String, Object> map);
	//分小时查询有效回答总数
	List<Integer> selectAnswerCountByMapGroupHours(Map<String, Object> map);
	/**
	 * 	查询回答总数--按小时、用户身份分组,有序集合按小时升序,
	 *  用agree_count接收count总数，create_at存储小时,source接收分类
	 */
	List<Answer> selectAnswerCountGroupHoursUserType(Map<String, Object> map);
	/**
	 * 	查询回答总数--按小时、问答渠道分组,有序集合按小时升序,用agree_count接收count总数，
	 *  create_at存储小时,source接收渠道分类
	 */
	List<Answer> selectAnswerSourceGroupHoursUserType(Map<String, Object> map);
	
	/**
	 * 根据时间查询每个用户答题总数(标兵和专家),用agree_count接收count总数，
	 * 可分按小时分段，根据字数查询
	 */
	List<Answer> selectAnswerQueCountGroupByTimeDayUid(Map<String, Object> map);
	/**
	 *  根据时间查询每个用户回答总数(标兵和专家),用agree_count接收count总数，
	 *  可分按小时分段，根据字数查询
	 */
	List<Answer> selectAnswersCountGroupByTimeDayUid(Map<String, Object> map);
	/**
	 * 	//查询每个用户(标兵和专家)回答字数大于30字的累计回复总数,用agree_count接收count总数，
	 */
	List<Answer> selectAnswersCountByContentGroupUid(Map<String, Object> map);
	/**
	 *查询每个用户(标兵和专家)回答字数大于30字的累计答题总数,用agree_count接收count总数，
	 */
	List<Answer> selectAnswersQueCountByContentGroupUid(Map<String, Object> map);
	//查询点赞数最多的前20个回复
	List<Answer> selectAnswerByAgreencount(Map<String, Object> map);
	//导出表格数据
	List<Answer> exportAnswer(Map<String, Object> map);
	//查询回传回复数据，用agree_count接收count总数，create_at存储小时,source接收分类
	//1:查询全部回传，2：查询有效回传数
	List<Answer> selectCallAnswerGroupHoursSource(Map<String, Object> map );
	//查询抢0回答题数
	List<Answer> selectFirstAnswerQueCountGroupDayUid(Map<String, Object> map);
	/**
	  * 时间倒序查询前50个未被删除的回复id
	  * @param uid
	  * @return
	  */
	List<Long> selectAnswerId(Long uid);
	/**
	 * 7月活动统计数据---统计专家3,5,10以上的答题数;按照用户id正序
	 * @param map
	 * @return
	 *    agreeCount:存储查询的数量
	 */
	List<Answer> selectAnswerQueCountByAnswerQueTime(Map<String, Object> map);
	/**
	 * 根据条件查询用户每天答题数
	 * @param userType:用户身份
	 * @param length:回复内容字数长度限制>=
	 * @param beforeDay:提数日期，正数当前日期往前推，负数往后推
	 * @return
	 *     agreeCount:存储查询的数量
	 */   
	List<Answer> selectAnswerQueCount(Map<String, Object> map);
	
	 /**
	  * 查询高能回答内容 ----专家回复满足200字且带图的
	  * @param questionId
	  * @return
	  */
	 List<Answer> selectHingEnergyAnswer(Answer answer );
	 
	 /**
	  * 查询用户的高能回答数
	  * @param map
	  * @return
	  */
	 List<Answer> selectPushHingEnergy(Map<String, Object> map);
	 
	 Answer selectFirstAnswer(Long answerId);
	 
	 int setFirstAnswer(Answer answer);
	
}
