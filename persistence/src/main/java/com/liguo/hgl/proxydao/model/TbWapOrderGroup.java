package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWapOrderGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String orderSerialNo;

	/**
	 * 订单总额
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
	 * 账户剩余金额
	 */
	private Double remainder;
	
    /**
     * 是否已经执行返利任务：0否，1是
     */
    private Integer rebate;

	public Integer getRebate() {
		return rebate;
	}

	public void setRebate(Integer rebate) {
		this.rebate = rebate;
	}

	/**
	 * 邮费
	 */
	private Double postage;

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
	 * 支付时间
	 */
	private Long payDt;

	/**
	 * 结算方式
	 */
	private Integer settleType;

	private Integer version;

	/**
	 * 收货方式
	 */
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
	 * 账户ID
	 */
	private Integer accountId;

	/**
	 * 取消订单原因
	 */
	private Integer cancelFlag;

	/**
	 * 买家删除订单时间
	 */
	private Long deleteDt;

	/**
	 * 卖家删除订单时间
	 */
	private Long deleteDtSeller;

	/**
	 * 买家删除订单flag
	 */
	private Integer deleteFlag;

	/**
	 * 卖家删除订单flag
	 */
	private Integer deleteFlagSeller;

	/**
	 * 订单类型
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
	 * 留言
	 */
	private String message;

	/**
	 * 评价
	 */
	private String evaluate;

	/**
	 * 评价星级
	 */
	private String evaluateStart;

	/**
	 * 评价图片
	 */
	private String evaluateImg;

	/**
	 * 推送师傅ID
	 */
	private Integer repairmanId;
	
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
		this.orderSerialNo = orderSerialNo == null ? null : orderSerialNo
				.trim();
	}

	/**
	 * @return 订单总额
	 */
	public Double getTotalMoney() {
		return totalMoney;
	}

	/**
	 * @param totalMoney
	 *            订单总额
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
	 * @return 账户剩余金额
	 */
	public Double getRemainder() {
		return remainder;
	}

	/**
	 * @param remainder
	 *            账户剩余金额
	 */
	public void setRemainder(Double remainder) {
		this.remainder = remainder;
	}

	/**
	 * @return 邮费
	 */
	public Double getPostage() {
		return postage;
	}

	/**
	 * @param postage
	 *            邮费
	 */
	public void setPostage(Double postage) {
		this.postage = postage;
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

	/**
	 * @return 收货方式
	 */
	public Integer getPlatFlag() {
		return platFlag;
	}

	/**
	 * @param platFlag
	 *            收货方式
	 */
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
	 * @return 账户ID
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            账户ID
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return 取消订单原因
	 */
	public Integer getCancelFlag() {
		return cancelFlag;
	}

	/**
	 * @param cancelFlag
	 *            取消订单原因
	 */
	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	/**
	 * @return 买家删除订单时间
	 */
	public Long getDeleteDt() {
		return deleteDt;
	}

	/**
	 * @param deleteDt
	 *            买家删除订单时间
	 */
	public void setDeleteDt(Long deleteDt) {
		this.deleteDt = deleteDt;
	}

	/**
	 * @return 卖家删除订单时间
	 */
	public Long getDeleteDtSeller() {
		return deleteDtSeller;
	}

	/**
	 * @param deleteDtSeller
	 *            卖家删除订单时间
	 */
	public void setDeleteDtSeller(Long deleteDtSeller) {
		this.deleteDtSeller = deleteDtSeller;
	}

	/**
	 * @return 买家删除订单flag
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * @param deleteFlag
	 *            买家删除订单flag
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * @return 卖家删除订单flag
	 */
	public Integer getDeleteFlagSeller() {
		return deleteFlagSeller;
	}

	/**
	 * @param deleteFlagSeller
	 *            卖家删除订单flag
	 */
	public void setDeleteFlagSeller(Integer deleteFlagSeller) {
		this.deleteFlagSeller = deleteFlagSeller;
	}

	/**
	 * @return 订单类型
	 */
	public Integer getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType
	 *            订单类型
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
	 * @return 留言
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            留言
	 */
	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}

	/**
	 * @return 评价
	 */
	public String getEvaluate() {
		return evaluate;
	}

	/**
	 * @param evaluate
	 *            评价
	 */
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate == null ? null : evaluate.trim();
	}

	/**
	 * @return 评价星级
	 */
	public String getEvaluateStart() {
		return evaluateStart;
	}

	/**
	 * @param evaluateStart
	 *            评价星级
	 */
	public void setEvaluateStart(String evaluateStart) {
		this.evaluateStart = evaluateStart == null ? null : evaluateStart
				.trim();
	}

	/**
	 * @return 评价图片
	 */
	public String getEvaluateImg() {
		return evaluateImg;
	}

	/**
	 * @param evaluateImg
	 *            评价图片
	 */
	public void setEvaluateImg(String evaluateImg) {
		this.evaluateImg = evaluateImg == null ? null : evaluateImg.trim();
	}

	public Integer getRepairmanId() {
		return repairmanId;
	}

	public void setRepairmanId(Integer repairmanId) {
		this.repairmanId = repairmanId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}