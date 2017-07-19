/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.linkon.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.service.TbAdminPermissionService;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName LoginInterceptor.java<br>
 * @Language Java<br>
 * @date 2016-01-12<br>
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private TbAdminPermissionService adminPermissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        TbAdminUserDto tbAdminUser = (TbAdminUserDto)session.getAttribute(HglContants.SESSION_KEY);
       /* String userName = tbAdminUser.getName();*/
        
        if(tbAdminUser == null){
            response.sendRedirect(request.getContextPath() + "/login/index");
            return false;
        }
        
        String actionURL = request.getServletPath();
        if(tbAdminUser!=null && !adminPermissionService.getAdminUserLicense(tbAdminUser, actionURL)){
        	response.sendRedirect(request.getContextPath()+"/license");
        	return false;
        }
       /* if(StringUtil.isBlank(userName)){
            response.sendRedirect(request.getContextPath() + "/login/index");
            return false;
        }*/
        return super.preHandle(request, response, handler);
    }
}
