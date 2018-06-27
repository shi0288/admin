package com.xyauto.qa.service.cloud.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.exceptions.CloudTransException;
import com.xyauto.qa.service.cloud.RUserService;

import feign.Param;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by shiqm on 2017/3/25.
 */
@Service
public class RUserServiceImpl extends RBaseServiceImpl<RUserService> implements RUserService {

    @Log
    private Logger logger;

    @Override
    public String getUserInfo(@Param("uid") String uid) {
        return this.proxy().getUserInfo(uid);
    }


    @Override
    public String getUserIdByNickName(@Param("nickName") String nickName) {
        try {
            String str = this.proxy().getUserIdByNickName(nickName);
            logger.info("微服务获取的信息：{}",str);
            JSONObject jsonObject = JSON.parseObject(str);
            if (CommonCons.Cloud_User_Flag.CODE_OK.equals(jsonObject.getInteger("code"))) {
                return jsonObject.getString("data");
            }
        } catch (Exception e) {
            throw new CloudTransException("通过微服务获取ID失败");
        }
        return null;
    }

    @Override
    public String getUserList(@Param("userids") String userids) {
        try {
            String str = this.proxy().getUserList(userids);
            logger.info("微服务获取的信息：{}",str);
            JSONObject jsonObject = JSON.parseObject(str);
            if (CommonCons.Cloud_User_Flag.CODE_OK.equals(jsonObject.getInteger("code"))) {
                return jsonObject.getString("data");
            }
        } catch (Exception e) {
            throw new CloudTransException("通过微服务获取ID失败");
        }
        return null;
    }


    
    
	@Override
	public String getUserNameList(@Param("uids") String uids) {
		 String str = this.proxy().getUserNameList(uids);
		 JSONObject jsonObject = JSON.parseObject(str);
         if (CommonCons.Cloud_User_Flag.CODE_OK.equals(jsonObject.getInteger("code"))) {        	 
        	 return jsonObject.getString("data");        	
         }
		return null;
	}

	
}
