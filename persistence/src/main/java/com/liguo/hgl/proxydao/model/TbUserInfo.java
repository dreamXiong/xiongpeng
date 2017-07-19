package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 用户类型
     */
    private Integer shopType;

    /**
     * 等级
     */
    private Integer expLevel;

    /**
     * 产生经验值
     */
    private Integer exp;

    /**
     * 注册省
     */
    private Integer regProvince;

    /**
     * 注册市
     */
    private Integer regCity;

    /**
     * 注册区县
     */
    private Integer regCountry;

    /**
     * 注册街道
     */
    private Integer regStreet;

    /**
     * 具体地址
     */
    private String regAddress;

    /**
     * 销售量
     */
    private Integer sales;

    /**
     * 营业执照图片
     */
    private String image;

    /**
     * 备注,扩展字段
     */
    private String remark;

    /**
     * 审核状态
     */
    private Integer authStatus;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 出生年
     */
    private Integer birthYear;

    /**
     * 生出月
     */
    private Integer birthMonth;

    /**
     * 生出日
     */
    private Integer birthDay;

    /**
     * 师傅的服务项目
     */
    private String serviceType;

    /**
     * 身份证正面照
     */
    private String imageFace;

    /**
     * 身份证反面照
     */
    private String iamgeBack;

    /**
     * Male:232,Female:234,unknow:236
     */
    private Integer gender;

    /**
     * 账户ID
     */
    private Integer accountId;

    private String feedBack;

    private Integer version;
    
    /***
     * 师傅的评价平均分
     */
    private String evaluateNumAvg;

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
     * @return 用户类型
     */
    public Integer getShopType() {
        return shopType;
    }

    /**
     * @param shopType 
	 *            用户类型
     */
    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    /**
     * @return 等级
     */
    public Integer getExpLevel() {
        return expLevel;
    }

    /**
     * @param expLevel 
	 *            等级
     */
    public void setExpLevel(Integer expLevel) {
        this.expLevel = expLevel;
    }

    /**
     * @return 产生经验值
     */
    public Integer getExp() {
        return exp;
    }

    /**
     * @param exp 
	 *            产生经验值
     */
    public void setExp(Integer exp) {
        this.exp = exp;
    }

    /**
     * @return 注册省
     */
    public Integer getRegProvince() {
        return regProvince;
    }

    /**
     * @param regProvince 
	 *            注册省
     */
    public void setRegProvince(Integer regProvince) {
        this.regProvince = regProvince;
    }

    /**
     * @return 注册市
     */
    public Integer getRegCity() {
        return regCity;
    }

    /**
     * @param regCity 
	 *            注册市
     */
    public void setRegCity(Integer regCity) {
        this.regCity = regCity;
    }

    /**
     * @return 注册区县
     */
    public Integer getRegCountry() {
        return regCountry;
    }

    /**
     * @param regCountry 
	 *            注册区县
     */
    public void setRegCountry(Integer regCountry) {
        this.regCountry = regCountry;
    }

    /**
     * @return 注册街道
     */
    public Integer getRegStreet() {
        return regStreet;
    }

    /**
     * @param regStreet 
	 *            注册街道
     */
    public void setRegStreet(Integer regStreet) {
        this.regStreet = regStreet;
    }

    /**
     * @return 具体地址
     */
    public String getRegAddress() {
        return regAddress;
    }

    /**
     * @param regAddress 
	 *            具体地址
     */
    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress == null ? null : regAddress.trim();
    }

    /**
     * @return 销售量
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * @param sales 
	 *            销售量
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * @return 营业执照图片
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image 
	 *            营业执照图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * @return 备注,扩展字段
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注,扩展字段
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return 审核状态
     */
    public Integer getAuthStatus() {
        return authStatus;
    }

    /**
     * @param authStatus 
	 *            审核状态
     */
    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
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
     * @return 出生年
     */
    public Integer getBirthYear() {
        return birthYear;
    }

    /**
     * @param birthYear 
	 *            出生年
     */
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * @return 生出月
     */
    public Integer getBirthMonth() {
        return birthMonth;
    }

    /**
     * @param birthMonth 
	 *            生出月
     */
    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    /**
     * @return 生出日
     */
    public Integer getBirthDay() {
        return birthDay;
    }

    /**
     * @param birthDay 
	 *            生出日
     */
    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * @return 师傅的服务项目
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType 
	 *            师傅的服务项目
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    /**
     * @return 身份证正面照
     */
    public String getImageFace() {
        return imageFace;
    }

    /**
     * @param imageFace 
	 *            身份证正面照
     */
    public void setImageFace(String imageFace) {
        this.imageFace = imageFace == null ? null : imageFace.trim();
    }

    /**
     * @return 身份证反面照
     */
    public String getIamgeBack() {
        return iamgeBack;
    }

    /**
     * @param iamgeBack 
	 *            身份证反面照
     */
    public void setIamgeBack(String iamgeBack) {
        this.iamgeBack = iamgeBack == null ? null : iamgeBack.trim();
    }

    /**
     * @return Male:232,Female:234,unknow:236
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender 
	 *            Male:232,Female:234,unknow:236
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return 账户ID
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @param accountId 
	 *            账户ID
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack == null ? null : feedBack.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getEvaluateNumAvg() {
        return evaluateNumAvg;
    }

    public void setEvaluateNumAvg(String evaluateNumAvg) {
        this.evaluateNumAvg = evaluateNumAvg == null ? null : evaluateNumAvg.trim();
    }
    
    
}