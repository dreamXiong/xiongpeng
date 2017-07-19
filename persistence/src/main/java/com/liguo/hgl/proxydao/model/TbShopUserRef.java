package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbShopUserRef implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 管理员ID
     */
    private Integer userId;
    /**
     * 管理员名称
     */
    private String userName;

    /**
     * 店铺名称
     */
    private String companyName;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 关系是否启用
     */
    private Integer status;

    /**
     * 备用字段1
     */
    private String remark1;

    /**
     * 备用字段2
     */
    private String remark2;
    
    /**
     * @return 店铺ID
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopid 
	 *            店铺ID
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 管理员ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            管理员ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return 管理员名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 
	 *            管理员名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return 店铺名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param shopName 
	 *            店铺名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * @return 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return 关系是否启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            关系是否启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 备用字段1
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * @param remark1 
	 *            备用字段1
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * @return 备用字段2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * @param remark2 
	 *            备用字段2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }
}