package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyType;

public interface TbCompanyTypeService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCompanyType selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCompanyType> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCompanyType record) throws RuntimeException;
    
    /**
     * @Description:服务公司分类信息保存
    * @author:ZK 
    * @date:2016-7-25
    * @parameter:
     */
    int insertTbCompanyType(TbCompanyType record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCompanyType record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCompanyType record) throws RuntimeException;
    
    /**
     * @Description:类型更新操作
    * @author:ZK 
    * @date:2016-7-27
    * @parameter:
     */
    int updateTbCompanyTypeInfo(TbCompanyType record);
    
    /**
     * 查询有服务公司的类型
     */
    List<TbCompanyType> selectCompanyTypeInfo();
}