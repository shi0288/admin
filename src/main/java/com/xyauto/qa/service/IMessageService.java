package com.xyauto.qa.service;

import java.util.List;

import com.xyauto.qa.entity.Message;



public interface IMessageService {
	
	 int add(long uid,int type,String content);
	 /**
	  * 高能回答推送信息每月每人只有一条
	  * @param uid
	  * @param time
	  * @param type
	  * @return
	  */
	 List<Message>  selectGroupUid(Long uid,String time,Integer type);
	 
	 /**
	  * 向用户推送消息
	  * @param uid
	  * @param type
	  * @param content
	  */
	 void pushMessage(long uid,int type,String content);

}
