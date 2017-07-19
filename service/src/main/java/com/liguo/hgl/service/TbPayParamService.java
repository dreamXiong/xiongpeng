package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayParam;
import java.util.List;

public interface TbPayParamService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbPayParam selectByPrimaryKey(String orderGroubNo) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbPayParam> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String orderGroubNo) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbPayParam record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbPayParam record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbPayParam record) throws RuntimeException;
    
}