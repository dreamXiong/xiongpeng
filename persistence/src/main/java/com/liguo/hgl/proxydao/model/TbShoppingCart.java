package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 购物车id
	 */
	private Integer id;

	/**
	 * 资源id
	 */
	private Integer inventoryId;

	/**
	 * 品牌ID
	 */
	private Integer brandId;

	/**
	 * 购买数量
	 */
	private Integer buyNum;

	/**
	 * 类型 招商和进货单
	 */
	private Boolean shoppingType;

	/**
	 * 单价
	 */
	private Double price;

	/**
	 * 折后价
	 */
	private Double salePrice;

	/**
	 * 操作人
	 */
	private Integer createBy;

	/**
	 * 加入购物车时间
	 */
	private Long createDt;

	/**
	 * 版本号
	 */
	private Integer version;

	/**
	 * 招商ID
	 */
	private Integer merchantsId;

	/**
	 * @return 购物车id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            购物车id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return 资源id
	 */
	public Integer getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId
	 *            资源id
	 */
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	/**
	 * @return 购买数量
	 */
	public Integer getBuyNum() {
		return buyNum;
	}

	/**
	 * @param buyNum
	 *            购买数量
	 */
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	/**
	 * @return 类型 招商和进货单
	 */
	public Boolean getShoppingType() {
		return shoppingType;
	}

	/**
	 * @param shoppingType
	 *            类型 招商和进货单
	 */
	public void setShoppingType(Boolean shoppingType) {
		this.shoppingType = shoppingType;
	}

	/**
	 * @return 单价
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            单价
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return 折后价
	 */
	public Double getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice
	 *            折后价
	 */
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return 操作人
	 */
	public Integer getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            操作人
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return 加入购物车时间
	 */
	public Long getCreateDt() {
		return createDt;
	}

	/**
	 * @param createDt
	 *            加入购物车时间
	 */
	public void setCreateDt(Long createDt) {
		this.createDt = createDt;
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

	public Integer getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(Integer merchantsId) {
		this.merchantsId = merchantsId;
	}

}