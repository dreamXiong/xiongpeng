package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.TbCompanyConsultDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyConsult;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbCompanyConsultService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCompanyConsult selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCompanyConsult> selectByObject(Criteria example) throws RuntimeException;
    
    /**
     * 根据指定条件 分页查询记录
     */
    List<TbCompanyConsultDto> selectByObjectPage(Criteria example,PageDto page);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCompanyConsult record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCompanyConsult record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCompanyConsult record) throws RuntimeException;
}