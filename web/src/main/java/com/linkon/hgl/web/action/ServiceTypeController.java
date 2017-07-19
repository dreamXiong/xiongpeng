package com.linkon.hgl.web.action;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.proxydao.dto.ServiceTypeDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbServiceTypeService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbUserInfoService;

@Controller
@RequestMapping("serviceType")
public class ServiceTypeController extends IBaseController {

	@Autowired
	private TbServiceTypeService serviceTypeService;
	
	@Autowired
	private TbUserInfoService userInfoService;
	
	@Autowired
	private TbShopInfoService tbShopInfoService;
	
	@RequestMapping("/doSearchMySkill")
	public String doSearchMySkill(Model model){
		
		TbShopInfo shopInfo = tbShopInfoService.selectByPrimaryKey(this.getLoginUser().getShopId());
		Criteria criteria = new Criteria();
		criteria.put("parentId",0);
		criteria.setOrderByClause("id");
		List<ServiceTypeDto> list = serviceTypeService.selectDtoByObject(criteria);
					
		for(int i=0;i<list.size();i++){
			Integer parentId = list.get(i).getId();
			ServiceTypeDto serviceTypeDto = list.get(i);	
			criteria = new Criteria();
			criteria.put("parentId",parentId);
			if(StringUtil.isNotBlank(shopInfo.getServiceType())){
				String[] strList = shopInfo.getServiceType().split(",");
				List<String> strListId = Arrays.asList(strList);
				criteria.put("item",strListId);	
			}
					
			List<ServiceTypeDto> listChild = serviceTypeService.selectServiceTypeByIds(criteria);
			serviceTypeDto.setChildList(listChild);			
			list.remove(i);
			list.add(i,serviceTypeDto);
		}		
		model.addAttribute("serviceTypeDtos", list);				
		return "worker/myskill";
	}
	
	@RequestMapping("/doUpdateMyServiceType")
	@ResponseBody
	public int doUpdateMyServiceType(Model model, ServiceTypeDto serviceType){
		
	    TbShopInfo tbShopInfo = tbShopInfoService.selectByPrimaryKey(this.getLoginUser().getShopId());
		if(tbShopInfo==null){
		    return 0;
		}
		tbShopInfo.setServiceType(serviceType.getSkill());
		int i =tbShopInfoService.updateByPrimaryKeySelective(tbShopInfo);
		return i;
		
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
