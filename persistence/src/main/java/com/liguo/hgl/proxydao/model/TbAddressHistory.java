package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbAddressHistory implements Serializable {
	
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
     * 手机
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
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 地址
     */
    private String extensionField;

    /**
     * 版本
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
     * @return 手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone 
	 *            手机
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
     * @return 地址
     */
    public String getExtensionField() {
        return extensionField;
    }

    /**
     * @param extensionField 
	 *            地址
     */
    public void setExtensionField(String extensionField) {
        this.extensionField = extensionField == null ? null : extensionField.trim();
    }

    /**
     * @return 版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}