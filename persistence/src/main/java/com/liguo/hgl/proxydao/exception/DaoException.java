/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.proxydao.exception;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName DaoException.java<br>
 * @Language Java<br>
 * @date 2015-11-20<br>
 */
public class DaoException extends RuntimeException {

    /** 错误码 */
    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public DaoException() {
        super();
    }

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(String msg, Throwable t) {
        super(msg, t);
    }

    public DaoException(String errorCode, String msg) {
        this(msg);
        this.errorCode = errorCode;
    }

    public DaoException(String errorCode, String msg, Throwable t) {
        this(msg, t);
        this.errorCode = errorCode;
    }

}
