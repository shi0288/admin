package com.xyauto.scheduled;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xyauto.qa.service.IAnswerService;
import com.xyauto.qa.service.QuestionService;
import com.xyauto.qa.service.cloud.QaUserService;
import com.xyauto.utils.cache.RedisKey.AnswerEnum;
import com.xyauto.utils.cache.RedisKeyUtil;
import com.xyauto.utils.cache.RedisKey.QuestionEnum;
import com.xyauto.utils.cache.RedisKeyUtil.Model;
import com.xyauto.utils.cache.service.ICacheService;

@Component
public class QuestionAnswerTask {
	@Autowired
    private QaUserService qaUserService;
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private QuestionService questionService; 
	@Autowired
    private IAnswerService answerService;
//	@Scheduled(cron = "0 0/1 * * * ?")
	protected void delQuestion(){
		boolean lock = false;
		String openkey=RedisKeyUtil.getKey(RedisKeyUtil.Model.question, "del_question_uid_task");
		cacheService.delString(openkey);
		lock = cacheService.setIfAbsent(openkey, "startTask", 30);
		if (!lock) {
			return ;
		}
		
		String key=RedisKeyUtil.getKey(Model.question, QuestionEnum.del_question_uid.getKey());
		List<Long> uidList = (List<Long>) cacheService.getObject(key);
		if (uidList==null ||uidList.size()<=0) {
			return ;
		}
		boolean isUpdateKey=false;
		List<Long> list=new ArrayList<Long>();
		for (Long uid : uidList) {
			List<Long> questionIdList= questionService.selectQuestionId(uid);
			if (questionIdList==null || questionIdList.size()<=0) {
				isUpdateKey=true;
				list.add(uid);
			}else {
				for (Long questionId : questionIdList) {
					//根据用户id获取用户问题(取前50条)
					String delInfo=qaUserService.deleteQuestion(questionId, 0L);
				}
			}
		}
		if (isUpdateKey) {
			uidList.removeAll(list);
			cacheService.set(key, uidList);
		}
				
	} 
//	@Scheduled(cron = "0 0/1 * * * ?")
	protected void delAnswer(){
		//集群部署简单锁
		boolean lock = false;
		String openkey=RedisKeyUtil.getKey(Model.answer, "del_answer_uid_task");
		cacheService.delString(openkey);
		lock = cacheService.setIfAbsent(openkey, "startTask", 30);
		if (!lock) {
			return ;
		}
		//获得锁执行任务内容
		String key=RedisKeyUtil.getKey(Model.answer, AnswerEnum.del_answer_uid.getKey());
		List<Long> uidList = (List<Long>) cacheService.getObject(key);
		if (uidList==null ||uidList.size()<=0) {
			return ;
		}
		boolean isUpdateKey=false;
		List<Long> list=new ArrayList<Long>();
		for (Long uid : uidList) {
			List<Long> AnswerIdList= answerService.selectAnswerId(uid);
			if (AnswerIdList==null || AnswerIdList.size()<=0) {
				isUpdateKey=true;
				list.add(uid);
			}else {
				for (Long answerId : AnswerIdList) {
					//根据用户id获取用户问题(取前50条)
					String delInfo=qaUserService.deleteAnswer(answerId, 0L);
				}
			}
		}
		if (isUpdateKey) {
			uidList.removeAll(list);
			cacheService.set(key, uidList);
		}
	}
	
}
