package com.linkon.admin.action;

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
import com.liguo.hgl.proxydao.model.TbCompanyService;
import com.liguo.hgl.proxydao.model.TbCompanyType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbCompanyServiceService;
import com.liguo.hgl.service.TbCompanyTypeService;
import com.liguo.hgl.util.ImageUtil;
import com.liguo.hgl.util.StringUtil;

/**
 * 类的功能描述:公司类型服<br>
 *
 * @author 周琨
 * @FileName CompanyTypeController.java<br>
 * @Language Java<br>
 * @date 2016-07-25<br>
 */
@Controller
@RequestMapping("companyType")
public class CompanyTypeController extends IBaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("productTypeService")
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbCompanyTypeService tbCompanyTypeService;
	
	@Autowired
	protected TbCompanyServiceService tbCompanyServiceService;
	
    @Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
    	List<TbCompanyType> tbCompanyType = tbCompanyTypeService.selectByObject(null);
    	model.addAttribute("tbCompanyType", tbCompanyType);
	}
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     * 添加页面
     * */
    @RequestMapping("/showAddPage")
	public String showAddPage(Model model) {
		return "companyType/addCompanyType";
	}
    
    /**
	 *更新页面
	 */
    @RequestMapping("/showUpdateInfo")
    public String showUpdateInfo(Integer id,Model model){
    	TbCompanyType pifd = tbCompanyTypeService.selectByPrimaryKey(id);
    	model.addAttribute("pifd",pifd);
    	return "companyType/updateCompanyType";
    }
    
    /**
	 *展示详情 
	 */
    @RequestMapping("/showdetailsInfo")
    public String showdetailsInfo(Integer id,Model model){
    	TbCompanyType pifd = tbCompanyTypeService.selectByPrimaryKey(id);
    	model.addAttribute("pifd",pifd);
    	return "companyType/detailsCompanyType";
    }
    /*
     * 保存添加信息
     * */
    @RequestMapping("/saveAddInfo")
    public String saveServiceInfo(HttpServletRequest request,Model model){
    	TbCompanyType t = new TbCompanyType(); 
    	t.setName(request.getParameter("name"));
    	t.setImgName(request.getParameter("imgName"));
    	t.setDescribes(request.getParameter("describes"));
    	t.setVersion(0);
    	tbCompanyTypeService.insertTbCompanyType(t);
    	return "redirect:/companyType/index";
    }
    /*
     * 信息更新操作
     * */
    @RequestMapping("/updateService")
    public String updateService(HttpServletRequest request){
    	
    	TbCompanyType t = new TbCompanyType(); 
    	t.setName(request.getParameter("name"));
    	t.setImgName(request.getParameter("imgName"));
    	t.setDescribes(request.getParameter("describes"));
    	t.setVersion(Integer.parseInt(request.getParameter("version")));
    	t.setId(Integer.parseInt(request.getParameter("id")));
    	tbCompanyTypeService.updateTbCompanyTypeInfo(t);
    	return "redirect:/companyType/index";
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
    	ImageUtil.showImageWapProduct(null,imgName,response,HglContants.COMPANY_TYPE);
        return null;
    }
    /*
     * 信息删除
     * */
    @RequestMapping("/delCustomerService")
    public String delCustomerService(Integer id) {
	    tbCompanyTypeService.deleteByPrimaryKey(id);
    	return "redirect:/companyType/index";
    }
   
    /**
     * 
    * @author  ZK 
    * @date 2016-7-18 上午11:37:11 
    * @Description:删除校验
    * @parameter:
    * @return:
     */
    @RequestMapping("/delServiceInfoValidate")
    @ResponseBody
    public Map<String,Object> delServiceInfoValidate(@RequestParam Integer id ,Model model){
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("code",HglContants.NO_REPEAT);
		Criteria c = new Criteria();
		c.put("typeId",id);
		List<TbCompanyService> cList = tbCompanyServiceService.selectByObject(c);
		if(cList.size() > 0){
			map.put("code",HglContants.IS_REPEAT);
		}
		return map;
    }
    
    /**
     * 添加校验
     * */
    @RequestMapping("/addValidateGroup")
   	@ResponseBody
   	public  Map<String,Object> addValidateGroup(Model model,String name){
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put("code", HglContants.NO_REPEAT);
   		Criteria e = new Criteria();  
   		e.put("name", name.trim());
   		List<TbCompanyType> tList = tbCompanyTypeService.selectByObject(e);
   		if(tList.size() > 0){
   			map.put("code", HglContants.IS_REPEAT);
   		}
   		return map;
   	}
}