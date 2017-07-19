package com.liguo.hgl.proxydao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TbProductInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 产品库存id
     */
    private Integer id;

    /**
     * 产品id
     */
    private Integer productId;

    private String code;

    /**
     * 产品库存名称
     */
    private String name;

    /**
     * 剩余库存
     */
    private Integer saleInventory=0;

    /**
     * 入库库存
     */
    private Integer totalInventory=0;

    /**
     * 占用库存
     */
    private Integer unsaleInventory=0;

    /**
     * 销售数量
     */
    private Integer salesCount=0;

    /**
     * 属性值
     */
    private String attributesValues;

    /**
     * 入库价格
     */
    private BigDecimal instockPrice;

    /**
     * 出库价格
     */
    private BigDecimal outstockPrice;

    /**
     * 零售价格
     */
    private BigDecimal salesPrice;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 装箱数
     */
    private Integer oneboxCount;

    /**
     * 备注,扩展字段
     */
    private String remark;

    private Integer version;

    private Integer deleted;

    private String createby;

    private Long createTime;

    private String lastupdateby;

    private Long lastUpdateTime;

    private String spec;

    private String material;

    private Integer warehouseId;

    /**
     * @return 产品库存id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            产品库存id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 产品id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId 
	 *            产品id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return 产品库存名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            产品库存名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 剩余库存
     */
    public Integer getSaleInventory() {
        return saleInventory;
    }

    /**
     * @param saleInventory 
	 *            剩余库存
     */
    public void setSaleInventory(Integer saleInventory) {
        this.saleInventory = saleInventory;
    }

    /**
     * @return 入库库存
     */
    public Integer getTotalInventory() {
        return totalInventory;
    }

    /**
     * @param totalInventory 
	 *            入库库存
     */
    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }

    /**
     * @return 占用库存
     */
    public Integer getUnsaleInventory() {
        return unsaleInventory;
    }

    /**
     * @param unsaleInventory 
	 *            占用库存
     */
    public void setUnsaleInventory(Integer unsaleInventory) {
        this.unsaleInventory = unsaleInventory;
    }

    /**
     * @return 销售数量
     */
    public Integer getSalesCount() {
        return salesCount;
    }

    /**
     * @param salesCount 
	 *            销售数量
     */
    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    /**
     * @return 属性值
     */
    public String getAttributesValues() {
        return attributesValues;
    }

    /**
     * @param attributesValues 
	 *            属性值
     */
    public void setAttributesValues(String attributesValues) {
        this.attributesValues = attributesValues == null ? null : attributesValues.trim();
    }

    /**
     * @return 入库价格
     */
    public BigDecimal getInstockPrice() {
        return instockPrice;
    }

    /**
     * @param instockPrice 
	 *            入库价格
     */
    public void setInstockPrice(BigDecimal instockPrice) {
        this.instockPrice = instockPrice;
    }

    /**
     * @return 出库价格
     */
    public BigDecimal getOutstockPrice() {
        return outstockPrice;
    }

    /**
     * @param outstockPrice 
	 *            出库价格
     */
    public void setOutstockPrice(BigDecimal outstockPrice) {
        this.outstockPrice = outstockPrice;
    }

    /**
     * @return 零售价格
     */
    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    /**
     * @param salesPrice 
	 *            零售价格
     */
    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
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
     * @return 装箱数
     */
    public Integer getOneboxCount() {
        return oneboxCount;
    }

    /**
     * @param oneboxCount 
	 *            装箱数
     */
    public void setOneboxCount(Integer oneboxCount) {
        this.oneboxCount = oneboxCount;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getLastupdateby() {
        return lastupdateby;
    }

    public void setLastupdateby(String lastupdateby) {
        this.lastupdateby = lastupdateby == null ? null : lastupdateby.trim();
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
}