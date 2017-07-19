package com.liguo.hgl.proxydao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TbCityLowestPrice implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 市id
     */
    private Integer cityid;

    /**
     * 最低价格
     */
    private BigDecimal lowestPrice;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备用字段1
     */
    private String remark1;

    /**
     * 备用字段2
     */
    private String remark2;

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
     * @return 市id
     */
    public Integer getCityid() {
        return cityid;
    }

    /**
     * @param cityid 
	 *            市id
     */
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    /**
     * @return 最低价格
     */
    public BigDecimal getLowestPrice() {
        return lowestPrice;
    }

    /**
     * @param lowestPrice 
	 *            最低价格
     */
    public void setLowestPrice(BigDecimal lowestPrice) {
        this.lowestPrice = lowestPrice;
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
     * @return 备用字段1
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * @param remark1 
	 *            备用字段1
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * @return 备用字段2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * @param remark2 
	 *            备用字段2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }
}