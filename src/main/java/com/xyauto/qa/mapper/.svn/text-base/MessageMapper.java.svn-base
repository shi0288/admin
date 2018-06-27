package com.xyauto.qa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xyauto.qa.entity.Message;

public interface MessageMapper {
    int add(@Param(value = "uid") long uid, @Param(value = "type") int type, @Param(value = "content") String content);
    /**
     * 查询高能推送消息
     * @param uid
     * @param time：时间必传
     * @param type：类型必传
     * @return
     */
    List<Message> selectGroupUid(@Param(value = "uid")Long uid, @Param(value = "time")String time,@Param(value = "type")Integer type);
}
