package com.linkon.hgl.web.action;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.proxydao.dto.IntegralDetailedDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbIntegralDetailedService;
import com.liguo.hgl.service.TbIntegralService;
import com.liguo.hgl.service.TbSystemConfigService;

@Controller
@RequestMapping("/sign")
public class SignController extends IBaseController {
	
	@Autowired
    protected TbIntegralDetailedService tbIntegralDetailedService;
	
	@Autowired
    protected TbIntegralService tbIntegralService;
	
	@Autowired
    protected TbSystemConfigService tbSystemConfigService;
	/**
	 * 初始化方法
	 */
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Integer signInregral = tbSystemConfigService.getSignInregral();
		model.addAttribute("signInregral", signInregral);
		//0：用户当天未签到 1：登录用户异常获取不到userID 2:用户当天已经签到
		int canSign=0;
		WebUserDto loginUser = this.getLoginUser();
		//未登录不能签到
		if(loginUser.getId()==null){
			canSign=1;
			model.addAttribute("canSign", canSign);
			return;
		}
		//根据userId 获得当天的签到积分记录
		IntegralDetailedDto integralDetailed = tbIntegralDetailedService.selectTodaySignByUserId(loginUser.getId());
		if(integralDetailed!=null && integralDetailed.getIntegral()!=null){
			canSign=2;
		}
		TbIntegral tbIntegral = tbIntegralService.selectByPrimaryKey(this.getIntegralId());
		model.addAttribute("integral", tbIntegral.getIntegral());
		model.addAttribute("canSign", canSign);
	}

	/**
	 * 产品列表显示跳转
	 */
	@RequestMapping(value = "/sign")
	@ResponseBody
	public Map<String,Object> sign(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		WebUserDto loginUser = this.getLoginUser();
		//未登录不能签到
		if(loginUser.getId()==null){
			map.put("code", 0);
		 	map.put("msg", "签到失败！");
			return map;
		}
		boolean flag = tbIntegralDetailedService.signByUserId(loginUser.getId());
	 	if(true){
			map.put("code", 1);
		 	map.put("msg", "签到成功！");
	 	}else{
		 	map.put("code", 0);
		 	map.put("msg", "签到失败！");
	 	}
	 	
		return map;
	}
	
	@Override
	public String doSearchResult() {
		return null;
	}


	
    
}
