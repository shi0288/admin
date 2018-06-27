package com.xyauto.qa.mapper;

import java.util.List;

import com.xyauto.qa.entity.UserApply;

public interface UserApplyMapper {
	public List<UserApply> selectActiveUserList(UserApply userApply);
	public UserApply selectActiveUser(Long id);
	public void updateActiveUser(UserApply userApply);
	
}
