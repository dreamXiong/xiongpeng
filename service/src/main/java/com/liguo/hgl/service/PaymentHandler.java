package com.liguo.hgl.service;

import java.util.Map;

import com.liguo.hgl.proxydao.model.TbPayWater;

public interface PaymentHandler   {

    /**统一支付入口
     * @return **/
    Map<String, Object> goToPayment(Map<String,Object> param,String payType)throws RuntimeException;
    
    /***统一回调入口***/
    void result_payment(TbPayWater  tbPayWater)throws RuntimeException;
    
}

