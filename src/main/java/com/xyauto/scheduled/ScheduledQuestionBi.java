package com.xyauto.scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.xyauto.bi.entity.QuestionBi;
import com.xyauto.bi.entity.QuestionTypeBi;
import com.xyauto.bi.service.IQuestionBIService;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.entity.Question;
import com.xyauto.qa.service.QuestionService;
import com.xyauto.qa.service.cloud.QaUserService;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.utils.cache.RedisKeyUtil;
import com.xyauto.utils.cache.service.ICacheService;
import com.xyauto.utils.sms.ISmsService;
import com.xyauto.utils.sms.vo.EmailVo;


@Component
public class ScheduledQuestionBi {
	@Log
	protected static Logger logger;
	@Autowired
	private IQuestionBIService questionBIService;
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private ISmsService smsService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private QaUserService qaUserService;
	
	@Autowired
    public Email _email; 
	
	@Component
	@ConfigurationProperties(prefix = "task")
	public class Email {
		private String open_send_email;
		
		public String getOpen_send_email() {
			return open_send_email;
		}

		public void setOpen_send_email(String open_send_email) {
			this.open_send_email = open_send_email;
		}
	}
	
	/**
	 * 定时发帖
	 */
	@Scheduled(cron = "0 0/2 * * * ?")
	public void releaseQuestion(){
		boolean lock = false;
		String key=RedisKeyUtil.getKey(RedisKeyUtil.Model.question, "release_question_task");
		lock = cacheService.setIfAbsent(key, "startTask", 30);	
		logger.info("定时发帖{}",lock);
		if (lock) {						
	    	String releaseKey=RedisKeyUtil.getKey(RedisKeyUtil.Model.question,"release_question");
	    	List<String> list=new ArrayList<String>();
	    	Map<String, Object> map=cacheService.getMap(releaseKey);
	    	Date date=new Date();
	    	boolean b=false;
			Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator(); 
			while (it.hasNext()) {				
				Map.Entry<String, Object> entry =  it.next();
				Date releaseDate=(Date)entry.getValue();
	    		if (releaseDate.before(date)) {
	    			logger.info("定时发帖开始执行");
	    			b=true;
	    			//更新状态值
	    			Question question= questionService.get(Long.parseLong(entry.getKey()));
	    			question.setStatus(CommonCons.Quesiotn_Flag.NORMAL_QUESTION);
	    			question.setUpdatedAt((int)(date.getTime()/1000));
	    			//logger.info("定时发布问题："+question.getQuestionId()+"删除时间："+question.getDeletedAt());
	    			//question.setDeletedAt(0);
	    			question.setCreatedAt((int)(date.getTime()/1000));
	    			questionService.update(question);
	    			//更新问题索引
	    	        qaUserService.updateQuestion(question.getQuestionId());
					list.add(entry.getKey());		
				}
			}	    	
	    	if (b) {
	    		for (String string : list) {
	    			map.remove(string);
				}	    		
	    		cacheService.del(releaseKey);
	        	cacheService.set(releaseKey, map);
			}
		}
	}
	
