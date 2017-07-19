package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbGroupDistribution implements Serializable {
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
     * 用户所属组ID
     */
    private String groupId;

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
     * @return 用户所属组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId 
	 *            用户所属组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
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