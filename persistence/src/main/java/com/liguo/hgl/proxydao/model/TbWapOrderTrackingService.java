package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWapOrderTrackingService implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 服务订单ID
	 */
	private Integer orderServiceId;

	/**
	 * 订单状态
	 */
	private Integer orderState;

	/**
	 * 订单描述
	 */
	private String operateName;

	/**
	 * 操作人
	 */
	private Integer operateBy;

	/**
	 * 操作时间
	 */
	private Long operateDt;

	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return 服务订单ID
	 */
	public Integer getOrderServiceId() {
		return orderServiceId;
	}

	/**
	 * @param orderServiceId
	 *            服务订单ID
	 */
	public void setOrderServiceId(Integer orderServiceId) {
		this.orderServiceId = orderServiceId;
	}

	/**
	 * @return 订单状态
	 */
	public Integer getOrderState() {
		return orderState;
	}

	/**
	 * @param orderState
	 *            订单状态
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	/**
	 * @return 订单描述
	 */
	public String getOperateName() {
		return operateName;
	}

	/**
	 * @param operateName
	 *            订单描述
	 */
	public void setOperateName(String operateName) {
		this.operateName = operateName == null ? null : operateName.trim();
	}

	public Integer getOperateBy() {
		return operateBy;
	}

	public void setOperateBy(Integer operateBy) {
		this.operateBy = operateBy;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}