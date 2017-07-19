package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbInventoryLock;

public interface TbInventoryLockService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbInventoryLock selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbInventoryLock> selectByObject(Criteria example) throws RuntimeException;

   /* *//**
     * 根据指定条件查询记录
     *//*
    DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException;*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbInventoryLock record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbInventoryLock record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbInventoryLock record) throws RuntimeException;
    
    /**
     * 订单提交时批量插入锁库存表
     * */
    public int  batchInsertSelective(List<TbInventoryLock> record) throws RuntimeException ;
    
    /**
     * @Description:批量删除锁库表记录
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:String
     */
    public int batchDeleteTbInventoryLock(List<Integer> record);
}