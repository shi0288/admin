package com.xyauto.qa.service.cloud;


import java.util.Map;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import org.springframework.stereotype.Component;

/**
 * Created by shiqm on 2017/3/28.
 */

@Component
public interface QaUserService {

    /**
     * 获取用户详情
     * @return
     */
    @RequestLine("GET /v1.0/user/{uid}")
    String getUserDesc(@Param("uid")String uid);


    /**
     * 发起提问
     *
     * @return
     */
    @RequestLine("POST /v1.0/question/add1?category_id={category_id}&content={content}&uid={uid}&cars={cars}&special={special}&city_id={city_id}&is_good={is_good}&attachs={attachs}")
    String createQuestionNoPic(@Param("category_id") String category_id,
                               @Param("content") String content,
                               @Param("uid") String uid,
                               @Param("cars") String cars,
                               @Param("special") String special,
                               @Param("city_id") String city_id,
                               @Param("attachs") String attachs,
                               @Param("is_good") Integer is_good);
    
    @RequestLine("POST /v1.0/question/add1?category_id={category_id}&content={content}&uid={uid}"
    		+ "&cars={cars}&special={special}&city_id={city_id}&is_good={is_good}&attachs={attachs}"
    		+ "&activity={activity}&type={type}&cover={cover}&is_html={is_html}&title={title}&distribute={distribute}")
    String createQuestionNoPic(@Param("category_id") String category_id,
                               @Param("content") String content,
                               @Param("uid") String uid,
                               @Param("cars") String cars,
                               @Param("special") String special,
                               @Param("city_id") String city_id,
                               @Param("attachs") String attachs,
                               @Param("is_good") Integer is_good,
                               @Param("activity") String activity,
                               @Param("type")Integer type ,
                               @Param("cover")String cover ,
                               @Param("is_html")Integer is_html ,
                               @Param("title")String title ,
                               @Param("distribute") String distribute
                               );
    @RequestLine("POST /v1.0/question/add1")
    String createQuestion(@QueryMap Map<String, Object> map );


    @RequestLine("POST /v1.0/answer/add1?question_id={question_id}&content={content}&attachs={attachs}&city_id={city_id}&reply_uid={reply_uid}&reply_answer_id={reply_answer_id}&uid={uid}")
    String createAnswerNoPic(@Param("question_id") Long question_id,
                               @Param("content") String content,
                               @Param("attachs") String attachs,
                               @Param("city_id") String city_id,
                               @Param("reply_uid") Long reply_uid,
                               @Param("reply_answer_id") Long reply_answer_id,
                               @Param("uid") Long uid);
    
    @RequestLine("GET /v1.0/answer/del?answer_id={answer_id}&uid={uid}")
    String deleteAnswer(@Param("answer_id")Long answer_id,@Param("uid")Long uid);
    
    
    @RequestLine("GET /v1.0/question/del?question_id={question_id}&uid={uid}")
    String deleteQuestion(@Param("question_id")Long question_id,@Param("uid")Long uid);

    /**
     * 更新索引
     * @param question_id
     * @return
     */
    @RequestLine("GET /v1.1/question/index/update?question_id={question_id}")
    String updateQuestion(@Param("question_id")Long question_id);

    /**
     * 获取问题分类
     * @return
     */
    @RequestLine("GET /v1.1/category/list")
    String categoryList();

    /**
     * 用户加入推送
     */
    @RequestLine("GET /v1.1/user/distribute/add?uid={uid}")
    String addDistribute(@Param("uid") String uid);
    
    /**
     * 用户加入推送
     */
    @RequestLine("GET /v1.1/user/distribute/del?uid={uid}")
    String delDistribute(@Param("uid") Long uid);
    
    /**
     * 创建评论
     * @param question_id
     * @param content
     * @param attachs
     * @param city_id
     * @param reply_uid
     * @param reply_answer_id
     * @param uid
     * @return
     */
    @RequestLine("POST /v1.0/comment/add?answer_id={answer_id}&content={content}&attachs={attachs}&uid={uid}&reply_uid={reply_uid}&reply_comment_id={reply_comment_id}")
    String createComment(@Param("answer_id") Long answer_id,
                               @Param("content") String content,
                               @Param("attachs") String attachs,
                               @Param("uid") Long uid,
                               @Param("reply_uid") Long reply_uid,
                               @Param("reply_comment_id") Long reply_comment_id
                               );

}
