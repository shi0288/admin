package com.xyauto.bi.service.basic;

import java.util.List;

import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.Question;

/**
 * 
 * @author zhangbh
 *
 */
public interface IQuestionBIBasicService {
	/**
	 * 查询问题总数--按小时分组---有序集合按小时升序
	 * @param beforeDay:提取当前时间的前N天(仅一天)的数据
	 * @return
	 */
	List<Integer> selectQuestionCountGroupHours(Integer beforeDay);
	//查询精华问题数---按小时分组---有序集合按小时升序 
	List<Integer> selectQuestionCountByGoodGroupHours(Integer beforeDay);
	//查询带图片问题数---按小时分组---有序集合按小时升序
	List<Integer> selectQuestionCountByAttachGroupHours(Integer beforeDay);
	//查询买车，用车问题总数--按小时/分类分组,有序集合按小时升序 ,用answer_count存储count总数,create_at存储小时,category_id存储分类
	List<Question> selectBuyCarQuestionCountGroupHours(Integer beforeDay);
	//查询回答总数--按小时分组,有序集合按小时升序
	List<Integer> selectAnswerCountByMapGroupHours(Integer beforeDay);
	/**
	 * 查询有回复信息的问题数，以及这些问题最快回答的总时间--按小时分组,
	 * 有序集合按小时升序,用answer_count存储count总数,create_at存储小时,first_answered_at存储回答总时间
	 */
	List<Question> selectQuestionByAnswerGroupHours(Integer beforeDay);
	/**
	 * 用户身份
	 */
	//查询问题总数--按小时、用户身份分组,有序集合按小时升序,用answer_count存储count总数,create_at存储小时,category_id存储分类
	List<Question> selectQuestionCountGroupHoursUserType(Integer beforeDay);
	//查询向用户提问总数--按小时、用户身份分组,有序集合按小时升序,用answer_count存储count总数,create_at存储小时,category_id存储分类
	List<Question> selectQuestionSpecifyCountGroupHoursUserType(Integer beforeDay);
	//查询回答总数--按小时、用户身份分组,有序集合按小时升序,用agree_count接收count总数，create_at存储小时,source接收分类
	List<Answer> selectAnswerCountGroupHoursUserType(Integer beforeDay);
	/**
	 * 渠道 来源
	 * status null：查询所有；1有效问题
	 */
	//查询问题总数--按小时、问答渠道分组,有序集合按小时升序,用answer_count存储count总数,create_at存储小时,source存储渠道分类
	List<Question> selectQuestionSourceGroupHoursUserType(Integer beforeDay,Integer status);
	//查询回答总数--按小时、问答渠道分组,有序集合按小时升序,用agree_count接收count总数，create_at存储小时,source接收渠道分类
	List<Answer> selectAnswerSourceGroupHoursUserType(Integer beforeDay);
	//查询删除的问题
	List<Question> selectDelQueGroupHoursSource(Integer beforeDay);
	//查询回传的回复数据，1：全部回传数，2：有效会传数
	List<Answer> selectCallAnswerGroupHoursSource(Integer type,Integer beforeDay);
	//有回复问题 数
	List<Question> selectAnswerQueGroupHoursSource(Integer beforeDay);
	/**
	 * 查询每个用户的数据(标兵、专家)----按天统计
	 */
	// 查询向每个用户提问总数
	List<Question> selectQuestionSpecifyCountGroupDayUid(Integer beforeDay);
	// 查询每个用户答题总数--可分按时间分段，可加字数限制条件
	List<Answer> selectAnswerQueCountGroupDayUid(Integer startHour,Integer endHour,Integer content,Integer beforeDay);
	// 查询每个用户回答总数--可分按时间分段，可加字数限制条件
	List<Answer> selectAnswersCountGroupDayUid(Integer startHour,Integer endHour,Integer content,Integer beforeDay);
	//查询抢0回答题数
	List<Answer> selectFirstAnswerQueCountGroupDayUid(Integer beforeDay);
	/**
	 * 查询累计数据
	 */
	// 查询向每个用户累计提问总数
	List<Question> selectQuestionCountGroupUid(Integer beforeDay);
	// 查询每个用户累计答题总数
	List<Answer> selectAnswerQueCountGroupUid(Integer beforeDay);
	// 查询每个用户累计回答总数
	List<Answer> selectAnswersCountGroupUid(Integer beforeDay);
	//查询每个用户回答字数大于30字的累计回复总数
	List<Answer> selectAnswersCountByContentGroupUid(Integer beforeDay);
	//查询每个用户回答字数大于30字的累计答题总数
	List<Answer> selectAnswersQueCountByContentGroupUid(Integer beforeDay);
	
	
	/**
	 * 
	 * 7月活动统计数据---统计专家3,5,10以上的答题数
	 * @param userType:用户身份
	 * @param length:回复内容字数长度限制>=
	 * @param minute:答题时间<=
	 * @param beforeDay:提数日期，正数当前日期往前推，负数往后推
	 * @return
	 */
	List<Answer> selectAnswerQueCountByAnswerQueTime(Integer userType,Integer length, Integer minute,Integer beforeDay);
	/**
	 * 根据条件查询用户每天答题数
	 * @param userType:用户身份
	 * @param length:回复内容字数长度限制>=
	 * @param beforeDay:提数日期，正数当前日期往前推，负数往后推
	 * @return
	 */
	List<Answer> selectAnswerQueCount(Integer userType,Integer length,
			Integer beforeDay);
	

}
