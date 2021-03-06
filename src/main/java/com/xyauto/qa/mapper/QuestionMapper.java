package com.xyauto.qa.mapper;

import com.github.pagehelper.PageInfo;
import com.xyauto.qa.entity.Question;
import com.xyauto.qa.util.BaseMapper;

import java.util.List;
import java.util.Map;

public interface QuestionMapper extends BaseMapper<Question> {

	Question getQuestionDesc(Long questionId);

	int updateHasAttach(Question question);

    List<Question> getQuestionList(Question question);

    int updateGood(Question question);

    int updateQuestion(Question question);

    int updateTop(Question question);

    int deleteQuestionById(Long questionId);

    int recoveryQuestionById(Long questionId);

    int verifyQuestionById(Long questionId);
    
    List<Question> getDelQuestionList(Question question);
    
    List<Question> getDelQuestionListByMapParam(Map<String, Object> map);

    List<Question> getQuestionListByMapParam(Map<String, Object> map);
    
    int getQuestionCountByMapParam(Map<String, Object> map);
    
    List<Integer> selectCountByMapGroupHours(Map<String, Object> map);
    //用answer_count存储count总数,create_at存储小时,category_id存储分类
    List<Question> selectBuyCarQuestionCountGroupHours(Map<String, Object> map);
    /**
     * 查询问题总数--按小时、用户身份分组,有序集合按小时升序,
     * 用answer_count存储count总数,create_at存储小时,category_id存储分类
     */
	List<Question> selectQuestionCountGroupHoursUserType(Map<String, Object> map);
	/**
	 * 查询向用户提问总数--按小时、用户身份分组,有序集合按小时升序,
	 * 用answer_count存储count总数,create_at存储小时,category_id存储分类
	 */
	List<Question> selectQuestionSpecifyCountGroupHoursUserType(Map<String, Object> map);
	/**
	 *查询问题总数--按小时、问答渠道分组,有序集合按小时升序,用answer_count存储count总数,
	 *create_at存储小时,source存储渠道分类
	 */
	List<Question> selectQuestionSourceGroupHoursUserType(Map<String, Object> map);
	/**
	 * 根据时间查询向每个用户(标兵和专家)提问总数 ,用answer_count存储count总数
	 */
	List<Question> selectQuestionSpecifyCountByTimeGroupDayUid(Map<String, Object> map);
	/**
	 * 查询有回复信息的问题数，以及这些问题最快回答的总时间--按小时分组,
	 * 有序集合按小时升序,用answer_count存储count总数,create_at存储小时,first_answered_at存储回答总时间
	 */
	List<Question> selectQuestionByAnswerGroupHours(Map<String, Object> map);

	//根据时间段查询回复数最多的前20个问题
	List<Question> selectQuestionByAnswercount(Map<String, Object> map);
	//导出表格时使用
	List<Question> exportQuestionListByMapParam(Map<String, Object> map);
	//查询删除的数据用answer_count存储count总数,create_at存储小时,source存储渠道分类
	List<Question> selectDelQueGroupHoursSource(Map<String, Object> map);
	//有回复问题 数
	List<Question> selectAnswerQueGroupHoursSource(Map<String, Object> map);
	//导出回收站表格
	List<Question> exportRubbishQuestion(Map<String, Object> map);
	/**
     * 根据时间倒序排序，查询前50个未被删除的问题
     * @param uid
     * @return
     */
	List<Long> selectQuestionId(Long uid);
	/**
	 * 查询高能问答
	 * @param map
	 * @return
	 */
	List<Question> selectHighEnergyQuestion(Map<String, Object> map);
	
	public int updateAnswer(Long quetionId);
	public List<Long> selectGood(int isGood);
}