	/**
	 * 定时提数
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	protected void insertQuestionBi() {
		boolean lock = false;
		try {
			logger.info("定时任务开启");
			String key=RedisKeyUtil.getKey(RedisKeyUtil.Model.bidata, "bi_task");
			lock = cacheService.setIfAbsent(key, "startTask", 5 * 60);
			if (lock) {
				questionBIService.insertQuestionBI(1);
				questionBIService.insertQuestionTypeBI(1);
				questionBIService.insertQuestionUserBI(1);
				questionBIService.insertUserExamine(1);
			} else {
				logger.info("没有获得锁，任务结束");
				return ;
			}
			logger.info("定时任务结束");
		} catch (Exception e) {
			logger.error("定时任务统计BI数据出错：" + e);
		}
	}

	@Scheduled(cron="0 0 6 * * ?")
	protected void sendEmail() {
		String str=_email.getOpen_send_email();
		if (StringUtils.isNotBlank(str)&&str.equals("false")) {
			return;
		}
		boolean lock = false;
		try {
			String key=RedisKeyUtil.getKey(RedisKeyUtil.Model.bidata, "send_email_task");
			lock = cacheService.setIfAbsent(key, "startTask", 15 * 60);
			if (lock) {
				logger.info("开始发送日报");
				String[] receiveMailToUser = { "xiaojn","duqq","liumd","liuz","wangbin","xiebx" };
				String[] receiveMailCCUser ={"zhangbh","lipeng","shiqm"};
				QuestionBi questionBi = questionBIService.selectQuestionBi(null);
				if (questionBi==null) {
					logger.error("日报发送失败，昨天数据没有导出");
					return ;
				}
				List<QuestionTypeBi> typeList = questionBIService
						.selectQuestionTypeBi(null);
				//获取活跃度
				String expertActive=questionBIService.selectActiveUser(null, null, 2);
				String pacesetterActive=questionBIService.selectActiveUser(null, null, 1);
				//平均响应时间
				int time = 0;
				if (questionBi.getRemark1() > 0) {
					time = (questionBi.getRemark2()/questionBi.getRemark1())/60;
				}
				int expertQue = 0,expertAnswer=0;
				int pacesetterQue = 0,pacesetterAnswer=0;	
				//普通用户有效问题
				int validQueCount=0;
				StringBuffer buffer = new StringBuffer();
				buffer.append("<html><body>");
				StringBuffer buffer1 = new StringBuffer();
				buffer1.append("问答基础数据统计<table><tr><td width='100' align='right'>有效问题:</td><td width='50'>");
				buffer1.append(questionBi.getQuestionCount());
				buffer1.append("</td><td width='100' align='right'>精华问题:</td><td width='50'>");
				buffer1.append(questionBi.getGoodCount());
				buffer1.append("</td><td width='100' align='right'>带图问题:</td><td width='50'>");
				buffer1.append(questionBi.getHasAttachCount());
				buffer1.append("</td><td width='100' align='right'>有回复问题:</td><td width='50'>");
				buffer1.append(questionBi.getRemark1());
				buffer1.append("</td></tr><tr><td width='100' align='right'>回复数:</td><td width='50'>");
				buffer1.append(questionBi.getAnswerCount());
				buffer1.append("<td width='100' align='right'>买车问题:</td><td width='50'>");
				buffer1.append(questionBi.getBuyCarCount());
				buffer1.append("<td width='100' align='right'>用车问题:</td><td width='50'>");
				buffer1.append(questionBi.getSellCarCount());
				buffer1.append("<td width='100' align='right'>平均响应时间:</td><td width='50'>");
				buffer1.append(time + "</td></tr></table>");		
				StringBuffer buffer2 = new StringBuffer();
				buffer2.append("<p/>用户身份数据统计<table>");
				StringBuffer buffer3 = new StringBuffer();
				buffer3.append("<p/>渠道问题来源统计<table><tr>");
				StringBuffer buffer4 = new StringBuffer();
				buffer4.append("<p/>渠道回答数据统计<table><tr>");
				for (QuestionTypeBi questionTypeBi : typeList) {
					if (questionTypeBi.getType().equalsIgnoreCase("userType")) {
						if (questionTypeBi.getTypeValue() == 1) {
							pacesetterQue=questionTypeBi.getQuestionCount();
							pacesetterAnswer=questionTypeBi.getAnswerCount();
							buffer2.append("<tr><td width='100' align='right'>向标兵提问:</td><td width='50'>");
							buffer2.append(questionTypeBi.getQuestionSpecifyCount());
							buffer2.append("<td width='100' align='right'>标兵提问:</td><td width='50'>");
							buffer2.append(questionTypeBi.getQuestionCount());
							buffer2.append("<td width='100' align='right'>标兵回复:</td><td width='50'>");
							buffer2.append(questionTypeBi.getAnswerCount()); 
							buffer2.append("<td width='100' align='right'>标兵活跃:</td><td width='50'>");
							buffer2.append(pacesetterActive);
							buffer2.append("</td></tr>");
						}
						if (questionTypeBi.getTypeValue() == 2) {
							expertQue=questionTypeBi.getQuestionCount();
							expertAnswer=questionTypeBi.getAnswerCount();
							buffer2.append("<tr><td width='100' align='right'>向专家提问:</td><td width='50'>");
							buffer2.append(questionTypeBi.getQuestionSpecifyCount());
							buffer2.append("</td><td width='100' align='right'>专家提问:</td><td width='50'>");
							buffer2.append(questionTypeBi.getQuestionCount());
							buffer2.append("</td><td width='100' align='right'>专家回复:</td><td width='50'>");
							buffer2.append(questionTypeBi.getAnswerCount()); 
							buffer2.append("</td><td width='100' align='right'>专家活跃:</td><td width='80'>");
							buffer2.append(expertActive);
							buffer2.append("</td></tr>");
						}
					}
					if (questionTypeBi.getType().equalsIgnoreCase("source")) {
						int type=questionTypeBi.getTypeValue();
						if (type == 101) {
							//惠商机
							buffer3.append("<td width='100' align='right'>惠商机问题:</td><td width='50'>");
							buffer3.append(questionTypeBi.getQuestionCount()).append("</td>");
							buffer4.append("<td width='100' align='right'>惠商机回复:</td><td width='50'>");
							buffer4.append(questionTypeBi.getAnswerCount()).append("</td>");
						}
						if (type == 102) {
							//百度
							buffer3.append("<td width='100' align='right'>百度问题:</td><td width='50'>");
							buffer3.append(questionTypeBi.getQuestionCount()).append("</td>");
						}
						if (type == 103) {
							//360
							buffer3.append("<td width='100' align='right'>360问题:</td><td width='50'>");
							buffer3.append(questionTypeBi.getQuestionCount()).append("</td>");
						}
						if (type==1||type==2||type==3||type==4|type==5||type==6||type==998) {
							validQueCount+=questionTypeBi.getValidQuestionCount();
						}
					}
				}
				buffer2.append("<tr><td width='100' align='right'>普通用户问题:</td><td width='50'>");
				buffer2.append(validQueCount-expertQue-pacesetterQue);
				buffer2.append("</td><td width='100' align='right'>普通用户回复:</td><td width='50'>");
				buffer2.append(questionBi.getAnswerCount()-expertAnswer-pacesetterAnswer);
				buffer2.append("</td></tr></table>");
				buffer3.append("</tr></table>");
				buffer4.append("</tr></table>");
				buffer.append(buffer1).append(buffer2).append(buffer3).append(buffer4);
				buffer.append("</body></html>");
				EmailVo emailVo = new EmailVo();
				emailVo.setReceiveMailToUser(receiveMailToUser);
				emailVo.setContent(buffer.toString());
				emailVo.setReceiveMailCCUser(receiveMailCCUser);
				Date date=DateUtils.beforDate(new Date(), 1);
				String sendDay=DateUtils.dateToStr(date);
				emailVo.setSubject(sendDay+"问答运营数据日报");
				smsService.sendEmailAli(emailVo);
				logger.info("发送日报成功");
			}
		} catch (Exception e) {
			logger.info("发送日报失败"+e);
			
		}
	}
	
}
