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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dto.CalulateFreightDto;
import com.liguo.hgl.proxydao.dto.OrderGroupDetailDto;
import com.liguo.hgl.proxydao.dto.ShopIndexForWapDto;
import com.liguo.hgl.proxydao.dto.SubmitOrderForm;
import com.liguo.hgl.proxydao.dto.WapOrderTrackingDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbWapOrderGroup;
import com.liguo.hgl.proxydao.model.WapOrderGroupDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.service.TbWapOrderTrackingService;
import com.liguo.hgl.util.Des3;


@Controller
@RequestMapping("wapOrderGroup")
public class WapOrderGroupController extends IBaseController {

	@Autowired
	protected TbWapOrderGroupService tbWapOrderGroupService;
	
	@Autowired
	protected TbShopInfoService tbShopInfoService;

	@Autowired
	protected TbWapOrderTrackingService tbWapOrderTrackingService;
	
	@Autowired
	protected TbAccountService tbAccountService;
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		
	}

	@Override
	public String doSearchResult() {
		
		return null;
	}
	/**
	 * 查询订单列表
	 * */
	@RequestMapping(value = "/selectOrderGroup")
	public String selectOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		String lon = (String)session.getAttribute(HglContants.LON);
		/*String lat = (String)session.getAttribute(HglContants.LAT);*/
		String city = (String)session.getAttribute(HglContants.ADDRESS_CITY);
		/**
		 * 当用户没有登陆时进入首页时，将用户当前的坐标传入session中,如果出现session失效的情况就重定向到首页 
		 * */
		//.判断当前用户有没有得坐标
		if(StringUtils.isBlank(lon) || StringUtils.isBlank(city)){
			//登陆角色不是师傅
			if(getLoginUser() == null || getLoginUser().getTypeId() != HglContants.USER_TYPE_MASTER){
				return "redirect:/customerIndex";
			}else{
				return "redirect:/";
			}
		}
		 initialization(myOrderForm);
		 myOrderForm.setUserId(getLoginUserId());
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		 System.out.println(dtoList.toString());
		return "myOrderGroup/myOrderGroup";
	}
	
	/**
	 * 去支付操作
	 * */
	@RequestMapping(value = "/payMyOrderGroup")
	public String payMyOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page) throws TransactionException{
		 tbWapOrderGroupService.payMyOrderGroup(myOrderForm.getOrderGroupId(), getAccountId());
		 initialization(myOrderForm);
		 Integer userId = this.getLoginUserId();
		 myOrderForm.setUserId(userId);
		 tbWapOrderGroupService.configReceivePayment(myOrderForm,userId,"买家已经付款");
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		return "myOrderGroup/myOrderGroupList";
	}
	
	/**
	 * 支付回调
	 * */
	@RequestMapping(value = "/payMyOrderGroupCallBack")
	public String payMyOrderGroupCallBack(MyOrderForm myOrderForm ,Model model,PageDto page) throws TransactionException{
		
		//执行支付逻辑
		
		
		 tbWapOrderGroupService.payMyOrderGroup(myOrderForm.getOrderGroupId(), getAccountId());
		 
		 initialization(myOrderForm);
		 Integer userId = this.getLoginUserId();
		 myOrderForm.setUserId(getLoginUserId());
		 tbWapOrderGroupService.configReceivePayment(myOrderForm,userId,"买家已经付款");
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		return "myOrderGroup/myOrderGroupList";
	}
	/**
	 * 订单取消操作
	 * */
	 @RequestMapping(value = "/cancleMyOrderGroup")
	public String cancleMyOrderGroupByBuyer(MyOrderForm myOrderForm ,Model model,PageDto page){
		 initialization(myOrderForm);
		 Integer userId = this.getLoginUserId();
		 myOrderForm.setUserId(getLoginUserId());
		 tbWapOrderGroupService.cancleMyOrderGroupByBuyer(myOrderForm,userId);
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		return "myOrderGroup/myOrderGroupList";
	}
	 /**
	 * 订单申请终止操作
	 * */
	 @RequestMapping(value = "/stopMyOrderGroup")
	public String stopMyOrderGroupByBuyer(MyOrderForm myOrderForm ,Model model,PageDto page){
		 initialization(myOrderForm);
		 Integer userId = this.getLoginUserId();
		 myOrderForm.setUserId(getLoginUserId());
		 tbWapOrderGroupService.stopMyOrderGroupByBuyer(myOrderForm,userId);
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		return "myOrderGroup/myOrderGroupList";
	}
	 
	 /**
	 * 确认终止操作
	 * */
	 @RequestMapping(value = "/configStopOrderGroup")
	public String configStopOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		 initialization(myOrderForm);
		 Integer userId = this.getLoginUserId();
		 myOrderForm.setUserId(getLoginUserId());
		 tbWapOrderGroupService.configStopOrderGroupByBuyer(myOrderForm,userId,"买家同意终止订单");
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		return "myOrderGroup/myOrderGroupList";
	}
	 
	 /**
	 * 确认收货
	 * */
	 @RequestMapping(value = "/configGoodsReceipt")
	public String configGoodsReceipt(MyOrderForm myOrderForm ,Model model,PageDto page){
		 initialization(myOrderForm);
		 Integer userId = this.getLoginUserId();
		 myOrderForm.setUserId(userId);
		 tbWapOrderGroupService.configGoodsReceipt(myOrderForm,userId);
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		return "myOrderGroup/myOrderGroupList";
	}
	 /**
	 * 取消订单申请终止操作
	 * */
	 @RequestMapping(value = "/cancleStopOrderGroup")
	public String cancleStopOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		 initialization(myOrderForm);
		 Integer userId = this.getLoginUserId();
		 myOrderForm.setUserId(getLoginUserId());
		 tbWapOrderGroupService.cancleStopOrderGroupByBuyer(myOrderForm,userId);
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		return "myOrderGroup/myOrderGroupList";
	}
	 
	 /**
	 * 订单删除操作
	 * */
	 @RequestMapping(value = "/deleteOrderGroup")
	public String deleteOrderGroup(MyOrderForm myOrderForm ,Model model,PageDto page){
		 myOrderForm.setUserId(getLoginUserId());
		 initialization(myOrderForm);
		 TbWapOrderGroup record = tbWapOrderGroupService.selectByPrimaryKey(myOrderForm.getOrderGroupId());
		 record.setVersion(myOrderForm.getVersion());
		 //设置删除标签
		 record.setDeleteFlag(1);
		 record.setDeleteDt(System.currentTimeMillis());
		 tbWapOrderGroupService.updateByPrimaryKey(record);
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		return "myOrderGroup/myOrderGroupList";
	}
	 
	 /**
	  * 提交订单
	  * @param submitOrderForm 提交订单用户选择的参数
	  * @return
	  */
	@RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
	public String submitOrder(SubmitOrderForm submitOrderForm,RedirectAttributes attr,Model model){
		try {
			//获取店铺的运费的session
			Object obj = this.session.getAttribute(HglContants.SESSION_FREIGHT);
			Map<Integer,CalulateFreightDto> calulateFreightMap = new HashMap<Integer,CalulateFreightDto>();
			if(obj != null){
				calulateFreightMap = (Map<Integer,CalulateFreightDto>)obj;
			}
			submitOrderForm.setCalulateFreightMap(calulateFreightMap);
			submitOrderForm.setRecommendShopid(this.getRecommendShopId());
			
			//提交订单	
			logger.debug("submitWapOrder: account..................."+this.getAccountId());
			int wapOrderGroupCount = tbWapOrderGroupService.submitOrder(submitOrderForm,this.getLoginUserId(),this.getAccountId());
			if(wapOrderGroupCount > 0){
				//销毁店铺运费的session
				this.session.removeAttribute(HglContants.SESSION_FREIGHT);
				attr.addAttribute("orderId", Des3.encode(String.valueOf(wapOrderGroupCount)));  
				return "redirect:/wapOrderGroup/selectByOrderId";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/wapOrderGroup/redirectOrderFail";
	}
	
	/**
	 * 根据订单id查询
	 * @param orderId 订单id
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectByOrderId")
	public String selectByOrderId(String orderId,Model model) throws Exception{
		//根据订单id查询出订单返回到页面
		TbWapOrderGroup wapOrderGroup = tbWapOrderGroupService.selectByPrimaryKey(Integer.parseInt(Des3.decode(orderId)));
		model.addAttribute("wapOrderGroup", wapOrderGroup);
		
		//查询出用户的账户余额
		TbAccount tbAccount = tbAccountService.selectByPrimaryKey(getAccountId());
		model.addAttribute("money", tbAccount.getBalance()-tbAccount.getFreeze());
		return "myOrderGroup/goPay";
	}
	
	/**
	 * 重定向到订单提交失败页面
	 * @return
	 */
	@RequestMapping(value = "/redirectOrderFail")
	public String redirectOrderFail(){
		return "myOrderGroup/orderFail";
	}
	
	/**
	 * 根据Tab查询
	 * */
	 @RequestMapping(value = "/selectOrderList")
	public String selectOrderList(MyOrderForm myOrderForm ,Model model,PageDto page){
		 initialization(myOrderForm);
		 myOrderForm.setUserId(getLoginUserId());
		 if(!StringUtils.isBlank(myOrderForm.getOrderState())){
			 myOrderForm.setOrderStateList(Arrays.asList(myOrderForm.getOrderState().split(",")));
			 myOrderForm.setOrderState(null);
		 }
		 List<WapOrderGroupDto> dtoList = tbWapOrderGroupService.selectOrderGroupList(myOrderForm,page);
		 model.addAttribute("dtoList",dtoList);
		return "myOrderGroup/myOrderGroupList";
	}
	 
	 /**
	  * 订单详情显示
	  * @param orderId
	  * @param model
	  * @return
	  */
    @RequestMapping(value = "/orderGroupDetail", method = RequestMethod.POST)
	public String orderGroupDetail(String orderId,Model model,String balance){
		if(StringUtils.isNotBlank(orderId)){
			OrderGroupDetailDto orderGroupDetailDto = tbWapOrderGroupService.selectOrderGroupDetail(Integer.parseInt(orderId));
			if(orderGroupDetailDto != null){
				Criteria parameter = new Criteria();
				List<String> iDList = new ArrayList<String>();
				iDList.add(orderId);
				parameter.put("iDList",iDList);
				parameter.put("lon",Double.parseDouble((String)session.getAttribute(HglContants.LON)));
			    parameter.put("lat", Double.parseDouble((String)session.getAttribute(HglContants.LAT)));
				List<WapOrderGroupDto> dList = tbWapOrderGroupService.selectOrderGroupList(parameter);
				model.addAttribute("dtoList",dList);
				model.addAttribute("orderGroupDetailDto", orderGroupDetailDto);
			}
			Criteria example = new Criteria();
			example.put("orderGroupId", orderId);
			List<WapOrderTrackingDto> tList = tbWapOrderTrackingService.selectByObjectDto(example);
			model.addAttribute("tList",tList);
			model.addAttribute("balance",balance);
		}
		return "myOrderGroup/myOrderGroupDetail";
	}
    
    @RequestMapping(value = "/goEvaluate")
	public String goEvaluate(Integer orderGroupId,String shopId, Model model){
		Criteria c = new Criteria();
		c.put("id",shopId);
		ShopIndexForWapDto t = tbShopInfoService.selectShopIndexInfo(c);
		model.addAttribute("t",t);
		model.addAttribute("orderGroupId",orderGroupId);
		return "myOrderGroup/evaluate";
	}
    @RequestMapping(value = "/saveEvaluate")
    public String saveEvaluate(Integer orderGroupId,String start,String evaluate,Model model){
    	tbWapOrderGroupService.saveEvaluate(orderGroupId,start,evaluate,getLoginUserId());
    	return "redirect:/wapOrderGroup/selectOrderGroup";
    }
    
    private void initialization(MyOrderForm myOrderForm){
    	 double lon = Double.parseDouble((String)session.getAttribute(HglContants.LON));
		 double lat = Double.parseDouble((String)session.getAttribute(HglContants.LAT));
		 myOrderForm.setLon(lon);
		 myOrderForm.setLat(lat);
    }
    
    /*材料订单改价*/
    @RequestMapping("/doUpdatePrice")
    @ResponseBody
    public String doUpdatePrice(Integer id,Double totalMoney){
    	TbWapOrderGroup orderGroup =tbWapOrderGroupService.selectByPrimaryKey(id);
    	if(orderGroup!=null){
    		orderGroup.setTotalMoney(totalMoney);
    		orderGroup.setPayMoney(orderGroup.getPostage()+totalMoney);
    		if(tbWapOrderGroupService.updateByPrimaryKeySelective(orderGroup)==1){
    			return String.valueOf(true);
    		}
    	}
    	
    	return String.valueOf(false);
    }
}
