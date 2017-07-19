package com.linkon.hgl.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
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
 * @2016-6-25	
 * @author 周琨
 */
@Controller
@RequestMapping("group")
public class GroupController extends IBaseController{
	
	@Autowired
	protected TbUserGroupService tbUserGroupService;
	
	@Autowired
	protected TbWebUserService tbWebUserService;
	
	@Autowired
	protected TbGroupDistributionService tbGroupDistributionService;

	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		if(getShopId() == null){
			return ;
		}
		Criteria c = new Criteria();
		c.put("shopId", getShopId());
		List<TbUserGroup> gList = tbUserGroupService.selectByObject(c);
		model.addAttribute("gList", gList);
	}

	@Override
	public String doSearchResult() {
		return null;
	}
	@RequestMapping(value="/addGroup")
	public String agency(HttpServletRequest request,String name,String remark,Integer discount){
		TbUserGroup record = new TbUserGroup();
		record.setName(name);
		record.setRemark(remark);
		record.setDiscount(discount);
		record.setVersion(0);
		record.setUserId(getLoginUserId());
		record.setShopId(getShopId());
		tbUserGroupService.insertSelective(record);
		return "redirect:/group/index";
    }
	/*
	 * 删除该组时校验该分组下面是否有用户
     * */
    @RequestMapping("/delectGroupValidate")
    @ResponseBody
    public Map<String,Object> delectGroupValidate(@RequestParam String groupId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	Criteria c = new Criteria();
    	c.put("userGroup", groupId);
    	List<TbGroupDistribution> tLsit = tbGroupDistributionService.selectByObject(c);
    	/*List<TbWebUser> tLsit= tbWebUserService.selectWebUserByGroupId(c);*/
    	if(tLsit.size() > 0){
    		map.put("code", "1");
    	}else{
    		map.put("code", "0");
    	}
    	return map;
    }
    @RequestMapping(value="/delectGroup")
	public String delectGroup(@RequestParam Integer groupId){
    	tbUserGroupService.deleteByPrimaryKey(groupId);
		return "redirect:/group/index";
    }
    
    @RequestMapping("/updateGroupDialog")
    public String updateGroupDialog(Model model,Integer groupId){
    	TbUserGroup tbUserGroup = tbUserGroupService.selectByPrimaryKey(groupId);
    	model.addAttribute("tbUserGroup", tbUserGroup);
    	return "group/updateDialog";
    }
    @RequestMapping("/saveUpdateGroup")
    public String saveUpdateGroup(Model model,Integer id,String name,Integer discount,String remark){
    	TbUserGroup tbUserGroup = tbUserGroupService.selectByPrimaryKey(id);
    	tbUserGroup.setName(name);
    	tbUserGroup.setDiscount(discount);
    	tbUserGroup.setRemark(remark);
    	tbUserGroupService.updateByPrimaryKey(tbUserGroup);
    	
    	Criteria c = new Criteria();
		c.put("shopId", getShopId());
		List<TbUserGroup> gList = tbUserGroupService.selectByObject(c);
		model.addAttribute("gList", gList);
    	model.addAttribute("tbUserGroup", tbUserGroup);
    	return "group/groupList";
    }
    
    @RequestMapping("/addValidateGroup")
	@ResponseBody
	public  Map<String,Object> addValidateGroup(Model model,String name){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", HglContants.NO_REPEAT);
		Criteria e = new Criteria();
		e.put("name", name.trim());
		e.put("shopId", getShopId());
		List<TbUserGroup> tList = tbUserGroupService.selectByObject(e);
		if(tList.size() > 0){
			map.put("code", HglContants.IS_REPEAT);
		}
		return map;
	}
    
    @RequestMapping("/updateValidateGroup")
	@ResponseBody
	public  Map<String,Object> updateValidateGroup(Model model,String name,Integer groupId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", HglContants.NO_REPEAT);
		Criteria e = new Criteria();
		e.put("name", name.trim());
		e.put("shopId", getShopId());
		List<TbUserGroup> tList = tbUserGroupService.selectByObject(e);
		if(tList.size() > 1){
			map.put("code", HglContants.IS_REPEAT);
		}
		if(tList.size() == 1){
			if(!tList.get(0).getId().toString().equals(groupId.toString())){
				map.put("code", HglContants.IS_REPEAT);
			}
		}
		return map;
	}
}
