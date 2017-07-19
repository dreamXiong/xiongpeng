/*
 * Copyright (c)2016 深圳立国网络技术有限公司。
 * 版权所有
 */
package com.linkon.hgl.web.action;

import java.io.IOException;
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
import com.liguo.hgl.proxydao.dto.ShoppingCartDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAddress;
import com.liguo.hgl.proxydao.model.TbProductType;
import com.liguo.hgl.proxydao.model.TbTalk;
import com.liguo.hgl.proxydao.model.TbWeixinPassUser;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.IDemoService;
import com.liguo.hgl.service.IErnieService;
import com.liguo.hgl.service.IProductTypeService;
import com.liguo.hgl.service.IWeixinTalkService;
import com.liguo.hgl.service.TbAddressService;
import com.liguo.hgl.service.TbProvinceInfoService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbShoppingCartService;
import com.liguo.hgl.util.ImageUtil;

/**
 * 支付结算业务入口Controller<br>
 * 购物车的实现
 *
 * @author 吴奇
 * @FileName ShoppingCarController.java<br>
 * @Language Java<br>
 * @date 2015-06-20<br>
 */
@Controller
@RequestMapping("/shoppingCar")
public class ShoppingCarController extends IBaseController {

    @Autowired
    protected IDemoService demoService;
    @Autowired
    protected IErnieService ernieService;
    @Autowired
    protected IWeixinTalkService weixinTalkService;
    @Autowired
    protected TbShoppingCartService tbShoppingCartService;
    @Autowired
	protected TbAddressService tbAddressService;
	@Autowired
	protected IProductTypeService productTypeService;
	@Autowired
	protected TbProvinceInfoService tbProvinceInfoService;
	@Autowired
	protected TbShopInfoService tbShopInfoService;
	
    /**
     * 显示购物车
     * @param model
     * @return
     * @throws Exception
     */
	@Override
	protected void init(PageDto page, HttpServletRequest request, HttpServletResponse response, Model model) {
    	Criteria criteria = new Criteria();   
    	Integer userId = this.getLoginUserId();
		criteria.put("userId", userId);
		criteria.put("discount", this.getUserSale());  
		Map<String,List<ShoppingCartDto>> shoppingMap = tbShoppingCartService.selectByCart(criteria);
		List<ShoppingCartDto> merchantOrderList = shoppingMap.get("merchantOrder");
		if(merchantOrderList != null && merchantOrderList.size() > 0){  //传递到前台显示
			model.addAttribute("merchantOrderList", merchantOrderList);
		}
		List<ShoppingCartDto> goodsOrderList = shoppingMap.get("goodsOrder");
		if(goodsOrderList != null && goodsOrderList.size() > 0){        //传递到前台显示
			model.addAttribute("goodsOrderList", goodsOrderList);
		}
	}

	@Override
	public String doSearchResult() {
		return resultPage();
	}

    /**
     * 弹幕页面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/barrage")
    public String barrage(PageDto page,HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
    	page.pageSize=5;
    	List<TbWeixinPassUser> queryRandeUserList = ernieService.queryRandeUserList(page); 
    	request.setAttribute(HglContants.PAGE_DTO_ID, page);
    	model.addAttribute("dataDto", queryRandeUserList);
        //获取弹幕消息
        return "";
    }
    
    /**
     * 弹幕页面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/searchResult")
    public String searchResult(PageDto page,HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
    	
    	List<TbWeixinPassUser> queryRandeUserList = ernieService.queryRandeUserList(page); 
    	request.setAttribute("page", page);
    	model.addAttribute("dataDto", queryRandeUserList);
        //获取弹幕消息
        return "/barrage/talkList";
    }
    
    /**
     * 获取弹幕列表
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/barrageList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> barrageList(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	Criteria condition = new Criteria();
    	condition.put("isDelete", "0");//未删除
    	condition.put("isShow", "1");//审核通过
    	/*condition.setMysqlOffset(0);
    	condition.setMysqlLength(1);*/
    	condition.setOrderByClause("create_time asc");
    	
        //获取弹幕消息
    	List<TbTalk> list = weixinTalkService.getWeixinTalkList(condition);
    	if(list.isEmpty() == false){
    		for(TbTalk talk:list){
    			talk.setIsDelete(1);
        		weixinTalkService.updateWeixinTalkStatut(talk);//设置为删除状态
    		}
    		map.put("talkList", list);
        	map.put("listSize", list.size());
    	}else{
    		map.put("talkList", null);
        	map.put("listSize", 0);
    	}
    	
