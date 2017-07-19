package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbGoodsProfit implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 库存ID
     */
    private Integer inventoryId;

    /**
     * 库存名称
     */
    private String inventoryName;

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 出库总价格
     */
    private Double sumOutstockPrice;

    /**
     * 入库总价格
     */
    private Double sumInstockPrice;

    /**
     * 库存销量
     */
    private Integer saleNum;

    /**
     * 库存量
     */
    private Integer saleInventory;

    /**
     * 销售额
     */
    private Double saleMoney;

    /**
     * 盈利额
     */
    private Double profitMoney;

    /**
     * 盈利百分比
     */
    private Double profitProportion;

    /**
     * 利润率
     */
    private Double profitsRate;

    /**
     * 预计可销售天数
     */
    private Integer saleDay;

    /**
     * 创建时间
     */
    private Long createDt;

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
     * @return 库存名称
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * @param inventoryName 
	 *            库存名称
     */
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName == null ? null : inventoryName.trim();
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
     * @return 出库总价格
     */
    public Double getSumOutstockPrice() {
        return sumOutstockPrice;
    }

    /**
     * @param sumOutstockPrice 
	 *            出库总价格
     */
    public void setSumOutstockPrice(Double sumOutstockPrice) {
        this.sumOutstockPrice = sumOutstockPrice;
    }

    /**
     * @return 入库总价格
     */
    public Double getSumInstockPrice() {
        return sumInstockPrice;
    }

    /**
     * @param sumInstockPrice 
	 *            入库总价格
     */
    public void setSumInstockPrice(Double sumInstockPrice) {
        this.sumInstockPrice = sumInstockPrice;
    }

    /**
     * @return 库存销量
     */
    public Integer getSaleNum() {
        return saleNum;
    }

    /**
     * @param saleNum 
	 *            库存销量
     */
    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    /**
     * @return 库存量
     */
    public Integer getSaleInventory() {
        return saleInventory;
    }

    /**
     * @param saleInventory 
	 *            库存量
     */
    public void setSaleInventory(Integer saleInventory) {
        this.saleInventory = saleInventory;
    }

    /**
     * @return 销售额
     */
    public Double getSaleMoney() {
        return saleMoney;
    }

    /**
     * @param saleMoney 
	 *            销售额
     */
    public void setSaleMoney(Double saleMoney) {
        this.saleMoney = saleMoney;
    }

    /**
     * @return 盈利额
     */
    public Double getProfitMoney() {
        return profitMoney;
    }

    /**
     * @param profitMoney 
	 *            盈利额
     */
    public void setProfitMoney(Double profitMoney) {
        this.profitMoney = profitMoney;
    }

    /**
     * @return 盈利百分比
     */
    public Double getProfitProportion() {
        return profitProportion;
    }

    /**
     * @param profitProportion 
	 *            盈利百分比
     */
    public void setProfitProportion(Double profitProportion) {
        this.profitProportion = profitProportion;
    }

    /**
     * @return 利润率
     */
    public Double getProfitsRate() {
        return profitsRate;
    }

    /**
     * @param profitsRate 
	 *            利润率
     */
    public void setProfitsRate(Double profitsRate) {
        this.profitsRate = profitsRate;
    }

    /**
     * @return 预计可销售天数
     */
    public Integer getSaleDay() {
        return saleDay;
    }

    /**
     * @param saleDay 
	 *            预计可销售天数
     */
    public void setSaleDay(Integer saleDay) {
        this.saleDay = saleDay;
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
}