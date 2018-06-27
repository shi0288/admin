package com.xyauto.bi.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xyauto.bi.entity.QuestionBi;
import com.xyauto.bi.entity.QuestionTypeBi;
import com.xyauto.bi.entity.QuestionUserBi;
import com.xyauto.bi.entity.UserExamineBi;
import com.xyauto.bi.entity.vo.CityVo;
import com.xyauto.bi.service.IQuestionBIService;
import com.xyauto.bi.service.IQuestionBiExcleService;
import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.Question;
import com.xyauto.qa.entity.Series;
import com.xyauto.qa.entity.User;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.utils.excel.ExcelUtils;
import com.xyauto.utils.excel.ExcelVo;

@Service
public class QuestionBiExcleServiceImpl implements IQuestionBiExcleService {
	@Autowired
	private IQuestionBIService questionBIService;

	@Override
	public void exportQuestionBi(HttpServletResponse response,
			Map<String, Object> map) {
		QuestionBi questionBi = questionBIService.selectQuestionBi(map);
		if (questionBi == null) {
			return;
		}
		String excelName = "问答总数.xls";
		String[] headers = new String[] { "时间", "问题总数", "精华问题数", "买车问题数",
				"用车问题数", "带图问题数", "回答总数", "平均响应时间", "有回复问题数" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;

		objs = new Object[headers.length];
		objs[0] = DateUtils.intToDateString(questionBi.getCreatedAt(), "yyyyMMdd");
		objs[1] = questionBi.getQuestionCount();
		objs[2] = questionBi.getGoodCount();
		objs[3] = questionBi.getBuyCarCount();
		objs[4] = questionBi.getSellCarCount();
		objs[5] = questionBi.getHasAttachCount();
		objs[6] = questionBi.getAnswerCount();
		if (questionBi.getRemark1() == 0) {
			objs[7] = 0;
		} else {
			objs[7] = questionBi.getRemark2() / questionBi.getRemark1();
		}
		objs[8] = questionBi.getRemark1();
		dataList.add(objs);

		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle("问答总数");
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);
	}

