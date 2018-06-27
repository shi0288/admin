package com.xyauto.bi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Multiset.Entry;
import com.xyauto.bi.entity.QuestionBi;
import com.xyauto.bi.entity.QuestionTypeBi;
import com.xyauto.bi.service.IQuestionBIService;
import com.xyauto.bi.service.IQuestionBiExcleService;
import com.xyauto.qa.controller.AjaxBaseController;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.qa.util.QaResult;

/**
 * 
 * @author zhangbh
 *
 */
@Controller
@RequestMapping("user/biData")
public class QuestoinBIController extends AjaxBaseController {
	@Autowired
	private IQuestionBIService questionBIService;
	@Autowired
	private IQuestionBiExcleService questionBiExcleService;

	//手动提数
	@RequestMapping("qustionBasic")
	public void qustionBasic(String startDay, String endDay) {		
		questionBIService.insertQuestionBI(1);
		questionBIService.insertQuestionTypeBI(1);
		questionBIService.insertQuestionUserBI(1);
		questionBIService.insertUserExamine(1);
	}
	@RequestMapping("qustionBi")
	public void qustionBi(Integer beforeDay) {	
		questionBIService.deleteQuestionBI(beforeDay);
		questionBIService.insertQuestionBI(beforeDay);
	}
	
	@RequestMapping("qustionUserBi")
	public void qustionUserBi(Integer beforeDay) {
		questionBIService.deleteQuestionUserBI(beforeDay);
		questionBIService.insertQuestionUserBI(beforeDay);
	}
	@RequestMapping("userExamineBi")
	public void UserExamineBi(Integer beforeDay) {
		questionBIService.deleteUserExamine(beforeDay);
		questionBIService.insertUserExamine(beforeDay);
	}
	
	@RequestMapping("questionTypeBI")
	public void QuestionTypeBI(Integer beforeDay) {		
		questionBIService.deleteQuestionTypeBI(beforeDay);
		questionBIService.insertQuestionTypeBI(beforeDay);
	}
 
	@RequestMapping("initBi")
	public void initBi() {}

	@RequestMapping("initBiQuestion")
	public void initBiQuestion() {}

	@RequestMapping("initBiQuestionLine")
	public void initBiQuestionLine() {}

	@RequestMapping("exportQueBi")
	public void exportQueBi(HttpServletResponse response, String startTime,
			String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		questionBiExcleService.exportQuestionBi(response, map);
	}
	
	@RequestMapping("exportQueTypeBi")
	public void exportQueTypeBi(HttpServletResponse response, String startTime,
			String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		questionBiExcleService.exportQuestionTypeBi(response, map);
	}
	@RequestMapping("exportQueUserBi")
	public void exportQueUserBi(HttpServletResponse response, String startTime,
			String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		questionBiExcleService.exportQuestionUserBi(response, map);
	}
	
	@RequestMapping("exportUserExpert")
	public void exportUserExpert(HttpServletResponse response, String startTime,
			String endTime) {
		
		questionBiExcleService.exportUserExpert(response,  startTime, endTime);
	}
	
