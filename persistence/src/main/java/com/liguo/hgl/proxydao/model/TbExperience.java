package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbExperience implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 结余（总经验值）
     */
    private Integer experienceSum;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 说明，明细
     */
    private String detail;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 用户人id
     */
    private Integer createBy;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 备注,扩展字段
     */
    private String remark;

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
     * @return 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number 
	 *            数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return 结余（总经验值）
     */
    public Integer getExperienceSum() {
        return experienceSum;
    }

    /**
     * @param experienceSum 
	 *            结余（总经验值）
     */
    public void setExperienceSum(Integer experienceSum) {
        this.experienceSum = experienceSum;
    }

    /**
     * @return 订单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber 
	 *            订单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    /**
     * @return 说明，明细
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail 
	 *            说明，明细
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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
     * @return 用户人id
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy 
	 *            用户人id
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * @return 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state 
	 *            状态
     */
    public void setState(Integer state) {
        this.state = state;
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