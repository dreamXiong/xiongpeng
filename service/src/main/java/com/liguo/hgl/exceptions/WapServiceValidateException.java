/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.exceptions;

/**
 * 结算支付平台数据验证没有通过，抛出该异常。<br>
 * 
 * @FileName XPayServiceValidateException.java。<br>
 * @Language java。<br>
 * @date 2014-07-31。<br>
 * @author 王丹
 */
public class WapServiceValidateException extends WapServiceException {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数。
	 * @param messageCode 异常消息码
	 */
	public WapServiceValidateException(String messageCode) {
		super(messageCode);
	}

	public WapServiceValidateException(String msg, Throwable t) {
		super(msg, t);
	}

    public WapServiceValidateException(String messageCode, String messageValue) {
        super(messageCode, messageValue);
    }
}
