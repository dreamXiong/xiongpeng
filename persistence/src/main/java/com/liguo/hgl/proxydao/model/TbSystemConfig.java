package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbSystemConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 系统配置key,唯一约束,且不能输入中文
     */
    private String configKey;

    /**
     * 系统配置value
     */
    private String configValue;

    /**
     * 系统配置描述
     */
    private String remark;

    /**
     * 删除标识
     */
    private Integer deleteFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Long updateDt;

    /**
     * 扩展属性
     */
    private String extensionField;

    /**
     * 版本
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
     * @return 系统配置key,唯一约束,且不能输入中文
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * @param configKey 
	 *            系统配置key,唯一约束,且不能输入中文
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    /**
     * @return 系统配置value
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * @param configValue 
	 *            系统配置value
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    /**
     * @return 系统配置描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            系统配置描述
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return 删除标识
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag 
	 *            删除标识
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @return 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy 
	 *            创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
     * @return 修改人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy 
	 *            修改人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * @return 修改时间
     */
    public Long getUpdateDt() {
        return updateDt;
    }

    /**
     * @param updateDt 
	 *            修改时间
     */
    public void setUpdateDt(Long updateDt) {
        this.updateDt = updateDt;
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
     * @return 版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}