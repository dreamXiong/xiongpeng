package com.liguo.hgl.proxydao.dao;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.base.BaseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopUserRef;

public interface TbShopUserRefMapper extends BaseMapper {
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
    int deleteByPrimaryKey(TbShopUserRef key);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(TbShopUserRef record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbShopUserRef record);

    /**
     * 根据条件查询记录集
     */
    List<TbShopUserRef> selectByObject(Criteria example);

    /**
     * 根据主键查询记录
     */
    TbShopUserRef selectByPrimaryKey(TbShopUserRef key);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbShopUserRef record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbShopUserRef record);
    
    
    /**
     * 根据管理员查询出非此管理员的工厂信息
     */
    List<Map<String,Object>> findNotUserList(TbShopUserRef record);
    
    public List<Integer> selectShopListByUser(Integer userId) throws RuntimeException ;
    
}