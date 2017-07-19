package com.liguo.hgl.exceptions;

import com.liguo.hgl.common.MessageEnum;

/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

/**
 * 支付结算平台的异常基础类型。
 * 支持异常应答包输出，支持异常日志输出，支持异常回滚调用<br>
 * 
 * @FileName XPayException.java。<br>
 * @Language java。<br>
 * @date 2014-07-30。<br>
 * @author LeoX
 */
public class WapException extends Exception {
    /** 错误码 */
    private String errorCode;

    public String getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(String errorCode) {

        this.errorCode = errorCode;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    //	public XPayException(String errCode) {
    //		super();
    //		errorCode = errCode;
    //	}
    public WapException() {

        super();
    }

    public WapException(String msg) {

        super(msg);
    }

    public WapException(String msg, Throwable t) {

        super(msg, t);
    }

    public WapException(String errorCode, String msg) {

        this(msg);
        this.errorCode = errorCode;
    }

    public WapException(String errorCode, String msg, Throwable t) {

        this(msg, t);
        this.errorCode = errorCode;
    }

    public WapException(MessageEnum messageEnum) {

        this(messageEnum.getMessageValue());
        this.errorCode = messageEnum.getMessageCode();
    }
}
