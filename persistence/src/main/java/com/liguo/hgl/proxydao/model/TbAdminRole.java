package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbAdminRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 权限ids
     */
    private String permissionIds;

    /**
     * 备注,扩展字段
     */
    private String remark;

    private Integer version;

    /**
     * @return 角色id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            角色id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            角色名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 权限ids
     */
    public String getPermissionIds() {
        return permissionIds;
    }

    /**
     * @param permissionIds 
	 *            权限ids
     */
    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds == null ? null : permissionIds.trim();
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
}