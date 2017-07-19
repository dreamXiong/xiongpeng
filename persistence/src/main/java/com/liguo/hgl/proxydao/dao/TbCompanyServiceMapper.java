package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.dto.TbCompanyServiceDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCompanyService;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbCompanyServiceMapper extends BaseMapper {
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
    int insert(TbCompanyService record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbCompanyService record);

    /**
     * 根据条件查询记录集
     */
    List<TbCompanyService> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbCompanyService selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbCompanyService record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbCompanyService record);
    
    public List<TbCompanyServiceDto> selectTbCompanyServicePage(Criteria criteria, PageDto page);
    
    public List<TbCompanyServiceDto> selectTbCompanyService(Criteria criteria);
}