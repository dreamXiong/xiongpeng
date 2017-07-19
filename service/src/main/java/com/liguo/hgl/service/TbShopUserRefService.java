package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopUserRef;

public interface TbShopUserRefService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbShopUserRef selectByPrimaryKey(TbShopUserRef key) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbShopUserRef> selectByObject(Criteria criteria) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(TbShopUserRef key) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbShopUserRef record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbShopUserRef record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbShopUserRef record) throws RuntimeException;
    
    /**
     * 根据管理员查询出非此管理员的工厂信息
     */
    List<Map<String,Object>> findNotUserList(TbShopUserRef record) throws RuntimeException;
    /**
     * @Description:查询店铺管理员
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
     */
    public List<Integer> selectShopListByUserID(Integer userId) throws RuntimeException;
}