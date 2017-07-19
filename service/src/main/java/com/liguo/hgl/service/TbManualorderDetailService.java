package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.ManualOrderDetailDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbManualorderDetail;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbManualorderDetailService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbManualorderDetail selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbManualorderDetail> selectByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbManualorderDetail record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbManualorderDetail record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbManualorderDetail record) throws RuntimeException;
    
    List<ManualOrderDetailDto> selectObjectByPage(Criteria example,PageDto page) throws RuntimeException;
    
}