/*
 * Copyright (c)2016 深圳立国网络技术有限公司。
 * 版权所有
 */
package com.linkon.hgl.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.CalulateFreightDto;
import com.liguo.hgl.proxydao.dto.WapProductInventoryDto;
import com.liguo.hgl.proxydao.dto.WapShoppingCartDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbGroupDistribution;
import com.liguo.hgl.proxydao.model.TbUserGroup;
import com.liguo.hgl.proxydao.model.TbWapAddress;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.BaiduMapUtil;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.TbGroupDistributionService;
import com.liguo.hgl.service.TbUserGroupService;
import com.liguo.hgl.service.TbWapAddressService;
import com.liguo.hgl.service.TbWapShoppingCartService;
import com.liguo.hgl.util.ImageUtil;

/**
 *
 * @author 熊鹏
 * @FileName ShoppingCarController.java<br>
 * @Language Java<br>
 * @date 2016-05-13<br>
 */
@Controller
@RequestMapping("/shoppingCar")
public class ShoppingCarController extends IBaseController {

    @Autowired
    protected TbWapShoppingCartService tbWapShoppingCartService;
    
	@Autowired
	protected IProductTypeService productTypeService;
	
	@Autowired
	protected TbWapAddressService tbWapAddressService;
	
	@Autowired
	protected MyAddressController myAddressController;
	
	@Autowired
	protected TbGroupDistributionService tbGroupDistributionService;
	
	@Autowired
	protected TbUserGroupService tbUserGroupService;
	
