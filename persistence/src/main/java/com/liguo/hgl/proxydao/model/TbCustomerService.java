package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbCustomerService implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品名id
     */
    private Integer id;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 操作人id
     */
    private Integer operationId;

    /**
     * 服务分类id
     */
    private Integer serviceTypeId;

    /**
     * 价格
     */
    private Double price;

    /**
     * 服务说明
     */
    private String describes;

    /**
     * 服务介绍
     */
    private String introduce;

    /**
     * 服务图片1
     */
    private String cimgOne;

    /**
     * 服务图片2
     */
    private String cimgTwo;

    /**
     * 服务图片3
     */
    private String cimgThree;

    private Integer version;

    /**
     * @return 品名id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            品名id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 服务名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            服务名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 操作人id
     */
    public Integer getOperationId() {
        return operationId;
    }

    /**
     * @param operationId 
	 *            操作人id
     */
    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    /**
     * @return 服务分类id
     */
    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    /**
     * @param serviceTypeId 
	 *            服务分类id
     */
    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    /**
     * @return 价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price 
	 *            价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return 服务说明
     */
    public String getDescribes() {
        return describes;
    }

    /**
     * @param describes 
	 *            服务说明
     */
    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    /**
     * @return 服务介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * @param introduce 
	 *            服务介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * @return 服务图片1
     */
    public String getCimgOne() {
        return cimgOne;
    }

    /**
     * @param cimgOne 
	 *            服务图片1
     */
    public void setCimgOne(String cimgOne) {
        this.cimgOne = cimgOne == null ? null : cimgOne.trim();
    }

    /**
     * @return 服务图片2
     */
    public String getCimgTwo() {
        return cimgTwo;
    }

    /**
     * @param cimgTwo 
	 *            服务图片2
     */
    public void setCimgTwo(String cimgTwo) {
        this.cimgTwo = cimgTwo == null ? null : cimgTwo.trim();
    }

    /**
     * @return 服务图片3
     */
    public String getCimgThree() {
        return cimgThree;
    }

    /**
     * @param cimgThree 
	 *            服务图片3
     */
    public void setCimgThree(String cimgThree) {
        this.cimgThree = cimgThree == null ? null : cimgThree.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}