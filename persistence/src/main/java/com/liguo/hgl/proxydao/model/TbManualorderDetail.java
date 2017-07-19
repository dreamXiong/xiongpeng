package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbManualorderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 动手补单id,自动增长
     */
    private Integer id;

    /**
     * 买人购id
     */
    private String buyId;

    /**
     * 单订表id
     */
    private Integer orderId;

    /**
     * 买购时间
     */
    private Long buyDt;

    /**
     * 买人购手机号
     */
    private String mobile;

    /**
     * 应付总金额
     */
    private Double payAmount;

    /**
     * 改修之前的态状
     */
    private Integer statusBefore;

    /**
     * 改修之后的状态
     */
    private Integer statusAfter;

    /**
     * 单补时间
     */
    private Long operateDt;

    /**
     * 单补人
     */
    private String operateBy;

    /**
     * 版本ID
     */
    private Integer version;

    /**
     * @return 动手补单id,自动增长
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            动手补单id,自动增长
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 买人购id
     */
    public String getBuyId() {
        return buyId;
    }

    /**
     * @param buyId 
	 *            买人购id
     */
    public void setBuyId(String buyId) {
        this.buyId = buyId == null ? null : buyId.trim();
    }

    /**
     * @return 单订表id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId 
	 *            单订表id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return 买购时间
     */
    public Long getBuyDt() {
        return buyDt;
    }

    /**
     * @param buyDt 
	 *            买购时间
     */
    public void setBuyDt(Long buyDt) {
        this.buyDt = buyDt;
    }

    /**
     * @return 买人购手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile 
	 *            买人购手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * @return 应付总金额
     */
    public Double getPayAmount() {
        return payAmount;
    }

    /**
     * @param payAmount 
	 *            应付总金额
     */
    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * @return 改修之前的态状
     */
    public Integer getStatusBefore() {
        return statusBefore;
    }

    /**
     * @param statusBefore 
	 *            改修之前的态状
     */
    public void setStatusBefore(Integer statusBefore) {
        this.statusBefore = statusBefore;
    }

    /**
     * @return 改修之后的状态
     */
    public Integer getStatusAfter() {
        return statusAfter;
    }

    /**
     * @param statusAfter 
	 *            改修之后的状态
     */
    public void setStatusAfter(Integer statusAfter) {
        this.statusAfter = statusAfter;
    }

    /**
     * @return 单补时间
     */
    public Long getOperateDt() {
        return operateDt;
    }

    /**
     * @param operateDt 
	 *            单补时间
     */
    public void setOperateDt(Long operateDt) {
        this.operateDt = operateDt;
    }

    /**
     * @return 单补人
     */
    public String getOperateBy() {
        return operateBy;
    }

    /**
     * @param operateBy 
	 *            单补人
     */
    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy == null ? null : operateBy.trim();
    }

    /**
     * @return 版本ID
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            版本ID
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}