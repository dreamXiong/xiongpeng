package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWarehouse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 仓库id
     */
    private Integer id;

    /**
     * 注册省
     */
    private Integer warehouseProvince;

    /**
     * 注册市
     */
    private Integer warehouseCity;

    /**
     * 注册区县
     */
    private Integer warehouseCountry;

    /**
     * 注册街道
     */
    private Integer warehouseStreet;

    /**
     * 具体地址
     */
    private String warehouseAddress;

    /**
     * 仓库类型
     */
    private String shopType;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 联系人
     */
    private String contract;

    /**
     * 联系人联系方式
     */
    private String contractPhone;

    /**
     * 仓库座机
     */
    private String warehouseTel;

    /**
     * 仓库管理员帐号
     */
    private Integer manageUser;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 状态：1 启用，0停用
     */
    private Integer states;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * @return 仓库id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            仓库id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 注册省
     */
    public Integer getWarehouseProvince() {
        return warehouseProvince;
    }

    /**
     * @param warehouseProvince 
	 *            注册省
     */
    public void setWarehouseProvince(Integer warehouseProvince) {
        this.warehouseProvince = warehouseProvince;
    }

    /**
     * @return 注册市
     */
    public Integer getWarehouseCity() {
        return warehouseCity;
    }

    /**
     * @param warehouseCity 
	 *            注册市
     */
    public void setWarehouseCity(Integer warehouseCity) {
        this.warehouseCity = warehouseCity;
    }

    /**
     * @return 注册区县
     */
    public Integer getWarehouseCountry() {
        return warehouseCountry;
    }

    /**
     * @param warehouseCountry 
	 *            注册区县
     */
    public void setWarehouseCountry(Integer warehouseCountry) {
        this.warehouseCountry = warehouseCountry;
    }

    /**
     * @return 注册街道
     */
    public Integer getWarehouseStreet() {
        return warehouseStreet;
    }

    /**
     * @param warehouseStreet 
	 *            注册街道
     */
    public void setWarehouseStreet(Integer warehouseStreet) {
        this.warehouseStreet = warehouseStreet;
    }

    /**
     * @return 具体地址
     */
    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    /**
     * @param warehouseAddress 
	 *            具体地址
     */
    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress == null ? null : warehouseAddress.trim();
    }

    /**
     * @return 仓库类型
     */
    public String getShopType() {
        return shopType;
    }

    /**
     * @param shopType 
	 *            仓库类型
     */
    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
    }

    /**
     * @return 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * @param warehouseName 
	 *            仓库名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
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
     * @return 仓库座机
     */
    public String getWarehouseTel() {
        return warehouseTel;
    }

    /**
     * @param warehouseTel 
	 *            仓库座机
     */
    public void setWarehouseTel(String warehouseTel) {
        this.warehouseTel = warehouseTel == null ? null : warehouseTel.trim();
    }

    /**
     * @return 仓库管理员帐号
     */
    public Integer getManageUser() {
        return manageUser;
    }

    /**
     * @param manageUser 
	 *            仓库管理员帐号
     */
    public void setManageUser(Integer manageUser) {
        this.manageUser = manageUser;
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
     * @return 状态：1 启用，0停用
     */
    public Integer getStates() {
        return states;
    }

    /**
     * @param states 
	 *            状态：1 启用，0停用
     */
    public void setStates(Integer states) {
        this.states = states;
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