package com.xyauto.qa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

public class Comment extends BaseEntity{
	private Long comment_id;
	private Long answer_id;
	private Long uid;
	private String content;
	private Long reply_comment_id;
	private Long reply_uid;
	private Integer source;
	private Integer has_attach;
	private Integer agree_count;
	private Integer created_at;
	private Integer updated_at;
	private Integer deleted_at;
	
	
	@Transient
    private User user;
	@Transient
    private List<Attach> attaches = new ArrayList<>();
	
	
	public Long getComment_id() {
		return comment_id;
	}
	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}
	public Long getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(Long answer_id) {
		this.answer_id = answer_id;
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
	public Long getReply_comment_id() {
		return reply_comment_id;
	}
	public void setReply_comment_id(Long reply_comment_id) {
		this.reply_comment_id = reply_comment_id;
	}
	public Long getReply_uid() {
		return reply_uid;
	}
	public void setReply_uid(Long reply_uid) {
		this.reply_uid = reply_uid;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getHas_attach() {
		return has_attach;
	}
	public void setHas_attach(Integer has_attach) {
		this.has_attach = has_attach;
	}
	public Integer getAgree_count() {
		return agree_count;
	}
	public void setAgree_count(Integer agree_count) {
		this.agree_count = agree_count;
	}
	public Integer getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Integer created_at) {
		this.created_at = created_at;
	}
	public Integer getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Integer updated_at) {
		this.updated_at = updated_at;
	}
	public Integer getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(Integer deleted_at) {
		this.deleted_at = deleted_at;
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
	
	
}
