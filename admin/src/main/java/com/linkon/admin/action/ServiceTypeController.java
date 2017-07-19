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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbServiceType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbCustomerServiceService;
import com.liguo.hgl.service.TbServiceTypeService;
import com.liguo.hgl.util.ImageUtil;

/**
 * 服务类别<br>
 *
 * @author 周琨
 * @FileName ProductTypeController.java<br>
 * @Language Java<br>
 * @date 2016-05-28<br>
 */
@Controller
@RequestMapping("serviceType")
public class ServiceTypeController extends IBaseController {
	@Autowired
	protected TbServiceTypeService tbServiceTypeService;
	
	@Autowired
	protected TbCustomerServiceService tbCustomerServiceService;

	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		//果询主服务记录
		Criteria e = new Criteria();
		e.put("parentId",0);
    	List<TbServiceType> sList = tbServiceTypeService.selectByObject(e);
    	List<TbServiceType> pList = null;
    	if(sList.size() > 0){
    		Criteria example = new Criteria();
    		example.put("parentId", sList.get(0).getId());
    		pList = tbServiceTypeService.selectByObject(example);
    		model.addAttribute("Id", sList.get(0).getId());
    	}
    	model.addAttribute("pList", pList);
    	model.addAttribute("sList", sList);
	}

	@Override
	public String doSearchResult() {
		return null;
	}
	
	 @RequestMapping(value="/goServiceTypeAddPage")
    public String updateLevel(Model mode){
        return "serviceType/serviceTypeAddPage";
    }
	 /*
     * 添加分类时查询表中是否有重复值
     * */
    @RequestMapping("/addValidateProductType")
    @ResponseBody
    public Map<String,Object> addValidateServicesType(@RequestParam String name){
    	Map<String,Object> map = new HashMap<String,Object>();
    	Criteria example = new Criteria();
    	example.put("name",name.trim());
    	List<TbServiceType> pList = tbServiceTypeService.selectByObject(example);
    	System.out.println(pList.size());
    	if(pList.size() > 0){
    		map.put("code", "1");
    	}else{
    		map.put("code", "0");
    	}
    	return map;
    }
    /**
     * 一级分类的添加
     * **/
    @RequestMapping("/addFirstProductType")
    public String addFirstProductType(@RequestParam String name,@RequestParam String describe){
    	TbServiceType record = new TbServiceType();
    	record.setName(name);
    	record.setDescribes(describe);
    	record.setParentId(0);
    	record.setVersion(0);
    	tbServiceTypeService.insertSelective(record);
    	return "redirect:/serviceType/index";
    }
    
    /*
     * 删除大分类时作校验（确定弹出框是否弹出）
     * 
     * */
    @RequestMapping("/deleteFirstServiceType")
    @ResponseBody
    public Map<String,Object> deleteFirstServiceType(@RequestParam Integer id){
    	Map<String,Object> map = new HashMap<String,Object>();
    		Criteria example = new Criteria();
    		example.put("parentId",id);
    		List<TbServiceType> pList = tbServiceTypeService.selectByObject(example);
    	if(pList.size() > 0){
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
    @RequestMapping("/configDeleteServiceType")
    public String configDeleteServiceType(@RequestParam Integer id){
    	tbServiceTypeService.deleteByPrimaryKey(id);
    	 return "redirect:/serviceType/index";
    }
    /**
     * 更新服务大类时初始化信息
     * */
    @RequestMapping("/updateServiceType")
    public String updateServiceType(@RequestParam Integer id,ModelMap model){
    	model.addAttribute("serviceType", tbServiceTypeService.selectByPrimaryKey(id));
    	return "/serviceType/updateServiceType";
    }
    
    /*
     * 修改分类时查询除自身外是否还有重复记录
     * 
     * */
    @RequestMapping("/updateValidateProductType")
    @ResponseBody
    public Map<String,Object> updateValidateProductType(@RequestParam String name,@RequestParam Integer id){
    	Map<String,Object> map = new HashMap<String,Object>();
    	Criteria example = new Criteria();
    	example.put("name",name.trim());
    	List<TbServiceType> pList = tbServiceTypeService.selectByObject(example);
    	map.put("code", "0");
    	if(pList.size() == 1){
    		TbServiceType t = pList.get(0);
    		if(!id.equals(t.getId())){
    			map.put("code", "1");
    		}
    	}
    	if(pList.size() > 1 ){
    		map.put("code", "1");
    	}
    	return map;
    }
    /**
     * 产品大类更新
     * **/
    @RequestMapping("/updateFirstProductType")
    public String updateFirstProductType(@RequestParam String name,@RequestParam String describes,@RequestParam Integer id,@RequestParam Integer version){
    	TbServiceType tbProductType = new TbServiceType();
    	tbProductType.setId(id);
    	tbProductType.setName(name);
    	tbProductType.setDescribes(describes);
    	tbProductType.setParentId(0);
    	tbProductType.setVersion(version);
    	tbServiceTypeService.updateByPrimaryKey(tbProductType);
        return "redirect:/serviceType/index";
    }
    
    /**
     * 添加二级服务页面
     * */
    @RequestMapping("/goSecondServiceTypeAddPage")
    public String goSecondServiceTypeAddPage(ModelMap model,@RequestParam Integer mainId){
    	model.addAttribute("serviceType", tbServiceTypeService.selectByPrimaryKey(mainId));
    	return "serviceType/addSecondServiceType";
    }
    
    /**
     * 二级分类的添加
     * **/
    @RequestMapping("/addSecondProductType")
    public String addSecondProductType(@RequestParam String name,@RequestParam String describes,@RequestParam Integer praentId){
    	TbServiceType t = new TbServiceType();
    	t.setName(name);
    	t.setDescribes(describes);
    	t.setParentId(praentId);
    	t.setVersion(0);
    	tbServiceTypeService.insertSelective(t);
    	return "redirect:/serviceType/index";
    }
    /*
     * 删除二级分类（删除框是否弹出）
     * 
     * */
    @RequestMapping("/deleteSecondServiceType")
    @ResponseBody
    public Map<String,Object> deleteSecondServiceType(@RequestParam Integer id){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("code", "0");
    	Criteria example = new Criteria();
    	example.put("servicetypeId",id);
    	int cSize = tbCustomerServiceService.selectByObject(example).size();
    	if(cSize > 0){
    		map.put("code", "1");
    	}
    	return map;
    }
    /**
     * 修改二级服务时初始化页面
     * */
    @RequestMapping("/updateSecondServiceType")
    public String updateSecondServiceType(@RequestParam Integer id,ModelMap model){
      	model.addAttribute("productType", tbServiceTypeService.selectByPrimaryKey(id));
      	Criteria example = new Criteria();
      	example.put("parentId", 0);
      	model.addAttribute("firstProductTypeList",tbServiceTypeService.selectByObject(example));
      	return "serviceType/updateSecondServiceType";
      }
    /**
     * 产品更新二级分类
     * **/
    @RequestMapping("/subUpdateSecondServiceType")
    public String subUpdateSecondServiceType(@RequestParam String name,@RequestParam String describes,
    										@RequestParam Integer parentId,@RequestParam Integer id,@RequestParam Integer version){
    	TbServiceType t = new TbServiceType();
    	t.setName(name);
    	t.setDescribes(describes);
    	t.setParentId(parentId);
    	t.setVersion(version);
    	t.setId(id);
    	tbServiceTypeService.updateByPrimaryKey(t);
        return "redirect:/serviceType/index";
    }
    
    @RequestMapping("/linkageMainPage")
    public String linkageMainPage(@RequestParam Integer id,ModelMap model){
    	Criteria example = new Criteria();
		example.put("parentId", id);
		List<TbServiceType> pList = tbServiceTypeService.selectByObject(example);
		model.addAttribute("Id", id);
		model.addAttribute("pList", pList);
		model.addAttribute("sList", 11);
    	return "serviceType/serviceTypeList";
    }
    
    @RequestMapping("/generateImage")
    public String generateImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageWapProduct(id,imgName,response,HglContants.CUSTOMER_PATH);
        return null;
    }
}
