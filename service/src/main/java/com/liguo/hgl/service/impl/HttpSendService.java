/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.liguo.hgl.base.AbstractService;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.service.IHttpSendService;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName HttpSendService.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
@Service
public class HttpSendService extends AbstractService implements IHttpSendService {

    @Override
    public String post(String url, List<NameValuePair> params) throws WapServiceException {

        HttpPost httpPost = new HttpPost(url);
        logger.info("请求地址：" + url);
        logger.info("参数列表：" + params);
        if (CollectionUtils.isNotEmpty(params)) {
            for (NameValuePair nameValuePair : params) {
                logger.info("参数名=" + nameValuePair.getName() + ",参数值=" + nameValuePair.getValue());
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, HglContants.CHARSET_UTF));
            return execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("", e);
            throw new WapServiceException("发送请求异常：", e);
        }
    }

    @Override
    public String postByJson(String url, String paramsJson) throws WapServiceException {

        HttpPost httpPost = new HttpPost(url);
        logger.info("请求地址：" + url);
        logger.info("参数列表：" + paramsJson);
        try {
            StringEntity entity = new StringEntity(paramsJson, HglContants.CHARSET_UTF);
            entity.setContentType("application/json");
            entity.setContentEncoding(HglContants.CHARSET_UTF);
            httpPost.setEntity(entity);
            return execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("", e);
            throw new WapServiceException("发送请求异常：", e);
        }

    }

    @Override
    public String get(String url, List<NameValuePair> params) throws WapServiceException {

        try {
            String paramStr = "";
            if (CollectionUtils.isNotEmpty(params)) {
                paramStr = EntityUtils.toString(new UrlEncodedFormEntity(params, HglContants.CHARSET_UTF));
            }
            if (url.indexOf("?") > 0) {
                url += "&" + paramStr;
            } else {
                url += "?" + paramStr;
            }
            HttpGet httpGet = new HttpGet(url);
            logger.info("请求地址：" + url);
            return execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
            throw new WapServiceException("发送请求异常：", e);
        }
    }

    private String execute(HttpUriRequest request) throws WapServiceException {

        String respStr = "";
        HttpEntity entity = null;
        HttpClient httpClient = new DefaultHttpClient();
        setParameter(httpClient);
        try {
            HttpResponse response = httpClient.execute(request);
            entity = response.getEntity();
            respStr = EntityUtils.toString(entity, HglContants.CHARSET_UTF);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
            throw new WapServiceException("发送请求异常：", e);
        } finally {
            if (null != entity) {
                try {
                    EntityUtils.consume(entity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            httpClient.getConnectionManager().shutdown();
        }
        logger.info("响应消息：" + respStr);
        return respStr;
    }

    private void setParameter(HttpClient httpClient) {

        httpClient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, HglContants.CHARSET_UTF);
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20 * 1000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60 * 1000);
    }

    public static boolean getImg(String url) {

        URL httpurl = null;
        try {
            httpurl = new URL(url);
            URLConnection rulConnection = httpurl.openConnection();
            rulConnection.getInputStream();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] ages) throws WapServiceException {

        boolean retCode = HttpSendService.getImg("http://wx.qlogo.cn/mmopen/aOupcJXDgZguia8cMrhb6qTmMUyASvAjtObV8nKDRy8XUUKGOGA1m1jhr0XG69EZ6c2WQLwfWyJ8Gu7mzFEnhHxKuSBkkqUPf/0");
        System.out.println(retCode);
        if (retCode) {//文件存在
            System.out.println("文件存在");
        } else {
            System.out.println("文件不存在");
        }

    }
}
