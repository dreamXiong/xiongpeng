package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;

public interface TbOpenedRegionalService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbOpenedRegional selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbOpenedRegional> selectByObject(Criteria example) throws RuntimeException;


    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbOpenedRegional record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbOpenedRegional record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbOpenedRegional record) throws RuntimeException;
    /**
     * 保存属性不为空的记录
     */
    List<TbOpenedRegional> findOpenedRegByproviceId(Criteria example) throws RuntimeException;
    
    
    /**
     * 根据名称查询是否开通该城市
     */
    TbOpenedRegional selectByNameOpenCity(Criteria example);
}