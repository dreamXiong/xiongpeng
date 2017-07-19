/*
 * Copyright (c)2015-5-25 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识字段长度的注解
 * 用于报文传输中消息体字段长度的验证
 * @FileName FieldLength.java
 * @Language java
 * @date 2015-5-25 下午2:33:36
 * @author HuangYuAn
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataField {
    
    /** 字段长度 */
    int length() default -1;
    /** 最大值 */
    int max() default -1;
    /** 最小值 */
    int min() default -1;
    /** 只能在该数组中取值 */
    String[] limit() default {};
    /** 允许为空 */
    boolean nullable() default true;
    
}
