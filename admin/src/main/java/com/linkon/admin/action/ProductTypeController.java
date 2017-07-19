package com.linkon.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbProductService;

/**
 * 类的功能描述:产品分类<br>
 *
 * @author 周琨
 * @FileName ProductTypeController.java<br>
 * @Language Java<br>
 * @date 2016-03-28<br>
 */
@Controller
@RequestMapping("productType")
public class ProductTypeController{
	private static final String PRODUCT_TYPE = "productType/productType";
	
	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbBrandService tbBrandService;

	
	@Autowired
	protected TbProductService tbProductService;
    @RequestMapping("/index")
    public String index(ModelMap model){
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setParentId(0);
    	List<TbProductType> pList = queryProductType(0);
    	if(pList.size() > 0){
    		/*初始化二级分类*/
    		Integer mainId = pList.get(0).getId();
    		TbProductType secondProductType = new TbProductType();
    		secondProductType.setParentId(mainId);
        	List<TbProductType> secondList = productTypeService.selectByTbProductType(secondProductType);
        	model.addAttribute("secondList", secondList);
        	model.addAttribute("mainId", mainId);
        	if(secondList.size() > 0){
        		model.addAttribute("parentId", secondList.get(0).getId());
        		/*初始化三级分类*/
            	TbProductType thirdProductType = new TbProductType();
            	thirdProductType.setMainId(mainId);
            	thirdProductType.setParentId(secondList.get(0).getId());
            	List<TbProductType> thirdList = productTypeService.selectByTbProductType(thirdProductType);
            	model.addAttribute("thirdList", thirdList);
        	}
    	}
    	model.addAttribute("pList", pList);
        return PRODUCT_TYPE;
    }
    /**
     * 一级分类的添加
     * **/
    @RequestMapping("/addFirstProductType")
    public String addFirstProductType(@RequestParam String name,@RequestParam String describe){
    	productTypeService.insert(name,describe,0,0,null,true);
    	return "redirect:/productType/index";
    }
    /**
     * 二级分类的添加
     * **/
    @RequestMapping("/addSecondProductType")
    public String addSecondProductType(@RequestParam String name,@RequestParam String describes,@RequestParam Integer mainId){
    	//id:为父类ID
    	productTypeService.insert(name,describes,mainId,mainId,null,true);
        return "redirect:/productType/index";
    }
    /**
     * 三级分类的添加
     * **/
    @RequestMapping("/addThirdProductType")
    public String addThirdProductType(@RequestParam String name,@RequestParam String describes,
    									@RequestParam Integer mainId,@RequestParam Integer parentId){
    	//id:为父类ID
    	productTypeService.insert(name,describes,parentId,mainId,null,true);
        return "redirect:/productType/index";
    }
    /**
     * 产品更新三级分类
     * **/
    @RequestMapping("/subUpdateThirdProductType")
    public String subUpdateThirdProductType(@RequestParam String name,@RequestParam String describes,
    										@RequestParam Integer parentId,@RequestParam Integer id,
    										@RequestParam Integer mainId){
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setId(id);
    	tbProductType.setName(name);
    	tbProductType.setDescribes(describes);
    	tbProductType.setParentId(parentId);
    	tbProductType.setMainId(mainId);
    	productTypeService.updateByPrimaryKey(tbProductType);
        return "redirect:/productType/index";
    }
    
    /**
     * 产品大类更新
     * **/
    @RequestMapping("/updateFirstProductType")
    public String updateFirstProductType(@RequestParam String name,@RequestParam String describes,@RequestParam Integer id,ModelMap model){
    	
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setId(id);
    	tbProductType.setName(name);
    	tbProductType.setDescribes(describes);
    	tbProductType.setParentId(0);
    	tbProductType.setMainId(0);
    	productTypeService.updateByPrimaryKey(tbProductType);
    	
        return "redirect:/productType/index";
    }
    /**
     * 产品更新二级分类
     * **/
    @RequestMapping("/subUpdateSecondProductType")
    public String subUpdateSecondProductType(@RequestParam String name,@RequestParam String describes,
    										@RequestParam Integer parentId,@RequestParam Integer id){
    	//二级分类的修改，三级分类的二级分类的Id是不会改变的，直接修改三级分类的ID
    	
    	TbProductType productType= productTypeService.selectByPrimaryKey(id);
    	
    	//查询该二级分类下面的所有三级分类
    	TbProductType selectBean = new TbProductType();
    	selectBean.setParentId(id);
    	selectBean.setMainId(productType.getParentId());
    	
    	List<TbProductType> thirdList = productTypeService.selectByTbProductType(selectBean);
    	/* 更新三级分类的主分类*/
    	if(thirdList.size() > 0){
    		 productTypeService.updateProductTyppeByPrimaryKeyArray(parentId,thirdList);
    	}
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setId(id);
    	tbProductType.setName(name);
    	tbProductType.setDescribes(describes);
    	tbProductType.setParentId(parentId);
    	tbProductType.setMainId(parentId);
    	productTypeService.updateByPrimaryKey(tbProductType);
        return "redirect:/productType/index";
    }
    @RequestMapping("/goProductTypeAddPage")
    public String goProductTypeAddPage(@RequestParam String pagePath,ModelMap model,@RequestParam Integer mainId){
    	List<TbProductType> pLists = queryProductType(0);
    	model.addAttribute("pLists", pLists);
    	TbProductType tbProductType = productTypeService.selectByPrimaryKey(mainId);
    	model.addAttribute("tbProductType", tbProductType);
    	return "productType/"+pagePath;
    }
    
