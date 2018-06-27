package com.xyauto.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xyauto.qa.controller.AjaxBaseController;
import com.xyauto.qa.util.QaResult;
import com.xyauto.system.entiy.User;
import com.xyauto.system.entiy.UserPower;
import com.xyauto.system.service.IUserPowerService;
import com.xyauto.system.service.IUserService;
import com.xyauto.system.vo.UserPowerVo;

@Controller
@RequestMapping("user/system")
public class UserRoleController extends AjaxBaseController{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserPowerService userPowerService;
	
	@RequestMapping("userList")
	public void userList(User user,ModelMap modelMap ){
		
		List<User> list=userService.selectUser(user);
		modelMap.put("userlist", list);
		modelMap.put("user", user);
	}
	@RequestMapping("toAddUser")
	public void toAddUser(){
		
	}
	@RequestMapping("addUser")
	@ResponseBody
	public QaResult addUser(User user,Integer source){
		if (source!=null&&source==1) {
			user.setPassword(null);
		}
		user.setRole((short)90);
		userService.insert(user);
		return new QaResult();
	}
	@RequestMapping("toEditUserOperate")
	public void toEditUserOperate(Long uid,ModelMap modelMap){
		//查询所有菜单以及分配的菜单
		List<UserPowerVo> userPowerList= userPowerService.selectMenuPowerByUid(uid);
		modelMap.put("userPowerList", userPowerList);
		modelMap.put("uid", uid);
	}
	
	@RequestMapping("saveUserPower")
	@ResponseBody
	public QaResult saveUserPower(Long uid,String powerIds){
		String [] powerId=powerIds.split(",");
		userPowerService.saveUserPower(uid,powerId,null);
		return new QaResult();
	}
	//查看用户权限
	public void selectPower(User user){
		//查询用户所分配的菜单
		userPowerService.selectMenuPowerByUid(user.getUid());
	}
		
	@ModelAttribute("urlObj")
    public JSONObject getUrlObj() {
        return super.getUrlObj();
    }
	
}
