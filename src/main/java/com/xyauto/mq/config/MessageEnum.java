package com.xyauto.mq.config;

public enum MessageEnum {
	add(101,"添加任务"),
	update(102,"修改任务时间"),
	delete(103,"删除任务");
	private int code;
    private String desc;   
	private MessageEnum(int code,String desc) {
		this.code=code;
		this.desc=desc;
	}
	public int getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
	
}
