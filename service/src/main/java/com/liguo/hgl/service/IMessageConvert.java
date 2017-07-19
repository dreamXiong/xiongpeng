/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName IHttpSendService.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
public interface IMessageConvert {

    String getErrorMessage();

    String convertToXml(Object obj);

    <T> T convertFromXml(Class<T> clazz, String message);

}
