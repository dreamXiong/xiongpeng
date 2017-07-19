/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.exceptions;


/**
 * 支付结算平台的商户没有和交易市场的客户建立绑定关系时，抛出该异常。<br>
 * 
 * @FileName XPayNotBindException.java。<br>
 * @Language java。<br>
 * @date 2014-07-31。<br>
 * @author 陈远长
 */
public class WapNotBindException extends WapException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数。
	 * @param messageCode 异常消息码
	 */
	public WapNotBindException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public WapNotBindException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}
}
