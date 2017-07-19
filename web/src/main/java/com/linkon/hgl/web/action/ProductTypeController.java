package com.linkon.hgl.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
 * @author 熊鹏
 * @FileName ProductTypeController.java<br>
 * @Language Java<br>
 * @date 2016-05-10<br>
 */
@Controller
@RequestMapping("productType")
public class ProductTypeController{
	
	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbBrandService tbBrandService;
	
	@Autowired
	protected TbProductService tbProductService;
	
    /*
     * 大类联动显示
     * */
    @RequestMapping("/linkageMainPage")
    public @ResponseBody Map<String,Object> linkageMainPage(@RequestParam Integer id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	/*初始化二级分类*/
//		TbProductType secondProductType = new TbProductType();
//		secondProductType.setParentId(id);
//    	List<TbProductType> secondList = productTypeService.selectByTbProductType(secondProductType);
//    	map.put("secondList", secondList);
//    	
    	/*联动品牌*/
    	Criteria criteria = new Criteria();
    	criteria.put("producttypeId", id);
    	List<TbBrand> tbBrandList = tbBrandService.selectByObject(criteria);
    	map.put("tbBrandList",tbBrandList);
        return map;
    }
    
    /*
     * 大类联动显示
     * */
    @RequestMapping("/linkageMainPageProduct")
    @ResponseBody
    public Map<String,Object> linkageMainPageProduct(@RequestParam Integer id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	/*初始化二级分类*/
		TbProductType secondProductType = new TbProductType();
		secondProductType.setParentId(id);
    	List<TbProductType> secondList = productTypeService.selectByTbProductType(secondProductType);
    	map.put("secondList", secondList);
    	/*联动品牌*/
    	Criteria criteria=new Criteria();
    	criteria.put("producttypeId", id);
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
    
}
