package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityLowestPrice;

import java.util.List;
import java.util.Map;

public interface TbCityLowestPriceMapper extends BaseMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByObject(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByObject(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbCityLowestPrice record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCityLowestPrice record);

    /**
     * 根据条件查询记录集
     */
    List<TbCityLowestPrice> selectByObject(Criteria example);
    
    /**
     * 根据条件查询记录集
     */
    List<String> selectCityIds();

    /**
     * 根据主键查询记录
     */
    TbCityLowestPrice selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCityLowestPrice record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCityLowestPrice record);
    
    
    List<Map<String,Object>> findLowestPriceBatchByCityId(List<String>list) throws RuntimeException;
    
    void insertLowestPriceBatch(List<String> strList) throws RuntimeException;
    
    int deleteLowestPrice_Batch(List<String> strList) throws RuntimeException;
}