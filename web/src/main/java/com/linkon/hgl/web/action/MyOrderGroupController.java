package com.linkon.hgl.web.action;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.dto.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.dto.WapOrderTrackingDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.model.WapOrderGroupDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbOrderDetailService;
import com.liguo.hgl.service.TbOrderGroupService;
import com.liguo.hgl.service.TbProductService;
import com.liguo.hgl.service.TbShoppingCartService;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.service.TbWapOrderTrackingService;
import com.liguo.hgl.util.ImageUtil;

@Controller
@RequestMapping("myOrderGroup")
public class MyOrderGroupController extends IBaseController {

	
	@Autowired
	protected TbProductService tbProductService;
	
	@Autowired
	protected TbOrderGroupService tbOrderGroupService;
	
    @Autowired
    protected TbShoppingCartService tbShoppingCartService;
    
    @Autowired
	protected TbOrderDetailService tbOrderDetailService;
    
	@Autowired
	protected TbWapOrderGroupService tbWapOrderGroupService;
	
	@Autowired
	protected TbWapOrderTrackingService tbWapOrderTrackingService;
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		Integer shopId = getShopId();
		MyOrderForm myOrderForm = new MyOrderForm();
		myOrderForm.setShopId(shopId);
		myOrderForm.setEndTime(System.currentTimeMillis()+"");
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, -1);
	    myOrderForm.setStartTime(c.getTimeInMillis()+"");
	    //初使化统计数据
	    MyOrderStatesCount orderStatesCount = tbOrderGroupService.selectMyOrderStatesCount(shopId);
	    model.addAttribute("orderStatesCount", orderStatesCount);
	    List<OrderGroupDto> dtoList = tbOrderGroupService.selectOrderGroupList(myOrderForm,page);
		model.addAttribute("dtoList", dtoList);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
	    
	}
	 @RequestMapping(value = "/serachMyOrderGroup")
	public String searchMyOrderGroupPage(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer shopId = this.getShopId();
		 initOrderGroup(myOrderForm,model,page,shopId);
		 return "myOrderGroup/myOrderGroupList";
	}
	 
	 /**
	 * 订单支付操作
	 * */
	@RequestMapping(value = "/payMyOrderGroup")
	public String payMyOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer shopId = this.getShopId();
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.payMyOrderGroup(myOrderForm,userId);
		 initOrderGroup(myOrderForm,model,page,shopId);
		return "myOrderGroup/myOrderGroupList";
	}
 
	/**
	 * 订单取消操作
	 * */
	 @RequestMapping(value = "/cancleMyOrderGroup")
	public String cancleMyOrderGroupByBuyer(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer shopId = this.getShopId();
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.cancleMyOrderGroupByBuyer(myOrderForm,shopId,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page,shopId);
		return "myOrderGroup/myOrderGroupList";
	}
	
	 @RequestMapping(value = "/selectMyOrderStatesCount")
	 @ResponseBody
	 public Map<String,Object> selectMyOrderStatesCount(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		MyOrderStatesCount orderStatesCount = tbOrderGroupService.selectMyOrderStatesCount(this.getShopId());
		map.put("tbProductList",orderStatesCount);
    	return map;
	}
	 
	 /**
	 * 订单申请终止操作
	 * */
	 @RequestMapping(value = "/stopMyOrderGroup")
	public String stopMyOrderGroupByBuyer(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer shopId = this.getShopId();
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.stopMyOrderGroupByBuyer(myOrderForm,shopId,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page,shopId);
		return "myOrderGroup/myOrderGroupList";
	}
	 
	 /**
	 * 确认终止操作
	 * */
	 @RequestMapping(value = "/configStopOrderGroup")
	public String configStopOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer shopId = this.getShopId();
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.configStopOrderGroupByBuyer(myOrderForm,shopId,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page,shopId);
		return "myOrderGroup/myOrderGroupList";
	}
	 
	 /**
	 * 确认收货
	 * */
	 @RequestMapping(value = "/configGoodsReceipt")
	public String configGoodsReceipt(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer shopId = this.getShopId();
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.configGoodsReceipt(myOrderForm, shopId,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page,shopId);
		return "myOrderGroup/myOrderGroupList";
	}
	 /**
	 * 订单申请终止操作
	 * */
	 @RequestMapping(value = "/cancleStopOrderGroup")
	public String cancleStopOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer shopId = this.getShopId();
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.cancleStopOrderGroupByBuyer(myOrderForm, shopId,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page,shopId);
		return "myOrderGroup/myOrderGroupList";
	}
	 /**
	  * 订单申请漏发补发操作
	  * @param myOrderForm
	  * @param model
	  * @param page
	  * @return
	  * @author zss
	  */
	 @RequestMapping(value = "/reissueOrderGroup")
		public String reissueOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
			 Integer shopId = this.getShopId();
			 Integer userId = this.getLoginUserId();
			 tbOrderGroupService.reissueOrderGroup(myOrderForm, shopId,userId);
			 //初使化统计数据
			 initOrderGroup(myOrderForm,model,page,shopId);
			return "myOrderGroup/myOrderGroupList";
		}
	 
	 /**
	  * 提交订单
	  * @param model
	  * @return
	  */
	@RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
	public String submitOrder(String cartIdList[],String addressId,String payType,String couponsMoney,Model model,String cartIds){
		Map<String, Object> resultMap = tbOrderGroupService.submitOrder(cartIds,cartIdList, addressId, payType, couponsMoney, this.getLoginUserId(),this.getShopId(),this.getCouponRule(),this.getUserSale());
		if(resultMap != null && resultMap.size()>0){
			model.addAttribute("totalPayMoney", resultMap.get("totalPayMoney"));
			model.addAttribute("orderGroupList", resultMap.get("orderGroupList"));
			int cartNumber = tbShoppingCartService.getUserCartNumber(this.getLoginUserId()); //获取购物车数量
			session.setAttribute(HglContants.SESSION_CART, String.valueOf(cartNumber));//保存session购物车的数量
		}
		return "myOrderGroup/goPay";
	}
	
	private void initOrderGroup(MyOrderForm myOrderForm,Model model,PageDto page,Integer shopId){
		 MyOrderStatesCount orderStatesCount = tbOrderGroupService.selectMyOrderStatesCount(shopId);
		 List<OrderGroupDto> dtoList = tbOrderGroupService.selectOrderList(myOrderForm,page,shopId);
		 model.addAttribute("dtoList", dtoList);
		 model.addAttribute("orderStatesCount", orderStatesCount);
		 model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}
	 @Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}

	 /**
	  * 订单详情显示
	  * @param orderId
	  * @param model
	  * @return
	  */
    @RequestMapping(value = "/orderGroupDetail",method = RequestMethod.POST)
	public String orderGroupDetail(String orderId,Model model){
		if(StringUtils.isNotBlank(orderId)){
			OrderGroupDetailDto orderGroupDetailDto = tbWapOrderGroupService.selectOrderGroupDetail(Integer.parseInt(orderId));
			if(orderGroupDetailDto != null){
				Criteria parameter = new Criteria();
				parameter.put("orderGroupId", orderId);
		    	List<WapOrderTrackingDto> dtoLists = tbWapOrderTrackingService.selectByObjectStatus(parameter);
				model.addAttribute("orderTrackings", dtoLists);
				
				//订单跟踪
				parameter = new Criteria();
				parameter.put("orderGroupId", orderId);
				List<WapOrderTrackingDto> orderTrackingDtos = tbWapOrderTrackingService.selectByObjectDto(parameter);		
				model.addAttribute("orderTrackingDtos", orderTrackingDtos);
				
				//订单详细列表
				List<String> iDList = new ArrayList<String>();
				iDList.add(orderId);
				parameter = new Criteria();
				parameter.put("iDList",iDList);
				List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(parameter);
			    model.addAttribute("dtoList",dtoList);
				model.addAttribute("orderGroupDetailDto", orderGroupDetailDto);
			}
		}
		return "saleOrderGroup/saleOrderGroupDetail";
	}
    
    @RequestMapping("/generateImage")
    public String generateProductImage(ModelMap model,Integer id,String imgName,HttpServletResponse response) {
    	ImageUtil.showImageWapProduct(id,imgName,response);
        return null;
    }
    
}
