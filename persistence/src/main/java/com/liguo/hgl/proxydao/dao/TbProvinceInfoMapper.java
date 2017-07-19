package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProvinceInfo;
import java.util.List;

public interface TbProvinceInfoMapper extends BaseMapper {
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
    int insert(TbProvinceInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbProvinceInfo record);

    /**
     * 根据条件查询记录集
     */
    List<TbProvinceInfo> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbProvinceInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbProvinceInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbProvinceInfo record);
    
    public String selectAddressByCode(Criteria parameter);
}