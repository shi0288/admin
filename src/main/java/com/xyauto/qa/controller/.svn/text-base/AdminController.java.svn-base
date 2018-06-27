package com.xyauto.qa.controller;

import java.util.List;
import java.util.Map;

import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.LogAction;
import com.xyauto.qa.core.annotation.QaLog;
import com.xyauto.qa.service.AdminService;
import com.xyauto.qa.util.QaResult;
import com.xyauto.system.entiy.User;
import com.xyauto.system.entiy.UserPower;
import com.xyauto.system.service.IUserPowerService;
import com.xyauto.system.vo.UserPowerVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shiqm on 2017/3/23.
 */

@RestController
public class AdminController extends AjaxBaseController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private IUserPowerService userPowerService;

    @QaLog(action = LogAction.USER_LOGIN, cmd = "login")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public QaResult login(String account, String password) {
        User user = adminService.login(account, password);
        this.setSession(CommonCons.Session_Flag.ACCOUNT, account);
        this.setSession(CommonCons.Session_Flag.USERINFO, user);
        /**
         * 查询用户分配的菜单权限：1、查看是否拥有全部菜单
         */
        //查询父级菜单
        List<UserPowerVo> menuParent = userPowerService.selectParmentMenu(user.getUid());
        //查询所有子集菜单
        List<UserPowerVo> menuChildren = userPowerService.selectPower(user.getUid());
        this.setSession(CommonCons.Session_Flag.MENU_PARENT, menuParent);
        this.setSession(CommonCons.Session_Flag.MENU_CHILDREN, menuChildren);
        //设置父菜单url为第一个子菜单url
        for(UserPowerVo tempParentMenu: menuParent){
            for (UserPowerVo tempChildrenMenu: menuChildren) {
                if(StringUtils.isEmpty(tempParentMenu.getUrl())){
                    if(tempParentMenu.getPowerId().equals(tempChildrenMenu.getParentId())){
                        tempParentMenu.setUrl(tempChildrenMenu.getUrl());
                    }
                }else{
                    break;
                }
            }
        }
        String url="";
        for (UserPowerVo userPowerVo : menuParent) {
			if (userPowerVo.getPowerName().equals("内容管理")) {
				url=userPowerVo.getUrl();
			}
		}
        return new QaResult(url);
    }




}
