package com.xyauto.qa.entity;

import com.xyauto.qa.cons.CommonCons;

import javax.persistence.Transient;

/**
 * Created by shiqm on 2017/3/22.
 */

public class BaseEntity {

    @Transient
    private Integer pageNum = 1;

    @Transient
    private String pageSort;

    @Transient
    private Integer pageSize = CommonCons.Pager_Flag.PAGE_LIMIT;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getPageSort() {
        return pageSort;
    }

    public void setPageSort(String pageSort) {
        this.pageSort = pageSort;
    }
}
