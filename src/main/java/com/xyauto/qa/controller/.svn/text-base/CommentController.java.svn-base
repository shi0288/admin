package com.xyauto.qa.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.Constants;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.ConvertCons;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.entity.Answer;
import com.xyauto.qa.entity.Comment;
import com.xyauto.qa.service.BlocksService;
import com.xyauto.qa.service.IAnswerService;
import com.xyauto.qa.service.ICommentService;
import com.xyauto.qa.service.cloud.QaUserService;
import com.xyauto.qa.service.cloud.RUserService;
import com.xyauto.qa.util.DateUtils;
import com.xyauto.qa.util.QaResult;

@Controller
@RequestMapping("user/comment")
public class CommentController extends ABaseController {
	@Autowired
	private RUserService rUserService;
	@Autowired
	private IAnswerService answerService;
	@Autowired
	private ICommentService commentService;
    @Autowired
    private BlocksService blocksService;
    @Autowired
    private QaUserService qaUserService;
    @Autowired
    private Constants constants;
	@RequestMapping("commentList")
	public void commentList(ModelMap modelMap, String startDate,Integer status,
			Integer userType, String endDate, String nickName, String keyWord,
			Comment comment) {
		if (comment != null && comment.getAnswer_id() != null) {
			Answer answer = answerService.selectOne(comment.getAnswer_id());
			modelMap.put("answer", answer);
		}
		if (StringUtils.isNotBlank(nickName)) {
			modelMap.put("nickName", nickName);
			String userId = rUserService.getUserIdByNickName(nickName);
			if (StringUtils.isNotBlank(userId) && !"0".equals(userId)) {
				comment.setUid(Long.parseLong(userId));
				nickName = null;
			}
		}
		if (StringUtils.isNotBlank(keyWord)) {
			modelMap.put("keyWord", keyWord);
		}
		if (userType != null) {
			modelMap.put("userType", userType);
		}
		if (status==null) {
			status=CommonCons.Quesiotn_Flag.NORMAL_QUESTION;
		}
		if (StringUtils.isNotBlank(startDate)) {
			Date date = DateUtils.strToDate(startDate);
			if (date != null) {
				comment.setCreated_at((int) (date.getTime() / 1000));
				modelMap.put("startDate", startDate);
			}
		}
		if (StringUtils.isNotBlank(endDate)) {
			Date date = DateUtils.strToDate(endDate);
			if (date != null) {
				comment.setUpdated_at((int) (date.getTime() / 1000));
				modelMap.put("endDate", endDate);
			}
		}
		
		PageInfo<Comment> page = commentService.getCommentList(comment,
				keyWord, status,userType);
		modelMap.put("status", status);
		modelMap.put("pager", page);
		modelMap.put("comment", comment);
		modelMap.put("sourceMap", ConvertCons.ResourceCover.getMap());
	}
	
	@RequestMapping("del")
	@ResponseBody
	public QaResult del(Long comment_id){
		
		return commentService.del(comment_id);
	}
	
	@RequestMapping("commentAdd")
	public void addComment(ModelMap modelMap,Comment comment){
		
		if (comment!=null&&comment.getComment_id()!=null) {
			comment=commentService.selectOne(comment.getComment_id());
			modelMap.put("comment", comment);
		}
		if (comment!=null&&comment.getAnswer_id()!=null) {
			Answer answer=answerService.selectOne(comment.getAnswer_id());
			modelMap.put("answer", answer);
		}
		JSONArray jsonArray = blocksService.getBlockByName(CommonCons.Blocks_Flag.USER_OPERATE_BUSINESS);
        JSONArray businessUserList = null;
        if (jsonArray != null) {
            String temp = rUserService.getUserList(StringUtils.join(jsonArray, ","));
            businessUserList = JSON.parseArray(temp);
        }
        modelMap.put("businessUserList", businessUserList);		
	}
	
	@RequestMapping("add")
	@ResponseBody
	public QaResult add(Comment comment,String attachs){
		if (comment==null||comment.getAnswer_id()==null 
				|| StringUtils.isBlank(comment.getContent())) {
			return new QaResult(ErrorCode.OVER,"参数不正确");
		}
		
		if (StringUtils.isNotBlank(attachs)) {
			attachs=this.transAttachList(attachs);
		}
		 
		String res=qaUserService.createComment(comment.getAnswer_id(), comment.getContent(), attachs,
				comment.getUid(), comment.getReply_uid(), comment.getReply_comment_id());
		JSONObject jsonObject = JSON.parseObject(res);
		if (jsonObject.getIntValue("code") == 10000) {
			
		}else{
			return new QaResult(ErrorCode.OVER, jsonObject.getString("msg"));
		}
		return new QaResult();
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
