/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.common.message.RespTokenMessage;
import com.liguo.hgl.exceptions.WapServiceException;

import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName QueryTokenExecuteService.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
@Service("queryTokenExecuteService")
public class QueryTokenExecuteService extends WeChatGetExecuteService {

    @Override
    protected String queryRequestUrl() {
        return HglContants.WE_CHAT_TOKEN;
    }

    @Override
    protected RespBaseMessage convertRespMessage(String respHtml) throws WapServiceException {
        return super.convertRespJson(respHtml, RespTokenMessage.class);
    }
}
