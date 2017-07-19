package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.WapOrderTrackingDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderTracking;

public interface TbWapOrderTrackingService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWapOrderTracking selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWapOrderTracking> selectByObject(Criteria example) throws RuntimeException;

   /* *//**
     * 根据指定条件查询记录
     *//*
    DataPackage getDatapackage(Criteria example, int lines, int page) throws RuntimeException;*/

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWapOrderTracking record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWapOrderTracking record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWapOrderTracking record) throws RuntimeException;
    
    List<WapOrderTrackingDto> selectByObjectDto(Criteria example) ;
    
    List<WapOrderTrackingDto> selectByObjectStatus(Criteria example) ;
    
    /***
     * 
     * <批量 insert订单跟踪表>
     * @param example
     * @return
     * @throws RuntimeException
     * @author wzt
     * @since   2016年7月18日
     */
    int  insertOrderTrackIsBatch(Criteria example) throws RuntimeException;
    
    
    
    
    
}
