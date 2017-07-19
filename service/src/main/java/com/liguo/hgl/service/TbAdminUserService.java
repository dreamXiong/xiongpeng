package com.liguo.hgl.service;

import java.util.List;

import com.liguo.hgl.common.HglContants.CheckType;
import com.liguo.hgl.proxydao.dto.TbAdminUserDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAdminUser;
import com.liguo.hgl.proxydao.page.PageDto;

public interface TbAdminUserService {
    /**
     * 根据条件获取总行数
     */
    int countByObject(Criteria example) throws RuntimeException;

    /**
     * 根据主键查询记录
     */
    TbAdminUser selectByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据指定条件查询记录
     */
    List<TbAdminUser> selectByObject(Criteria example) throws RuntimeException;
    
    /**
     * 
    * @Description:根据仓库ID查询出该仓库下所有的管理员
    * @author:ZK 
    * @date:2016-7-18
    * @parameter:
    * @return:List<TbAdminUser>
     */
    List<TbAdminUser> selectByTbWarehouseId(Integer tbWIntegerId) throws RuntimeException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id) throws RuntimeException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(TbAdminUser record) throws RuntimeException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(TbAdminUser record) throws RuntimeException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TbAdminUser record) throws RuntimeException;
    
    /**
     * 通过用户名与密码获取用户
     */
    TbAdminUserDto selectByUsernameAndPwd(String userName,String pwd) throws RuntimeException;
    
    /**
     * 查询用户名重复的记录
     */
    List<TbAdminUser> selectDuplicateRecord(String params,Integer checkType);
    
    /**
     * 分页查询 
     * @param example
     * @param pgo
     * @return
     * @throws RuntimeException
     */
    List<TbAdminUserDto> selectByObjectPage(Criteria example,PageDto pgo) throws RuntimeException;
}