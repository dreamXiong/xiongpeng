package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.ServiceTypeDto;
import com.liguo.hgl.proxydao.dto.WapServiceTypeDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbServiceType;
import java.util.List;

public interface TbServiceTypeMapper extends BaseMapper {
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
    int insert(TbServiceType record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbServiceType record);

    /**
     * 根据条件查询记录集
     */
    List<TbServiceType> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbServiceType selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbServiceType record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbServiceType record);
    
    List<ServiceTypeDto> selectServiceTypeByIds(Criteria example);
    
    /*根据主键查询返回Dto记录*/
    ServiceTypeDto selectDtoByPrimaryKey(Integer id);
    
    /*查询所有符合条件的Dto记录*/
    List<ServiceTypeDto> selectDtoByObject(Criteria example);
    
    List<TbServiceType> selectByWap();
    
    public List<WapServiceTypeDto> selectServiceType();
}