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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralRules;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebPermission;
import com.liguo.hgl.proxydao.model.TbWebRole;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbIntegralRulesService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbRecommendedService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbShoppingCartService;
import com.liguo.hgl.service.TbStandLetterService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWebPermissionService;
import com.liguo.hgl.service.TbWebRoleService;
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
@SessionAttributes({"parentList","letterCount"})
public class LoginController extends IBaseController {

    // private final String userName = "admin";
    // private final String pwd = "lcjt12#";
    
    // 设置字母的大小,大小
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final String LOGOUT = "redirect:/index";
    private Font mFont = new Font("Times New Roman", Font.PLAIN, 20);

    @Autowired
    private TbWebUserService tbWebUserService;
    
    @Autowired
    protected TbShoppingCartService tbShoppingCartService;
    
    @Autowired
    private TbUserInfoService userInfoService;
    
   /* @Autowired
    private ShopInfoController shopInfroController;*/
    
    @Autowired
    private TbRecommendedService tbRecommendedService;
    
    @Autowired
    protected TbShopInfoService tbShopInfoService;
    
    @Autowired
   	private TbAccountService tbAccountService;
    
    @Autowired
    private TbWebPermissionService tbWebPermissionService;
    
    @Autowired
    private TbWebRoleService tbWebRoleService;
    
    @Autowired
   	private TbStandLetterService tbStandLetterService;
    
    @Autowired
	protected IProductTypeService productTypeService;
    
    @Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
    
    @Autowired
    protected TbIntegralRulesService tbIntegralRulesService;
    
    // childNode属性为0，表示该菜单是父菜单
    private static final String CHILD_NODE = "0";

    @RequestMapping(value = "/index")
    public String indexPage(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
        /*init(page, request, response, model);*/
    	String userName = getCookie(request,HglContants.COOKIE_USERNAME);
    	if(userName != null){
    		model.addAttribute("userName", userName);
    	}
        return "index/login";
    }
    