	@Override
	public void exportQuestionTypeBi(HttpServletResponse response,
			Map<String, Object> map) {
		List<QuestionTypeBi> list = questionBIService.selectQuestionTypeBi(map);
		if (list == null || list.size() <= 0) {
			return;
		}
		String excelName = "按类型统计问答数据.xls";
		String[] headers = new String[] { "时间", "类型名称", "具体类型", "问题总数", "回答总数",
				"被提问数" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (QuestionTypeBi bi : list) {
			objs = new Object[headers.length];
			objs[0] = DateUtils.intToDateString(bi.getCreatedAt(), "yyyyMMdd");
			if (bi.getType().equals("source")) {
				objs[1] = "渠道";
				if (bi.getTypeValue() == 101) {
					objs[2] = "惠商机";
				} else if (bi.getTypeValue() == 102) {
					objs[2] = "百度";
				} else if (bi.getTypeValue() == 103) {
					objs[2] = "360";
				} else {
					objs[2] = bi.getTypeValue();
				}
			} else if (bi.getType().equals("userType")) {
				objs[1] = "用户身份";
				if (bi.getTypeValue() == 1) {
					objs[2] = "标兵";
				} else if (bi.getTypeValue() == 2) {
					objs[2] = "专家";
				} else {
					objs[2] = bi.getTypeValue();
				}
			} else {
				objs[1] = bi.getType();
				objs[2] = bi.getTypeValue();
			}
			objs[3] = bi.getQuestionCount();
			objs[4] = bi.getAnswerCount();
			objs[5] = bi.getQuestionSpecifyCount();
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle("类型统计问答数据");
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);

	}

	@Override
	public void exportQuestionUserBi(HttpServletResponse response,
			Map<String, Object> map) {
		List<QuestionUserBi> list = questionBIService.selectQuestionUserBi(map);
		if (list == null || list.size() <= 0) {
			return;
		}
		String excelName = "按用户统计问答数据.xls";
		String[] headers = new String[] { "时间", "用户id", "被提问数", "答题总数", "回答总数",
				"累计被提问数", "累计答题数", "累计回复数", "累计大于30字的回复数", "累计大于30字的答题数",
				"8-16回复数", "16-23回复数", "8-16大于30字的答题数", "16-23大于30字的答题数","抢0回答题数" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (QuestionUserBi bi : list) {
			objs = new Object[headers.length];
			objs[0] = DateUtils.intToDateString(bi.getCreatedAt(), "yyyyMMdd");
			objs[1] = bi.getUid();
			objs[2] = bi.getQuestionSpeCount();
			objs[3] = bi.getAnswerQueCount();
			objs[4] = bi.getAnswerCount();
			objs[5] = bi.getQuestionSpeSumCount();
			objs[6] = bi.getAnswerQueSumCount();
			objs[7] = bi.getAnswerSumCount();
			objs[8] = bi.getAnswerContentSumCount();
			objs[9] = bi.getAnswerQueContentSumCount();
			objs[10] = bi.getRemark1();
			objs[11] = bi.getRemark2();
			objs[12] = bi.getRemark3();
			objs[13] = bi.getRemark4();
			objs[14] = bi.getHasAnswerQuestionCount();
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle("用户统计问答数据");
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);
	}

	@Override
	public void exportCarsByQuesitoncount(HttpServletResponse response,String startTime, String endTime) {
		List<Series> list=questionBIService.selectCarsByQuesitoncount(startTime, endTime);
		if (list == null || list.size() <= 0) {
			return;
		}
		String excelName="关联问题最多的前50车型.xls";
		String[] headers = new String[] { "车型id", "品牌名称", "问题数量" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (Series bi : list) {
			objs = new Object[headers.length];
			objs[0] = bi.getSeriesId();
			objs[1] = bi.getName();
			objs[2] = bi.getQuestionCount();
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle("关联问题最多的前50车型");
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);
	}

	@Override
	public void exportQuestionByAnswercount(HttpServletResponse response,String startTime, String endTime) {
		List<Question> list=questionBIService.selectQuestionByAnswercount(startTime, endTime);
		if (list == null || list.size() <= 0) {
			return;
		}
		String excelName="回复数最多的前20问题.xls";
		String[] headers = new String[] {"回答数量","问题ID","提问者昵称","提问者ID","问题内容"};
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (Question bi : list) {
			objs = new Object[headers.length];
			objs[0] = bi.getAnswerCount();
			objs[1] = bi.getQuestionId();
			JSONObject data=bi.getUser().getUserInfo().getJSONObject("data");			
			String name=null;
			if (data!=null) {
				 name=data.getString("nick_name");									
			}
			if(StringUtils.isNotBlank(name)){
				objs[2] =name;
			}else{
				objs[2] ="未知昵称";
			}
			objs[3] = bi.getUid();
			objs[4] = bi.getContent();
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle("回复数最多的前20问题");
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);	
	}

	@Override
	public void exportAnswerByAgreencount(HttpServletResponse response,String startTime, String endTime) {
		List<Answer> list=questionBIService.selectAnswerByAgreencount(startTime, endTime);
		if (list == null || list.size() <= 0) {
			return;
		}
		String excelName="点赞数最多的前20个回复.xls";
		String[] headers = new String[] {"点赞数","回复ID","回复人ID","回复人昵称","问题ID"};
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (Answer bi : list) {
			objs = new Object[headers.length];
			objs[0] = bi.getAgreeCount();
			objs[1] = bi.getAnswerId();			
			objs[2] = bi.getUid();
			JSONObject data=bi.getUser().getUserInfo().getJSONObject("data");			
			String name=null;
			if (data!=null) {
				 name=data.getString("nick_name");									
			}
			if(StringUtils.isNotBlank(name)){
				objs[3] =name;
			}else{
				objs[3] ="未知昵称";
			}
			objs[4] = bi.getQuestionId();
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle("点赞数最多的前20个回复");
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);	
		
	}

	@Override
	public void exportUserByQueCountOrAnsCount(HttpServletResponse response,String startTime,
			String endTime, String type) {
		List<User> list=questionBIService.selectUserByQueCountOrAnsCount(startTime, endTime,type);
		if (list == null || list.size() <= 0) {
			return;
		}
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		String title=null;
		String excelName=null;
		if (type.equalsIgnoreCase("question")) {
			 title="提问最多的前50用户";
			 excelName="提问最多的前50用户.xls";
		}else{
			 title="回答最多的前50用户";
			 excelName="回答最多的前50用户.xls";
		}
		String[] headers = new String[] {"数量","用户ID","用户昵称"};
		for (User bi : list) {
			objs = new Object[headers.length];
			if (type.equalsIgnoreCase("question")) {
				objs[0] = bi.getQuestion_count();
			}else{
				objs[0] = bi.getAnswer_count();
			}			
			objs[1] = bi.getUid();
			JSONObject data=bi.getUserInfo().getJSONObject("data");			
			String name=null;
			if (data!=null) {
				 name=data.getString("nick_name");									
			}
			if(StringUtils.isNotBlank(name)){
				objs[2] =name;
			}else{
				objs[2] ="未知昵称";
			}
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle(title);
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);			
	}

	@Override
	public void exportCity(HttpServletResponse response,String startTime, String endTime, String type) {
		List<CityVo> list=questionBIService.selectCity(startTime, endTime,type);
		if (list == null || list.size() <= 0) {
			return;
		}
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		String title=null;
		String excelName=null;
		if (type.equalsIgnoreCase("question")) {
			 title="关联问题最多的前50城市";
			 excelName="关联问题最多的前50城市.xls";
		}else{
			 title="关联回复最多的前50城市";
			 excelName="关联回复最多的前50城市.xls";
		}
		String[] headers = new String[] {"城市","省份","关联次数"};
		for (CityVo bi : list) {
			objs = new Object[headers.length];
			objs[0] = bi.getCityName();
			objs[1] = bi.getProviceName();
			if (type.equalsIgnoreCase("question")) {
				objs[2] = bi.getQuestionCount();
			}else{
				objs[2] = bi.getAnswerCount();
			}			
			dataList.add(objs);
		}
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle(title);
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);			
	}

	@Override
	public void exportUserExpert(HttpServletResponse response,
			String startTime, String endTime) {
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);		
		map.put("userType", 2);
		
		/*
		 * map.put("type", 4);	
		//大于30字的答题数
		List<UserExamineBi> list = questionBIService.selectUserExamine(map);
		if (list == null || list.size() <= 0) {
			return;
		}
		//大于30字的且回答时间小于3分钟的答题数
		map.put("type", 1);	
		List<UserExamineBi> list3 = questionBIService.selectUserExamine(map);
		//大于30字的且回答时间小于5分钟的答题数
		map.put("type", 2);	
		List<UserExamineBi> list5 = questionBIService.selectUserExamine(map);
		//大于30字的且回答时间小于10分钟的答题数
		map.put("type", 3);	
		List<UserExamineBi> list10 = questionBIService.selectUserExamine(map);	
		*/
		
		map.put("type", 5);	
		List<UserExamineBi> answerQueCountList = questionBIService.selectUserExamine(map);
		
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		String title="专家考核数据";
		String excelName="专家考核数据.xls";
		String[] headers = new String[] {"时间","用户id","每天答题数"};
		for (int i = 0; i < answerQueCountList.size(); i++) {
			objs = new Object[headers.length];
			objs[0] = DateUtils.intToDateString(answerQueCountList.get(i).getCreatedAt()-24*3600, "yyyyMMdd");
			objs[1] = answerQueCountList.get(i).getUid();
			objs[2] = answerQueCountList.get(i).getResultCount();
			dataList.add(objs);
		}		
		ExcelVo excelVo = new ExcelVo();
		excelVo.setTitle(title);
		excelVo.setRowName(headers);
		excelVo.setDataList(dataList);
		List<ExcelVo> excelList = new ArrayList<ExcelVo>();
		excelList.add(excelVo);
		ExcelUtils.exportExcel(excelName, excelList, response);	
	}

}
