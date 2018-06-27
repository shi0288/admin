package com.xyauto.qa.service.impl;

import java.util.List;

import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.exceptions.LoginFailedException;
import com.xyauto.qa.service.AdminService;
import com.xyauto.qa.util.QaResult;
import com.xyauto.qa.webservice.EmployeeService;
import com.xyauto.qa.webservice.EmployeeServiceSoap;
import com.xyauto.qa.webservice.LoginResult;
import com.xyauto.system.entiy.User;
import com.xyauto.system.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shiqm on 2017/3/23.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Log
    private Logger logger;
    @Autowired
    private IUserService userService;
    @Override
    public User login(String account, String password) {
    	logger.info("有用户登录--------{}", account);
        if(StringUtils.isEmpty(account)){
            throw new LoginFailedException("用户名不能为空！");
        }
        if(StringUtils.isEmpty(password)){
            throw new LoginFailedException("密码不能为空！");
        }
        LoginResult loginResult = null;
        	
        try {        	
        	//查询用户是否存在，已经是否是域账户(密码为null)
        	User user=new User();
			user.setUserName(account);
			List<User> userList=userService.selectUser(user);
			if (userList==null||userList.size()==0) {	
				logger.info("您无权限访问，请联系问答组管理员！");
				throw new LoginFailedException("您无权限访问，请联系问答组管理员！");
			}else if (StringUtils.isBlank(userList.get(0).getPassword())) {
				EmployeeService employeeService = new EmployeeService();
	            EmployeeServiceSoap employeeServiceSoap = employeeService.getEmployeeServiceSoap();
	            loginResult = employeeServiceSoap.login(account, password);
	            if (!"Success".equals(loginResult.value())) {
	            	logger.info("用户名或密码错误");
	                throw new LoginFailedException("用户名或密码错误！");
	            }
			}else{
				QaResult result= userService.login(account, password);
				if(result.getCode()==ErrorCode.LOGIN_ERR.FAILED){
					logger.info("1111用户名或密码错误");
					throw new LoginFailedException("用户名或密码错误！");
				}
			}           
        } catch (Exception e) {
        	e.printStackTrace();
            throw new LoginFailedException("登录数据无法获取！");
        }          
        User user=new User();
        user.setUserName(account);
        
        return userService.selectUser(user).get(0);
    }
    
}
