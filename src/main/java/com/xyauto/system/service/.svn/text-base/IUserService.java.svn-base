package com.xyauto.system.service;

import java.util.List;

import com.xyauto.qa.service.ABaseService;
import com.xyauto.qa.util.QaResult;
import com.xyauto.system.entiy.User;



public interface IUserService extends ABaseService<User, Long>{
	int insert(User user);
	QaResult login(String userName,String password);
	List<User> selectUser(User user);
}
