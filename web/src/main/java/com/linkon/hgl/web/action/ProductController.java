package com.linkon.hgl.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.ProductDto;
import com.liguo.hgl.proxydao.dto.TbWapProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbWapProduct;
import com.liguo.hgl.proxydao.model.TbWapProductInventory;
import com.liguo.hgl.proxydao.model.WapProductInfoDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.FileUtil;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbWapProductInventoryService;
import com.liguo.hgl.service.TbWapProductService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

/**
 * 类的功能描述:wap产品<br>
 *
 * @author 熊鹏
 * @FileName ProductController.java<br>
 * @Language Java<br>
 * @date 2016-05-10<br>
 */
@Controller
@RequestMapping("product")
public class ProductController extends IBaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbBrandService tbBrandService;

	@Autowired
	protected TbWapProductService tbWapProductService;
	
	@Autowired
	protected TbWapProductInventoryService tbWapProductInventoryService;
	
	 /**
   	 * 查询数据列表
   	 * @param page
   	 * @param request
   	 * @param response
   	 * @param model
   	 * @return
   	 * @throws IOException
   	 */
//    @RequestMapping("/index")
//    public String index(PageDto page,ModelMap model){
//    	Criteria criteria = new Criteria();
//    	List<WapProductInfoDto> productInfoList = tbWapProductService.selectInfoListByName(criteria,page);
//    	model.addAttribute("productInfoList", productInfoList);
//    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
//    	return "product/product";
//    }
    
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
    public String serachProduct(String mainId,String brandId,String name,PageDto page,Model model) throws IOException {
   		Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
		if(StringUtils.isNotBlank(mainId)){
			criteria.put("typeId", mainId);
		}
		if(StringUtils.isNotBlank(brandId)){
			criteria.put("brandId", brandId);
		}
		if(StringUtils.isNotBlank(name)){
			criteria.put("name", name);
		}
   		List<WapProductInfoDto> productInfoList = tbWapProductService.selectInfoListByName(criteria,page);
	 	model.addAttribute("productInfoList", productInfoList);
	 	model.addAttribute(HglContants.PAGE_DTO_ID, page);
        return "/product/productList";
    }
   	 
    /**
     * 初始化添加页面信息
     * @param model
     * @return
     */
    @RequestMapping("/initializationInfo")
	public String operationInventory(Model model) {
		TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		List<TbProductType> mLists = productTypeService.selectByTbProductType(tbProductType);
		model.addAttribute("mLists", mLists);
		return "product/addProduct";
	}
    
    /**
     * 显示修改的产品信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showUpdateInfo")
    public String showUpdateInfo(Integer id,Model model){
    	Criteria criteria = new Criteria();
		criteria.put("id", id);
		criteria.put("userId", this.getLoginUserId());
    	WapProductInfoDto pifd = tbWapProductService.selectUpdateInfo(criteria);
    	String[] attr= new String[]{};
    	if(!StringUtils.isBlank(pifd.getAttributes())){
    	    attr = pifd.getAttributes().split("/");
    	}
    	model.addAttribute("sign",validateDelete(id));
    	model.addAttribute("pifd",pifd);
    	model.addAttribute("attr",attr);
    	return "product/updateProduct";
    }
    
    /**
     * 产品的详情信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showdetailsInfo")
    public String showdetailsInfo(Integer id,Model model){
    	Criteria criteria = new Criteria();
		criteria.put("id", id);
		criteria.put("userId", this.getLoginUserId());
    	WapProductInfoDto pifd = tbWapProductService.selectUpdateInfo(criteria);
    	String[] attr = pifd.getAttributes().split("/");
    	model.addAttribute("pifd",pifd);
    	model.addAttribute("attr",attr);
    	return "product/productDetail";
    }
    
    /**
     * 保存产品添加信息
     * @param dto
     * @param model
     * @return
     */
    @RequestMapping("/saveProductInfo")
    public String saveProductInfo(ProductDto dto,Model model){
    	TbWapProduct t = new TbWapProduct(); 
    	try{
    		BeanUtils.copyProperties(dto, t,"id","version");
    		if(dto.getThirdType() != null && dto.getThirdType() != 0){
    			t.setProductTypeId(dto.getThirdType());
    		}
    		t.setCreateBy(this.getLoginUserId());
    		t.setShopFlag(0);
    		t.setShopId(this.getShopId());
    		int count = tbWapProductService.insert(t);
    		
    		Integer userId =getLoginUserId();
    		copyFileToProduct(userId, t);
    		logger.info("wap产品保存记录: " + count);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "product/addProduct";
    }
    
    /**
     * 产品信息更新操作
     * @param dto
     * @return
     */
    @RequestMapping("/updateProduct")
    public String updateProduct(ProductDto dto,Model model){
    	TbWapProduct t = new TbWapProduct();   
    	try{
    		BeanUtils.copyProperties(dto, t);
    		t.setCreateBy(getLoginUserId());
    		int count = tbWapProductService.updateByPrimaryKeySelective(t);
    		Integer userId =getLoginUserId();
    		copyFileToProduct(userId, t);
    		logger.info("wap产品修改记录: " + count);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "product/updateProduct";
    }
    
    /**
     * 图片展示
     * @param imgFile
     * @param request
     * @param imgNo
     * @param model
     * @return
     */
    @RequestMapping("/validateImg")
    @ResponseBody
    public Map<String,Object> validate(@RequestParam CommonsMultipartFile imgFile,HttpServletRequest request ,String imgNo,Model model){
		String img = imgNo;
		Map<String, Object> map = new HashMap<String, Object>();
		String imgName = imgFile.getOriginalFilename();
		map.put("code", HglContants.SUCCESS);
		long imgSize = imgFile.getSize();
		if (imgSize > 5242880) {
			// 图片过大 不超过5MB
			map.put("code", HglContants.FAIL);
			return map;
		}
		long name = System.currentTimeMillis();
		String newName = StringUtil.changeFileName(img + "_" + name, imgName);
		try {
			productTypeService.uploadForm(imgFile, newName,HglContants.WAP_PRODUCT_TESTPATH);
			System.out.println(imgFile.toString());
			System.out.println(newName.toString());
		} catch (Exception e) {
			logger.debug("文件上传失败.....");
			e.printStackTrace();
		}
		map.put("imgNo", newName);
		model.addAttribute(imgNo, imgNo);
		return map;
	}
  
    /**
     * 生成显示图片
     * @param model
     * @param id
     * @param imgName
     * @param response
     * @return
     */
    @RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageWapProduct(id,imgName,response);
        return null;
    }
    
   /**
    * 产品删除
    * @param id
    * @return
    */
    @RequestMapping("/deleteProduct")
    public String deleteProduct(Integer id) {
    	if(validateDelete(id)){
    		tbWapProductService.deleteByPrimaryKey(id);
    	}
    	return "redirect:/product/index";
    }
    
   /**
    * 删除产品校验
    * @param id
    * @param model
    * @return
    */
    @RequestMapping("/deleteProductValidate")
    @ResponseBody
    public Map<String,Object> deleteProduct(@RequestParam Integer id ,Model model){
    	Map<String,Object> map = new HashMap<String,Object>();
    	boolean sign = validateDelete(id);
    	Integer code = sign ? HglContants.NO_REPEAT : HglContants.IS_REPEAT;
		map.put("code",code);
		return map;
    }
    
    /**
     * 验证该产品是否能够删除
     * @param id
     * @return
     */
    private boolean validateDelete(Integer id){
    	Criteria example = new Criteria();
    	example.put("productId",id);
    	List<TbWapProductInventory> tpiList = tbWapProductInventoryService.selectByObject(example);
    	if(tpiList.size() > 0){
    		//不可以删   		
    		return false;
    	}
    	return true;
    }
    
    /**
     * 根据条件搜索列表展示
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/searchProduct")
    public @ResponseBody Map<String,Object> searchProduct(HttpServletRequest request,Model model){
    	Criteria criteria = new Criteria();
    	String name = request.getParameter("name");
    	if (StringUtils.isNotBlank(name)) {
    		criteria.put("name", name);
    	}
		String firstTypeId = request.getParameter("firstTypeId");
		if (StringUtils.isNotBlank(firstTypeId) && !"-1".equals(firstTypeId)) {
			criteria.put("firstType", Integer.parseInt(firstTypeId));
		}
		String secondTypeId = request.getParameter("secondTypeId");
		if (StringUtils.isNotBlank(secondTypeId) && !"-1".equals(secondTypeId)) {
			criteria.put("secondType", Integer.parseInt(secondTypeId));
		}
		String thirdTypeId = request.getParameter("thirdTypeId");
		if (StringUtils.isNotBlank(thirdTypeId) && !"-1".equals(thirdTypeId)) {
			criteria.put("thirdType", Integer.parseInt(thirdTypeId));
		}
		String brandIds = request.getParameter("brandIds");
		if (StringUtils.isNotBlank(brandIds) && !"-1".equals(brandIds)) {
			criteria.put("brandIds", brandIds.split(","));
		}
		
		List<TbWapProductDto> tbProductList = tbWapProductService.selectByCriteria(criteria);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productList", tbProductList);
		return map;
    }
    
    /**
     * 批量上下架
     * @param status
     * @param productId
     * @return
     */
    @RequestMapping("/batchUpOrdownFrame")
    public Map<String,Object> batchUpOrdownFrame(String status,String productId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	Criteria criteria = new Criteria();
    	criteria.put("status", status);
    	criteria.put("productId", productId);
    	int count = tbWapProductInventoryService.batchUpOrdownFrame(criteria);
    	logger.info("批量上下架更新记录数： " + count);
    	if(count > 0){
    		map.put("isSuccess", true);
    	}
		return map;
    }

	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response,Model model) {
		TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		List<TbProductType> mList = productTypeService.selectByTbProductType(tbProductType);
		model.addAttribute("mList", mList);
		
		Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
    	List<WapProductInfoDto> productInfoList = tbWapProductService.selectInfoListByName(criteria,page);
    	model.addAttribute("productInfoList", productInfoList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}

	@Override
	public String doSearchResult() {
		return null;
	}
	
	/***
     * 
     * <相册空间图片复制到产品目录>
     * 
     * @param userId
     * @param tempTbWapProduct
     * @author wzt
     * @since 2016年6月8日
     */
    private void copyFileToProduct(Integer userId, TbWapProduct tempTbWapProduct) {
        String pimgone = tempTbWapProduct.getPimgOne();
        String pimgtwo = tempTbWapProduct.getPimgTwo();
        String pimgthree = tempTbWapProduct.getPimgThree();
        String dimgone = tempTbWapProduct.getDimgOne();
        String dimgtwo = tempTbWapProduct.getDimgTwo();
        String dimgthree = tempTbWapProduct.getDimgThree();

        Integer id = tempTbWapProduct.getId();

        getFilePath(userId, id, pimgone);

        getFilePath(userId, id, pimgtwo);

        getFilePath(userId, id, pimgthree);

        getFilePath(userId, id, dimgone);

        getFilePath(userId, id, dimgtwo);

        getFilePath(userId, id, dimgthree);
    }
    
    private void getFilePath(Integer userId, Integer id, String pimgone) {
        String fromFileName = HglContants.WAP_ALBUM_SPACE + userId + File.separator + pimgone;
        String toFileName = HglContants.WAP_PRODUCT + id + File.separator + pimgone;
        FileUtil.copy(fromFileName, toFileName);
    }

    
}