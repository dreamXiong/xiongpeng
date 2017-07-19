package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralRules;

public interface TbIntegralRulesMapper extends BaseMapper {
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
    int insert(TbIntegralRules record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbIntegralRules record);

    /**
     * 根据条件查询记录集
     */
    List<TbIntegralRules> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbIntegralRules selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbIntegralRules record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbIntegralRules record);
    
    /**
     * 批量插入记录
     */
    int batchInsert(List<TbIntegralRules> record);
    
    /**
     * 批量更新记录
     */
    int batchUpdate(List<TbIntegralRules> record);
    
    /**
     * @Description:根据用户支付金额查询符合条件的店铺积分规则
    * @author:ZK 
    * @date:2016-8-4
    * @parameter:
     */
    TbIntegralRules selectIntegralByMoney(Criteria parameter);
    
    /**
     * @Description:查询正在使用的店铺积分规则
    * @author:ZK 
    * @date:2016-8-4
    * @parameter:
     */
    List<TbIntegralRules> selectUsedRule(Criteria parameter);
}