package com.linkon.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.model.TbWarehouse;
import com.liguo.hgl.proxydao.model.TbWarehouseUser;
import com.liguo.hgl.proxydao.model.WarehouseDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbAdminUserService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbWarehouseService;
import com.liguo.hgl.service.TbWarehouseUserService;
import com.liguo.hgl.util.BeanCopyUtil;
/**
 * 类的功能描述:仓库管理<br>
 * @author 周琨
 * @FileName BrandController.java<br>
 * @Language Java<br>
 * @date 2016-03-28<br>
 */
@Controller
@RequestMapping("warehouse")
public class WarehouseController{
  
	@Autowired
	protected TbWarehouseService tbWarehouseService;
	
	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
	
	@Autowired
	protected TbAdminUserService tbAdminUserService;
	
	@Autowired
	protected TbWarehouseUserService tbWarehouseUserService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping("/index")
    public String index(PageDto page,ModelMap model){
    	List<WarehouseDto> tList = tbWarehouseService.selectByObject(new Criteria(),page);
    	model.addAttribute(HglContants.PAGE_DTO_ID, page);
    	model.addAttribute("tList", tList);
        return "warehouse/warehouse";
    }
    
    /**
     * 
     * 去添加页面时初始化的信息
     * 
     * */
    
    @RequestMapping("/goAddWarehouseInfo")
    public String goAddWarehouseInfo(ModelMap model){
    	
    	model.addAttribute("provinceInfos", tbProvinceInfoService.selectByObject(null));
    	return "warehouse/addWarehousePage";
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
 	@RequestMapping("/serachWarehouse")
    public String searchResult(HttpServletRequest request,String name,PageDto page,Model model) throws IOException {
		Criteria criteria=new Criteria();
		criteria.put("name",name);
		List<WarehouseDto> tList = tbWarehouseService.selectByObject(criteria,page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
    	model.addAttribute("tList", tList);
    	model.addAttribute("name", name);
        return "/warehouse/warehouseList";
    }
    /**
     * 保存添加的数据
     * */
    @RequestMapping("/addTbWarehouse")
    public String addTbWarehouse(WarehouseDto dto,ModelMap model){
    	
    	TbWarehouse record = new TbWarehouse();
    	try{
    		BeanCopyUtil.CopyBeanToBean(dto,record);
    		record.setCreateDt(System.currentTimeMillis());
    		record.setVersion(0);
    		/*默认为启用状态*/
    		record.setStates(1);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	tbWarehouseService.insertSelective(record);
        return "redirect:/warehouse/index";
    }
    
    /**
     * 修改数据保存
     * */
    @RequestMapping("/updateTbWarehouse")
    public String updateTbWarehouse(WarehouseDto dto,ModelMap model) {
    	TbWarehouse record = new TbWarehouse();
    	try{
    		BeanCopyUtil.CopyBeanToBean(dto,record);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	tbWarehouseService.updateByPrimaryKeySelective(record);
        return "redirect:/warehouse/index";
    }
    
    /**
     * 修改数据时信息展示
     * */
    @RequestMapping("/showUpdateTbWInfo")
    public String showUpdateTbWInfo(Integer id,ModelMap model){
    	
    	TbWarehouse record = tbWarehouseService.selectByPrimaryKey(id);
    	model.addAttribute("provinceInfo", tbProvinceInfoService.selectByObject(null));
    	model.addAttribute("record", record);
        return "warehouse/updateWarehousePage";
    }
    /*
     * 添加仓库管理员时
     * */
    @RequestMapping("/showAddUserInfo")
    public String showAddUserInfo(ModelMap model,Integer wId){
    	//根据仓库ID，过滤掉已经是仓库管理员的adminuser用户
    	List<TbAdminUser> tbAdminUser = tbAdminUserService.selectByTbWarehouseId(wId);
    	
    	Criteria example = new Criteria();
    	example.put("warehouseId", wId);
    	List<TbWarehouseUser> warehouseUserList = tbWarehouseUserService.selectByObject(example);
    	model.addAttribute("tbAdminUser", tbAdminUser);
    	model.addAttribute("warehouseUserList", warehouseUserList);
    	return "warehouse/warehouseUserPage";
    }
    /*
     * 添加仓库管理员时
     * */
    @RequestMapping("/saveAddWarehouseUserInfo")
    public String saveAddWarehouseUserInfo(ModelMap model,Integer wId,Integer userId){
    	
    	TbAdminUser tbAdminUser = tbAdminUserService.selectByPrimaryKey(userId);
    	TbWarehouse tbWarehouse = tbWarehouseService.selectByPrimaryKey(wId);
    	TbWarehouseUser record = new TbWarehouseUser();
    	record.setWarehouseId(wId);
    	record.setUserId(userId);
    	record.setShopType(tbWarehouse.getShopType());
    	record.setWarehouseName(tbWarehouse.getWarehouseName());
    	record.setWarehouseTel(tbWarehouse.getWarehouseTel());
    	record.setContract(tbWarehouse.getContract());
    	record.setContractPhone(tbWarehouse.getContractPhone());
    	record.setCreateBy(111);
    	record.setCreateDt(System.currentTimeMillis());
    	record.setUserName(tbAdminUser.getName());
    	record.setUserAccount(tbAdminUser.getUserName());
    	tbWarehouseUserService.insertSelective(record); 
    	
    	/*执行操作完之后JSP页面反显*/
    	List<TbAdminUser> tbAdminUserList = tbAdminUserService.selectByTbWarehouseId(wId);
    	model.addAttribute("tbAdminUser", tbAdminUserList);
    	Criteria example = new Criteria();
    	example.put("warehouseId", wId);
    	List<TbWarehouseUser> warehouseUserList = tbWarehouseUserService.selectByObject(example);
    	model.addAttribute("warehouseUserList", warehouseUserList);
    	
    	return "warehouse/warehouseUserPage";
    }
    /*
     * 添加仓库管理员时
     * */
    @RequestMapping("/deleteWarehouseUserInfo")
    public String deleteWarehouseUserInfo(ModelMap model,Integer wId,Integer userId){
    	
    	tbWarehouseUserService.deleteByPrimaryKey(userId);
    	
    	Criteria example = new Criteria();
    	example.put("warehouseId", wId);
    	List<TbWarehouseUser> warehouseUserList = tbWarehouseUserService.selectByObject(example);
    	model.addAttribute("warehouseUserList", warehouseUserList);
    	/*执行操作完之后JSP页面反显*/
    	List<TbAdminUser> tbAdminUserList = tbAdminUserService.selectByTbWarehouseId(wId);
    	model.addAttribute("tbAdminUser", tbAdminUserList);
    	return "warehouse/warehouseUserPage";
    }
    @RequestMapping("/changeStates")
    public String changeStates(Integer wId ,Integer states,String name,PageDto page,Model model){
    	TbWarehouse tbWarehouse = tbWarehouseService.selectByPrimaryKey(wId);
    	tbWarehouse.setStates(states);
    	tbWarehouseService.updateByPrimaryKeySelective(tbWarehouse);
    	Criteria criteria=new Criteria();
		criteria.put("name",name);
		List<WarehouseDto> tList = tbWarehouseService.selectByObject(criteria,page);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
    	model.addAttribute("tList", tList);
    	model.addAttribute("name", name);
        return "/warehouse/warehouseList";
    }
}