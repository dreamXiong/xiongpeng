package com.linkon.hgl.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAddress;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbAddressService;
import com.liguo.hgl.service.TbProvinceInfoService;

/**
 * 收货地址
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/myAddress")
public class MyAddressController extends IBaseController {

	@Autowired
	protected TbAddressService tbAddressService;
	@Autowired
	protected IProductTypeService productTypeService;
	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
	
	/**
	 * 查询出当前用户的收货地址
	 */
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
		List<TbAddress> addressList = tbAddressService.selectByObject(criteria);
		if(addressList != null && addressList.size()>0){
			model.addAttribute("addressList", addressList);
		}
		
		TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		model.addAttribute("productTypes",  productTypeService.selectByTbProductType(tbProductType));
		model.addAttribute("provinceInfos", tbProvinceInfoService.selectByObject(null));
	}

	 /**
     * 添加收货地址
     * @param orderListNum
     * @param shoppingType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(TbAddress address,Model model) throws Exception {
    	Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
		List<TbAddress> addressList = tbAddressService.selectByObject(criteria);
		address.setCheckFlag(0);
		if(addressList != null && addressList.size()>0){
			address.setCheckFlag(1);
		}
    	address.setCreateBy(this.getLoginUserId());
    	int count = tbAddressService.insert(address);
    	super.logger.info("插入地址记录数: " + count);
    	init(null,null,null,model); //调用查询方法
    	return "shoppingCar/addressList";
    }
    
    /**
     * 修改收货地址
     * @param orderListNum
     * @param shoppingType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modifyAddress", method = RequestMethod.POST)
    public String modifyAddress(TbAddress address,Model model) throws Exception {
    	address.setCreateBy(this.getLoginUserId());
    	int count = tbAddressService.updateByPrimaryKey(address);
    	super.logger.info("更新地址记录数: " + count);
    	init(null,null,null,model); //调用查询方法
    	return "shoppingCar/addressList";
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
    		int count = tbAddressService.deleteByObject(criteria);
    		super.logger.info("删除地址记录数: " + count);
    	}
    	init(null,null,null,model); //调用查询方法
    	return "shoppingCar/addressList";
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
    		int count = tbAddressService.defaultAddress(id, newId, this.getLoginUserId());
    		super.logger.info("设置为默认地址记录数: " + count);
    	}
    	init(null,null,null,model); //调用查询方法
    	return "shoppingCar/addressList";
    }
    
	@Override
	public String doSearchResult() {
		return null;
	}

}
