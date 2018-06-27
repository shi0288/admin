package com.xyauto.qa.service;


import com.github.pagehelper.PageInfo;
import com.xyauto.qa.entity.Comment;
import com.xyauto.qa.util.QaResult;

public interface ICommentService {
	public PageInfo<Comment> getCommentList(Comment comment,String keyWord,Integer status,Integer userType);
	public QaResult del(Long comment_id);
	/**
	 * 根据主键id查询,不带附件信息
	 * @param comment_id
	 * @return
	 */
	Comment selectOne(Long comment_id);
}
