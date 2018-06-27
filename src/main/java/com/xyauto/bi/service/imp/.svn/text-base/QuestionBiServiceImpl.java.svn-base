package com.xyauto.bi.service.imp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.bi.entity.QuestionBi;
import com.xyauto.bi.entity.QuestionTypeBi;
import com.xyauto.bi.entity.QuestionUserBi;
import com.xyauto.bi.entity.UserExamineBi;
import com.xyauto.bi.entity.vo.CityVo;
import com.xyauto.bi.mapper.QuestionBiMapper;
import com.xyauto.bi.mapper.QuestionTypeBiMapper;
import com.xyauto.bi.mapper.QuestionUserBiMapper;
import com.xyauto.bi.mapper.UserExamineBiMapper;
import com.xyauto.bi.service.IQuestionBIService;
import com.xyauto.bi.service.basic.IQuestionBIBasicService;
import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.Question;
import com.xyauto.qa.entity.Series;
import com.xyauto.qa.entity.User;
import com.xyauto.qa.mapper.CityMapper;
import com.xyauto.qa.service.IAnswerService;
import com.xyauto.qa.service.QuestionService;
import com.xyauto.qa.service.SeriesService;
import com.xyauto.qa.service.UserService;
import com.xyauto.qa.util.DateUtils;

/**
 * 
 * @author zhangbh
 *
 */
@Service
public class QuestionBiServiceImpl implements IQuestionBIService {
	@Autowired
	private IQuestionBIBasicService questionBIBasicService;
	@Log
	protected Logger logger;
	@Autowired
	private QuestionBiMapper questionBiMapper;
	@Autowired
	private QuestionTypeBiMapper typeBiMapper;
	@Autowired
	private QuestionUserBiMapper userBiMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private IAnswerService answerService;
	@Autowired
	private SeriesService seriesService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private UserExamineBiMapper examineBiMapper;

	/**
	 * 设计思路 1、问题总数，精华问题数，带图片问题数，回答总数都借助于小时表,把每个小时的数据查出来(没有则补0)
	 * 因此该数据必定是24条，且按照时间升序排序，方便封装数据
	 * 2、买车、用车数据先按照小时分组在按照类型分组，因此该数据必定是每个时段都有两条，用CreatedAt存储小时值，方便封装数据
	 */
	@Override
	public int insertQuestionBI(Integer beforeDay) {
		// 设置查询时间
		Date selectDate = DateUtils.beforDate(new Date(), beforeDay);
		String startTime, endTime;
		startTime = endTime = DateUtils.dateToStr(selectDate,
				DateUtils.formatDateDay);
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		logger.info("插入数据:" + startTime.toString() + "结束时间：" + endTime);
		// 插入数据前先统计当天是否执行过
		QuestionBi questionbi = questionBiMapper.selectByMap(map);
		if (questionbi != null) {
			logger.info("查询的数据是：" + questionbi.toString());
			logger.info("问答总数当天已经统计过：" + startTime);
			return 1;
		}
		List<Integer> questionCountList = questionBIBasicService
				.selectQuestionCountGroupHours(beforeDay);
		List<Integer> questionGoodCountList = questionBIBasicService
				.selectQuestionCountByGoodGroupHours(beforeDay);
		List<Integer> questionAttachCountList = questionBIBasicService
				.selectQuestionCountByAttachGroupHours(beforeDay);
		List<Integer> answerCountList = questionBIBasicService
				.selectAnswerCountByMapGroupHours(beforeDay);
		// 由于按照时间和分类进行分组需要特殊处理
		List<Question> questionBuyCarCountList = questionBIBasicService
				.selectBuyCarQuestionCountGroupHours(beforeDay);
		List<Question> questionTimeCountList = questionBIBasicService
				.selectQuestionByAnswerGroupHours(beforeDay);
		List<QuestionBi> list = new CopyOnWriteArrayList<QuestionBi>();
		QuestionBi questionBi = null;
		// 设置插入时间
		Date createDate = DateUtils.beforDate(new Date(), beforeDay - 1);
		for (int i = 0; i < questionCountList.size(); i++) {
			questionBi = new QuestionBi();
			questionBi.setQuestionCount(questionCountList.get(i));
			questionBi.setHours((short) i);
			questionBi.setCreatedAt((int) (createDate.getTime() / 1000));
			questionBi.setGoodCount(questionGoodCountList.get(i));
			questionBi.setHasAttachCount(questionAttachCountList.get(i));
			questionBi.setAnswerCount(answerCountList.get(i));
			Question question = questionTimeCountList.get(i);
			questionBi.setRemark1(question.getAnswerCount());
			if (question.getFirstAnsweredAt() != null) {
				questionBi.setRemark2(question.getFirstAnsweredAt());
			}
			list.add(questionBi);
		}
		for (int i = 0; i < questionBuyCarCountList.size(); i++) {
			Question question = questionBuyCarCountList.get(i);
			questionBi = list.get(question.getCreatedAt());
			logger.info(questionBi.toString());
			if (question.getCategoryId() != null) {
				if (question.getCategoryId() == 1) {
					questionBi.setBuyCarCount(question.getAnswerCount());
				}
				if (question.getCategoryId() == 3) {
					questionBi.setSellCarCount(question.getAnswerCount());
				}
			}
		}
		int count = questionBiMapper.insert(list);
		if (count <= 0) {
			logger.error("插入bi统计数据失败");
		}
		return 0;
	}

