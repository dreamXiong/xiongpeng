package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbOrderTracking implements Serializable {
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
     * 经销商操作
     */
    private Integer operateBy;

    /**
     * 后台操作ID
     */
    private Integer operateByAdmin;

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
     * @return 经销商操作
     */
    public Integer getOperateBy() {
        return operateBy;
    }

    /**
     * @param operateBy 
	 *            经销商操作
     */
    public void setOperateBy(Integer operateBy) {
        this.operateBy = operateBy;
    }

    /**
     * @return 后台操作ID
     */
    public Integer getOperateByAdmin() {
        return operateByAdmin;
    }

    /**
     * @param operateByAdmin 
	 *            后台操作ID
     */
    public void setOperateByAdmin(Integer operateByAdmin) {
        this.operateByAdmin = operateByAdmin;
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