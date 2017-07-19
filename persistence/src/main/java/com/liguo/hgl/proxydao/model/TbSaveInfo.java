package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbSaveInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 动自增长的id
     */
    private Integer id;

    /**
     * 藏收的产品、店铺id
     */
    private Integer saveId;

    /**
     * 藏收的店铺、产品名称
     */
    private String saveName;

    private Integer brandId;

    private String brandName;

    /**
     * 藏收类型(270:店铺收藏,272:产品收藏)
     */
    private Integer saveType;

    /**
     * 藏收的链接地址
     */
    private String saveUrl;

    /**
     * 藏收的产品、店铺图片
     */
    private String saveImage;

    /**
     * 品产价格
     */
    private Double price;

    /**
     * 藏收时间
     */
    private Long createDt;

    /**
     * 收藏人
     */
    private Integer createBy;

    /**
     * 是否为绑定店铺(是绑定1,非绑定0)
     */
    private Integer bind;

    /**
     * 本版id
     */
    private Integer version;

    /**
     * @return 动自增长的id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            动自增长的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 藏收的产品、店铺id
     */
    public Integer getSaveId() {
        return saveId;
    }

    /**
     * @param saveId 
	 *            藏收的产品、店铺id
     */
    public void setSaveId(Integer saveId) {
        this.saveId = saveId;
    }

    /**
     * @return 藏收的店铺、产品名称
     */
    public String getSaveName() {
        return saveName;
    }

    /**
     * @param saveName 
	 *            藏收的店铺、产品名称
     */
    public void setSaveName(String saveName) {
        this.saveName = saveName == null ? null : saveName.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * @return 藏收类型(270:店铺收藏,272:产品收藏)
     */
    public Integer getSaveType() {
        return saveType;
    }

    /**
     * @param saveType 
	 *            藏收类型(270:店铺收藏,272:产品收藏)
     */
    public void setSaveType(Integer saveType) {
        this.saveType = saveType;
    }

    /**
     * @return 藏收的链接地址
     */
    public String getSaveUrl() {
        return saveUrl;
    }

    /**
     * @param saveUrl 
	 *            藏收的链接地址
     */
    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl == null ? null : saveUrl.trim();
    }

    /**
     * @return 藏收的产品、店铺图片
     */
    public String getSaveImage() {
        return saveImage;
    }

    /**
     * @param saveImage 
	 *            藏收的产品、店铺图片
     */
    public void setSaveImage(String saveImage) {
        this.saveImage = saveImage == null ? null : saveImage.trim();
    }

    /**
     * @return 品产价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price 
	 *            品产价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return 藏收时间
     */
    public Long getCreateDt() {
        return createDt;
    }

    /**
     * @param createDt 
	 *            藏收时间
     */
    public void setCreateDt(Long createDt) {
        this.createDt = createDt;
    }

    /**
     * @return 收藏人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy 
	 *            收藏人
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * @return 是否为绑定店铺(是绑定1,非绑定0)
     */
    public Integer getBind() {
        return bind;
    }

    /**
     * @param bind 
	 *            是否为绑定店铺(是绑定1,非绑定0)
     */
    public void setBind(Integer bind) {
        this.bind = bind;
    }

    /**
     * @return 本版id
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            本版id
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}