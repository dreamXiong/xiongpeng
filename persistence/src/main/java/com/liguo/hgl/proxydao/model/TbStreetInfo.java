package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbStreetInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 道街的id
     */
    private Integer id;

    /**
     * 道街的名称
     */
    private String name;

    /**
     * 道街对应的区县id
     */
    private Integer parentid;

    /**
     * @return 道街的id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            道街的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 道街的名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            道街的名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 道街对应的区县id
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * @param parentid 
	 *            道街对应的区县id
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}