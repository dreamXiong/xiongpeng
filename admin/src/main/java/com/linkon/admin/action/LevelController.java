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

import com.liguo.hgl.proxydao.model.TbShopLevel;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbShopLevelService;
/**
 * 用户等级
 * @fiShopLevelController.java
 * @2016-5-9	
 * @author 周双双
 */
@Controller
@RequestMapping("/level")
public class LevelController extends IBaseController{

	@Autowired
	protected TbShopLevelService levelService;
	
	/**
	 * 查询所有的用户等级信息
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value="/level")
     public ModelAndView getSuppliers(){
    	ModelAndView modelAndView = new ModelAndView();
    	List<TbShopLevel> leveList = levelService.selectByObject(null);
    	if(leveList.size()>0){
    		modelAndView.addObject("leveList", leveList);
    	}
    	modelAndView.setViewName("shop/level");
        return modelAndView;
     }
	/**
	 * 修改用户等级信息
	 * @param id
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value="/getUpdateLevel")
     public ModelAndView getUpdateLevel(Integer id){
    	ModelAndView modelAndView = new ModelAndView();
    	if(id!=null && id >0){
    		TbShopLevel level =	levelService.selectByPrimaryKey(id);
    		
    		modelAndView.addObject("level", level);
    	}
    	modelAndView.setViewName("shop/updateLevel");
        return modelAndView;
     }
	    
    @RequestMapping(value="/updateLevel",method=RequestMethod.POST)
    public ModelAndView updateLevel(TbShopLevel shopLevel){
    	ModelAndView modelAndView = new ModelAndView();
    	shopLevel.setUpdateBy(getLoginUserId());//获取当前登录用户
    	levelService.updateByPrimaryKeySelective(shopLevel);
    	modelAndView.setViewName("redirect:/level/level");
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