	/**
	 * 设计思路 1、按照多个类型来查询的同一类的数据，如用户身份、渠道来源查询问题数据，因此把类型按行存储
	 * 2、相同的列有值则存，没有补0，不同的内容各自不用处理
	 */
	@Override
	public int insertQuestionTypeBI(Integer beforeDay) {
		// 插入数据前先统计当天是否执行过
		Date selectDate = DateUtils.beforDate(new Date(), beforeDay);
		String startTime, endTime;
		startTime = endTime = DateUtils.dateToStr(selectDate);
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<QuestionTypeBi> listBI = typeBiMapper.selectByMap(map);
		if (listBI != null && listBI.size() > 0) {
			logger.info("按类型问答总数当天已经统计过：" + startTime);
			return 1;
		}
		List<QuestionTypeBi> list = new CopyOnWriteArrayList<QuestionTypeBi>();
		List<Question> userTypeQueList = questionBIBasicService
				.selectQuestionCountGroupHoursUserType(beforeDay);
		List<Question> userTypeQuespList = questionBIBasicService
				.selectQuestionSpecifyCountGroupHoursUserType(beforeDay);
		List<Answer> userTypeAnsList = questionBIBasicService
				.selectAnswerCountGroupHoursUserType(beforeDay);
		// 按照用户身份统计数据
		Date createDate = DateUtils.beforDate(new Date(), beforeDay - 1);
		int createAt = (int) (createDate.getTime() / 1000);
		QuestionTypeBi questionTypeBi = null;
		for (int i = 0; i < userTypeQueList.size(); i++) {
			questionTypeBi = new QuestionTypeBi();
			questionTypeBi.setCreatedAt(createAt);
			questionTypeBi.setHours((userTypeQuespList.get(i).getCreatedAt())
					.shortValue());
			questionTypeBi.setType("userType");
			questionTypeBi.setTypeValue(userTypeQuespList.get(i)
					.getCategoryId());
			questionTypeBi.setQuestionCount(userTypeQueList.get(i)
					.getAnswerCount());
			questionTypeBi.setQuestionSpecifyCount(userTypeQuespList.get(i)
					.getAnswerCount());
			questionTypeBi.setAnswerCount(userTypeAnsList.get(i)
					.getAgreeCount());
			list.add(questionTypeBi);
		}
		// 按照渠道类型统计数据
		List<Question> sourceTypeQueList = questionBIBasicService
				.selectQuestionSourceGroupHoursUserType(beforeDay, null);
		List<Answer> sourceTypeAnsList = questionBIBasicService
				.selectAnswerSourceGroupHoursUserType(beforeDay);
		// 被删除的问题数
		List<Question> sourceDeleteQueList = questionBIBasicService
				.selectDelQueGroupHoursSource(beforeDay);
		// 回传回复数
		List<Answer> sourceCallAnswerList = questionBIBasicService
				.selectCallAnswerGroupHoursSource(1, beforeDay);
		// 回传成功回复数
		List<Answer> sourceSuccessCallAnswerList = questionBIBasicService
				.selectCallAnswerGroupHoursSource(2, beforeDay);
		// 有回复问题数
		List<Question> answerQueList = questionBIBasicService
				.selectAnswerQueGroupHoursSource(beforeDay);
		// 查询有效问题数
		List<Question> validQuestionList = questionBIBasicService
				.selectQuestionSourceGroupHoursUserType(beforeDay, 1);
		for (int i = 0; i < sourceTypeQueList.size(); i++) {
			questionTypeBi = new QuestionTypeBi();
			questionTypeBi.setCreatedAt(createAt);
			questionTypeBi.setHours((sourceTypeQueList.get(i).getCreatedAt())
					.shortValue());
			questionTypeBi.setType("source");
			questionTypeBi.setTypeValue(sourceTypeQueList.get(i).getSource());
			questionTypeBi.setQuestionCount(sourceTypeQueList.get(i)
					.getAnswerCount());
			questionTypeBi.setAnswerCount(sourceTypeAnsList.get(i)
					.getAgreeCount());
			questionTypeBi.setRemark1(sourceDeleteQueList.get(i)
					.getAnswerCount());
			questionTypeBi.setRemark2(sourceCallAnswerList.get(i)
					.getAgreeCount());
			questionTypeBi.setRemark3(sourceSuccessCallAnswerList.get(i)
					.getAgreeCount());
			questionTypeBi.setRemark4(answerQueList.get(i).getAnswerCount());
			questionTypeBi.setValidQuestionCount(validQuestionList.get(i)
					.getAnswerCount());
			list.add(questionTypeBi);
		}
		int count = typeBiMapper.insert(list);
		if (count <= 0) {
			logger.error("插入bi统计数据失败");
		}
		return 0;
	}

