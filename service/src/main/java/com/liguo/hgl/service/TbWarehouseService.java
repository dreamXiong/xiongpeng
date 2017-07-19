package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWarehouse;
import com.liguo.hgl.proxydao.model.WarehouseDto;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbWarehouseService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWarehouse selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据条件查询记录
     */
    List<TbWarehouse> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据条件查询记录、分页
     */
    List<WarehouseDto> selectByObject(Criteria example,PageDto page) throws RuntimeException;
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWarehouse record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWarehouse record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWarehouse record) throws RuntimeException;
    
    
}