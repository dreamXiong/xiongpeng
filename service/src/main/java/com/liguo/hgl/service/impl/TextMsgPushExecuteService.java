/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.service.impl;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.message.ReqMsgPushMessage;
import com.liguo.hgl.common.message.RespBaseMessage;
import com.liguo.hgl.common.message.RespMsgPushMessage;
import com.liguo.hgl.exceptions.WapServiceException;
import com.liguo.hgl.proxydao.dao.TbTalkMapper;
import com.liguo.hgl.proxydao.model.TbTalk;
import com.liguo.hgl.service.IManagerService;
import com.liguo.hgl.service.IWeChatExecuteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName TextMsgPushExecuteService.java<br>
 * @Language Java<br>
 * @date 2016-01-12<br>
 */
@Service("textMsgPushExecuteService")
public class TextMsgPushExecuteService implements IWeChatExecuteService<ReqMsgPushMessage> {

    @Autowired
    protected TbTalkMapper tbTalkMapper;
    @Autowired
    protected IManagerService managerService;

    @Override
    public RespBaseMessage execute(ReqMsgPushMessage reqMessage) throws WapServiceException {
        if(managerService.isOpenInteract()){
            TbTalk tbTalk = new TbTalk();
            tbTalk.setVersion(HglContants.DEFUAL_VERSION);
            tbTalk.setOpenId(reqMessage.getFromUserName());
            tbTalk.setCreateTime(reqMessage.getCreateTime());
            tbTalk.setMessage(reqMessage.getContent());
            tbTalkMapper.insertSelective(tbTalk);
        }
        return new RespMsgPushMessage();
    }
}
