package com.linkon.hgl.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbGroupDistribution;
import com.liguo.hgl.proxydao.model.TbUserGroup;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbGroupDistributionService;
import com.liguo.hgl.service.TbUserGroupService;
import com.liguo.hgl.service.TbWebUserService;
/**
 * group用户分组类
 * @fiShopInfroController.java
 * @2016-6-27	
 * @author 周琨
 */
@Controller
@RequestMapping("userGroup")
public class UserGroupController extends IBaseController{
	
	@Autowired
	protected TbUserGroupService tbUserGroupService;
	
	@Autowired
	protected TbWebUserService tbWebUserService;

	@Autowired
	protected TbGroupDistributionService tbGroupDistributionService;
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		Criteria example = new Criteria();
		example.put("shopId",getShopId());
		List<TbUserGroup> gList = tbUserGroupService.selectByObject(example);
		
		Criteria e = new Criteria();
		e.put("recommendShopId", getShopId());
		List<WebUserDto> uList = tbWebUserService.selectUserGroupPage(e, page);
		model.addAttribute("gList", gList);
		model.addAttribute("uList", uList);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}

	@Override
	public String doSearchResult() {
		return null;
	}
	
	@RequestMapping(value = "/updateUserGroup")
	public String updateUserGroup(PageDto page,Integer userId,String userGroup,Model model){
		Criteria example = new Criteria();
		example.put("shopId",getShopId());
		example.put("userId",userId);
		List<TbGroupDistribution> dList = tbGroupDistributionService.selectByObject(example);
		//如果没有数据就插入数据
		if(dList.size() == 0){
			TbGroupDistribution tbGroupDistribution = new TbGroupDistribution();
			tbGroupDistribution.setShopId(getShopId());
			tbGroupDistribution.setUserId(userId);
			tbGroupDistribution.setGroupId(userGroup);
			tbGroupDistribution.setVersion(0);
			tbGroupDistributionService.insertSelective(tbGroupDistribution);
		}
		if(dList.size() > 0){
			TbGroupDistribution tbGroupDistribution = dList.get(0);
			tbGroupDistribution.setGroupId(userGroup);
			tbGroupDistributionService.updateByPrimaryKey(tbGroupDistribution);
		}
		
		example.put("userId",null);
		List<TbUserGroup> gList = tbUserGroupService.selectByObject(example);
		
		example.put("recommendShopId", getShopId());
		List<WebUserDto> uList = tbWebUserService.selectUserGroupPage(example, page);
		model.addAttribute("gList", gList);
		model.addAttribute("uList", uList);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		return "userGroup/userGroupList";
	}
	
	@RequestMapping(value = "/serachUserGroup")
	public String serachUserGroup(PageDto page,String name,Model model,Integer typeId,String groupName){
		Criteria example = new Criteria();
		example.put("shopId", getShopId());
		
		List<TbUserGroup> gList = tbUserGroupService.selectByObject(example);
		Criteria e = new Criteria();
		e.put("recommendShopId", getShopId());
		if(!StringUtils.isBlank(name)){
			e.put("name", name.trim());
		}
		if(!StringUtils.isBlank(groupName)){
			e.put("groupName", groupName.trim());
		}
		e.put("typeId", typeId);
		List<WebUserDto> uList = tbWebUserService.selectUserGroupPage(e, page);
		model.addAttribute("gList", gList);
		model.addAttribute("uList", uList);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		return "userGroup/userGroupList";
	}
}
