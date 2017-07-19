package com.linkon.hgl.web.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.QRCodeUtil;
import com.liguo.hgl.proxydao.dto.UserRecommendedDto;
import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbStandLetterService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.service.TbWapOrderServiceService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.MD5Utils;




@Controller
@RequestMapping("personalCenter")
public class PersonalCenterController extends IBaseController {
	
	@Autowired
	private TbUserInfoService userInfoService;
	
	@Autowired
	private TbWebUserService webUserService;
	
	@Autowired
	private TbStandLetterService tbStandLetterService;
	
	@Autowired
	private TbWapOrderServiceService wapOrderServiceService;
	
	@Autowired
	private TbWapOrderGroupService wapOrderGroupService;
	
	@Autowired
	private TbShopInfoService shopInfoService;

	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		WebUserDto webUserDto = this.getLoginUser();
		if(StringUtils.isNotBlank(webUserDto.getOpenId()) && StringUtils.isBlank(webUserDto.getMobile()) && webUserDto.getUserName().startsWith("temp_user_")){
			model.addAttribute("bindingWeixinFlag", true);
		}
		model.addAttribute("webUserDto", webUserDto);
		
		int letterCount = tbStandLetterService.selectLetterCount(getLoginUserId());
		model.addAttribute("letterCount", letterCount);
		
