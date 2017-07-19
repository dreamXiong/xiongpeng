package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbCouponInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
    private Integer id;

    /**
     * 优惠券类型(300:购买,302:赠送)
     */
    private Integer typeId;

    /**
     * 优惠券的状态(400:获得,402:使用)
     */
    private Integer status;

    /**
     * 优惠券金额
     */
    private Double amount;

    private String bussinessId;

    /**
     * 铺店id
     */
    private Integer shopId;

    /**
     * 业务号
     */
    private String orderSerialNo;

    /**
     * 铺店name
     */
    private String shopName;

    /**
     * 获得时间
     */
    private Long obtainDt;

    /**
     * 惠券优的使用时间
     */
    private Long useDt;

    /**
     * 操作时间
     */
    private Long operateDt;

    private String remark;

    /**
     * @return 优惠券id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            优惠券id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 优惠券类型(300:购买,302:赠送)
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId 
	 *            优惠券类型(300:购买,302:赠送)
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * @return 优惠券的状态(400:获得,402:使用)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            优惠券的状态(400:获得,402:使用)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 优惠券金额
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount 
	 *            优惠券金额
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId == null ? null : bussinessId.trim();
    }

    /**
     * @return 铺店id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            铺店id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 业务号
     */
    public String getOrderSerialNo() {
        return orderSerialNo;
    }

    /**
     * @param orderSerialNo 
	 *            业务号
     */
    public void setOrderSerialNo(String orderSerialNo) {
        this.orderSerialNo = orderSerialNo == null ? null : orderSerialNo.trim();
    }

    /**
     * @return 铺店name
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * @param shopName 
	 *            铺店name
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * @return 获得时间
     */
    public Long getObtainDt() {
        return obtainDt;
    }

    /**
     * @param obtainDt 
	 *            获得时间
     */
    public void setObtainDt(Long obtainDt) {
        this.obtainDt = obtainDt;
    }

    /**
     * @return 惠券优的使用时间
     */
    public Long getUseDt() {
        return useDt;
    }

    /**
     * @param useDt 
	 *            惠券优的使用时间
     */
    public void setUseDt(Long useDt) {
        this.useDt = useDt;
    }

    /**
     * @return 操作时间
     */
    public Long getOperateDt() {
        return operateDt;
    }

    /**
     * @param operateDt 
	 *            操作时间
     */
    public void setOperateDt(Long operateDt) {
        this.operateDt = operateDt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}