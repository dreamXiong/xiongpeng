package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbDeliveryTerms implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表id,自增主键
     */
    private Integer id;

    /**
     * 铺店id
     */
    private Integer shopId;

    /**
     * 描述
     */
    private String description;

    /**
     * 计算方式(按距离:410,按金额:412)
     */
    private Integer calcMethod;

    /**
     * 起始距离(单位千米)
     */
    private Double minDistance;

    /**
     * 结束距离(单位千米)
     */
    private Double maxDistance;

    /**
     * 起始金额(单位元)
     */
    private Double minAmount;

    /**
     * 结束金额(单位元)
     */
    private Double maxAmount;

    /**
     * 运费
     */
    private Double freight;

    private Integer version;

    /**
     * @return 表id,自增主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            表id,自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 铺店id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            铺店id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description 
	 *            描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * @return 计算方式(按距离:410,按金额:412)
     */
    public Integer getCalcMethod() {
        return calcMethod;
    }

    /**
     * @param calcMethod 
	 *            计算方式(按距离:410,按金额:412)
     */
    public void setCalcMethod(Integer calcMethod) {
        this.calcMethod = calcMethod;
    }

    /**
     * @return 起始距离(单位千米)
     */
    public Double getMinDistance() {
        return minDistance;
    }

    /**
     * @param minDistance 
	 *            起始距离(单位千米)
     */
    public void setMinDistance(Double minDistance) {
        this.minDistance = minDistance;
    }

    /**
     * @return 结束距离(单位千米)
     */
    public Double getMaxDistance() {
        return maxDistance;
    }

    /**
     * @param maxDistance 
	 *            结束距离(单位千米)
     */
    public void setMaxDistance(Double maxDistance) {
        this.maxDistance = maxDistance;
    }

    /**
     * @return 起始金额(单位元)
     */
    public Double getMinAmount() {
        return minAmount;
    }

    /**
     * @param minAmount 
	 *            起始金额(单位元)
     */
    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * @return 结束金额(单位元)
     */
    public Double getMaxAmount() {
        return maxAmount;
    }

    /**
     * @param maxAmount 
	 *            结束金额(单位元)
     */
    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * @return 运费
     */
    public Double getFreight() {
        return freight;
    }

    /**
     * @param freight 
	 *            运费
     */
    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}