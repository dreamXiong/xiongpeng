package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWapAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 收货人
	 */
	private String recipient;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 省份名称
	 */
	private String provinceName;

	/**
	 * 市
	 */
	private String city;

	/**
	 * 区
	 */
	private String area;

	/**
	 * 街道
	 */
	private String street;

	/**
	 * 街道明细
	 */
	private String streetDetail;
	/**
	 * 手机
	 */
	private String phone;

	/**
	 * 邮编
	 */
	private String code;

	/**
	 * 固定电话
	 */
	private String telephone;

	/**
	 * 是否选中
	 */
	private Integer checkFlag;

	/**
	 * 创建人
	 */
	private Integer createBy;

	/**
	 * 创建时间
	 */
	private Long createDt;

	/**
	 * 扩展属性
	 */
	private String extensionField;

	/**
	 * 地址明细
	 */
	private String address;

	/**
	 * 地址的纵坐标
	 */
	private Double lon;

	/**
	 * 地址的横坐标
	 */
	private Double lat;

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
	 * @return 收货人
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient
	 *            收货人
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient == null ? null : recipient.trim();
	}

	/**
	 * @return 省份
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            省份
	 */
	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * @return 市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            市
	 */
	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	/**
	 * @return 区
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            区
	 */
	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	/**
	 * @return 街道
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            街道
	 */
	public void setStreet(String street) {
		this.street = street == null ? null : street.trim();
	}

	public String getStreetDetail() {
		return streetDetail;
	}

	public void setStreetDetail(String streetDetail) {
		this.streetDetail = streetDetail;
	}

	/**
	 * @return 手机
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            手机
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * @return 邮编
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            邮编
	 */
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	 * @return 固定电话
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            固定电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public Integer getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Integer checkFlag) {
		this.checkFlag = checkFlag;
	}

	/**
	 * @return 创建人
	 */
	public Integer getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            创建人
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
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
		this.extensionField = extensionField == null ? null : extensionField
				.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
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