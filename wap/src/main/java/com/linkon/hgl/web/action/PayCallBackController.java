package com.linkon.hgl.web.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liguo.hgl.ceche.SpringContextUtils;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayContextParam;
import com.liguo.hgl.service.PayClallBackService;
import com.liguo.hgl.service.TbAccountService;
import com.liguo.hgl.service.TbCashAccountService;
import com.liguo.hgl.service.TbIntegralMallRecordService;
import com.liguo.hgl.service.TbPayContextParamService;
import com.liguo.hgl.service.TbPayWaterService;
import com.liguo.hgl.service.TbShopInfoService;
import com.liguo.hgl.service.TbUserInfoService;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.util.Des3;

/**
 	支付成功后回调
 */
@Controller
@RequestMapping("payCallBack")
public class PayCallBackController{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    protected TbUserInfoService tbUserInfoService;

    @Autowired
	protected TbWapOrderGroupService tbWapOrderGroupService;
    
    @Autowired
   	private TbAccountService tbAccountService;
    
    @Autowired
    private TbPayContextParamService tbPayContextParamService;
    
    @Autowired
    private TbPayWaterService tbPayWaterService;
    
    @Autowired
    private TbCashAccountService tbCashAccountService;
    
    @Autowired
    protected TbShopInfoService tbShopInfoService;
    
    @Autowired
	protected TbIntegralMallRecordService tbIntegralMallRecordService;
    
    /**
     * 微信回调
     * */
    @RequestMapping("/weixinPay")
	public String indexPage(String orderGroupNo,String payStatusCode) throws  Exception{
    	//微信回调成功
    	String orderGroup = Des3.decode(orderGroupNo);
    	String returnPage = "";
    	logger.debug("orderGroupNo.............."+orderGroup+"payStatusCode:"+payStatusCode);
    	if("0".equals(payStatusCode)){
    		Criteria example = new Criteria();
    		example.put("orderGroubNo",orderGroup);
    		List<TbPayContextParam> pList = tbPayContextParamService.selectByObject(example);
    		if(pList.size() != 1){
    			logger.debug("维护支付订单表数据异常！");
            	throw new TransactionException(MessageEnum.Z2006);
    		}
    		TbPayContextParam p = pList.get(0);
    		String payment = p.getPayment();
    		logger.debug(" >>>>>>>>Payment>>>>>>>>>>>>>" + payment);
   	       	PayClallBackService payClallBackService = (PayClallBackService)SpringContextUtils.getBeanById(payment);
   	       if(payClallBackService != null){
   	    	   returnPage = payClallBackService.doSomething(orderGroup,p.getAccountId());
   	       }else{
   	    	   logger.debug(payClallBackService+" >>>>>>>>>>>>>>>>>>>>>回调失败");
   	       }
   	       return "redirect:"+returnPage;
    	
    	}else{
    		logger.debug("支付回调失败！！");
    	}
    	return null;
	}
}
