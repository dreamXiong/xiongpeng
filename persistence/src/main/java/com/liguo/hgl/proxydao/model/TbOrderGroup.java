package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbOrderGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String orderSerialNo;

    /**
     *提交订单时group的总金额
     */
    private Double totalMoney;

    /**
     * 购买这个group下所有产品的总数量
     */
    private Integer totalNum;

    /**
     * 应付的总金额，不含佣金
     */
    private Double payMoney;

    /**
     * 卖家ID
     */
    private Integer shopId;

    /**
     * 买家ID
     */
    private Integer buyerId;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 仓库ID
     */
    private Integer warehouseId;

    /**
     * 支付时间
     */
    private Long payDt;

    /**
     * 佣金
     */
    private Double commission;

    /**
     * 结算方式
     */
    private Integer settleType;

    private Integer version;

    private Integer platFlag;

    /**
     * 取消时间
     */
    private Long cancelDt;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 使用优惠卷的金额
     */
    private Integer couponMoney;

    /**
     * 地址ID
     */
    private Integer addressId;

    /**
     * 取消订单原因，数据存放在字典中
     */
    private Integer cancelFlag;

    /**
     * 逻辑删除时间
     */
    private Long deleteDt;

    /**
     * 逻辑删除flag
     */
    private Integer deleteFlag;

    /**
     * 逻辑删除flag
     */
    private Integer orderType;

    /**
     * 终止之前的状态
     */
    private Integer stopStatus;

    /**
     * 终止原因
     */
    private String stopReason;

    /**
     * 漏发描述
     */
    private String reissueDescription;

    /**
     * 漏发图片
     */
    private String reissueImage;
    
    /**
     * 订单最新更新时间
     */
	private Long updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSerialNo() {
        return orderSerialNo;
    }

    public void setOrderSerialNo(String orderSerialNo) {
        this.orderSerialNo = orderSerialNo == null ? null : orderSerialNo.trim();
    }

    /**
     * @return 提交订单时group的总金额
     */
    public Double getTotalMoney() {
        return totalMoney;
    }

    /**
     * @param totalMoney 
	 *           提交订单时group的总金额
     */
    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * @return 购买这个group下所有产品的总数量
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * @param totalNum 
	 *            购买这个group下所有产品的总数量
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * @return 应付的总金额，不含佣金
     */
    public Double getPayMoney() {
        return payMoney;
    }

    /**
     * @param payMoney 
	 *            应付的总金额，不含佣金
     */
    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    /**
     * @return 卖家ID
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            卖家ID
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 买家ID
     */
    public Integer getBuyerId() {
        return buyerId;
    }

    /**
     * @param buyerId 
	 *            买家ID
     */
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return 品牌ID
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * @param brandId 
	 *            品牌ID
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * @return 订单状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus 
	 *            订单状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return 仓库ID
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * @param warehouseId 
	 *            仓库ID
     */
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * @return 支付时间
     */
    public Long getPayDt() {
        return payDt;
    }

    /**
     * @param payDt 
	 *            支付时间
     */
    public void setPayDt(Long payDt) {
        this.payDt = payDt;
    }

    /**
     * @return 佣金
     */
    public Double getCommission() {
        return commission;
    }

    /**
     * @param commission 
	 *            佣金
     */
    public void setCommission(Double commission) {
        this.commission = commission;
    }

    /**
     * @return 结算方式
     */
    public Integer getSettleType() {
        return settleType;
    }

    /**
     * @param settleType 
	 *            结算方式
     */
    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getPlatFlag() {
        return platFlag;
    }

    public void setPlatFlag(Integer platFlag) {
        this.platFlag = platFlag;
    }

    /**
     * @return 取消时间
     */
    public Long getCancelDt() {
        return cancelDt;
    }

    /**
     * @param cancelDt 
	 *            取消时间
     */
    public void setCancelDt(Long cancelDt) {
        this.cancelDt = cancelDt;
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
     * @return 使用优惠卷的金额
     */
    public Integer getCouponMoney() {
        return couponMoney;
    }

    /**
     * @param couponMoney 
	 *            使用优惠卷的金额
     */
    public void setCouponMoney(Integer couponMoney) {
        this.couponMoney = couponMoney;
    }

    /**
     * @return 地址ID
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId 
	 *            地址ID
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * @return 取消订单原因，数据存放在字典中
     */
    public Integer getCancelFlag() {
        return cancelFlag;
    }

    /**
     * @param cancelFlag 
	 *            取消订单原因，数据存放在字典中
     */
    public void setCancelFlag(Integer cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    /**
     * @return 逻辑删除时间
     */
    public Long getDeleteDt() {
        return deleteDt;
    }

    /**
     * @param deleteDt 
	 *            逻辑删除时间
     */
    public void setDeleteDt(Long deleteDt) {
        this.deleteDt = deleteDt;
    }

    /**
     * @return 逻辑删除flag
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag 
	 *            逻辑删除flag
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @return 逻辑删除flag
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * @param orderType 
	 *            逻辑删除flag
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * @return 终止之前的状态
     */
    public Integer getStopStatus() {
        return stopStatus;
    }

    /**
     * @param stopStatus 
	 *            终止之前的状态
     */
    public void setStopStatus(Integer stopStatus) {
        this.stopStatus = stopStatus;
    }

    /**
     * @return 终止原因
     */
    public String getStopReason() {
        return stopReason;
    }

    /**
     * @param stopReason 
	 *            终止原因
     */
    public void setStopReason(String stopReason) {
        this.stopReason = stopReason == null ? null : stopReason.trim();
    }

    /**
     * @return 漏发描述
     */
    public String getReissueDescription() {
        return reissueDescription;
    }

    /**
     * @param reissueDescription 
	 *            漏发描述
     */
    public void setReissueDescription(String reissueDescription) {
        this.reissueDescription = reissueDescription == null ? null : reissueDescription.trim();
    }

    /**
     * @return 漏发图片
     */
    public String getReissueImage() {
        return reissueImage;
    }

    /**
     * @param reissueImage 
	 *            漏发图片
     */
    public void setReissueImage(String reissueImage) {
        this.reissueImage = reissueImage == null ? null : reissueImage.trim();
    }
    
    /**
     * @return 订单最新更新时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updatetime 
     *            订单最新更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}