package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbRecommended implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 推荐id
     */
    private Integer id;

    /**
     * 推荐人Id
     */
    private Integer recommended;

    /**
     * 被推荐人Id
     */
    private Integer isRecommended;

    /**
     * 推荐人类型
     */
    private Integer recommendedType;

    /**
     * 被推荐人类型
     */
    private Integer isRecommendedType;

    /**
     * 推荐类型规则表0代表平台，1代表经销商
     */
    private Integer typeRecommend;

    /**
     * 推荐之间的相互联系类型Id
     */
    private Integer recommendedContactTypeId;

    /**
     * 收益类型：0 单位为积分 1单位为元
     */
    private Integer type;

    /**
     * 收益
     */
    private Double earnings;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 首单Id
     */
    private Integer firstOrderId;

    /**
     * 首单金额
     */
    private Double money;

    /**
     * 预留
     */
    private Integer reserved;

    /**
     * 描述
     */
    private String describes;

    /**
     * 创建时间
     */
    private Long createDt;

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
     * @return 推荐id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            推荐id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 推荐人Id
     */
    public Integer getRecommended() {
        return recommended;
    }

    /**
     * @param recommended 
	 *            推荐人Id
     */
    public void setRecommended(Integer recommended) {
        this.recommended = recommended;
    }

    /**
     * @return 被推荐人Id
     */
    public Integer getIsRecommended() {
        return isRecommended;
    }

    /**
     * @param isRecommended 
	 *            被推荐人Id
     */
    public void setIsRecommended(Integer isRecommended) {
        this.isRecommended = isRecommended;
    }

    /**
     * @return 推荐人类型
     */
    public Integer getRecommendedType() {
        return recommendedType;
    }

    /**
     * @param recommendedType 
	 *            推荐人类型
     */
    public void setRecommendedType(Integer recommendedType) {
        this.recommendedType = recommendedType;
    }

    /**
     * @return 被推荐人类型
     */
    public Integer getIsRecommendedType() {
        return isRecommendedType;
    }

    /**
     * @param isRecommendedType 
	 *            被推荐人类型
     */
    public void setIsRecommendedType(Integer isRecommendedType) {
        this.isRecommendedType = isRecommendedType;
    }

    /**
     * @return 推荐类型规则表0代表平台，1代表经销商
     */
    public Integer getTypeRecommend() {
        return typeRecommend;
    }

    /**
     * @param typeRecommend 
	 *            推荐类型规则表0代表平台，1代表经销商
     */
    public void setTypeRecommend(Integer typeRecommend) {
        this.typeRecommend = typeRecommend;
    }

    /**
     * @return 推荐之间的相互联系类型Id
     */
    public Integer getRecommendedContactTypeId() {
        return recommendedContactTypeId;
    }

    /**
     * @param recommendedContactTypeId 
	 *            推荐之间的相互联系类型Id
     */
    public void setRecommendedContactTypeId(Integer recommendedContactTypeId) {
        this.recommendedContactTypeId = recommendedContactTypeId;
    }

    /**
     * @return 收益类型：0 单位为积分 1单位为元
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            收益类型：0 单位为积分 1单位为元
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 收益
     */
    public Double getEarnings() {
        return earnings;
    }

    /**
     * @param earnings 
	 *            收益
     */
    public void setEarnings(Double earnings) {
        this.earnings = earnings;
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
     * @return 首单Id
     */
    public Integer getFirstOrderId() {
        return firstOrderId;
    }

    /**
     * @param firstOrderId 
	 *            首单Id
     */
    public void setFirstOrderId(Integer firstOrderId) {
        this.firstOrderId = firstOrderId;
    }

    /**
     * @return 首单金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * @param money 
	 *            首单金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * @return 预留
     */
    public Integer getReserved() {
        return reserved;
    }

    /**
     * @param reserved 
	 *            预留
     */
    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    /**
     * @return 描述
     */
    public String getDescribes() {
        return describes;
    }

    /**
     * @param describes 
	 *            描述
     */
    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
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
}