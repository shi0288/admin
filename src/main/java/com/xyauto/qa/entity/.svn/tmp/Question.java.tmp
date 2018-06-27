package com.xyauto.qa.entity;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Table(name = "question")
public class Question extends BaseEntity {
    @Id
    @Column(name = "question_id")
    private Long questionId;

    private Long uid;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "has_attach")
    private Integer hasAttach;

    @Column(name = "answer_count")
    private Integer answerCount;

    @Column(name = "answer_users_count")
    private Integer answerUsersCount;

    private Integer source;

    @Column(name = "is_good")
    private Integer isGood;

    @Column(name = "good_sort")
    private Integer goodSort;

    @Column(name = "is_top")
    private Integer isTop;

    @Column(name = "top_sort")
    private Integer topSort;

    @Column(name = "first_answered_at")
    private Integer firstAnsweredAt;

    private Float lng;

    private Float lat;

    @Column(name = "city_id")
    private Integer cityId;

    private Integer status;

    @Column(name = "report_count")
    private Integer reportCount;

    @Column(name = "created_at")
    private Integer createdAt;

    @Column(name = "updated_at")
    private Integer updatedAt;

    @Column(name = "deleted_at")
    private Integer deletedAt;
    @Column(name = "question.key")
    private String key;

    @Column(name = "deleted_self")
    private Integer deletedSelf;

    private String content;
    
    @Column(name = "distrust_reason")     
    private String distrustReason;

    private Short type ;
    private Integer agree_count;
    private String title;
    private Short is_html;
    private String cover;
    
    @Transient
    private City city;

    @Transient
    private User user;

    @Transient
    private Category category;

    @Transient
    private List<Series> series = new ArrayList<>();

    @Transient
    private List<Attach> attaches = new ArrayList<>();

    @Transient
    private List<Brand> brands = new ArrayList<>();


    /**
     * @return question_id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * @return uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return category_id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return has_attach
     */
    public Integer getHasAttach() {
        return hasAttach;
    }

    /**
     * @param hasAttach
     */
    public void setHasAttach(Integer hasAttach) {
        this.hasAttach = hasAttach;
    }

    /**
     * @return answer_count
     */
    public Integer getAnswerCount() {
        return answerCount;
    }

    /**
     * @param answerCount
     */
    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    /**
     * @return answer_users_count
     */
    public Integer getAnswerUsersCount() {
        return answerUsersCount;
    }

    /**
     * @param answerUsersCount
     */
    public void setAnswerUsersCount(Integer answerUsersCount) {
        this.answerUsersCount = answerUsersCount;
    }

    /**
     * @return source
     */
    public Integer getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * @return is_good
     */
    public Integer getIsGood() {
        return isGood;
    }

    /**
     * @param isGood
     */
    public void setIsGood(Integer isGood) {
        this.isGood = isGood;
    }

    /**
     * @return good_sort
     */
    public Integer getGoodSort() {
        return goodSort;
    }

    /**
     * @param goodSort
     */
    public void setGoodSort(Integer goodSort) {
        this.goodSort = goodSort;
    }

    /**
     * @return is_top
     */
    public Integer getIsTop() {
        return isTop;
    }

    /**
     * @param isTop
     */
    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    /**
     * @return top_sort
     */
    public Integer getTopSort() {
        return topSort;
    }

    /**
     * @param topSort
     */
    public void setTopSort(Integer topSort) {
        this.topSort = topSort;
    }

    /**
     * @return first_answered_at
     */
    public Integer getFirstAnsweredAt() {
        return firstAnsweredAt;
    }

    /**
     * @param firstAnsweredAt
     */
    public void setFirstAnsweredAt(Integer firstAnsweredAt) {
        this.firstAnsweredAt = firstAnsweredAt;
    }

    /**
     * @return lng
     */
    public Float getLng() {
        return lng;
    }

    /**
     * @param lng
     */
    public void setLng(Float lng) {
        this.lng = lng;
    }

    /**
     * @return lat
     */
    public Float getLat() {
        return lat;
    }

    /**
     * @param lat
     */
    public void setLat(Float lat) {
        this.lat = lat;
    }

    /**
     * @return city_id
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return report_count
     */
    public Integer getReportCount() {
        return reportCount;
    }

    /**
     * @param reportCount
     */
    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    /**
     * @return created_at
     */
    public Integer getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return updated_at
     */
    public Integer getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return deleted_at
     */
    public Integer getDeletedAt() {
        return deletedAt;
    }

    /**
     * @param deletedAt
     */
    public void setDeletedAt(Integer deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return deleted_self
     */
    public Integer getDeletedSelf() {
        return deletedSelf;
    }

    /**
     * @param deletedSelf
     */
    public void setDeletedSelf(Integer deletedSelf) {
        this.deletedSelf = deletedSelf;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public List<Attach> getAttaches() {
        return attaches;
    }

    public void setAttaches(List<Attach> attaches) {
        this.attaches = attaches;
    }

	public String getDistrustReason() {
		return distrustReason;
	}

	public void setDistrustReason(String distrustReason) {
		this.distrustReason = distrustReason;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Integer getAgree_count() {
		return agree_count;
	}

	public void setAgree_count(Integer agree_count) {
		this.agree_count = agree_count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Short getIs_html() {
		return is_html;
	}

	public void setIs_html(Short is_html) {
		this.is_html = is_html;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}


}