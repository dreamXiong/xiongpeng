/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName Token.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
public class TokenKey {

    private String token;
    /** 失效时间，毫秒 */
    private long expiresTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }
}
