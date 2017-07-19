package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbCountryInfo;

import java.util.List;

public interface TbCountryInfoMapper extends BaseMapper {
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
    int insert(TbCountryInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCountryInfo record);

    /**
     * 根据条件查询记录集
     */
    List<TbCountryInfo> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbCountryInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCountryInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCountryInfo record);
    
    List<TbCityInfo> findBatchCountryById(Criteria example) throws RuntimeException;
}