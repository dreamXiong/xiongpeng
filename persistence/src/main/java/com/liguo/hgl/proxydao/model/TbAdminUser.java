package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbAdminUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 常用地址
     */
    private String address;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 姓名
     */
    private String name;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 态状(用启132,停用134)
     */
    private Integer status;

    /**
     * 扩展字段_身份证
     */
    private String idCard;

    /**
     * 备注,扩展字段
     */
    private String remark;
    /**
     * 推荐码
     */
    private String recommendCode;

    private Integer version;

    /**
     * @return 用户id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            用户id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 登录用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 
	 *            登录用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 
	 *            密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return 常用地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            常用地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 
	 *            邮箱地址
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile 
	 *            手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 
	 *            角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 态状(用启132,停用134)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            态状(用启132,停用134)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 扩展字段_身份证
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * @param idCard 
	 *            扩展字段_身份证
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    /**
     * @return 备注,扩展字段
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注,扩展字段
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode == null ? null : recommendCode.trim();
    }

    public String getRecommendCode() {
        return recommendCode;
    }
    
    
}