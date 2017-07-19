package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;

public interface TbIntegralService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbIntegral selectByPrimaryKey(Integer id) throws RuntimeException;
    
    /**
     * 根据UserID查询记录
     */
    TbIntegral selectByUserIdObject(Criteria example) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbIntegral> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbIntegral record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbIntegral record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbIntegral record) throws RuntimeException;
    
    /**
     * 保存属性不为空的记录
     */
    int insert(TbIntegral record) throws RuntimeException;
    
    /**
     * 增加积分
     * @param userId 用户名
     * @param integral 增加的积分
     * @param orderId 订单ID
     * @param orderSerialNo 订单号
     */
    void addIntegral(Integer userId,Integer integral,Integer orderId,String orderSerialNo,Integer type);
    
}