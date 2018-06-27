package com.xyauto.bi.entity;
/**
 * 用户考核表，用于记录单个用户每个月不同的考核数据
 * @author zhangbh
 *
 */
public class UserExamineBi {
	private Long uid;
	/**
	 * 7月份考核标准，专家回答字数大于30字
	 *     1:3分钟以内，2:5分钟以内，3:10分钟以内，4：全部，5：每天答题数
	 *  
	 */
	private Short type;
	private Integer resultCount;
	private Integer createdAt;
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public Integer getResultCount() {
		return resultCount;
	}
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	public Integer getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
