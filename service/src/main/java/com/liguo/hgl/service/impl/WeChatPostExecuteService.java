/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.exceptions.WapServiceException;

import java.util.Map;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName WeChatPostExecuteService.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
public abstract class WeChatPostExecuteService extends AbstractWeChatExecuteService<Map<String, String>> {

    @Override
    public RespBaseMessage execute(Map<String, String> reqMessage) throws WapServiceException {
        String respHtml = httpSendService.get(queryRequestUrl(), buildParams(reqMessage));
        return convertRespMessage(respHtml);
    }

}
