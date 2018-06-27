package com.xyauto.qa.service.cloud.impl;

import com.netflix.appinfo.InstanceInfo;
import com.xyauto.qa.core.cloud.RUserForwardedForInterceptor;
import com.xyauto.qa.util.QaFeignClientUtil;
import feign.Feign;
import feign.form.FormEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by shiqm on 2017/3/13.
 */

@Service
public class QBaseServiceImpl<T> {

    private Class<T> entityClass;

    @Autowired
    private QaFeignClientUtil qaFeignClientUtil;

    @Autowired
    private RUserForwardedForInterceptor forwardedForInterceptor;


    public QBaseServiceImpl() {
        this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterType[0];
        }
    }

    public T proxy() {
        InstanceInfo instance = qaFeignClientUtil.getInstance();
        return Feign.builder().requestInterceptor(forwardedForInterceptor).target(entityClass, instance.getHomePageUrl());
    }

}