	@Override
	public int insertQuestionUserBI(Integer beforeDay) {
		// 插入数据前先统计当天是否执行过
		Date selectDate = DateUtils.beforDate(new Date(), beforeDay);
		String startTime, endTime;
		startTime = endTime = DateUtils.dateToStr(selectDate);
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		// 插入数据前先统计当天是否执行过
		List<QuestionUserBi> listBI = userBiMapper.selectByMap(map);
		if (listBI != null && listBI.size() > 0) {
			logger.info("按用户问答总数当天已经统计过：" + startTime);
			return 1;
		}
		List<Question> questionSpecifylist = questionBIBasicService
				.selectQuestionSpecifyCountGroupDayUid(beforeDay);
		// 查询每个用户答题总数
		List<Answer> answerQuelist = questionBIBasicService
				.selectAnswerQueCountGroupDayUid(null, null, 30, beforeDay);
		// 查询每个用户回答总数
		List<Answer> answerlist = questionBIBasicService
				.selectAnswersCountGroupDayUid(null, null, null, beforeDay);
		// 查询向每个用户累计提问总数
		List<Question> questionCountlist = questionBIBasicService
				.selectQuestionCountGroupUid(beforeDay);
		// 查询每个用户累计答题总数
		List<Answer> answerQueCountlist = questionBIBasicService
				.selectAnswerQueCountGroupUid(beforeDay);
		// 查询每个用户累计回答总数
		List<Answer> answerCountlist = questionBIBasicService
				.selectAnswersCountGroupUid(beforeDay);
		// 查询每个用户回答字数大于30字的累计回复总数
		List<Answer> answerContentCountlist = questionBIBasicService
				.selectAnswersCountByContentGroupUid(beforeDay);
		// 查询每个用户回答字数大于30字的累计答题总数
		List<Answer> answersQueCententCountlist = questionBIBasicService
				.selectAnswersQueCountByContentGroupUid(beforeDay);
		// 查询每个用户回答8-16的回答数
		List<Answer> answerHour1list = questionBIBasicService
				.selectAnswersCountGroupDayUid(8, 15, null, beforeDay);
		// 查询每个用户回答16-23的回答数
		List<Answer> answerHour2list = questionBIBasicService
				.selectAnswersCountGroupDayUid(16, 23, null, beforeDay);
		// 查询每个用户回答8-16的大于30字的答题数
		List<Answer> answerQueHour1list = questionBIBasicService
				.selectAnswerQueCountGroupDayUid(8, 15, 30, beforeDay);
		// 查询每个用户回答16-23的大于30字的答题数
		List<Answer> answerQueHour2list = questionBIBasicService
				.selectAnswerQueCountGroupDayUid(16, 23, 30, beforeDay);
		// 查询抢0回答题数
		List<Answer> firstAnswerQueCountList = questionBIBasicService
				.selectFirstAnswerQueCountGroupDayUid(beforeDay);
		List<QuestionUserBi> list = new CopyOnWriteArrayList<QuestionUserBi>();
		QuestionUserBi questionUserBi;
		Date createDate = DateUtils.beforDate(new Date(), beforeDay - 1);
		int createAt = (int) (createDate.getTime() / 1000);
		for (int i = 0; i < answerQuelist.size(); i++) {
			questionUserBi = new QuestionUserBi();
			questionUserBi.setCreatedAt(createAt);
			questionUserBi.setUid(questionSpecifylist.get(i).getUid());
			questionUserBi.setQuestionSpeCount(questionSpecifylist.get(i)
					.getAnswerCount());
			questionUserBi.setAnswerQueCount(answerQuelist.get(i)
					.getAgreeCount());
			questionUserBi.setAnswerCount(answerlist.get(i).getAgreeCount());
			questionUserBi.setQuestionSpeSumCount(questionCountlist.get(i)
					.getAnswerCount());
			questionUserBi.setAnswerQueSumCount(answerQueCountlist.get(i)
					.getAgreeCount());
			questionUserBi.setAnswerSumCount(answerCountlist.get(i)
					.getAgreeCount());
			questionUserBi.setAnswerContentSumCount(answerContentCountlist.get(
					i).getAgreeCount());
			questionUserBi
					.setAnswerQueContentSumCount(answersQueCententCountlist
							.get(i).getAgreeCount());
			questionUserBi.setRemark1(answerHour1list.get(i).getAgreeCount());
			questionUserBi.setRemark2(answerHour2list.get(i).getAgreeCount());
			questionUserBi
					.setRemark3(answerQueHour1list.get(i).getAgreeCount());
			questionUserBi
					.setRemark4(answerQueHour2list.get(i).getAgreeCount());
			questionUserBi.setHasAnswerQuestionCount(firstAnswerQueCountList
					.get(i).getAgreeCount());
			list.add(questionUserBi);
		}
		int count = userBiMapper.insert(list);
		if (count <= 0) {
			logger.error("插入bi统计数据失败");
		}
		return 0;
	}

