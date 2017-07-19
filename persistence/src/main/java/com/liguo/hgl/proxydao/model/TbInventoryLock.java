package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbInventoryLock implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 库存ID
     */
    private Integer inventoryId;

    /**
     * 订单ID
     */
    private Integer goodsDetailId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 锁定量
     */
    private Integer lockQuantity;

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
     * @return 库存ID
     */
    public Integer getInventoryId() {
        return inventoryId;
    }

    /**
     * @param inventoryId 
	 *            库存ID
     */
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * @return 订单ID
     */
    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    /**
     * @param goodsDetailId 
	 *            订单ID
     */
    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    /**
     * @return 订单状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus 
	 *            订单状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return 订单类型
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * @param orderType 
	 *            订单类型
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * @return 锁定量
     */
    public Integer getLockQuantity() {
        return lockQuantity;
    }

    /**
     * @param lockQuantity 
	 *            锁定量
     */
    public void setLockQuantity(Integer lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}