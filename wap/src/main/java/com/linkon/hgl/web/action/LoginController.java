/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.linkon.hgl.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.ActionUrlRecordDto;
import com.liguo.hgl.proxydao.dto.ServiceTypeDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.CommonUtil;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbIntegralService;
import com.liguo.hgl.service.TbServiceTypeService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbShoppingCartService;
import com.liguo.hgl.service.TbStandLetterService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.service.TbWapOrderServiceService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.MD5Utils;
import com.liguo.hgl.util.SmsSend;

/**
 * 类的功能描述<br>
 * 
 * @author 王丹
 * @FileName LoginController.java<br>
 * @Language Java<br>
 * @date 2016-01-12<br>
 */
@Controller
@RequestMapping("login")
public class LoginController extends IBaseController {
	// 设置字母的大小,大小
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Font mFont = new Font("Times New Roman", Font.PLAIN, 20);

	@Autowired
	private TbWebUserService tbWebUserService;
	
    @Autowired
    protected TbShoppingCartService tbShoppingCartService;
    
    @Autowired
    protected TbShopInfoService tbShopInfoService;
    
    @Autowired
	private TbAccountService tbAccountService;
    
    @Autowired
    private TbIntegralService tbIntegralService;
    
    @Autowired
    private TbUserInfoService userInfoService;
    
    @Autowired
    private TbServiceTypeService serviceTypeService;
    
    
    @Autowired
	private TbStandLetterService tbStandLetterService;
	
	@Autowired
	private TbWapOrderServiceService wapOrderServiceService;
	
	@Autowired
	private TbWapOrderGroupService wapOrderGroupService;

