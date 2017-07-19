
package com.linkon.hgl.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.service.TbWebPermissionService;
import com.liguo.hgl.service.TbWebRoleService;

/**
 * 类的功能描述<br>
 *
 * @author huliang
 * @FileName LoginInterceptor.java<br>
 * @Language Java<br>
 * @date 2016-04-12<br>
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private TbWebRoleService webRoleService;
	
	@Autowired
	private TbWebPermissionService webPermissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object objSession =session.getAttribute(HglContants.SESSION_KEY);
        
        if(objSession instanceof TbWebUser){
        	TbWebUser user = (TbWebUser)objSession;
        	if(user != null && (HglContants.WEB_AUTH_STATUS == user.getState() || HglContants.WEB_AUTH_STATUS2 == user.getState())){
        		 response.sendRedirect(request.getContextPath() + "/login/shop"); 
        	     return false;
        	}
        	
        	/*如果用户访问的URL在tb_web_permission表中,但是用户没有访问权限，则跳转到license页面*/
        	String actionURL = request.getServletPath();
        	if(user!=null && !webPermissionService.getUserLicense(user, actionURL)){  	
       			response.sendRedirect(request.getContextPath() + "/license");
       			return false;		    	
       		}
       		
            return super.preHandle(request, response, handler);
        }
        String type = request.getHeader("X-Requested-With");
		//判断是否ajax请求,是ajax请求返回头信息
		if("XMLHttpRequest".equalsIgnoreCase(type)) {
			response.setHeader("sessionstatus", "timeout");
		}else{
			response.sendRedirect(request.getContextPath() + "/login/index"); 
		}
        return false;
    }
}
