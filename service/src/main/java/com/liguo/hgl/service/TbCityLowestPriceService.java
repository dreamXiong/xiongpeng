package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityLowestPrice;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;

public interface TbCityLowestPriceService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCityLowestPrice selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCityLowestPrice> selectByObject(Criteria example) throws RuntimeException;


    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCityLowestPrice record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCityLowestPrice record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCityLowestPrice record) throws RuntimeException;
    
    /**
     * 查询一个市下面所有区的最低价格
     */
    List<Map<String,Object>> findLowestPriceBatchByCityId(List<String>list) throws RuntimeException;
    
    /**
     * 市最低价设置
     */
    List<String> InsertOrDelLowestPrice(TbOpenedRegional tbOpenedRegional) throws RuntimeException;
}