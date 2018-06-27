package com.xyauto.qa.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="user_city")
public class UserCity extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_city_id;
	private Long uid;
	private Integer city_id;
	private Integer created_at;
	private Integer deleted_at;
	
	public Long getUser_city_id() {
		return user_city_id;
	}
	public void setUser_city_id(Long user_city_id) {
		this.user_city_id = user_city_id;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Integer getCity_id() {
		return city_id;
	}
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	public Integer getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Integer created_at) {
		this.created_at = created_at;
	}
	public Integer getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(Integer deleted_at) {
		this.deleted_at = deleted_at;
	}
	
}
