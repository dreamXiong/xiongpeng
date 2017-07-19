package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralMallType;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbIntegralMallTypeService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbIntegralMallType selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbIntegralMallType> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbIntegralMallType> selectByObject(Criteria example,PageDto pgo) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbIntegralMallType record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbIntegralMallType record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbIntegralMallType record) throws RuntimeException;
    

    /**
     * 保存属性记录
     */
    int insert(TbIntegralMallType record) throws RuntimeException;
}