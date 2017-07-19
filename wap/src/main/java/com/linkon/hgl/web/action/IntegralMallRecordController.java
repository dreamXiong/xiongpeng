package com.linkon.hgl.web.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dto.IntegralExchangeForm;
import com.liguo.hgl.proxydao.dto.IntegralMallRecordDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbIntegralMallRecord;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbIntegralMallRecordService;
import com.liguo.hgl.util.Des3;

@Controller
@RequestMapping("integralMallRecord")
public class IntegralMallRecordController extends IBaseController {
	
	@Autowired
	protected TbIntegralMallRecordService tbIntegralMallRecordService;
	
	@Autowired
	protected TbAccountService tbAccountService;
	
	/**
	 * 查询列表
	 */
	@Override
	protected void init(PageDto page, HttpServletRequest request,HttpServletResponse response, Model model) {
		Criteria criteria = new Criteria();
 		criteria.put("userId", this.getLoginUserId());
 		criteria.put("payStatus", HglContants.integrall_mall_pay_1106);
		List<IntegralMallRecordDto> integralMallRecordList = tbIntegralMallRecordService.selectByObject(criteria);
		model.addAttribute("integralMallRecordList", integralMallRecordList);
	}
	
	 /**
	  * 我要支付(我要兑换)
	  * @param integralExchangeForm
	  * @param model
	  * @return
	  * @throws Exception
	  */
     @RequestMapping("/wantPay")
     public String wantPay(IntegralExchangeForm integralExchangeForm,RedirectAttributes attr,Model model) throws Exception{
    	 Map<String,Object> param = tbIntegralMallRecordService.wantPay(integralExchangeForm,this.getLoginUserId());
    	 if(param != null && param.size()>0){
			 Integer integralMallRecordId = (Integer)param.get("integralMallRecordId");
			 attr.addAttribute("orderId", Des3.encode(String.valueOf(integralMallRecordId)));  
			 return "redirect:/integralMallRecord/selectByOrderId";
    	 }else{
    		return "redirect:/integralMallRecord/redirectOrderFail";
    	 }
     }
     
 	/**
 	 * 根据积分商城订单id查询
 	 * @param orderId 积分商城订单id
 	 * @param model
 	 * @return
 	 * @throws Exception 
 	 */
 	@RequestMapping(value = "/selectByOrderId")
 	public String selectByOrderId(String orderId,Model model) throws Exception{
 		//根据订单id查询出订单返回到页面
 		TbIntegralMallRecord integralMallRecord = tbIntegralMallRecordService.selectByPrimaryKey(Integer.parseInt(Des3.decode(orderId)));
 		model.addAttribute("integralMallRecord", integralMallRecord);
 		
 		//查询出用户的账户余额
 		TbAccount tbAccount = tbAccountService.selectByPrimaryKey(getAccountId());
 		model.addAttribute("money", tbAccount.getBalance()-tbAccount.getFreeze());
 		return "integralMallRecord/goPay";
 	}
 	
	/**
	 * 重定向到订单提交失败页面
	 * @return
	 */
	@RequestMapping(value = "/redirectOrderFail")
	public String redirectOrderFail(){
		return "integralMallRecord/orderFail";
	}
	
	/**
	 * 积分规则跳转
	 * @return
	 */
	@RequestMapping(value = "/integralRules")
	public String integralRules(){
		return "integralMall/integralRules";
	}
	
	/**
	 * 账户余额支付操作
	 * */
	@RequestMapping(value = "/payIntegralMallRecord")
	public @ResponseBody String payIntegralMallRecord(Integer orderGroupId,Model model) throws Exception{
		tbIntegralMallRecordService.payMyOrderGroup(orderGroupId, getAccountId());
		return null;
	}
    
	@Override 
	public String doSearchResult() {
		return null;
	}
	
}