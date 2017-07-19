package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbBrand implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌名称
     */
    private String logoName;

    /**
     * 品牌网址
     */
    private String url;

    /**
     * 状态：0是显示 1是隐藏
     */
    private Integer state;

    /**
     * 厂商id
     */
    private Integer manufacturerId;

    /**
     * 厂家名称
     */
    private String manufacturerName;

    /**
     * 品牌描述
     */
    private String remark;

    /**
     * 分类id
     */
    private Integer productTypeId;

    private String productTypeName;

    /**
     * 排序
     */
    private Integer sort;

    private Integer version;

    /**
     * 类型 0 厂家 1 自有
     */
    private Integer type;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * @return 品牌id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            品牌id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 品牌名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            品牌名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 品牌名称
     */
    public String getLogoName() {
        return logoName;
    }

    /**
     * @param logoName 
	 *            品牌名称
     */
    public void setLogoName(String logoName) {
        this.logoName = logoName == null ? null : logoName.trim();
    }

    /**
     * @return 品牌网址
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url 
	 *            品牌网址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return 状态：0是显示 1是隐藏
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state 
	 *            状态：0是显示 1是隐藏
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return 厂商id
     */
    public Integer getManufacturerId() {
        return manufacturerId;
    }

    /**
     * @param manufacturerId 
	 *            厂商id
     */
    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * @return 厂家名称
     */
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * @param manufacturerName 
	 *            厂家名称
     */
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName == null ? null : manufacturerName.trim();
    }

    /**
     * @return 品牌描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            品牌描述
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return 分类id
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId 
	 *            分类id
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName == null ? null : productTypeName.trim();
    }

    /**
     * @return 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort 
	 *            排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return 类型 0 厂家 1 自有
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型 0 厂家 1 自有
     */
    public void setType(Integer type) {
        this.type = type;
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
}