    @RequestMapping(value = "/")
	public String indexPage(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
    	/*init(page, request, response, model);*/
//    	String productId = request.getParameter("productId");
//    	if(StringUtils.isNotBlank(productId)){
//    		String distance = request.getParameter("distance");
//    		session.setAttribute("productId", productId);
//    		session.setAttribute("distance", distance);
//    	}
    	//判断是否微信中打开的
		if(CommonUtil.isWeiXinBrowser(request)){
			model.addAttribute("isWeiXinBrowser", true);
		}
		return "index/login";
	}
	/*@RequestMapping("/index")
	public String index(Model model) {
	
	}*/

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model,RedirectAttributes attr) throws Exception,NoSuchAlgorithmException {
		/* 验证码 */
//		String verify = request.getParameter("verify");
//		if (verify == null || !verify.equalsIgnoreCase((String)session.getAttribute("verify"))) {
//			model.addAttribute("error", "用户名不能为空");
//			return "index/loginForm";
//		}
		/* 用户名 */
		String userName = request.getParameter("userName");
		if(StringUtils.isBlank(userName)){
			model.addAttribute("error", "用户名不能为空");
			return "index/loginForm";
		}
		/* 密码 */
		String pwd = request.getParameter("pwd");
		if(StringUtils.isBlank(pwd)){
			model.addAttribute("error", "密码不能为空");
			return "index/loginForm";
		}
		pwd = MD5Utils.md5(pwd, "UTF-8");
		logger.debug("wap端登录加密后的密码: " + pwd);
		WebUserDto tbWebUser = tbWebUserService.selectByUsernameAndPwd(userName,pwd);
		if(tbWebUser==null){
			tbWebUser = tbWebUserService.selectByMobileAndPwd(userName, pwd);
		}
		
		if(tbWebUser!=null && !HglContants.ADMIN_USER_NAME.equals(tbWebUser.getUserName())&&(
				!(tbWebUser.getTypeId().equals(HglContants.USER_TYPE_CUS)
				||tbWebUser.getTypeId().equals(HglContants.USER_TYPE_MASTER)
				||tbWebUser.getTypeId().equals(HglContants.USER_TYPE_DISTRIBUTOR)))){
			model.addAttribute("error", "用户名密码不匹配");
			return "index/loginForm";
		}
		
		if(tbWebUser !=null && tbWebUser.getState().equals(HglContants.WEB_AUTH_STATUS)){
			model.addAttribute("error", "您的账户还未审核");
			return "index/loginForm";
		}
		if(tbWebUser !=null && tbWebUser.getState().equals(HglContants.WEB_AUTH_STATUS2)){
			model.addAttribute("error", "您的账户未通过审核<a href='"+request.getContextPath()+"/register/reject?id="+tbWebUser.getId()+"' style='color:red;font-size:12px;'>查看原因</a>");
			model.addAttribute("errorType", "1");
			model.addAttribute("webUser", tbWebUser);
			return "index/loginForm";
		}
		
		if (tbWebUser != null && tbWebUser.getName() != null) {
			//获取登录用户是否绑定微信自动登录,如果绑定了更新登录状态
			String autoLoginFlag = request.getParameter("autoLoginFlag");
			//判断是否微信浏览器 ,获取openId
			if(CommonUtil.isWeiXinBrowser(request)){
				session.setAttribute(HglContants.SESSION_KEY, tbWebUser);  //保存登录信息到session
				model.addAttribute("weixinOpenUrl", HglContants.WEIXIN_OPEN_URL.replace("APPID", HglContants.WEIXIN_APP_ID).replace("REDIRECT_URI", CommonUtil.urlEncodeUTF8(HglContants.WEIXIN_LOGIN_CALL_METHOD)).replace("SCOPE", HglContants.WEIXIN_SNSAPI_BASE).replace("STATE", autoLoginFlag));
			}else{
				pageJump(tbWebUser, request, model);
			}
			return "index/loginForm";
		} else {
			model.addAttribute("error", "用户名密码不匹配");
			return "index/loginForm";
		}
	}
	
	/**
	 * 封装登录成功的执行操作
	 * @param tbWebUser
	 * @param request
	 * @param model
	 */
	public void pageJump(WebUserDto tbWebUser,HttpServletRequest request,Model model){
		tbWebUser = tbWebUserService.selectByUsernameAndPwd(tbWebUser.getUserName(),tbWebUser.getPassword());
		Integer accountId = 0;
		session.setAttribute(HglContants.SESSION_KEY, tbWebUser);  //保存登录信息到session
		//accountId查询
		accountId = tbAccountService.selectAccountByUserId(tbWebUser.getId()).getId();
		String redicectUrl = request.getContextPath() + "/";
		
		Criteria example = new Criteria();
    	example.put("userId", tbWebUser.getId());
		List<TbIntegral> iList = tbIntegralService.selectByObject(example);
		if(iList.size() == 0){
			TbIntegral tbIntegral= new TbIntegral();
			tbIntegral.setUserId(tbWebUser.getId());
			tbIntegralService.insert(tbIntegral);
		}
		if(tbWebUser.getTypeId() != HglContants.USER_TYPE_MASTER){
    		redicectUrl = request.getContextPath() + "/customerIndex";
    	}
    	//从session中获取连接,如果获取不到跳转首页
    	Object obj = session.getAttribute(HglContants.ACTION_URL_RECORD);
    	if(obj != null && obj instanceof ActionUrlRecordDto){
    		ActionUrlRecordDto record = (ActionUrlRecordDto)obj;
    		model.addAttribute("success", record);
    		session.removeAttribute(HglContants.ACTION_URL_RECORD);
    	}else{
    		ActionUrlRecordDto record = new ActionUrlRecordDto();
    		record.setActionUrl(redicectUrl);
    		model.addAttribute("success", record);
    	}
    	countValues(tbWebUser);
	}
	
	/**
	 * 绑定微信
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bindingWeixin")
	public @ResponseBody Map<String,Object> bindingWeixin(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		//用户名 
		String userName = request.getParameter("name");
		if(StringUtils.isBlank(userName)){
			map.put("tipsInfo", "用户名不能为空");
			return map;
		}
		//密码 
		String pwd = request.getParameter("pwd");
		if(StringUtils.isBlank(pwd)){
			map.put("tipsInfo", "密码不能为空");
			return map;
		}
		pwd = MD5Utils.md5(pwd, "UTF-8");
		logger.debug("wap-bindingWeixin端登录加密后的密码: " + pwd);
		WebUserDto webUser = tbWebUserService.selectByUsernameAndPwd(userName,pwd);
		if(webUser != null){
			if(webUser.getId().equals(this.getLoginUserId())){
				map.put("tipsInfo", "不能绑定当前用户");
				return map;
			}
			if(this.getLoginOpenId() != null){
				webUser.setOpenId(this.getLoginOpenId());
				if(tbWebUserService.updateUserOpenId(webUser, this.getLoginUserId())){
					session.setAttribute(HglContants.SESSION_KEY, webUser);  //保存登录信息到session
					map.put("tipsInfo", "true");
					return map;
				}
			}else{
				map.put("tipsInfo", "绑定失败");
				return map;
			}
		}
		map.put("tipsInfo", "用户名或密码错误");
		return map;
	}

	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping("/authImage")
	public String authImage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 表明生成的响应是图片
		response.setContentType("image/jpeg");

		int width = 100, height = 36;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(mFont);

		g.setColor(getRandColor(160, 200));

		// 画随机线
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 从另一方向画随机线
		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}

		// 生成随机数,并将随机数字转换为字母
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			int itmp = random.nextInt(26) + 65;
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(String.valueOf(ctmp), 15 * i + 10, 16);
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("verify", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return null;
	}

	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// TODO Auto-generated method stub
		
	}
	 @RequestMapping("/generateImage")
	    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
	    	ImageUtil.showImageShop(id,imgName,response);
	        return null;
	    }
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取手机验证码
	 * @param request
	 * @param response
	 */
    @RequestMapping("/getPhoneCode")
	public @ResponseBody Map<String,String> getPhoneCode(HttpServletRequest request,HttpServletResponse response){
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("tipsInfo", "success");
    	String userName = request.getParameter("userName");
        if (StringUtils.isBlank(userName)) {
        	map.put("tipsInfo", "用户名不能为空");
        	return map;
        }
        String verify = request.getParameter("verify");
        if (verify == null || !verify.equalsIgnoreCase((String) session.getAttribute("verify"))) {
        	map.put("tipsInfo", "验证码不正确");
        	return map;
        }
        Criteria example = new Criteria();
		example.put("userName", userName);
		TbWebUser webUser = tbWebUserService.selectByUserName(example);
		if(webUser == null){
			map.put("tipsInfo", "用户名不存在");
        	return map;
		}
		String code = RandomStringUtils.random(4, "123456789");
		request.getSession().setAttribute(HglContants.SESSION_PHONE_CODE, code);
		SmsSend.sendModifyPasswordSms(webUser.getMobile(),code);
        return map;
	}
    
    /**
     * 提交找回密码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/submitPhoneCheck")
   	public @ResponseBody Map<String,String> submitPhoneCheck(HttpServletRequest request,HttpServletResponse response){
       	Map<String,String> map = new HashMap<String,String>();
       	map.put("tipsInfo", "success");
       	String userName = request.getParameter("userName");
       	String vcodePhone = request.getParameter("vcodePhone");
	   	if (vcodePhone == null || !vcodePhone.equalsIgnoreCase((String) session.getAttribute(HglContants.SESSION_PHONE_CODE))) {
	     	map.put("tipsInfo", "手机验证码不正确");
	     	return map;
	    }
        Criteria example = new Criteria();
   		example.put("userName", userName);
   		TbWebUser webUser = tbWebUserService.selectByUserName(example);
   		if(webUser == null){
   			map.put("tipsInfo", "用户名不存在");
           	return map;
   		}
   		request.getSession().setAttribute(HglContants.SESSION_USERNAME, userName);
   	  	map.put("updateUrl", "/login/backNewPasswordUrl");
        return map;
   	}
    
    /**
     * 提交找回密码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/modifyPassword")
   	public @ResponseBody String modifyPassword(HttpServletRequest request,HttpServletResponse response){
    	String userName = (String)request.getSession().getAttribute(HglContants.SESSION_USERNAME);
    	String password = request.getParameter("password");
        password = MD5Utils.md5(password, "UTF-8");
        logger.debug("-------------password: " + password);
        Criteria example = new Criteria();
   		example.put("userName", userName);
   		example.put("password", password);
        int count = tbWebUserService.updateUserPassword(example);
        logger.debug("-------------count: " + count);
        request.getSession().removeAttribute(HglContants.SESSION_USERNAME);
    	return "/login/";
    }
    
    /**
     * 跳转到输入新密码页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/backNewPasswordUrl")
   	public String backNewPasswordUrl(HttpServletRequest request,HttpServletResponse response){
    	return "index/passwordSubmit";
    }
    
    /**
     * 跳转到找回密码页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/backPasswordUrl")
   	public String backPasswordUrl(HttpServletRequest request,HttpServletResponse response){
    	return "index/backPassword";
    }
    
    @RequestMapping("/displayImage")
    public String displayImage(Integer id,String imageName,HttpServletRequest request,HttpServletResponse response){
    	ImageUtil.showWapHeaderImage(id, imageName, request, response);
    	return null;
    }
    
    @RequestMapping("/reject")
    public String reject(Integer id,ModelMap model){
    	TbUserInfo userInfo = null;
    	List<ServiceTypeDto> list = null;
    	TbWebUser webUser = tbWebUserService.selectByPrimaryKey(id);
    	if(webUser!=null){
    		userInfo = userInfoService.selectByPrimaryKey(webUser.getUserinfoId());
    		if(userInfo!=null){
    			list = serviceTypeService.getSkills(userInfo.getId());
    		}  		
    	}
    	model.addAttribute("webUser", webUser);
    	model.addAttribute("userInfo", userInfo);
    	model.addAttribute("serviceTypeDtos", list);
    	return "redirect:register/reject";
    }
    
    private void countValues(WebUserDto webUserDto){
		if(StringUtils.isNotBlank(webUserDto.getOpenId()) && StringUtils.isBlank(webUserDto.getMobile()) && webUserDto.getUserName().startsWith("temp_user_")){
			session.setAttribute(HglContants.BIND_WEIXIN_FLAG, true);
		}
		int infoCountSum = 0;
		infoCountSum = tbStandLetterService.selectLetterCount(getLoginUserId());
		/*未完成的服务订单数量*/
		Criteria c = new Criteria();
		TbWebUser webUser = this.getLoginUser();
		if(webUser.getTypeId().equals(HglContants.USER_TYPE_CUS)){
			String str = "810";
			List<String> arrList = Arrays.asList(str);
			c.put("statusList", arrList);
			c.put("masterId", this.getLoginUserId());
			int unCompleteCnt = wapOrderServiceService.selectUnCompleteOrderCount(c);
			infoCountSum = infoCountSum + unCompleteCnt;
			/*model.addAttribute("unCompleteCnt", unCompleteCnt);*/
		}else if(webUser.getTypeId().equals(HglContants.USER_TYPE_MASTER)){
			String str = "802,804,806";
			List<String> arrList = Arrays.asList(str.split(","));
			c.put("statusList", arrList);
			c.put("repairmanId", this.getLoginUserId());
			int unCompleteCnt = wapOrderServiceService.selectUnCompleteOrderCount(c);
			infoCountSum = infoCountSum + unCompleteCnt;
		}	
		/*未完成的材料订单*/
		c = new Criteria();
		String str = "602,610,612,616";
		List<String> stList = Arrays.asList(str.split(","));
		c.put("statusList", stList);
		c.put("buyId", this.getLoginUserId());
		int unCompleteOrderCnt = wapOrderGroupService.selectUnCompleteOrder(c);
		infoCountSum = infoCountSum + unCompleteOrderCnt;
		session.setAttribute(HglContants.INFO_COUNT_SUM, infoCountSum);
    }
}
