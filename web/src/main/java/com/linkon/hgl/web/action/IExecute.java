/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.linkon.hgl.web.action;

import org.aspectj.bridge.IMessage;

import com.liguo.hgl.exceptions.WapException;

/**
 * 一个用于执行支付结算业务的入口接口。所有具体的支付结算业务，都应该实现该接口，提供自己的业务逻辑。<br>
 * 
 * @FileName IExecute.java。<br>
 * @Language java。
 * @date 2014-07-30。
 * @author 陈远长
 */
public interface IExecute {
    //	
    //	/**
    //	 * 设置执行业务操作的请求报文数据。
    //	 * @param message 请求报文数据
    //	 */
    //	void setMessage(IMessage message);

    /**
     * 执行业务的方法。
     * @param message 请求的报文数据实体对象
     * @return 返回的响应数据报实体对象
     * @exception WapException 支付结算平台业务过程中，抛出的异常
     */
    IMessage execute(IMessage message) throws WapException;
}
