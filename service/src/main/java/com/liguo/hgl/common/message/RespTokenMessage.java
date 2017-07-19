/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common.message;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName RespTokenMessage.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
public class RespTokenMessage extends RespBaseMessage {

    private String accessToken;
    /** 公众号返回的凭证有效时间 单位：秒 */
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
