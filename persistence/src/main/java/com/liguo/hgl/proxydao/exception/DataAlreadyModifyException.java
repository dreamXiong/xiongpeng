/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.proxydao.exception;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName DataAlreadyModifyException.java<br>
 * @Language Java<br>
 * @date 2015-11-20<br>
 */
public class DataAlreadyModifyException extends DaoException {

    public DataAlreadyModifyException(String msg) {
        super(msg);
    }

    public DataAlreadyModifyException(String errorCode, String msg) {
        super(errorCode, msg);
    }
}
