package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbCountryInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 区县的id
     */
    private Integer id;

    /**
     * 县区对应的名称
     */
    private String name;

    /**
     * 县区对应的市id
     */
    private Integer parentid;

    /**
     * @return 区县的id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            区县的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 县区对应的名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            县区对应的名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 县区对应的市id
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * @param parentid 
	 *            县区对应的市id
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}