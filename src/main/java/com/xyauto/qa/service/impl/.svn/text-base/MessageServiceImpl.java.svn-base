package com.xyauto.qa.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.entity.Message;
import com.xyauto.qa.mapper.MessageMapper;
import com.xyauto.qa.service.IMessageService;
import com.xyauto.qa.service.cloud.QaUserService;
import com.xyauto.qa.service.cloud.impl.PushService;

@Service
public class MessageServiceImpl implements IMessageService {
	@Autowired
	private QaUserService qaUserService;
	@Autowired
	private PushService pushService;
	@Log
	private Logger logger;
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public int add(long uid, int type, String content) {
		messageMapper.add(uid, type, content);
		return 0;
	}

	@Override
	public List<Message> selectGroupUid(Long uid, String time,Integer type) {
		return messageMapper.selectGroupUid( uid,  time, type);		
	}

	@Override
	public void pushMessage(long uid, int type, String content) {
		// TODO Auto-generated method stub
		try {				
			//根据用户id获取token
			JSONObject userInfo=(JSONObject) JSON.parse(qaUserService.getUserDesc(String.valueOf(uid))); 			
			JSONObject userData=(JSONObject)userInfo.get("data");	
			String token=userData.getString("token");
			content=userData.getString("nick")+content;
			//保存消息
			this.add(uid, type, JSONObject.toJSONString(content));	
			//推送消息
			pushService.push(token, content, new HashMap<String, String>() {
	            {
	                put("url", "xycar://qa_message");
	            }
	        });
		} catch (Exception e) {
			logger.info("推送消息失败，推送内容是："+content);
			e.printStackTrace();
		}
		
		
	}

}
