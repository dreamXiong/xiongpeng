/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.base;

import com.liguo.hgl.proxydao.base.IBodyData;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 响应只包含请求头的空报文返回消息<br>
 *
 * @author 王丹
 * @FileName RespVoidMessage.java<br>
 * @Language Java<br>
 * @date 2014-11-13<br>
 */
@XStreamAlias("Message")
public class RespVoidMessage implements IMessage {
    public Header Head;
    public String Signature;

    @Override
    public Header getHead() {

        return Head;
    }

    @Override
    public IBodyData getData() {

        return null;
    }
}
