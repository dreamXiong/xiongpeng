package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbOpenedRegional implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 省ID
     */
    private Integer proviceId;

    /**
     * 已开通市
     */
    private String openCity;

    /**
     * 已开通区
     */
    private String openCountry;

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
     * @return 省ID
     */
    public Integer getProviceId() {
        return proviceId;
    }
    /**
     * @param proviceid 
     *            省ID
     */
    public void setProviceId(Integer proviceId) {
        this.proviceId = proviceId;
    }

    /**
     * @return 已开通市
     */
    public String getOpenCity() {
        return openCity;
    }

    /**
     * @param openCity 
     *            已开通市
     */
    public void setOpenCity(String openCity) {
        this.openCity = openCity == null ? null : openCity.trim();
    }

    /**
     * @return 已开通区
     */
    public String getOpenCountry() {
        return openCountry;
    }

    /**
     * @param openCountry 
     *            已开通区
     */
    public void setOpenCountry(String openCountry) {
        this.openCountry = openCountry == null ? null : openCountry.trim();
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