package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbAgency implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 代理id
     */
    private Integer id;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 招商id
     */
    private Integer merchantId;

    /**
     * 经销商id
     */
    private Integer shopId;

    /**
     * 商品购买订单号
     */
    private String orderId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 代理层级
     */
    private Integer expLevel;

    /**
     * 招商购买量
     */
    private Double number;

    /**
     * 招商服务费
     */
    private Double coupons;

    /**
     * 服务费状态
     */
    private Integer couponsState;

    /**
     * 代理区域
     */
    private Integer address;

    /**
     * 省
     */
    private Integer province;

    /**
     * 市
     */
    private Integer city;

    /**
     * 区
     */
    private Integer country;

    /**
     * 街镇
     */
    private Integer street;

    /**
     * 预留字段int
     */
    private Integer reserved;

    private Integer version;

    /**
     * 备注,扩展字段
     */
    private String remark;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    private String recommendCode;

    /**
     * 订单状态
     */
    private Integer orderState;

    /**
     * 服务费订单号
     */
    private String couponsOrderNumber;

    /**
     * @return 代理id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            代理id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 品牌id
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * @param brandId 
	 *            品牌id
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * @return 招商id
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId 
	 *            招商id
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return 经销商id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            经销商id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 商品购买订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId 
	 *            商品购买订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * @return 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 代理层级
     */
    public Integer getExpLevel() {
        return expLevel;
    }

    /**
     * @param expLevel 
	 *            代理层级
     */
    public void setExpLevel(Integer expLevel) {
        this.expLevel = expLevel;
    }

    /**
     * @return 招商购买量
     */
    public Double getNumber() {
        return number;
    }

    /**
     * @param number 
	 *            招商购买量
     */
    public void setNumber(Double number) {
        this.number = number;
    }

    /**
     * @return 招商服务费
     */
    public Double getCoupons() {
        return coupons;
    }

    /**
     * @param coupons 
	 *            招商服务费
     */
    public void setCoupons(Double coupons) {
        this.coupons = coupons;
    }

    /**
     * @return 服务费状态
     */
    public Integer getCouponsState() {
        return couponsState;
    }

    /**
     * @param couponsState 
	 *            服务费状态
     */
    public void setCouponsState(Integer couponsState) {
        this.couponsState = couponsState;
    }

    /**
     * @return 代理区域
     */
    public Integer getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            代理区域
     */
    public void setAddress(Integer address) {
        this.address = address;
    }

    /**
     * @return 省
     */
    public Integer getProvince() {
        return province;
    }

    /**
     * @param province 
	 *            省
     */
    public void setProvince(Integer province) {
        this.province = province;
    }

    /**
     * @return 市
     */
    public Integer getCity() {
        return city;
    }

    /**
     * @param city 
	 *            市
     */
    public void setCity(Integer city) {
        this.city = city;
    }

    /**
     * @return 区
     */
    public Integer getCountry() {
        return country;
    }

    /**
     * @param country 
	 *            区
     */
    public void setCountry(Integer country) {
        this.country = country;
    }

    /**
     * @return 街镇
     */
    public Integer getStreet() {
        return street;
    }

    /**
     * @param street 
	 *            街镇
     */
    public void setStreet(Integer street) {
        this.street = street;
    }

    /**
     * @return 预留字段int
     */
    public Integer getReserved() {
        return reserved;
    }

    /**
     * @param reserved 
	 *            预留字段int
     */
    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return 备注,扩展字段
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注,扩展字段
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state 
	 *            状态
     */
    public void setState(Integer state) {
        this.state = state;
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

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode == null ? null : recommendCode.trim();
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

    /**
     * @return 服务费订单号
     */
    public String getCouponsOrderNumber() {
        return couponsOrderNumber;
    }

    /**
     * @param couponsOrderNumber 
	 *            服务费订单号
     */
    public void setCouponsOrderNumber(String couponsOrderNumber) {
        this.couponsOrderNumber = couponsOrderNumber == null ? null : couponsOrderNumber.trim();
    }
}