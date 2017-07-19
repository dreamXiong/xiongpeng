/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common.message;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName ReqCustomSendMessage.java<br>
 * @Language Java<br>
 * @date 2016-01-11<br>
 */
public class ReqCustomSendMessage extends ReqBaseMessage {

    private String touser;
    private String msgtype;
    private Text text;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public static class Text{
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
