package com.linkon.admin.action;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebRole;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWebRoleService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.MD5Utils;

@Controller
@RequestMapping("webuser")
public class WebUserController extends IBaseController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String WEBUSER="webuser/webuser";
	
	private static final String WEBUSERLIST="webuser/webuserlist";
	
	private static final String WEBUSERUPD="webuser/updwebuser";
	

	@Autowired
	private TbWebUserService webUserService;
	
	@Autowired
	private TbWebRoleService webRoleService;
	
	@Autowired
	private TbUserInfoService userInfoService;
	
	/**
	 * 查询出所有的Web端用户信息
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/init")
	public String init(PageDto page,ModelMap model){
		String strTypeId = "102,104,106,108,110,112,114";
		List<String> typeIds = Arrays.asList(strTypeId.split(","));
		Criteria criteria = new Criteria();		
		criteria.setOrderByClause("id desc");
		criteria.put("typeId",typeIds);
		List<WebUserDto> list = webUserService.selectByObjectPage(criteria, page);
		model.addAttribute("webUserDto", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		
		return WEBUSER;
	}
	
	/**
	 * 查询出符合条件的Web端用户信息
	 * @param userName
	 * @param name
	 * @param mobile
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/doSearchResult")
	public String doSearchResult(String userName,String name,String mobile,PageDto page,ModelMap model){
		String strTypeId = "102,104,106,108,110,112";
		List<String> typeIds = Arrays.asList(strTypeId.split(","));
		Criteria criteria = new Criteria();
		criteria.put("userName", userName);
		criteria.put("name", name);
		criteria.put("mobile", mobile);
		criteria.put("typeId", typeIds);
		criteria.setOrderByClause("id desc");
		
		List<WebUserDto> list= webUserService.selectByObjectPage(criteria, page);
		model.addAttribute("webUserDto", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("userName", userName);
		model.addAttribute("name", name);
		model.addAttribute("mobile", mobile);
		return WEBUSERLIST;
	}
	
	/**
	 * 审核用户
	 * @param id
	 * @param state
	 * @return
	 */
	@RequestMapping("/doUpdateWebUserStatus")
	@ResponseBody
	public String doUpdateWebUserStatus(Integer id,Integer state){
		TbWebUser webUser = webUserService.selectByPrimaryKey(id);
		if(webUser!=null){
			webUser.setState(state);
			if(webUserService.updateByPrimaryKey(webUser)==1){
				return String.valueOf(true);
			}else{
				return String.valueOf(false);
			}
		}		
		return String.valueOf(false);
	}
	
	/**
	 * 跳转到修改用户信息页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/doInitUpdateWebUser")
	public String doInitUpdateWebUser(Integer id,ModelMap model){
		
		TbWebUser webUser = webUserService.selectByPrimaryKey(id);
		model.addAttribute("webUser", webUser);
		
		TbUserInfo userInfo = new TbUserInfo();
		if(webUser!=null){
			userInfo = userInfoService.selectByPrimaryKey(webUser.getUserinfoId());
		}
		model.addAttribute("userInfo", userInfo);
		
		List<TbWebRole> list = webRoleService.selectByObject(new Criteria());
		model.addAttribute("webRoles", list);
		
		return WEBUSERUPD;
	}
	
	/**
	 * 修改用户信息
	 * @param webUser
	 * @return
	 */
	@RequestMapping("/doUpdateWebUser")
	public ModelAndView doUpdateWebUser(TbWebUser webUser){
		ModelAndView mv = new ModelAndView();
		TbWebUser user = new TbWebUser();
		user.setId(webUser.getId());
		user.setName(webUser.getName());
		user.setUserName(webUser.getUserName());
		user.setMobile(webUser.getMobile());
		user.setEmail(webUser.getEmail());
		user.setRoleId(webUser.getRoleId());
		user.setState(webUser.getState());
		user.setRemark(webUser.getRemark());
		webUserService.updateByPrimaryKeySelective(user);
		
		mv.setViewName("redirect:init");
		
		return mv;
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping("/doUpdateWebUserPassword")
	@ResponseBody
	public String doUpdateWebUserPassword(Integer id){
		TbWebUser webUser = webUserService.selectByPrimaryKey(id);
		if(webUser!=null){
			String mobile = webUser.getMobile();
			if(StringUtils.isNotBlank(mobile)){
				String newPsd = mobile.substring(5, 11);
				webUser.setPassword(MD5Utils.md5(newPsd, "UTF-8"));
				if(webUserService.updateByPrimaryKey(webUser)==1){
					return String.valueOf(true);
				}else{
					return String.valueOf(false);
				}
			}
		}		
		return String.valueOf(false);
	}
	
	/**
	 * 验证电话号码不能重复
	 * @param id
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doCheckMobile")	
	public String doCheckMobile(Integer id,String mobile){
		Criteria criteria = new Criteria();
		criteria.put("mobile", mobile);
		List<TbWebUser> list = webUserService.selectByObject(criteria);
		if(list.isEmpty()){
			return String.valueOf(true);
		}else{
			if(list.size()==1){
				if(id.equals(list.get(0).getId())){
					return String.valueOf(true);
				}else{
					return String.valueOf(false);
				}			
			}			
		}
		return String.valueOf(false);
	}
		
	/**
	 * 验证邮箱不能重复
	 * @param id
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doCheckEmail")	
	public String doCheckEmail(Integer id,String email){
		Criteria criteria = new Criteria();
		criteria.put("email", email);
		List<TbWebUser> list = webUserService.selectByObject(criteria);
		if(list.isEmpty()){
			return String.valueOf(true);
		}else{
			if(list.size()==1){
				if(id.equals(list.get(0).getId())){
					return String.valueOf(true);
				}else{
					return String.valueOf(false);
				}
			}
		}
		return String.valueOf(false);
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
