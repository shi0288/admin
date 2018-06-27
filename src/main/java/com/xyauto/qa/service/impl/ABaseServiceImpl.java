package com.xyauto.qa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.service.ABaseService;
import com.xyauto.qa.util.BaseMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by shiqm on 2017/3/22.
 */
public class ABaseServiceImpl<T, PK extends Serializable> implements ABaseService<T, PK> {


    @Log
    protected Logger logger;

    @Autowired
    private BaseMapper<T> baseMapper;

    public BaseMapper<T> getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }


    @Override
    public T get(PK id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> get(T entity) {
        return baseMapper.select(entity);
    }

    @Override
    public List<T> getAll() {
        return baseMapper.selectAll();
    }

    @Override
    public int getTotalCount() {
        return baseMapper.selectCount(null);
    }

    @Override
    public int save(T entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public int update(T entity) {   	
        return baseMapper.updateByPrimaryKey(entity);       
    }

    @Override
    public int delete(T entity) {
        return baseMapper.delete(entity);
    }

    @Override
    public int delete(PK id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public T getOne(T entity) {
        return baseMapper.selectOne(entity);
    }

    @Override
    public int getCount(T entity) {
        return baseMapper.selectCount(entity);
    }

    @Override
    public PageInfo findByPager(T entity) {
        Class<?> clazz = entity.getClass().getSuperclass();
        Integer pageNum = null;
        Integer pageSize = null;
        String pageSort = null;
        try {
            Field field = clazz.getDeclaredField("pageSort");
            field.setAccessible(true);
            pageSort= (String) field.get(entity);
            field = clazz.getDeclaredField("pageNum");
            field.setAccessible(true);
            pageNum= (Integer) field.get(entity);
            field = clazz.getDeclaredField("pageSize");
            field.setAccessible(true);
            pageSize= (Integer) field.get(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(1, CommonCons.Pager_Flag.PAGE_LIMIT);
        }
        if(!StringUtils.isEmpty(pageSort)){
            OrderByHelper.orderBy(pageSort);
        }
        return new PageInfo<T>(baseMapper.select(entity));
    }

	@Override
	public int updateSelective(T entity) {		
		return baseMapper.updateByPrimaryKeySelective(entity);
	}

}
