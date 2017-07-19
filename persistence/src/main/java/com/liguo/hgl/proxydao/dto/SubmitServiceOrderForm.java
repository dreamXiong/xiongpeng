package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;

public class SubmitServiceOrderForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recipient;
	private String phone;
	private String addressId;
	private Integer serviceTypeId;
	private String message;
	private Long appointment;
	private String appointmentPeriod;
	private Integer[] appointments;
	private Integer oderServiceId;//主键id--修改
	private Integer cityCode;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public Integer getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(Integer serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getAppointment() {
		return appointment;
	}

	public void setAppointment(Long appointment) {
		this.appointment = appointment;
	}

	public String getAppointmentPeriod() {
		return appointmentPeriod;
	}

	public void setAppointmentPeriod(String appointmentPeriod) {
		this.appointmentPeriod = appointmentPeriod;
	}

	public Integer[] getAppointments() {
		return appointments;
	}

	public void setAppointments(Integer[] appointments) {
		this.appointments = appointments;
	}

	public Integer getOderServiceId() {
		return oderServiceId;
	}

	public void setOderServiceId(Integer oderServiceId) {
		this.oderServiceId = oderServiceId;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	
}
