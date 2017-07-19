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
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.CustomerServiceDto;
import com.liguo.hgl.proxydao.model.TbCustomerService;
import com.liguo.hgl.proxydao.model.TbServiceType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbBrandService;
import com.liguo.hgl.service.TbCustomerServiceService;
import com.liguo.hgl.service.TbProductInventoryService;
import com.liguo.hgl.service.TbServiceTypeService;
import com.liguo.hgl.util.BeanCopyUtil;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

/**
 * 类的功能描述:服务产品<br>
 *
 * @author 周琨
 * @FileName CustomerServiceController.java<br>
 * @Language Java<br>
 * @date 2016-06-2<br>
 */
@Controller
@RequestMapping("customerService")
public class CustomerServiceController extends IBaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbCustomerServiceService tbCustomerServiceService;
	
	@Autowired
	protected TbServiceTypeService tbServiceTypeService;
	
    @Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
    	List<CustomerServiceDto> customerServiceList = tbCustomerServiceService.selectInfoListByName(null,page);
    	model.addAttribute("productInfoList", customerServiceList);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
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
   	 @RequestMapping(value = "/serachCustomerService")
   	    public String serachProduct(String name,PageDto page,Model model) throws IOException {
   		 	List<CustomerServiceDto> productInfoList = tbCustomerServiceService.selectInfoListByName(name,page);
   		 	model.addAttribute("productInfoList", productInfoList);
   		 	model.addAttribute(HglContants.PAGE_DTO_ID, page);
   	        return "customerService/customerServiceList";
   	    }
    /**
     * 初始化添加页面信息
     * */
    @RequestMapping("/initializationInfo")
	public String operationInventory(Model model) {
		Criteria example = new Criteria();
		example.put("parentId", 0);
		List<TbServiceType> mLists = tbServiceTypeService.selectByObject(example);
		model.addAttribute("mLists", mLists);
		return "customerService/addCustomerService";
	}
    
    @RequestMapping("/showUpdateInfo")
    public String showUpdateInfo(Integer id,Model model){
    	TbCustomerService pifd = tbCustomerServiceService.selectInfoById(id);
    	model.addAttribute("pifd",pifd);
    	return "customerService/updateCustomerService";
    }
    
    @RequestMapping("/showdetailsInfo")
    public String showdetailsInfo(Integer id,Model model){
    	CustomerServiceDto pifd = tbCustomerServiceService.selectInfoById(id);
    	model.addAttribute("pifd",pifd);
    	return "customerService/detailsCustomerService";
    }
    /*
     * 保存产品添加信息
     * */
    @RequestMapping("/saveServiceInfo")
    public String saveServiceInfo(CustomerServiceDto dto,Model model){
    	TbCustomerService t = new TbCustomerService(); 
    	try{
    		BeanCopyUtil.CopyBeanToBean(dto,t);
    		t.setVersion(0);
    		t.setOperationId(getLoginUserId());
    		tbCustomerServiceService.insertCustomerService(t);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "redirect:/customerService/index";
    }
    /*
     * 产品信息更新操作
     * */
    @RequestMapping("/updateService")
    public String updateService(CustomerServiceDto dto){
    	TbCustomerService t = new TbCustomerService(); 
    	try{
    		BeanCopyUtil.CopyBeanToBean(dto,t);
    		tbCustomerServiceService.UpdateCustomerService(t);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "redirect:/customerService/index";
    }
    
    @RequestMapping("/linkageMainPage")
    @ResponseBody
    public Map<String,Object> linkageMainPage(@RequestParam Integer id) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	Criteria example = new Criteria();
    	example.put("parentId", id);
    	List<TbServiceType> secondList = tbServiceTypeService.selectByObject(example);
    	map.put("secondList", secondList);
        return map;
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
  		  productTypeService.uploadForm(imgFile,newName,HglContants.CUSTOMER_TESTPATH);
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
    	ImageUtil.showImageWapProduct(id,imgName,response,HglContants.CUSTOMER_PATH);
        return null;
    }
    /*
     * 产品删除
     * */
    @RequestMapping("/delCustomerService")
    public String delCustomerService(Integer id) {
    	tbCustomerServiceService.deleteByPrimaryKey(id);
    	return "redirect:/customerService/index";
    }
   
    /**
     * 
    * @author  ZK 
    * @date 2016-7-18 上午11:37:11 
    * @Description:删除产品校验
    * @parameter:
    * @return:
     */
    @RequestMapping("/delServiceInfoValidate")
    @ResponseBody
    public Map<String,Object> delServiceInfoValidate(@RequestParam Integer id ,Model model){
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("code",00000);
		return map;
    }
}