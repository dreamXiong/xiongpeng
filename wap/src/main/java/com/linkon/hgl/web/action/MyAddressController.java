package com.linkon.hgl.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liguo.hgl.proxydao.dto.WebUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbWapAddress;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbWapAddressService;

/**
 * 收货地址
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/myAddress")
public class MyAddressController extends IBaseController {

	@Autowired
	protected TbWapAddressService tbWapAddressService;
	@Autowired
	protected IProductTypeService productTypeService;
	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
	
	/**
	 * 查询出当前用户的收货地址
	 */
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		//查询出当前用户的所有收货地址
		Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
		List<TbWapAddress> addressList = tbWapAddressService.selectByObject(criteria);
		if(addressList != null && addressList.size()>0){
			model.addAttribute("addressList", addressList);
		}
	}
	
	/**
	 * 新增收货地址初始化
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/addAddressInit")
    public String addAddressInit(Model model) throws Exception {
    	//从根节点查询出产品类型
    	TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		model.addAttribute("productTypes",  productTypeService.selectByTbProductType(tbProductType));
		model.addAttribute("provinceInfos", tbProvinceInfoService.selectByObject(null));
    	return "myAddress/AddMyAddress";
    }
    
    /**
	 * 新增定位收货地址初始化
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/addLocationAddressInit")
    public String addLocationAddressInit(String lon,String lat,Model model) throws Exception {
    	if(lon != null && lat != null){   
    		model.addAttribute("location", lon+","+lat);
    	}
    	return "myAddress/AddLocationMyAddress";
    }
    
    /**
	 * 新增定位收货地址确认
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/AddLocationMyAddressConfirm")
    public String AddLocationMyAddressConfirm(String name,String address,String lon,String lat,String id,String doorplate,Model model) throws Exception {
    	model.addAttribute("name", name);
    	model.addAttribute("address",address);
    	model.addAttribute("lon", lon);
    	model.addAttribute("lat", lat);
    	if(id != null && !"".equals(id)){
    		model.addAttribute("id", id);
    		model.addAttribute("doorplate", doorplate);
    	}
    	return "myAddress/AddLocationMyAddressConfirm";
    }

	 /**
     * 添加收货地址
     * @param orderListNum
     * @param shoppingType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(TbWapAddress address,Model model) throws Exception {
    	//判断当前用户是否添加过收货地址,如果没有添加过,第一条为默认地址
    	Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
		List<TbWapAddress> addressList = tbWapAddressService.selectByObject(criteria);
		address.setCheckFlag(0);
		if(addressList != null && addressList.size()>0){
			address.setCheckFlag(1);
		}
		WebUserDto webUser = this.getLoginUser();
		address.setPhone(webUser.getMobile());
		address.setRecipient(webUser.getName());
    	address.setCreateBy(this.getLoginUserId());
    	int count = tbWapAddressService.insert(address);
    	super.logger.info("插入地址记录数: " + count);
    	return "redirect:index";
    }
    
	/**
	 * 修改收货地址位置初始化
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/modifyLocationAddressInit")
    public String modifyLocationAddressInit(Integer id,String lon,String lat,Model model) throws Exception {
    	if(id != null){
    		//id不等于空,修改操作,查询出当前的位置
    		Criteria criteria = new Criteria();
    		criteria.put("id", id);
    		criteria.put("userId", this.getLoginUserId());
    		TbWapAddress address = tbWapAddressService.selectByPrimaryKey(criteria);
    		model.addAttribute("address", address);
    		if(address != null && address.getLon() != null && address.getLat() != null){   
    			model.addAttribute("location", address.getLon()+","+address.getLat());
    		}
    	}else{
    		//新增操作,根据坐标定位 
    		if(lon != null && lat != null){   
        		model.addAttribute("location", lon+","+lat);
        	}
    	}
    	return "myAddress/AddLocationMyAddress";
    }
    
	/**
	 * 修改收货地址初始化
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/modifyAddressInit")
    public String modifyAddressInit(Integer id,Model model) throws Exception {
    	//根据地址id查询出地址
    	Criteria criteria = new Criteria();
    	criteria.put("id", id);
		criteria.put("userId", this.getLoginUserId());
		TbWapAddress address = tbWapAddressService.selectByPrimaryKey(criteria);
		model.addAttribute("address", address);
		
		//查询出级联的省份
    	TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		model.addAttribute("productTypes",  productTypeService.selectByTbProductType(tbProductType));
		model.addAttribute("provinceInfos", tbProvinceInfoService.selectByObject(null));
    	return "myAddress/updateMyAddress";
    }
    
    /**
     * 修改收货地址
     * @param orderListNum
     * @param shoppingType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modifyAddress", method = RequestMethod.POST)
    public String modifyAddress(TbWapAddress address,Model model) throws Exception {
    	//先根据id查询,在拷贝到新对象在更新
    	Criteria criteria = new Criteria();
    	criteria.put("id", address.getId());
		criteria.put("userId", this.getLoginUserId());
		TbWapAddress persistentaddress = tbWapAddressService.selectByPrimaryKey(criteria);
    	BeanUtils.copyProperties(address, persistentaddress,"id","version","createDt","createBy");
    	int count = tbWapAddressService.updateByPrimaryKeySelective(persistentaddress);
    	super.logger.info("更新地址记录数: " + count);
    	return "redirect:index";
    }
    
    /**
     * 删除收货地址
     * @param orderListNum
     * @param shoppingType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
    public String deleteAddress(String id,Model model) throws Exception {
    	if(StringUtils.isNotBlank(id)){
    		Criteria criteria = new Criteria();
    		criteria.put("userId", this.getLoginUserId());
    		criteria.put("id", id.trim());
    		int count = tbWapAddressService.deleteByObject(criteria);
    		super.logger.info("删除地址记录数: " + count);
    	}
    	return "redirect:index";
    }
    
    /**
     * 设置为默认收货地址
     * @param orderListNum
     * @param shoppingType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/defaultAddress", method = RequestMethod.POST)
    public String defaultAddress(String id,String newId,Model model) throws Exception {
    	if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(newId)){
    		int count = tbWapAddressService.defaultAddress(id, newId, this.getLoginUserId());
    		super.logger.info("设置为默认地址记录数: " + count);
    	}
    	return "redirect:index";
    }
    
	@Override
	public String doSearchResult() {
		return null;
	}

}
