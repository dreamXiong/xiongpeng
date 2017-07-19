package com.liguo.hgl.service;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminPermission;
import com.liguo.hgl.proxydao.model.TbAdminUser;

import java.util.List;
import java.util.Map;

public interface TbAdminPermissionService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbAdminPermission selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbAdminPermission> selectByObject(Criteria example) throws RuntimeException;
    
    /**
     * 根据指定条件查询记录返回Map
     */
    List<Map<String,Object>> selectByObjectToMap(Criteria example) throws RuntimeException;


    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbAdminPermission record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbAdminPermission record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbAdminPermission record) throws RuntimeException;
    /**
     * 查询角色拥有的权限
     */
    List<TbAdminPermission> selectByStringList(List<String> strList)throws RuntimeException;
    
    TbAdminPermission selectByURL(Criteria criteria) throws RuntimeException;
    
    /*获取用户是否有访问某一URL的权限---后台用户*/
    boolean getAdminUserLicense(TbAdminUser adminUser,String url);
}