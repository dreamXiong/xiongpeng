package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWebRole;
import java.util.List;

public interface TbWebRoleService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWebRole selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWebRole> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWebRole record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWebRole record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWebRole record) throws RuntimeException;
}