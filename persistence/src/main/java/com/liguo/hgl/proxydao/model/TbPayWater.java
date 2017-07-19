package com.liguo.hgl.proxydao.model;

import java.math.BigDecimal;
import java.util.Date;

public class TbPayWater {
    /**
     * id
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderGroupNo;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 支付时间开始时间
     */
    private Date payDateStart;

    /**
     * 支付结束时间
     */
    private Date payDateEnd;

    /**
     * 支付金额,微信是分，支付宝是元
     */
    private BigDecimal payAmt;

    /**
     * 支付状态码(本系统)
     */
    private String payStatusCode;

    /**
     * 结果状态码(本系统)
     */
    private String payResultCode;

    /**
     * 支付接口名称
     */
    private String payServiceName;

    /**
     * 支付宝签约账号
     */
    private String payZfbSignCode;

    /**
     * 支付宝卖家账号
     */
    private String payZfbSeller;

    /**
     * 支付宝买家账号
     */
    private String payZfbBuyer;

    /**
     * 第三方交易流水号
     */
    private String thdTradeNo;

    /**
     * 第三方交易状态
     */
    private String thdTradeStatus;

    /**
     * 启用状态
     */
    private String payStatus;

    /**
     * 定时任务查询次数
     */
    private String timeTaskQueries;

    /**
     * 微信回调URL
     */
    private String thdWxUrl;

    /**
     * 微信公众号ID
     */
    private String thdWxId;

    /**
     * 微信微信商户号随机字符串
     */
    private String thdWxMch;

    /**
     * 微信随机字符串
     */
    private String thdWxNonce;

    /**
     * 微信终端ip
     */
    private String thdWxSpbill;

    /**
     * 二维码链接
     */
    private String thdWxCodeUrl;

    /**
     * 版本
     */
    private Integer version;
    
    
    private String message;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 订单号
     */
    public String getOrderGroupNo() {
        return orderGroupNo;
    }

    /**
     * @param orderGroupNo 
	 *            订单号
     */
    public void setOrderGroupNo(String orderGroupNo) {
        this.orderGroupNo = orderGroupNo == null ? null : orderGroupNo.trim();
    }

    /**
     * @return 支付方式
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType 
	 *            支付方式
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * @return 支付时间开始时间
     */
    public Date getPayDateStart() {
        return payDateStart;
    }

    /**
     * @param payDateStart 
	 *            支付时间开始时间
     */
    public void setPayDateStart(Date payDateStart) {
        this.payDateStart = payDateStart;
    }

    /**
     * @return 支付结束时间
     */
    public Date getPayDateEnd() {
        return payDateEnd;
    }

    /**
     * @param payDateEnd 
	 *            支付结束时间
     */
    public void setPayDateEnd(Date payDateEnd) {
        this.payDateEnd = payDateEnd;
    }

    /**
     * @return 支付金额,微信是分，支付宝是元
     */
    public BigDecimal getPayAmt() {
        return payAmt;
    }

    /**
     * @param payAmt 
	 *            支付金额,微信是分，支付宝是元
     */
    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    /**
     * @return 支付状态码(本系统)
     */
    public String getPayStatusCode() {
        return payStatusCode;
    }

    /**
     * @param payStatusCode 
	 *            支付状态码(本系统)
     */
    public void setPayStatusCode(String payStatusCode) {
        this.payStatusCode = payStatusCode == null ? null : payStatusCode.trim();
    }

    /**
     * @return 结果状态码(本系统)
     */
    public String getPayResultCode() {
        return payResultCode;
    }

    /**
     * @param payResultCode 
	 *            结果状态码(本系统)
     */
    public void setPayResultCode(String payResultCode) {
        this.payResultCode = payResultCode == null ? null : payResultCode.trim();
    }

    /**
     * @return 支付接口名称
     */
    public String getPayServiceName() {
        return payServiceName;
    }

    /**
     * @param payServiceName 
	 *            支付接口名称
     */
    public void setPayServiceName(String payServiceName) {
        this.payServiceName = payServiceName == null ? null : payServiceName.trim();
    }

    /**
     * @return 支付宝签约账号
     */
    public String getPayZfbSignCode() {
        return payZfbSignCode;
    }

    /**
     * @param payZfbSignCode 
	 *            支付宝签约账号
     */
    public void setPayZfbSignCode(String payZfbSignCode) {
        this.payZfbSignCode = payZfbSignCode == null ? null : payZfbSignCode.trim();
    }

