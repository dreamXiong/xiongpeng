package com.linkon.admin.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbWebUserService;
/**
 * 前台用户controller类
 * @fiWebUserController.java
 * @2016-4-23	
 * @author 周双双
 */
@Controller
@RequestMapping("/dealers")
public class DealersController extends IBaseController{

	@Autowired
	protected TbWebUserService tbWebUserService;
	
	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
	
	@Autowired
	protected TbShopInfoService tbShopInfoService;
	
	/**
	 * 查询---经销商
	 * @param page
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value="/dealers")
     public ModelAndView getDealers(PageDto page){
    	ModelAndView modelAndView = new ModelAndView();
    	//List<TbwebUserShopDto> webList = tbWebUserService.getDealersUserList(new Criteria(),page);
    	List<ShopWebUserDto> webList = tbShopInfoService.getDealersUserList(new Criteria(),page);
    	if(webList.size()>0){
    		modelAndView.addObject("dealers", webList);
    	}
    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
    	modelAndView.setViewName("shop/dealers");
        return modelAndView;
     }
	 
		@RequestMapping(value="/serachDealers")
	    @ResponseBody
	    public ModelAndView getSerachDealers(HttpServletRequest request,PageDto page){
	    	Criteria criteria = new Criteria();
			String name = request.getParameter("shopName");
			if (!StringUtil.isEmpty(name)) {
				criteria.put("shopName", StringUtil.trim(name));
			}
			if(!StringUtil.isEmpty(request.getParameter("state"))){
				criteria.put("state",  Integer.parseInt(request.getParameter("state")));
			}
	    	ModelAndView modelAndView = new ModelAndView();
	    	List<ShopWebUserDto> dealers = tbShopInfoService.getDealersUserList(criteria,page);
	    	
	    		modelAndView.addObject("dealers", dealers);
	    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
	    	modelAndView.setViewName("shop/dealersUserList");
	        return modelAndView;
	    }
		 /**
		  * 经销商后台详情
		  * @param id
		  * @return
		  * @author zss
		  */
		 @RequestMapping(value="/dealersDetail")
	     public ModelAndView getDealersDetail(@RequestParam Integer id){
	    	ModelAndView modelAndView = new ModelAndView();
	    	ShopWebUserDto shopDto  = tbWebUserService.getShopWebUser(id);
	    	if(shopDto!=null){
	    		modelAndView.addObject("shopDto", shopDto);
	    	}
	    	modelAndView.setViewName("shop/dealersDetail");
	        return modelAndView;
	     } 
		/**
		 * 修改经销商 
		 * @param id
		 * @return
		 * @author zss
		 */
		@RequestMapping(value="/updateDealers",method=RequestMethod.GET) 
	    public ModelAndView getDealersById(@RequestParam Integer id){
	    	ModelAndView modelAndView = new ModelAndView();
	    	/*TbwebUserShopDto shopDto  = tbWebUserService.getUserShop(id);
	    	if(shopDto!=null){
	    		modelAndView.addObject("dealers", shopDto);
	    	}*/
	    	ShopWebUserDto shopDto  = tbWebUserService.getShopWebUser(id);
	    	if(shopDto!=null){
	    		modelAndView.addObject("shopDto", shopDto);
	    		modelAndView.addObject("provinceInfos", tbProvinceInfoService.selectByObject(null));
	    	}
	    	modelAndView.setViewName("shop/updateDealers");
	        return modelAndView;
	     }
		
		@RequestMapping(value="/updateDealers",method=RequestMethod.POST) 
	    public ModelAndView updateDealers(@RequestParam Map<String, Object> param,@RequestParam Integer id){
	    	ModelAndView modelAndView = new ModelAndView();
	    	 tbWebUserService.updateUserShop(param, id);
	    	//if(count >0){
	    		modelAndView.setViewName("redirect:dealers");
	    	/*}else{
	    		modelAndView.setViewName("shop/dealersDetail");
	    	}*/
	    	
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
