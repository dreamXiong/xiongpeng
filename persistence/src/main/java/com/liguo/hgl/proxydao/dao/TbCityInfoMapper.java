package com.liguo.hgl.proxydao.dao;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;

public interface TbCityInfoMapper extends BaseMapper {
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
    int insert(TbCityInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCityInfo record);

    /**
     * 根据条件查询记录集
     */
    List<TbCityInfo> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbCityInfo selectByPrimaryKey(Integer id);
    
    /**
     * 根据主键查询记录
     */
    List<Map<String,Object>> findCityByProvice(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCityInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCityInfo record);
    
    
    List<TbCityInfo> findBatchCityById(List<String> tbOpenedRegionalList) throws RuntimeException;
    
    /**
     * 根据主键更新记录
     */
    String searchCity(Criteria example);
}