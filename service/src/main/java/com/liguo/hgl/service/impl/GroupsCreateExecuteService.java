/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.common.message.RespGroupsCreateMessage;
import com.liguo.hgl.exceptions.WapServiceException;

import org.springframework.stereotype.Service;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName GroupsCreateExecuteService.java<br>
 * @Language Java<br>
 * @date 2016-01-11<br>
 */
@Service("groupsCreateExecuteService")
public class GroupsCreateExecuteService extends WeChatPostDataExecuteService {

    @Override
    protected String queryRequestUrl() throws WapServiceException {
        return HglContants.WE_CHAT_GROUPS_CREATE + String.format(HglContants.WE_CHAT_ACCESS_TOKEN_PARAM, tokenService.getToken());
    }

    @Override
    protected RespBaseMessage convertRespMessage(String respHtml) throws WapServiceException {
        return super.convertRespJson(respHtml, RespGroupsCreateMessage.class);
    }
}
