/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.exceptions;

/**
 * 签约相关的异常基础类型
 * @author 陈远长
 *
 */
public class SigningException extends WapException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SigningException() {
		super();
	}
	

	public SigningException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public SigningException(String errorMessage, String msg) {
		super(errorMessage, msg);
	}
	
	public SigningException(String errorMessage, String msg, Throwable t) {
		super(errorMessage, msg, t);
	}

}
