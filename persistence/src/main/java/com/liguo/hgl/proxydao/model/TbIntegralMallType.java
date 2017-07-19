package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbIntegralMallType implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 商品类型名称
     */
    private String goodsTypeName;

    /**
     * 商品类型图片
     */
    private String goodsTypeImage;

    /**
     * 商品类型描述
     */
    private String goodsTypeDescribe;

    /**
     * 商品类型状态: 0下架,1上架
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
     * @return 商品类型名称
     */
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    /**
     * @param goodsTypeName 
	 *            商品类型名称
     */
    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName == null ? null : goodsTypeName.trim();
    }

    /**
     * @return 商品类型图片
     */
    public String getGoodsTypeImage() {
        return goodsTypeImage;
    }

    /**
     * @param goodsTypeImage 
	 *            商品类型图片
     */
    public void setGoodsTypeImage(String goodsTypeImage) {
        this.goodsTypeImage = goodsTypeImage == null ? null : goodsTypeImage.trim();
    }

    /**
     * @return 商品类型描述
     */
    public String getGoodsTypeDescribe() {
        return goodsTypeDescribe;
    }

    /**
     * @param goodsTypeDescribe 
	 *            商品类型描述
     */
    public void setGoodsTypeDescribe(String goodsTypeDescribe) {
        this.goodsTypeDescribe = goodsTypeDescribe == null ? null : goodsTypeDescribe.trim();
    }

    /**
     * @return 商品类型状态: 0下架,1上架
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            商品类型状态: 0下架,1上架
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
}