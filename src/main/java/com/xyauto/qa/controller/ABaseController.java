package com.xyauto.qa.controller;

import com.alibaba.fastjson.JSONObject;
import com.xyauto.qa.core.annotation.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 基础Controller类
 * ABaseController 前面A没有特别用意 只是为了在开发环境中 排在第一位置 便于明了
 * Created by shiqm on 2017/3/22.
 */
public class ABaseController {

    @Log
    protected Logger logger;


    @Autowired
    protected HttpSession httpSession;


    @Autowired
    protected HttpServletRequest request;


    protected void setSession(String key, Object value) {
        httpSession.setAttribute(key, value);
    }

    protected void removeSession(String key) {
        httpSession.removeAttribute(key);
    }

    protected Object getSession(String key) {
        return httpSession.getAttribute(key);
    }

    protected String getSessionSring(String key) {
        if (httpSession.getAttribute(key) != null) {
            return (String) httpSession.getAttribute(key);
        }
        return null;
    }


    protected String emptyNull(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return value;
    }


    public JSONObject getUrlObj() {
        JSONObject jsonObject = new JSONObject();
        String url=request.getRequestURI().split("\\?")[0];
        String[] urlArrays = url.split("\\/");
        if (urlArrays.length == 4) {
            jsonObject.put("levelOne", urlArrays[2]);
            jsonObject.put("levelTwo", urlArrays[3]);
        }
        return jsonObject;
    }

}
