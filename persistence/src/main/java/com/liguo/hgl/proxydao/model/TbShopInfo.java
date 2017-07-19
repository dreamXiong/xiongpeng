package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbShopInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 店铺id
     */
    private Integer id;

    /**
     * 厂家:102，经销商：104
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
     * 店铺名称
     */
    private String shopName;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 联系人
     */
    private String contract;

    /**
     * 联系人联系方式
     */
    private String contractPhone;

    /**
     * 公司座机
     */
    private String companyTel;

    /**
     * 法人代表
     */
    private String legalRepresentative;

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
     * 经营的分类id
     */
    private Integer productTypeId;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 经营范围
     */
    private String scope;

    /**
     * 销售量
     */
    private Integer sales;

    /**
     * 一般纳税人图片
     */
    private String taxpayerImage;

    /**
     * 营业执照图片
     */
    private String licenseImage;

    /**
     * 组织机构代码证图片
     */
    private String organizationImage;

    /**
     * 实景图片
     */
    private String shopImage1;

    /**
     * 实景图片
     */
    private String shopImage2;

    /**
     * 实景图片
     */
    private String shopImage3;

    /**
     * 公司名称
     */
    private String adsImage;

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
     * 惠券优收入金额
     */
    private Double couponGainAmt;

    /**
     * 惠券优支出金额
     */
    private Double couponEmployAmt;

    /**
     * 优惠券余额
     */
    private Double couponRemainingAmt;

    /**
     * 店铺结算预存状态
     */
    private Integer balance;

    /**
     * 店铺的纵坐标
     */
    private Double lon;

    /**
     * 店铺的横坐标
     */
    private Double lat;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 账户ID
     */
    private Integer accountId;

    /**
     * 是否自动确认订单：0 否，1 是
     */
    private Integer isAutomaticOrder;

    /**
     * 平台结算：1，自结算：2
     */
    private Integer settlement;

    /**
     * 返利百分比
     */
    private Double rebate;

    /**
     * 等级
     */
    private String grade;

    /**
     * 师傅的服务项目
     */
    private String serviceType;

    /**
     * 金牌代理商权限标示0:未开通,1:未审核,2:已开通
     */
    private Integer medalAgentFlag;
    
    /**
     * @return 店铺id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            店铺id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 厂家:102，经销商：104
     */
    public Integer getShopType() {
        return shopType;
    }

    /**
     * @param shopType 
	 *            厂家:102，经销商：104
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
     * @return 店铺名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * @param shopName 
	 *            店铺名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * @return 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName 
	 *            公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * @return 联系人
     */
    public String getContract() {
        return contract;
    }

    /**
     * @param contract 
	 *            联系人
     */
    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    /**
     * @return 联系人联系方式
     */
    public String getContractPhone() {
        return contractPhone;
    }

    /**
     * @param contractPhone 
	 *            联系人联系方式
     */
    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone == null ? null : contractPhone.trim();
    }

    /**
     * @return 公司座机
     */
    public String getCompanyTel() {
        return companyTel;
    }

    /**
     * @param companyTel 
	 *            公司座机
     */
    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    /**
     * @return 法人代表
     */
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    /**
     * @param legalRepresentative 
	 *            法人代表
     */
    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative == null ? null : legalRepresentative.trim();
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
     * @return 经营的分类id
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId 
	 *            经营的分类id
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
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
     * @return 经营范围
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope 
	 *            经营范围
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
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
     * @return 一般纳税人图片
     */
    public String getTaxpayerImage() {
        return taxpayerImage;
    }

    /**
     * @param taxpayerImage 
	 *            一般纳税人图片
     */
    public void setTaxpayerImage(String taxpayerImage) {
        this.taxpayerImage = taxpayerImage == null ? null : taxpayerImage.trim();
    }

    /**
     * @return 营业执照图片
     */
    public String getLicenseImage() {
        return licenseImage;
    }

    /**
     * @param licenseImage 
	 *            营业执照图片
     */
    public void setLicenseImage(String licenseImage) {
        this.licenseImage = licenseImage == null ? null : licenseImage.trim();
    }

    /**
     * @return 组织机构代码证图片
     */
    public String getOrganizationImage() {
        return organizationImage;
    }

    /**
     * @param organizationImage 
	 *            组织机构代码证图片
     */
    public void setOrganizationImage(String organizationImage) {
        this.organizationImage = organizationImage == null ? null : organizationImage.trim();
    }

    /**
     * @return 实景图片
     */
    public String getShopImage1() {
        return shopImage1;
    }

    /**
     * @param shopImage1 
	 *            实景图片
     */
    public void setShopImage1(String shopImage1) {
        this.shopImage1 = shopImage1 == null ? null : shopImage1.trim();
    }

    /**
     * @return 实景图片
     */
    public String getShopImage2() {
        return shopImage2;
    }

    /**
     * @param shopImage2 
	 *            实景图片
     */
    public void setShopImage2(String shopImage2) {
        this.shopImage2 = shopImage2 == null ? null : shopImage2.trim();
    }

    /**
     * @return 实景图片
     */
    public String getShopImage3() {
        return shopImage3;
    }

    /**
     * @param shopImage3 
	 *            实景图片
     */
    public void setShopImage3(String shopImage3) {
        this.shopImage3 = shopImage3 == null ? null : shopImage3.trim();
    }

    /**
     * @return 公司名称
     */
    public String getAdsImage() {
        return adsImage;
    }

    /**
     * @param adsImage 
	 *            公司名称
     */
    public void setAdsImage(String adsImage) {
        this.adsImage = adsImage == null ? null : adsImage.trim();
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
     * @return 惠券优收入金额
     */
    public Double getCouponGainAmt() {
        return couponGainAmt;
    }

    /**
     * @param couponGainAmt 
	 *            惠券优收入金额
     */
    public void setCouponGainAmt(Double couponGainAmt) {
        this.couponGainAmt = couponGainAmt;
    }

    /**
     * @return 惠券优支出金额
     */
    public Double getCouponEmployAmt() {
        return couponEmployAmt;
    }

    /**
     * @param couponEmployAmt 
	 *            惠券优支出金额
     */
    public void setCouponEmployAmt(Double couponEmployAmt) {
        this.couponEmployAmt = couponEmployAmt;
    }

    /**
     * @return 优惠券余额
     */
    public Double getCouponRemainingAmt() {
        return couponRemainingAmt;
    }

    /**
     * @param couponRemainingAmt 
	 *            优惠券余额
     */
    public void setCouponRemainingAmt(Double couponRemainingAmt) {
        this.couponRemainingAmt = couponRemainingAmt;
    }

    /**
     * @return 店铺结算预存状态
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * @param balance 
	 *            店铺结算预存状态
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * @return 店铺的纵坐标
     */
    public Double getLon() {
        return lon;
    }

    /**
     * @param lon 
	 *            店铺的纵坐标
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * @return 店铺的横坐标
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat 
	 *            店铺的横坐标
     */
    public void setLat(Double lat) {
        this.lat = lat;
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

    /**
     * @return 是否自动确认订单：0 否，1 是
     */
    public Integer getIsAutomaticOrder() {
        return isAutomaticOrder;
    }

    /**
     * @param isAutomaticOrder 
	 *            是否自动确认订单：0 否，1 是
     */
    public void setIsAutomaticOrder(Integer isAutomaticOrder) {
        this.isAutomaticOrder = isAutomaticOrder;
    }

    /**
     * @return 平台结算：1，自结算：2
     */
    public Integer getSettlement() {
        return settlement;
    }

    /**
     * @param settlement 
	 *            平台结算：1，自结算：2
     */
    public void setSettlement(Integer settlement) {
        this.settlement = settlement;
    }

    /**
     * @return 返利百分比
     */
    public Double getRebate() {
        return rebate;
    }

    /**
     * @param rebate 
	 *            返利百分比
     */
    public void setRebate(Double rebate) {
        this.rebate = rebate;
    }

    /**
     * @return 等级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade 
	 *            等级
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
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
     * @return 金牌代理商权限标示0:未开通,1:未审核,2:已开通
     */
    public Integer getMedalAgentFlag() {
        return medalAgentFlag;
    }

    /**
     * @param medalAgentFlag 
	 * 金牌代理商权限标示0:未开通,1:未审核,2:已开通
     */
    public void setMedalAgentFlag(Integer medalAgentFlag) {
        this.medalAgentFlag = medalAgentFlag;
    }
}