package com.xyauto.qa.entity;

import javax.persistence.*;

@Table(name = "series")
public class Series {
    @Id
    @Column(name = "series_id")
    private Integer seriesId;

    @Column(name = "subbrand_id")
    private Integer subbrandId;

    @Column(name = "brand_id")
    private Integer brandId;

    private String name;

    @Column(name = "question_count")
    private Integer questionCount;

    @Column(name = "created_at")
    private Integer createdAt;

    private String img;

    private String urlspell;

    @Transient
    private Brand brand;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * @return series_id
     */
    public Integer getSeriesId() {
        return seriesId;
    }

    /**
     * @param seriesId
     */
    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    /**
     * @return subbrand_id
     */
    public Integer getSubbrandId() {
        return subbrandId;
    }

    /**
     * @param subbrandId
     */
    public void setSubbrandId(Integer subbrandId) {
        this.subbrandId = subbrandId;
    }

    /**
     * @return brand_id
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * @param brandId
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return question_count
     */
    public Integer getQuestionCount() {
        return questionCount;
    }

    /**
     * @param questionCount
     */
    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
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
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return urlspell
     */
    public String getUrlspell() {
        return urlspell;
    }

    /**
     * @param urlspell
     */
    public void setUrlspell(String urlspell) {
        this.urlspell = urlspell;
    }
}