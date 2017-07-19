package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralRules;

public interface TbIntegralRulesService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbIntegralRules selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbIntegralRules> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbIntegralRules record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbIntegralRules record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbIntegralRules record) throws RuntimeException;
    
    /**
     * 批量插入记录
     */
    int batchInsert(List<TbIntegralRules> record);
    
    /**
     * 批量更新记录
     */
    int batchUpdate(List<TbIntegralRules> record);
    
    /**
     * @Description:查询正在使用的店铺积分规则
    * @author:ZK 
    * @date:2016-8-4
    * @parameter:
     */
    List<TbIntegralRules> selectUsedRule(Integer shopId);
    
}