package com.xyauto.qa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xyauto.qa.cons.ConvertCons;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.cons.LogAction;
import com.xyauto.qa.service.cloud.QaUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.core.annotation.QaLog;
import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.City;
import com.xyauto.qa.entity.Message;
import com.xyauto.qa.entity.Question;
import com.xyauto.qa.entity.vo.HingEntryAnswerVo;
import com.xyauto.qa.mapper.CityMapper;
import com.xyauto.qa.service.BlocksService;
import com.xyauto.qa.service.IAnswerService;
import com.xyauto.qa.service.IMessageService;
import com.xyauto.qa.service.QuestionService;
import com.xyauto.qa.service.UserService;
import com.xyauto.qa.service.cloud.RUserService;
import com.xyauto.qa.service.cloud.impl.PushService;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.qa.util.QaResult;
import com.xyauto.utils.enumutil.EnumUtils.MessageType;

/**
 * 回复信息类
 *
 * @author zhangbh
 */
@Controller
@RequestMapping("user/answer")
public class AnswerQuestionController extends AjaxBaseController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private BlocksService blocksService;
    @Autowired
    private RUserService rUserService;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private IAnswerService answerService;
    @Autowired
    private QaUserService qaUserService;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private PushService pushService;
    @Autowired
    private UserService userService;

    @RequestMapping("answerAdd")
    public void toAnswerQuestion(ModelMap modelMap, Long questionId, Long answerId) {
        //根据问题id查询问题信息
        Question question = questionService.getQuestionDesc(questionId);
        modelMap.put("question", question);
        JSONArray jsonArray = blocksService.getBlockByName(CommonCons.Blocks_Flag.USER_OPERATE_BUSINESS);
        JSONArray businessUserList = null;
        if (jsonArray != null) {
            String temp = rUserService.getUserList(StringUtils.join(jsonArray, ","));
            businessUserList = JSON.parseArray(temp);
        }
        modelMap.put("businessUserList", businessUserList);
        //获取回复信息
        if (answerId != null) {
            Answer answer = new Answer();
            answer.setAnswerId(answerId);
            PageInfo<Answer> pageInfoAnswer = answerService.getAnswerList(answer);
            answer = pageInfoAnswer.getList().get(0);
            modelMap.put("answer", answer);
        }
        //获取城市信息
        City city = new City();
        city.setParent(0);
        modelMap.put("provinceList", cityMapper.select(city));
    }

    @QaLog(action = LogAction.ANSWER_CREATE_ANSWER, cmd = "createAnswer")
    @RequestMapping(value = "createAnswer", method = RequestMethod.POST)
    @ResponseBody
    public QaResult createAnswer(Answer answer, String attachs) {
        if (answer == null) {
            return new QaResult(ErrorCode.OVER, "参数错误");
        }
        String cityId = null;
        if (answer.getCityId() != null) {
            cityId = answer.getCityId().toString();
        }
        System.out.println(answer.getQuestionId());
        System.out.println(answer.getContent());
        System.out.println(attachs);
        System.out.println(cityId);
        System.out.println(answer.getReplyUid());
        System.out.println(answer.getReplyAnswerId());
        System.out.println(answer.getUid());
        String res = qaUserService.createAnswerNoPic(answer.getQuestionId(), answer.getContent(), attachs, cityId, answer.getReplyUid(), answer.getReplyAnswerId(), answer.getUid());
        JSONObject jsonObject = JSON.parseObject(res);
        if (jsonObject.getIntValue("code") == 10000) {
            return new QaResult();
        } else {
            return new QaResult(ErrorCode.OVER, jsonObject.getString("msg"));
        }
    }

    @RequestMapping("answerList")
    public void answerList(Answer answer, String startDate, String endDate, String nickName, 
    		Integer userType, String sort, String keyWord, ModelMap modelMap,Integer beforDay) {
        if (answer != null && answer.getQuestionId() != null) {
            //根据问题id查询问题信息
            Question question = new Question();
            question.setQuestionId(answer.getQuestionId());
            PageInfo<Question> pageInfo = questionService.getQuestionList(question);
            question = pageInfo.getList().get(0);
            modelMap.put("question", question);
        }
        if (StringUtils.isNotBlank(keyWord)) {
            modelMap.put("keyWord", keyWord);
        }
        if (userType != null) {
            modelMap.put("userType", userType);
        }
        if (StringUtils.isNotBlank(nickName)) {
            String userId = rUserService.getUserIdByNickName(nickName);
            modelMap.put("nickName", nickName);
			if (StringUtils.isNotBlank(userId) && !"0".equals(userId)) {
				answer.setUid(Long.parseLong(userId));
				nickName = null;
			}
        }
        if (beforDay!=null) {
        	startDate=DateUtils.dateToStr(DateUtils.beforDate(new Date(), 7),DateUtils.formatTime);
        	endDate=DateUtils.dateToStr(new Date(),DateUtils.formatTime);
		}
        if (StringUtils.isNotBlank(startDate)) {
            Date date = DateUtils.strToDate(startDate);
            if (date != null) {
                answer.setCreatedAt((int) (date.getTime() / 1000));
                modelMap.put("startDate", startDate);
            }
        }
        if (StringUtils.isNotBlank(endDate)) {
            Date date = DateUtils.strToDate(endDate);
            if (date != null) {
                answer.setUpdatedAt((int) (date.getTime() / 1000));
                modelMap.put("endDate", endDate);
            }
        }
        PageInfo<Answer> pageInfoAnswer = answerService.getAnswerList(answer, userType, sort, keyWord, null,nickName);
        modelMap.put("pager", pageInfoAnswer);
        modelMap.put("answer", answer);

        //获取市信息
        City city = new City();
        city.setParent(1);
        modelMap.put("provinceList", cityMapper.select(city));
        if (answer.getSource() != null && answer.getSource() != 0) {
            modelMap.put("source", answer.getSource());
        }
        modelMap.put("sourceMap", ConvertCons.ResourceCover.getMap());
    }
    
    @QaLog(action = LogAction.ANSWER_DELETE_ANSWER, cmd = "deleteAnswer")
    @RequestMapping(value = "deleteAnswer", method = RequestMethod.POST)
    @ResponseBody
    public QaResult deleteAnswer(Long answerId) {
        String delInfo = qaUserService.deleteAnswer(answerId, 0L);
        JSONObject jsonObject = JSON.parseObject(delInfo);
        return new QaResult(jsonObject.getInteger("sub_code").toString(), jsonObject.getString("msg"));
    }

    //恢复回复
    @QaLog(action = LogAction.ANSWER_RECOVER_ANSWER, cmd = "recoverAnswer")
    @RequestMapping(value = "recoverAnswer", method = RequestMethod.POST)
    @ResponseBody
    public QaResult recoverAnswer(Long answerId) {
        Answer answer = answerService.get(answerId);
        answer.setAnswerId(answerId);
        answer.setUpdatedAt((int) (new Date().getTime() / 1000));
        answer.setDeletedAt(0);
        answer.setDeletedSelf(0);
        answerService.update(answer);
        //更新回复索引
      //  qaUserService.updateQuestion(null);
        //更新问题信息
        Question question=questionService.get(answer.getQuestionId());       
        if (question!=null) {
        	questionService.updateAnswer(question.getQuestionId());
			if (question.getDeletedAt()==0&&question.getStatus()>0) {
				qaUserService.updateQuestion(question.getQuestionId());
			}
		}      
        //更新用户信息
        userService.updateAnswerCount(answer.getUid());
        return new QaResult();
    }

    //审核回复
    @RequestMapping(value = "verifyAnswer", method = RequestMethod.POST)
    @ResponseBody
    public QaResult verifyAnswer(Long answerId) {
        Answer answer = answerService.get(answerId);
        answer.setAnswerId(answerId);
        answer.setStatus((short) 2);
        answer.setUpdatedAt((int) (new Date().getTime() / 1000));
        answerService.update(answer);
        return new QaResult();
    }
    
    @RequestMapping("exportAnswer")
    public void exportAnswer(HttpServletResponse response,String startDate, 
    		String endDate,String keyWord,Integer categoryId ){
    	Map<String, Object> map=new HashMap<String, Object>();
        if (StringUtils.isNotBlank(keyWord)) {
        	map.put("keyWord", keyWord);
        }
        if (categoryId!=null) {
        	map.put("categoryId", categoryId);
		}
        if (StringUtils.isNotBlank(startDate)&&StringUtils.isNotBlank(endDate)) {
            Date date = DateUtils.strToDate(startDate);
            map.put("startTime", (int)(date.getTime() / 1000));
            Date date1 = DateUtils.strToDate(endDate);
            map.put("endTime", (int)(date1.getTime() / 1000));
        }else{
        	return;
        }
        answerService.exportAnswer(response,map);
        
    }
    
    @RequestMapping("hingEnergyAnswer")
    public void hingEnergyAnswer(Long questionId,ModelMap modelMap){
    	//查询问题
        Question question = questionService.getQuestionDesc(questionId);        
        modelMap.put("question", question);       
    	//查询满足条件的回答
    	List<Answer> answerList=answerService.selectHingEnergyAnswer(questionId,null);
    	modelMap.put("answerList", answerList);  
    }
    @RequestMapping("setHingEnergy")
    @ResponseBody
    public QaResult setHingEnergy(Long answerId,boolean isHingEnergy,String comment){
    	String result= answerService.setHingEnergy(answerId,isHingEnergy,comment);
    	if (isHingEnergy) {
			if (result.equalsIgnoreCase("success")) {
				return new QaResult("设置高能成功");
			}
		}else {
			if (result.equalsIgnoreCase("success")) {
				return new QaResult("取消高能成功");
			}
		}
    	return new QaResult(result);
    }
    
    @RequestMapping("writeComment")
    @ResponseBody
    public QaResult writeComment(Long answerId,String comment){
    	if (StringUtils.isBlank(comment)) {
    		return new QaResult("评论不能为null");
		}
    	Answer answer=answerService.get(answerId);
    	answer.setGoodDesc(comment);
    	answerService.update(answer);
    	return new QaResult();
    }
    
    @RequestMapping("hingEnergyList")
    public void pushHingEnergyList(Long uid,String time ,ModelMap map){
    	//查询用户高能回答信息
    	map.put("uid", uid);
    	map.put("time", time);
    	map.put("list",answerService.selectPushHingEnergy(uid,time));    	
    }
    
    @RequestMapping("pushAnswer")
    @ResponseBody
    public QaResult pushAnswer(Long uid,String time,Double money){
    	//查询是否已经推送过，没有则查询推送信息
    	List<HingEntryAnswerVo> list=answerService.selectPushHingEnergy(uid,time);
    	if (list==null||list.size()==0) {
    		new QaResult("推送数据有误");
		}
    	HingEntryAnswerVo vo=list.get(0);
    	if (vo.isPush()) {
			new QaResult("该用户在所选时间已经推送过，请不用重复推送");
		}
    	Date date=DateUtils.strToDate(time,"yyyyMM");
    	//推送之前保存信息
    	String content="专家你好！"+(date.getMonth()+1)+"月份通过审核的高能回答共计"+vo.getCount()+"条，奖励金额"+money+"元，稍后运营同学会联系你~";
    	messageService.pushMessage(uid, MessageType.Good_Answer.getCode(), content);    	
    	return new QaResult();
    }
    
    @RequestMapping("firstAnswer")
    @ResponseBody
    public QaResult firstAnswer(Long answerId){
    	//设置首条回答   	
    	return answerService.setFirstAnswer(answerId);
//    	return new QaResult(ErrorCode.OVER,"该回复已经是第一条，请不要重复设置");
    }
    
    @ModelAttribute("urlObj")
    public JSONObject getUrlObj() {
        return super.getUrlObj();
    }
}
