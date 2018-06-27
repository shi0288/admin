package com.xyauto.qa.service.cloud;


import feign.Headers;
import feign.Param;
import feign.RequestLine;

import org.springframework.stereotype.Component;

/**
 * Created by shiqm on 2017/3/25.
 */

@Component
public interface RUserService {

    /**
     * 获取用户信息
     * @param uid
     * @return
     */
    @RequestLine("GET /userinfo/{uid}")
    String getUserInfo(@Param("uid") String uid);



    /**
     * 根据昵称获取用户ID
     * @param nickName
     * @return
     */
    @RequestLine("GET /userinfo/useridbynick/{nickName}")
    String getUserIdByNickName(@Param("nickName") String nickName);




    /**
     * 根据昵称获取用户ID
     * @param userids
     * @return
     */
    @RequestLine("POST /userinfo/userlist?userids={userids}")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    String getUserList(@Param("userids") String userids);
    
    /**
     * 根据用户id获取用户昵称---批量
     */
    @RequestLine("GET /userinfo/getnickbyids/{uids}")
    String getUserNameList(@Param("uids") String uids);
    
    
    


}
