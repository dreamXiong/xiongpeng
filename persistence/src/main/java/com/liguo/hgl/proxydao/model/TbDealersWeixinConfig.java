package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbDealersWeixinConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 店铺Id
     */
    private Integer shopId;

    /**
     * 公众号
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String appKey;

    /**
     * 回调url
     */
    private String notifyUrl;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 版本号
     */
    private Integer version;

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
     * @return 店铺Id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            店铺Id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 公众号
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId 
	 *            公众号
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * @return 商户号
     */
    public String getMchId() {
        return mchId;
    }

    /**
     * @param mchId 
	 *            商户号
     */
    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    /**
     * @return 商户密钥
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * @param appKey 
	 *            商户密钥
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    /**
     * @return 回调url
     */
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /**
     * @param notifyUrl 
	 *            回调url
     */
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    /**
     * @return 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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
}