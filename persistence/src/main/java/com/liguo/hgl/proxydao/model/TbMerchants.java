package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbMerchants implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 招商id
     */
    private Integer id;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 招商层次
     */
    private Integer level;

    /**
     * 招商购买量
     */
    private Double number;

    /**
     * 其他
     */
    private String other;

    /**
     * 优惠卷
     */
    private Double coupons;

    /**
     * 招商区域
     */
    private Integer address;

    /**
     * 围观商家
     */
    private Integer views;

    /**
     * 已中标商家
     */
    private Integer winning;

    /**
     * 参与商家
     */
    private Integer participate;

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
     * 招商位数
     */
    private Integer places;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 预留字段int
     */
    private Integer reserved;

    /**
     * 预留字段String
     */
    private String reserved1;

    private Integer version;

    private Long createTime;

    private Long releaseTime;

    private Long lastUpdateTime;

    /**
     * @return 招商id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            招商id
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
     * @return 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * @return 招商层次
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level 
	 *            招商层次
     */
    public void setLevel(Integer level) {
        this.level = level;
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
     * @return 其他
     */
    public String getOther() {
        return other;
    }

    /**
     * @param other 
	 *            其他
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    /**
     * @return 优惠卷
     */
    public Double getCoupons() {
        return coupons;
    }

    /**
     * @param coupons 
	 *            优惠卷
     */
    public void setCoupons(Double coupons) {
        this.coupons = coupons;
    }

    /**
     * @return 招商区域
     */
    public Integer getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            招商区域
     */
    public void setAddress(Integer address) {
        this.address = address;
    }

    /**
     * @return 围观商家
     */
    public Integer getViews() {
        return views;
    }

    /**
     * @param views 
	 *            围观商家
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * @return 已中标商家
     */
    public Integer getWinning() {
        return winning;
    }

    /**
     * @param winning 
	 *            已中标商家
     */
    public void setWinning(Integer winning) {
        this.winning = winning;
    }

    /**
     * @return 参与商家
     */
    public Integer getParticipate() {
        return participate;
    }

    /**
     * @param participate 
	 *            参与商家
     */
    public void setParticipate(Integer participate) {
        this.participate = participate;
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
     * @return 招商位数
     */
    public Integer getPlaces() {
        return places;
    }

    /**
     * @param places 
	 *            招商位数
     */
    public void setPlaces(Integer places) {
        this.places = places;
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

    /**
     * @return 预留字段String
     */
    public String getReserved1() {
        return reserved1;
    }

    /**
     * @param reserved1 
	 *            预留字段String
     */
    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}