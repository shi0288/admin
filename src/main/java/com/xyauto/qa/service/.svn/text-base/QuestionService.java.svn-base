package com.xyauto.qa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.xyauto.qa.entity.Brand;
import com.xyauto.qa.entity.Question;
import com.xyauto.qa.entity.Series;

/**
 * Created by shiqm on 2017/3/28.
 */
public interface QuestionService extends ABaseService<Question, Long> {
	PageInfo<Question> getQuestionList(Question question);

	void updateGood(Question question, boolean is);

	void updateTop(Question question, boolean is);

	void deleteQuestionById(Long questionId);

	PageInfo<Question> getDelQuestionList(Question question);

	void recoveryQuestionById(Long questionId);

	/**
	 * 
	 * @param question
	 * @param keyWord
	 * @param nick_name
	 * @param brand
	 * @param series
	 * @param statue
	 *            状态 ：删除 0，待审核 -1，垃圾 -2,正常 2
	 * @return
	 */
	PageInfo<Question> getQuestionListByMapParam(Question question,
			String keyWord, String nick_name, Integer userType, Brand brand,
			Series series, Integer statue);

	// 审核问题
	void verifyQuestion(Long questionId);

	// 根据时间段查询问题数最多的前20个回复
	List<Question> selectQuestionByAnswercount(String startTime, String endTime);

	Question getQuestionDesc(Long questionId);

	void updateQuestion(Question question, String[] attachs, String[] seriesIds);

	void exportQuestionListByMapParam(HttpServletResponse response,
			Map<String, Object> map);

	// 导出回收站数据
	void exportRubbishQuestion(HttpServletResponse response,
			Map<String, Object> map);

	/**
	 * 根据时间倒序排序，查询前50个未被删除的问题
	 * 
	 * @param uid
	 * @return
	 */
	List<Long> selectQuestionId(Long uid);

	/**
	 * 
	 * @param startTime
	 *            :
	 * @param endTime
	 *            :
	 * @param uid
	 *            :
	 * @return
	 */
	PageInfo<Question> selectHighEnergyQuestion(String startTime,
			String endTime, Question question);
	/**
	 * 回答恢复时，更新问题相关内容
	 *    回答数，回答人数，最先回答时间
	 * @param quetionId
	 * @return
	 */
	int updateAnswer(Long quetionId);
	
	/**
	 * 查询精华问题数
	 * @param isGood
	 * @return
	 */
	List<Long> selectGood(int isGood);
}
