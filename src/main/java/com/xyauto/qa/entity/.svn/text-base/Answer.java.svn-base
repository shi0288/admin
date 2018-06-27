package com.xyauto.qa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Table(name = "answer")
public class Answer extends BaseEntity{
	@Id
	@Column(name = "answer_id")
	private Long answerId;
	
	@Column(name = "question_id")
	private Long questionId;
	//用户ID
	private Long uid;
	//回复内容
	private String content;
	//点赞数量
	@Column(name = "agree_count")
	private Integer agreeCount;
	//是否带有附件
	@Column(name = "has_attach")
	private Short hasAttach;
	//问题来源:0 未知 1 pc 2 ios 3 android 4 wap 5 m 6 admin
	private Short source;
	//引用回复用户ID
	@Column(name = "reply_uid")
	private Long replyUid;
	//引用回复ID
	@Column(name = "reply_answer_id")
	private Long replyAnswerId;
	//回复城市ID
	@Column(name = "city_id")
	private Long cityId;
	
	@Column(name = "created_at")
	private Integer createdAt;
	
	@Column(name = "updated_at")
	private Integer updatedAt;
	
	@Column(name = "deleted_at")
	private Integer deletedAt;
	//是否自己删除 ：0否 1是
	@Column(name = "deleted_self")
	private Integer deletedSelf;
	//评论
	private String goodDesc;
	//高能时间
	private Integer goodAt;
	private Integer comment_count;
	
	private Short status;
    @Transient
    private City city;

    @Transient
    private User user;
    
    @Transient
    private List<Attach> attaches = new ArrayList<>();

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAgreeCount() {
		return agreeCount;
	}

	public void setAgreeCount(Integer agreeCount) {
		this.agreeCount = agreeCount;
	}

	public Short getHasAttach() {
		return hasAttach;
	}

	public void setHasAttach(Short hasAttach) {
		this.hasAttach = hasAttach;
	}

	public Short getSource() {
		return source;
	}

	public void setSource(Short source) {
		this.source = source;
	}

	public Long getReplyUid() {
		return replyUid;
	}

	public void setReplyUid(Long replyUid) {
		this.replyUid = replyUid;
	}

	public Long getReplyAnswerId() {
		return replyAnswerId;
	}

	public void setReplyAnswerId(Long replyAnswerId) {
		this.replyAnswerId = replyAnswerId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
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

	public Integer getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Integer deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Integer getDeletedSelf() {
		return deletedSelf;
	}

	public void setDeletedSelf(Integer deletedSelf) {
		this.deletedSelf = deletedSelf;
	}
	
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getGoodDesc() {
		return goodDesc;
	}

	public void setGoodDesc(String goodDesc) {
		this.goodDesc = goodDesc;
	}

	public Integer getGoodAt() {
		return goodAt;
	}

	public void setGoodAt(Integer goodAt) {
		this.goodAt = goodAt;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Attach> getAttaches() {
		return attaches;
	}

	public void setAttaches(List<Attach> attaches) {
		this.attaches = attaches;
	}

	public Integer getComment_count() {
		return comment_count;
	}

	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}
     
    
	
	
}
