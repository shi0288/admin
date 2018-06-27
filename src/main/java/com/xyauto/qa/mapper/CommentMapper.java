package com.xyauto.qa.mapper;

import java.util.List;
import java.util.Map;

import com.xyauto.qa.entity.Comment;
import com.xyauto.qa.util.BaseMapper;
import com.xyauto.qa.util.QaResult;

public interface CommentMapper {
	int getCommentCountByMapParam(Map<String, Object> map);
	List<Comment> getCommentListByMapParam(Map<String, Object> map);
	
	int del(Long comment_id);
	Comment selectOne(Long comment_id);
	
}
