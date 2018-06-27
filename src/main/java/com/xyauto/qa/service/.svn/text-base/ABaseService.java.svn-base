package com.xyauto.qa.service;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shiqm on 2017/3/22.
 */
public interface ABaseService<T, PK extends Serializable> {

    /**
     * 根据ID获取实体对象.
     *
     * @param id 记录ID
     * @return 实体对象
     */
    public T get(PK id);


    /**
     * 根据对象属性获取实体对象集合.
     *
     * @param entity 对象属性
     * @return 实体对象
     */
    public List<T> get(T entity);


    /**
     * 获取所有实体对象集合.
     *
     * @return 实体对象集合
     */
    public List<T> getAll();


    /**
     * 获取所有实体对象总数.
     *
     * @return 实体对象总数
     */
    public int getTotalCount();

    /**
     * 保存实体对象.
     *
     * @param entity 对象
     * @return ID
     */
    public int save(T entity);


    /**
     * 更新实体对象.
     *
     * @param entity 对象
     */
    public int update(T entity);


    /**
     * 根据ID删除实体对象.
     *
     * @param id 记录ID
     */
    public int delete(PK id);


    /**
     * 根据属性删除实体对象.
     *
     * @param entity 对象属性
     */
    public int delete(T entity);


    /**
     * 根据属性获取实体对象.
     *
     * @param entity 对象属性
     */
    public T getOne(T entity);


    /**
     * 获取所有实体对象总数.
     *
     * @param entity 对象属性
     */
    public int getCount(T entity);


    /**
     * 根据Pager对象进行查询.
     *
     * @param entity 对象属性
     * @return Pager对象
     */
    public PageInfo findByPager(T entity);
    
    /**
     * 根据主键更新不为null的值
     * @param entity
     * @return
     */
    public int updateSelective(T entity);


}
