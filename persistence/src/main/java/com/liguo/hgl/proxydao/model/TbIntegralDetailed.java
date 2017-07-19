package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbIntegralDetailed implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 积分ID
     */
    private Integer integralId;

    /**
     * 操作时间
     */
    private Long opearDt;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 订单号
     */
    private String orderSerialNo;

    /**
     * 所得积分
     */
    private Integer integral;

    /**
     * 1:支付积分，2：返利积分
     */
    private Integer type;

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
     * @return 积分ID
     */
    public Integer getIntegralId() {
        return integralId;
    }

    /**
     * @param integralId 
	 *            积分ID
     */
    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
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
     * @return 所得积分
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * @param integral 
	 *            所得积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * @return 1:支付积分，2：返利积分
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            1:支付积分，2：返利积分
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}