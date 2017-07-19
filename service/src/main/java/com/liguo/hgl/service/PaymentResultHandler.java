package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.TbPayWater;

public interface PaymentResultHandler   {

    /**统一回调入口**/
    void savePaymentResult(TbPayWater tbPayWater)throws RuntimeException;
}

