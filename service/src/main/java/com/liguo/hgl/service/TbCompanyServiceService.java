package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.proxydao.dto.TbCompanyServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyService;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbCompanyServiceService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbCompanyService selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbCompanyService> selectByObject(Criteria example) throws RuntimeException;
    
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCompanyService record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCompanyService record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCompanyService record) throws RuntimeException;
    
    /**
     * 服务公司分页列表 
     */
    List<TbCompanyServiceDto> selectTbCompanyServicePage(Criteria criteria,PageDto page);
    
    /**
     * 服务公司列表 
     */
    List<TbCompanyServiceDto> selectTbCompanyService(Criteria criteria);
    
    /**
     * 保存服务公司信息 
     */
    public int saveAddInfo(TbCompanyService record) throws Exception;
    /**
     * 更新服务公司信息 
     */
    public int saveUpdateInfo(TbCompanyService record) throws Exception;
}