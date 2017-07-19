/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common.message;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName ReqGroupsMoveMessage.java<br>
 * @Language Java<br>
 * @date 2016-01-11<br>
 */
public class ReqGroupsMoveMessage extends ReqBaseMessage {

    private String openid;
    private String toGroupid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getToGroupid() {
        return toGroupid;
    }

    public void setToGroupid(String toGroupid) {
        this.toGroupid = toGroupid;
    }
}
