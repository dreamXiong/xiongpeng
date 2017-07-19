package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbIntegralRules implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 推荐规则id
     */
    private Integer id;

    /**
     * 经销商id
     */
    private Integer shopId;

    /**
     * 类型：0 百分比，1 满送
     */
    private Integer type;

    /**
     * 满送金额
     */
    private Integer money;

    /**
     * 购买金额
     */
    private Integer payMoney;

    /**
     * 使用情况：0 使用中，1 禁用
     */
    private Integer useSituation;

    /**
     * 版本
     */
    private Integer version;

    /**
     * @return 推荐规则id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            推荐规则id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 经销商id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            经销商id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 类型：0 百分比，1 满送
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型：0 百分比，1 满送
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 满送金额
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * @param money 
	 *            满送金额
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * @return 购买金额
     */
    public Integer getPayMoney() {
        return payMoney;
    }

    /**
     * @param payMoney 
	 *            购买金额
     */
    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    /**
     * @return 使用情况：0 使用中，1 禁用
     */
    public Integer getUseSituation() {
        return useSituation;
    }

    /**
     * @param useSituation 
	 *            使用情况：0 使用中，1 禁用
     */
    public void setUseSituation(Integer useSituation) {
        this.useSituation = useSituation;
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