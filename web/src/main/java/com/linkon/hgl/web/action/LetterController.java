package com.linkon.hgl.web.action;

import java.io.IOException;
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

import com.liguo.hgl.proxydao.dto.GroupUserDto;
import com.liguo.hgl.proxydao.dto.LetterActivityDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbActivityInfo;
import com.liguo.hgl.proxydao.model.TbUserGroup;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbActivityInfoService;
import com.liguo.hgl.service.TbStandLetterService;
import com.liguo.hgl.service.TbUserGroupService;
import com.liguo.hgl.service.TbWebUserService;

/**
 * 站内信操作类
 * @fiStandLetterController.java
 * @2016-6-30	
 * @author 周双双
 */
@Controller
@RequestMapping("letter")
public class LetterController extends IBaseController{

	@Autowired
	protected TbStandLetterService tbStandLetterService;
	
	@Autowired
	protected TbUserGroupService tbUserGroupService;
	
	@Autowired
	protected TbWebUserService tbWebUserService;
	
	@Autowired
	protected TbActivityInfoService tbActivityInfoService;
	
	/**
	 * 站内信页面
	 * @param model
	 * @return
	 * @author zss
	 */
	@RequestMapping("/letter")
	public String getLetter(Model model){
		
		
		List<TbWebUser> uList = tbWebUserService.selectBySaveShop(getShopId(),getLoginUserId());
		model.addAttribute("uList", uList);//所有的用户
		Criteria example1 = new Criteria();
		example1.put("shopId", getShopId());
		List<TbUserGroup> groups = tbUserGroupService.selectByObject(example1);
		model.addAttribute("groups", groups);//所有的分组
		
		List<GroupUserDto> groupUserDtos = tbUserGroupService.selectgroupUser(getShopId());
		model.addAttribute("groupUserDtos", groupUserDtos);//分组用户
		
		List<TbWebUser> gzUserList = tbWebUserService.selectBySaveShopUser(getShopId());
		model.addAttribute("gzUserList", gzUserList);//关注店铺用户
		
		List<TbActivityInfo> activityInfos = tbActivityInfoService.selectByObject(example1);
		model.addAttribute("activityInfos", activityInfos);//所有活动
		
		List<LetterActivityDto> activityDtos = tbStandLetterService.selectLetters(getLoginUserId());
		model.addAttribute("activityDtos", activityDtos);//站内信信息
		
		 session.setAttribute("letterCount", 0);
		
		
		
		return "letter/letter";
	}
	/**
	 * 根据分组Id查询出店铺对应的用户
	 * @param gid
	 * @return
	 * @author zss
	 */
	
	 @RequestMapping("/groupUserList")
	 @ResponseBody
	public Map<String, Object> getGroupUserList(@RequestParam Integer gid){
		if(gid!=null && gid>0&&getShopId()!=null){
			return tbUserGroupService.selectUserListByGroup(gid,getShopId());
		}
		return null;
	}
	/**
	 * 发送站内信
	 * @param model
	 * @param param
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/addLetter")
	public String addLetter(Model model,@RequestParam Map<String, Object> param){
			
		logger.debug("---param:"+param);
	
	    tbStandLetterService.addLetter(getLoginUserId(),param);
			
		return "redirect:/letter/letter";
	}
	
	
	/**
	 * 每隔一段时间刷新请求一次
	 * @param model
	 * @param uId
	 * @param response
	 * @return
	 * @throws IOException
	 * @author zss
	 */
	@RequestMapping("/letterByTime")
	public String getLetterByTime(Model model,HttpServletResponse response) throws IOException{
		int count =tbStandLetterService.selectNewLetter(null,getLoginUserId());
		if(count>0){
			List<LetterActivityDto> activityDtos = tbStandLetterService.selectLetters(getLoginUserId());
			model.addAttribute("activityDtos", activityDtos);//站内信信息
			return "letter/letterList";
		}
	
	response.getWriter().write("");
	return null;
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
