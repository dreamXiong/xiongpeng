package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWapShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 库存ID
	 */
	private Integer inventoryId;

	/**
	 * 购买的数量
	 */
	private Integer buyNum;

	/**
	 * 创建人,谁购买的
	 */
	private Integer createBy;

	/**
	 * 购买时间
	 */
	private Long createDt;

	/**
	 * 是否是师傅推送标识
	 */
	private Integer pushFlag;

	/**
	 * 师傅ID
	 */
	private Integer masterId;

	/**
	 * 版本号
	 */
	private Integer version;

	/**
	 * @return ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return 库存ID
	 */
	public Integer getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId
	 *            库存ID
	 */
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * @return 购买的数量
	 */
	public Integer getBuyNum() {
		return buyNum;
	}

	/**
	 * @param buyNum
	 *            购买的数量
	 */
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	/**
	 * @return 创建人,谁购买的
	 */
	public Integer getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            创建人,谁购买的
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return 购买时间
	 */
	public Long getCreateDt() {
		return createDt;
	}

	/**
	 * @param createDt
	 *            购买时间
	 */
	public void setCreateDt(Long createDt) {
		this.createDt = createDt;
	}

	/**
	 * @return 是否是师傅推送标识
	 */
	public Integer getPushFlag() {
		return pushFlag;
	}

	/**
	 * @param pushFlag
	 *            是否是师傅推送标识
	 */
	public void setPushFlag(Integer pushFlag) {
		this.pushFlag = pushFlag;
	}

	/**
	 * @return 师傅ID
	 */
	public Integer getMasterId() {
		return masterId;
	}

	/**
	 * @param masterId
	 *            师傅ID
	 */
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	/**
	 * @return 版本号
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            版本号
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
}