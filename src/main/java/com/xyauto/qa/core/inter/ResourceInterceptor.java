package com.xyauto.qa.core.inter;

import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.util.HttpUtils;
import com.xyauto.system.vo.UserPowerVo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by shiqm on 2017/3/23.
 */

public class ResourceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String base = HttpUtils.getBasePath(httpServletRequest);
        String jsPath = base + "/script";
        String cssPath = base + "/style";
        String imgPath = base + "/images";
        httpServletRequest.setAttribute("base", base);
        httpServletRequest.setAttribute("jsPath", jsPath);
        httpServletRequest.setAttribute("cssPath", cssPath);
        httpServletRequest.setAttribute("imgPath", imgPath);
        String prefix = httpServletRequest.getRequestURI();
        HttpSession httpSession = httpServletRequest.getSession();
        if( httpSession.getAttribute(CommonCons.Session_Flag.USERINFO)!=null){
            try {
                List<UserPowerVo> menuParent = (List<UserPowerVo>) httpSession.getAttribute(CommonCons.Session_Flag.MENU_PARENT);
                for(UserPowerVo tempParentMenu: menuParent) {
                	String url=tempParentMenu.getUrl().split("\\?")[0];
                    if(url.equals(prefix)){
                        httpSession.setAttribute(CommonCons.Session_Flag.CUR_PARENT,tempParentMenu.getPowerId());
                    }
                }
            }catch (Exception e){
            }
        }
        httpServletRequest.setAttribute("getRequestUrl", prefix);

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
