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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.ProductDto;
import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductInfoDto;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductInventory;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbProductInventoryService;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.util.BeanCopyUtil;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

/**
 * 类的功能描述:产品<br>
 *
 * @author 周琨
 * @FileName ProductTypeController.java<br>
 * @Language Java<br>
 * @date 2016-03-28<br>
 */
@Controller
@RequestMapping("product")
public class ProductController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String PRODUCT_TYPE = "productType/productType";
	
	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbBrandService tbBrandService;

	@Autowired
	protected TbProductService tbProductService;
	
	@Autowired
	protected TbProductInventoryService tbProductInventoryService;
	
    @RequestMapping("/index")
    public String index(PageDto page,ModelMap model){
    	List<ProductInfoDto> productInfoList = tbProductService.selectInfoListByName(null,page);
    	model.addAttribute("productInfoList", productInfoList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
    	return "product/product";
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
   	 @RequestMapping(value = "/serachProduct")
   	    public String serachProduct(String name,PageDto page,Model model) throws IOException {
   		 	List<ProductInfoDto> productInfoList = tbProductService.selectInfoListByName(name,page);
   		 	model.addAttribute("productInfoList", productInfoList);
   		 	model.addAttribute(HglContants.PAGE_DTO_ID, page);
   	        return "/product/productList";
   	    }
    /*初始化添加页面信息*/
    @RequestMapping("/initializationInfo")
	public String operationInventory(Model model) {
			TbProductType tbProductType = new TbProductType();
			tbProductType.setParentId(0);
			List<TbProductType> mLists = productTypeService.selectByTbProductType(tbProductType);
			
			model.addAttribute("mLists", mLists);
			
		return "product/addProduct";
	}
    @RequestMapping("/showUpdateInfo")
    public String showUpdateInfo(Integer id,Model model){
    	ProductInfoDto pifd = tbProductService.selectUpdateInfo(id);
    	String[] attr = pifd.getAttributes().split("/");
    	model.addAttribute("sign",validateDelete(id));
    	model.addAttribute("pifd",pifd);
    	model.addAttribute("attr",attr);
    	return "product/updateProduct";
    }
    
    @RequestMapping("/showdetailsInfo")
    public String showdetailsInfo(Integer id,Model model){
    	ProductInfoDto pifd = tbProductService.selectUpdateInfo(id);
    	String[] attr = pifd.getAttributes().split("/");
    	model.addAttribute("pifd",pifd);
    	model.addAttribute("attr",attr);
    	return "product/detailsProduct";
    }
    
    /*
     * 保存产品添加信息
     * */
    @RequestMapping("/saveProductInfo")
    public String saveProductInfo(ProductDto dto,Model model){
    	TbProduct t = new TbProduct(); 
    	try{
    		BeanCopyUtil.CopyBeanToBean(dto,t);
    		if(dto.getThirdType() != null && dto.getThirdType() != 0){
    			t.setProductTypeId(dto.getThirdType());
    		}
    		tbProductService.insertProduct(t);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "redirect:/product/index";
    }
    /*
     * 产品信息更新操作
     * */
    @RequestMapping("/updateProduct")
    public String updateProduct(ProductDto dto){
    	TbProduct t = new TbProduct(); 
    	try{
    		BeanCopyUtil.CopyBeanToBean(dto,t);
    		tbProductService.updateProduct(t);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "redirect:/product/index";
    }
    /*
     * 图片展示
     * */
    @RequestMapping("/validateImg")
    @ResponseBody
    public Map<String,Object> validate(MultipartFile imgFile,HttpServletRequest request ,String imgNo,Model model){
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
  		  productTypeService.uploadForm(imgFile,newName,HglContants.PRODUCT_TESTPATH);
  	  }catch(Exception e){
  		  logger.debug("文件上传失败.....");
  		  e.printStackTrace();
  	  }
  	  map.put("imgNo",newName);
  	  model.addAttribute(imgNo,imgNo);
  	  return map;
    }
  
    @RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageProduct(id,imgName,response);
        return null;
    }
    /*
     * 产品删除
     * */
    @RequestMapping("/deleteProduct")
    public String deleteProduct(Integer id) {
    	if(validateDelete(id)){
    		tbProductService.deleteByPrimaryKey(id);
    	}
    	return "redirect:/product/index";
    }
    /*
     * 删除产品校验
     * 
     * */
    @RequestMapping("/deleteProductValidate")
    @ResponseBody
    public Map<String,Object> deleteProduct(@RequestParam Integer id ,Model model){
    	Map<String,Object> map = new HashMap<String,Object>();
    	boolean sign = validateDelete(id);
    	Integer code = sign ? HglContants.NO_REPEAT : HglContants.IS_REPEAT;
		map.put("code",code);
		return map;
    }
    private boolean validateDelete(Integer id){
    	Criteria example = new Criteria();
    	example.put("productId",id);
    	List<TbProductInventory> tpiList = tbProductInventoryService.selectByObject(example);
    	if(tpiList.size() > 0){
    		//不可以删   		
    		return false;
    	}
    	return true;
    }
    
    @RequestMapping("/searchProduct")
    @ResponseBody
    public Map<String,Object>searchProduct(HttpServletRequest request,Model model){
    	Criteria criteria = new Criteria();
    	String name = request.getParameter("name");
    	if (com.liguo.hgl.proxydao.util.StringUtil.isNotEmpty(name) ) {
    		criteria.put("name", name);
    	}
		String firstTypeId = request.getParameter("firstTypeId");
		if (com.liguo.hgl.proxydao.util.StringUtil.isNotEmpty(firstTypeId) && !"-1".equals(firstTypeId)) {
			criteria.put("firstType", Integer.parseInt(firstTypeId));
		}
		String secondTypeId = request.getParameter("secondTypeId");
		if (com.liguo.hgl.proxydao.util.StringUtil.isNotEmpty(secondTypeId) && !"-1".equals(secondTypeId)) {
			criteria.put("secondType", Integer.parseInt(secondTypeId));
		}
		String thirdTypeId = request.getParameter("thirdTypeId");
		if (com.liguo.hgl.proxydao.util.StringUtil.isNotEmpty(thirdTypeId) && !"-1".equals(thirdTypeId)) {
			criteria.put("thirdType", Integer.parseInt(thirdTypeId));
		}
		String brandIds = request.getParameter("brandIds");
		if (com.liguo.hgl.proxydao.util.StringUtil.isNotEmpty(brandIds) && !"-1".equals(brandIds)) {
			criteria.put("brandIds", brandIds.split(","));
		}
		
		List<TbProductDto> tbProductList = tbProductService.selectByCriteria(
				criteria);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("productList", tbProductList);
		return map;
    }
}