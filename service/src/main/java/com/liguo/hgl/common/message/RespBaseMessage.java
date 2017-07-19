/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common.message;

import com.liguo.hgl.common.MessageEnum;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName RespBaseMessage.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
public class RespBaseMessage {

    @XStreamOmitField
    private String errcode = MessageEnum.I0000.getMessageCode();

    @XStreamOmitField
    private String errmsg = MessageEnum.I0000.getMessageValue();

    public RespBaseMessage(){}

    public RespBaseMessage(MessageEnum messageEnum){
        this.errcode = messageEnum.getMessageCode();
        this.errmsg = messageEnum.getMessageValue();
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
