package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbUserInfo;
import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;
import java.util.Map;

public interface TbUserInfoMapper extends BaseMapper {
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
    int insert(TbUserInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbUserInfo record);

    /**
     * 根据条件查询记录集
     */
    List<TbUserInfo> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbUserInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbUserInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbUserInfo record);
    /**
     * 根据主键更新记录
     */
    int updateEvaluateNumAvg(Criteria example);
    
    /**
     * 根据条件查询记录集
     */
    List<Map<String, Object>> findMasterByShopId(Criteria criteria,PageDto page);
}