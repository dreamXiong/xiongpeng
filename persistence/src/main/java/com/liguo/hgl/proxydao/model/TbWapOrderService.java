package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWapOrderService implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String orderSerialNo;

    /**
     * 订单总额
     */
    private Double totalMoney;

    /**
     * 师傅ID
     */
    private Integer repairmanId;

    /**
     * 主人ID
     */
    private Integer masterId;

    /**
     * 服务类型ID
     */
    private Integer serviceTypeId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 结算方式
     */
    private Integer settleType;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 支付时间
     */
    private Long payDt;

    /**
     * 预约时间
     */
    private Long appointmentDt;

    /**
     * 预约时间段
     */
    private String appointmentPeriodDt;

    /**
     * 地址ID
     */
    private Integer addressId;

    /**
     * 取消时间
     */
    private Long cancelDt;

    /**
     * 取消订单原因
     */
    private Integer cancelFlag;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 描述
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
     * 扩展属性
     */
    private String extensionField;

    private Integer version;

    /**
     * 师傅是否显示 0 显示 1 隐藏
     */
    private Integer repairmanShow;

    /**
     * 个人是否显示 0 显示 1 隐藏
     */
    private Integer masterShow;

    /**
     * 城市code
     */
    private Integer cityCode;

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
     * @return 师傅ID
     */
    public Integer getRepairmanId() {
        return repairmanId;
    }

    /**
     * @param repairmanId 
	 *            师傅ID
     */
    public void setRepairmanId(Integer repairmanId) {
        this.repairmanId = repairmanId;
    }

    /**
     * @return 主人ID
     */
    public Integer getMasterId() {
        return masterId;
    }

    /**
     * @param masterId 
	 *            主人ID
     */
    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    /**
     * @return 服务类型ID
     */
    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    /**
     * @param serviceTypeId 
	 *            服务类型ID
     */
    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
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
     * @return 预约时间
     */
    public Long getAppointmentDt() {
        return appointmentDt;
    }

    /**
     * @param appointmentDt 
	 *            预约时间
     */
    public void setAppointmentDt(Long appointmentDt) {
        this.appointmentDt = appointmentDt;
    }

    /**
     * @return 预约时间段
     */
    public String getAppointmentPeriodDt() {
        return appointmentPeriodDt;
    }

    /**
     * @param appointmentPeriodDt 
	 *            预约时间段
     */
    public void setAppointmentPeriodDt(String appointmentPeriodDt) {
        this.appointmentPeriodDt = appointmentPeriodDt == null ? null : appointmentPeriodDt.trim();
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
     * @return 描述
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message 
	 *            描述
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
        this.evaluateStart = evaluateStart == null ? null : evaluateStart.trim();
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return 师傅是否显示 0 显示 1 隐藏
     */
    public Integer getRepairmanShow() {
        return repairmanShow;
    }

    /**
     * @param repairmanShow 
	 *            师傅是否显示 0 显示 1 隐藏
     */
    public void setRepairmanShow(Integer repairmanShow) {
        this.repairmanShow = repairmanShow;
    }

    /**
     * @return 个人是否显示 0 显示 1 隐藏
     */
    public Integer getMasterShow() {
        return masterShow;
    }

    /**
     * @param masterShow 
	 *            个人是否显示 0 显示 1 隐藏
     */
    public void setMasterShow(Integer masterShow) {
        this.masterShow = masterShow;
    }

    /**
     * @return 城市code
     */
    public Integer getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode 
	 *            城市code
     */
    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }
}