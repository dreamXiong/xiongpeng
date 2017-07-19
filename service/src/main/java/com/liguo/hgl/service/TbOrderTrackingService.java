package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.OrderTrackingDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbOrderTracking;

public interface TbOrderTrackingService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbOrderTracking selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbOrderTracking> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
  /*  DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException;*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbOrderTracking record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbOrderTracking record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbOrderTracking record) throws RuntimeException;
    
    /*取多状态记录*/
    List<TbOrderTracking> selectByObjectStatus(Criteria example);
    
    List<OrderTrackingDto> selectByObjectDto(Criteria example);
}