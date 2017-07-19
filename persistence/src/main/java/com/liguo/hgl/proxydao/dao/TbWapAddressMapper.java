package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapAddress;
import java.util.List;

public interface TbWapAddressMapper extends BaseMapper {
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
    int insert(TbWapAddress record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapAddress record);

    /**
     * 根据条件查询记录集
     */
    List<TbWapAddress> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbWapAddress selectByPrimaryKey(Criteria parameter);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapAddress record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapAddress record);
    
    TbWapAddress selectByDefaultObject(Criteria parameter);
    
    /**
     * 根据主键更新记录
     */
    int updateByDefaultPrimaryKey(TbWapAddress record);
    
    /**
     * 更新操作
     * @param record
     * @return
     */
    int updateByNamePrimaryKey(TbWapAddress record) ;
}