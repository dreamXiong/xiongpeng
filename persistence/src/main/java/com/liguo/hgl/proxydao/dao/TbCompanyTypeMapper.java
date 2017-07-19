package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyType;
import java.util.List;

public interface TbCompanyTypeMapper extends BaseMapper {
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
    int insert(TbCompanyType record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCompanyType record);

    /**
     * 根据条件查询记录集
     */
    List<TbCompanyType> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbCompanyType selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCompanyType record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCompanyType record);
    
    /**
     * 查询有公司的类型
     */
    public List<TbCompanyType> selectCompanyTypeInfo();
}