	@Override
	public QuestionBi selectQuestionBi(Map<String, Object> map) {
		QuestionBi list = questionBiMapper.selectByMap(map);
		return list;
	}

	@Override
	public List<QuestionTypeBi> selectQuestionTypeBi(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return typeBiMapper.selectByMap(map);
	}

	@Override
	public List<QuestionUserBi> selectQuestionUserBi(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userBiMapper.selectByMap(map);
	}

	@Override
	public String selectActiveUser(String startTime, String endTime,
			Integer userType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("userType", userType);
		// 查询用户总数
		double userCount = userBiMapper.selectUserCountByUserType(map);
		// 查询回复数大于0的人数
		map.put("MAX_answerCount", 0);
		double answerUserCount = userBiMapper.selectUserCountByUserType(map);
		if (answerUserCount > 0 && userCount > 0) {
			NumberFormat nt = NumberFormat.getPercentInstance();
			nt.setMinimumFractionDigits(2);

			return nt.format(answerUserCount / userCount);
		}
		return "0.00%";
	}

	@Override
	public List<Series> selectCarsByQuesitoncount(String startTime,
			String endTime) {
		return seriesService.selectSeriesByQuestionCount(startTime, endTime);
	}

	@Override
	public List<Question> selectQuestionByAnswercount(String startTime,
			String endTime) {
		List<Question> list = questionService.selectQuestionByAnswercount(
				startTime, endTime);
		for (Question question : list) {
			User user = userService.getUserAndInfo(question.getUid());
			question.setUser(user);
		}
		return list;
	}

