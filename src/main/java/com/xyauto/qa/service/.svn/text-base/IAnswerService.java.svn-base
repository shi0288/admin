package com.xyauto.qa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.vo.HingEntryAnswerVo;
import com.xyauto.qa.util.QaResult;

public interface IAnswerService extends ABaseService<Answer, Long> {
	/**
	 * 查询回复信息
	 * 
	 * @param answer
	 * @return
	 */
	public PageInfo<Answer> getAnswerList(Answer answer);

	/**
	 * 
	 * @param answer
	 * @param userType
	 *            :用户身份
	 * @param sort
	 *            ：排序方式
	 * @param keyWord
	 * @param status状态
	 *            ：删除 0，待审核 -1，垃圾 -2,正常 2
	 * @return
	 */
	public PageInfo<Answer> getAnswerList(Answer answer, Integer userType,
			String sort, String keyWord, Integer status, String nick_name);

	// 查询回复人数
	public int selectAnswerUserCount(String startTime, String endTime,
			Integer userType);

	// 查询点赞数最多的前20个回复---回复时间在时间范围内
	List<Answer> selectAnswerByAgreencount(String startTime, String endTime);

	// 导出表格
	void exportAnswer(HttpServletResponse response, Map<String, Object> map);

	/**
	 * 时间倒序查询前50个未被删除的回复id
	 * 
	 * @param uid
	 * @return
	 */
	List<Long> selectAnswerId(Long uid);

	/**
	 * 查询高能回答内容 ----专家回复满足200字且带图的
	 * 
	 * @param questionId
	 * @param isHingEnergy
	 *            :0:非高能回答，1：高能回答，null全部
	 * @return
	 */
	List<Answer> selectHingEnergyAnswer(Long questionId, Boolean isHingEnergy);

	/**
	 * 设置高能回答
	 * 
	 * @param answerId
	 * @param isHingEnergy
	 *            ：0非高能；1高能
	 * @param comment
	 *            ：点评内容
	 * @return
	 */
	String setHingEnergy(Long answerId, boolean isHingEnergy, String comment);

	/**
	 * 查询用户高能回答量
	 */
	List<HingEntryAnswerVo> selectPushHingEnergy(Long uid, String time);
	
	QaResult setFirstAnswer(Long answerId);
	
	Answer selectOne(Long answerId);
}
