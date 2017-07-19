/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common;

import java.lang.reflect.Field;

/**
 * 消息码枚举定义<br>
 *
 * @author 王丹
 * @FileName MessageEnum.java<br>
 * @Language Java<br>
 * @date 2014-11-5<br>
 */
public class MessageEnum {

    /** 验证类错误码 */
    public static final MessageEnum I0000 = new MessageEnum("0", "ok");

    public static final MessageEnum I1000 = new MessageEnum("801000", "业务执行方法不存在");
    public static final MessageEnum I1001 = new MessageEnum("801001", "你已经签过到了");
    public static final MessageEnum I1002 = new MessageEnum("801002", "签到已经关闭");


    public static final MessageEnum E9000 = new MessageEnum("909000", "数据实体已经被修改");
    public static final MessageEnum E9999 = new MessageEnum("909999", "系统异常");
    
    public static final MessageEnum Z2001 = new MessageEnum("602001", "支付金额不足");
    public static final MessageEnum Z2002 = new MessageEnum("602002", "订单账号不匹配！");
    public static final MessageEnum Z2003 = new MessageEnum("602003", "返利扣除金额不足");
    public static final MessageEnum Z2004 = new MessageEnum("602004", "提现时余额不足");
    public static final MessageEnum Z2005 = new MessageEnum("602006", "支付回调时订单不存在");
    public static final MessageEnum Z2006 = new MessageEnum("602007", "维护支付订单表数据异常");
    public static final MessageEnum Z2007 = new MessageEnum("602008", "店铺尚未开通结算功能");
    public static final MessageEnum Z2008 = new MessageEnum("602009", "支付金额小于0");

    protected String messageCode;
    protected String messageValue;

    public MessageEnum(String code, String value) {

        this.messageCode = code;
        this.messageValue = value;
    }
    
    public static MessageEnum valueOf(String code){
    	try {
			Class<MessageEnum> c = MessageEnum.class;
			Field[] fs = c.getFields();
			for(Field f : fs){
				if(f.getName().equals(code)){
					return (MessageEnum)f.get(new MessageEnum("",""));
				}
			}
		} catch (Exception e) {
		} 
		return null;
    }
    
    public String name(){
    	return messageCode;
    }

    public String getMessageCode() {

        return messageCode;
    }

    public String getMessageValue() {

        return messageValue;
    }

    public boolean isSuccess() {

        boolean ret = false;
        if (MessageEnum.I0000.getMessageCode().equals(this.messageCode)) {
            ret = true;
        }
        return ret;
    }
    
    

    @Override
	public String toString() {
		return this.messageCode;
	}

	public static boolean isSuccess(String code) {

        boolean ret = false;
        if (MessageEnum.I0000.getMessageCode().equals(code)) {
            ret = true;
        }
        return ret;
    }
}
