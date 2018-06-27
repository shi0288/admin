package com.xyauto.qa.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xyauto.qa.entity.Blocks;

import java.util.List;

/**
 * Created by shiqm on 2017/3/22.
 */
public interface BlocksService extends ABaseService<Blocks,Integer> {

    List getPcBannerList();
    List getMobileBannerList();
    JSONObject getExpertPicList();
    List getPcNoticeList();
    JSONArray getBlockByName(String name);    
    JSONObject getTopic();
    List getInformationList();
    JSONObject getAdver1();
    JSONObject getAdver2();
    List getUserExpertList();
    List getPopularTopicList();
    List getOneInformationList();
    List recommendTopicList();
    
    List getList(String type);
    
    void saveBlock(String content,String type);


}