	@Override
	public List<Answer> selectAnswerByAgreencount(String startTime,
			String endTime) {
		List<Answer> list = answerService.selectAnswerByAgreencount(startTime,
				endTime);
		for (Answer answer : list) {
			User user = userService.getUserAndInfo(answer.getUid());
			answer.setUser(user);
		}
		return list;
	}

	@Override
	public List<User> selectUserByQueCountOrAnsCount(String startTime,
			String endTime, String type) {
		List<User> list = userService.selectUserByQueCountOrAnsCount(startTime,
				endTime, type);
		for (User user : list) {
			User user1 = userService.getUserAndInfo(user.getUid());
			user.setUserInfo(user1.getUserInfo());
		}
		return list;
	}

	@Override
	public List<CityVo> selectCity(String startTime, String endTime, String type) {
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		if (type.equalsIgnoreCase("answer")) {
			return cityMapper.selectCityByAnswerCount(map);
		} else {
			return cityMapper.selectCityByQuestionCount(map);
		}
	}

	@Override
	public List<QuestionBi> selectQuetionBiGroupByDay(Map<String, Object> map) {

		return questionBiMapper.selectQuetionBiGroupByDay(map);
	}

	@Override
	public List<QuestionTypeBi> selectQuestionTypeBiGroupByDay(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return typeBiMapper.selectQuestionTypeBiGroupByDay(map);
	}

