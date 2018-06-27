package com.xyauto.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.service.impl.ABaseServiceImpl;
import com.xyauto.qa.util.QaResult;
import com.xyauto.system.entiy.User;
import com.xyauto.system.mapper.UserRoleMapper;
import com.xyauto.system.service.IUserService;
import com.xyauto.system.util.EncryptMd5;

@Service("userSystemService")
public class UserServiceImpl extends ABaseServiceImpl<User, Long> implements IUserService{
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Override
	public int insert(User user) {
		try {
			String password=user.getPassword();
			if (StringUtils.isNotBlank(password)) {
				user.setPassword(EncryptMd5.md5Encode(password));
			}			
			if (user.getStatus()==null) {
				user.setStatus((short)1);
			}
			if (user.getRole()==null) {
				user.setRole((short)99);
			}
			int count=userRoleMapper.insertUser(user);
			if (count<=0) {
				logger.error("插入用户失败{}："+user.getUserName());
			}
			logger.info("插入用户成功："+user.getUserName());
		} catch (Exception e) {
			logger.error("插入用户失败{}："+e);
		}		
		return 0;
	}
	@Override
	public QaResult login(String userName, String password) {
		try {
			User user=new User();
			user.setUserName(userName);
			user.setPassword(password);
			List<User> loginUser=this.selectUser(user);
			if (loginUser.size()!=1) {
				return new QaResult(ErrorCode.LOGIN_ERR.FAILED,"用户或密码错误");
			}
		} catch (Exception e) {
			logger.error("登录失败，用户名是："+userName+"错误信息是："+e);
			return new QaResult(ErrorCode.LOGIN_ERR.FAILED,"用户或密码错误");
		}		
		return new QaResult(ErrorCode.OK,"登录成功");
	}
	@Override
	public List<User> selectUser(User user) {	
		try {
			if (user!=null&&user.getPassword()!=null) {
				user.setPassword(EncryptMd5.md5Encode(user.getPassword()));
			}
			logger.info("用户名是"+user.getUserName());
			List<User> list=userRoleMapper.selectUser(user);
			if (list.size()<=0) {
				return null;
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
}
