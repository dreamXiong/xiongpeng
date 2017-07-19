package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbUserGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 说明
     */
    private String remark;

    /**
     * 组名
     */
    private String name;

    /**
     * 折扣
     */
    private Integer discount;

    /**
     * 版本
     */
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
     * @return 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return 说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            说明
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return 组名
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            组名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 折扣
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * @param discount 
	 *            折扣
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
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