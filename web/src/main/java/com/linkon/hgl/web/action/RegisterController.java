package com.linkon.hgl.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.MD5Utils;
import com.liguo.hgl.util.SmsSend;
import com.liguo.hgl.util.StringUtil;
import com.linkon.hgl.web.common.StrUtil;

/**
 * 用户注册
 * @fiRegisterController.java
 * @2016-4-18	
 * @author 周双双
 */
@Controller
@RequestMapping("register")
public class RegisterController extends IBaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
	
	@Autowired
	protected TbWebUserService tbWebUserService;
	
	@Autowired
	protected TbShopInfoService shopInfoService;
	
	@Autowired
	protected IProductTypeService productTypeService;
	
	@Autowired
	private TbAccountService tbAccountService;
	
	/**
	 * 注册，普通用户初始页
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value="/register",method=RequestMethod.GET)
	 public ModelAndView getRegister(){
	        return this.getRegister("user/register");
	 }
	 /**
	  * 厂家注册初始页
	  * @param modelAndView
	  * @return
	  * @author zss
	  */
	 @RequestMapping(value="/cjregister",method=RequestMethod.GET)
	 public ModelAndView getCjRegister(ModelAndView modelAndView){
		 TbProductType tbProductType = new TbProductType();
		 tbProductType.setParentId(0);
		 modelAndView.addObject("productTypes",  productTypeService.selectByTbProductType(tbProductType));
		 modelAndView.addObject("provinceInfos", tbProvinceInfoService.selectByObject(null));
	     modelAndView.setViewName("user/cjregister");
	      return modelAndView;
	  }
	 /**
	  * 经销商注册初始页
	  * @return
	  * @author zss
	  */
	 @RequestMapping(value="/jxregister",method=RequestMethod.GET)
	 public ModelAndView getJxRegister(ModelMap model){
		 
		 TbProductType tbProductType = new TbProductType();
		 tbProductType.setParentId(0);
		 tbProductType.setMainId(0);
		 List<TbProductType> tbProductTypeList = productTypeService.selectByTbProductType(tbProductType);
		 model.addAttribute("tbProductTypeList",tbProductTypeList);
	     return this.getRegister("user/jxregister");
	  }
	 
	 private ModelAndView getRegister(String url){
		 ModelAndView modelAndView = new ModelAndView();
	    	modelAndView.addObject("provinceInfos", tbProvinceInfoService.selectByObject(null));
	    	modelAndView.setViewName(url);
	        return modelAndView;
	 }
	/**
	 * 普通用户注册
	 * @param session
	 * @param redirect
	 * @param param
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/register")
	@ResponseBody
	public ModelAndView register(RedirectAttributes redirect,@RequestParam Map<String, Object> param){
		
		String url="register";
		ModelAndView modelAndView = registerCheckVcode(redirect,param,url);
		if(modelAndView.isEmpty()){;
			int count =tbWebUserService.register(param);
			if(count==0){
				redirect.addFlashAttribute("errmsg", "checkEmpty");
				modelAndView.setViewName("redirect:"+url);
				return modelAndView;
			}else{
				registerLogin(param);
			}
		}
		
		modelAndView.setViewName("redirect:register");
		return modelAndView;
	}
	private void registerLogin(Map<String, Object> param) {
		String userName = param.get("userName").toString();
		String pwd = MD5Utils.md5(param.get("password").toString(), "UTF-8");
		WebUserDto tbWebUser = tbWebUserService.selectByUsernameAndPwd(userName,pwd);
		if (tbWebUser != null) {
			session.setAttribute(HglContants.SESSION_KEY, tbWebUser);
			session.setAttribute(HglContants.SESSION_CART, "0");//保存session购物车的数量
		}
	}
	/**
	 * 验证手机验证码和图片验证码
	 * @param redirect
	 * @param param
	 * @param url
	 * @return
	 * @author zss
	 */
	public ModelAndView registerCheckVcode(RedirectAttributes redirect,@RequestParam Map<String, Object> param,String url){
		ModelAndView modelAndView = new ModelAndView();
		/* 验证码 */
		String verify = param.get("vcodeImage").toString().trim();
		
		if (verify == null || !verify.equalsIgnoreCase((String) this.session.getAttribute("verify"))) {
			redirect.addFlashAttribute("errmsg", "vcodeImageEmpty");
			modelAndView.setViewName("redirect:"+url);
			return modelAndView;
		}
		String vcodePhone =param.get("vcodePhone").toString().trim();
		if(!StrUtil.hasText(vcodePhone) || vcodePhone.length()!=4 || !vcodePhone.equalsIgnoreCase((String) session
				.getAttribute("vodeMobile"))){
			redirect.addFlashAttribute("errmsg", "vcodePhoneEmpty");
			modelAndView.setViewName("redirect:"+url);
			return modelAndView;
		}
		return modelAndView;
	}
	
	/**
	 * 厂家注册
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/cjregister")
	@ResponseBody
	public ModelAndView cjregister(RedirectAttributes redirect,@RequestParam Map<String, Object> param
			){
		logger.debug("cj---param:"+param);
		String url="cjregister";
		ModelAndView modelAndView = registerCheckVcode(redirect,param,url);
		if(modelAndView.isEmpty()){;
			int count=tbWebUserService.cjregister(param);
			if(count==0){
				redirect.addFlashAttribute("errmsg", "checkEmpty");
				modelAndView.setViewName("redirect:"+url);
				return modelAndView;
			}else{
				registerLogin(param);
			}
		}
		modelAndView.setViewName("redirect:cjregister");
		return modelAndView;
	}

	/**
	 * 经销商注册
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/jxregister")
	@ResponseBody
	public ModelAndView jxregister(RedirectAttributes redirect,@RequestParam Map<String, Object> param){
		String url="jxregister";
		ModelAndView modelAndView = registerCheckVcode(redirect,param,url);
		if(modelAndView.isEmpty()){;
			int count=tbWebUserService.jxregister(param);
			if(count==0){
				redirect.addFlashAttribute("errmsg", "checkEmpty");
				modelAndView.setViewName("redirect:"+url);
				return modelAndView;
			}else{
				registerLogin(param);
			}
		}
		modelAndView.setViewName("redirect:/webuser/doInitEditWebUser");
		return modelAndView;
	}
	/**
	 * 验证用户名
	 * @param username
	 * @return
	 * @author zss
	 */
	 @ResponseBody
	 @RequestMapping(value="/checkName")
	 public Map<String,Object> checkName(@RequestParam String username){
		 Map<String,Object> map = tbWebUserService.checkName(username);
	 	
	 	return map;
	 }
	 
	 /**
	  * 验证手机号码
	  * @param mobile
	  * @return
	  * @author zss
	  */
	 @ResponseBody
	 @RequestMapping(value="/checkMobile")
	 public Map<String,Object> checkMobile(@RequestParam String mobile){
		 Map<String,Object> map = tbWebUserService.checkMobile(mobile);
	 	return map;
	 }
	 
	 /**
	  * 校验此大类下品牌名称是否存在
	  * @param productTypeId 大类id
	  * @param brandName 品牌名称
	  * @return
	  * @author zss
	  */
	 @ResponseBody
	 @RequestMapping(value="/checkBranName")
	 public Map<String,Object> checkBranName(@RequestParam Integer productTypeId,@RequestParam String brandName){
		 Map<String,Object> map = tbWebUserService.checkBranName(productTypeId,brandName);
	 	return map;
	 }
	 /**
	  * 获取手机验证码
	  * @param request
	  * @param type 用户类型
	  * @param phoneNum //手机号码
	  * @return
	  * @author zss
	  */
	 @ResponseBody
	 @RequestMapping(value="/getVodePhone")
	 public String getVodePhone(HttpServletRequest request,String type,String phoneNum){
		String vode= StrUtil.getRandom4Number();
		logger.debug("phoneNum="+phoneNum+";type="+type+";vode="+vode);
		String sendRegestSms = SmsSend.sendRegestSms(phoneNum, vode, type);
		if("".equals(sendRegestSms)){
			HttpSession session = request.getSession(true);
			session.setAttribute("vodeMobile", vode);
		}
		return sendRegestSms;
	 }
	 
	 /**
	  * 图片验证码
	  * @param imgFile
	  * @param request
	  * @param imgNo
	  * @param model
	  * @return
	  * @author zss
	  */
	    @RequestMapping(value="/validateImg")
	    @ResponseBody
	    public Map<String,Object> validate(@RequestParam CommonsMultipartFile imgFile,HttpServletRequest request ,String imgNo,Model model){
	      String img = imgNo;
	  	  Map<String, Object> map = new HashMap<String, Object>();
	  	  String imgName = imgFile.getOriginalFilename();
	  	  map.put("code", HglContants.SUCCESS);
	  	  long imgSize = imgFile.getSize();
	  	  if(imgSize > 5242880){
	 		//图片过大 不超过5MB
	 		map.put("code", HglContants.FAIL);
	 		return map;
	 	  }
	  	  long name = System.currentTimeMillis();
	  	  String newName = StringUtil.changeFileName(img+"_"+name,imgName);
	  	  try{
	  		 productTypeService.uploadForm(imgFile,newName,HglContants.SHOP_INFO_TESTPATH);
	  	  }catch(Exception e){
	  		  logger.debug("文件上传失败.....");
	  		  e.printStackTrace();
	  	  }
	  	  map.put("imgNo",newName);
	  	  model.addAttribute(imgNo,imgNo);
	  	  return map;
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
	  
		/**
		 * 判断验证码是否输入正确
		 * @param identCode
		 * @return
		 * @author zss
		 */
		@RequestMapping("/checkVerify")
		@ResponseBody
		public String checkVerify(String identCode){
			String verify = session.getAttribute("verify").toString();
			if(!verify.equalsIgnoreCase(identCode)){
				return String.valueOf(false);
			}else{
				return String.valueOf(true);
			}
		}
		
		/**
		 * 判断短信验证码是否输入正确
		 * @param identCode
		 * @return
		 * @author zss
		 */
		@RequestMapping("/checkPhoneCode")
		@ResponseBody
		public String checkvodeMobile(String identCode){
			String verify = session.getAttribute("vodeMobile").toString();
			if(!verify.equalsIgnoreCase(identCode)){
				return String.valueOf(false);
			}else{
				return String.valueOf(true);
			}
		}
		/**
		 * 验证推荐码是否存在
		 * @param recommendCode
		 * @return
		 * @author zss
		 */
		 @ResponseBody
		 @RequestMapping(value="/checkRecommedCode")
		 public Map<String,Object> checkRecommedCode(@RequestParam String recommendCode){
			 Map<String,Object> map = tbWebUserService.checkRecommedCode(recommendCode);
		 	return map;
		 }
}
