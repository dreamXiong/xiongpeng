package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbShopLevel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 等级id
     */
    private Integer id;

    /**
     * 等级名称
     */
    private String levelName;

    /**
     * 最小经验值
     */
    private Integer minExp;

    /**
     * 经验折算规则
     */
    private Double expProportion;

    /**
     * 用户优惠折扣
     */
    private Double userSale;

    /**
     * 优惠卷使用规则
     */
    private Double couponRule;

    /**
     * 体现次数
     */
    private Integer cashNum;

    /**
     * 等级说明
     */
    private String levelRemark;

    /**
     * 修改用户人id
     */
    private Integer updateBy;

    /**
     * 修改时间
     */
    private Long updateDt;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 最大经验值
     */
    private Integer maxExp;

    /**
     * @return 等级id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            等级id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 等级名称
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * @param levelName 
	 *            等级名称
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    /**
     * @return 最小经验值
     */
    public Integer getMinExp() {
        return minExp;
    }

    /**
     * @param minExp 
	 *            最小经验值
     */
    public void setMinExp(Integer minExp) {
        this.minExp = minExp;
    }

    /**
     * @return 经验折算规则
     */
    public Double getExpProportion() {
        return expProportion;
    }

    /**
     * @param expProportion 
	 *            经验折算规则
     */
    public void setExpProportion(Double expProportion) {
        this.expProportion = expProportion;
    }

    /**
     * @return 用户优惠折扣
     */
    public Double getUserSale() {
        return userSale;
    }

    /**
     * @param userSale 
	 *            用户优惠折扣
     */
    public void setUserSale(Double userSale) {
        this.userSale = userSale;
    }

    /**
     * @return 优惠卷使用规则
     */
    public Double getCouponRule() {
        return couponRule;
    }

    /**
     * @param couponRule 
	 *            优惠卷使用规则
     */
    public void setCouponRule(Double couponRule) {
        this.couponRule = couponRule;
    }

    /**
     * @return 体现次数
     */
    public Integer getCashNum() {
        return cashNum;
    }

    /**
     * @param cashNum 
	 *            体现次数
     */
    public void setCashNum(Integer cashNum) {
        this.cashNum = cashNum;
    }

    /**
     * @return 等级说明
     */
    public String getLevelRemark() {
        return levelRemark;
    }

    /**
     * @param levelRemark 
	 *            等级说明
     */
    public void setLevelRemark(String levelRemark) {
        this.levelRemark = levelRemark == null ? null : levelRemark.trim();
    }

    /**
     * @return 修改用户人id
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy 
	 *            修改用户人id
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return 修改时间
     */
    public Long getUpdateDt() {
        return updateDt;
    }

    /**
     * @param updateDt 
	 *            修改时间
     */
    public void setUpdateDt(Long updateDt) {
        this.updateDt = updateDt;
    }

    /**
     * @return 版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return 最大经验值
     */
    public Integer getMaxExp() {
        return maxExp;
    }

    /**
     * @param maxExp 
	 *            最大经验值
     */
    public void setMaxExp(Integer maxExp) {
        this.maxExp = maxExp;
    }
}