	@RequestMapping("basicBi")
	public void basicBi(ModelMap modelMap,String startTime,String endTime){
		//查询基本数据
		modelMap.put("startTime", startTime);
		modelMap.put("endTime", endTime);
		QuestionBi questionBi = questionBIService.selectQuestionBi(modelMap);
		if (questionBi==null) {
			return ;
		}
		//查询外部渠道信息数据
		List<QuestionTypeBi> typeList = questionBIService
				.selectQuestionTypeBi(modelMap);		
		//获取活跃度
		String expertActive=questionBIService.selectActiveUser(startTime, endTime, 2);
		String pacesetterActive=questionBIService.selectActiveUser(startTime, endTime, 1);
		//获取车顾问活跃人数，答题数，回复数
		List<Integer>  carAdviser=questionBIService.selectCarAdviser(startTime, endTime);
		if (carAdviser!=null&&carAdviser.size()>0) {
			modelMap.put("adviser_answer_count", carAdviser.get(0));
			modelMap.put("adviser_question_count", carAdviser.get(1));
			modelMap.put("adviser_user_count", carAdviser.get(2));
		}
		//计算平均时间
		int avgTime=0;
		if (questionBi.getRemark1()>0) {
			avgTime=questionBi.getRemark2()/questionBi.getRemark1()/60;
		}
		modelMap.put("questionBi", questionBi);
		modelMap.put("typeList", typeList);
		modelMap.put("expertActive", expertActive);
		modelMap.put("pacesetterActive", pacesetterActive);
		modelMap.put("avgTime", avgTime);		
	}
	@RequestMapping("topBi")
	public void topbi(ModelMap modelMap,String startTime,String endTime,Integer selectType){
		if (selectType==null) {
			return;
		}
		//默认查询当天数据
		if (StringUtils.isBlank(startTime)||StringUtils.isBlank(endTime)) {
			startTime=endTime=DateUtils.dateToStr(new Date());
		}	
		modelMap.put("startTime", startTime);
		modelMap.put("endTime", endTime);
		//查询车型		
		List list=null;
		if (selectType==1) {
			list=questionBIService.selectCarsByQuesitoncount(startTime, endTime);			
		}
		else if (selectType==2) {
			//查询问题
			list=questionBIService.selectQuestionByAnswercount(startTime, endTime);	
		}else if(selectType==3){
			//查询回复
			list=questionBIService.selectAnswerByAgreencount(startTime, endTime);
		}else if(selectType==4){
			//查询发布问题最多的用户
			list=questionBIService.selectUserByQueCountOrAnsCount(startTime, endTime, "question");
		}else if(selectType==5){
			//查询回答数最多的用户
			list=questionBIService.selectUserByQueCountOrAnsCount(startTime, endTime, "answer");
		}else if(selectType==6){
			//查询发布问题最多的城市
			list=questionBIService.selectCity(startTime, endTime, "question");
		}else if(selectType==7){
			//查询回复内容最多的城市
			list=questionBIService.selectCity(startTime, endTime, "answer");
		}
		modelMap.put("selectType", selectType);
		modelMap.put("list", list);
	}
	@RequestMapping("exportTopBi")
	public void exportTopBi(HttpServletResponse response,String startTime,String endTime,Integer selectType){
		if (selectType==null) {
			return;
		}
		//默认查询当天数据
		if (StringUtils.isBlank(startTime)||StringUtils.isBlank(endTime)) {
			startTime=endTime=DateUtils.dateToStr(new Date());
		}	
		if (selectType==1) {
			questionBiExcleService.exportCarsByQuesitoncount(response,startTime, endTime);
		}
		else if (selectType==2) {
			//查询问题
			questionBiExcleService.exportQuestionByAnswercount(response,startTime, endTime);
		}else if(selectType==3){
			//查询回复
			questionBiExcleService.exportAnswerByAgreencount(response,startTime, endTime);
		}else if(selectType==4){
			//查询发布问题最多的用户
			questionBiExcleService.exportUserByQueCountOrAnsCount(response,startTime, endTime, "question");
		}else if(selectType==5){
			//查询回答数最多的用户
			questionBiExcleService.exportUserByQueCountOrAnsCount(response,startTime, endTime, "answer");
		}else if(selectType==6){
			//查询发布问题最多的城市
			questionBiExcleService.exportCity(response,startTime, endTime, "question");
		}else if(selectType==7){
			//查询回复内容最多的城市
			questionBiExcleService.exportCity(response,startTime, endTime, "answer");
		}		
	}
	
