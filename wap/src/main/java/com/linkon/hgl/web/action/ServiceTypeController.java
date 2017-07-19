package com.linkon.hgl.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.proxydao.dto.ServiceTypeDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbServiceTypeService;
import com.liguo.hgl.service.TbUserInfoService;

@Controller
@RequestMapping("serviceType")
public class ServiceTypeController extends IBaseController {

	@Autowired
	private TbServiceTypeService serviceTypeService;
	
	@Autowired
	private TbUserInfoService userInfoService;
	
	@RequestMapping("/doSearchMySkill")
	public String doSearchMySkill(ModelMap model){	
		
		List<ServiceTypeDto> list = serviceTypeService.getSkills(this.getUserinfoId());			
		model.addAttribute("serviceTypeDtos", list);
						
		return "worker/myskill";
	}
	
	@RequestMapping("/doUpdateMyServiceType")
	@ResponseBody
	public String doUpdateMyServiceType(ServiceTypeDto serviceType){
		
		TbUserInfo userInfo = userInfoService.selectByPrimaryKey(this.getLoginUser().getUserinfoId());
		if(userInfo!=null){
			userInfo.setServiceType(serviceType.getSkill());
			if(userInfoService.updateByPrimaryKeySelective(userInfo)==1){
				return String.valueOf(true);
			}else{
				return String.valueOf(false);
			}
		}
		
		return String.valueOf(true);	
	}
	
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
