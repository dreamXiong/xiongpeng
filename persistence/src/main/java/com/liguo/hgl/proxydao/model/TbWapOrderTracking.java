package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWapOrderTracking implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderGroupId;

    /**
     * 订单状态
     */
    private Integer orderState;

    private String operateName;

    /**
     * 操作人
     */
    private Integer operateBy;

    /**
     * 操作时间
     */
    private Long operateDt;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 订单ID
     */
    public Integer getOrderGroupId() {
        return orderGroupId;
    }

    /**
     * @param orderGroupId 
	 *            订单ID
     */
    public void setOrderGroupId(Integer orderGroupId) {
        this.orderGroupId = orderGroupId;
    }

    /**
     * @return 订单状态
     */
    public Integer getOrderState() {
        return orderState;
    }

    /**
     * @param orderState 
	 *            订单状态
     */
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName == null ? null : operateName.trim();
    }

    /**
     * @return 操作人
     */
    public Integer getOperateBy() {
        return operateBy;
    }

    /**
     * @param operateBy 
	 *            操作人
     */
    public void setOperateBy(Integer operateBy) {
        this.operateBy = operateBy;
    }

    /**
     * @return 操作时间
     */
    public Long getOperateDt() {
        return operateDt;
    }

    /**
     * @param operateDt 
	 *            操作时间
     */
    public void setOperateDt(Long operateDt) {
        this.operateDt = operateDt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}