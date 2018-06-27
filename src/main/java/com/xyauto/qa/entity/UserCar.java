package com.xyauto.qa.entity;

import javax.persistence.*;

@Table(name = "user_car")
public class UserCar {
    @Id
    @Column(name = "user_car_id")
    private Long userCarId;

    private Long uid;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "series_id")
    private Integer seriesId;

    @Column(name = "created_at")
    private Integer createdAt;

    @Column(name = "deleted_at")
    private Integer deletedAt;

    /**
     * @return user_car_id
     */
    public Long getUserCarId() {
        return userCarId;
    }

    /**
     * @param userCarId
     */
    public void setUserCarId(Long userCarId) {
        this.userCarId = userCarId;
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
}