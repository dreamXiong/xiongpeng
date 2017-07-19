package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品名id
     */
    private Integer id;

    /**
     * 品名
     */
    private String name;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 产品价格
     */
    private Float price;

    /**
     * 分类id
     */
    private Integer productTypeId;

    /**
     * 销量
     */
    private Integer saleNum;

    /**
     * 附加属性
     */
    private String attributes;

    /**
     * 产品描述
     */
    private String describes;

    /**
     * 产品图片1
     */
    private String pimgOne;

    /**
     * 产品图片2
     */
    private String pimgTwo;

    /**
     * 产品图片3
     */
    private String pimgThree;

    /**
     * 描述图片1
     */
    private String dimgOne;

    /**
     * 描述图片2
     */
    private String dimgTwo;

    /**
     * 描述图片3
     */
    private String dimgThree;

    /**
     * 计量单位
     */
    private String meterageUnit;

    /**
     * 装箱数
     */
    private Integer pickNo;

    private Integer version;

    /**
     * @return 品名id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            品名id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 品名
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            品名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * @return 产品价格
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price 
	 *            产品价格
     */
    public void setPrice(Float price) {
        this.price = price;
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

    /**
     * @return 销量
     */
    public Integer getSaleNum() {
        return saleNum;
    }

    /**
     * @param saleNum 
	 *            销量
     */
    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    /**
     * @return 附加属性
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * @param attributes 
	 *            附加属性
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes == null ? null : attributes.trim();
    }

    /**
     * @return 产品描述
     */
    public String getDescribes() {
        return describes;
    }

    /**
     * @param describes 
	 *            产品描述
     */
    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    /**
     * @return 产品图片1
     */
    public String getPimgOne() {
        return pimgOne;
    }

    /**
     * @param pimgOne 
	 *            产品图片1
     */
    public void setPimgOne(String pimgOne) {
        this.pimgOne = pimgOne == null ? null : pimgOne.trim();
    }

    /**
     * @return 产品图片2
     */
    public String getPimgTwo() {
        return pimgTwo;
    }

    /**
     * @param pimgTwo 
	 *            产品图片2
     */
    public void setPimgTwo(String pimgTwo) {
        this.pimgTwo = pimgTwo == null ? null : pimgTwo.trim();
    }

    /**
     * @return 产品图片3
     */
    public String getPimgThree() {
        return pimgThree;
    }

    /**
     * @param pimgThree 
	 *            产品图片3
     */
    public void setPimgThree(String pimgThree) {
        this.pimgThree = pimgThree == null ? null : pimgThree.trim();
    }

    /**
     * @return 描述图片1
     */
    public String getDimgOne() {
        return dimgOne;
    }

    /**
     * @param dimgOne 
	 *            描述图片1
     */
    public void setDimgOne(String dimgOne) {
        this.dimgOne = dimgOne == null ? null : dimgOne.trim();
    }

    /**
     * @return 描述图片2
     */
    public String getDimgTwo() {
        return dimgTwo;
    }

    /**
     * @param dimgTwo 
	 *            描述图片2
     */
    public void setDimgTwo(String dimgTwo) {
        this.dimgTwo = dimgTwo == null ? null : dimgTwo.trim();
    }

    /**
     * @return 描述图片3
     */
    public String getDimgThree() {
        return dimgThree;
    }

    /**
     * @param dimgThree 
	 *            描述图片3
     */
    public void setDimgThree(String dimgThree) {
        this.dimgThree = dimgThree == null ? null : dimgThree.trim();
    }

    /**
     * @return 计量单位
     */
    public String getMeterageUnit() {
        return meterageUnit;
    }

    /**
     * @param meterageUnit 
	 *            计量单位
     */
    public void setMeterageUnit(String meterageUnit) {
        this.meterageUnit = meterageUnit == null ? null : meterageUnit.trim();
    }

    /**
     * @return 装箱数
     */
    public Integer getPickNo() {
        return pickNo;
    }

    /**
     * @param pickNo 
	 *            装箱数
     */
    public void setPickNo(Integer pickNo) {
        this.pickNo = pickNo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}