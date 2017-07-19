package com.linkon.hgl.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.ServiceTypeDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.CommonUtil;
import com.liguo.hgl.proxydao.util.IdCreator;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbNoticeInfoService;
import com.liguo.hgl.service.TbServiceTypeService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.SmsSend;
import com.linkon.hgl.web.common.StrUtil;

@Controller
@RequestMapping("register")
public class RegisterController extends IBaseController{
	
	/**推荐码生成器 */
    @Resource
    private IdCreator recommendCode;
	
	@Autowired
	private TbWebUserService webUserService;
	
	@Autowired
	private TbServiceTypeService serviceTypeService;
	
	@Autowired
	private TbUserInfoService userInfoService;
	
	@Autowired
	private TbNoticeInfoService noticeInfoService;
	
	@Autowired
	private TbAccountService tbAccountService;
	
	
	/**
	 * 跳转到注册页面
	 * @param recommendCode
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doInitWebUserRegister")
	public String doInitWebUserRegister(String recommendCode,HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
    	//判断是否微信中打开的
		if(CommonUtil.isWeiXinBrowser(request)){
			model.addAttribute("isWeiXinBrowser", true);
		}
		return "wapUser/register";
	}
		  
	/**
	 * 个人注册
	 * @param webUser
	 * @param headimgurl
	 * @param model
	 * @return
	 */
	@RequestMapping("/doRegisterUser")
	public String doRegisterUser(TbWebUser webUser,String headimgurl,Model model){
		if(webUserService.registerCustomer(webUser, headimgurl)==1){
			//如果是微信自动登录不需要再去登录
			if(headimgurl == null){
				WebUserDto webUserDto = webUserService.selectByUsernameAndPwd(webUser.getUserName(), webUser.getPassword());
				session.setAttribute(HglContants.SESSION_KEY, webUserDto); //注册成功后，保存用户session信息	
			}
			
			//显示最新公告
			List<TbNoticeInfo> tbNoticeInfolist = noticeInfoService.showNewestNoticeInfo(0, 5);
			model.addAttribute("tbNoticeInfolist", tbNoticeInfolist);
		
			return "redirect:/";
		}else{
			return "wapUser/register";
		}	
	}
	
	@RequestMapping("/doPhoneImage")
	public String doPhoneImage(){
		
		return "worker/phone";
	}
	
