/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.base;

import com.liguo.hgl.proxydao.base.IBodyData;

/**
 * 一个用于标记支付结算报文数据报文的接口。<br>
 *
 * @FileName Message.java
 * @Language java
 * @date 2014-07-30
 * @author 陈远长
 */
public interface IMessage {
    /**
     * 获取报文中的报文头部分
     * @return 报文头数据实体对象
     */
    Header getHead();

    /**
     * 获取报文中的数据对象
     * @return 报文数据实体对象
     */
    IBodyData getData();
}
