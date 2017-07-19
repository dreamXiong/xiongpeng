package com.linkon.hgl.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbWebUserService;
/**
 * wap品牌维护
 * @fiBrandController.java
 * @2016-6-1	
 * @author 周双双
 */
@Controller
@RequestMapping("brand")
public class BrandController extends IBaseController{

	@Autowired
	protected TbBrandService tbBrandService;
	
	@Autowired
	protected TbWebUserService tbWebUserService;
	
	@Autowired
	protected IProductTypeService productTypeService;
	
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
	 * 品牌列表页面
	 * @param page
	 * @param model
	 * @return
	 * @author zss
	 */
	@RequestMapping("/brand")
    public String brandList(PageDto page,ModelMap model){
		Criteria criteria=new Criteria();
    	List<TbBrand> bList = tbBrandService.selectByObject(criteria,page);
    	TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		List<TbProductType> mLists = productTypeService.selectByTbProductType(tbProductType);
		model.addAttribute("mLists", mLists);
    	model.addAttribute("bList",bList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
    	return "product/brand";
    }
	/**
	 *根据条件查询
	 * @param request
	 * @param name
	 * @param type
	 * @param producttypeId
	 * @param page
	 * @param model
	 * @return
	 * @throws IOException
	 * @author zss
	 */
	@RequestMapping(value = "/serachBrand")
    public String searchResult(HttpServletRequest request,String name,String type,String producttypeId,PageDto page,Model model) throws IOException {
		Criteria criteria=new Criteria();
		criteria.put("name",name);
		criteria.put("type","".equals(type)?null:type);
		criteria.put("producttypeId","".equals(producttypeId)?null:producttypeId);		
		List<TbBrand> bList = tbBrandService.selectByObject(criteria,page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
    	model.addAttribute("bList", bList);
    	model.addAttribute("name", name);
        return "product/brandList";
    }
	
	
	
	 /**
	  * 增加品牌的初始页
	  * @param model
	  * @return
	  * @author zss
	  */
    @RequestMapping("/addBrand")
    public String addBrandPage(ModelMap model){
    	//初始化主分类信息
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setParentId(0);
    	List<TbProductType> mLists = productTypeService.selectByTbProductType(tbProductType);
    	model.addAttribute("mLists", mLists);
    	return "product/addBrand";
    }
    /**
     * 保存品牌内容
     * @param model
     * @param param
     * @return
     * @author zss
     */
    @RequestMapping(value="/saveBrand",method=RequestMethod.POST) 
    public String saveBrand(ModelMap model,@RequestParam Map<String, Object> param){
    	int count = tbBrandService.saveBrandByUser(param,getLoginUserId());
    	if(count >0){
    		return "redirect:/brand/brand";
    	}else{
    		return "product/addBrand";
    	}
       
     }
    
    /**
     * 分类
     * @param model
     * @return
     * @author zss
     */
    @RequestMapping("/productType")
    public String productTypeList(ModelMap model){
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setParentId(0);
    	List<TbProductType> pList = productTypeService.selectByTbProductType(tbProductType);
    	if(pList.size() > 0){
    		/*初始化二级分类*/
    		Integer mainId = pList.get(0).getId();
    		TbProductType secondProductType = new TbProductType();
    		secondProductType.setParentId(mainId);
        	List<TbProductType> secondList = productTypeService.selectByTbProductType(secondProductType);
        	model.addAttribute("secondList", secondList);
        	model.addAttribute("mainId", mainId);
        	model.addAttribute("mainName", pList.get(0).getName());
        	if(secondList.size() > 0){
        		model.addAttribute("parentId", secondList.get(0).getId());
        		model.addAttribute("parentName", secondList.get(0).getName());
        		/*初始化三级分类*/
            	TbProductType thirdProductType = new TbProductType();
            	thirdProductType.setMainId(mainId);
            	thirdProductType.setParentId(secondList.get(0).getId());
            	List<TbProductType> thirdList = productTypeService.selectByTbProductType(thirdProductType);
            	model.addAttribute("thirdList", thirdList);
        	}
    	}
    	model.addAttribute("pList", pList);
        return "product/addProductType";
        
    }
    
    
    /**
     * 大类联动显示
     * */
    @RequestMapping("/linkageMainPage")
    @ResponseBody
    public Map<String,Object> linkageMainPage(@RequestParam Integer id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	/*初始化二级分类*/
		TbProductType secondProductType = new TbProductType();
		secondProductType.setParentId(id);
    	List<TbProductType> secondList = productTypeService.selectByTbProductType(secondProductType);
    	map.put("secondList", secondList);
    	/*联动品牌*/
    	/*Criteria criteria=new Criteria();
    	criteria.put("producttypeId", id);
    	//查询显示的品牌
    	criteria.put("state", 0);
    	List<TbBrand> tbBrandList=tbBrandService.selectByObject(criteria);
    	map.put("tbBrandList",tbBrandList);*/
    	
    	/*联动第一个二级分类下面的三级分类*/
    	if(secondList.size() > 0){
    		TbProductType thirdProductType = new TbProductType();
        	thirdProductType.setMainId(id);
        	thirdProductType.setParentId(secondList.get(0).getId());
        	/*thirdProductType.setMainId(secondList.get(0).getParentId());*/
        	List<TbProductType> thirdList = productTypeService.selectByTbProductType(thirdProductType);
        	map.put("thirdList",thirdList);
    	}
    	/*联动产品*/
    	/*List<TbProduct> tbProductList=tbProductService.selectByObject(criteria);
    	map.put("tbProductList",tbProductList);*/
        return map;
    }
    
    /**
     * 主页面二级分类联动显示
     * */
    @RequestMapping("/linkageMainPageSecond")
    @ResponseBody
    public Map<String,Object> linkageMainPageSecond(@RequestParam Integer id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	TbProductType thirdProductType = new TbProductType();
    	thirdProductType.setMainId(productTypeService.selectByPrimaryKey(id).getParentId());
    	thirdProductType.setParentId(id);
    	List<TbProductType> thirdList = productTypeService.selectByTbProductType(thirdProductType);
    	map.put("thirdList",thirdList);
    	/*联动产品*/
    	/*Criteria criteria=new Criteria();
    	criteria.put("producttypeId", id);
    	List<TbProduct> tbProductList=tbProductService.selectByObject(criteria);
    	map.put("tbProductList",tbProductList);*/
        return map;
    }
	 
    /**
     * 添加分类时查询表中是否有重复值
     * */
    @RequestMapping("/addValidateProductType")
    @ResponseBody
    public Map<String,Object> addValidateProductType(@RequestParam String name){
    	Map<String,Object> map = new HashMap<String,Object>();
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setName(name.trim());
    	List<TbProductType> pList = productTypeService.selectByTbProductType(tbProductType);
    	System.out.println(pList.size());
    	if(pList.size() > 0){
    		//TODO:0：说明存在相同的数据
    		map.put("code", "1");
    	}else{
    		map.put("code", "0");
    	}
    	return map;
    }
    /**
     * 添加二级分类
     * @param name
     * @param describes
     * @param firstId
     * @param request
     * @return
     * @throws Exception
     * @author zss
     */
    @RequestMapping("/addSecondProductType")
    public ModelAndView addSecondProductType(String name,String describes,Integer firstId,HttpServletRequest request) throws Exception{
    	//request.setCharacterEncoding("utf-8");
    	ModelAndView modelAndView = new ModelAndView();
    	//id:为父类ID
    	productTypeService.insert(name,describes,firstId,firstId,getLoginUserId(),false);
      //  return "product/addProductType";
        modelAndView.setViewName("product/addProductType");
        return modelAndView;
    }
    
    /**
     * 三级分类的添加
     * **/
    @RequestMapping("/addThirdProductType")
    public ModelAndView addThirdProductType(@RequestParam String name,@RequestParam String describes,
    									@RequestParam Integer firstId,@RequestParam Integer secondId,HttpServletRequest request){
    	ModelAndView modelAndView = new ModelAndView();
    	//id:为父类ID
    	productTypeService.insert(name,describes,secondId,firstId,getLoginUserId(),false);
    	modelAndView.setViewName("redirect:/brand/productType");
        return modelAndView;
    }
    
    
    /***********************以下是独立用于展示的内容，不被拦截器拦截,展示内容为display_开头****************************/
    @RequestMapping("/display_productType")
    public String display_productType(ModelMap model){
        TbProductType tbProductType = new TbProductType();
        tbProductType.setParentId(0);
        List<TbProductType> pList = productTypeService.selectByTbProductType(tbProductType);
        if(pList.size() > 0){
            /*初始化二级分类*/
            Integer mainId = pList.get(0).getId();
            TbProductType secondProductType = new TbProductType();
            secondProductType.setParentId(mainId);
            List<TbProductType> secondList = productTypeService.selectByTbProductType(secondProductType);
            model.addAttribute("secondList", secondList);
            model.addAttribute("mainId", mainId);
            model.addAttribute("mainName", pList.get(0).getName());
            if(secondList.size() > 0){
                model.addAttribute("parentId", secondList.get(0).getId());
                model.addAttribute("parentName", secondList.get(0).getName());
                /*初始化三级分类*/
                TbProductType thirdProductType = new TbProductType();
                thirdProductType.setMainId(mainId);
                thirdProductType.setParentId(secondList.get(0).getId());
                List<TbProductType> thirdList = productTypeService.selectByTbProductType(thirdProductType);
                model.addAttribute("thirdList", thirdList);
            }
        }
        model.addAttribute("pList", pList);
        return "product/display_productType";
    }
    
    
    /**
     * 大类联动显示
     * */
    @RequestMapping("/display_linkageMainPage")
    @ResponseBody
    public Map<String,Object> display_linkageMainPage(@RequestParam Integer id) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        /*初始化二级分类*/
        TbProductType secondProductType = new TbProductType();
        secondProductType.setParentId(id);
        List<TbProductType> secondList = productTypeService.selectByTbProductType(secondProductType);
        map.put("secondList", secondList);
        /*联动品牌*/
        /*Criteria criteria=new Criteria();
        criteria.put("producttypeId", id);
        //查询显示的品牌
        criteria.put("state", 0);
        List<TbBrand> tbBrandList=tbBrandService.selectByObject(criteria);
        map.put("tbBrandList",tbBrandList);*/
        
        /*联动第一个二级分类下面的三级分类*/
        if(secondList.size() > 0){
            TbProductType thirdProductType = new TbProductType();
            thirdProductType.setMainId(id);
            thirdProductType.setParentId(secondList.get(0).getId());
            /*thirdProductType.setMainId(secondList.get(0).getParentId());*/
            List<TbProductType> thirdList = productTypeService.selectByTbProductType(thirdProductType);
            map.put("thirdList",thirdList);
        }
        /*联动产品*/
        /*List<TbProduct> tbProductList=tbProductService.selectByObject(criteria);
        map.put("tbProductList",tbProductList);*/
        return map;
    }
    
    
    /**
     * 主页面二级分类联动显示
     * */
    @RequestMapping("/display_linkageMainPageSecond")
    @ResponseBody
    public Map<String,Object> display_linkageMainPageSecond(@RequestParam Integer id) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        TbProductType thirdProductType = new TbProductType();
        thirdProductType.setMainId(productTypeService.selectByPrimaryKey(id).getParentId());
        thirdProductType.setParentId(id);
        List<TbProductType> thirdList = productTypeService.selectByTbProductType(thirdProductType);
        map.put("thirdList",thirdList);
        /*联动产品*/
        /*Criteria criteria=new Criteria();
        criteria.put("producttypeId", id);
        List<TbProduct> tbProductList=tbProductService.selectByObject(criteria);
        map.put("tbProductList",tbProductList);*/
        return map;
    }
    /**
     * 品牌列表页面
     * @param page
     * @param model
     * @return
     * @author zss
     */
    @RequestMapping("/display_brand")
    public String display_brand(PageDto page,ModelMap model){
        Criteria criteria=new Criteria();
        List<TbBrand> bList = tbBrandService.selectByObject(criteria,page);
        TbProductType tbProductType = new TbProductType();
        tbProductType.setParentId(0);
        List<TbProductType> mLists = productTypeService.selectByTbProductType(tbProductType);
        model.addAttribute("mLists", mLists);
        model.addAttribute("bList",bList);
        model.addAttribute(HglContants.PAGE_DTO_ID, page);
        return "product/display_brand";
    }
    
}
