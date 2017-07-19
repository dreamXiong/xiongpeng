package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWapAddressHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 收货人
     */
    private String recipient;

    /**
     * 省份
     */
    private String provinceName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮编
     */
    private String code;

    /**
     * 固定电话
     */
    private String telephone;

    /**
     * 创建人,是谁的地址
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 扩展字段
     */
    private String extensionField;

    /**
     * 地址经度
     */
    private Double lon;

    /**
     * 地址纬度
     */
    private Double lat;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 收货地址表Id
     */
    private Integer addressId;

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
     * @return 收货人
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * @param recipient 
	 *            收货人
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    /**
     * @return 省份
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName 
	 *            省份
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * @return 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone 
	 *            电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return 邮编
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 
	 *            邮编
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return 固定电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone 
	 *            固定电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * @return 创建人,是谁的地址
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy 
	 *            创建人,是谁的地址
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
     * @return 扩展字段
     */
    public String getExtensionField() {
        return extensionField;
    }

    /**
     * @param extensionField 
	 *            扩展字段
     */
    public void setExtensionField(String extensionField) {
        this.extensionField = extensionField == null ? null : extensionField.trim();
    }

    /**
     * @return 地址经度
     */
    public Double getLon() {
        return lon;
    }

    /**
     * @param lon 
	 *            地址经度
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * @return 地址纬度
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat 
	 *            地址纬度
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
     * @return 收货地址表Id
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId 
	 *            收货地址表Id
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}