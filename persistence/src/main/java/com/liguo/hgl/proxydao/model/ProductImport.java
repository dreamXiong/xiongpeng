package com.liguo.hgl.proxydao.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.liguo.hgl.proxydao.util.StringUtil;

public class ProductImport {

    private String name;
    private Integer brandId;
    private Float price;
    private Integer productTypeId;
    private String attributes;
    private String describes;
    private String pimgOne;
    private String pimgTwo;
    private String pimgThree;
    private String dimgOne;
    private String dimgTwo;
    private String dimgThree;
    private String meterageUnit;
    private Integer shopId;
    private String code;
    private String totalInventory;
    private String attributesValues;
    private String instockPrice;
    private String outstockPrice;
    private String salesPrice;
    private String spec;
    private String material;
    private String message;
    private String inventoryName;
    
    public Integer getProductTypeId() {
        return productTypeId;
    }
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
    public String getPimgOne() {
        return pimgOne;
    }
    public void setPimgOne(String pimgOne) {
        this.pimgOne = pimgOne;
    }
    public String getPimgTwo() {
        return pimgTwo;
    }
    public void setPimgTwo(String pimgTwo) {
        this.pimgTwo = pimgTwo;
    }
    public String getPimgThree() {
        return pimgThree;
    }
    public void setPimgThree(String pimgThree) {
        this.pimgThree = pimgThree;
    }
    public String getDimgOne() {
        return dimgOne;
    }
    public void setDimgOne(String dimgOne) {
        this.dimgOne = dimgOne;
    }
    public String getDimgTwo() {
        return dimgTwo;
    }
    public void setDimgTwo(String dimgTwo) {
        this.dimgTwo = dimgTwo;
    }
    public String getDimgThree() {
        return dimgThree;
    }
    public void setDimgThree(String dimgThree) {
        this.dimgThree = dimgThree;
    }
    public String getMeterageUnit() {
        return meterageUnit;
    }
    public void setMeterageUnit(String meterageUnit) {
        this.meterageUnit = meterageUnit;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBrandId() {
        return brandId;
    }
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public String getAttributes() {
        return attributes;
    }
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
    public String getDescribes() {
        return describes;
    }
    public void setDescribes(String describes) {
        this.describes = describes;
    }
    public Integer getShopId() {
        return shopId;
    }
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTotalInventory() {
        return totalInventory;
    }
    public void setTotalInventory(String totalInventory) {
        this.totalInventory = totalInventory;
    }
    public String getAttributesValues() {
        return attributesValues;
    }
    public void setAttributesValues(String attributesValues) {
        this.attributesValues = attributesValues;
    }
    public String getInstockPrice() {
        return instockPrice;
    }
    public void setInstockPrice(String instockPrice) {
        this.instockPrice = instockPrice;
    }
    public String getSpec() {
        return spec;
    }
    public void setSpec(String spec) {
        this.spec = spec;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    @Override
    public int hashCode() {
        return  HashCodeBuilder.reflectionHashCode( this );
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        // TODO Auto-generated method stub
        if(!(obj instanceof ProductImport)){
            return false;
        }
        
        if(super.equals(obj)){
            return true;
        }
        ProductImport pi = (ProductImport) obj;
        
        if(null!=this.name&&null!=this.code&&(this.name.equals(pi.getName()))&&(this.code.equals(pi.getCode()))){
            equals = true;
        }
        
        if(null!=this.brandId&&this.brandId.equals(pi.getBrandId())){
            equals = true;
        }
        if(null!=this.productTypeId&&this.productTypeId.equals(pi.getProductTypeId())){
            equals = true;
        }
        
        return equals;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getOutstockPrice() {
        return outstockPrice;
    }
    public void setOutstockPrice(String outstockPrice) {
        this.outstockPrice = outstockPrice;
    }
    public String getSalesPrice() {
        return salesPrice;
    }
    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }
    public String getInventoryName() {
        return inventoryName;
    }
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }    
    
    
}

