package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbRecommended;

public interface TbRecommendedService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbRecommended selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbRecommended> selectByObject(Criteria example) throws RuntimeException;

    

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbRecommended record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbRecommended record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbRecommended record) throws RuntimeException;
    
    /**
     * 执行返利任务
     * */
    public int rebate() throws TransactionException;
}