package com.xyauto.qa.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.util.HttpUtils;
import com.xyauto.qa.util.QaResult;
import com.xyauto.system.entiy.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by shiqm on 2017/3/23.
 */

@WebFilter(filterName="adminFilter",urlPatterns="/user/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.getSession().setMaxInactiveInterval(3600);
        String admin = (String) request.getSession().getAttribute(CommonCons.Session_Flag.ACCOUNT);
        if (admin == null) {
            response.sendRedirect(HttpUtils.getBasePath(request) + "/");
        } else {
        	if (!verifyAuthority(servletRequest)) {
        		ObjectMapper mapper = new ObjectMapper();  
            	response.setCharacterEncoding("utf-8");  
            	response.setContentType("application/json; charset=utf-8");  
                response.getWriter().write(mapper.writeValueAsString("权限不足")); 
			}else{
	            filterChain.doFilter(request, response);
			}        	 
        }
        
    }

    @Override
    public void destroy() {

    }
    
    static boolean  verifyAuthority(ServletRequest servletRequest){
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
    	User user =(User) request.getSession().getAttribute(CommonCons.Session_Flag.USERINFO);
    	String str1=request.getRequestURI();
    	String [] arrayUrl=str1.split("\\/");
    	if (user!=null&&user.getRole()==90) {
    		if (!(arrayUrl[2].equals("question")
    				||arrayUrl[2].equals("answer")
    				||arrayUrl[2].equals("biData"))) {
    			return false;
    		}
		}    	
    	return true;
    }
}
