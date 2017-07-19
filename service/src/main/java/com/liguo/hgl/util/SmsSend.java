/**
 * 短信接口的工具类
 */
package com.liguo.hgl.util;


public class SmsSend {
	public static final String SERVER_URL="http://gw.api.taobao.com/router/rest";
	public static final String APP_KEY="23393216";
	public static final String APP_SECRET="0119618e86b90490db2da109e542dffa";
	
	/**
	 * 注册验证码发送
	 * @param phoneNum 手机号
	 * @param code	验证码
	 * @param userType 用户类型，直接中文显示
	 * 发送到短信格式为  ：   尊敬的用户，您正在注册成为惠给利 ${userType} ,您的验证码为 ${code} ，验证码10分钟内有效。
	 * 
	 * @return 错误码，为空表示发送成功
	 */
	public static String sendRegestSms(String phoneNum,String code,String userType){
		String paramString="{\"code\":\""+code+"\",\"userType\":\""+userType+"\"}";
		String smsTemplateCode="SMS_12220378";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}
	
	
	/**
	 * 身份验证码
	 * @param phoneNum 手机号
	 * @param code	验证码
	 * 发送到短信格式为  ：   验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！
	 * 
	 * @return 错误码，为空表示发送成功
	 */
	public static String sendCheckSms(String phoneNum,String code){
		String paramString="{\"code\":\""+code+"\",\"product\":\"惠给利\"}";
		String smsTemplateCode="SMS_10890067";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}
	
	/**
	 * 修改密码验证码
	 * @param phoneNum 手机号
	 * @param code	验证码
	 * 发送到短信格式为  ：   验证码${code}，您正在尝试修改${product}登录密码，请妥善保管账户信息。
	 * 
	 * @return 错误码，为空表示发送成功
	 */
	public static String sendModifyPasswordSms(String phoneNum,String code){
		String paramString="{\"code\":\""+code+"\",\"product\":\"惠给利\"}";
		String smsTemplateCode="SMS_10890061";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}
	
	/**-------------------------------------------------------------------
	*模板类型:短信通知
	*模板名称:经销商注册
	*模板ID:SMS_12520233
	*模板内容:尊敬的${name}，您申请的注册信息已提交，我们将尽快为您审核，审核通过后请重新登录平台，感谢您的支持。
	*申请说明:经销商注册
	 */
	/*public static String sendStoreMsg(String phoneNum,String name){
		String paramString="{\"name\":\""+name+"\"}";
		String smsTemplateCode="SMS_12520233";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}*/
	/**
	 * 	模板类型:短信通知
		模板名称:申请通过
		模板ID:SMS_12470131
		模板内容:尊敬的${name}，您申请的注册信息已通过审核，请重新登录平台，并获取上架货品权限，感谢您的支持。
		申请说明:申请通过
	 */
	public static String sendStorePass(String phoneNum,String name){
		String paramString="{\"name\":\""+name+"\"}";
		String smsTemplateCode="SMS_12470131";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}
	
	/**
	模板类型:短信通知
	模板名称:获取权限成功
	模板ID:SMS_12490181
	模板内容:尊敬的${name}，您已获得上架货品权限，感谢您的支持，如有疑问欢迎致电： 4006-718-278 。
	申请说明:获取权限成功 
	 */
	public static String sendPayed(String phoneNum,String name){
		String paramString="{\"name\":\""+name+"\"}";
		String smsTemplateCode="SMS_12490181";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}
	
	/**
	 模板类型:短信通知
	模板名称:提现审核通过
	模板ID:SMS_12485135
	模板内容:尊敬的${name}，您申请的金额为${money}的提现已经审核通过，汇入尾号为${num}银行账户，请注意查收。
	申请说明:提现审核通过
	 */
	public static String sendCashPass(String phoneNum,String name,Double money,String num){
		String paramString="{\"code\":\""+name+"\",\"money\":\""+money+"\",\"num\":\""+num+"\"}";
		String smsTemplateCode="SMS_12450184";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}
	
	/**
	 模板类型:短信通知
	模板名称:提现审核拒绝
	模板ID:SMS_12515247
	模板内容:尊敬的${name}，您申请的金额为${money}的提现未通过平台审核，请重新填写。
	申请说明:提现审核通过
	 */
	public static String sendCashRefuse(String phoneNum,String name,Double money){
		String paramString="{\"code\":\""+name+"\",\"money\":\""+money+"\"}";
		String smsTemplateCode="SMS_12515247";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}
	
	/**
	 模板类型:订单支付成功
	模板内容:尊敬的${name}，您申请的金额为${money}的提现未通过平台审核，请重新填写。
	申请说明:提现审核通过
	 */
	/*public static String paymentSuccess(String phoneNum,String orderSerialNo,String name,Double money){
		String paramString="{\"code\":\""+name+"\",\"money\":\""+money+"\",\"orderSerialNo\":\""+orderSerialNo+"\"}";
		String smsTemplateCode="SMS_12495254";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}*/
	
	/**
	模板类型:短信通知
	模板名称:提交提现审核
	模板ID:SMS_11505001
	模板内容:尊敬的${name}，您申请的金额为${money}的体现已提交，审核通过后将汇入尾号为${name}，请注意查收。
	申请说明:提交提现审核
	 */
	/*public static String sendCash(String phoneNum,String name,Double money,String num){
		String paramString="{\"code\":\""+name+"\",\"money\":\""+money+"\",\"num\":\""+num+"\"}";
		String smsTemplateCode="SMS_11505001";		
		return sendSms(phoneNum, paramString, smsTemplateCode);
	}*/
	
	/**
	 * 
	 * @param phoneNum
	 * @param paramString
	 * @param smsTemplateCode
	 * @return
	 */	
	private static String sendSms(String phoneNum,String paramString,String smsTemplateCode){
		String retStr="";
//		System.out.println("phoneNum="+phoneNum+";paramString="+paramString+";smsTemplateCode="+smsTemplateCode);
//		TaobaoClient client = new DefaultTaobaoClient(SERVER_URL, APP_KEY, APP_SECRET);
//		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend("123456");
//		req.setSmsType("normal");
//		req.setSmsFreeSignName("惠给利");
//		req.setSmsParamString(paramString);
//		req.setRecNum(phoneNum);
//		req.setSmsTemplateCode(smsTemplateCode);
//		AlibabaAliqinFcSmsNumSendResponse rsp;
//		try {
//			rsp = client.execute(req);
//			if(!rsp.isSuccess()){
//				retStr=rsp.getMsg();
//			}
//		} catch (ApiException e) {
//			e.printStackTrace();
//			retStr="发送失败！";
//		}
		return retStr;
	}
	
	
	public static void main(String[] args) {
		sendModifyPasswordSms("15820914042","5231");
	}
}
