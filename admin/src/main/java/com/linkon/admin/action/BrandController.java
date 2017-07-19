package com.linkon.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.BrandDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.service.TbWapProductService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;
/**
 * 类的功能描述:品牌列表<br>
 *
 * @author 周琨
 * @FileName BrandController.java<br>
 * @Language Java<br>
 * @date 2016-03-28<br>
 */
@Controller
@RequestMapping("brand")
public class BrandController{
  
	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbBrandService tbBrandService;
	
	@Autowired
	protected TbProductService tbProductService;
	
	@Autowired
	protected TbWapProductService tbWapProductService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/index")
    public String index(PageDto page,ModelMap model){
    	
    	Criteria criteria=new Criteria();
    	List<TbBrand> bList = tbBrandService.selectByObject(criteria,page);
    	model.addAttribute("bList",bList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
        return "brand/brand";
    }
    /**
     * 
    * @author:ZK 
    * @date:2016-7-18 下午2:08:15 
    * @Description:添加页面
    * @return:String
     */
    @RequestMapping("/addBrandPage")
    public String addBrandPage(ModelMap model){
    	//初始化主分类信息
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setParentId(0);
    	List<TbProductType> mLists = productTypeService.selectByTbProductType(tbProductType);
    	model.addAttribute("mLists", mLists);
    	return "brand/addBrandPage";
    }
    /**
	 * 查询数据列表
	 * @param page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	 @RequestMapping(value = "/serachBrand")
	    public String searchResult(HttpServletRequest request,String name,PageDto page,Model model) throws IOException {
			Criteria criteria=new Criteria();
			criteria.put("name",name);
			List<TbBrand> bList = tbBrandService.selectByObject(criteria,page);
			model.addAttribute(HglContants.PAGE_DTO_ID, page);
	    	model.addAttribute("bList", bList);
	    	model.addAttribute("name", name);
	        return "/brand/brandList";
	    }
  
	 /**
	  * 
	 * @author:ZK 
	 * @date:2016-7-18 下午2:09:04 
	 * @Description:更新页面信息初始化
	 * @return:String
	  */
    @RequestMapping("/goUpdateBrandPage")
    public String goUpdateBrandPage(Integer id,ModelMap model){
    	//初始化主分类信息
    	TbProductType tbProductType = new TbProductType();
    	tbProductType.setParentId(0);
    	List<TbProductType> mLists = productTypeService.selectByTbProductType(tbProductType);
    	model.addAttribute("mLists", mLists);
    	TbBrand tbBrand = tbBrandService.selectByPrimaryKey(id);
    	String path = HglContants.LOGO_PATH+tbBrand.getLogoName();
    	model.addAttribute("imgUrl", HglContants.LOGO_PATH+tbBrand.getLogoName());
    	model.addAttribute("tbBrand", tbBrand);
    	return "brand/updateBrandPage";
    }
    /**
    * @author:ZK 
    * @date:2016-7-18 下午2:09:53 
    * @Description:保存修改信息
    * @return:String
     */
    @RequestMapping("/saveUpdateBrand")
    public String saveUpdateBrand(BrandDto imgFile,HttpServletRequest request) throws Exception{
    	TbBrand tbBrand = tbBrandService.selectByPrimaryKey(imgFile.getId());
    	String newName = StringUtil.changeFileName(tbBrand.getId().toString(),imgFile.getImgNameInfo());
    	tbBrand.setName(imgFile.getName());
    	if(imgFile.getImgNameInfo() != null && !"".equals(imgFile.getImgNameInfo())){
    		tbBrand.setLogoName(newName);
    	}
    	tbBrand.setUrl(imgFile.getUrl());
    	tbBrand.setState(imgFile.getState());
    	tbBrand.setManufacturerName(imgFile.getManufacturerName());
    	tbBrand.setRemark(imgFile.getRemark());
    	tbBrand.setProductTypeId(imgFile.getProductTypeId());
    	tbBrand.setProductTypeName(productTypeService.selectByPrimaryKey(imgFile.getProductTypeId()).getName());
    	tbBrand.setSort(imgFile.getSort());
    	tbBrandService.updateByPrimaryKey(tbBrand);
    	if(imgFile.getImgNameInfo() != null && !"".equals(imgFile.getImgNameInfo())){
    		ImageUtil.changImageName(imgFile.getImgNameInfo(),newName);
    	}
    	return "redirect:/brand/index";
    }
    /*
     * 
     * 品牌添加时校验
     * */
    @RequestMapping("/addValidateBrand")
    @ResponseBody
    public Map<String,Object>  addValidateBrand(String name,Integer producttypeId){
    	Map<String, Object> map = new HashMap<String, Object>();
   	  	map.put("code", HglContants.NO_REPEAT);
    	TbBrand brand = new TbBrand();
    	brand.setName(name);
    	brand.setProductTypeId(producttypeId);
    	List<TbBrand> tbList= tbBrandService.selectByTbBrand(brand);
    	if(tbList.size() > 0){
    		map.put("code", HglContants.IS_REPEAT);
    	}
    	return map;
    }
    
    /*
     * 品牌修改时校验
     * 
     * */
    @RequestMapping("/updateValidateBrand")
    @ResponseBody
    public Map<String,Object>  updateValidateBrand(String name,Integer producttypeId,Integer id){
    	Map<String, Object> map = new HashMap<String, Object>();
   	  	map.put("code", HglContants.NO_REPEAT);
    	TbBrand brand = new TbBrand();
    	brand.setName(name);
    	brand.setProductTypeId(producttypeId);
    	List<TbBrand> tbList= tbBrandService.selectByTbBrand(brand);
    	if(tbList.size() > 1){
    		map.put("code", HglContants.IS_REPEAT);
    		return map;
    	}
    	if(tbList.size() == 1){
    		if(!tbList.get(0).getId().equals(id)){
    			map.put("code", HglContants.IS_REPEAT);
    		return map;
    		}
    	}
    	return map;
    }
    
    /*
     * 图片展示
     * */
    @RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,HttpServletResponse response) {
    	TbBrand tbBrand = tbBrandService.selectByPrimaryKey(id);
    	model.addAttribute("tbBrand", tbBrand);
    	ImageUtil.showImage("shopLogo",tbBrand.getLogoName(),response);
        return null;
    }
    /* 
     * 保存添加的品牌信息
     * 
     * */
     @RequestMapping("/saveBrandPage")
     public String saveBrandPage(BrandDto imgFile,HttpServletRequest request,ModelMap model) throws Exception{
    	 TbBrand record = new TbBrand();
    	 record.setLogoName(imgFile.getImgNameInfo());
    	 record.setManufacturerId(2222);
    	 record.setName(imgFile.getName());
    	 record.setProductTypeId(imgFile.getProductTypeId());
    	 record.setSort(imgFile.getSort());
    	 record.setState(imgFile.getState());
    	 record.setUrl(imgFile.getUrl());
    	 record.setManufacturerName(imgFile.getManufacturerName());
    	 record.setProductTypeName(productTypeService.selectByPrimaryKey(imgFile.getProductTypeId()).getName());
    	 record.setRemark(imgFile.getRemark());
    	 //0：为厂家
    	 record.setType(0);
    	 tbBrandService.insert(record);
    	 Integer BrandId = record.getId();
    	 String newName = StringUtil.changeFileName(BrandId.toString(),imgFile.getImgNameInfo());
    	 ImageUtil.changImageName(imgFile.getImgNameInfo(),newName);
    	 record.setLogoName(newName);
    	 tbBrandService.updateByPrimaryKey(record);
     	return "redirect:/brand/index";
     }
     /*
      * 根据ID删除品牌
      * */
     @RequestMapping("/deleteBrandById")
     public String deleteBrandById(Integer id){
    	 tbBrandService.deleteByPrimaryKey(id);
    	 return "redirect:/brand/index";
     }
     
