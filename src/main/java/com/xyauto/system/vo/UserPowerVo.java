package com.xyauto.system.vo;

public class UserPowerVo {

	@Override
	public String toString() {
		return "UserPowerVo{" +
				"uid=" + uid +
				", userName='" + userName + '\'' +
				", powerId=" + powerId +
				", powerName='" + powerName + '\'' +
				", url='" + url + '\'' +
				", parentId=" + parentId +
				'}';
	}

	//用户id
	private Long uid;
	//用户名
	private String userName;
	//权限id:功能id或者是菜单id
	private Integer powerId;
	private String powerName;
	private String url;
	private Integer parentId;
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getPowerId() {
		return powerId;
	}
	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}
	public String getPowerName() {
		return powerName;
	}
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	
	
			
}