    /*
     * 跳转到三级分类添加页面
     * 
     * */
    @RequestMapping("/goAddThirdProductTypePage")
    public String goAddThirdProductTypePage(ModelMap model,@RequestParam Integer mainId,@RequestParam Integer parentId){
    	TbProductType mainP = productTypeService.selectByPrimaryKey(mainId);
    	model.addAttribute("mainP", mainP);
    	TbProductType parentP = productTypeService.selectByPrimaryKey(parentId);
    	model.addAttribute("parentP", parentP);
    	return "productType/addThirdProductType";
    }
    
    /*
     * 跳转到三级分类修改页面
     * 
     * */
    
    @RequestMapping("/updateThirdProductTypePage")
    public String updateThirdProductTypePage(ModelMap model,@RequestParam Integer id){
    	TbProductType tbProductType = productTypeService.selectByPrimaryKey(id);
    	
    	TbProductType secondProductType = new TbProductType();
		secondProductType.setParentId(tbProductType.getMainId());
    	List<TbProductType> secondList = productTypeService.selectByTbProductType(secondProductType);
    	List<TbProductType> pLists = queryProductType(0);
    	model.addAttribute("pLists", pLists);
    	model.addAttribute("id", id);
    	model.addAttribute("secondList", secondList);
    	model.addAttribute("tbProductType", tbProductType);
    	return "productType/updateThirdProductType";
    }
    /*
     * 三级联动添加时，下拉联动
     * */
    @RequestMapping("/linkageProductType")
    @ResponseBody
    public Map<String,Object> linkageProductType(@RequestParam Integer id){
    	TbProductType secondProductType = new TbProductType();
    	Map<String,Object> map = new HashMap<String,Object>();
		secondProductType.setParentId(id);
    	List<TbProductType> sList = productTypeService.selectByTbProductType(secondProductType);
    	map.put("sList", sList);
    	return map;
    }
    /*
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
    	Criteria criteria=new Criteria();
    	criteria.put("producttypeId", id);
    	//查询显示的品牌
    	criteria.put("state", 0);
    	List<TbBrand> tbBrandList=tbBrandService.selectByObject(criteria);
    	map.put("tbBrandList",tbBrandList);
    	
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
    	List<TbProduct> tbProductList=tbProductService.selectByObject(criteria);
    	map.put("tbProductList",tbProductList);
        return map;
    }
    
    /*
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
    	Criteria criteria=new Criteria();
    	criteria.put("producttypeId", id);
    	List<TbProduct> tbProductList=tbProductService.selectByObject(criteria);
    	map.put("tbProductList",tbProductList);
        return map;
    }
 
    /*
     * 主页面三级分类联动显示
     * */
    @RequestMapping("/linkageMainPageThird")
    @ResponseBody
    public Map<String,Object> linkageMainPageThird(@RequestParam Integer id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	/*联动产品*/
    	Criteria criteria=new Criteria();
    	criteria.put("producttypeId", id);
    	List<TbProduct> tbProductList=tbProductService.selectByObject(criteria);
    	map.put("tbProductList",tbProductList);
    	return map;
    }
    
    /*
     * 品牌联动显示
     * */
    @RequestMapping("/linkageMainPageBrand")
    @ResponseBody
    public Map<String,Object> linkageMainPageBrand(@RequestParam Integer id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	/*联动产品*/
    	Criteria criteria=new Criteria();
    	criteria.put("brandId", id);
    	List<TbProduct> tbProductList=tbProductService.selectByObject(criteria);
    	map.put("tbProductList",tbProductList);
    	return map;
    }
    
