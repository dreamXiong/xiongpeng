package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbMerchNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商招公告Id
     */
    private Integer id;

    /**
     * 商招id
     */
    private Integer merchantid;

    /**
     * 招商公告名称
     */
    private String name;

    /**
     * 招商公告类型(290:招商公告,292:中标公告)
     */
    private Integer typeId;

    /**
     * 告公状态(280:激活,282:关闭)
     */
    private Integer status;

    /**
     * 品牌id
     */
    private Integer brandid;

    /**
     * 招商购买量
     */
    private Double number;

    /**
     * 优惠券
     */
    private Double coupons;

    /**
     * 招商层级
     */
    private Integer level;

    /**
     * 商招地址
     */
    private String address;

    /**
     * 面封图片
     */
    private String imgfile;

    /**
     * 发布人
     */
    private String createdby;

    /**
     * 发布时间
     */
    private Long createddatetime;

    /**
     * 本版号
     */
    private Integer version;

    /**
     * 招商公告详情
     */
    private String detail;

    /**
     * @return 商招公告Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            商招公告Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 商招id
     */
    public Integer getMerchantid() {
        return merchantid;
    }

    /**
     * @param merchantid 
	 *            商招id
     */
    public void setMerchantid(Integer merchantid) {
        this.merchantid = merchantid;
    }

    /**
     * @return 招商公告名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            招商公告名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 招商公告类型(290:招商公告,292:中标公告)
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId 
	 *            招商公告类型(290:招商公告,292:中标公告)
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * @return 告公状态(280:激活,282:关闭)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            告公状态(280:激活,282:关闭)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 品牌id
     */
    public Integer getBrandid() {
        return brandid;
    }

    /**
     * @param brandid 
	 *            品牌id
     */
    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    /**
     * @return 招商购买量
     */
    public Double getNumber() {
        return number;
    }

    /**
     * @param number 
	 *            招商购买量
     */
    public void setNumber(Double number) {
        this.number = number;
    }

    /**
     * @return 优惠券
     */
    public Double getCoupons() {
        return coupons;
    }

    /**
     * @param coupons 
	 *            优惠券
     */
    public void setCoupons(Double coupons) {
        this.coupons = coupons;
    }

    /**
     * @return 招商层级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level 
	 *            招商层级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return 商招地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            商招地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return 面封图片
     */
    public String getImgfile() {
        return imgfile;
    }

    /**
     * @param imgfile 
	 *            面封图片
     */
    public void setImgfile(String imgfile) {
        this.imgfile = imgfile == null ? null : imgfile.trim();
    }

    /**
     * @return 发布人
     */
    public String getCreatedby() {
        return createdby;
    }

    /**
     * @param createdby 
	 *            发布人
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
    }

    /**
     * @return 发布时间
     */
    public Long getCreateddatetime() {
        return createddatetime;
    }

    /**
     * @param createddatetime 
	 *            发布时间
     */
    public void setCreateddatetime(Long createddatetime) {
        this.createddatetime = createddatetime;
    }

    /**
     * @return 本版号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            本版号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return 招商公告详情
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail 
	 *            招商公告详情
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}