/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * 一个用于标识报文类型属于哪个交易码的注解。<br>
 * 
 * @FileName TradeCode.java。<br>
 * @Language java。
 * @date 2014-10-14。
 * @author 陈远长
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TradeCode {
	/** 交易码 */
    TranCode tradeCode() default TranCode.XPAY1001;
}
