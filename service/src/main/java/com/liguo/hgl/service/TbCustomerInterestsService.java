package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.dto.CustomerInterestsDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCustomerInterests;

public interface TbCustomerInterestsService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCustomerInterests selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCustomerInterests> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCustomerInterests record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCustomerInterests record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCustomerInterests record) throws RuntimeException;
    
    /**
     * 调用存储过程
     * @param example
     * @return
     */
    int callCustomerInterests(Map<String,Integer> rowCountMap) throws RuntimeException;
    
    /**
     * 查询出数据分析报表
     * @param example
     * @return
     * @throws RuntimeException
     */
    List<CustomerInterestsDto> selectByDataInfo(Criteria example) throws RuntimeException;
    
    /**
     * 查询出数据分析报表求和的数据
     * @param example
     * @return
     * @throws RuntimeException
     */
    TbCustomerInterests selectByDataInfoSum(Criteria example) throws RuntimeException;
}