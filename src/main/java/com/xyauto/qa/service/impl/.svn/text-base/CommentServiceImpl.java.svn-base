package com.xyauto.qa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.xyauto.qa.Constants;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.entity.Attach;
import com.xyauto.qa.entity.Comment;
import com.xyauto.qa.entity.User;
import com.xyauto.qa.mapper.CommentMapper;
import com.xyauto.qa.service.ICommentService;
import com.xyauto.qa.service.UserService;
import com.xyauto.qa.util.QaResult;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private UserService userService;
	@Autowired
    private Constants constants;
    @Log
    protected Logger logger;

	
	@Override
	public PageInfo<Comment> getCommentList(Comment comment, String keyWord,Integer status,
			Integer userType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid",comment.getUid() );
		map.put("answerId",comment.getAnswer_id() );
		map.put("startTime", comment.getCreated_at());
		map.put("endTime", comment.getUpdated_at());
		map.put("keyWord", keyWord);
		map.put("status", status);	
		map.put("source", comment.getSource());
		if (userType != null && userType == 9) {
			map.put("isOfficial", userType);
		} else {
			map.put("userType", userType);
		}
		return getCommentListByMapParam(comment, map);
	}
	PageInfo<Comment> getDelCommentListByMapParam(Comment comment, Map map){
		return null;
	}
	PageInfo<Comment> getCommentListByMapParam(Comment comment, Map map){
		int total = commentMapper.getCommentCountByMapParam(map);
		if (total <= 0) {
			return null;
		}				
		List<Comment> list=commentMapper.getCommentListByMapParam(map);		
		return getPageInfo(total,list,comment);
	}
	
	PageInfo<Comment> getPageInfo(int total,List<Comment> list,Comment comment){
		PageInfo<Comment> pageInfo =new PageInfo<Comment>();
		int num=comment.getPageNum();
		int size=comment.getPageSize();		
		if (list!=null&&list.size()>0) {
			int endNum=num*size;
			if (endNum>list.size()) {
				endNum=list.size();
			}
			pageInfo.setList(list.subList((num-1)*size, endNum));
			pageInfo.setTotal(list.size());
			int pages=list.size()/size;
			if (list.size()%size>0) {
				pages++;
			}
			pageInfo.setPages(pages);
		}		
		pageInfo.setPageNum(num);
		pageInfo.setPageSize(size);
		for (Comment comment2 : pageInfo.getList()) {
			User user = userService.getUserAndInfo(comment2.getUid());
			comment2.setUser(user);
			List<Attach> attachList = comment2.getAttaches();
			for (Attach attach : attachList) {
				if (attach.getFileType() == 0) {
					String pathUrl = attach.getFile();
					if (pathUrl.toLowerCase().startsWith("http://")
							|| pathUrl.toLowerCase().startsWith("https://")) {
					}
					if (pathUrl.startsWith("group1")) {
						pathUrl = constants.getAvatarGroup1Root() + pathUrl;
					}
					if (pathUrl.startsWith("group2")) {
						pathUrl = constants.getAvatarGroup2Root() + pathUrl;
					}
					attach.setFile(pathUrl);
				}
			}
		}
		return pageInfo;
	}
	@Override
	public QaResult del(Long comment_id) {
		// TODO Auto-generated method stub
		try {
			commentMapper.del(comment_id);
		} catch (Exception e) {
			// TODO: handle exception			
			logger.info("删除评论异常：");
			e.printStackTrace();
			return new QaResult(ErrorCode.OVER,"删除评论失败");
		}
		return new QaResult();
	}
	@Override
	public Comment selectOne(Long comment_id) {
		// TODO Auto-generated method stub
		Comment comment=commentMapper.selectOne(comment_id);
		User user = userService.getUserAndInfo(comment.getUid());
		comment.setUser(user);
		return comment;
		
	} 
	

}
