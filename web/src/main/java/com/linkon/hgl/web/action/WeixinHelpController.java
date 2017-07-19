/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.linkon.hgl.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 支付结算业务入口Controller<br>
 * 用于各个市场调用支付结算入口，市场定义参照交易平台通道表（marketchanneltable）定义的市场通道
 * 根据交易平台通道表srvprivatekey字段解密报文，将来是否配置签名验签证书路径待定
 * 根据交易码将报文转换成对应的业务消息
 * 根据交易码调用对应的业务逻辑服务
 * 返回相应的业务报文
 *
 * @author 王丹
 * @FileName PlatformController.java<br>
 * @Language Java<br>
 * @date 2015-05-06<br>
 */
@Controller
@RequestMapping("/help")
public class WeixinHelpController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String HELP_PAGE = "/help";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {

       
        return HELP_PAGE;
    }

}
