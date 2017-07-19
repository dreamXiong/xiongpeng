package com.linkon.admin.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminRole;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.IdCreator;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbAdminRoleService;
import com.liguo.hgl.service.TbAdminUserService;
import com.liguo.hgl.util.MD5Utils;

@Controller
@RequestMapping("adminuser")
public class AdminUserController extends IBaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String ADMINUSER = "adminuser/adminuser";
	private static final String ADMINUSERLIST = "adminuser/adminuserlist";
	private static final String ADMINUSERPSD="adminuser/newPsd";

	@Autowired
	private TbAdminUserService adminUserService;

	@Autowired
	private TbAdminRoleService adminRoleService;
	
	 /** 招商推荐码生成器 */
    @Resource
    private IdCreator recommendCode;
    
//    private Integer threadNum = 100;
    

	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {

	}

	@Override
	public String doSearchResult() {

		return null;
	}

	@RequestMapping("/init")
	public String init(PageDto page, ModelMap model) {
		Criteria criteria = new Criteria();
		criteria.setOrderByClause("id desc");
		List<TbAdminUserDto> list = adminUserService.selectByObjectPage(
				criteria, page);
		model.addAttribute("adminUsersDto", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);

		return ADMINUSER;
	}

	@RequestMapping("/doSearchResult")
	public String doSearchResult(String name, String userName, String mobile,
			PageDto page, ModelMap model) {

		logger.debug("根据条件查询出后台用户信息，并分页显示");

		Criteria criteria = new Criteria();
		criteria.put("name", name);
		criteria.put("userName", userName);
		criteria.put("mobile", mobile);
		criteria.setOrderByClause("id desc");

		List<TbAdminUserDto> list = adminUserService.selectByObjectPage(
				criteria, page);
		model.addAttribute("adminUsersDto", list);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		model.addAttribute("name", name);
		model.addAttribute("userName", userName);
		model.addAttribute("mobile", mobile);

		return ADMINUSERLIST;
	}

	@RequestMapping(value = "/queryAdminUserDetail")
	public ModelAndView queryAdminUserDetail(Integer id) {

		logger.debug("查看用户详情");

		String path = "adminuser/adminuserdetail";
		ModelAndView mv = new ModelAndView(path);
		TbAdminUser adminUser = adminUserService.selectByPrimaryKey(id);
		mv.addObject("adminUser", adminUser);

		return mv;
	}

	@RequestMapping(value = "/initAddAdminUser")
	public ModelAndView initAddAdminUser() {
		logger.debug("初始化添加用户页面");

		String path = "adminuser/addadminuser";
		ModelAndView mv = new ModelAndView(path);
		List<TbAdminRole> list = adminRoleService
				.selectByObject(new Criteria());
		mv.addObject("adminRoles", list);

		return mv;
	}

	@RequestMapping(value = "/addAdminUser")
	@ResponseBody
	public String addAdminUser(String userName, String password, String name,
			String address, String mobile, String email, Integer roleId,
			Integer type, Integer status, String idCard, String remark)
			throws Exception {

		String insertCount = "";
		TbAdminUser adminUser = new TbAdminUser();
		adminUser.setUserName(userName);
		if (password != null) {
			password = MD5Utils.md5(password, "UTF-8");
		}
		adminUser.setPassword(password);
		adminUser.setName(name);
		adminUser.setAddress(address);
		adminUser.setMobile(mobile);
		adminUser.setEmail(email);
		adminUser.setRoleId(roleId);
		adminUser.setType(type);
		adminUser.setStatus(status);
		adminUser.setIdCard(idCard);
		adminUser.setRemark(remark);

		logger.debug("添加用户信息");
		setRecomCode(type, adminUser);
//		ExecutorService executor =Executors.newFixedThreadPool(threadNum);
//		executor.execute(new testThread());
//		logger.debug("thread start ...");
		
		insertCount = String.valueOf(adminUserService
				.insertSelective(adminUser));

		return insertCount;
	}
	
	private void setRecomCode(Integer type, TbAdminUser adminUser) {
	    String reCode =adminUser.getRecommendCode();
	    /**地推人员,无推荐码,新增推荐码**/
        if(HglContants.USER_TYPE_SALE.equals(type)&&StringUtil.isBlank(reCode)){
            /**新增推荐码操作**/
            String code = recommendCode.create();
            logger.debug("recommendCode produce ..."+ code);
            adminUser.setRecommendCode(code);
        }
        
    }

	@RequestMapping(value = "/initUpdateAdminUser")
	public ModelAndView initUpdateAdminUser(Integer id) {

		logger.debug("初始化修改用户信息页面");

		String path = "adminuser/updadminuser";
		ModelAndView mv = new ModelAndView(path);
		TbAdminUser adminUser = adminUserService.selectByPrimaryKey(id);
		List<TbAdminRole> list = adminRoleService
				.selectByObject(new Criteria());

		mv.addObject("adminUser", adminUser);
		mv.addObject("adminRoles", list);

		return mv;
	}

	/**
	 * 验证用户名、手机号、身份证号是否已经被占用
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping("/checkAdminUser")
	@ResponseBody
	public String checkAdminUser(String params, Integer checkTypeHtml,
			Integer id) {
		boolean result = true;
		List<TbAdminUser> list = adminUserService.selectDuplicateRecord(params,
				checkTypeHtml);
		if (id == null || id == 0) { // 表示添加

			if (list.isEmpty()) {
				result = true;
			} else {
				result = false;
			}
		} else { // 表示修改
			if (list.isEmpty()) {
				result = true;
			} else if (list.size() == 1) {
				TbAdminUser adminUser = list.get(0);
				if (adminUser.getId().equals(id)) {
					result = true;
				} else {
					result = false;
				}
			} else {
				result = false;
			}
		}

		return String.valueOf(result);
	}

	/**
	 * 修改用户
	 * 
	 * @param adminUser
	 * @return
	 */
	@RequestMapping(value = "/updateAdminUser")
	public ModelAndView updateAdminUser(TbAdminUser adminUser) {
		ModelAndView mv = new ModelAndView();
		/**添加推荐码操作**/
        if(null!=adminUser){
            Integer type =adminUser.getType();
            setRecomCode(type, adminUser);
        }
		adminUserService.updateByPrimaryKeySelective(adminUser);
		mv.setViewName("redirect:" + "init");

		return mv;
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteAdminUser")
	@ResponseBody
	public String deleteAdminUser(Integer id) {

		logger.debug("删除id为:" + id + " 的用户");

		String dtlCount = String.valueOf(adminUserService
				.deleteByPrimaryKey(id));

		return dtlCount;
	}

	/**
	 * 初始化重置密码
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/initUpdatePassword")
	public ModelAndView initUpdatePassword(Integer id) {
		String path = "adminuser/updpassword";
		ModelAndView mv = new ModelAndView(path);
		TbAdminUser adminUser = adminUserService.selectByPrimaryKey(id);
		mv.addObject("adminUser", adminUser);

		return mv;
	}

	/**
	 * 验证旧密码
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkOldPassword")
	@ResponseBody
	public String checkOldPassword(String userName, String password)
			throws Exception {
		String result = "";
		if (password != null) {
			password = MD5Utils.md5(password, "UTF-8");
		}
		TbAdminUser adminUser = adminUserService.selectByUsernameAndPwd(
				userName, password);
		if (adminUser != null) {
			result = "true";
		} else {
			result = "false";
		}
		return result;
	}

	/**
	 * 重置密码
	 * @param adminUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPassword")
	@ResponseBody
	public String resetPassword(Integer id) throws Exception {
		String result = "";
		logger.debug("重置密码");

		TbAdminUser adminUser = adminUserService.selectByPrimaryKey(id);
		if (adminUser != null) {
			Integer version = adminUser.getVersion();
			String idCard = adminUser.getIdCard();
			String newPassword = idCard.substring(idCard.length() - 6,
					idCard.length());
			System.out.println(newPassword);
			if (StringUtil.isNotBlank(newPassword)) {
				adminUser = new TbAdminUser();
				adminUser.setId(id);
				adminUser.setVersion(version);
				adminUser.setPassword(MD5Utils.md5(newPassword, "UTF-8"));
				result = String.valueOf(adminUserService
						.updateByPrimaryKeySelective(adminUser));
			}
		}

		return result;
	}

	/**
	 * 修改用户状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/updateAdminUserStatus")
	@ResponseBody
	public String updateAdminUserStatus(Integer id, Integer status) {
		String result = "";
		TbAdminUser adminUser = adminUserService.selectByPrimaryKey(id);
		if (adminUser != null) {
			adminUser.setId(id);
			adminUser.setVersion(adminUser.getVersion());
			adminUser.setStatus(status);
		}
		result = String.valueOf(adminUserService
				.updateByPrimaryKeySelective(adminUser));

		return result;
	}
	
	/*初始化用户自己修改密码*/
	@RequestMapping(value="/doInitUpdatePassword")
	public String doInitUpdatePassword(ModelMap model){
		TbAdminUser adminUser = this.getLoginUser();
		model.addAttribute("adminUser", adminUser);
		
		return ADMINUSERPSD;
	}
	
	/**
	 * 验证旧密码输入是否正确
	 * @param oldPassword
	 * @return
	 */
	@RequestMapping("/doCheckOldPassword")
	@ResponseBody
	public String doCheckOldPassword(String oldPassword){
		String  password = this.getLoginUser().getPassword();
		if(StringUtil.isNotBlank(oldPassword)){
			if(password.equals(MD5Utils.md5(oldPassword, "UTF-8"))){
				return String.valueOf(true);
			}else{
				return String.valueOf(false);
			}
		}		
		return String.valueOf(false);		
	}
	
	/**
	 * 修改后台用户密码
	 * @param request
	 * @param adminUser
	 * @return
	 */
	@RequestMapping("/doUpdatePassword")
	public String doUpdatePassword(HttpServletRequest request,TbAdminUser adminUser){
		if(StringUtil.isNotBlank(adminUser.getPassword())){
			TbAdminUser adminUserUpd = this.getLoginUser();
			adminUserUpd.setPassword(MD5Utils.md5(adminUser.getPassword(), "UTF-8"));
			if(adminUserService.updateByPrimaryKeySelective(adminUserUpd)==1){
				HttpSession session = request.getSession();
				session.setAttribute(HglContants.SESSION_KEY, null);
				return "index/login";
			}else{
				return ADMINUSERPSD;
			}
		}
		return ADMINUSERPSD;
	}

}
