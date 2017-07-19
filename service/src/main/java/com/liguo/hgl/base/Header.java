/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.base;

import java.io.Serializable;

import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.common.TranCode;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.util.BeanUtil;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.proxydao.util.ToolsUtil;

/**
 * 支付结算平台报文的头信息Bean。<br>
 *
 * @FileName Head.java
 * @Language java
 * @date 2014-07-30
 * @author 陈远长
 */
public class Header implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 版本号，交易时要判断版本匹配 */
    //    public String Version;
    /** 交易日期,请求端生成 */
    //    public String TrnDate;
    /** 交易流水号,请求端生成 */
    public String TrnSerNo;
    /** 交易请求代码,请求端生成 */
    public String TrnCode;
    /** 平台流水号 */
    public String PlatformSerNo;
    /** 第三方系统代码,不可为空 */
    public String SystemNo;
    /** 请求端时间戳：YYYYMMDDHHMMSS,请求时使用 **/
    public String ClientTime;
    /** 响应端时间戳：YYYYMMDDHHMMSS,响应时使用 */
    public String ServerTime;
    /** 交易处理结果代码,000000成功,响应时给出 */
    public String RespCode;
    /** 交易处理结果信息,响应时给出 */
    public String RespMessage;
    /** 附加信息 */
    public String Addtion;
    /** 银行参考号（建行的要求） */
    public String bankrefno;

    public String getTrnSerNo() {

        return TrnSerNo;
    }

    public void setTrnSerNo(String trnSerNo) {

        TrnSerNo = trnSerNo;
    }

    public String getTrnCode() {

        return TrnCode;
    }

    public void setTrnCode(String trnCode) {

        TrnCode = trnCode;
    }

    public String getPlatformSerNo() {

        return PlatformSerNo;
    }

    public void setPlatformSerNo(String platformSerNo) {

        PlatformSerNo = platformSerNo;
    }

    public String getSystemNo() {

        return SystemNo;
    }

    public void setSystemNo(String systemNo) {

        SystemNo = systemNo;
    }

    public String getClientTime() {

        return ClientTime;
    }

    public void setClientTime(String clientTime) {

        ClientTime = clientTime;
    }

    public String getServerTime() {

        return ServerTime;
    }

    public void setServerTime(String serverTime) {

        ServerTime = serverTime;
    }

    public String getRespCode() {

        return RespCode;
    }

    public void setRespCode(String respCode) {

        RespCode = respCode;
    }

    public String getRespMessage() {

        return RespMessage;
    }

    public void setRespMessage(String respMessage) {

        RespMessage = respMessage;
    }

    public String getAddtion() {

        return Addtion;
    }

    public void setAddtion(String addtion) {

        Addtion = addtion;
    }

    public String getBankrefno() {

        return bankrefno;
    }

    public void setBankrefno(String bankrefno) {

        this.bankrefno = bankrefno;
    }
}
