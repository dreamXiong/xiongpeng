package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbOrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer orderGroupId;

    private Integer inventoryId;

    /**
     * 提交订单时候的价格
     */
    private Double price;

    /**
     * 实际付款的价格，有可能会
     */
    private Double buyPrice;

    /**
     * 购买数量
     */
    private Integer buyNum;

    /**
     * 均重
     */
    private Double perWeight;

    /**
     * 该detail实际支付金额
     */
    private Double payMoney;

    /**
     * 该detail的总重量
     */
    private Double totalWeight;

    /**
     * 提交订单时该detail的金额
     */
    private Double money;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderGroupId() {
        return orderGroupId;
    }

    public void setOrderGroupId(Integer orderGroupId) {
        this.orderGroupId = orderGroupId;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * @return 提交订单时候的价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price 
	 *            提交订单时候的价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return 实际付款的价格，有可能会
     */
    public Double getBuyPrice() {
        return buyPrice;
    }

    /**
     * @param buyPrice 
	 *            实际付款的价格，有可能会
     */
    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * @return 购买数量
     */
    public Integer getBuyNum() {
        return buyNum;
    }

    /**
     * @param buyNum 
	 *            购买数量
     */
    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    /**
     * @return 均重
     */
    public Double getPerWeight() {
        return perWeight;
    }

    /**
     * @param perWeight 
	 *            均重
     */
    public void setPerWeight(Double perWeight) {
        this.perWeight = perWeight;
    }

    /**
     * @return 该detail实际支付金额
     */
    public Double getPayMoney() {
        return payMoney;
    }

    /**
     * @param payMoney 
	 *            该detail实际支付金额
     */
    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    /**
     * @return 该detail的总重量
     */
    public Double getTotalWeight() {
        return totalWeight;
    }

    /**
     * @param totalWeight 
	 *            该detail的总重量
     */
    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    /**
     * @return 提交订单时该detail的金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * @param money 
	 *            提交订单时该detail的金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}