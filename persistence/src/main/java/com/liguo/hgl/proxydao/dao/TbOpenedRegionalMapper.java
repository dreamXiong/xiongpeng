package com.liguo.hgl.proxydao.dao;

import java.util.List;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;

public interface TbOpenedRegionalMapper extends BaseMapper {
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
    int insert(TbOpenedRegional record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbOpenedRegional record);

    /**
     * 根据条件查询记录集
     */
    List<TbOpenedRegional> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbOpenedRegional selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbOpenedRegional record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbOpenedRegional record);
    
    /***
     * 
     * <批量查询已开通区域>
     * @param tbCityInfo 市id
     * @return
     * @throws RuntimeException
     * @author wzt
     * @since   2016年6月12日
     */
    List<TbOpenedRegional> findOpenedRegByproviceId(Criteria example) throws RuntimeException;
    
    /**
     * 根据名称查询是否开通该城市
     */
    TbOpenedRegional selectByNameOpenCity(Criteria example);
}