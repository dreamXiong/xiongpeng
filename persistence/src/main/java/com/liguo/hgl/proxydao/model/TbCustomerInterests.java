package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbCustomerInterests implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 客户ID
     */
    private Integer userId;

    /**
     * 客户名称
     */
    private String userName;

    /**
     * 盈利
     */
    private Double profitMoney;

    /**
     * 销售额
     */
    private Double salePrice;

    /**
     * 推荐人数
     */
    private Integer recommendCount;

    /**
     * 总盈利
     */
    private Double totalProfit;

    /**
     * 推荐人
     */
    private String recommendName;

    /**
     * 创建时间
     */
    private Long createDt;

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
     * @return 店铺ID
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            店铺ID
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 客户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            客户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return 客户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 
	 *            客户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return 盈利
     */
    public Double getProfitMoney() {
        return profitMoney;
    }

    /**
     * @param profitMoney 
	 *            盈利
     */
    public void setProfitMoney(Double profitMoney) {
        this.profitMoney = profitMoney;
    }

    /**
     * @return 销售额
     */
    public Double getSalePrice() {
        return salePrice;
    }

    /**
     * @param salePrice 
	 *            销售额
     */
    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * @return 推荐人数
     */
    public Integer getRecommendCount() {
        return recommendCount;
    }

    /**
     * @param recommendCount 
	 *            推荐人数
     */
    public void setRecommendCount(Integer recommendCount) {
        this.recommendCount = recommendCount;
    }

    /**
     * @return 总盈利
     */
    public Double getTotalProfit() {
        return totalProfit;
    }

    /**
     * @param totalProfit 
	 *            总盈利
     */
    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    /**
     * @return 推荐人
     */
    public String getRecommendName() {
        return recommendName;
    }

    /**
     * @param recommendName 
	 *            推荐人
     */
    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName == null ? null : recommendName.trim();
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