package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbCashAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 支出
     */
    private Double cashOut;

    /**
     * 转入
     */
    private Double cashIn;

    /**
     * 余额
     */
    private Double balance;

    /**
     * 操作时间
     */
    private Long operationDt;

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 账户ID
     */
    private Integer accountId;

    /**
     * 平台发起：1wap,0其它
     */
    private Integer platform;

    /**
     * 订单号
     */
    private String orderSerialNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 支出
     */
    public Double getCashOut() {
        return cashOut;
    }

    /**
     * @param cashOut 
	 *            支出
     */
    public void setCashOut(Double cashOut) {
        this.cashOut = cashOut;
    }

    /**
     * @return 转入
     */
    public Double getCashIn() {
        return cashIn;
    }

    /**
     * @param cashIn 
	 *            转入
     */
    public void setCashIn(Double cashIn) {
        this.cashIn = cashIn;
    }

    /**
     * @return 余额
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance 
	 *            余额
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * @return 操作时间
     */
    public Long getOperationDt() {
        return operationDt;
    }

    /**
     * @param operationDt 
	 *            操作时间
     */
    public void setOperationDt(Long operationDt) {
        this.operationDt = operationDt;
    }

    /**
     * @return 店铺ID
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            店铺ID
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 账户ID
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @param accountId 
	 *            账户ID
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * @return 平台发起：1wap,0其它
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * @param platform 
	 *            平台发起：1wap,0其它
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * @return 订单号
     */
    public String getOrderSerialNo() {
        return orderSerialNo;
    }

    /**
     * @param orderSerialNo 
	 *            订单号
     */
    public void setOrderSerialNo(String orderSerialNo) {
        this.orderSerialNo = orderSerialNo == null ? null : orderSerialNo.trim();
    }
}