package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbRecommendedType implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 推荐类型id
     */
    private Integer id;

    /**
     * 推荐人类型
     */
    private Integer recommendedType;

    /**
     * 被推荐人类型
     */
    private Integer isRecommendedType;

    /**
     * 方式：0 直接给予，1，经予
     */
    private Integer way;

    /**
     * 奖励类型：0 积分，1金额
     */
    private Integer rewardType;

    /**
     * 奖励方式：0 百分比，1固定
     */
    private Integer rewardWay;

    /**
     * 奖励
     */
    private Integer reward;

    /**
     * 描述
     */
    private String describes;

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
     * @return 推荐类型id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            推荐类型id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return 方式：0 直接给予，1，经予
     */
    public Integer getWay() {
        return way;
    }

    /**
     * @param way 
	 *            方式：0 直接给予，1，经予
     */
    public void setWay(Integer way) {
        this.way = way;
    }

    /**
     * @return 奖励类型：0 积分，1金额
     */
    public Integer getRewardType() {
        return rewardType;
    }

    /**
     * @param rewardType 
	 *            奖励类型：0 积分，1金额
     */
    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    /**
     * @return 奖励方式：0 百分比，1固定
     */
    public Integer getRewardWay() {
        return rewardWay;
    }

    /**
     * @param rewardWay 
	 *            奖励方式：0 百分比，1固定
     */
    public void setRewardWay(Integer rewardWay) {
        this.rewardWay = rewardWay;
    }

    /**
     * @return 奖励
     */
    public Integer getReward() {
        return reward;
    }

    /**
     * @param reward 
	 *            奖励
     */
    public void setReward(Integer reward) {
        this.reward = reward;
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