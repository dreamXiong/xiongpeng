package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.ServiceTypeDto;
import com.liguo.hgl.proxydao.dto.WapServiceTypeDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbServiceType;

public interface TbServiceTypeService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbServiceType selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbServiceType> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 查询有子分类的大类
     */
    List<TbServiceType> selectByWap();
    
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbServiceType record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbServiceType record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbServiceType record) throws RuntimeException;
    
    List<ServiceTypeDto> selectServiceTypeByIds(Criteria example) throws RuntimeException;
    
    ServiceTypeDto selectDtoByPrimaryKey(Integer id) throws RuntimeException;
    
    public List<ServiceTypeDto> selectDtoByObject(Criteria example) throws RuntimeException;
    
    /**
     * @Description:查询所有服务类型
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
     */
    public List<WapServiceTypeDto> selectServiceType();
    
    /*查找技能*/
    public List<ServiceTypeDto> getSkills(Integer userInfoId);
}