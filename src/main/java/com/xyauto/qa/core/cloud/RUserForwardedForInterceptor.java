package com.xyauto.qa.core.cloud;

import java.util.Collection;
import java.util.Map;

import com.xyauto.qa.cons.ApiUserCons;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 封装了请求时附加的app_id和sign,解耦业务逻辑
 *
 * Created by shiqm on 2017/3/14.
 */

@Component
public class RUserForwardedForInterceptor implements RequestInterceptor {


    @Autowired
    private ApiUserCons apiCons;

    @Override
    public void apply(RequestTemplate input) {
    	Map<String, Collection<String>> queries=input.queries();
    	Collection<String> app_id=queries.get("app_id");
    	if (app_id==null) {
    		input.query(true, "app_id", apiCons.getApp_id());
		}       
        input.query(true, "sign", apiCons.getSign());
    }


}
