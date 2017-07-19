package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbCompanyType implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String describes;

    /**
     * 页面图片
     */
    private String imgName;

    private Integer version;

    /**
     * @return 分类id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            分类id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            分类名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 分类描述
     */
    public String getDescribes() {
        return describes;
    }

    /**
     * @param describes 
	 *            分类描述
     */
    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    /**
     * @return 页面图片
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * @param imgName 
	 *            页面图片
     */
    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}