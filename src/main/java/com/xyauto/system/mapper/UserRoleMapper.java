package com.xyauto.system.mapper;

import java.util.List;

import com.xyauto.qa.util.BaseMapper;
import com.xyauto.system.entiy.User;


public interface UserRoleMapper extends BaseMapper<User>{
	int insertUser(User user);
	List<User> selectUser(User user);
}
