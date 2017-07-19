/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service;

import com.liguo.hgl.exceptions.WapServiceException;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName IHttpSendService.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
public interface IHttpSendService {

    String post(String url, List<NameValuePair> params) throws WapServiceException;

    String postByJson(String url, String paramsJson) throws WapServiceException;

    String get(String url, List<NameValuePair> params) throws WapServiceException;
}
