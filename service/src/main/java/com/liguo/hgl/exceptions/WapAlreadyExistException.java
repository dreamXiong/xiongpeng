/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.exceptions;

/**
 * 当需要创建保存的数据已经存在时，抛出该异常。<br>
 * 
 * @FileName MerchantService.java
 * @Language java
 * @date 2014-07-31
 * @author 陈远长
 */
public class WapAlreadyExistException extends WapException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数。
	 * 
	 * @param messageCode
	 *            异常消息码
	 */
	public WapAlreadyExistException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public WapAlreadyExistException(String msg, Throwable t) {
		super(msg, t);
	}
}
