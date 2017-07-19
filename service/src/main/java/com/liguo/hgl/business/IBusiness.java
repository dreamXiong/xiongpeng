/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.business;

import com.liguo.hgl.base.IMessage;
import com.liguo.hgl.exceptions.WapException;

/**
 * 业务处理接口<br>
 *
 * @author 王丹
 * @FileName IHandleAccountingService.java<br>
 * @Language Java<br>
 * @date 2014-11-12<br>
 */
public interface IBusiness {

    /**
     * 执行业务处理
     * @param reqMessage
     * @return
     * @throws XPayException
     */
    public <E> E execute(IMessage reqMessage) throws WapException;

}