	/**
	 * 由于数据提取规则是当天提取前一天的数据，因此要在查询时时间要往后推一天
	 * @param source
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping("questionCharts")
	@ResponseBody
	public QaResult  questionCharts(Integer source,String startDate,String endDate){

		if (StringUtils.isBlank(startDate)||StringUtils.isBlank(endDate)) {
			Date time = new Date();
	        int daySplit = 6;
	        startDate = DateUtils.dateToStr(DateUtils.beforDate(time, daySplit));
	        endDate = DateUtils.dateToStr(DateUtils.beforDate(time, 0));
		}else {
			startDate=DateUtils.dateToStr(DateUtils.beforDate(startDate, -1));
			endDate=DateUtils.dateToStr(DateUtils.beforDate(endDate, -1));
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startTime", startDate);
		map.put("endTime", endDate);
        JSONObject data = new JSONObject();
        JSONObject object = new JSONObject();
        JSONArray times=new JSONArray();
		if (source==null||source==1) {
	        List<QuestionBi> list = questionBIService
	                .selectQuetionBiGroupByDay(map);
	        JSONArray questionCount=new JSONArray();
	        JSONArray buyCarQueCount=new JSONArray();
	        JSONArray sellCarQueCount=new JSONArray();       
	        for (QuestionBi bi : list) {
	            questionCount.add(bi.getQuestionCount());
	            buyCarQueCount.add(bi.getBuyCarCount());
	            sellCarQueCount.add(bi.getSellCarCount());
	            times.add(DateUtils.intToDateString(bi.getCreatedAt(), "yy年M月d日"));
	        }
	        
	      //内部数据
			List<Integer> typeValueList=new ArrayList<Integer>();
			typeValueList.add(1);
			typeValueList.add(2);
			typeValueList.add(3);
			typeValueList.add(4);
			typeValueList.add(5);
			typeValueList.add(6);
			typeValueList.add(998);
			map.put("type", "source");
			map.put("typeValue", typeValueList);
			List<QuestionTypeBi> typeList = questionBIService
					.selectQuestionTypeBiGroupByDay(map);
			JSONArray pcQuestionCount=new JSONArray();
	        JSONArray iosQuestionCount=new JSONArray();
	        JSONArray androidQuestionCount=new JSONArray(); 
	        JSONArray mQuestionCount=new JSONArray();
	        JSONArray h5QuestionCount=new JSONArray();
	        JSONArray backQuestionCount=new JSONArray(); 
			for (QuestionTypeBi bi : typeList) {
				if (bi.getTypeValue()==1) {
					pcQuestionCount.add(bi.getValidQuestionCount());
				}else if (bi.getTypeValue()==2) {
					iosQuestionCount.add(bi.getValidQuestionCount());
				}else if (bi.getTypeValue()==3) {
					androidQuestionCount.add(bi.getValidQuestionCount());
				}else if (bi.getTypeValue()==4) {
					mQuestionCount.add(bi.getValidQuestionCount());
				}else if (bi.getTypeValue()==5) {
					h5QuestionCount.add(bi.getValidQuestionCount());
				}else if (bi.getTypeValue()==6) {
					//h5QuestionCount.add(bi.getValidQuestionCount());
				}else if (bi.getTypeValue()==998) {
					backQuestionCount.add(bi.getValidQuestionCount());
				}
			}
			object.put("有效问题数",questionCount);
	        object.put("买车",buyCarQueCount);
	        object.put("用车",sellCarQueCount);
			object.put("pc",pcQuestionCount);
	        object.put("ios",iosQuestionCount);
	        object.put("android",androidQuestionCount);
	        object.put("m",mQuestionCount);
	        object.put("H5",h5QuestionCount);
	        object.put("后台",backQuestionCount);
		}else if(source==2){
			List<QuestionBi> list = questionBIService
	                .selectQuetionBiGroupByDay(map);
			JSONArray answerCount=new JSONArray();
			for (QuestionBi bi : list) {
				answerCount.add(bi.getAnswerCount());
				times.add(DateUtils.intToDateString(bi.getCreatedAt(), "yy年M月d日"));
			}
			List<Integer> typeValueList=new ArrayList<Integer>();
			typeValueList.add(1);
			typeValueList.add(2);
			map.put("type", "userType");
			map.put("typeValue", typeValueList);
			List<QuestionTypeBi> typeList = questionBIService
					.selectQuestionTypeBiGroupByDay(map);
			JSONArray expertAnsCount=new JSONArray();
			JSONArray pacesetterAnsCount=new JSONArray();
			for (QuestionTypeBi bi : typeList) {
				if (bi.getTypeValue()==1) {
					pacesetterAnsCount.add(bi.getAnswerCount());					
				}else if (bi.getTypeValue()==2) {
					expertAnsCount.add(bi.getAnswerCount());
				}
			}
	        
	      //内部渠道数据
			List<Integer> typeValueList2=new ArrayList<Integer>();
			typeValueList2.add(1);
			typeValueList2.add(2);
			typeValueList2.add(3);
			typeValueList2.add(4);
			typeValueList2.add(5);
			typeValueList2.add(6);
			typeValueList2.add(998);
			map.put("type", "source");
			map.put("typeValue", typeValueList2);
			List<QuestionTypeBi> typeList1 = questionBIService
					.selectQuestionTypeBiGroupByDay(map);
			JSONArray pcAnswerCount=new JSONArray();
	        JSONArray iosAnswerCountt=new JSONArray();
	        JSONArray androidAnswerCount=new JSONArray(); 
	        JSONArray mAnswerCount=new JSONArray();
	        JSONArray h5AnswerCount=new JSONArray();
	        JSONArray backAnswerCount=new JSONArray(); 
			for (QuestionTypeBi bi : typeList1) {
				if (bi.getTypeValue()==1) {
					pcAnswerCount.add(bi.getAnswerCount());
				}else if (bi.getTypeValue()==2) {
					iosAnswerCountt.add(bi.getAnswerCount());
				}else if (bi.getTypeValue()==3) {
					androidAnswerCount.add(bi.getAnswerCount());
				}else if (bi.getTypeValue()==4) {
					mAnswerCount.add(bi.getAnswerCount());
				}else if (bi.getTypeValue()==5) {
					h5AnswerCount.add(bi.getAnswerCount());
				}else if (bi.getTypeValue()==6) {
					//h5AnswerCount.add(bi.getAnswerCount());
				}else if (bi.getTypeValue()==998) {
					backAnswerCount.add(bi.getAnswerCount());
				}
			}
			object.put("回答总数",answerCount);
	        object.put("标兵",pacesetterAnsCount);
	        object.put("专家",expertAnsCount);
			object.put("pc",pcAnswerCount);
	        object.put("ios",iosAnswerCountt);
	        object.put("android",androidAnswerCount);
	        object.put("m",mAnswerCount);
	        object.put("H5",h5AnswerCount);
	        object.put("后台",backAnswerCount);
			
		}else if (source==3) {
			//百度数据
			List<Integer> typeValueList=new ArrayList<Integer>();
			typeValueList.add(102);
			map.put("type", "source");
			map.put("typeValue", typeValueList);
			List<QuestionTypeBi> typeList = questionBIService
					.selectQuestionTypeBiGroupByDay(map);
			JSONArray questionCount=new JSONArray();
			JSONArray validQuestionCount=new JSONArray();
			JSONArray ansQuestionCount=new JSONArray();
			for (QuestionTypeBi bi : typeList) {
				 times.add(DateUtils.intToDateString(bi.getCreatedAt(), "yy年M月d日"));
				 questionCount.add(bi.getQuestionCount());	
				 validQuestionCount.add(bi.getValidQuestionCount());
				 ansQuestionCount.add(bi.getRemark4());
			}
			object.put("百度问题总数",questionCount);
	        object.put("百度有效问题数",validQuestionCount);
	        object.put("百度解题数",ansQuestionCount);
		}else if (source==5) {
			//平均响应时间
			List<QuestionBi> list = questionBIService
	                .selectQuetionBiGroupByDay(map);
			JSONArray avgTime=new JSONArray();
			for (QuestionBi bi : list) {
				times.add(DateUtils.intToDateString(bi.getCreatedAt(), "yy年M月d日"));
				if (bi.getRemark1()==0||bi.getRemark2()==0) {
					avgTime.add(0);
				}else{
					avgTime.add(bi.getRemark2()/bi.getRemark1()/60); 
				}
			}
			object.put("平均响应时间",avgTime);
		}else if (source==6) {
			//活跃度					
			Map<Integer,String> pacesetterMap=questionBIService.selectActiveUserGroupByDay(startDate ,endDate ,1);			
			Map<Integer,String> expertMap=questionBIService.selectActiveUserGroupByDay(startDate ,endDate ,2);
			JSONArray pacesetterActive=new JSONArray();
			for (Map.Entry<Integer, String> entry : pacesetterMap.entrySet()) {				
				times.add(DateUtils.intToDateString(entry.getKey(), "yy年M月d日"));
				pacesetterActive.add(entry.getValue());
			}
			JSONArray expertActive=new JSONArray();
			for (Map.Entry<Integer, String> entry : expertMap.entrySet()) {
				expertActive.add(entry.getValue());
			}
			object.put("标兵活跃度", pacesetterActive);
			object.put("专家活跃度", expertActive);
		}		
        data.put("values", object);
        data.put("times", times);
        return new QaResult(data);
	}
	
	
    @ModelAttribute("urlObj")
    public JSONObject getUrlObj() {
        return super.getUrlObj();
    }
}
