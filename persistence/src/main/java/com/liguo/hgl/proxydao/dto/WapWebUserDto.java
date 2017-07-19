package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbWebUser;

public class WapWebUserDto extends TbWebUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String serviceType;
	private String image;
	private String imageFace;
	private String imageBack;
	
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageFace() {
		return imageFace;
	}
	public void setImageFace(String imageFace) {
		this.imageFace = imageFace;
	}
	public String getImageBack() {
		return imageBack;
	}
	public void setImageBack(String imageBack) {
		this.imageBack = imageBack;
	}
	

}
