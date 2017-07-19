/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.common;

/**
 * 交易码枚举。<br>
 *
 * @FileName TranCode.java
 * @Language java
 * @date 2014-10-17
 * @author 陈远长
 */
public enum TranCode {
    /** 与支付结算前台的接口定义从XPAY1001开始，请统一命名规范 */
	XPAY1001("XPAY1001", "市场发起签约"),//商户,平台，银行签约交易码
	XPAY1002("XPAY1002", "获取用户已签约银行列表"),//获取用户已签约银行列表
	XPAY1003("XPAY1003", "获取用户未签约银行信息"),//获取用户未签约银行信息
	XPAY1004("XPAY1004", "获取用户出入金记录"),//获取用户出入金记录
	XPAY1005("XPAY1005", "获取用户已签约银行的资金信息"),//获取用户已签约银行的资金信息
	XPAY1006("XPAY1006", "支付前台市场发起交易入金"),//入金操作 (验证数据合法性,生成入金记录,调银行前置机,修改账户金额,记流水)
    XPAY1007("XPAY1007", "交易出金"),
	XPAY1008("XPAY1008", "查询用户总额和银行资金信息"),//前台通用头部页面
	XPAY1009("XPAY1009","银行开闭市查询"),
	XPAY1010("XPAY1010","明细账查询"),
	XPAY1011("XPAY1011","冻结查询"),
	XPAY1012("XPAY1012","支付查询"),
    XPAY1013("XPAY1013","修改默认银行"),
	XPAY1014("XPAY1014","待支付查询"),
    XPAY1015("XPAY1015","登录鉴权值验证"),
    XPAY1016("XPAY1016","查询用户信息"),
    XPAY1017("XPAY1017","修改用户信息"),
    
    XPAY1018("XPAY1018","用户登录"),
    XPAY1019("XPAY1019","首页信息"),
    XPAY1020("XPAY1020","查询用户最新动态"),
    
    XPAY2001("XPAY2001","查询用户月交易收入总额、交易支出总额、入金总额、出金总额"),
    XPAY2002("XPAY2002","查询入金记录"),
    XPAY2003("XPAY2003","查询出金记录"),
    XPAY2004("XPAY2004", "查询待支付业务笔数"),

    /**账户管理相关接口**/
    XPAY3001("XPAY3001","查询用户登录历史记录"),
    XPAY3002("XPAY3002","查询平台银行信息"),
    XPAY3003("XPAY3003","添加银行账户"),
    XPAY3004("XPAY3004","删除银行账户"),
    XPAY3005("XPAY3005","修改银行账户"),
    XPAY3006("XPAY3006","修改用户密码"),
    
    
    /** 与快诺电商的接口定义从KWINER2001开始，请统一命名规范 */
    KWINER2001("KWINER2001","用户验证"),// 用户验证，业务系统注册用户时，需要先验证用户是否存在
    KWINER2002("KWINER2002","用户创建并绑定"),// 用户创建并绑定
    KWINER2003("KWINER2003","用户激活"),// 用户激活
    KWINER2004("KWINER2004","用户绑定"),// 用户绑定
    KWINER2005("KWINER2005","用户解绑"),// 用户解绑
    KWINER2006("KWINER2006","关联查询"),// 关联查询
    KWINER2007("KWINER2007",""),

    KWINER2100("KWINER2100","用户余额查询"),
    KWINER2101("KWINER2101","王丹专用"),
    KWINER2102("KWINER2102","王丹专用"),
    KWINER2103("KWINER2103","王丹专用"),
    KWINER2104("KWINER2104","王丹专用"),
    KWINER2105("KWINER2105","王丹专用"),

    /** 暂时无用的定义 */
    XPAY9999("", "");
    String code;
    String desc;

    TranCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
