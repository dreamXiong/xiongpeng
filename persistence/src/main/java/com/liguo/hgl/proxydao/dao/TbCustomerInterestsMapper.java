package com.liguo.hgl.proxydao.dao;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.CustomerInterestsDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCustomerInterests;

public interface TbCustomerInterestsMapper extends BaseMapper {
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
    int insert(TbCustomerInterests record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCustomerInterests record);

    /**
     * 根据条件查询记录集
     */
    List<TbCustomerInterests> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbCustomerInterests selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCustomerInterests record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCustomerInterests record);
    
    /**
     * 调用存储过程
     * @param example
     * @return
     */
    void callCustomerInterests(Map<String,Integer> rowCountMap);
    
    /**
     * 查询出数据分析报表
     * @param example
     * @return
     * @throws RuntimeException
     */
    List<CustomerInterestsDto> selectByDataInfo(Criteria example);
    
    /**
     * 查询出数据分析报表求和的数据
     * @param example
     * @return
     * @throws RuntimeException
     */
    TbCustomerInterests selectByDataInfoSum(Criteria example) ;
}