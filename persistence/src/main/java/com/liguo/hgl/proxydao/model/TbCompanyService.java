package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbCompanyService implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 公司id
     */
    private Integer id;

    /**
     * 公司类型id
     */
    private Integer typeId;

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
     * 经营范围
     */
    private String scope;

    /**
     * 实景图片
     */
    private String companyImage1;

    /**
     * 实景图片
     */
    private String companyImage2;

    /**
     * 实景图片
     */
    private String companyImage3;

    /**
     * 备注,扩展字段
     */
    private String remark;

    /**
     * 公司描述
     */
    private String describes;

    /**
     * 公司的纵坐标
     */
    private Double lon;

    /**
     * 公司的横坐标
     */
    private Double lat;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * @return 公司id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            公司id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 公司类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId 
	 *            公司类型id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
     * @return 实景图片
     */
    public String getCompanyImage1() {
        return companyImage1;
    }

    /**
     * @param companyImage1 
	 *            实景图片
     */
    public void setCompanyImage1(String companyImage1) {
        this.companyImage1 = companyImage1 == null ? null : companyImage1.trim();
    }

    /**
     * @return 实景图片
     */
    public String getCompanyImage2() {
        return companyImage2;
    }

    /**
     * @param companyImage2 
	 *            实景图片
     */
    public void setCompanyImage2(String companyImage2) {
        this.companyImage2 = companyImage2 == null ? null : companyImage2.trim();
    }

    /**
     * @return 实景图片
     */
    public String getCompanyImage3() {
        return companyImage3;
    }

    /**
     * @param companyImage3 
	 *            实景图片
     */
    public void setCompanyImage3(String companyImage3) {
        this.companyImage3 = companyImage3 == null ? null : companyImage3.trim();
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
     * @return 公司描述
     */
    public String getDescribes() {
        return describes;
    }

    /**
     * @param describes 
	 *            公司描述
     */
    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    /**
     * @return 公司的纵坐标
     */
    public Double getLon() {
        return lon;
    }

    /**
     * @param lon 
	 *            公司的纵坐标
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * @return 公司的横坐标
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat 
	 *            公司的横坐标
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
}