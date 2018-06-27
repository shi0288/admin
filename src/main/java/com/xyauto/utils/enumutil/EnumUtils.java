package com.xyauto.utils.enumutil;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class EnumUtils {

	public enum MessageType {
		NewAnswer("新建回答", 1), 
		NewReply("", 2), 
		NewFavoriteAnswer("", 3), 
		NewSpecifyQuestion("向谁提问", 4), 
		DelQuestion("删除问题", 5), 
		DelAnswer("删除回答", 6), 
		Good_Answer("高能回答", 7);
		private Integer code;
		private String value;

		public Integer getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}

		private MessageType(String value, Integer code) {
			this.code = code;
			this.value = value;
		}
	}
	
	public enum ActivityType{
		status0("未处理",0),
		status1("已联系",1),
		status2("信息错误",2),
		status3("审核未过",3),
		status4("审核通过",4);	
		private Integer code;
		private String value;

		public Integer getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
		private ActivityType(String value, Integer code) {
			this.code = code;
			this.value = value;
		}
		public static Map<Integer, String> getMap(){
			Map<Integer, String> map=new HashMap<Integer, String>();
			for (ActivityType activityType2 : EnumSet.allOf(ActivityType.class)) {
				map.put(activityType2.getCode(), activityType2.getValue());
			}
			return map;
		}		
	}	

}
