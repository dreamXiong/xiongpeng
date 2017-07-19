package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbFreezing implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 账户ID
     */
    private Integer accountId;

    /**
     * 订单号
     */
    private String orderSerialNo;

    /**
     * 冻结金额
     */
    private Double freezeMoney;

    /**
     * 解冻金额
     */
    private Double unfreezeMoney;

    /**
     * 账户总额
     */
    private Double balance;

    /**
     * 冻结总额
     */
    private Double freeze;

    /**
     * 操作时间
     */
    private Long opearDt;

    /**
     * 是否完成：0否，1是
     */
    private Integer finish;

    private Integer version;

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
     * @return 订单ID
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId 
	 *            订单ID
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    /**
     * @return 冻结金额
     */
    public Double getFreezeMoney() {
        return freezeMoney;
    }

    /**
     * @param freezeMoney 
	 *            冻结金额
     */
    public void setFreezeMoney(Double freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    /**
     * @return 解冻金额
     */
    public Double getUnfreezeMoney() {
        return unfreezeMoney;
    }

    /**
     * @param unfreezeMoney 
	 *            解冻金额
     */
    public void setUnfreezeMoney(Double unfreezeMoney) {
        this.unfreezeMoney = unfreezeMoney;
    }

    /**
     * @return 账户总额
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance 
	 *            账户总额
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * @return 冻结总额
     */
    public Double getFreeze() {
        return freeze;
    }

    /**
     * @param freeze 
	 *            冻结总额
     */
    public void setFreeze(Double freeze) {
        this.freeze = freeze;
    }

    /**
     * @return 操作时间
     */
    public Long getOpearDt() {
        return opearDt;
    }

    /**
     * @param opearDt 
	 *            操作时间
     */
    public void setOpearDt(Long opearDt) {
        this.opearDt = opearDt;
    }

    /**
     * @return 是否完成：0否，1是
     */
    public Integer getFinish() {
        return finish;
    }

    /**
     * @param finish 
	 *            是否完成：0否，1是
     */
    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}