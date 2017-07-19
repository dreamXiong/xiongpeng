package com.linkon.hgl.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.UserRecommendedDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbExperience;
import com.liguo.hgl.proxydao.model.TbShopLevel;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbExperienceService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbShopLevelService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;
/**
 * 等级和经验值,邀请分享前台查询
 * @fiLevelExperienceController.java
 * @2016-5-9	
 * @author 周双双
 */
@Controller
@RequestMapping("level")
public class LevelExperienceController extends IBaseController{
	@Autowired
	protected TbShopLevelService levelService;
	
	@Autowired
	protected TbExperienceService experienceService;
	
	@Autowired
	protected TbShopInfoService tbShopInfoService;
	
	@Autowired
	protected TbWebUserService webUserService;
	
	/**
	 * 我的等级
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value="/level")
     public ModelAndView getSuppliers(){
    	ModelAndView modelAndView = new ModelAndView();
    	List<TbShopLevel> leveList = levelService.selectByObject(null);
    	if(leveList.size()>0){
    		modelAndView.addObject("leveList", leveList);
    		modelAndView.addObject("shopName", getShopInfoName());
    		modelAndView.addObject("experience",getMyExperience());
    	}
    	modelAndView.setViewName("myOrderGroup/myLevel");
        return modelAndView;
     }
	 
	 /**
	  * 我的经验值
	  * @return
	  * @author zss
	  */
	 private int getMyExperience(){
		 if(getShopId() !=null){
			 return tbShopInfoService.selectByPrimaryKey(getShopId()).getExp();
		 }
		 return 0;
		// return experienceService.getMyExperience(getLoginUserId());
	 }
	
	 /**
	  * 我的店铺名称
	  * @return
	  * @author zss
	  */
	private String getShopInfoName(){
		 if(getShopId() !=null){
			 return tbShopInfoService.selectByPrimaryKey(getShopId()).getShopName();
		 }
		 return null;
	}	
		
	/**
	 * 经验值初始页面
	 */
	 @RequestMapping(value="/experience")
     public ModelAndView getExperience(PageDto page){
    	 ModelAndView modelAndView = new ModelAndView();
		Criteria criteria = new Criteria();
		criteria.put("userId", getLoginUserId());
	    //初始化列表信息
		List<TbExperience> experienceList =  experienceService.selectExperienceList(criteria,page);
		modelAndView.addObject("experienceList", experienceList);
		modelAndView.addObject("experience",  getMyExperience());
		modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
    	modelAndView.setViewName("myOrderGroup/myExperience");
        return modelAndView;
     }
	 
	/**
	 * 经验值初始页面
	 */
	 @RequestMapping(value="/serachExperience")
     public ModelAndView serachExperienceList(HttpServletRequest request,PageDto page){
    	 ModelAndView modelAndView = new ModelAndView();
		Criteria criteria = new Criteria();
		criteria.put("userId", getLoginUserId());
	//	 myOrderForm.setStartTime(setDate(myOrderForm.getStartTime(),"yyyy-MM-dd").toString());
		Long startTime = setDate(request.getParameter("startTime").toString(),"yyyy-MM-dd");
		Long endTime = setDate(request.getParameter("endTime"),"yyyy-MM-dd")+86400000;
		criteria.put("startTime", startTime);
		criteria.put("endTime", endTime);
		List<TbExperience> experienceList =  experienceService.selectExperienceList(criteria,page);
		modelAndView.addObject("experienceList", experienceList);
		modelAndView.addObject("experience", getMyExperience());
		modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
    	modelAndView.setViewName("myOrderGroup/myExperienceList");
        return modelAndView;
     }
     /**
      * 设置时间
      * @param time
      * @param fomart
      * @return
      * @author zss
      */
	 private Long setDate(String time,String fomart) {
			SimpleDateFormat sdf = new SimpleDateFormat(fomart);
			long millionSeconds = 0;
			try {
				millionSeconds = sdf.parse(time).getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//毫秒
			return millionSeconds ;
    }
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
	
		
	}

	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 我的推荐
	 * @param model
	 * @return
	 * @author zss
	 */
	 @RequestMapping("/myRecommended")
	 public String myRecommended(Model model){
		 if(getLoginUser()!=null){
			 model.addAttribute("code", getLoginUser().getLogoCode());
			 List<UserRecommendedDto> recommendedDtos = webUserService.selectRecommended(getLoginUser().getLogoCode());
			 model.addAttribute("recommendedDtos", recommendedDtos);
			 model.addAttribute("isJxs", getLoginUser().getTypeId().equals(HglContants.USER_TYPE_DISTRIBUTOR));
		 }
		return "myOrderGroup/myRecommended";
	}
	 /**
	  * 我的二维码
	  * @param model
	  * @param code
	  * @param response
	  * @return
	  * @author zss
	  */
	 @RequestMapping("/logoCode")
	    public String logoCodeImage(ModelMap model,String code,HttpServletResponse response) {
	    	ImageUtil.showImageLogoCode(code,response);
	        return null;
	    }
}