    	map.put("code", "0000");
    	
    	
        return map;
    }
    
   
   /* @RequestMapping("/index")
	public String index(Model model) throws Exception {}*/

    /**
     * 添加商品到购物车
     * @param orderListNum
     * @param shoppingType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addShoppingCart", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> addShoppingCart(String orderListNum[],String deleteIdsList[],String shoppingType,String merchantsId,String brandId) throws Exception {
    	Map<String,Object> map = new HashMap<String,Object>();
    	//招商为true,订货单为false
    	boolean shopType = HglContants.MERCHANT_ORDER.equals(Integer.parseInt(shoppingType)) ? true : false;
    	//执行加入购物车方法
    	boolean isSuccess = tbShoppingCartService.addShoppingCart(orderListNum,deleteIdsList,shopType,this.getLoginUser(),this.getUserSale(),merchantsId,brandId);
    	map.put("isSuccess", isSuccess);
    	if(isSuccess){
    		//获取购物车数量,并添加session
    		int cart = tbShoppingCartService.getUserCartNumber(this.getLoginUserId()); 
			session.setAttribute(HglContants.SESSION_CART, String.valueOf(cart));
			map.put("cartNumber", String.valueOf(cart));
    	}
    	return map;
    }

    /**
     * 删除购物车中选中的商品
     * @param deleteGoodsList
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteShoppingCart", method = RequestMethod.POST)
    public String deleteShoppingCart(String deleteGoodsList[],String cartNumber,Model model) throws Exception {
    	if(deleteGoodsList != null && deleteGoodsList.length>0){
    		//转换为list删除
    		List<String> delIdsList = Arrays.asList(deleteGoodsList); 
    		//执行删除购物车操作
    		Criteria criteria = new Criteria();
    		criteria.put("idsList", delIdsList);
    		criteria.put("userId", this.getLoginUserId());
    		int delCount = tbShoppingCartService.deleteByPrimaryKey(criteria);
    		logger.info("deleteShoppingCart删除购物车条数: " + delCount);
    		//调用查询方法
    		init(null,null,null,model); 
    		//重新计算购物车数量
    		reCalculateCartNumber(cartNumber);
    	}
    	return "shoppingCar/shoppingCarList";
    }
    
    /**
     * 去结算
     * @param orderListNum
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSettlement", method = RequestMethod.POST)
	public String doSettlement(String orderListNum[],String cartIdsList[],String cartNumber,Model model,String cartIds) throws Exception {
    	//获取添加的购物车信息
    	List<ShoppingCartDto> cartList = tbShoppingCartService.doSettlement(orderListNum,cartIdsList,this.getLoginUserId(),this.getUserSale());
		if(cartList != null && cartList.size() > 0){        
			model.addAttribute("goodsOrderList", cartList);
		}
		//获取该用户的收货地址
		Criteria criteria = new Criteria();
		criteria.put("userId", this.getLoginUserId());
		List<TbAddress> addressList = tbAddressService.selectByObject(criteria);
		if(addressList != null && addressList.size()>0){
			model.addAttribute("addressList", addressList);
		}
		//获取省份信息
		TbProductType tbProductType = new TbProductType();
		tbProductType.setParentId(0);
		model.addAttribute("productTypes",  productTypeService.selectByTbProductType(tbProductType));
		model.addAttribute("provinceInfos", tbProvinceInfoService.selectByObject(null));
		model.addAttribute("cartIds", String.valueOf(cartIds));
		
		//优惠券余额
		model.addAttribute("couponRemainingAmt", tbShopInfoService.selectByPrimaryKey(getShopId()).getCouponRemainingAmt());
		model.addAttribute("couponRule", getCouponRule());
		
		//更新session购物车的数量
		session.setAttribute(HglContants.SESSION_CART, cartNumber);
    	return "shoppingCar/settlement";
    }
    /**
     * 判断是否可以进行结算
     * @param cartIdsList
     * @return
     * @author zss
     */
    @RequestMapping("/isSettlement")
    public @ResponseBody Map<String,Object> isSettlement(String cartIdsList[]){
    	Map<String,Object> map = tbShoppingCartService.isSettlement(cartIdsList,getLoginUserId());
    	return map;
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
    	ImageUtil.showImageProduct(id,imgName,response);
        return null;
    }
    
    /**
     * 重新计算购物车数量
     * @param cartNumber
     */
    public void reCalculateCartNumber(String cartNumber){
		if(StringUtils.isNotBlank(cartNumber)){  //重新计算购物车数量
			String sessionCart = (String)session.getAttribute(HglContants.SESSION_CART);
			if(StringUtils.isNotBlank(sessionCart)){
				try {
					int cart = Integer.parseInt(sessionCart)-Integer.parseInt(cartNumber);
					session.setAttribute(HglContants.SESSION_CART, String.valueOf(cart));//更新session购物车的数量
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
    }
    
    @RequestMapping(value = "/doSettlementMerchant")
 	public String doSettlementMerchant(Integer bid,String cartNumber,Model model) throws Exception {
     	List<ShoppingCartDto> cartList = tbShoppingCartService.doSettlementMerchant(bid,this.getLoginUserId(),this.getUserSale());
 		if(cartList != null && cartList.size() > 0){        //传递到前台显示
 			model.addAttribute("goodsOrderList", cartList);
 		}
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
 		session.setAttribute(HglContants.SESSION_CART, cartNumber);//更新session购物车的数量
     	return "shoppingCar/settlement";
     }
    
}
