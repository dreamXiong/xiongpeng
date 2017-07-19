package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWebUser implements Serializable {
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
     * 类型id
     */
    private Integer typeId;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 用户详情id
     */
    private Integer userinfoId;

    /**
     * 扩展字段_身份证
     */
    private String idCard;

    /**
     * 备注,扩展字段
     */
    private String remark;

    private Integer version;

    /**
     * 用户状态
     */
    private Integer state;

    /**
     * 审核描述
     */
    private String auditDescription;

    /**
     * 我的标识码/推荐码
     */
    private String logoCode;

    /**
     * 被推荐码/我被推荐
     */
    private String recommendCode;

    /**
     * 定绑shopId
     */
    private Integer recommendShopId;

    /**
     * 微信openid
     */
    private String openId;

    /**
     * 是否自动登录, 1自动
     */
    private Integer autoLoginFlag;

    /**
     * 用户组
     */
    private String userGroup;

    /**
     * 创建时间
     */
    private Long createDt;

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
     * @return 类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId 
	 *            类型id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * @return 店铺id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            店铺id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 用户详情id
     */
    public Integer getUserinfoId() {
        return userinfoId;
    }

    /**
     * @param userinfoId 
	 *            用户详情id
     */
    public void setUserinfoId(Integer userinfoId) {
        this.userinfoId = userinfoId;
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

    /**
     * @return 用户状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state 
	 *            用户状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return 审核描述
     */
    public String getAuditDescription() {
        return auditDescription;
    }

    /**
     * @param auditDescription 
	 *            审核描述
     */
    public void setAuditDescription(String auditDescription) {
        this.auditDescription = auditDescription == null ? null : auditDescription.trim();
    }

    /**
     * @return 我的标识码/推荐码
     */
    public String getLogoCode() {
        return logoCode;
    }

    /**
     * @param logoCode 
	 *            我的标识码/推荐码
     */
    public void setLogoCode(String logoCode) {
        this.logoCode = logoCode == null ? null : logoCode.trim();
    }

    /**
     * @return 被推荐码/我被推荐
     */
    public String getRecommendCode() {
        return recommendCode;
    }

    /**
     * @param recommendCode 
	 *            被推荐码/我被推荐
     */
    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode == null ? null : recommendCode.trim();
    }

    /**
     * @return 定绑shopId
     */
    public Integer getRecommendShopId() {
        return recommendShopId;
    }

    /**
     * @param recommendShopId 
	 *            定绑shopId
     */
    public void setRecommendShopId(Integer recommendShopId) {
        this.recommendShopId = recommendShopId;
    }

    /**
     * @return 微信openid
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId 
	 *            微信openid
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * @return 是否自动登录, 1自动
     */
    public Integer getAutoLoginFlag() {
        return autoLoginFlag;
    }

    /**
     * @param autoLoginFlag 
	 *            是否自动登录, 1自动
     */
    public void setAutoLoginFlag(Integer autoLoginFlag) {
        this.autoLoginFlag = autoLoginFlag;
    }

    /**
     * @return 用户组
     */
    public String getUserGroup() {
        return userGroup;
    }

    /**
     * @param userGroup 
	 *            用户组
     */
    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup == null ? null : userGroup.trim();
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
}