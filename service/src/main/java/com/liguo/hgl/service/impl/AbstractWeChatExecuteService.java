/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import com.liguo.hgl.common.message.ReqBaseMessage;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.common.message.RespTokenMessage;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.service.IHttpSendService;
import com.liguo.hgl.service.ITokenService;
import com.liguo.hgl.service.IWeChatExecuteService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName AbstractWeChatExecuteService.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
public abstract class AbstractWeChatExecuteService<T> implements IWeChatExecuteService<T> {

    @Autowired
    protected IHttpSendService httpSendService;
    @Autowired
    protected ITokenService tokenService;

    protected ObjectMapper mapper = new ObjectMapper();

    protected List<NameValuePair> buildParams(Map<String, String> reqMessage){
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(String key : reqMessage.keySet()){
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(key, reqMessage.get(key));
            pairs.add(basicNameValuePair);
        }
        return pairs;
    }

    protected String buildParamsJson(ReqBaseMessage reqMessage) throws WapServiceException {
        try {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            return mapper.writeValueAsString(reqMessage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new WapServiceException("转换请求报文异常");
        }
    }

    protected RespBaseMessage convertRespJson(String respHtml, Class<? extends RespBaseMessage> clazz) throws WapServiceException{
        try {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            return mapper.readValue(respHtml, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new WapServiceException("转换响应报文异常");
        }
    }

    protected abstract String queryRequestUrl() throws WapServiceException;

    protected abstract RespBaseMessage convertRespMessage(String respHtml) throws WapServiceException;

}
