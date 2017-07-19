/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common.crypt;

import com.liguo.hgl.common.HglContants;

/**
 * 类的功能描述<br>
 *
 * @author 王丹
 * @FileName MyTest.java<br>
 * @Language Java<br>
 * @date 2016-01-07<br>
 */
public class MyTest {

    public static void main(String[] args) {
        try {
            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(HglContants.TOKEN_FOR_KWINER, HglContants.AES_KEY_FOR_KWINER, HglContants.APP_ID_FOR_KWINER);
            String signature,timestamp,nonce,echostr;
            signature = "2b20feb39736e571ccafafa58e57d34e0ea2466a";
            timestamp = "1452159482";
            nonce = "849435541";
            echostr = "191457424477944315";
            String s = wxBizMsgCrypt.verifyUrl(signature, timestamp, nonce, echostr, false);
            System.out.println(s);
        } catch (AesException e) {
            e.printStackTrace();
        }

    }
}
