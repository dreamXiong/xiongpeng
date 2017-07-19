package com.liguo.hgl.proxydao.model;

import java.io.Serializable;
import java.util.List;

import com.liguo.hgl.proxydao.dto.ServiceTypeDto;

public class TbServiceType implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String describes;

    /**
     * 页面
     */
    private String jsp;

    private Integer version;
    
    private List<ServiceTypeDto> childList;
    
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
     * @return 父节点
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId 
	 *            父节点
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
     * @return 页面
     */
    public String getJsp() {
        return jsp;
    }

    /**
     * @param jsp 
	 *            页面
     */
    public void setJsp(String jsp) {
        this.jsp = jsp == null ? null : jsp.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

	public List<ServiceTypeDto> getChildList() {
		return childList;
	}

	public void setChildList(List<ServiceTypeDto> childList) {
		this.childList = childList;
	} 
}