		/*未完成的服务订单数量*/
		int unCompleteCnt = 0;
		Criteria c = new Criteria();
		TbWebUser webUser = this.getLoginUser();
		if(webUser.getTypeId().equals(HglContants.USER_TYPE_CUS)){
			String str = "810";
			List<String> arrList = Arrays.asList(str);
			c.put("statusList", arrList);
			c.put("masterId", this.getLoginUserId());
			unCompleteCnt = wapOrderServiceService.selectUnCompleteOrderCount(c);
			model.addAttribute("unCompleteCnt", unCompleteCnt);
		}else if(webUser.getTypeId().equals(HglContants.USER_TYPE_MASTER)){
			String str = "802,804,806";
			List<String> arrList = Arrays.asList(str.split(","));
			c.put("statusList", arrList);
			c.put("repairmanId", this.getLoginUserId());
			unCompleteCnt = wapOrderServiceService.selectUnCompleteOrderCount(c);
			model.addAttribute("unCompleteCnt", unCompleteCnt);
		}	
		/*未完成的材料订单*/
		c = new Criteria();
		String str = "602,610,612,616";
		List<String> stList = Arrays.asList(str.split(","));
		c.put("statusList", stList);
		c.put("buyId", this.getLoginUserId());
		int unCompleteOrderCnt = wapOrderGroupService.selectUnCompleteOrder(c);
		model.addAttribute("unCompleteOrderCnt", unCompleteOrderCnt);
		session.setAttribute(HglContants.INFO_COUNT_SUM, unCompleteOrderCnt + unCompleteCnt + letterCount);
	}
	
	/**
	 * 绑定微信跳转
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
    @RequestMapping("/bindingWeixinUser")
    public String bindingWeixinUser() throws Exception {
    	return "personalCenter/bindingWeixinUser";
    }

	@Override
	public String doSearchResult() {
		return null;
	}
	
	/**
	 * wap退出当前用户
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        WebUserDto user = (WebUserDto)session.getAttribute(HglContants.SESSION_KEY);
        //如果绑定了自动登录，在退出时候解除绑定
        if(user !=null && "1".equals(String.valueOf(user.getAutoLoginFlag()))){
        	webUserService.updateUserAutoLoginFlag(user.getId(),0);
        }
        session.removeAttribute(HglContants.SESSION_KEY);
        session.removeAttribute(HglContants.INFO_COUNT_SUM);
        return "redirect:/login/";
    }
       
    @RequestMapping("/myRecommended")
	 public String myRecommended(Model model){
		 if(getLoginUser()!=null){
			 model.addAttribute("code", getLoginUser().getLogoCode());
			 List<UserRecommendedDto> recommendedDtos = webUserService.selectRecommended(getLoginUser().getLogoCode());
			 model.addAttribute("recommendedDtos", recommendedDtos);
		 }
		return "personalCenter/myRecommended";
	}
    
    /*初始化修改用户信息*/
    @RequestMapping("/doInitUpdateWebUser")
    public String doInitUpdateWebUser(ModelMap model){
    	model.addAttribute("webUserDto", this.getLoginUser());
    	return "personalCenter/updateWebUser";
    }
    
    /*验证手机号是否被占用*/
    @RequestMapping("/doCheckMobile")
    @ResponseBody
    public String doCheckMobile(String mobile){
    	logger.debug("验证手机号:"+mobile+" 是否已经被占用");
    	TbWebUser webUser = this.getLoginUser();
    	if(webUser.getMobile().equals(mobile)){
    		return String.valueOf(true);
    	}else{
    		Criteria criteria = new Criteria();
    		criteria.put("mobile", mobile);
    		List<TbWebUser> list = webUserService.selectByObject(criteria);
    		if(list.size()>0){
    			return String.valueOf(false);
    		}else{
    			return String.valueOf(true);
    		}
    	}   	 	
    }
    
    @RequestMapping("/doUpdateWebUser")
    public String doUpdateWebUser(WebUserDto webUserDto){
    	
    	logger.debug("将用户昵称修改为:"+webUserDto.getName()+", 电话号码修改过为:"+webUserDto.getMobile());
    	
    	TbWebUser webUser = this.getLoginUser();
    	if(webUser!=null){
    		webUser.setName(webUserDto.getName());
    		webUser.setMobile(webUserDto.getMobile());
    		if(webUserService.updateByPrimaryKey(webUser)==1){
    			return "personalCenter/personalCenter";
    		}
    	}   	
    	return "personalCenter/personalCenter";
    }
        
    
    /*修改用户头像*/
    @RequestMapping("/doUploadHeaderImage")
    public String doUploadHeaderImage(HttpServletRequest request,ModelMap model){
    	MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest)request;
    	MultipartFile multImage = multipartRequest.getFile("image");
    	String imageNameOrg = multImage.getOriginalFilename();
    	String image = StringUtil.changeFileName("image", imageNameOrg);
    	try {
			if(HglContants.USER_TYPE_CUS.equals(this.getLoginUser().getTypeId())||HglContants.USER_TYPE_MASTER.equals(this.getLoginUser().getTypeId())){
				TbUserInfo  userInfo = userInfoService.selectByPrimaryKey(this.getUserinfoId());
				if(userInfo!=null){            	
					userInfoService.uploadImage(multImage, image, String.valueOf(userInfo.getId()));
					userInfo.setImage(image);
					userInfoService.updateByPrimaryKey(userInfo); 
					//个人更新头像时更新推荐二维码
					String userImageUrl = HglContants.USER_PATH + userInfo.getId() + File.separator + image;
					QRCodeUtil.createLogoCodeImage(this.getLoginUser().getRecommendShopId(),this.getLoginUser().getLogoCode(),userImageUrl);
				}
			}else if(HglContants.USER_TYPE_DISTRIBUTOR.equals(this.getLoginUser().getTypeId())){
				TbShopInfo shopInfo = shopInfoService.selectByPrimaryKey(this.getLoginUser().getShopId());
				if(shopInfo!=null){
					shopInfoService.uploadShopInfoImage(multImage, image, String.valueOf(shopInfo.getId()));
					shopInfo.setShopImage1(image);
					shopInfoService.updateByPrimaryKey(shopInfo);
					
					//取场景图片地址，生成二维码
					String shopImageUrl = HglContants.SHOP_INFO_PATH+shopInfo.getId()+File.separator+image;
					QRCodeUtil.createShopQrCode(shopInfo.getId(),shopImageUrl);//店铺二维码
					QRCodeUtil.createLogoCodeImage(this.getLoginUser().getRecommendShopId(),this.getLoginUser().getLogoCode(),shopImageUrl);//推荐二维码
				}
			}
		} catch (IOException e) {
			logger.debug("图像修改失败");
			e.printStackTrace();
		}   	
    	model.addAttribute("webUserDto", this.getLoginUser());
    	   	
    	return "personalCenter/personalCenter";
    }
    
    
    @RequestMapping("/goSaveShopInfo")
    public String goSaveShopInfo(Model model,Integer sId,String recommendCode){
		QRCodeUtil.createSaveInfoCodeImage(sId,recommendCode);
		model.addAttribute("recommendCode", recommendCode);
		model.addAttribute("saveShopCode", recommendCode+"_"+sId);
    	return "personalCenter/goSaveShopInfo";
    	
    }
    /*显示头像*/
    @RequestMapping("/showHeaderImage")
    public String showHeaderImage(HttpServletRequest request,HttpServletResponse response){
    	TbWebUser webUser = this.getLoginUser();
    	if(HglContants.USER_TYPE_CUS.equals(webUser.getTypeId())||HglContants.USER_TYPE_MASTER.equals(webUser.getTypeId())){
    		TbUserInfo userInfo = userInfoService.selectByPrimaryKey(this.getUserinfoId());
    		if(userInfo!=null && StringUtil.isNotBlank(userInfo.getImage())){
        		//判断是否是http开头的,是http开头是微信头像
    			if(userInfo.getImage().startsWith("http")){
    				return "redirect:"+userInfo.getImage();
    			}else{
    				ImageUtil.showWapHeaderImage(userInfo.getId(),userInfo.getImage(),request,response);
    			}
        	}else{
        		return "redirect:/images/use.jpg";
        	}
    	}else if(HglContants.USER_TYPE_DISTRIBUTOR.equals(webUser.getTypeId())){
    		TbShopInfo shopInfo = shopInfoService.selectByPrimaryKey(webUser.getShopId());
    		if(shopInfo!=null && StringUtils.isNotBlank(shopInfo.getShopImage1())){
    			if(shopInfo.getShopImage1().startsWith("http")){
    				return "redirect:"+shopInfo.getShopImage1();
    			}else{
    				ImageUtil.showImageShop(shopInfo.getId(), shopInfo.getShopImage1(), response);

    			}
    		}else{
    			return "redirect:/images/use.jpg";
    		}
    	}  	
    	return null; 	
    }
    
    /*验证旧密码是否输入正确*/
    @RequestMapping("/doCheckPassword")
    @ResponseBody
    public String doCheckPassword(String password){
    	TbWebUser webUser= this.getLoginUser();
    	if(webUser!=null && StringUtils.isNotBlank(password)){
    		String pwd = MD5Utils.md5(password, "UTF-8");
    		if(pwd.equals(webUser.getPassword())){
    			return String.valueOf(true);
    		}
    	}    	
    	return String.valueOf(false);
    }
    
    /*修改密码*/
    @RequestMapping("/doUpdatePassword")
    public String doUpdatePassword(String password,ModelMap model){
    	//验证新旧密码输入是否一致
    	if(StringUtils.isNotBlank(password)){
    		webUserService.updatePassword(this.getLoginUser(), password);
        	model.addAttribute("webUserDto", this.getLoginUser());
    	}
    	
      	return "personalCenter/personalCenter";
    }
    
    @RequestMapping("/logoCode")
    public String logoCodeImage(ModelMap model,String code,HttpServletResponse response) {
    	ImageUtil.showImageLogoCode(code,response);
        return null;
    }
    	
}
