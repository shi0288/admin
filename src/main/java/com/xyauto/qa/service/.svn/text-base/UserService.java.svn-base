package com.xyauto.qa.service;

import com.github.pagehelper.PageInfo;
import com.xyauto.qa.entity.City;
import com.xyauto.qa.entity.Series;
import com.xyauto.qa.entity.User;
import com.xyauto.qa.entity.UserApply;

import java.util.List;
import java.util.Map;

/**
 * Created by shiqm on 2017/3/23.
 */
public interface UserService extends ABaseService<User,Long>{

    PageInfo getUserList(User user);

    int frozenExpert(User user);

    int thawUser(User user);

    int frozenUserAndExpert(User user,boolean is);
    /**
     * 封禁用户
     * @param uid：用户id
     * @param time：封禁时间，以天为单位
     * @param delQue：是否删除问题
     * @param delAns：是否删除回复
     * @param content：封禁原因
     * @return
     */
    int frozenUserAndExpert(Long uid,Integer time,Boolean delQue,
    		Boolean delAns,String content);

    User getUserAndInfo(Long uid);

    List<User>  getUserByBlock(String name);

    void saveUser(User user,String[] seriesIds);

    void updateUserType(User user);
    
    /**
     * 根据用户昵称查询用户信息，同步到问答库
     * @param nickName
     * @return
     */
    Long getUserIdByNickName(String nickName);
    
    /**
     * 查询问题或回答数最多的用户--- 根据时间段查询---必须限定时间段
     * @param startTime
     * @param endTime
     * @param type  answer:回复，question：问题
     * @return
     */
    List<User> selectUserByQueCountOrAnsCount(String startTime, String endTime,String type);
    /**
     * 根据用户id查询用户昵称，批量接口；
     * @param uids
     * @return key:uid   value:nick_name
     */
    Map<Long, String> selectNickName(String uids);
    /**
     * 恢复禁言时间到达的用户身份
     * @return
     */
    int recoverUser();
    
    City selectCityByuid(Long uid);
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
    
    void updateUserCar(List<Series> seriesList,Long uid);
    
    List<Long> getUserIdByType(Object[] array,int userType);
    /**
     * 查询参加活动的用户
     * @param userApply
     * @return
     */
    PageInfo<UserApply> selectActiveUserList(UserApply userApply);
    
    UserApply selectActiveUser(Long id);
    /**
     * 仅更新状态
     * @param userApply
     */
    void updateActiveUser(UserApply userApply);
}
