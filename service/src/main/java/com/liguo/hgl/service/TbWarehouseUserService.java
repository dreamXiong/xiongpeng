package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWarehouseUser;

public interface TbWarehouseUserService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWarehouseUser selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWarehouseUser> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
   /* DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException;*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWarehouseUser record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWarehouseUser record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWarehouseUser record) throws RuntimeException;
    
    /**
     * @Description:根据仓库查询该仓库下所有订单
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
     */
    List<Integer> selectOrderByWarehouseIds(Integer userId);
}