    /*
     * 产品联动显示属性
     * */
    @RequestMapping("/linkProductForAttribute")
    @ResponseBody
    public Map<String,Object> linkProductForAttribute(@RequestParam Integer id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	TbProduct tbProduct=tbProductService.selectByPrimaryKey(id);
    	String attributes=tbProduct.getAttributes();
    	if(attributes!=null && !"".equals(attributes)){
    		String[]attributeList=attributes.split("/");
    		map.put("attributeList", attributeList);
    	}
    	return map;
    }
    
    
    
    /*
     * 
     * */
    @RequestMapping("/updateProductType")
    public String updateProductType(@RequestParam Integer id,ModelMap model){
    	model.addAttribute("productType", productTypeService.selectByPrimaryKey(id));
    	return "/productType/updateFirstProductType";
    }
    
    @RequestMapping("/updateSecondProductType")
    public String updateSecondProductType(@RequestParam Integer id,ModelMap model){
      	model.addAttribute("productType", productTypeService.selectByPrimaryKey(id));
      	model.addAttribute("firstProductTypeList",queryProductType(0));
      	return "/productType/updateSecondProductType";
      }
    /*
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
    /*
     * 修改分类时查询除自身外是否还有重复记录
     * 
     * */
    @RequestMapping("/updateValidateProductType")
    @ResponseBody
    public Map<String,Object> updateValidateProductType(@RequestParam String name,@RequestParam Integer id){
    	Map<String,Object> map = new HashMap<String,Object>();
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setName(name);
    	List<TbProductType> pList = productTypeService.selectByTbProductType(tbProductType);
    	System.out.println(pList.size());
    	map.put("code", "0");
    	if(pList.size() == 1){
    		TbProductType t = pList.get(0);
    		if(!t.getId().equals(id)){
    			map.put("code", "1");
    		}
    	}
    	if(pList.size() > 1 ){
    		map.put("code", "1");
    	}
    	return map;
    }
    
    /*
     * 删除大分类时作校验（确定弹出框是否弹出）
     * 
     * */
    @RequestMapping("/deleteFirstProductType")
    @ResponseBody
    public Map<String,Object> deleteFirstProductType(@RequestParam Integer id){
    	Map<String,Object> map = new HashMap<String,Object>();
    	Criteria example = new Criteria();
    	example.put("producttypeId", id);
    	List<TbBrand> tList = tbBrandService.selectByObject(example);
    	TbProductType tbProductType = productTypeService.selectByPrimaryKey(id);
    	TbProductType t = new TbProductType();
    	t.setParentId(tbProductType.getId());
    	List<TbProductType> pList = productTypeService.selectByTbProductType(t);
    	
    	if(pList.size() > 0 || tList.size() > 0){
    		//不可以删
    		map.put("code", "1");
    		return map;
    	}else{
    		map.put("code", "0");
    		return map;
    	}
    }
    /*
     * 确认删除分类
     * 
     * */
    @RequestMapping("/configDeleteProductType")
    public String configDeleteProductType(@RequestParam Integer id){
    	productTypeService.deleteByPrimaryKey(id);
    	 return "redirect:/productType/index";
    }
    /*
     * 删除二级分类（删除框是否弹出）
     * 
     * */
    @RequestMapping("/deleteSecondProductType")
    @ResponseBody
    public Map<String,Object> deleteSecondProductType(@RequestParam Integer id){
    	Map<String,Object> map = new HashMap<String,Object>();
    	TbProductType tbProductType = productTypeService.selectByPrimaryKey(id);
    	TbProductType t = new TbProductType();
    	t.setParentId(tbProductType.getId());
    	List<TbProductType> pList = productTypeService.selectByTbProductType(t);
    	if(pList.size() > 0 || !validateProducy(id)){
    		//不可以删
    		map.put("code", "1");
    		return map;
    	}else{
    		map.put("code", "0");
    		return map;
    	}
    }
    /*
     * 删除三级分类
     * 
     * */
    @RequestMapping("/deleteThirdProductType")
    @ResponseBody
    public Map<String,Object> deleteThirdProductType(@RequestParam Integer id){
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(validateProducy(id)){
    		map.put("code", "0");
    	}else{
    		map.put("code", "1");
    	}
   		
   		return map;
    }
    private List<TbProductType> queryProductType(Integer id){
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setParentId(id);
    	List<TbProductType> pLists = productTypeService.selectByTbProductType(tbProductType);
    	return pLists;
    }
    /**
     * 校验该分类下有没有产品
     * 
     * */
    private boolean validateProducy(Integer id){
    	Criteria criteria = new Criteria();
    	criteria.put("producttypeId", id);
    	int tList = tbProductService.countByObject(criteria);
    	if(tList > 0){
    		//该分类下 有产品 不能删除
    		return false;
    	}
    	return true;
    }

}