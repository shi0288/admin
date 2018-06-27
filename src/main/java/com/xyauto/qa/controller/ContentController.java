package com.xyauto.qa.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.Constants;
import com.xyauto.qa.cons.*;
import com.xyauto.qa.core.annotation.QaLog;
import com.xyauto.qa.entity.*;
import com.xyauto.qa.entity.vo.SpiderQuestionVo;
import com.xyauto.qa.mapper.AttachMapper;
import com.xyauto.qa.mapper.CityMapper;
import com.xyauto.qa.service.*;
import com.xyauto.qa.service.cloud.QaServerService;
import com.xyauto.qa.service.cloud.QaUserService;
import com.xyauto.qa.service.cloud.RUserService;
import com.xyauto.qa.service.cloud.impl.PushService;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.qa.util.QaResult;
import com.xyauto.qa.util.QuestionUtil;
import com.xyauto.utils.HttpUtil;
import com.xyauto.utils.cache.RedisKey;
import com.xyauto.utils.cache.RedisKeyUtil;
import com.xyauto.utils.cache.service.ICacheService;
import com.xyauto.utils.enumutil.EnumUtils.MessageType;
import com.xyauto.utils.enumutil.FrozenReasonEnum;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by shiqm on 2017/3/23.
 */
@Controller
@RequestMapping("user/question")
public class ContentController extends AjaxBaseController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserService userService;

	@Autowired
	private BlocksService blocksService;

	@Autowired
	private RUserService rUserService;

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private QaUserService qaUserService;
	@Autowired
	private IAnswerService answerService;
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private PushService pushService;
	@Autowired
	private IMessageService messageService;
	@Autowired
    private Constants constants;
	@Autowired
	private ISpiderQuestionService spiderQuestionService;
	
	@Autowired
	private SeriesService seriesService ;
	@Autowired
	private QaServerService qaServerService;


	@RequestMapping("contentList")
	public void contentList(ModelMap modelMap, String startDate,
			String endDate, Integer userType, Question question,
			String keyWord, String nickName, Integer beforDay, Brand brand,
			Series series) {

		if (StringUtils.isNotBlank(nickName)) {
			modelMap.put("nickName", nickName);
			String userId = rUserService.getUserIdByNickName(nickName);
			if (StringUtils.isNotBlank(userId) && !"0".equals(userId)) {
				question.setUid(Long.parseLong(userId));
				nickName = null;
			}
		}
		if (StringUtils.isNotBlank(keyWord)) {
			modelMap.put("keyWord", keyWord);
		}
		if (beforDay != null) {
			startDate = DateUtils.dateToStr(DateUtils.beforDate(new Date(), 7),
					DateUtils.formatTime);
			endDate = DateUtils.dateToStr(new Date(), DateUtils.formatTime);
		}
		if (StringUtils.isNotBlank(startDate)) {
			Date date = DateUtils.strToDate(startDate);
			if (date != null) {
				question.setCreatedAt((int) (date.getTime() / 1000));
				modelMap.put("startDate", startDate);
			}
		}
		if (StringUtils.isNotBlank(endDate)) {
			Date date = DateUtils.strToDate(endDate);
			if (date != null) {
				question.setUpdatedAt((int) (date.getTime() / 1000));
				modelMap.put("endDate", endDate);
			}
		}
		modelMap.put("userType", userType);
		PageInfo pageInfo = questionService.getQuestionListByMapParam(question,
				keyWord, nickName, userType, brand, series,
				CommonCons.Quesiotn_Flag.NORMAL_QUESTION);
		modelMap.put("pager", pageInfo);
		modelMap.put("question", question);
		Category category = new Category();
		category.setParent(0);
		// 获取问题分类信息
		modelMap.put("categoryList", categoryService.get(category));
		// 获取品牌信息
		modelMap.put("brandList", brandService.selectBrandOrderByName());
		// 获取市信息
		City city = new City();
		city.setParent(0);
		modelMap.put("provinceList", cityMapper.select(city));

		// add by shim 封装所有的来源信息
		if (question.getSource() != null && question.getSource() != 0) {
			modelMap.put("source", question.getSource());
		}
		modelMap.put("sourceMap", ConvertCons.ResourceCover.getMap());

		if (pageInfo != null && pageInfo.getList() != null
				&& pageInfo.getList().size() > 0) {
			Question question2 = (Question) pageInfo.getList().get(0);
			if (question2.getAttaches() != null
					&& question2.getAttaches().size() > 0) {
				modelMap.put("questionType", question2.getAttaches().get(0)
						.getFileType());
			}
		}

	}

	@RequestMapping("rubbishList")
	public void rubbishList(ModelMap modelMap, Integer answerType,
			String startDate, String endDate, Integer userType,
			Question question, Answer answer, String keyWord, String nickName,
			Integer beforDay) {

		if (StringUtils.isNotBlank(nickName)) {
			String userId = rUserService.getUserIdByNickName(nickName);
			modelMap.put("nickName", nickName);
			if (StringUtils.isNotBlank(userId) && !"0".equals(userId)) {
				question.setUid(Long.parseLong(userId));
				answer.setUid(Long.parseLong(userId));
				nickName = null;
			}
		}
		if (StringUtils.isNotBlank(keyWord)) {
			modelMap.put("keyWord", keyWord);
		}
		if (beforDay != null) {
			startDate = DateUtils.dateToStr(DateUtils.beforDate(new Date(), 7),
					DateUtils.formatTime);
			endDate = DateUtils.dateToStr(new Date(), DateUtils.formatTime);
		}
		if (StringUtils.isNotBlank(startDate)) {
			Date date = DateUtils.strToDate(startDate);
			if (date != null) {
				question.setCreatedAt((int) (date.getTime() / 1000));
				answer.setCreatedAt((int) (date.getTime() / 1000));
				modelMap.put("startDate", startDate);
			}
		}
		if (StringUtils.isNotBlank(endDate)) {
			Date date = DateUtils.strToDate(endDate);
			if (date != null) {
				question.setUpdatedAt((int) (date.getTime() / 1000));
				answer.setUpdatedAt((int) (date.getTime() / 1000));
				modelMap.put("endDate", endDate);
			}
		}
		PageInfo pageInfo;
		if (answerType != null && answerType == 2) {
			pageInfo = answerService
					.getAnswerList(answer, userType, null, keyWord,
							CommonCons.Quesiotn_Flag.DELETE_QUESTION, nickName);
			modelMap.put("answeQuestion", answer);
			modelMap.put("answerType", 2);
			modelMap.put("question", null);
		} else {
			pageInfo = questionService.getQuestionListByMapParam(question,
					keyWord, nickName, userType, null, null,
					CommonCons.Quesiotn_Flag.DELETE_QUESTION);
			modelMap.put("answeQuestion", question);
			modelMap.put("answerType", 1);
			Category category = new Category();
			category.setParent(0);
			// 获取问题分类信息
			modelMap.put("categoryList", categoryService.get(category));
		}
		modelMap.put("pager", pageInfo);
		modelMap.put("userType", userType);
		// 获取市信息
		City city = new City();
		city.setParent(0);
		modelMap.put("provinceList", cityMapper.select(city));
		modelMap.put("sourceMap", ConvertCons.ResourceCover.getMap());
		modelMap.put("userTypeMap", ConvertCons.UserTypeCover.getMap());
	}

	@RequestMapping(value="getOperateUser", method = RequestMethod.POST )
	@ResponseBody
	public QaResult getOperateUser(int userType){
		JSONArray jsonArray = blocksService
				.getBlockByName(CommonCons.Blocks_Flag.USER_OPERATE_BUSINESS);
		JSONArray businessUserList = null;
		if (jsonArray != null) {
			if (userType==1) {
				List list=userService.getUserIdByType(jsonArray.toArray(), 0);								
				jsonArray=new JSONArray(list);
			}
			String temp = rUserService.getUserList(StringUtils.join(jsonArray,","));
						 
			businessUserList = JSON.parseArray(temp);
			return new QaResult(businessUserList);
		}
		return new QaResult(ErrorCode.OVER,"没有用户");
	}
	
	@RequestMapping("questionAdd")
	public void questionAdd(ModelMap modelMap) {
		JSONArray jsonArray = blocksService
				.getBlockByName(CommonCons.Blocks_Flag.USER_OPERATE_BUSINESS);
		JSONArray businessUserList = null;
		if (jsonArray != null) {
			String temp = rUserService.getUserList(StringUtils.join(jsonArray,
					","));
			businessUserList = JSON.parseArray(temp);
		}
		modelMap.put("businessUserList", businessUserList);
		City city = new City();
		city.setParent(0);
		modelMap.put("provinceList", cityMapper.select(city));
		JSONObject categoryStr = JSON.parseObject(qaUserService.categoryList());
		JSONArray categoryArray = categoryStr.getJSONArray("data");
		modelMap.put("categoryList", categoryArray);
		modelMap.put("brandList", brandService.selectBrandOrderByName());
		modelMap.put("questionTypeMap", ConvertCons.QuestionType.getMap());
	}

	@QaLog(action = LogAction.QUESTION_ADD_QUESTION, cmd = "createQuestion")
	@RequestMapping(value = "createQuestion", method = RequestMethod.POST)
	@ResponseBody
	public QaResult createQuestion(String uid, String category_id,
			String content, Integer questionType, String[] seriesIds,
			String special_id, String city_id, String attachs, Integer is_good,
			String releaseTime, Integer activeId, String applyStartTime,
			String applyEndTime, String[] contentId, Integer partakeTimeId,
			String[] partakeTime,String title,int contentType,String container,
			String subjet_content,Integer subject_type) {

		if (StringUtils.isEmpty(uid)) {
			return new QaResult(ErrorCode.OVER, "发布用户不能为空");
		}
		
		if (StringUtils.isEmpty(category_id)) {
			return new QaResult(ErrorCode.OVER, "问题类型不能为空");
		}
		String attach="";
		String cover="";  
		if (contentType==1) {
			content=container.substring(0,1);
			if (",".equals(content)) {
				content=container.substring(1);
			}else{
				content=container;
			}
			if (StringUtils.isNotBlank(attachs)) {
				if (attachs.contains(",")) {
					return new QaResult(ErrorCode.OVER, "封面不能有多张图片");
				}
				cover=attachs;
			}
			if (StringUtils.isBlank(title)) {
				return new QaResult(ErrorCode.OVER, "请输入标题");
			}
		}else{
			 attach=this.transAttachList(attachs);
		}
		if (StringUtils.isEmpty(content)) {
			return new QaResult(ErrorCode.OVER, "内容不能为空");
		}
		Integer type = null;
		String distribute = null;
		String activity = null;
		if (questionType == 5) {
			type = 1;
		} else if (questionType == 4) {
			if (activeId == null) {
				return new QaResult(ErrorCode.OVER, "请选择活动类型");
			} else {
				if ((activeId==1||activeId==2||activeId == 4)&&(StringUtils.isBlank(applyStartTime)
						|| StringUtils.isBlank(applyEndTime))) {
					return new QaResult(ErrorCode.OVER, "活动报名时间不能为空");
				}
				if (activeId == 2 && partakeTimeId != null
						&& partakeTimeId == 4) {
					if (StringUtils.isBlank(partakeTime[0])) {
						return new QaResult(ErrorCode.OVER, "活动参加时间不能为空");
					}
				}
				if (activeId == 4) {
					if (StringUtils.isBlank(subjet_content)||subject_type==null) {
						return new QaResult(ErrorCode.OVER, "话题活动，关联话题和话题类型不能为空");
					}
				}
				String sign = "";
				String signInfo = "";
				JSONObject activeobj = new JSONObject();
				if (activeId == 2) {
					sign = "indirect";
					// 对照必填项目
					JSONObject contentobj = new JSONObject();
					for (String activeContent : contentId) {
						if (StringUtils.isNotBlank(activeContent)) {
							contentobj.put(activeContent, "1");
						}
					}
					// 对照时间
					JSONArray timeobj = new JSONArray();
					if (partakeTimeId != null && partakeTimeId == 4) {
						for (String time : partakeTime) {
							if (StringUtils.isNotBlank(time)) {
								time = time.replace(" ", "");
								timeobj.add(time);
							}
						}
						contentobj.put("time", timeobj);
					}
					signInfo = contentobj.toString();
				} else if (activeId == 1) {
					sign = "direct";
				} else if (activeId == 3) {
					sign = "none";
				} else if (activeId == 4) {
					sign = "subject";
					activeobj.put("subject", subjet_content);
					if (subject_type==1) {
						activeobj.put("publish", "distribute");
					}else{
						activeobj.put("publish", "ask");
					}					
				} else {
					return new QaResult(ErrorCode.OVER, "活动类型出错");
				}
				Long startTime =0l;;
				Long endTime =0l;
				if (StringUtils.isNotBlank(applyStartTime)) {
					startTime = DateUtils.strToDate(applyStartTime).getTime() / 1000;
					endTime = DateUtils.strToDate(applyEndTime).getTime() / 1000;					
				}		
				activeobj.put("start_time", startTime.toString());
				activeobj.put("end_time", endTime.toString());
				activeobj.put("limit", "0");
				activeobj.put("sign", sign);
				activeobj.put("sign_info", signInfo);
				JSONArray array = new JSONArray();
				array.add(activeobj);
				JSONObject obj = new JSONObject();
				obj.put("type", "activity");
				obj.put("data", array);
				activity = obj.toString();
			}
		}
		String cars = null;
		if (seriesIds != null && seriesIds.length > 0) {
			cars = StringUtils.join(seriesIds, ",");
		}
		Date date = new Date();
		
		String res = qaUserService.createQuestionNoPic(category_id, content,
				uid, cars, null, city_id, attach, is_good, activity, type,cover,
				contentType,title,distribute);
		JSONObject jsonObject = JSON.parseObject(res);

		if (jsonObject.getIntValue("code") == 10000) {
			if (StringUtils.isNotBlank(releaseTime)) {
				Date releaseDate = DateUtils.strToDate(releaseTime);
				if (releaseDate.after(date)) {
					// 创建完成后立刻更新状态---待发布
					JSONObject dataObject = jsonObject.getJSONObject("data");
					String questionId = dataObject.getString("question_id");
					Question question = questionService.get(Long
							.parseLong(questionId));					
					/**
					 * 十一期间临时调整
					 */
//					qaUserService.deleteQuestion(question.getQuestionId(), 0l);
					question.setStatus(CommonCons.Quesiotn_Flag.RELEASE_QUESTION);// 待发布
//					question.setDeletedAt((int)(new Date().getTime()/1000));
					questionService.update(question);
					qaUserService.updateQuestion(question.getQuestionId());
					// redis中设置定时发布的问题
					String key = RedisKeyUtil.getKey(
							RedisKeyUtil.Model.question, "release_question");
					Map<String, Object> map = cacheService.getMap(key);
					map.put(questionId, releaseDate);
					cacheService.set(key, map);
				}
			}
			return new QaResult();
		} else {
			logger.error("创建问题失败，参数是{}：" + uid + ";" + content);
			logger.error("返回结果是：" + res);
			return new QaResult(ErrorCode.OVER, jsonObject.getString("msg"));
		}
	}

	// 封禁用户
	@QaLog(action = LogAction.FROZEN_USER, cmd = "frozenUser")
	@RequestMapping(value = "frozenUserAndExpert", method = RequestMethod.POST)
	@ResponseBody
	public QaResult frozenUserAndExpert(Long uid, Boolean is, Integer time,
			boolean delQue, boolean delAns, Integer reasonCode, String content) {
		Map<Integer, String> reasonMap = FrozenReasonEnum.reasonMap;
		if (reasonCode != FrozenReasonEnum.reason5.getCode()) {
			content = reasonMap.get(reasonCode);
		}

		if (is != null && !is) {
			User user = new User();
			user.setUid(uid);
			userService.frozenUserAndExpert(user, is);
		} else {
			userService.frozenUserAndExpert(uid, time, delQue, delAns, content);
		}
		return new QaResult();
	}

	@QaLog(action = LogAction.QUESTION_SET_GOOD, cmd = "setGood")
	@RequestMapping(value = "setGood", method = RequestMethod.POST)
	@ResponseBody
	public QaResult setGood(Question question, Boolean is) {
		if (question.getGoodSort() == null) {
			question.setGoodSort(0);
		}
		questionService.updateGood(question, is);
		qaUserService.updateQuestion(question.getQuestionId());
		return new QaResult();
	}

	@QaLog(action = LogAction.QUESTION_SET_TOP, cmd = "setTop")
	@RequestMapping(value = "setTop", method = RequestMethod.POST)
	@ResponseBody
	public QaResult setTop(Question question, Boolean is) {
		if (question.getTopSort() == null) {
			question.setTopSort(0);
		}
		questionService.updateTop(question, is);
		qaUserService.updateQuestion(question.getQuestionId());
		return new QaResult();
	}

	@QaLog(action = LogAction.QUESTION_DELETE_QUESTION, cmd = "deleteQuestion")
	@RequestMapping(value = "deleteQuestion", method = RequestMethod.POST)
	@ResponseBody
	public QaResult deleteQuestion(Question question) {
		question=questionService.get(question.getQuestionId());
		User user=userService.get(question.getUid());
		String delInfo = qaUserService.deleteQuestion(question.getQuestionId(),
				0L);
		JSONObject jsonObject = JSON.parseObject(delInfo);
		int code=jsonObject.getInteger("code");
		if (code==10000&&user.getIsOfficial()==1) {
			//删除成功，发送消息 
			JSONObject dataInfo=new JSONObject();
			dataInfo.put("entityid", question.getQuestionId());
			JSONArray data=new JSONArray();
			data.add(dataInfo);
			JSONObject info=new JSONObject();
			info.put("appid", 1);
			info.put("entitytype", 4);
			info.put("opertype", 2);
			info.put("datatype", -1);			
			info.put("data", data);			
			Long time=System.currentTimeMillis();
			String url="http://mq.manager.xingyuanauto.com/mq/send.do?businessId="+time+"&routingKey=news.news_zuixin.del_ask";
			String result=HttpUtil.doPostStr(url, info);
			logger.info("删除问题发送消息，问题id:{},结果是:{}", question.getQuestionId(),result);
		} 
		return new QaResult(jsonObject.getInteger("sub_code").toString(),
				jsonObject.getString("msg"));
	}

	@QaLog(action = LogAction.QUESTION_RECOVERY_QUESTION, cmd = "recoveryQuestion")
	@RequestMapping(value = "recoveryQuestion", method = RequestMethod.POST)
	@ResponseBody
	public QaResult recoveryQuestion(Question question) {
		questionService.recoveryQuestionById(question.getQuestionId());
		qaUserService.updateQuestion(question.getQuestionId());
		return new QaResult();
	}

	@RequestMapping("toUpdateQuestion")
	public void toUpdateQuestion(Long questionId, ModelMap modelMap) {
		Question question = questionService.getQuestionDesc(questionId);
		modelMap.put("question", question);
		JSONArray jsonArray = blocksService
				.getBlockByName(CommonCons.Blocks_Flag.USER_OPERATE_BUSINESS);
		JSONArray businessUserList = null;
		if (jsonArray != null) {
			String temp = rUserService.getUserList(StringUtils.join(jsonArray,
					","));
			businessUserList = JSON.parseArray(temp);
		}
		modelMap.put("businessUserList", businessUserList);
		if (question.getAttaches() != null) {
			List temp = question.getAttaches();
			List<String> target = new ArrayList<String>();
			for (int i = 0; i < temp.size(); i++) {
				Attach attach = (Attach) temp.get(i);
				if (attach.getFileType() == 0) {
					target.add(attach.getFile());
				}
			}
			modelMap.put("attachs", StringUtils.join(target, ","));
		}
		City city = new City();
		city.setParent(0);
		modelMap.put("provinceList", cityMapper.select(city));
		if (question.getCity() != null) {
			city.setParent(question.getCity().getParent());
			modelMap.put("curCitys", cityMapper.select(city));
		}
		Category category = new Category();
		category.setParent(0);
		modelMap.put("categoryList", categoryService.get(category));
		modelMap.put("brandList", brandService.selectBrandOrderByName());
		if (question.getStatus() == CommonCons.Quesiotn_Flag.RELEASE_QUESTION) {
			// 从缓存中获取定时发布的时间
			String key = RedisKeyUtil.getKey(RedisKeyUtil.Model.question,
					"release_question");
			Map<String, Object> map = cacheService.getMap(key);
			Date date = (Date) map.get(questionId.toString());
			if (date != null) {
				String releaseTime = DateUtils.dateToStr(date,
						DateUtils.formatTime);
				modelMap.put("releaseTime", releaseTime);
			}
		}
	}

	@QaLog(action = LogAction.QUESTION_UPDATE_QUESTION, cmd = "updateQuestion")
	@RequestMapping(value = "updateQuestion", method = RequestMethod.POST)
	@ResponseBody
	public QaResult updateQuestion(Question que, String releaseTime,
			String[] attachs, String[] seriesIds,String container,int contentType ) {
		if (StringUtils.isNotBlank(releaseTime)) {
			Date releaseDate = DateUtils.strToDate(releaseTime);
			// redis中设置定时发布的问题
			String key = RedisKeyUtil.getKey(RedisKeyUtil.Model.question,
					"release_question");
			Map<String, Object> map = cacheService.getMap(key);
			Date date1 = (Date) map.get(que.getQuestionId().toString());
			if (date1 != null && releaseDate.getTime() != date1.getTime()) {
				map.put(que.getQuestionId().toString(), releaseDate);
			}
			cacheService.set(key, map);
		}
		que.setIs_html((short)contentType);
		if (que.getIs_html()==1) {
			String content=container.substring(0,1);
			if (",".equals(content)) {
				content=container.substring(1);
			}else{
				content=container;
			}
			que.setContent(content);
			if (attachs!=null&&attachs.length>0) {				
				if (attachs.length>1) {
					return new QaResult(ErrorCode.OVER, "封面不能有多张图片");
				}
				que.setCover(attachs[0]);				
			}else{
				que.setCover("");
			}
			if (StringUtils.isBlank(que.getTitle())) {
				return new QaResult(ErrorCode.OVER, "图文帖必须有标题");
			}						
			questionService.updateQuestion(que, null, seriesIds);
		}else{
			//截取图片附件
			String [] attachArray=new String[attachs.length];
			for (int i = 0; i < attachs.length; i++) {
				String attach=attachs[i];
				attach=attach.replaceAll(constants.getAvatarGroup1Root(), "");
				attach=attach.replaceAll(constants.getAvatarGroup2Root(), "");
				attachArray[i]=attach;
			}
			questionService.updateQuestion(que, attachArray, seriesIds);
		}		
		qaUserService.updateQuestion(que.getQuestionId());
		return new QaResult();
	}

	@RequestMapping("verifyList")
	public void verifyList(ModelMap modelMap, Integer answerType,
			String startDate, String endDate, Question question, Answer answer,
			String keyWord, String nickName) {
		if (StringUtils.isNotBlank(nickName)) {
			String userId = rUserService.getUserIdByNickName(nickName);
			modelMap.put("nickName", nickName);
			if (StringUtils.isNotBlank(userId) && !"0".equals(userId)) {
				question.setUid(Long.parseLong(userId));
				nickName = null;
			}
		}
		if (StringUtils.isNotBlank(keyWord)) {
			modelMap.put("keyWord", keyWord);
		}
		if (StringUtils.isNotBlank(startDate)) {
			Date date = DateUtils.strToDate(startDate);
			if (date != null) {
				question.setCreatedAt((int) (date.getTime() / 1000));
				modelMap.put("startDate", startDate);
			}
		}
		if (StringUtils.isNotBlank(endDate)) {
			Date date = DateUtils.strToDate(endDate);
			if (date != null) {
				question.setUpdatedAt((int) (date.getTime() / 1000));
				modelMap.put("endDate", endDate);
			}
		}
		if (question.getStatus() == null) {
			question.setStatus(-1);
		}
		PageInfo pageInfo;
		if (answerType != null && answerType == 2) {
			pageInfo = answerService.getAnswerList(answer, null, null, keyWord,
					CommonCons.Quesiotn_Flag.REVIEW_QUESTION,nickName);
			modelMap.put("answeQuestion", answer);
			modelMap.put("answerType", 2);
			modelMap.put("question", null);
		} else {
			pageInfo = questionService.getQuestionListByMapParam(question,
					keyWord, nickName, null, null, null,
					CommonCons.Quesiotn_Flag.REVIEW_QUESTION);
			modelMap.put("answerType", 1);
			modelMap.put("answeQuestion", question);
			Category category = new Category();
			category.setParent(0);
			// 获取问题分类信息
			modelMap.put("categoryList", categoryService.get(category));
			// 获取定时问题
			String releaseKey = RedisKeyUtil.getKey(
					RedisKeyUtil.Model.question, "release_question");
			Map<String, Object> releaseMap = cacheService.getMap(releaseKey);
			modelMap.put("releaseMap", releaseMap);
		}
		modelMap.put("pager", pageInfo);
		// 获取市信息
		City city = new City();
		city.setParent(0);
		modelMap.put("provinceList", cityMapper.select(city));
	}

	// 审核问题
	@QaLog(action = LogAction.QUESTION_VERIFY_QUESTION, cmd = "verifyQuestion")
	@RequestMapping(value = "verifyQuestion", method = RequestMethod.POST)
	@ResponseBody
	public QaResult verifyQuestion(Question question) {
		question=questionService.get(question.getQuestionId());
		if (question==null) {
			return new QaResult(ErrorCode.OVER);
		}
		//更新状态
		questionService.verifyQuestion(question.getQuestionId());
		qaUserService.updateQuestion(question.getQuestionId());
		//更新用户问题数
		userService.updateQuestionCount(question.getUid());
		
		return new QaResult();
	}

	@RequestMapping("topList")
	public void topList(ModelMap modelMap, Long questionId) {
		// 查询需要置顶的帖子
		Question question = questionService.get(questionId);
		if (question == null) {
			logger.info("问题id为空");
			return;
		}
		// 查询已经置顶的帖子
		Question questiontop = new Question();
		questiontop.setIsTop(1);
		List<Question> topList = questionService.get(questiontop);
		List<Question> questionList = new ArrayList<Question>();
		questionList.add(question);
		questionList.addAll(topList);
		modelMap.put("questionList", questionList);
	}

	@RequestMapping(value = "selectQuestionKey", method = RequestMethod.POST)
	@ResponseBody
	public QaResult selectQuestionKey(Long questionId) {
		Question question = questionService.get(questionId);
		return new QaResult(question.getKey());
	}

	@RequestMapping("updateAttach")
	@ResponseBody
	public QaResult updateAttach(Long attachId) {
		Attach attach = attachMapper.selectByPrimaryKey(attachId);
		if (attach.getFileType() == 3) {
			attach.setFileType(4);
		} else if (attach.getFileType() == 4) {
			attach.setFileType(3);
		}
		attachMapper.updateByPrimaryKey(attach);
		return new QaResult();
	}

	@RequestMapping("exportQuestion")
	public void exportQuestion(HttpServletResponse response, String startDate,
			String endDate, String keyWord, Integer categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keyWord)) {
			map.put("keyWord", keyWord);
		}
		if (categoryId != null) {
			map.put("categoryId", categoryId);
		}
		if (StringUtils.isNotBlank(startDate)
				&& StringUtils.isNotBlank(endDate)) {
			Date date = DateUtils.strToDate(startDate);
			map.put("startTime", (int) (date.getTime() / 1000));
			Date date1 = DateUtils.strToDate(endDate);
			map.put("endTime", (int) (date1.getTime() / 1000));
		} else {
			return;
		}
		questionService.exportQuestionListByMapParam(response, map);
	}

	@RequestMapping("exportRubbish")
	public void exportRubbish(HttpServletResponse response, String startDate,
			String endDate, String keyWord, Integer categoryId, Integer source) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keyWord)) {
			map.put("keyWord", keyWord);
		}
		if (source != null) {
			map.put("source", source);
		}
		if (StringUtils.isNotBlank(startDate)
				&& StringUtils.isNotBlank(endDate)) {
			Date date = DateUtils.strToDate(startDate);
			map.put("startTime", (int) (date.getTime() / 1000));
			Date date1 = DateUtils.strToDate(endDate);
			map.put("endTime", (int) (date1.getTime() / 1000));
		}
		questionService.exportRubbishQuestion(response, map);
	}

	@RequestMapping("toFrozenUser")
	public void toFrozenUser(Long uid, ModelMap modelMap) {
		modelMap.put("reason", FrozenReasonEnum.reasonMap);
		modelMap.put("uid", uid);
	}

	@RequestMapping("hingEnergyList")
	public void hingEnergyList(String startTime, String endTime,
			Question question, ModelMap modelMap) {
		PageInfo<Question> page = questionService.selectHighEnergyQuestion(
				startTime, endTime, question);
		modelMap.put("pager", page);
		modelMap.put("question", question);
		modelMap.put("startTime", startTime);
		modelMap.put("endTime", endTime);
	}

	@RequestMapping("questionSpeed")
	public void questionSpeed(ModelMap map) {
		String key = RedisKey.QuestionEnum.question_speed.getKey();
		String speed = cacheService.getStr(key);
		Long time = cacheService.getTime(key, TimeUnit.MINUTES);
		map.put("speed", speed);
		map.put("time", time);
	}

	@RequestMapping("saveQuestionSpeed")
	@ResponseBody
	public QaResult saveQuestionSpeed(String speed, Long time) {
		boolean b = cacheService.set(
				RedisKey.QuestionEnum.question_speed.getKey(), speed, time,
				TimeUnit.MINUTES);
		return new QaResult();
	}

	@RequestMapping("pushQuestion")
	@ResponseBody
	public QaResult pushQuestion(Long questionId, Long uid) {
		Question question = questionService.get(questionId);
		JSONObject userInfo = (JSONObject) JSON.parse(qaUserService
				.getUserDesc(String.valueOf(uid)));
		Integer code = userInfo.getInteger("code");
		if (code != 10000) {
			return new QaResult("没有此用户，请核对后在推送");
		}
		JSONObject userData = (JSONObject) userInfo.get("data");
		String token = userData.getString("token");
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("content", "有人向您发来新的提问，去帮帮Ta");
		message.put("question_id", String.valueOf(questionId));
		message.put("question_key", question.getKey());
		messageService.add(uid, MessageType.NewSpecifyQuestion.getCode(),
				JSONObject.toJSONString(message));
		pushService.push(token, "有人向您提问，去帮帮Ta", new HashMap<String, String>() {
			{
				put("url", "xycar://qa_notification");
			}
		});
		return new QaResult();
	}

	@ModelAttribute("urlObj")
	public JSONObject getUrlObj() {
		return super.getUrlObj();
	}
	
	@RequestMapping(value="delcache")
	@ResponseBody
	public QaResult delCache(){
		String key="qa:sync:third:display";
		if (cacheService.delString(key)) {
			return new QaResult();
		}else{
			return new QaResult("清除缓存失败");
		}		
	}
	
	@RequestMapping("spiderList")
	public void spiderList(ModelMap modelMap,SpiderQuestion spiderQuestion){
		
		spiderQuestion.setStatus((short)1);
		PageInfo<SpiderQuestionVo> page=spiderQuestionService.selectByPage(spiderQuestion);
		//获取所有车型
		List<Series> series=seriesService.getAll();
		//获取所有分类		
		List<Category> categories=categoryService.getAll();		
		modelMap.put("pager", page);
		modelMap.put("series", series);
		modelMap.put("categories", categories);
	}
	
	@RequestMapping(value = "getSubCategory")
	@ResponseBody
	public QaResult getSubCategory(Integer category_id){
		Category category=new Category();
		category.setParent(category_id);
		List<Category> categories=categoryService.get(category);	
		return new QaResult(categories);
	}
	
	@RequestMapping(value = "saveSpider")
	@ResponseBody
	public QaResult saveSpider(SpiderQuestionVo spiderQuestionVo){
		if (spiderQuestionVo.getId()==null||spiderQuestionVo.getSeries_id()==null
				||spiderQuestionVo.getCategory_id()==null) {
			return new QaResult(ErrorCode.OVER,"车型和分类不允许为空");
		}
		if (StringUtils.isBlank(spiderQuestionVo.getContent())) {
			return new QaResult(ErrorCode.OVER,"内容不能为空");
		}
		//用户id
		int uid=QuestionUtil.getRandom();
		//分类
		int category_id=spiderQuestionVo.getCategory_id();
		if (spiderQuestionVo.getSub_category_id()!=null) {
			category_id=spiderQuestionVo.getSub_category_id();
		}
		int today=1;
		String result=qaServerService.batchAdd(uid, (short)category_id, spiderQuestionVo.getContent(), 
				spiderQuestionVo.getSeries_id().toString(),(int)(System.currentTimeMillis()/1000), today);
		System.out.println(result);
		return new QaResult("成功");
	}
	
	@RequestMapping(value = "delSpider")
	@ResponseBody
	public QaResult delSpider(Long id){
		spiderQuestionService.delSpider(id);
		return new QaResult("成功");
	}	
	
	private  String transAttachList(String attachs){
		StringBuffer result=new StringBuffer();
		if (attachs.contains(",")) {
			String[] attachArr=attachs.split(",");
			for (int i = 0; i < attachArr.length; i++) {
				String attach=attachArr[i];
				attach=attach.replaceAll(constants.getAvatarGroup1Root(), "");
				attach=attach.replaceAll(constants.getAvatarGroup2Root(), "");
				result.append(attach);
				if (i<attachArr.length-1) {
					result.append(",");
				}
			}
		}else{
			attachs=attachs.replaceAll(constants.getAvatarGroup1Root(), "");
			attachs=attachs.replaceAll(constants.getAvatarGroup2Root(), "");
			result.append(attachs);
		}
		return result.toString();
	}

}