     @RequestMapping("/delectBrandValidate")
     @ResponseBody
     public Map<String,Object> delectBrandValidate(Integer id){
   	  Map<String, Object> map = new HashMap<String, Object>();
   	  map.put("code", HglContants.NO_REPEAT) ;
   	  Criteria example = new Criteria();
   	  example.put("brandId", id);
   	  int pList = tbProductService.countByObject(example);
   	  int wappList = tbWapProductService.countByObject(example);
   	  if(pList > 0 || wappList>0){
   		map.put("code", HglContants.IS_REPEAT) ;
   	  }
   	  return map;
     }
     
     /* 
      * 难图片大小
      * 
      * */
      @RequestMapping("/validateImg")
      @ResponseBody
      public Map<String,Object> validate(MultipartFile imgFile,HttpServletRequest request){
    	  Map<String, Object> map = new HashMap<String, Object>();
    	  String imgName = imgFile.getOriginalFilename();
    	  map.put("code", HglContants.SUCCESS);
    	  long imgSize = imgFile.getSize();
    	  long name = System.currentTimeMillis();
    	  String newName = StringUtil.changeFileName("Logo_"+name,imgName);
    	  if(imgSize > 5242880){
	    		//图片过大 不超过5MB
	    		map.put("code", HglContants.FAIL);
	    		return map;
	    	}
    	  try{
    		  productTypeService.uploadForm(imgFile,newName);
    	  }catch(Exception e){
    		  logger.debug("文件上传失败.....");
    		  e.printStackTrace();
    	  }
    	  map.put("imgName",newName);
    	  return map;
      }
}