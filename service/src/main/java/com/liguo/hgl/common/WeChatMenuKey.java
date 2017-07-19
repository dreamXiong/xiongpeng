/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName WeChatKey.java<br>
 * @Language Java<br>
 * @date 2016-01-11<br>
 */
public enum WeChatMenuKey {

    YEAR_MAIN("", "", "签到摇奖"),

    YEAR_SIGN_IN("click", "KWINER_YEAR_SIGN_IN", "签到"),
    YEAR_WIN("view", HglContants.SERVER_URL+"win", "摇奖"),
    YEAR_VOTE("view", "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+HglContants.APP_ID_FOR_KWINER+"&redirect_uri="+HglContants.PRAISE_URL+"&response_type=code&scope=snsapi_base#wechat_redirect", "投票"),

    YEAR_HELP("view", HglContants.SERVER_URL+"help", "帮助");

    String type;
    String code;
    String value;

    WeChatMenuKey(String type, String code, String value) {
        this.type = type;
        this.code = code;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
