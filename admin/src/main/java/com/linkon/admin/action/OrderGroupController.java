package com.linkon.admin.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.model.MyOrderStatesCount;
import com.liguo.hgl.proxydao.model.OrderGroupDto;
import com.liguo.hgl.proxydao.model.TbOrderGroup;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbOrderDetailService;
import com.liguo.hgl.service.TbOrderGroupService;
import com.liguo.hgl.service.TbProductService;

/**
 * 订单列表
 * @author 周琨
 * @FileName BrandController.java<br>
 * @Language Java<br>
 * @date 2016-4-25<br>
 */
@Controller
@RequestMapping("orderGroup")
public class OrderGroupController extends IBaseController {

	@Autowired
	protected TbProductService tbProductService;
	
	@Autowired
	protected TbOrderGroupService tbOrderGroupService;
	
	@Autowired
	private TbOrderDetailService orderDetailService;
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		
		MyOrderForm myOrderForm = new MyOrderForm();
		myOrderForm.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.MONTH, -1);
	     myOrderForm.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(c.getTimeInMillis())));
	    //初使化统计数据
		MyOrderStatesCount orderStatesCount = tbOrderGroupService.selectMyOrderStatesCountAdmin(getShopId());
	    //初始化列表信息
		List<OrderGroupDto> dtoList =  tbOrderGroupService.selectOrderListByAdmin(myOrderForm,page,getShopId());
		model.addAttribute("orderStatesCount", orderStatesCount);
		model.addAttribute("dtoList", dtoList);
		model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}
	
	 @RequestMapping(value = "/serachOrderGroup")
	public String searchResult(MyOrderForm myOrderForm ,Model model,PageDto page) {
		 initOrderGroup(myOrderForm,model,page);
		 return "orderGroup/orderGroupList";
	}

	/**
	 * 确认订单操作
	 * */
	 @RequestMapping(value = "/configOrderGroup")
	public String configOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.configOrderGroup(myOrderForm,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page);
		 return "orderGroup/orderGroupList";
	}
	 
	 /**
	 * 订单取消操作
	 * */
	 @RequestMapping(value = "/cancleMyOrderGroupAdmin")
	public String cancleMyOrderGroupAdmin(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.cancleMyOrderGroupByAdmin(myOrderForm,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page);
		return "orderGroup/orderGroupList";
	}
	 
	 /**
	 * admin申请终止订单操作
	 * */
	 @RequestMapping(value = "/stopOrderGroupAdmin")
	public String stopOrderGroupAdmin(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.stopOrderGroupByAdmin(myOrderForm,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page);
		 return "orderGroup/orderGroupList";
	}
	 
	 /**
	 * 线下 ：确认收到货款
	 * */
	 @RequestMapping(value = "/configReceivePayment")
	public String configReceivePayment(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.configReceivePayment(myOrderForm,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page);
		return "orderGroup/orderGroupList";
	}
	 /**
		 * admin确认（买家申请）终止订单操作
	 * */
	 @RequestMapping(value = "/configStopOrderGroupAdmin")
	public String configStopOrderGroupAdmin(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.configStopOrderGroupAdmin(myOrderForm,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page);
		return "orderGroup/orderGroupList";
	} 
 
	 /**
	  * admin不同意订单终止
	  * */
	 @RequestMapping(value = "/cancleStopOrderGroupAdmin")
	 public String cancleStopOrderGroupAdmin(MyOrderForm myOrderForm ,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.cancleStopOrderGroupAdmin(myOrderForm,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page);
		return "orderGroup/orderGroupList";
	}
	 
	@Override
	public String doSearchResult() {
			// TODO Auto-generated method stub
			return null;
		}
	
	@RequestMapping(value = "/selectMyOrderStatesCount")
	 @ResponseBody
	 public Map<String,Object> selectMyOrderStatesCount(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		MyOrderStatesCount orderStatesCount = tbOrderGroupService.selectMyOrderStatesCountAdmin(this.getShopId());
		map.put("tbProductList",orderStatesCount);
		return map;
	}
	
	@RequestMapping(value = "/sendOutGoods")
	public String sendOutGoods(MyOrderForm myOrderForm,Model model,PageDto page){
		 Integer userId = this.getLoginUserId();
		 tbOrderGroupService.sendOutGoods(myOrderForm,userId);
		 //初使化统计数据
		 initOrderGroup(myOrderForm,model,page);
		return "orderGroup/orderGroupList";
	}
	
	private void initOrderGroup(MyOrderForm myOrderForm,Model model,PageDto page){
		MyOrderStatesCount orderStatesCount = tbOrderGroupService.selectMyOrderStatesCountAdmin(getShopId());
		 List<OrderGroupDto> dtoList = tbOrderGroupService.selectOrderListByAdmin(myOrderForm,page,getShopId());
		 model.addAttribute("dtoList", dtoList);
		 model.addAttribute("orderStatesCount", orderStatesCount);
		 model.addAttribute(HglContants.PAGE_DTO_ID, page);
	}
	
	
	/*初始化订单改价*/
	@RequestMapping("/doInitOrderGroup")
	@ResponseBody
	public String doInitOrderGroup(Integer id){
		String strJson = "";
		TbOrderGroup orderGroup = tbOrderGroupService.selectByPrimaryKey(id);
		ObjectMapper mapper = new ObjectMapper();
		try {
			strJson = mapper.writeValueAsString(orderGroup);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return strJson;
	}
	
	/*修改订单总价格*/
	@RequestMapping("/doUpdatePayMoney")
	@ResponseBody
	public String doUpdatePayMoney(Integer id,Double payMoney){
		TbOrderGroup orderGroup = tbOrderGroupService.selectByPrimaryKey(id);
		if(orderGroup!=null){
			orderGroup.setPayMoney(payMoney);
			orderGroup.setTotalMoney(payMoney);
			if(tbOrderGroupService.updateByPrimaryKeySelective(orderGroup)==1){
				return String.valueOf(true);
			}else{
				return String.valueOf(false);
			}
		}
		
		return String.valueOf(false);
	}
}	
