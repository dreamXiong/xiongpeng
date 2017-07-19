package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbIntegralMallRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 积分商城表ID
     */
    private Integer integralMallId;

    /**
     * 积分序列号(系统生成)
     */
    private String integralSerialNo;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 应付的总金额
     */
    private Double payMoney;

    /**
     * 使用积分
     */
    private Integer remainingIntegral;

    /**
     * 支付状态(待支付,已支付)
     */
    private Integer payStatus;

    /**
     * 发货状态(待发货,已发货)
     */
    private Integer platStatus;

    /**
     * 兑换数量
     */
    private Integer exchangeNum;

    /**
     * 收货地址历史表(tb_wap_address_history)ID
     */
    private Integer addressId;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注信息
     */
    private String remark;

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
     * @return 积分商城表ID
     */
    public Integer getIntegralMallId() {
        return integralMallId;
    }

    /**
     * @param integralMallId 
	 *            积分商城表ID
     */
    public void setIntegralMallId(Integer integralMallId) {
        this.integralMallId = integralMallId;
    }

    /**
     * @return 积分序列号(系统生成)
     */
    public String getIntegralSerialNo() {
        return integralSerialNo;
    }

    /**
     * @param integralSerialNo 
	 *            积分序列号(系统生成)
     */
    public void setIntegralSerialNo(String integralSerialNo) {
        this.integralSerialNo = integralSerialNo == null ? null : integralSerialNo.trim();
    }

    /**
     * @return 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return 应付的总金额
     */
    public Double getPayMoney() {
        return payMoney;
    }

    /**
     * @param payMoney 
	 *            应付的总金额
     */
    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    /**
     * @return 剩余积分
     */
    public Integer getRemainingIntegral() {
        return remainingIntegral;
    }

    /**
     * @param remainingIntegral 
	 *            剩余积分
     */
    public void setRemainingIntegral(Integer remainingIntegral) {
        this.remainingIntegral = remainingIntegral;
    }

    /**
     * @return 支付状态(待支付,已支付)
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * @param payStatus 
	 *            支付状态(待支付,已支付)
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * @return 发货状态(待发货,已发货)
     */
    public Integer getPlatStatus() {
        return platStatus;
    }

    /**
     * @param platStatus 
	 *            发货状态(待发货,已发货)
     */
    public void setPlatStatus(Integer platStatus) {
        this.platStatus = platStatus;
    }

    /**
     * @return 兑换数量
     */
    public Integer getExchangeNum() {
        return exchangeNum;
    }

    /**
     * @param exchangeNum 
	 *            兑换数量
     */
    public void setExchangeNum(Integer exchangeNum) {
        this.exchangeNum = exchangeNum;
    }

    /**
     * @return 收货地址历史表(tb_wap_address_history)ID
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId 
	 *            收货地址历史表(tb_wap_address_history)ID
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * @return 创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy 
	 *            创建人
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * @return 创建时间
     */
    public Long getCreateDt() {
        return createDt;
    }

    /**
     * @param createDt 
	 *            创建时间
     */
    public void setCreateDt(Long createDt) {
        this.createDt = createDt;
    }

    /**
     * @return 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * @return 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}