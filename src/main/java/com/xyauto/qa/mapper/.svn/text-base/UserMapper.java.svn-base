package com.xyauto.qa.mapper;

import com.xyauto.qa.entity.User;
import com.xyauto.qa.util.BaseMapper;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * Created by shiqm on 2017/3/23.
 */
public interface UserMapper extends BaseMapper<User> {

   int updateUserStatus(User user);

   User getUserById(Long uid);

   int updateUser(User user);

   int updateUserType(User user);

   User getUserBrandsById(Long uid);
   //查询发布问题数最多的用户
   List<User> selectUserByQuestioncount(Map<String, Object> map);
   //查询回复数对多的用户
   List<User> selectUserByAnswercount(Map<String, Object> map);
   /**
    * 恢复禁言时间到达的用户身份
    * @return
    */
   int recoverUser();
   
   /**
    * 更新问题数量
    * @param uid
    * @return
    */
   int updateQuestionCount(Long uid);
   
   int updateAnswerCount(Long uid);
   
   /**
    * 查询头像为NULL的车顾问信息
    * @return
    */
   List<User> selectAviser();
   List<Long> getUserIdByType(@Param("array")Object[] array, @Param("userType")int userType);

}
