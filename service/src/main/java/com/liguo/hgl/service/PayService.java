package com.liguo.hgl.service;

import java.util.Map;


public interface PayService {
	/**
	 * 服务支付回调接口
	 * @param orderNumber
	 * @param money
	 * @return
	 * @author zss
	 */
	 Map<String,Object> payCallResults(String orderNumber,Double money);
	 /**
	  * 订单支付回调接口
	  * @param orderNumber
	  * @param money
	  * @return
	  * @author zss
	  */
	 Map<String,Object> payCallOderResults(String orderNumber,Double money,String type);
	 
	 /**
	  * 支付成功后回调方法
	  * @param orderNumber
	  * @param money
	  * @return
	  * @author zss
	  */
	 Map<String,Object> payCallSuccessResults(String orderNumber,Double money);
}