    /**
     *  显示购物车
     */
	@Override
	protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
		//根据当前用户查询出购物车信息到页面展示
    	Criteria criteria = new Criteria();
		criteria.put("userId",  this.getLoginUserId());
		List<WapShoppingCartDto> goodsOrderList = tbWapShoppingCartService.selectByCart(criteria,this.getLon(),this.getLat());
		if(goodsOrderList != null && goodsOrderList.size() > 0){        
			model.addAttribute("goodsOrderList", goodsOrderList);
		}
	}

	@Override
	public String doSearchResult() {
		return resultPage();
	}

    /**
     * 添加商品到购物车ajax
     * @param orderListNum 记录用户添加的数量,格式: 库存=数量
     * @param deleteIdsList 记录是否在已经添加过的库存上添加的数量
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addShoppingCart", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> addShoppingCart(String orderListNum[],String deleteIdsList[]) throws Exception {
    	Map<String,Object> map = new HashMap<String,Object>();
    	boolean isSuccess = tbWapShoppingCartService.addShoppingCart(orderListNum,deleteIdsList,"accumulate",null,this.getLoginUserId());
    	map.put("isSuccess", isSuccess);
    	return map;
    }

    /**
     * 删除购物车中选中的商品ajax
     * @param deleteId 删除购物车ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteShoppingCart", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> deleteShoppingCart(String deleteId,Model model) throws Exception {
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(StringUtils.isNotBlank(deleteId)){
    		Criteria criteria = new Criteria();
    		criteria.put("userId", this.getLoginUserId());
    		criteria.put("cartId", Integer.parseInt(deleteId));
    		int delCount = tbWapShoppingCartService.deleteByCartObject(criteria);
    		logger.info("wap-deleteShoppingCart删除购物车条数: " + delCount);
    		if(delCount >0){
    			map.put("isSuccess", true);
    		}
    	}
    	return map;
    }
    
    /**
     * 删除失效购物车中的商品ajax
     * @param deleteIds 批量删除失效的购物车的所有id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteFailureShoppingCart", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> deleteFailureShoppingCart(String[] deleteIds,Model model) throws Exception {
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(deleteIds != null && deleteIds.length>0){
    		Criteria criteria = new Criteria();
			criteria.put("userId", this.getLoginUserId());
			criteria.put("idsList", Arrays.asList(deleteIds));
    		int delCount = this.tbWapShoppingCartService.deleteAllByPrimaryKey(criteria);
    		logger.info("wap-deleteShoppingCart删除失效购物车条数: " + delCount);
    		if(delCount >0){
    			map.put("isSuccess", true);
    		}
    	}
    	return map;
    }
    
   /**
    * 去计算跳转到提交订单页
    * @param orderListNum 记录用户添加的数量,格式: 库存=数量
    * @param cartIdsList 购物车Id,多个
    * @param addressId 收货地址Id
    * @param model
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/doSettlement", method = RequestMethod.POST)
	public String doSettlement(String orderListNum[],String cartIdsList[],Integer addressId,Model model) throws Exception {
    	//查询出该用户的勾选的商品
    	List<WapShoppingCartDto> cartList = tbWapShoppingCartService.doSettlement(orderListNum,cartIdsList,this.getLoginUserId());
		if(cartList != null && cartList.size() > 0){       
			model.addAttribute("goodsOrderList", cartList);
			//查询出我的地址和计算邮费
			doAddress(cartList,addressId,model);
		}
    	return "shoppingCar/settlement";
    }
    
    /**
     * 处理产品详情立即购买功能
     * @param orderListNum 记录用户添加的数量,格式: 库存=数量
     * @param deleteIdsList 记录是否在已经添加过的库存上添加的数量
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/nowBuy", method = RequestMethod.POST)
    public String nowBuy(String orderListNum[],String deleteIdsList[],Model model) throws Exception {
    	//查询出该用户的选中立即购买的商品
    	List<WapShoppingCartDto> cartList = tbWapShoppingCartService.nowBuy(orderListNum,deleteIdsList,this.getLoginUserId());
		if(cartList != null && cartList.size() > 0){        
			model.addAttribute("goodsOrderList", cartList);
			//查询出我的地址和计算邮费
			doAddress(cartList,null,model);
		}
    	return "shoppingCar/settlement";
    }
    
    /**
     * 处理我的地址和邮费计算,公用方法 
     * @param cartList 我的购物车列表集合
     * @param addressId 收货地址ID
     * @param model
     * @throws Exception
     */
    public void doAddress(List<WapShoppingCartDto> cartList,Integer addressId,Model model) throws Exception {
    	//查询出该用户的默认地址
		Criteria criteria = new Criteria();
		if(addressId != null){
			criteria.put("id", addressId);
		}else{
			criteria.put("checkFlag", 0);
		}
		criteria.put("userId", this.getLoginUserId());
		TbWapAddress wapAddress = tbWapAddressService.selectByDefaultObject(criteria);
		if(wapAddress != null){
			model.addAttribute("wapAddress", wapAddress);
			//根据地址和价格计算出运费
			Map<Integer, CalulateFreightDto> resultMap = tbWapShoppingCartService.calculateFreight(cartList,wapAddress.getLon(),wapAddress.getLat());
			if(resultMap != null && resultMap.size()>0){
				this.session.setAttribute(HglContants.SESSION_FREIGHT,resultMap);
			}
		}
		//计算出店铺的距离
		String cartIdsList = "";
		String shopIds = "";
		for(WapShoppingCartDto cart : cartList){
			 double lon1 = cart.getLon() == null ? 0.00 : Double.parseDouble(cart.getLon());
			 double lat1 = cart.getLat() == null ? 0.00 : Double.parseDouble(cart.getLat());
			 double lon = this.getLon() == null ? 0.00 : Double.parseDouble(this.getLon());
			 double lat = this.getLat() == null ? 0.00 : Double.parseDouble(this.getLat());
			 Double shopDistance = BaiduMapUtil.GetShortDistance(lon,lat, lon1, lat1);
			 cart.setShopDistance(shopDistance == null ? "" : String.valueOf(shopDistance));
			 for(WapProductInventoryDto inventory : cart.getProductInventoryList()){
				cartIdsList = cartIdsList+inventory.getCartId()+",";
			 }
			 shopIds = shopIds+cart.getShopId()+",";
		}
		//截取最后一个逗号
		if(!"".equals(cartIdsList) && cartIdsList.indexOf(",") != -1){ 
			cartIdsList = cartIdsList.substring(0, cartIdsList.lastIndexOf(","));
			model.addAttribute("cartIdsList", cartIdsList);
		}
		//截取最后一个逗号
		if(!"".equals(shopIds) && shopIds.indexOf(",") != -1){ 
			shopIds = shopIds.substring(0, shopIds.lastIndexOf(","));
		}
		//查询出优惠折扣
		if(StringUtils.isNotBlank(shopIds)){
			//想查询出用户属于哪个组
			criteria.put("shopIds", shopIds);
			List<TbGroupDistribution> groupDistributionList = tbGroupDistributionService.selectByShopIds(criteria);
			String groupIds = "";
			for(int i =0;i<groupDistributionList.size();i++){
				TbGroupDistribution group = groupDistributionList.get(i);
				if(i==0){
					groupIds = groupIds + group.getGroupId();
				}else{
					groupIds = groupIds + ","+group.getGroupId();
				}
			}
			//查询出该组中的优惠折扣
			if(StringUtils.isNotBlank(groupIds)){
				criteria.put("groupIds", groupIds);
				List<TbUserGroup> groupList = tbUserGroupService.selectByGroup(criteria);
				model.addAttribute("groupList", groupList);
			}
		}
    }
    
    /**
     * 显示出我的收货地址
     * @param addressId  收货地址ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showMyAddress", method = RequestMethod.POST)
    public String showMyAddress(String addressId,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
    	//根据地址Id查找出我的地址
		Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
		List<TbWapAddress> addressList = tbWapAddressService.selectByObject(criteria);
		if(addressList != null && addressList.size()>0){
			model.addAttribute("addressList", addressList);
			return "shoppingCar/addressList";
		}
		return "myAddress/AddLocationMyAddress";
    }
    
    /**
     * 根据收货ID查询
     * @param id  地址ID
     * @param frightFlag 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMyAddressById", method = RequestMethod.POST)
    public String getMyAddressById(Integer id,String frightFlag,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
		//查询出该用户的默认地址
		Criteria criteria = new Criteria();
		criteria.put("id", id);
		criteria.put("userId", this.getLoginUserId());
		TbWapAddress wapAddress = tbWapAddressService.selectByDefaultObject(criteria);
		if(wapAddress != null){
			model.addAttribute("wapAddress", wapAddress);
			//计算运费
			Object obj = this.session.getAttribute(HglContants.SESSION_FREIGHT);
			if(obj != null){
				Map<Integer, CalulateFreightDto> map = (Map<Integer, CalulateFreightDto>)obj;
				List<CalulateFreightDto> calulateFreightList = new ArrayList<CalulateFreightDto>();
				Map<Integer, CalulateFreightDto> resultMap = tbWapShoppingCartService.findCalculateFreight(map,calulateFreightList,wapAddress.getExtensionField());
				model.addAttribute("calulateFreightList", calulateFreightList);
				if(resultMap != null && resultMap.size()>0){
					this.session.setAttribute(HglContants.SESSION_FREIGHT,resultMap);
				}
			}
		}
		return "shoppingCar/address";
    }
    
	/**
	 * 新增收货地址初始化跳转
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/addAddressInit", method = RequestMethod.POST)
    public String addAddressInit(Model model) throws Exception {
    	myAddressController.addAddressInit(model);
		return "shoppingCar/addAddress";
    }
    
	 /**
     * 添加收货地址
     * @param address 地址对象
     * @param cartIdsList 需要购买的产品列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(TbWapAddress address,String cartIdsList[],HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
    	myAddressController.addAddress(address,model);
    	doSettlement(null,cartIdsList,address.getId(),model);
    	return "shoppingCar/settlement";
    }

    /**
     * 显示服务器图片
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
     * 获取收货地址
     * @param criteria
     * @param model
     */
    public void getAddress(Criteria criteria,Model model){
		criteria.put("userId", this.getLoginUserId());
		TbWapAddress wapAddress = tbWapAddressService.selectByDefaultObject(criteria);
		if(wapAddress != null){
			model.addAttribute("wapAddress", wapAddress);
		}
    }
    
}