    /**
     * 经销商审核拦截
     * @param page
     * @param request
     * @param response
     * @param modelAndView
     * @return
     * @author zss
     */
    @RequestMapping(value = "/shop")
    public ModelAndView shop(PageDto page, HttpServletRequest request,HttpServletResponse response, ModelAndView modelAndView) {
        /*init(page, request, response, model);*/
	   	 if(getShopId() !=null){
			 Map<String,Object> map=tbWebUserService.isApproved(getLoginUserId());
			logger.debug("------------errcode:"+map.get("errcode"));
			 modelAndView.addObject("errcode", map.get("errcode"));
			 modelAndView.addObject("msg", map.get("msg"));
			 if(map!=null && map.get("errcode").equals("2")){
				 TbProductType tbProductType = new TbProductType();
				 tbProductType.setParentId(0);
				 modelAndView.addObject("productTypes",  productTypeService.selectByTbProductType(tbProductType));
		    	 ShopWebUserDto shopDto  = tbWebUserService.getShopWebUser(getShopId());
		    	if(shopDto!=null){
		    		modelAndView.addObject("shopDto", shopDto);
		    		modelAndView.addObject("provinceInfos", tbProvinceInfoService.selectByObject(null));
		    	}
			 }
		 }
		
		modelAndView.setViewName("webuser/shopEdit");
        return modelAndView;
    }
    /**
     * 经销商重新提交审核资料
     * @param param
     * @return
     * @author zss
     */
    @RequestMapping(value="/shopEdit",method=RequestMethod.POST) 
    public ModelAndView updateSuppliers(@RequestParam Map<String, Object> param){
		
    	ModelAndView modelAndView = new ModelAndView();
    	tbWebUserService.updateShopByUser(param, getShopId(),getLoginUserId());
    	modelAndView.setViewName("redirect:shop");
        return modelAndView;
     }
    /**
     * 显示经销商图片
     * @param model
     * @param id 经销商shopid
     * @param imgName 图片名称
     * @param response
     * @return
     * @author zss
     */
    @RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageShop(id,imgName,response);
        return null;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpSession session,HttpServletResponse response,
            ModelMap model) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        /* 验证码 */
        /*String verify = request.getParameter("verify");
        if (verify == null
                || !verify.equalsIgnoreCase((String) session
                        .getAttribute("verify"))) {
            model.put("error", "验证码不正确");
            return "index/login";
        }*/
        
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        if(pwd!=null){
            pwd = MD5Utils.md5(pwd, "UTF-8");
        }
        logger.debug(pwd);
        WebUserDto tbWebUser = tbWebUserService.selectByUsernameAndPwd(userName,pwd);
        if(tbWebUser != null &&(HglContants.USER_TYPE_CUS.equals(tbWebUser.getTypeId())||HglContants.USER_TYPE_MASTER.equals(tbWebUser.getTypeId()))){
        	model.put("error", "用户名密码不匹配");
        	return "index/login";
        }
        
        if (tbWebUser != null &&(tbWebUser.getState().equals(HglContants.WEB_AUTH_STATUS1))){  //审核通过
        /*	session.setAttribute(HglContants.SETTLEMENT_KEY, tbShopInfoService.selectByPrimaryKey(tbWebUser.getRecommendShopid()).getSettlement());*/
            session.setAttribute(HglContants.SESSION_KEY, tbWebUser);
            //int cartNumber = tbShoppingCartService.getUserCartNumber(this.getLoginUserId()); //获取购物车数量
            //session.setAttribute(HglContants.SESSION_CART, String.valueOf(cartNumber));//保存session购物车的数量
            
           //如果是店铺
            if(tbWebUser.getTypeId().toString().equals(HglContants.USER_TYPE_DISTRIBUTOR.toString())){
            	insertInRule(tbWebUser.getId());
            }
    		/**添加菜单**/
    		viewPerList(model);
    		//记住账号保存cookie
    		String breapass = request.getParameter("breapass");
    		logger.info("---------------breapass: " + breapass);
    		if(StringUtils.isNotBlank(breapass)){
    			addCookie(response,HglContants.COOKIE_USERNAME,URLEncoder.encode(tbWebUser.getUserName(),"UTF-8"),30*24*3600);
    		}else{
    			deleteCookie(response,HglContants.COOKIE_USERNAME);
    		}
    		
            return "redirect:/webuser/doInitEditWebUser";
        }else if(tbWebUser != null &&(tbWebUser.getState().equals(HglContants.WEB_AUTH_STATUS))){  //待审核
        	session.setAttribute(HglContants.SESSION_KEY, tbWebUser);  
        	model.addAttribute("webUser",tbWebUser);
        	TbUserInfo userInfo = new TbUserInfo();
        	if(tbWebUser.getUserinfoId()!=null){
        		userInfo = userInfoService.selectByPrimaryKey(tbWebUser.getUserinfoId());
        	}
    		model.addAttribute("userInfo", userInfo);
    		/**添加菜单**/
            viewPerList(model);
        	return "/webuser/webuseredit";
        }else if(tbWebUser != null &&(tbWebUser.getState().equals(HglContants.WEB_AUTH_STATUS2))){//审核拒绝
        	 session.setAttribute(HglContants.SESSION_KEY, tbWebUser);
        	return "redirect:/login/shop";
        	
        }else if(tbWebUser != null &&(tbWebUser.getState().equals(HglContants.WEB_AUTH_STATUS3))){
            	
        	model.put("error", "您的账户已失效");
            return "index/login";
        }else {
            model.put("error", "用户名密码不匹配");
            return "index/login";
        }
    }
    /**
     * @Description:用户
    * @author:ZK 
    * @date:2016-8-8
    * @parameter:
     */
    private void insertInRule(Integer userId){
    	
    	TbShopInfo tbShopInfo = tbShopInfoService.selectTbShopInfoByUserId(userId);
    	
    	Criteria example = new Criteria();
		example.put("shopId",tbShopInfo.getId());
		List<TbIntegralRules> ruleList = tbIntegralRulesService.selectByObject(example);
		if(ruleList.size() < 1){
			List<TbIntegralRules> tList = new ArrayList<TbIntegralRules>();
			TbIntegralRules tbIntegralRules1 = new TbIntegralRules();
			tbIntegralRules1.setShopId(tbShopInfo.getId());
			tbIntegralRules1.setType(0);
			tbIntegralRules1.setMoney(0);
			tbIntegralRules1.setUseSituation(0);
			tbIntegralRules1.setPayMoney(0);
			tList.add(tbIntegralRules1);
			
			TbIntegralRules tbIntegralRules2 = new TbIntegralRules();
			tbIntegralRules2.setShopId(tbShopInfo.getId());
			tbIntegralRules2.setType(1);
			tbIntegralRules2.setMoney(0);
			tbIntegralRules2.setUseSituation(1);
			tbIntegralRules2.setPayMoney(0);
			for(int i=0 ; i<5 ; i++){
				tList.add(tbIntegralRules2);
			}
			tbIntegralRulesService.batchInsert(tList);
		}
    }
    
    
    private void viewPerList(ModelMap model) {
        // 加载菜单
        WebUserDto webUserDto = getLoginUser();
        String userName = webUserDto.getUserName();
        List<TbWebPermission> webPermissionList = null;
        // 超级管理员拥有所有权限
        if (HglContants.ADMIN_USER_NAME.equals(userName)) {
            Criteria c =new Criteria();
            webPermissionList = tbWebPermissionService.selectByObject(c);
        }
        else {
            Integer roleId = webUserDto.getRoleId();
            TbWebRole tbWebRole =tbWebRoleService.selectByPrimaryKey(roleId);
            // 用户没分配角色
            if (null == tbWebRole) {
                return ;
            }
            String perIds = tbWebRole.getPermissionIds();
            
            // 角色没有分配菜单
            if (StringUtil.isBlank(perIds)) {
                return ;
            }
            String[] perArray = perIds.split(",");
            List<String> perList = Arrays.asList(perArray);
            webPermissionList = tbWebPermissionService.selectByStringList(perList);
        }
        List<TbWebPermission> parentList = getMenuList(webPermissionList);
       // session.setAttribute("letterCount", tbStandLetterService.selectLetterCount(getLoginUserId()));
        logger.debug("letterCount--------  "+tbStandLetterService.selectLetterCount(getLoginUserId()));
        model.addAttribute("parentList", parentList);
        model.addAttribute("letterCount", tbStandLetterService.selectLetterCount(getLoginUserId()));
    }
    
    private List<TbWebPermission> getMenuList(List<TbWebPermission> webPermissionList) {
        List<TbWebPermission> parentList = new ArrayList<TbWebPermission>();
        Map<String, TbWebPermission> parentMap = new HashMap<String, TbWebPermission>();
        int listSize=0;
        TbWebPermission menu = null;
        listSize = webPermissionList.size();

        for (int i = 0; i < listSize; i++) {
            // for(Map<String, Object> tempList :AdminPermissionList){
            menu = webPermissionList.get(i);
            String childNode = String.valueOf(menu.getChildNode());
            // 父节点
            if (CHILD_NODE.equals(childNode)) {
                parentList.add(menu);
                parentMap.put(String.valueOf(menu.getId()), menu);
                // 在原来的查询结果上除去父节点
                webPermissionList.remove(i--);
                listSize--;
            }
        }
        menu = null;

        // 只剩下子节点
        for (TbWebPermission map : webPermissionList) {
            menu = parentMap.get(StringUtil.trim(String.valueOf(map.getParentNode())));
            if (menu == null) {
                continue;
            }
            menu.addChild(map);
        }
        parentMap.clear();
        webPermissionList.clear();
        return parentList;
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
            g.drawString(String.valueOf(ctmp), 18 * i + 10, 25);
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

    @Override
    public String doSearchResult() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        HttpSession session = request.getSession();
        //清空session
        session.setAttribute(HglContants.SESSION_KEY, null);
        session.setAttribute(HglContants.SESSION_PARENT_LIST_KEY, null);
        session.setAttribute("letterCount", null);
        return LOGOUT;
    }
	@RequestMapping("/rebate")
	public String rebate() throws TransactionException{
		tbRecommendedService.rebate();
		return null;
	}
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
	    Cookie cookie = new Cookie(name,value);
	    cookie.setPath("/");
	    cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}
	
	/**
	 * 删除cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void deleteCookie(HttpServletResponse response,String name){
	    Cookie cookie = new Cookie(name,null);
	    cookie.setPath("/");
	    cookie.setMaxAge(0);
	    response.addCookie(cookie);
	}
	
	/**
	 * 获取cookie值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request,String name) {
		try {
			Cookie[] cookies = request.getCookies();
			if(cookies != null && cookies.length>0){
				for(Cookie cookie : cookies){
					if(name.equals(cookie.getName())){
						return URLDecoder.decode(cookie.getValue(),"UTF-8");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		example.put("typeId", 104);
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
   		example.put("typeId", 104);
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
    	return "/login/index";
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
	
}
