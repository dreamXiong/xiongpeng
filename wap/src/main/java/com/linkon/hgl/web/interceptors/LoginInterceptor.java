
package com.linkon.hgl.web.interceptors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.ActionUrlRecordDto;
import com.liguo.hgl.proxydao.dto.KeyValueDto;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.util.CommonUtil;
import com.liguo.hgl.proxydao.util.StringUtil;
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
        TbWebUser user = (TbWebUser)session.getAttribute(HglContants.SESSION_KEY);
        
        /*如果用户访问的URL在tb_web_permission表中,但是用户没有访问权限，则跳转到license页面*/
//    	String actionURL = request.getServletPath();					
//   		if(user!=null && !webPermissionService.getUserLicense(user,actionURL)){  	
//   			response.sendRedirect(request.getContextPath() + "/license");
//   			return false;		    	
//   		}
		
		if (user == null || StringUtil.isBlank(user.getUserName())) {
			String type = request.getHeader("X-Requested-With");
			//判断是否ajax请求,是ajax请求返回头信息
			if("XMLHttpRequest".equalsIgnoreCase(type)) {
				response.setHeader("sessionstatus", "timeout");
			}else{
				//记录拦截的actionURL
				ActionUrlRecordDto record = new ActionUrlRecordDto();
				record.setActionUrl(request.getRequestURI());
				//循环遍历请求的参数添加到list
				Map<String, String[]> parameterMap = request.getParameterMap();
				if (parameterMap != null && parameterMap.size() > 0) {
					List<KeyValueDto> list = new ArrayList<KeyValueDto>();
					Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
					Iterator<Entry<String, String[]>> iterator = entrySet.iterator();
					while (iterator.hasNext()) {
						Entry<String, String[]> entry = iterator.next();
						for (String value : entry.getValue()) {
							KeyValueDto keyValue = new KeyValueDto();
							keyValue.setKey(entry.getKey());
							keyValue.setValue(value);
							list.add(keyValue);
						}
					}
					record.setList(list);
				}
				//把actionURL和参数保存到session中
				session.setAttribute(HglContants.ACTION_URL_RECORD, record);
	
				//判断是否微信浏览器
				if(CommonUtil.isWeiXinBrowser(request)){
					response.sendRedirect(HglContants.WEIXIN_OPEN_URL.replace("APPID", HglContants.WEIXIN_APP_ID).replace("REDIRECT_URI", CommonUtil.urlEncodeUTF8(HglContants.WEIXIN_AUTOLOGIN_CALL_METHOD)).replace("SCOPE", HglContants.WEIXIN_SNSAPI_BASE).replace("STATE", "autoLoginFlag"));
				}else{
					response.sendRedirect(request.getContextPath() + "/login/");
				}
			}
			return false;
		}else if(user != null && !user.getState().toString().equals(HglContants.WEB_AUTH_STATUS1+"")){
			response.sendRedirect(request.getContextPath() + "/login/");
		}
		
        return super.preHandle(request, response, handler);
    }

}
