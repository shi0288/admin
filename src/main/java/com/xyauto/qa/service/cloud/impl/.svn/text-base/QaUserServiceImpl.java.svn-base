package com.xyauto.qa.service.cloud.impl;

import java.util.Map;

import com.xyauto.qa.service.cloud.QaUserService;

import feign.Param;

import org.springframework.stereotype.Service;

/**
 * Created by shiqm on 2017/3/28.
 */
@Service
public class QaUserServiceImpl extends QBaseServiceImpl<QaUserService> implements QaUserService {

    @Override
    public String getUserDesc(@Param("uid") String uid) {
        return this.proxy().getUserDesc(uid);
    }


    @Override
    public String createQuestionNoPic(@Param("category_id") String category_id, @Param("content") String content, @Param("uid") String uid, @Param("cars") String cars, @Param("special") String special, @Param("city_id") String city_id, @Param("attachs") String attachs, @Param("is_good") Integer is_good) {
        return this.proxy().createQuestionNoPic(category_id, content, uid, cars, special, city_id, attachs, is_good);
    }


	@Override
	public String createAnswerNoPic(@Param("question_id")    Long question_id,
                               @Param("content")    String content,
                               @Param("attachs")    String attachs,
                               @Param("city_id")    String city_id,
                               @Param("reply_uid")  Long  reply_uid,
                               @Param("reply_answer_id")  Long reply_answer_id ,
                               @Param("uid")   Long uid) {
		return this.proxy().createAnswerNoPic(question_id, content, attachs, city_id, reply_uid, reply_answer_id, uid);
	}


	@Override
	public String deleteAnswer(@Param("answer_id")Long answer_id,@Param("uid")Long uid) {
		// TODO Auto-generated method stub
		return this.proxy().deleteAnswer(answer_id, uid);
	}


	@Override
	public String deleteQuestion(@Param("question_id")Long question_id,@Param("uid")Long uid) {
		// TODO Auto-generated method stub
		return this.proxy().deleteQuestion(question_id,uid);
	}


	@Override
	public String updateQuestion(@Param("question_id")Long question_id) {
		// TODO Auto-generated method stub
		return this.proxy().updateQuestion(question_id);
	}


	@Override
	public String categoryList() {
		// TODO Auto-generated method stub
		return this.proxy().categoryList();
	}


	@Override
	public String createQuestionNoPic(@Param("category_id") String category_id, 
			@Param("content") String content, @Param("uid") String uid, @Param("cars") String cars, 
			@Param("special") String special, @Param("city_id") String city_id, 
			@Param("attachs") String attachs, @Param("is_good") Integer is_good, String activity,
			@Param("type")Integer type ,@Param("cover")String cover ,
			@Param("is_html")Integer is_html ,@Param("title")String title ,
			@Param("distribute") String distribute) {
        String str= this.proxy().createQuestionNoPic(category_id, content, uid, cars, special, 
        		city_id, attachs, is_good,activity,type,cover,is_html,title,distribute);
         return str;
    }


	@Override
	public String createQuestion(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  this.proxy().createQuestion(map);
	}

	@Override
	public String addDistribute(@Param("uid") String uid) {
		String result=this.proxy().addDistribute(uid);
		return result;
	}


	@Override
	public String delDistribute(@Param("uid") Long uid) {
		// TODO Auto-generated method stub
		String result=this.proxy().delDistribute(uid);
		return result;
	}


	@Override
	public String createComment(Long answer_id, String content, String attachs,
			Long uid, Long reply_uid, Long reply_comment_id) {
		// TODO Auto-generated method stub
		return this.proxy().createComment(answer_id, content, attachs, uid, reply_uid, reply_comment_id);
	}
	
	
}
