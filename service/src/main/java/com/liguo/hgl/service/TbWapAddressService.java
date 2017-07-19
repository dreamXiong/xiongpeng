package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapAddress;
import java.util.List;

public interface TbWapAddressService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWapAddress selectByPrimaryKey(Criteria parameter) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWapAddress> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapAddress record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapAddress record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapAddress record) throws RuntimeException;
    
    /**
     * 保存属性不为空的记录
     */
    int insert(TbWapAddress record) throws RuntimeException;
    
    /**
     * 根据条件删除记录
     */
    int deleteByObject(Criteria example);
    
    /**
     * 根据id查询出默认地址
     * @param id
     * @param newId
     * @param userId
     * @return
     */
    int defaultAddress(String id,String newId,Integer userId);
    
    /**
     * 根据条件查询出地址对象
     * @param parameter
     * @return
     */
    TbWapAddress selectByDefaultObject(Criteria parameter);
}