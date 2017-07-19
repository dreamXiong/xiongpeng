/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.exceptions;

/**
 * 支付结算平台的异常基础类型。
 * 支持异常应答包输出，支持异常日志输出，支持异常回滚调用<br>
 * 
 * @FileName XPayServiceException.java。<br>
 * @Language java。<br>
 * @date 2014-11-11。<br>
 * @author 王丹
 */
public class WapServiceException extends WapException {
	/** 错误码 */
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

//	public XPayException(String errCode) {
//		super();
//		errorCode = errCode;
//	}
	public WapServiceException() {
		super();
	}

	public WapServiceException(String msg) {
		super(msg);
	}

	public WapServiceException(String msg, Throwable t) {
		super(msg, t);
	}

	public WapServiceException(String errorCode, String msg) {
		this(msg);
		this.errorCode = errorCode;
	}

	public WapServiceException(String errorMessage, String msg, Throwable t) {
		this(msg, t);
		this.errorCode = errorCode;
	}
}
