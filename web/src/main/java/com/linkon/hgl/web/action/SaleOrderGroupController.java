package com.linkon.hgl.web.action;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.TbWapOrderDetail;
import com.liguo.hgl.proxydao.model.TbWapOrderGroup;
import com.liguo.hgl.proxydao.model.WapOrderGroupDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbWapOrderDetailService;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.service.TbWapProductService;

/**
 * 订单列表
 * @author 周琨
 * @FileName BrandController.java<br>
 * @Language Java<br>
 * @date 2016-4-25<br>
 */
@Controller
@RequestMapping("/saleOrderGroup")
public class SaleOrderGroupController extends IBaseController {

	@Autowired
	protected TbWapProductService tbWapProductService;
	
	@Autowired
	protected TbWapOrderGroupService tbWapOrderGroupService;
	
	@Autowired
	protected TbWapOrderDetailService tbWapOrderDetailService;
		
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		MyOrderForm myOrderForm = new MyOrderForm();
		myOrderForm.setEndTime(System.currentTimeMillis()+"");
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, -1);
	    myOrderForm.setStartTime(c.getTimeInMillis()+"");
		
		MyOrderStatesCount orderStatesCount = tbWapOrderGroupService.selectMyOrderStatesCountWap(getShopId());
		myOrderForm.setShopId(getShopId());
		myOrderForm.setSaleOrderFlag("0");
		List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		model.addAttribute("orderStatesCount", orderStatesCount);
		model.addAttribute("dtoList", dtoList);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}
	
	@RequestMapping(value = "/serachSaleOrderGroup")
	public String searchResult(MyOrderForm myOrderForm ,Model model,PageDto page) {
		 myOrderForm.setShopId(getShopId());
		 initSaleOrderGroup( myOrderForm, model, page);
	 return "saleOrderGroup/saleOrderGroupList";
	}
	 
	@Override
	public String doSearchResult() {
		// TODO Auto-generated method stub
		return null;
	}

	 @RequestMapping(value = "/selectSaleOrderStatesCount")
	 @ResponseBody
	 public Map<String,Object> selectMyOrderStatesCount(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		MyOrderStatesCount orderStatesCount = tbWapOrderGroupService.selectMyOrderStatesCountWap(this.getShopId());
		map.put("tbProductList",orderStatesCount);
		return map;
	}
	 private void initSaleOrderGroup(MyOrderForm myOrderForm,Model model,PageDto page){
		myOrderForm.setShopId(getShopId());
		myOrderForm.setSaleOrderFlag("0");
		MyOrderStatesCount orderStatesCount = tbWapOrderGroupService.selectMyOrderStatesCountWap(getShopId());
		List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderList(myOrderForm,page);
		model.addAttribute("dtoList", dtoList);
		model.addAttribute("orderStatesCount", orderStatesCount);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}

	/**
	 * 确认订单操作
	 * */
	@RequestMapping(value = "/configOrderGroup")
	public String configOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		 myOrderForm.setShopId(getShopId());
		 myOrderForm.setSaleOrderFlag("0");
		 tbWapOrderGroupService.configOrderGroup(myOrderForm,this.getLoginUserId());
		 //初使化统计数据
		 initSaleOrderGroup(myOrderForm,model,page);
		 return "saleOrderGroup/saleOrderGroupList";
	}
	
	 /**
	 * 订单取消操作
	 * */
	 @RequestMapping(value = "/cancleMyOrderGroupShop")
	public String cancleMyOrderGroupShop(MyOrderForm myOrderForm ,Model model,PageDto page){
		 myOrderForm.setShopId(getShopId());
		 myOrderForm.setSaleOrderFlag("0");
		 tbWapOrderGroupService.cancleMyOrderGroupByShop(myOrderForm,this.getLoginUserId());
		//初使化统计数据
		 initSaleOrderGroup(myOrderForm,model,page);
		return "saleOrderGroup/saleOrderGroupList";
	}
	 
	 /**
	 * 线下 ：确认收到货款
	 * */
	@RequestMapping(value = "/configReceivePayment")
	public String configReceivePayment(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbWapOrderGroupService.configReceivePayment(myOrderForm,userId,"卖家确认收到货款");
		//初使化统计数据
		 initSaleOrderGroup(myOrderForm,model,page);
		return "saleOrderGroup/saleOrderGroupList";
	}
	 /**
	  * 店铺发货
	  * */
	@RequestMapping(value = "/sendOutGoodsByShop")
	public String sendOutGoodsByShop(MyOrderForm myOrderForm,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbWapOrderGroupService.sendOutGoods(myOrderForm,userId);
		//初使化统计数据
		 initSaleOrderGroup(myOrderForm,model,page);
		 return "saleOrderGroup/saleOrderGroupList";
	} 
	
	 /**
	  * 店铺发起终止订单
	  * */
	@RequestMapping(value = "/stopMyOrderGroup")
	public String stopMyOrderGroup(MyOrderForm myOrderForm,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbWapOrderGroupService.stopMyOrderGroupByShop(myOrderForm,userId);
		//初使化统计数据
		 initSaleOrderGroup(myOrderForm,model,page);
		 return "saleOrderGroup/saleOrderGroupList";
	} 
	
	/**
	  * 店铺取消终止订单
	  * */
	@RequestMapping(value = "/cancleStopOrderGroup")
	public String cancleStopOrderGroup(MyOrderForm myOrderForm,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbWapOrderGroupService.cancleStopOrderGroupByShop(myOrderForm,userId);
		//初使化统计数据
		 initSaleOrderGroup(myOrderForm,model,page);
		 return "saleOrderGroup/saleOrderGroupList";
	} 
	/**
	  * 店铺同意终止订单
	  * */
	@RequestMapping(value = "/configStopOrderGroup")
	public String configStopOrderGroup(MyOrderForm myOrderForm,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbWapOrderGroupService.configStopOrderGroupByShop(myOrderForm,userId);
		//初使化统计数据
		 initSaleOrderGroup(myOrderForm,model,page);
		 return "saleOrderGroup/saleOrderGroupList";
	} 
	
	@RequestMapping(value = "/stopOrder")
	protected String stopOrder(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		MyOrderForm myOrderForm = new MyOrderForm();
		myOrderForm.setEndTime(System.currentTimeMillis()+"");
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, -1);
	    myOrderForm.setStartTime(c.getTimeInMillis()+"");
		MyOrderStatesCount orderStatesCount = tbWapOrderGroupService.selectMyOrderStatesCountWap(getShopId());
		myOrderForm.setShopId(getShopId());
		myOrderForm.setOrderState("622,606");
		List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		model.addAttribute("orderStatesCount", orderStatesCount);
		model.addAttribute("dtoList", dtoList);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
		return "saleOrderGroup/stopOrder";
	}
	
	@RequestMapping(value = "/serachStopOrder")
	public String serachStopOrder(MyOrderForm myOrderForm ,Model model,PageDto page) {
		 myOrderForm.setShopId(getShopId());
		 myOrderForm.setOrderState("622,606");
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderList(myOrderForm,page);
		 model.addAttribute("dtoList", dtoList);
		 model.addAttribute(HglContants.PAGE_DTO_ID, page);
		 return "saleOrderGroup/stopOrderList";
	}
	 /**
	 * 订单删除操作
	 * */
	@RequestMapping(value = "/deleteOrderGroup")
	public String deleteOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		 
		 TbWapOrderGroup record = tbWapOrderGroupService.selectByPrimaryKey(myOrderForm.getOrderGroupId());
		 //设置删除标签
		 record.setDeleteFlagSeller(1);
		 record.setDeleteDtSeller(System.currentTimeMillis());
		 tbWapOrderGroupService.updateByPrimaryKey(record);
		 myOrderForm.setShopId(getShopId());
		 myOrderForm.setOrderState("622,606");
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderList(myOrderForm,page);
		 model.addAttribute("dtoList", dtoList);
		 model.addAttribute(HglContants.PAGE_DTO_ID, page);
		 return "saleOrderGroup/stopOrderList";
	}
	
	/*修改订单价格*/
	@RequestMapping(value="/doUpdateTotalMoney")
	@ResponseBody
	public String doUpdateTotalMoney(Integer id,Double totalMoney){
		
		if(tbWapOrderGroupService.doUpdateOrderGroupPrice(id, totalMoney)==1){
			return String.valueOf(true);
		}
		return String.valueOf(false);
	}
	
	/*修改订单单价*/
	@ResponseBody
	@RequestMapping("/doUpdateUnitPrice")
	public String doUpdateUnitPrice(Integer groupId,Integer detailId,Double unitPrice){
		if(tbWapOrderGroupService.doUpdateUnitPrice(groupId, detailId, unitPrice)==true){
			TbWapOrderGroup orderGroup = tbWapOrderGroupService.selectByPrimaryKey(groupId);
			return String.valueOf(orderGroup.getPayMoney());
		}else{
			return String.valueOf(false);
		}
	}
	
}	
