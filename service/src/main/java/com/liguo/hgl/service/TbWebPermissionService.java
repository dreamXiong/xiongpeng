package com.liguo.hgl.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.model.TbWebPermission;
import com.liguo.hgl.proxydao.model.TbWebUser;

public interface TbWebPermissionService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbWebPermission selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbWebPermission> selectByObject(Criteria example) throws RuntimeException;

    /***
     * 
     * <返回Map>
     * @param example
     * @return
     * @throws RuntimeException
     * @author wzt
     * @since   2016年5月6日
     */
    List<Map<String,Object>> selectByObjectToMap(Criteria example) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbWebPermission record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbWebPermission record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbWebPermission record) throws RuntimeException;
    
    /**
     * 查询角色拥有的权限
     */
    List<TbWebPermission> selectByStringList(List<String> strList)throws RuntimeException;
    
    /*根据URL查询用户权限*/
    TbWebPermission selectByUrl(Criteria example) throws RuntimeException;
    
    /*获取用户是否有访问某一URL的权限---前台用户*/
    boolean getUserLicense(TbWebUser webUser,String url);   
}