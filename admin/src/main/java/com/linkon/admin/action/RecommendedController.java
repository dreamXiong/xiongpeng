package com.linkon.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.proxydao.model.TbRecommendedType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbRecommendedTypeService;

/**
 * 平台推荐规则
 * @fiRecommendedController.java
 * @2016-7-7	
 * @author 周双双
 */
@Controller
@RequestMapping("/recommended")
public class RecommendedController extends IBaseController{

	@Autowired
	private TbRecommendedTypeService tbRecommendedTypeService;
	
	/**
	 * 查询平台推荐经销商规则
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value="/recommended")
     public ModelAndView getSuppliers(){
    	ModelAndView modelAndView = new ModelAndView();
    	List<TbRecommendedType> recommendedTypes = tbRecommendedTypeService.selectByObject(null);
    	if(recommendedTypes.size()>0){
    		modelAndView.addObject("recommendedTypes", recommendedTypes);
    	}
    	modelAndView.setViewName("shop/recommended");
        return modelAndView;
     }
	 
	 /**
	  * 
	  * @param rulesId
	  * @return
	  * @author zss
	  */
	  @RequestMapping(value="/recommendedTypes")
	  public ModelAndView getShopRules(Integer rId){
	    	ModelAndView modelAndView = new ModelAndView();
	    	if(rId !=null){
	    		
	    	TbRecommendedType recommendedType = tbRecommendedTypeService.selectByPrimaryKey(rId);
	    	modelAndView.addObject("recommendedType", recommendedType);
	    	
	    	}
	    	modelAndView.setViewName("shop/updateRecommendedTypes");
	        return modelAndView;
	 } 
	  
	  @RequestMapping(value="/updateRecommended",method=RequestMethod.POST)
	  public ModelAndView updateRecommended(TbRecommendedType recommendedType){
	  	ModelAndView modelAndView = new ModelAndView();
	  	recommendedType.setUpdateDt(System.currentTimeMillis());
	  	recommendedType.setUpdateBy(getLoginUserId());
	  	tbRecommendedTypeService.updateByPrimaryKeySelective(recommendedType);
	  	modelAndView.setViewName("redirect:/recommended/recommended");
	      return modelAndView;
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
