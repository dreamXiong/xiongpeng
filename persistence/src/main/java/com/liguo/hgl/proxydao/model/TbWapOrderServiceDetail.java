package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWapOrderServiceDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 服务订单ID
     */
    private Integer orderServiceId;

    /**
     * 库存ID
     */
    private Integer inventoryId;

    /**
     * 购买的数量
     */
    private Integer buyNum;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 扩展属性
     */
    private String extensionField;

    /**
     * 版本号
     */
    private Integer version;
    
    private TbWapProductInventory productInventory;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 服务订单ID
     */
    public Integer getOrderServiceId() {
        return orderServiceId;
    }

    /**
     * @param orderServiceId 
	 *            服务订单ID
     */
    public void setOrderServiceId(Integer orderServiceId) {
        this.orderServiceId = orderServiceId;
    }

    /**
     * @return 库存ID
     */
    public Integer getInventoryId() {
        return inventoryId;
    }

    /**
     * @param inventoryId 
	 *            库存ID
     */
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * @return 购买的数量
     */
    public Integer getBuyNum() {
        return buyNum;
    }

    /**
     * @param buyNum 
	 *            购买的数量
     */
    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    /**
     * @return 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * @return 扩展属性
     */
    public String getExtensionField() {
        return extensionField;
    }

    /**
     * @param extensionField 
	 *            扩展属性
     */
    public void setExtensionField(String extensionField) {
        this.extensionField = extensionField == null ? null : extensionField.trim();
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

	public TbWapProductInventory getProductInventory() {
		return productInventory;
	}

	public void setProductInventory(TbWapProductInventory productInventory) {
		this.productInventory = productInventory;
	}  
}