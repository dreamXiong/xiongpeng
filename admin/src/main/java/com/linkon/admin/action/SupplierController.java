package com.linkon.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.ShopWebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbWebUserService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;
/**
 * 供应商审核管理逻辑层
 * @fiSupplierController.java
 * @2016-4-27	
 * @author 周双双
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController extends IBaseController{
	@Autowired
	protected TbWebUserService tbWebUserService;
	
	@Autowired
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
	
	@Autowired
	protected TbShopInfoService tbShopInfoService;
	
	/**
	 * 查询所有的厂家信息
	 * @param page
	 * @return
	 * @author zss
	 */
	 @RequestMapping(value="/suppliers")
     public ModelAndView getSuppliers(PageDto page){
    	ModelAndView modelAndView = new ModelAndView();
    	//List<TbwebUserShopDto> webList = tbWebUserService.getSupplierWebUserList(new Criteria(),page);
    	List<ShopWebUserDto> webList = tbShopInfoService.getSupplierWebUserList(new Criteria(),page);
    	if(webList.size()>0){
    		modelAndView.addObject("suppliers", webList);
    	}
    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
    	modelAndView.setViewName("shop/suppliers");
        return modelAndView;
     }
	 
	@RequestMapping(value="/searchResult")
    @ResponseBody
    public ModelAndView getSearchResult(HttpServletRequest request,PageDto page){
    	Criteria criteria = new Criteria();
		String name = request.getParameter("companyName").trim();
		if (!StringUtils.isEmpty(name)) {
			criteria.put("companyName", name);
		}
		if(!StringUtils.isEmpty(request.getParameter("state"))){
		criteria.put("state",  Integer.parseInt(request.getParameter("state")));
		}
    	ModelAndView modelAndView = new ModelAndView();
    	//List<TbwebUserShopDto> suppliers = tbWebUserService.getSupplierWebUserList(criteria,page);
    	List<ShopWebUserDto> suppliers = tbShopInfoService.getSupplierWebUserList(criteria,page);
    	modelAndView.addObject("suppliers", suppliers);
    	modelAndView.addObject(HglContants.PAGE_DTO_ID, page);
    	modelAndView.setViewName("shop/suppliersUserList");
        return modelAndView;
    }
	/**
	 * 修改初始页
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/updateSuppliers",method=RequestMethod.GET) 
    public ModelAndView getSuppliersById(@RequestParam Integer id){
    	ModelAndView modelAndView = new ModelAndView();
    	 TbProductType tbProductType = new TbProductType();
		 tbProductType.setParentId(0);
		 modelAndView.addObject("productTypes",  productTypeService.selectByTbProductType(tbProductType));
    	ShopWebUserDto shopDto  = tbWebUserService.getShopWebUser(id);
    	if(shopDto!=null){
    		modelAndView.addObject("shopDto", shopDto);
    		modelAndView.addObject("provinceInfos", tbProvinceInfoService.selectByObject(null));
    	}
    	modelAndView.setViewName("shop/updateSuppliers");
        return modelAndView;
     }
	/**
	 * 修改厂家资料
	 * @param param
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/updateSuppliers",method=RequestMethod.POST) 
    public ModelAndView updateSuppliers(@RequestParam Map<String, Object> param,@RequestParam Integer id){
		System.out.println("param:::::"+param+"---------"+id);
    	ModelAndView modelAndView = new ModelAndView();
    	tbWebUserService.updateUserShop(param, id);
    	modelAndView.setViewName("redirect:suppliers");
        return modelAndView;
     }
	
	/**
	 * 厂家详情页面
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/suppliersDetail") 
    public ModelAndView getSuppliersDetial(@RequestParam Integer id){
    	ModelAndView modelAndView = new ModelAndView();
    	ShopWebUserDto shopDto  = tbWebUserService.getShopWebUser(id);
    	if(shopDto!=null){
    		modelAndView.addObject("shopDto", shopDto);
    	}
    	modelAndView.setViewName("shop/suppliersDetail");
        return modelAndView;
     }
	/**
	 * 修改用户状态
	 * @param id
	 * @return
	 * @author zss
	 */
	@RequestMapping(value="/updateUserState")
    @ResponseBody
    public Map<String,Object> updateUserState(Integer id,Integer userId,Integer state,String checkmesg){
		logger.debug("id----------"+id+"-------"+state);
    	Map<String,Object> map = tbWebUserService.updateState(id,userId,state,checkmesg);
    	return map;
    }
	
	/**
	 * 平台审核通过平台结算功能
	 * */
	@RequestMapping(value="/openSettlement")
    @ResponseBody
    public Map<String,String> openSettlement(Integer id,String openType){
    	Map<String,String> map = new HashMap<String ,String>();
    	if(tbShopInfoService.openSettlement(id,openType) == 1){
    		tbShopInfoService.addShopIntegral(id);
    		map.put("message", "平台结算");
    		logger.debug("平台结算审核成功");
    	}else{
    		map.put("message", "平台结算审核失败");
    		 logger.debug("平台结算审核失败");
    	}
    	return map;
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
	/**
	 * 修改图片 shopInfo
	 * @param imgFile
	 * @param request
	 * @param imgNo
	 * @param model
	 * @return
	 * @author zss
	 */
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
	  		 productTypeService.uploadForm(imgFile,newName,HglContants.SHOP_INFO_TESTPATH);
	  		  System.out.println(newName.toString());
	  	  }catch(Exception e){
	  		  logger.debug("文件上传失败.....");
	  		  e.printStackTrace();
	  	  }
	  	  map.put("imgNo",newName);
	  	  model.addAttribute(imgNo,imgNo);
	  	  return map;
	    }
	 /**
	  * 显示图片，shop
	  * @param model
	  * @param id
	  * @param imgName
	  * @param response
	  * @return
	  * @author zss
	  */
	  @RequestMapping("/generateImage")
	    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
	    	ImageUtil.showImageShop(id,imgName,response);
	        return null;
	    }

}
