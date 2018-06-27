package com.xyauto.qa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "brand")
public class Brand {
    @Id
    @Column(name = "brand_id")
    private Integer brandId;

    private String name;

    private String logo;

    @Column(name = "question_count")
    private Integer questionCount;

    @Column(name = "first_letter")
    private String firstLetter;

    @Column(name = "created_at")
    private Integer createdAt;


    @Transient
    private List<Series> series = new ArrayList<>();


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
     * @return logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
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
     * @return first_letter
     */
    public String getFirstLetter() {
        return firstLetter;
    }

    /**
     * @param firstLetter
     */
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
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


    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }
}