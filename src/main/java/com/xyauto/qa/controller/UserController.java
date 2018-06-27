package com.xyauto.qa.controller;

import com.github.pagehelper.PageInfo;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.ConvertCons;
import com.xyauto.qa.entity.AdminLogs;
import com.xyauto.qa.service.AdminLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shiqm on 2017/3/23.
 */

@Controller
@RequestMapping("user")
public class UserController extends ABaseController{


    @Autowired
    private AdminLogsService adminLogsService;


    /**
     * 主页
     */
    @RequestMapping("home")
    public void home(){}



    /**
     * 日志
     */
    @RequestMapping("adminLog")
    public void adminLog(ModelMap modelMap, AdminLogs adminLogs){
        if("".equals(adminLogs.getUsername())){
            adminLogs.setUsername(null);
        }
        adminLogs.setPageSort("id desc");
        PageInfo pageInfo = adminLogsService.findByPager(adminLogs);
        modelMap.put("pager", pageInfo);
        modelMap.put("adminLogs", adminLogs);
        modelMap.put("moduleMap", ConvertCons.LogModuleCover.getMap());
        
    }



    /**
     * 退出登录
     */
    @RequestMapping("loginout")
    public String loginout(){
        this.removeSession(CommonCons.Session_Flag.ACCOUNT);
        return "redirect:/";
    }


}
