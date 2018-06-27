package com.xyauto.system.entiy;

import java.io.Serializable;

/**
 * 
 * @author zhangbh 
 * triggerGroupName+triggername 不可重复 
 * jobGroupName+jobdetailname 不可重复
 */
public class Task implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	// job对应类名
	private String targetObject;
	// job对应的方法名
	private String methodName;
	// 设置Job名称
	private String jobDetailName;
	// job 分组名
	private String jobGroupName;
	// trigger名称
	private String triggerName;
	// trigger 分组名
	private String triggerGroupName;
	// 执行时间
	private String cron;
	// 最后一次任务时间情况 1：成功；0：失败
	private Short status;
	// 成功次数
	private Integer successCount;
	// 失败次数
	private Integer faileCount;
	private Integer createdAt;
	private Integer updatedAt;
	private Short isOpen;
	private String desc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTargetObject() {
		return targetObject;
	}
	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getJobDetailName() {
		return jobDetailName;
	}
	public void setJobDetailName(String jobDetailName) {
		this.jobDetailName = jobDetailName;
	}
	public String getJobGroupName() {
		return jobGroupName;
	}
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroupName() {
		return triggerGroupName;
	}
	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Integer getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	public Integer getFaileCount() {
		return faileCount;
	}
	public void setFaileCount(Integer faileCount) {
		this.faileCount = faileCount;
	}
	public Integer getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Integer updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Short getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Short isOpen) {
		this.isOpen = isOpen;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("trigger组名称：").append(this.triggerGroupName);
		buffer.append("trigger名称：").append(this.triggerName);
		buffer.append("job组名：").append(this.jobGroupName);
		buffer.append("job名称：").append(jobDetailName);
		buffer.append("执行时间：").append(this.cron);
		buffer.append("类名：").append(this.targetObject);
		buffer.append("方法名：").append(this.methodName);
		return buffer.toString();
	}

}
