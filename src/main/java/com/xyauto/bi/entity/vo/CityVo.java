package com.xyauto.bi.entity.vo;

public class CityVo {
	private Integer cityId;
	private String  cityName;
	private Integer proviceId;
	private String  proviceName;
	private Integer questionCount;
	private Integer answerCount;
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getProviceId() {
		return proviceId;
	}
	public void setProviceId(Integer proviceId) {
		this.proviceId = proviceId;
	}
	public String getProviceName() {
		return proviceName;
	}
	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}
	public Integer getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}
	public Integer getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}
	
}
