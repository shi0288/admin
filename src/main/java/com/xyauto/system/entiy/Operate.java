package com.xyauto.system.entiy;

public class Operate {
	private Integer operateId;
	private String operateName;
	//操作类型：1查询，2新增，3修改，4删除
	private Short  operateType;
	private String url;
	private Integer mid;
	private Integer createdAt;
	private Integer deletedAt;
	public Integer getOperateId() {
		return operateId;
	}
	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public Short getOperateType() {
		return operateType;
	}
	public void setOperateType(Short operateType) {
		this.operateType = operateType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Integer deletedAt) {
		this.deletedAt = deletedAt;
	}
	
	
}