	@Override
	public Map<Integer, String> selectActiveUserGroupByDay(String startDay,
			String endDay, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startDay);
		map.put("endTime", endDay);
		map.put("userType", type);
		// 查询用户总数
		List<QuestionUserBi> userCountList = userBiMapper
				.selectUserCountGroupByDay(map);
		// 查询回复数大于0的人数
		map.put("MAX_answerCount", 0);
		List<QuestionUserBi> answerUserCountList = userBiMapper
				.selectUserCountGroupByDay(map);
		Map<Integer, String> activeMap = new TreeMap<Integer, String>();
		DecimalFormat df = new DecimalFormat("#.00");
		for (int i = 0; i < userCountList.size(); i++) {
			double answerUserCount = answerUserCountList.get(i).getUid();
			double userCount = userCountList.get(i).getUid();
			if (answerUserCount == 0 || userCount == 0) {
				activeMap.put(userCountList.get(i).getCreatedAt(), "0.00");
			} else {
				activeMap.put(userCountList.get(i).getCreatedAt(),
						df.format(answerUserCount * 100 / userCount));
			}
		}
		return activeMap;
	}

	@Override
	public int insertUserExamine(Integer beforeDay) {
		/*List<Answer> list3 = questionBIBasicService
				.selectAnswerQueCountByAnswerQueTime(2, 30, 3, beforeDay);
		List<Answer> list5 = questionBIBasicService
				.selectAnswerQueCountByAnswerQueTime(2, 30, 5, beforeDay);
		List<Answer> list10 = questionBIBasicService
				.selectAnswerQueCountByAnswerQueTime(2, 30, 10, beforeDay);
		List<Answer> list = questionBIBasicService
				.selectAnswerQueCountByAnswerQueTime(2, 50, 0, beforeDay);*/
		//查询专家每天答题数
		List<Answer> answerQueCountList=questionBIBasicService.selectAnswerQueCount(2, 50, beforeDay);
		int createAt = 0;
		if (beforeDay != null) {
			Date createDate = DateUtils.beforDate(new Date(), beforeDay - 1);
			createAt = (int) (createDate.getTime() / 1000);
		} else {
			createAt = (int) (new Date().getTime() / 1000);
		}
		List<UserExamineBi> userExamineList = new ArrayList<UserExamineBi>();
		/**
		 * 屏蔽闪电回答的提数内容
		 * for (Answer answer : list3) {
			UserExamineBi examineBi = new UserExamineBi();
			examineBi.setUid(answer.getUid());
			if (answer.getAgreeCount() != null) {
				examineBi.setResultCount(answer.getAgreeCount());
			} else {
				examineBi.setResultCount(0);
			}
			examineBi.setType((short) 1);
			examineBi.setCreatedAt(createAt);
			userExamineList.add(examineBi);
		}
		for (Answer answer : list5) {
			UserExamineBi examineBi = new UserExamineBi();
			examineBi.setUid(answer.getUid());
			if (answer.getAgreeCount() != null) {
				examineBi.setResultCount(answer.getAgreeCount());
			} else {
				examineBi.setResultCount(0);
			}
			examineBi.setType((short) 2);
			examineBi.setCreatedAt(createAt);
			userExamineList.add(examineBi);
		}
		for (Answer answer : list10) {
			UserExamineBi examineBi = new UserExamineBi();
			examineBi.setUid(answer.getUid());
			if (answer.getAgreeCount() != null) {
				examineBi.setResultCount(answer.getAgreeCount());
			} else {
				examineBi.setResultCount(0);
			}
			examineBi.setType((short) 3);
			examineBi.setCreatedAt(createAt);
			userExamineList.add(examineBi);
		}
		for (Answer answer : list) {
			UserExamineBi examineBi = new UserExamineBi();
			examineBi.setUid(answer.getUid());
			if (answer.getAgreeCount() != null) {
				examineBi.setResultCount(answer.getAgreeCount());
			} else {
				examineBi.setResultCount(0);
			}
			examineBi.setType((short) 4);
			examineBi.setCreatedAt(createAt);
			userExamineList.add(examineBi);
		}
		*/
		for (Answer answer : answerQueCountList) {
			UserExamineBi examineBi = new UserExamineBi();
			examineBi.setUid(answer.getUid());
			if (answer.getAgreeCount() != null) {
				examineBi.setResultCount(answer.getAgreeCount());
			} else {
				examineBi.setResultCount(0);
			}
			examineBi.setType((short) 5);
			examineBi.setCreatedAt(createAt);
			userExamineList.add(examineBi);
		}
		// 插入数据前先统计当天是否执行过
		Date selectDate=new Date(); 
		if (beforeDay!=null) {
			selectDate = DateUtils.beforDate(new Date(), beforeDay-1);
		}		
		String startTime, endTime;
		startTime = endTime = DateUtils.dateToStr(selectDate);
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("type", 4);
		map.put("userType", 2);
		// 插入数据前先统计当天是否执行过
		List<UserExamineBi> listBI = examineBiMapper.select(map);
		if (listBI != null && listBI.size() > 0) {
			logger.info("用户每月考核数据已经统计过" + startTime);
			return 1;
		}
		examineBiMapper.insertList(userExamineList);
		return 0;
	}

	@Override
	public List<UserExamineBi> selectUserExamine(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return examineBiMapper.select(map);
	}

	@Override
	public List<Integer> selectCarAdviser(String startTime, String endTime) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return questionBiMapper.selectCarAdviser(map);
	}

	@Override
	public int deleteQuestionBI(Integer beforeDay) {
		// TODO Auto-generated method stub
		return questionBiMapper.delete(beforeDay);
	}

	@Override
	public int deleteQuestionTypeBI(Integer beforeDay) {
		// TODO Auto-generated method stub
		return typeBiMapper.delete(beforeDay);
	}

	@Override
	public int deleteQuestionUserBI(Integer beforeDay) {
		// TODO Auto-generated method stub
		return userBiMapper.delete(beforeDay);
	}

	@Override
	public int deleteUserExamine(Integer beforeDay) {
		// TODO Auto-generated method stub
		return examineBiMapper.delete(beforeDay);
	}

}
