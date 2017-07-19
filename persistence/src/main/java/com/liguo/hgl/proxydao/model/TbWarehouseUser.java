package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWarehouseUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 仓库id
     */
    private Integer warehouseId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 仓库类型
     */
    private String shopType;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 登陆帐户
     */
    private String userAccount;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 仓库座机
     */
    private String warehouseTel;

    /**
     * 联系人
     */
    private String contract;

    /**
     * 联系人联系方式
     */
    private String contractPhone;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 版本号
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
     * @return 仓库id
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * @param warehouseId 
	 *            仓库id
     */
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * @return 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * @return 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 
	 *            用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return 登陆帐户
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * @param userAccount 
	 *            登陆帐户
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
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