	/**
	 * 获得个人手机验证码
	 * @param request
	 * @param phoneNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getPersonalVodePhone")
	public String getPersonalVodePhone(HttpServletRequest request,String phoneNum){
		String vode= StrUtil.getRandom4Number();
		String personalName = "的终端用户";
		String sendRegestSms = SmsSend.sendRegestSms(phoneNum, vode, personalName);
		if("".equals(sendRegestSms)){
			HttpSession session = request.getSession(true);
			session.setAttribute("personalVodeCode", vode);
		}
		return sendRegestSms;
	}
	
	/**
	 * 验证个人手机验证码 
	 * @param request
	 * @param personalVodeCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doCheckPersonalVodeCode")
	public String doCheckPersonalVodeCode(HttpServletRequest request,String personalVodeCode){
		logger.debug("验证用户输入的验证码:"+personalVodeCode+" 输入是否正确");
		HttpSession session = request.getSession(true);
		String personalVodeCodeOrg = "";
		if(session.getAttribute("personalVodeCode")!=null){
			personalVodeCodeOrg = session.getAttribute("personalVodeCode").toString();
			if(personalVodeCodeOrg.equals(personalVodeCode)){
				return String.valueOf(true);
			}
		}		
		return String.valueOf(false);		
	}
	
	/**
	 * 跳转到师傅注册页面
	 * @param recommendCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/doInitWorkerRegister")
	public String doInitWorkerRegister(String recommendCode,ModelMap model){		
		Criteria criteria = new Criteria();
		criteria.put("parentId",0);
		criteria.setOrderByClause("id");
		List<ServiceTypeDto> list = serviceTypeService.selectDtoByObject(criteria);				
		for(int i=0;i<list.size();i++){
			Integer parentId = list.get(i).getId();
			ServiceTypeDto serviceTypeDto = list.get(i);	
			criteria = new Criteria();
			criteria.put("parentId",parentId);					
			List<ServiceTypeDto> listChild = serviceTypeService.selectServiceTypeByIds(criteria);
			serviceTypeDto.setChildList(listChild);			
			list.remove(i);
			list.add(i,serviceTypeDto);
		}
		model.addAttribute("serviceTypeDtos", list);
		
		return "worker/register";
	}
	
	/**
	 * 获取师傅的手机验证码
	 * @param request
	 * @param phoneNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getWorkerVodePhone")
	public String getWorkerVodePhone(HttpServletRequest request,String phoneNum){
		String vode = StrUtil.getRandom4Number();
		String workerName = "的师傅";
		String sendRegestSms = SmsSend.sendRegestSms(phoneNum, vode, workerName);
		if("".equals(sendRegestSms)){
			HttpSession session = request.getSession();
			session.setAttribute("workerVodeCode", vode);
		}		
		return sendRegestSms;
	}
	
	/**
	 * 验证验证码是否输入正确
	 * @param request
	 * @param workerVodeCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doCheckWorkerVodeCode")
	public String doCheckWorkerVodeCode(HttpServletRequest request,String workerVodeCode){
		logger.debug("验证用户输入的验证码:"+workerVodeCode+" 输入是否正确");
		HttpSession session = request.getSession(true);
		String workerVodeCodeOrg = "";
		if(session.getAttribute("workerVodeCode")!=null){
			workerVodeCodeOrg = session.getAttribute("workerVodeCode").toString();
			if(workerVodeCodeOrg.equals(workerVodeCode)){
				return String.valueOf(true);
			}
		}		
		return String.valueOf(false);		
	}
	
	/**
	 * 师傅注册
	 * @param webUser
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/doRegisterWorker",method=RequestMethod.POST)
	public String doRegisterWorker(WebUserDto webUser,HttpServletRequest request,ModelMap model){
		logger.debug("师傅注册个人信息");
		if(webUserService.registerWorker(webUser, request)==1){
		    session.removeAttribute(HglContants.SESSION_KEY);
			return "worker/success";	
		}else{
			return "worker/register";
		}			
	}
	
	/**
	 * 验证用户名是否被占用
	 * @param userName
	 * @return
	 */
	@RequestMapping("/doCheckUserName")
	@ResponseBody
	public String doCheckUserName(String userName){
		Criteria criteria = new Criteria();
		criteria.put("userName", userName);
		List<TbWebUser> list = webUserService.selectByObject(criteria);
		if(list.size()>0){
			return String.valueOf(false);
		}else{
			return String.valueOf(true);
		}
	}
	
	/**
	 * 验证手机号是否已经被占用
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/doCheckMobile")
	@ResponseBody
	public String doCheckMobile(String mobile){
		Criteria criteria = new Criteria();
		criteria.put("mobile", mobile);
		List<TbWebUser> webUsers = webUserService.selectByObject(criteria);
		if(webUsers.size()>0){
			return String.valueOf(false);
		}else{
			return String.valueOf(true);
		}
	}
	
	/**
	 * 判断推荐码输入是否正确
	 * @param recommendcode
	 * @return
	 */
	@RequestMapping("/doCheckLogoCode")
	@ResponseBody
	public String doCheckLogoCode(String recommendcode){
		Criteria criteria = new Criteria();
		criteria.put("logoCode", recommendcode);
		TbWebUser webUser = webUserService.selectUserByLogoCode(criteria);
		if(webUser!=null){
			return String.valueOf(true);
		}else{
			return String.valueOf(false);
		}
	}
	
	/**
	 * 跳转到审核拒绝页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/reject")
    public String reject(Integer id,ModelMap model){
    	TbUserInfo userInfo = null;
    	List<ServiceTypeDto> list = null;
    	TbWebUser webUser = webUserService.selectByPrimaryKey(id);
    	if(webUser!=null){
    		userInfo = userInfoService.selectByPrimaryKey(webUser.getUserinfoId());
    		if(userInfo!=null){
    			list = serviceTypeService.getSkills(userInfo.getId());
    		}  		
    	}
    	model.addAttribute("webUser", webUser);
    	model.addAttribute("userInfo", userInfo);
    	model.addAttribute("serviceTypeDtos", list);
    	return "worker/reject";
    }
	
	/**
	 * 审核拒绝后师傅重新提交审核
	 * @param webUser
	 * @param request
	 * @return
	 */
	@RequestMapping("/doUpdateWorker")
	public String doUpdateWorker(WebUserDto webUser,HttpServletRequest request){
		if(webUser!=null){
			if(webUserService.doUpdateWorker(webUser, request)==1){				
				return "worker/success";
			}
		}
		return "worker/success";
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
