package com.liguo.hgl.proxydao.model;

public class TbPayParam {
    /**
     * 订单号
     */
    private String orderGroubNo;

    /**
     * id
     */
    private String id;

    /**
     * 版本
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
     * 报文内容
     */
    private byte[] repParam;

    /**
     * @return 订单号
     */
    public String getOrderGroubNo() {
        return orderGroubNo;
    }

    /**
     * @param orderGroubNo 
	 *            订单号
     */
    public void setOrderGroubNo(String orderGroubNo) {
        this.orderGroubNo = orderGroubNo == null ? null : orderGroubNo.trim();
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    /**
     * @return 报文内容
     */
    public byte[] getRepParam() {
        return repParam;
    }

    /**
     * @param repParam 
	 *            报文内容
     */
    public void setRepParam(byte[] repParam) {
        this.repParam = repParam;
    }
}