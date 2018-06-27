package com.xyauto.qa;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pic.url")
public class Constants {

	// 上传文件分组名
	public static String fileGroupName = "group1";
	// 默认值
	public static long zero = 0;
	/**
	 * 车顾问信息
	 */
	public static String appKey="QA";
	public static String appsecret="3B4BF673-1065-4478-8704-C3EA4ABB21C7"; 
	public static String url= "http://api.xingyuanauto.com/QA/GetBrokerInfo";

	// 头像前缀
	// public static String avatarGroup1Root = "http://192.168.3.71/";
	// public static String avatarGroup2Root = "http://192.168.3.71/";
	// http://img1.qcdqcdn.com/
	// http://img2.qcdqcdn.com/

	private String avatarGroup1Root;
	private String avatarGroup2Root;
	
	public String getAvatarGroup1Root() {
		return avatarGroup1Root;
	}
	public void setAvatarGroup1Root(String avatarGroup1Root) {
		this.avatarGroup1Root = avatarGroup1Root;
	}
	public String getAvatarGroup2Root() {
		return avatarGroup2Root;
	}
	public void setAvatarGroup2Root(String avatarGroup2Root) {
		this.avatarGroup2Root = avatarGroup2Root;
	}
	

}
