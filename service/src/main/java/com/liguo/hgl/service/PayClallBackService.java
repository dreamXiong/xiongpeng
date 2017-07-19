package com.liguo.hgl.service;

import com.liguo.hgl.exceptions.TransactionException;

public interface PayClallBackService {

    /**
     * 
     * 参数一：订单号<br>
     * 参数二：账号<br>
     * 支付回调逻辑<br>
     * 
     **/
    String doSomething(String... args)throws TransactionException;
}

