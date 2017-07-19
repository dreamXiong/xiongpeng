/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common.message;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName RespMenuAddconditionalMessage.java<br>
 * @Language Java<br>
 * @date 2016-01-11<br>
 */
public class RespMenuAddconditionalMessage extends RespBaseMessage {

    private String menuid;

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
}
