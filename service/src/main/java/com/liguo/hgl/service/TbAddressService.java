package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAddress;
import java.util.List;

public interface TbAddressService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbAddress selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbAddress> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbAddress record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbAddress record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbAddress record) throws RuntimeException;
    
    /**
     * 保存属性不为空的记录
     */
    int insert(TbAddress record) throws RuntimeException;
    
    /**
     * 根据条件删除记录
     */
    int deleteByObject(Criteria example);
    
    /**
     * 查询默认地址 
     * @param id
     * @param newId
     * @param userId
     * @return
     */
    int defaultAddress(String id,String newId,Integer userId);
}