    /**
     * @return 支付宝卖家账号
     */
    public String getPayZfbSeller() {
        return payZfbSeller;
    }

    /**
     * @param payZfbSeller 
	 *            支付宝卖家账号
     */
    public void setPayZfbSeller(String payZfbSeller) {
        this.payZfbSeller = payZfbSeller == null ? null : payZfbSeller.trim();
    }

    /**
     * @return 支付宝买家账号
     */
    public String getPayZfbBuyer() {
        return payZfbBuyer;
    }

    /**
     * @param payZfbBuyer 
	 *            支付宝买家账号
     */
    public void setPayZfbBuyer(String payZfbBuyer) {
        this.payZfbBuyer = payZfbBuyer == null ? null : payZfbBuyer.trim();
    }

    /**
     * @return 第三方交易流水号
     */
    public String getThdTradeNo() {
        return thdTradeNo;
    }

    /**
     * @param thdTradeNo 
	 *            第三方交易流水号
     */
    public void setThdTradeNo(String thdTradeNo) {
        this.thdTradeNo = thdTradeNo == null ? null : thdTradeNo.trim();
    }

    /**
     * @return 第三方交易状态
     */
    public String getThdTradeStatus() {
        return thdTradeStatus;
    }

    /**
     * @param thdTradeStatus 
	 *            第三方交易状态
     */
    public void setThdTradeStatus(String thdTradeStatus) {
        this.thdTradeStatus = thdTradeStatus == null ? null : thdTradeStatus.trim();
    }

    /**
     * @return 启用状态
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * @param payStatus 
	 *            启用状态
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    /**
     * @return 定时任务查询次数
     */
    public String getTimeTaskQueries() {
        return timeTaskQueries;
    }

    /**
     * @param timeTaskQueries 
	 *            定时任务查询次数
     */
    public void setTimeTaskQueries(String timeTaskQueries) {
        this.timeTaskQueries = timeTaskQueries == null ? null : timeTaskQueries.trim();
    }

    /**
     * @return 微信回调URL
     */
    public String getThdWxUrl() {
        return thdWxUrl;
    }

    /**
     * @param thdWxUrl 
	 *            微信回调URL
     */
    public void setThdWxUrl(String thdWxUrl) {
        this.thdWxUrl = thdWxUrl == null ? null : thdWxUrl.trim();
    }

    /**
     * @return 微信公众号ID
     */
    public String getThdWxId() {
        return thdWxId;
    }

    /**
     * @param thdWxId 
	 *            微信公众号ID
     */
    public void setThdWxId(String thdWxId) {
        this.thdWxId = thdWxId == null ? null : thdWxId.trim();
    }

    /**
     * @return 微信微信商户号随机字符串
     */
    public String getThdWxMch() {
        return thdWxMch;
    }

    /**
     * @param thdWxMch 
	 *            微信微信商户号随机字符串
     */
    public void setThdWxMch(String thdWxMch) {
        this.thdWxMch = thdWxMch == null ? null : thdWxMch.trim();
    }

    /**
     * @return 微信随机字符串
     */
    public String getThdWxNonce() {
        return thdWxNonce;
    }

    /**
     * @param thdWxNonce 
	 *            微信随机字符串
     */
    public void setThdWxNonce(String thdWxNonce) {
        this.thdWxNonce = thdWxNonce == null ? null : thdWxNonce.trim();
    }

    /**
     * @return 微信终端ip
     */
    public String getThdWxSpbill() {
        return thdWxSpbill;
    }

    /**
     * @param thdWxSpbill 
	 *            微信终端ip
     */
    public void setThdWxSpbill(String thdWxSpbill) {
        this.thdWxSpbill = thdWxSpbill == null ? null : thdWxSpbill.trim();
    }

    /**
     * @return 二维码链接
     */
    public String getThdWxCodeUrl() {
        return thdWxCodeUrl;
    }

    /**
     * @param thdWxCodeUrl 
	 *            二维码链接
     */
    public void setThdWxCodeUrl(String thdWxCodeUrl) {
        this.thdWxCodeUrl = thdWxCodeUrl == null ? null : thdWxCodeUrl.trim();
    }

    /**
     * @return 版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * @param 返回信息 
     *            微信终端ip
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * @return 返回信息
     */
    public String getMessage() {
        return message;
    }
}