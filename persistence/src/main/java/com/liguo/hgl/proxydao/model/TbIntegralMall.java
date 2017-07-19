package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbIntegralMall implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 积分
     */
    private Integer integral;

    /**
     * 兑换后支付金额
     */
    private Double payAmount;

    /**
     * 商品的市场价格
     */
    private Double marketAmount;

    /**
     * 商品图片
     */
    private String goodsImage;

    /**
     * 商品描述
     */
    private String goodsDescribe;

    /**
     * 注意事项
     */
    private String attentionMatters;

    /**
     * 兑换流程
     */
    private String exchangeProcess;

    /**
     * 商品状态: 0下架,1上架
     */
    private Integer status;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 积分商城类型ID
     */
    private Integer integralMallTypeId;
    
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
     * @return 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * @param goodsName 
	 *            商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * @return 积分
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * @param integral 
	 *            积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * @return 兑换后支付金额
     */
    public Double getPayAmount() {
        return payAmount;
    }

    /**
     * @param payAmount 
	 *            兑换后支付金额
     */
    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * @return 商品的市场价格
     */
    public Double getMarketAmount() {
        return marketAmount;
    }

    /**
     * @param marketAmount 
	 *            商品的市场价格
     */
    public void setMarketAmount(Double marketAmount) {
        this.marketAmount = marketAmount;
    }

    /**
     * @return 商品图片
     */
    public String getGoodsImage() {
        return goodsImage;
    }

    /**
     * @param goodsImage 
	 *            商品图片
     */
    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage == null ? null : goodsImage.trim();
    }

    /**
     * @return 商品描述
     */
    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    /**
     * @param goodsDescribe 
	 *            商品描述
     */
    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe == null ? null : goodsDescribe.trim();
    }

    /**
     * @return 注意事项
     */
    public String getAttentionMatters() {
        return attentionMatters;
    }

    /**
     * @param attentionMatters 
	 *            注意事项
     */
    public void setAttentionMatters(String attentionMatters) {
        this.attentionMatters = attentionMatters == null ? null : attentionMatters.trim();
    }

    /**
     * @return 兑换流程
     */
    public String getExchangeProcess() {
        return exchangeProcess;
    }

    /**
     * @param exchangeProcess 
	 *            兑换流程
     */
    public void setExchangeProcess(String exchangeProcess) {
        this.exchangeProcess = exchangeProcess == null ? null : exchangeProcess.trim();
    }

    /**
     * @return 商品状态: 0下架,1上架
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            商品状态: 0下架,1上架
     */
    public void setStatus(Integer status) {
        this.status = status;
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
    
    /**
     * @return 积分商城类型ID
     */
    public Integer getIntegralMallTypeId() {
        return integralMallTypeId;
    }

    /**
     * @param integralMallTypeId 
	 *            积分商城类型ID
     */
    public void setIntegralMallTypeId(Integer integralMallTypeId) {
        this.integralMallTypeId = integralMallTypeId;
    }
}