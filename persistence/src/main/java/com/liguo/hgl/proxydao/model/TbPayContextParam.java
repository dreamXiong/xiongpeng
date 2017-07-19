package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbPayContextParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderGroubNo;

    /**
     * 当前订单的状态
     */
    private Integer tradeStatus;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 0:支付，1:充值
     */
    private String payment;

    /**
     * 备用字段2
     */
    private String accountId;

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
    public String getOrderGroubNo() {
        return orderGroubNo;
    }

    /**
     * @param orderGroubNo 
	 *            订单号
     */
    public void setOrderGroubNo(String orderGroubNo) {
        this.orderGroubNo = orderGroubNo == null ? null : orderGroubNo.trim();
    }

    /**
     * @return 当前订单的状态
     */
    public Integer getTradeStatus() {
        return tradeStatus;
    }

    /**
     * @param tradeStatus 
	 *            当前订单的状态
     */
    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
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
     * @return 0:支付，1:充值
     */
    public String getPayment() {
        return payment;
    }

    /**
     * @param payment 
	 *            0:支付，1:充值
     */
    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    /**
     * @return 备用字段2
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId 
	 *            备用字段2
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
}