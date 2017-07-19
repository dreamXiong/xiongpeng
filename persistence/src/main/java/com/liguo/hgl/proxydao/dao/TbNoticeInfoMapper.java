package com.liguo.hgl.proxydao.dao;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbBrand;
import com.liguo.hgl.proxydao.model.TbNoticeInfo;
import com.liguo.hgl.proxydao.page.PageDto;

import java.util.List;

public interface TbNoticeInfoMapper extends BaseMapper {
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
    int insert(TbNoticeInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbNoticeInfo record);

    /**
     * 根据条件查询记录集
     */
    List<TbNoticeInfo> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbNoticeInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbNoticeInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbNoticeInfo record);
    
    
    public List<TbNoticeInfo> selectByObjectPage(Criteria example,PageDto pgo);
}