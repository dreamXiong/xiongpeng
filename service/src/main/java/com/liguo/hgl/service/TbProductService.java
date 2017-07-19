package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.TbProductDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.ProductInfoDto;
import com.liguo.hgl.proxydao.model.TbProduct;
import com.liguo.hgl.proxydao.model.TbProductTestDto;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbProductService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;
    
    /**
     * 插入信息
     */
    public int insertProduct(TbProduct record) throws RuntimeException;
    
    /**
     * 查询更新信息
     */
    public ProductInfoDto selectUpdateInfo(Integer id);
    
    /**
     * 更新信息
     */
    public int updateProduct(TbProduct record) throws RuntimeException;
    
    /**
     * 根据产品名称查询
     */
    public List<ProductInfoDto> selectInfoListByName(String name,PageDto page);
    /**
     * 根据主键查询记录
     */
    TbProduct selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbProduct> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 分页查询记录
     */
    public List<TbProductDto> selectByCriteria(Criteria criteria, PageDto pgo)throws RuntimeException;
    
    /**
     * 查询记录
     */
    public List<TbProductDto> selectByCriteria(Criteria criteria)throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbProduct record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbProduct record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbProduct record) throws RuntimeException;
    
    /**
     * 根据ID查询记录
     */
    TbProductDto selectProductDtoByPrimaryKey(Integer id)throws RuntimeException;
    /**
     * 分页查询记录
     */
   /* public List<TbProductTestDto> selectTest();*/
    
    /**
     * 根据产品ID查询厂家ID
     * */
    public Integer selectShopIdByPId(Integer productId);
}